package fr.emn.criojo.core

import Criojo._
import statemachine.StateMachine

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 05/10/11
 * Time: 13:31
 */
abstract class CriojoGuard(val atoms:List[Atom]) extends Guard with StateMachine with RelationObserver {

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

}

class ExistGuard(atoms:List[Atom]) extends CriojoGuard(atoms){
  def eval(vals: Valuation) = {
    val newAtoms = atoms.map(_.applyValuation(vals))
    val finalState = states(size - 1)
    finalState.hasExecution(ex =>
      newAtoms.forall(nat=>ex.atoms.exists(exat=>exat.matches(nat)))
    )
  }

  override def toString = atoms.mkString("Exst(", ",", ")")
}

class AbsGuard(atoms:List[Atom]) extends ExistGuard(atoms){
  override def eval(vals: Valuation) = {
    val finalState = states(size - 1)
    !finalState.hasExecutions || !super.eval(vals)
  }

  override def toString = atoms.mkString("Abs(", ",", ")")
}

class AndGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard(lguard.atoms ++ rguard.atoms){
  def eval(vals: Valuation) = {
    lguard.eval(vals) && rguard.eval(vals)
  }

  override def toString = "["+lguard +" ^ "+ rguard +"]"
}

class OrGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard(lguard.atoms ++ rguard.atoms){
  def eval(vals:Valuation) = {
    lguard.eval(vals) || rguard.eval(vals)
  }

  override def toString = "["+lguard +" v "+ rguard +"]"
}

