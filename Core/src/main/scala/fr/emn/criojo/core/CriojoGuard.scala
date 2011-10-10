package fr.emn.criojo.core

import Criojo._
import fr.emn.criojo.util.Logger._

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 05/10/11
 * Time: 13:31
 */
class CriojoGuard(val starter:Atom, ruleDefs:List[RuleFactory => Rule], relList: List[Relation]) extends GuardCham with Guard{

//  var rules:List[Rule] = List()
//  override val solution = new GuardSolution(List[Atom]())
  addRelation(new LocalRelation("true"))
  addRelation(new LocalRelation("false"))

  initRules(ruleDefs)

  //Copy relations from owner CHAM
  def initRelations(){
    for(r <- relList){
      addRelation(r.copy(this.solution))
//      r match{
//        case nr:NativeRelation => addRelation(new NativeRelation(nr.name, this.solution, nr.f))
//        case _ => addRelation(new LocalRelation(r.name,r.isMultiRel))
//      }
    }
  }

  def empty = rules.isEmpty

  def eval(sol:Solution, subs:List[Substitution]):Boolean = {
    log("------------------------------------------------------")
    log("[CriojoGuard.eval] Begin " + this.toString)
    log("[CriojoGuard.eval] solution: " + sol)
//    log("[CriojoGuard.eval] substitutions: " + subs)

    levelDown
    this.solution.revert()
    this.solution.update(sol.copy(this))
    this.solution.addAtom(starter.applySubstitutions(subs))
    rules.foreach{r =>
      r.execute(List[Substitution]())
    }
    levelUp

    log("[CriojoGuard.eval] finished with solution: " + solution)
    log("------------------------------------------------------")
    solution.exists(a => a.relName == starter.relName)
  }

  override def toString = starter + ":" + printRules

//  def createRule(h: Head, b: Body, g: Guard, scope: List[Variable]) = new GuardRule(h, b, g, scope)

//  override def toString:String = {
//    starter + ":" + rules.mkString("",";","")
//  }

/*
  class GuardRule(h:List[Atom], val body:List[Atom], val guard:Guard, scp:List[Variable]) extends Rule{
    def head = h

    def execute(subs: List[Criojo.Substitution]) = {
      var matches = List[Atom]()
      var result = false

      if (this.isAxiom ||
              (head.size == 1 && head.filter(_.isActive).isEmpty) ||
              !{matches= query(head.filter(_.isActive),subs); matches}.isEmpty){
        log("[Rule.execute] {"+this+"} Substitutions: " + subs)
        log("[Rule.execute] solution= " + solution)
        log("[Rule.execute] Found Matches: " + matches + " for rule " + this)
        levelDown
        val subs2 = subs.union(getSubstitutions(matches))
        if (guard.empty || guard.eval(solution, subs2)){
          levelUp
          applyReaction(solution, subs2)
          result=true
        }else
          levelUp
      }

      //Activate atoms that were inactivated but not eliminated
      solution.revert
      result
    }

    def notifyRelationObservers(atom: Atom){}
  }
*/
}

class GuardSolution(var elems: List[Atom]) extends Solution{
  private var oldElements:List[Atom] = List()

  def addAtom(atom: Atom) { elems :+= atom  }

  def addMolecule(molecule: List[Atom]) { elems ++= molecule  }

  def remove(a: Atom){  elems.filterNot(_ == a) }

  def clear(){   elems = List[Atom]()  }

  def cleanup(){  elems = elems.filter(_.isActive)  }

  def update(newsol: Solution){
    if (newsol.contains(False) || newsol.isEmpty){
      clear()
    }else{
      this.elems = newsol.elems
    }
  }

  def inactivate(a: Atom){  a.setActive(false)  }

  def activate(a: Atom){  a.setActive(true)  }

  def copy(newOwner: CHAM) = new GuardSolution(elems.map(a => a.clone))

  def notifyCHAM(newAtom: Atom){}

  def createBackUp(){
  }
  def reverse(){
  }

}

// A simplified CHAM whose rules does not produce reactions, only reductions
class GuardCham extends CHAM{
  override val solution:Solution = new GuardSolution(List[Atom]())

  override def receiveUpdate(atom:Atom){
  /*Does nothing*/
  }

  override def initRule(rf: RuleFactory => Rule){
    val rule = rf(this)
    processRuleBody(List[RelVariable](), rule)
    addRule(rule)
  }

  override def findRelation(relName:String):Option[Relation] =
    super.findRelation(relName) match{
      case Some(r) => Some(r)
      case _ => Some(new LocalRelation(relName))
    }
}