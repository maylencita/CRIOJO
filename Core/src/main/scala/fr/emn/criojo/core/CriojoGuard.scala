package fr.emn.criojo.core

import Criojo._
import collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import fr.emn.criojo.lang.Molecule
import fr.emn.criojo.util.Logger._
import statemachine.StateMachine

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
    if (atom.isActive)
      addExecution(atom)
    else
      removeExecution(atom)
  }

  //TODO Implement in subclasses
  def eval(valuation:Valuation):Boolean = {
    eval(EmptySolution,valuation.toList)
  }
}

class ExistGuard(atoms:List[Atom]) extends CriojoGuard(atoms){
  def eval(sol: Solution, subs: List[Criojo.Substitution]) = {
    val newAtoms = atoms.map(_.applySubstitutions(subs))
    val finalState = states(size - 1)
    finalState.hasExecution(ex =>
      newAtoms.forall(nat=>ex.atoms.exists(exat=>exat.matches(nat)))
    )
  }

  override def toString = atoms.mkString("Exst(", ",", ")")
}

class AbsGuard(atoms:List[Atom]) extends ExistGuard(atoms){
  override def eval(sol: Solution, subs: List[Criojo.Substitution]) = {
    val finalState = states(size - 1)
    !finalState.hasExecutions || !super.eval(sol,subs)
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

