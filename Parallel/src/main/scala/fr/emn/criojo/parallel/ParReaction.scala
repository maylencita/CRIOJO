package fr.emn.criojo.parallel

import messages._
import fr.emn.criojo.core._
import fr.emn.criojo.ext.expression.Relation.Relation
import datatype.{Term, Variable}
import impur.NativeRelation
import statemachine.{PartialExecution, StateMachine}

import scala.actors.Actor

/**
* Created with IntelliJ IDEA.
* User: mayleen
* Date: 11/23/12
* Time: 6:06 PM
* To change this template use File | Settings | File Templates.
*/
class ParReaction(val head:List[Atom], val body:List[Atom], val guard:Guard, scope:Set[Variable], owner:Dispatcher)
  extends Rule with Actor{

  //Messages are filtered by relation
  val relations = {
    var rset = Set[Relation]()
    for (a <- head)
      rset += a.relation
    guard match{
      case cg:CriojoGuard=>cg.observed.foreach {r => rset += r}
      case _ => //
    }
    rset
  }
  val stateMachine = new StateMachine(head.toArray)

  /** Executes rule.
    *
    * Test if the rule has to be executed. If the rule has to be executed,
    * the reaction is applying, nothing otherwise.
    *
    * @return  true if rule is executed, false otherwise.
    */
  override def execute() = {
    var executed = false
    val finalState = stateMachine.states(stateMachine.size - 1)
    if(finalState.hasExecutions){
      finalState.removeExecution(pe => guard.eval(pe.valuation)) match{
        case Some(pe:PartialExecution) => {
          applyReaction(pe)

          executed = true
        }
        case _ => //Skip
      }
    }
    executed
  }

  def receiveUpdate(atom: Atom) {}

  def act = {
    loop{
      react{
        case Put(atom) if(filter(atom)) => stateMachine.addExecution(atom); notifyGuard(atom)
        case Remove(atom) if(filter(atom)) => stateMachine.removeExecution(atom)
        case Ready() => reply(if (execute()){ Ok() }else{ NotOk()})
        case _ =>
      }
    }
  }

  private def filter(atom:Atom):Boolean = {
    relations.exists(r=>atom.relation.name == r.name)
  }

  private def applyReaction(finalExecution:PartialExecution) {
    val finalValuation = finalExecution.valuation

    if(!finalValuation.isEmpty) {

      val newAtoms = this.body.map(_.applyValuation(finalValuation))

      val removeAtoms = for (i <- 0 until head.size; if !head(i).persistent) yield{
        finalExecution.atom(i)
      }

      removeAtoms.foreach(a => owner ! Remove(a))
      newAtoms.foreach(a => owner ! Put(a))
    }
  }

  private def notifyGuard(atom:Atom) {
    guard match{
      case cg:CriojoGuard => cg.receiveUpdate(atom)
      case _ => //No nothing
    }
  }
}
