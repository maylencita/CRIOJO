package fr.emn.criojo.core

import Criojo._
import collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import fr.emn.criojo.lang.Molecule
import fr.emn.criojo.util.Logger._

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 05/10/11
 * Time: 13:31
 */
abstract class CriojoGuard(val atoms:List[Atom]) extends Guard with StateMachine with RelationObserver{
  val starter = null

  init(atoms.toArray)

  def empty = false

  def initRelations(){}

  def onFinalState(){}

  def receiveUpdate(atom: Atom){
//println("Guard updated with: " + atom)
    if (atom.isActive)
      addExecution(atom)
    else
      removeExecution(atom)
  }
}

class ExistGuard(atoms:List[Atom]) extends CriojoGuard(atoms){
  def eval(sol: Solution, subs: List[Criojo.Substitution]) = {
    val newAtoms = atoms.map(_.applySubstitutions(subs))
    val finalState = states(size - 1)
    finalState.executions.exists{pe =>
      newAtoms.forall(na=>pe.atoms.exists(a=>a.matches(na)))
    }
//    newAtoms.forall(sol.contains(_))
  }
  override def toString = atoms.mkString("Exst(", ",", ")")
}

class AbsGuard(atoms:List[Atom]) extends ExistGuard(atoms){
  override def eval(sol: Solution, subs: List[Criojo.Substitution]) = {
    val finalState = states(size - 1)
    finalState.executions.isEmpty ||
      ! super.eval(sol,subs)
//    newAtoms.forall{a => !sol.contains(a)}
  }

  override def toString = atoms.mkString("Abs(", ",", ")")
}

class AndGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard(lguard.atoms ++ rguard.atoms){
  def eval(sol: Solution, subs: List[Criojo.Substitution]) = {
    lguard.eval(sol,subs) && rguard.eval(sol,subs)
  }

  override def toString = "["+lguard +" ^ "+ rguard +"]"
}

class OrGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard(lguard.atoms ++ rguard.atoms){
  def eval(sol: Solution, subs: List[Criojo.Substitution]) = {
    lguard.eval(sol,subs) || rguard.eval(sol,subs)
  }
  override def toString = "["+lguard +" v "+ rguard +"]"
}

// CUSTOM GUARDS

class EqualsGuard(term1:Term, term2:Term) extends CriojoGuard(List()){
  def eval(sol: Solution, subs: List[Criojo.Substitution]) = {

    var value1 : Term = null
    var value2 : Term = null

    term1 match {
      case x:ValueTerm[_] => {
        value1 = term1
      }
      case _ =>
    }

    term2 match {
      case x:ValueTerm[_] => {
        value2 = term2
      }
      case _ =>
    }

    subs.forall( s => {
      s match{
        case (x:Variable,v:Term) => {

          if(value1 == null && term1 == x) {
            value1 = v
          }

          if(value2 == null && term2 == x) {
            value2 = v
          }
        }
      }

      !(value1!=null && value2!=null)
    })

    value1.equals(value2)
  }
  override def toString = atoms.mkString("Equals(", ",", ")")
}

class NotEqualsGuard(term1:Term, term2:Term) extends CriojoGuard(List()){
  def eval(sol: Solution, subs: List[Criojo.Substitution]) = {

    var value1 : Term = null
    var value2 : Term = null

    term1 match {
      case x:ValueTerm[_] => {
        value1 = term1
      }
      case _ =>
    }

    term2 match {
      case x:ValueTerm[_] => {
        value2 = term2
      }
      case _ =>
    }

    subs.forall( s => {
      s match{
        case (x:Variable,v:Term) => {

          if(value1 == null && term1 == x) {
            value1 = v
          }

          if(value2 == null && term2 == x) {
            value2 = v
          }
        }
      }

      !(value1!=null && value2!=null)
    })

    !(value1.equals(value2))
  }
  override def toString = atoms.mkString("Equals(", ",", ")")
}

class AllAreTrueGuard(guards:List[Guard]) extends CriojoGuard(List()){
  def eval(sol: Solution, subs: List[Criojo.Substitution]) = {

    val allGuards = guards
    allGuards.forall(g => {

      g.eval(sol, subs)
    })
  }
  override def toString = atoms.mkString("ForEachTrue(", ",", ")")
}


