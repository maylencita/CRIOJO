package fr.emn.criojo.core

import datatype._
import statemachine.StateMachine
import fr.emn.criojo.ext.expression.Relation.Relation

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 05/10/11
 * Time: 13:31
 */
abstract class CriojoGuard extends Guard {
  def empty = false

  /**
   * The set of valuations that satisfy this guard
   * @return
   */
  def valuations(valuation:Valuation):ValuationList = new ValuationList()

  /**
   * Returns the list of ids of observed relations
   * @return
   */
  //  def observed:Set[Relation]

  def eval(valuation: Valuation) = {
    valuations(valuation:Valuation).exists(v => v.consistentWith(valuation))
  }

  implicit def valuationToValuationList(valuation:Valuation):ValuationList = {

    new ValuationList(List(new NormalForm(valuation, List())))
  }
}

case class PresenceGuard(atoms:List[Atom], engine:CriojoEngine) extends CriojoGuard {
  //init(atoms.toArray)

  //val finalState = states(size - 1)

  //  def onFinalState(){}

  //  def observed = atoms.map(a => a.relation).toSet

  //  def receiveUpdate(atom: Atom){
  //    if (atom.isActive)
  //      addExecution(atom)
  //    else
  //      removeExecution(atom)
  //  }

//  override def valuations(valuation:Valuation):ValuationList = {
//    if(engine.solution.containsAll(atoms.map(a => a.applyValuation(valuation))))
//      new ValuationList(List(NormalForm(TopValuation)))
//    else
//      new ValuationList(List(NormalForm(BottomValuation)))
//    //new ValuationList(List(NormalForm(TopValuation)))
//  }

  override def valuations(valuation:Valuation):ValuationList = {
    var forms:List[NormalForm] = List()

    (atoms.map( a => {
      if(engine.solution.indexOfAtoms.contains(a.relation.name)) {
        //NormalForm(TopValuation)
        var normalForms = engine.solution.indexOfAtoms.get(a.relation.name).get.map(a2 => {
          var currentValuation = a.getValuation(a2)
          NormalForm( currentValuation, List())
        })
        normalForms.foreach( f => forms = f :: forms)
      }
      else {
        //forms = NormalForm(TopValuation) :: forms
      }

    }))
    var result = new ValuationList(forms)
    result
    //new ValuationList(List(NormalForm(TopValuation)))
  }
  //    new ValuationList(finalState.executions.map{ex =>
  //      if(ex.valuation.isEmpty)
  //        NormalForm(TopValuation)
  //      else
  //        NormalForm(ex.valuation)
  //    }.toList)

  override def toString= atoms.mkString("<",",",">")
}

case class AbsGuard(override val atoms:List[Atom], override val engine:CriojoEngine) extends PresenceGuard(atoms, engine){
  override def valuations(valuation:Valuation) = super.valuations(valuation:Valuation).not

  override def toString = atoms.mkString("Abs(", ",", ")")
}

case class NotGuard(guard:CriojoGuard) extends CriojoGuard{

  //  def observed = guard.observed

  override def valuations(valuation:Valuation) = guard.valuations(valuation).not

  //  def receiveUpdate(atom: Atom){guard.receiveUpdate(atom)}
}

case class ExistsGuard(guard:CriojoGuard, x:Variable) extends CriojoGuard {
  //  def observed = guard.observed

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

  override def valuations(valuation:Valuation) = guard.valuations(valuation).map(v => gamma(v))

  //  def receiveUpdate(atom: Atom){guard.receiveUpdate(atom)}

  override def toString = "Exst("+x+"):" + guard
}

class ForAllGuard(guard:CriojoGuard, x:Variable) extends ExistsGuard(NotGuard(guard),x){
  override def valuations(valuation:Valuation) = super.valuations(valuation).not
}

case class OrGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard{

  //  def observed = lguard.observed ++ rguard.observed

  override def valuations(valuation:Valuation) = lguard.valuations(valuation) or rguard.valuations(valuation)

  //  def receiveUpdate(atom: Atom){lguard.receiveUpdate(atom);rguard.receiveUpdate(atom)}

  override def toString = "["+lguard +" v "+ rguard +"]"
}

case class AndGuard(lguard:CriojoGuard, rguard:CriojoGuard) extends CriojoGuard{

  //  def observed = lguard.observed ++ rguard.observed

  override def valuations(valuation:Valuation) = lguard.valuations(valuation) intersect rguard.valuations(valuation)

  //  def receiveUpdate(atom: Atom){lguard.receiveUpdate(atom);rguard.receiveUpdate(atom)}

  override def toString = "["+lguard +" ^ "+ rguard +"]"
}


