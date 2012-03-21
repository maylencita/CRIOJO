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
        case Some(relation) => {
          var r = relation
          relation.notifyObservers(atom)
        }
        case _ => log(WARNING, this.getClass, "notifyCham", "Undefined relation " + atom.relName)
      }
  }

  def addRelation(relation:Relation) { relations :+= relation }
  def addRule(rule:Rule) {
    rules :+= rule
  }

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

