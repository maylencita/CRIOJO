package fr.emn.criojo.core

import Criojo._
import statemachine.StateMachine

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 05/10/11
 * Time: 13:31
 */
abstract class CriojoGuard extends Guard{
//with StateMachine with RelationObserver {

  val starter = null

//  init(atoms.toArray)

  def empty = false

//  def initRelations(){}
//
//  def onFinalState(){}

//  def receiveUpdate(atom: Atom){
//    if (atom.isActive)
//      addExecution(atom)
//    else
//      removeExecution(atom)
//  }

}

class PresenceGuard(val atoms:List[Atom]) extends CriojoGuard with StateMachine with RelationObserver{
  init(atoms.toArray)

  val finalState = states(size - 1)

  def initRelations(){}

  def onFinalState(){}

  def receiveUpdate(atom: Atom){
    if (atom.isActive)
      addExecution(atom)
    else
      removeExecution(atom)
  }

  def valuations:ValuationList =
    new ValuationList(finalState.executions.map{ex =>
      if(ex.valuation.isEmpty)
        new ValGenerator(TopValuation)
      else
        new ValGenerator(ex.valuation)
    }.toList)

  def eval(valuation: Valuation) = {
    valuations.exists(v => v.consistentWith(valuation))
  }

  override def toString= atoms.mkString("<",",",">")
}

class AbsGuard(atoms:List[Atom]) extends PresenceGuard(atoms){
  override def valuations = super.valuations.not
  override def toString = atoms.mkString("Abs(", ",", ")")
}

class ExistGuard(atoms:List[Atom]) extends CriojoGuard{
  def eval(vals: Valuation) = {
    //TODO implement
    true
  }

  override def toString = atoms.mkString("Exst(", ",", ")")
}

class AndGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard{

  def eval(vals: Valuation) = {
    lguard.eval(vals) && rguard.eval(vals)
  }

  override def toString = "["+lguard +" ^ "+ rguard +"]"
}

class OrGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard{
  def eval(vals:Valuation) = {
    lguard.eval(vals) || rguard.eval(vals)
  }
  override def toString = "["+lguard +" v "+ rguard +"]"
}

