package fr.emn.criojo.core.engine

import fr.emn.criojo.core.model._
import relation.Relation
import fr.emn.criojo.core.statemachine.StateMachine
import fr.emn.criojo.observer.RelationObserver

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 05/10/11
 * Time: 13:31
 */
abstract class CriojoGuard extends Guard with RelationObserver{
  def empty = false

  /**
   * The set of valuations that satisfy this guard
   * @return
   */
  def valuations:ValuationList = new ValuationList()

  /**
   * Returns the list of ids of observed relations
   * @return
   */
  def observed:Set[Relation]

  /**
   * Notifies the guard of the addition of destruction of an Atom
   * @param atom
   */
  def receiveUpdate(atom: Atom)

  def eval(valuation: Valuation) = {
    val result = valuations.exists(v => v.consistentWith(valuation))
//println("[CriojoGuard] eval: valuation= " + valuation + " this.valuations=" + valuations + " => " + result)
    result
  }

  implicit def valuationToValuationList(valuation:Valuation):ValuationList = {

     new ValuationList(List(new NormalForm(valuation, List())))
  }

}

case class PresenceGuard(atoms:List[Atom]) extends CriojoGuard {//with StateMachine{
  //init(atoms.toArray)
  val stateMachine = new StateMachine(atoms.toArray)

  val finalState = stateMachine.states(stateMachine.size - 1)

  def onFinalState(){}

  def observed = atoms.map(a => a.relation).toSet

  def receiveUpdate(atom: Atom){
    if (atom.isActive){
      stateMachine.addAtom(atom)
    }else{
      stateMachine.removeAtom(atom)
    }
  }

  override def valuations:ValuationList =
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

  def observed = guard.observed

  override def valuations = guard.valuations.not

  def receiveUpdate(atom: Atom){guard.receiveUpdate(atom)}
}

case class ExistsGuard(guard:CriojoGuard, x:Variable) extends CriojoGuard{
  def observed = guard.observed

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

  override def valuations = guard.valuations.map(v => gamma(v))

  def receiveUpdate(atom: Atom){guard.receiveUpdate(atom)}

  override def toString = "Exst("+x+"):" + guard
}

class ForAllGuard(guard:CriojoGuard, x:Variable) extends ExistsGuard(NotGuard(guard),x){
  override def valuations = super.valuations.not
}

case class OrGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard{

  def observed = lguard.observed ++ rguard.observed

  override def valuations = lguard.valuations or rguard.valuations

  def receiveUpdate(atom: Atom){lguard.receiveUpdate(atom);rguard.receiveUpdate(atom)}

  override def toString = "["+lguard +" v "+ rguard +"]"
}

case class AndGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard{

  def observed = lguard.observed ++ rguard.observed

  override def valuations = lguard.valuations intersect rguard.valuations

  def receiveUpdate(atom: Atom){lguard.receiveUpdate(atom);rguard.receiveUpdate(atom)}

  override def toString = "["+lguard +" ^ "+ rguard +"]"
}


