package fr.emn.criojo.core

import statemachine.StateMachine

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 05/10/11
 * Time: 13:31
 */
abstract class CriojoGuard extends Guard{
  def empty = false

  /**
   * The set of valuations that satisfy this guard
   * @return
   */
  def valuations:ValuationList

  def eval(valuation: Valuation) = {
    valuations.exists(v => v.consistentWith(valuation))
  }
}

case class PresenceGuard(val atoms:List[Atom]) extends CriojoGuard with StateMachine with RelationObserver{
  init(atoms.toArray)

  val finalState = states(size - 1)

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
        NormalForm(TopValuation)
      else
        NormalForm(ex.valuation)
    }.toList)

  override def toString= atoms.mkString("<",",",">")
}

case class AbsGuard(override val atoms:List[Atom]) extends PresenceGuard(atoms){
  override def valuations = super.valuations.not
  override def toString = atoms.mkString("Abs(", ",", ")")
}

case class NotGuard(guard:CriojoGuard) extends CriojoGuard{
  def valuations = guard.valuations.not
}

case class ExistsGuard(guard:CriojoGuard, x:Variable) extends CriojoGuard{
  def gamma(nf: NormalForm):NormalForm = {
    if (nf.alpha.domain.contains(x)){
      NormalForm(nf.alpha.restrict(nf.alpha.domain-x),
        nf.beta.map(b =>
          b.restrict(b.domain-x)
        )
      )
    }else{
      val newBetas = nf.beta.filterNot(b => b.domain.contains(x))
      NormalForm(nf.alpha,newBetas)
    }
  }

  def valuations = guard.valuations.map(v => gamma(v))

  override def toString = "Exst("+x+"):" + guard
}

class ForAllGuard(guard:CriojoGuard, x:Variable) extends ExistsGuard(NotGuard(guard),x){
  override def valuations = super.valuations.not
}

case class OrGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard{
  def valuations = lguard.valuations or rguard.valuations

  override def toString = "["+lguard +" v "+ rguard +"]"
}

case class AndGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard{
  //TODO is there a more efficient way?
  def valuations = (lguard.valuations.not or rguard.valuations.not).not

  override def toString = "["+lguard +" ^ "+ rguard +"]"
}


