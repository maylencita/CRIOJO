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

  /**
   * A guard is of the form:
   *
   * g := T | F | g v g | g ^ g | not(g) | m --> g
   *
   * where m is a set of atoms.
   * @return the sub-guard in m --> '''g'''
   */
  def subGuard:Guard

//  /**
//   * The set of valuations that satisfy this guard
//   * @return
//   */
//  def valuations:ValuationList = new ValuationList()

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

//  def eval(valuation: Valuation) = {
//    val result = valuations.exists(v => v.consistentWith(valuation))
////println("[CriojoGuard] eval: valuation= " + valuation + " this.valuations=" + valuations + " => " + result)
//    result
//  }

//  implicit def valuationToValuationList(valuation:Valuation):ValuationList = {
//
//     new ValuationList(List(new NormalForm(valuation, List())))
//  }

}

object TrueGuard extends CriojoGuard{
  def empty = true

  def eval(valuation: Valuation) = true

  def subGuard = null

  def observed = Set[Relation]()

  def receiveUpdate(atom: Atom) {}
}

object FalseGuard extends CriojoGuard{
  def empty = true

  def eval(valuation: Valuation) = false

  def subGuard = null

  def observed = Set[Relation]()

  def receiveUpdate(atom: Atom) {}
}

case class SuchThatGuard(atoms:List[Atom], subGuard:Guard) extends CriojoGuard{
  val empty = false

  private val stateMachine = new StateMachine(atoms.toArray)

  private val finalState = stateMachine.states(stateMachine.size - 1)

  def eval(valuation: Valuation) = {
    finalState.hasExecution(pe => pe.valuation.hasExtension(valuation) &&
      subGuard.eval(pe.valuation.union(valuation)))
  }

  /**
   * Returns the list of ids of observed relations
   * @return
   */
  def observed = atoms.map(a => a.relation).toSet

  /**
   * Notifies the guard of the addition of destruction of an Atom
   * @param atom
   */
  def receiveUpdate(atom: Atom) {
    if (atom.isActive){
      stateMachine.addAtom(atom)
    }else{
      stateMachine.removeAtom(atom)
    }

    //Update subguard also
    subGuard match{
      case cg:CriojoGuard => cg.receiveUpdate(atom)
      case _ => //Nothing
    }
  }

  override def toString = atoms.mkString(",") + "->" + subGuard
}

case class PresenceGuard(alst:List[Atom]) extends SuchThatGuard(alst, TrueGuard) {
  override def toString= atoms.mkString("<",",",">")
}

case class AbsGuard(override val atoms:List[Atom]) extends PresenceGuard(atoms){
//  override def valuations = super.valuations.not
  override def eval(valuation: Valuation) = !super.eval(valuation)
  override def toString = atoms.mkString("Abs(", ",", ")")
}

case class NotGuard(guard:Guard) extends CriojoGuard{

  val empty = false
  val subGuard = TrueGuard

  def observed = guard match{
    case cg:CriojoGuard => cg.observed
    case _ => Set[Relation]()
  }

//  override def valuations = guard.valuations.not

  def receiveUpdate(atom: Atom){
    guard match{
      case cg:CriojoGuard => cg.receiveUpdate(atom)
      case _ =>
    }
  }

  def eval(valuation:Valuation) = !guard.eval(valuation)
}

//case class ExistsGuard(guard:CriojoGuard, x:Variable) extends CriojoGuard{
//  def observed = guard.observed
//
//  def gamma(nf: NormalForm):NormalForm = {
//    if (nf.alpha.domain.contains(x)){
//      NormalForm(nf.alpha.restrict(nf.alpha.domain-x),
//        nf.beta.map(b =>
//          b.restrict(b.domain-x)
//        )
//      )
//    }else{
//      val newBetas = nf.beta.filterNot(b => b.domain.contains(x))
//      NormalForm(nf.alpha,newBetas)
//    }
//  }
//
//  override def valuations = guard.valuations.map(v => gamma(v))
//
//  def receiveUpdate(atom: Atom){guard.receiveUpdate(atom)}
//
//  override def toString = "Exst("+x+"):" + guard
//}

//class ForAllGuard(guard:CriojoGuard, x:Variable) extends ExistsGuard(NotGuard(guard),x){
//  override def valuations = super.valuations.not
//}

case class OrGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard{

  val empty = false
  val subGuard = TrueGuard

  def observed = lguard.observed ++ rguard.observed

//  override def valuations = lguard.valuations or rguard.valuations

  def receiveUpdate(atom: Atom){lguard.receiveUpdate(atom);rguard.receiveUpdate(atom)}

  def eval(valuation: Valuation) = lguard.eval(valuation) || rguard.eval(valuation)

  override def toString = "["+lguard +" v "+ rguard +"]"

}

case class AndGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard{

  val empty = false
  val subGuard = TrueGuard

  def observed = lguard.observed ++ rguard.observed

//  override def valuations = lguard.valuations intersect rguard.valuations

  def receiveUpdate(atom: Atom){lguard.receiveUpdate(atom);rguard.receiveUpdate(atom)}

  def eval(valuation: Valuation) = lguard.eval(valuation) && rguard.eval(valuation)

  override def toString = "["+lguard +" ^ "+ rguard +"]"
}


