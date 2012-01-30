package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 17/11/11
 * Time: 17:18
 */
import Criojo._
import fr.emn.criojo.util.Logger._

trait Engine extends RuleFactory{
  protected var index = 0

  protected var rules:List[Rule] = List()
  protected var relations:List[Relation] = List()
  protected val solution:Solution = initSolution

  def initSolution:Solution
  def executeRules()
  def introduceAtom(atom:Atom)
  def getSolution:Solution

  @Deprecated //("Use notifyRelationObservers")
  def receiveUpdate(atom:Atom){}

  def notifyRelationObservers(atom: Atom){
    if (atom.relation != null)
      atom.relation.notifyObservers(atom)
    else
      findRelation(atom.relName) match{
        case Some(relation) =>
          relation.notifyObservers(atom)
        case _ => log(WARNING, this.getClass, "notifyCham", "Undefined relation " + atom.relName)
      }
  }

  def addRelation(relation:Relation) { relations :+= relation }
  def addRule(rule:Rule) { rules :+= rule }

  /**
   * Searches for a relation. If found, Some[Relation] is returned.
   * Otherwise, it will return Nothing
   */
  def findRelation(relName:String):Option[Relation] = relations.find(_.name == relName)

  def getRelation(relName:String):Relation = findRelation(relName) match{
      case Some(r) => r
      case _ =>
        if(relName startsWith ("$"))
          new LocalRelation(relName)
        else{
          log(WARNING, this.getClass, "findRelation","Undefined relation " + relName)
          new LocalRelation("Undefined")
        }
  }

  def newLocalRelation(name:String,public:Boolean):LocalRelation = {
    new LocalRelation(name, public)
  }

  def newRemoteRelation(remoteName:String,url:String):RemoteRelation = {
    throw new IllegalAccessException("Unimplemented method newRemoteRelation.")
  }

  def initRules(ruleDefs:List[RuleFactory => Rule]){
    ruleDefs.foreach{ f => initRule(f)  }
  }

  def initRule(rf: RuleFactory => Rule){
    val rule = rf(this)
    processRuleBody(processRuleHead(rule), rule)
    addRule(rule)
  }

  def processRuleHead(rule:Rule):List[RelVariable]={
    var headVars = List[RelVariable]()

    if (!rule.isAxiom)
      rule.head.foreach{a =>
        findRelation(a.relName) match{
          case Some(r) => r.addObserver(rule)
          case _ =>
            log(WARNING, this.getClass, "addRule","Undefined relation " + a.relName)
            a.relation = new LocalRelation("Undefined")
        }
        a.terms.foreach{
          case rv:RelVariable => headVars :+= rv
          case _ =>
        }
      }
    headVars
  }

  def processRuleBody(headVars:List[RelVariable], rule:Rule){
    rule.guard match{
      case cg:CriojoGuard =>
        cg.atoms.foreach{a =>
          findRelation(a.relName) match{
            case Some(r) => r.addObserver(cg)
            case _ => a.relation = new LocalRelation("Undefined")
          }
        }
      case _ =>
    }
    rule.body.foreach{a =>
      a.relation = headVars.find(hv => hv.name == a.relName) match{
        case Some(hv) => hv.relation
        case _=> getRelation(a.relName)
      }
      a.terms.foreach{
        case rv: RelVariable if(!headVars.contains(rv)) =>
          findRelation(rv.name) match{
            case Some(r) => rv.relation = r
            case _ => log(WARNING, this.getClass, "addRule", "Undefined relation variable " + rv.name);
          }
        case _ =>
      }
    }
  }

  def printRules: String = rules.mkString("","\n","")

}

trait SimpleEngine extends Engine{

  def initSolution:Solution = Solution(this)

  def getSolution:Solution = solution

  def executeRules(){
    while (rules.exists(r => r.isAxiom && r.execute)){}
  }

  def introduceAtom(atom:Atom){
    solution.addAtom(atom)
  }

  def query(conj:List[Atom], subs:List[Substitution]):List[Atom] = solution.findMatches(conj, subs)

  def querySansEffect(conj:List[Atom]):List[Atom] = {
    val lstresult= query(conj, conj.flatMap(a=>a.vars.zip(a.terms)).filterNot(p => p._1 == Undef))
    solution.revert()
    lstresult
  }

  def createRule(h:Head,b:Body,g:Guard,scope:List[Variable]):Rule = new CHAMRule(h, b, g, scope)

  class CHAMRule(h:List[Atom], val body:List[Atom], val guard:Guard, scp:List[Variable]) extends Rule{
    def this() = this(List(), List(), EmptyGuard, List())

    scope = scp
    val head:List[HeadAtom] = h.map{h => new HeadAtom(h)}

    def receiveUpdate(atom:Atom){
      this.head.foreach{a=>
        if(a.relName == atom.relName){
          a.receiveUpdate(atom)
        }
      }
    }

    def notifyCham(atom:Atom){
//      receiveUpdate(atom)
      if (atom.relation != null)
        atom.relation.notifyObservers(atom)
      else
        findRelation(atom.relName) match{
          case Some(relation) =>
            relation.notifyObservers(atom)
          case _ => log(WARNING, this.getClass, "notifyCham", "Undefined relation " + atom.relName)
        }
    }

    def execute (subs:List[Substitution]):Boolean = {
      var matches = List[Atom]()
      var result = false

      if (this.isAxiom ||
              (head.size == 1 && head.filter(_.isActive).isEmpty) ||
              !{matches= query(head.filter(_.isActive),subs); matches}.isEmpty){
        log("[Rule.executeRules] {"+this+"} Substitutions: " + subs)
//        log("[Rule.executeRules] solution= " + solution)
//        log("[Rule.executeRules] Found Matches: " + matches + " for rule " + this)
        levelDown
        val subs2 = subs.union(getHeadSubstitutions(matches))
        if (guard.empty || guard.eval(solution, subs2)){
          levelUp
          applyReaction(solution, subs2)
          result=true
        }else
          levelUp
      }

      //Activate atoms that were inactivated but not eliminated
      solution.revert()
      result
    }

    class HeadAtom(relName:String, terms: List[Term]) extends Atom(relName, terms) /*with RelationObserver*/{
      def this(a:Atom) = this(a.relName, a.terms)

      def receiveUpdate(atom:Atom){
        if (atom.isActive){
          val subs = scope.map{v => (v,v+"@"+Indexator.getIndex)}.union(getSubstitutions(this.terms, atom.terms))
          if(this.applySubstitutions(subs).matches(atom)){
            this.active = false
            atom.setActive(false)

            log("============================================================================")
      //      Logger.log(this.getClass,"receiveUpdate","this: " + this)
            levelDown
            if(!execute(subs)){
              atom.setActive(true)
              this.active = true
            }
            levelUp
      //    Logger.log(this.getClass, "receiveUpdate", "Final solution: " + solution)
            log("============================================================================")
          }
        }
      }
    }

  }

}