//class CriojoGuard(val starter:Atom, ruleDefs:List[RuleFactory => Rule], relList: List[Relation]) extends GuardCham with Guard{
//
////  var rules:List[Rule] = List()
////  override val solution = new GuardSolution(List[Atom]())
//  addRelation(new LocalRelation("true"))
//  addRelation(new LocalRelation("false"))
//
//  initRules(ruleDefs)
//
//  //Copy relations from owner CHAM
//  def initRelations(){
//    for(r <- relList){
//      addRelation(r.copy(this.solution))
////      r match{
////        case nr:NativeRelation => addRelation(new NativeRelation(nr.name, this.solution, nr.f))
////        case _ => addRelation(new LocalRelation(r.name,r.isMultiRel))
////      }
//    }
//  }
//
//  def empty = rules.isEmpty
//
//  def eval(sol:Solution, subs:List[Substitution]):Boolean = {
//    log("------------------------------------------------------")
//    log("[CriojoGuard.eval] Begin " + this.toString)
//    log("[CriojoGuard.eval] solution: " + sol)
////    log("[CriojoGuard.eval] substitutions: " + subs)
//
//    levelDown
//    this.solution.revert()
//    this.solution.update(sol.copy(this))
//    this.solution.addAtom(starter.applySubstitutions(subs))
//    rules.foreach{r =>
//      r.executeRules(List[Substitution]())
//    }
//    levelUp
//
//    log("[CriojoGuard.eval] finished with solution: " + solution)
//    log("------------------------------------------------------")
//    solution.exists(a => a.relName == starter.relName)
//  }
//
//  override def toString = starter + ":" + printRules
//
////  def createRule(h: Head, b: Body, g: Guard, scope: List[Variable]) = new GuardRule(h, b, g, scope)
//
////  override def toString:String = {
////    starter + ":" + rules.mkString("",";","")
////  }
//
///*
//  class GuardRule(h:List[Atom], val body:List[Atom], val guard:Guard, scp:List[Variable]) extends Rule{
//    def head = h
//
//    def executeRules(subs: List[Criojo.Substitution]) = {
//      var matches = List[Atom]()
//      var result = false
//
//      if (this.isAxiom ||
//              (head.size == 1 && head.filter(_.isActive).isEmpty) ||
//              !{matches= query(head.filter(_.isActive),subs); matches}.isEmpty){
//        log("[Rule.executeRules] {"+this+"} Substitutions: " + subs)
//        log("[Rule.executeRules] solution= " + solution)
//        log("[Rule.executeRules] Found Matches: " + matches + " for rule " + this)
//        levelDown
//        val subs2 = subs.union(getHeadSubstitutions(matches))
//        if (guard.empty || guard.eval(solution, subs2)){
//          levelUp
//          applyReaction(solution, subs2)
//          result=true
//        }else
//          levelUp
//      }
//
//      //Activate atoms that were inactivated but not eliminated
//      solution.revert
//      result
//    }
//
//    def notifyCham(atom: Atom){}
//  }
//*/
//}
//
//class GuardSolution(var elems: List[Atom]) extends Solution{
//  private var oldElements:List[Atom] = List()
//
//  def addAtom(atom: Atom) { elems :+= atom  }
//
//  def addMolecule(molecule: List[Atom]) { elems ++= molecule  }
//
//  def remove(a: Atom){  elems.filterNot(_ == a) }
//
//  def clear(){   elems = List[Atom]()  }
//
//  def cleanup(){  elems = elems.filter(_.isActive)  }
//
//  def update(newsol: Solution){
//    if (newsol.contains(False) || newsol.isEmpty){
//      clear()
//    }else{
//      this.elems = newsol.elems
//    }
//  }
//
//  def inactivate(a: Atom){  a.setActive(false)  }
//
//  def activate(a: Atom){  a.setActive(true)  }
//
//  def copy(newOwner: CHAM) = new GuardSolution(elems.map(a => a.clone))
//
//  def notifyCHAM(newAtom: Atom){}
//
//  def createBackUp(){
//  }
//  def reverse(){
//  }
//
//}

// A simplified CHAM whose rules does not produce reactions, only reductions
//class GuardCham extends CHAM{
//  override val solution:Solution = new GuardSolution(List[Atom]())
//
//  override def receiveUpdate(atom:Atom){
//  /*Does nothing*/
//  }
//
//  override def initRule(rf: RuleFactory => Rule){
//    val rule = rf(this)
//    processRuleBody(List[RelVariable](), rule)
//    addRule(rule)
//  }
//
//  override def findRelation(relName:String):Option[Relation] =
//    super.findRelation(relName) match{
//      case Some(r) => Some(r)
//      case _ => Some(new LocalRelation(relName))
//    }
//}