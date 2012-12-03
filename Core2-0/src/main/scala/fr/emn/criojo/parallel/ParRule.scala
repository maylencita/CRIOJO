package fr.emn.criojo.parallel

import messages._
import fr.emn.criojo.core.{model,statemachine,engine}
import statemachine.{PartialExecution, StateMachine}
import engine.{Rule, Guard, CriojoGuard}
import model.{Variable, Atom, relation}
import relation.Relation

import fr.emn.criojo.core.engine.Reaction

import scala.actors.Actor

/**
* Created with IntelliJ IDEA.
* User: mayleen
* Date: 11/23/12
* Time: 6:06 PM
* To change this template use File | Settings | File Templates.
*/
class ParRule(val head:List[Atom], val body:List[Atom], val guard:Guard, scope:Set[Variable], owner:ActorBasedCham)
  extends Rule with Actor{

  object State extends Enumeration{
    type State = Value
    val Idle, Active = Value
  }
  import State._

  var id = 0
  var ruleStatus = Idle

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
    * the reaction is applied, nothing otherwise.
    *
    * @return  true if rule is executed, false otherwise.
    */
  def execute:Option[Reaction] = {
    val finalState = stateMachine.states(stateMachine.size - 1)
    if(finalState.hasExecutions){
      finalState.removeExecution(pe => guard.eval(pe.valuation)) match{
        case Some(pe:PartialExecution) => {
          applyReaction(pe)
        }
        case _ => None
      }
    }else{
      None
    }
  }

  private def updateStatus(){
    if (ruleStatus != Active){
      val finalState = stateMachine.states(stateMachine.size - 1)
      if(finalState.hasExecution(pe => guard.eval(pe.valuation))){
          owner ! Ready(this)
        ruleStatus = Active
      }
    }
  }

  def act(){
    loop{
      updateStatus()

      react{
        case Put(atom) if(filter(atom)) => stateMachine.addAtom(atom); notifyGuard(atom)
        case Remove(atom) if(filter(atom)) =>
          atom.setActive(false)
          stateMachine.removeAtom(atom)
          notifyGuard(atom)
        case Fire =>
          reply( execute match {
          case Some(execution) =>
            execution
          case other =>
              NotOk
          })
          ruleStatus = Idle
        case _ =>
      }
    }
  }

  private def applyReaction(finalExecution:PartialExecution):Option[Reaction] = {
    val finalValuation = finalExecution.valuation
    if(!finalValuation.isEmpty) {
      val newAtoms = this.body.map(_.applyValuation(finalValuation))
      val removeAtoms = for (i <- 0 until head.size; if !head(i).persistent) yield{
       finalExecution.atom(i)
      }
      Some(Reaction(removeAtoms, newAtoms))
    }else{
      None
    }
  }

  private def filter(atom:Atom):Boolean = {
    relations.exists(r=>atom.relation.name == r.name)
  }

  private def notifyGuard(atom:Atom) {
    guard match{
      case cg:CriojoGuard =>
        if (cg.observed.exists(r=>atom.relation.name == r.name))
          cg.receiveUpdate(atom)
      case _ => //No nothing
    }
  }

}
