package fr.emn.criojo.sequential

import fr.emn.criojo.core.engine.{Reaction, Cham, Rule, Guard, CriojoGuard}
import fr.emn.criojo.core.model.{Variable, Atom, relation}
import relation._
import fr.emn.criojo.core.statemachine.{PartialExecution, StateMachine}
import fr.emn.criojo.observer.{ObservedRelation, RelationObserver}

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/26/12
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */
trait ObserverPatternCham extends Cham{

  def LocalRelation(name:String) = new LocalRelation(name) with ObservedRelation

  def InputChannel(name: String):Channel = new InChannel(name,new ChannelLocation(this.location,name)) with ObservedRelation
  def OutputChannel(name: String, location: String):OutChannel = new OutChannel(name, new ChannelLocation(location,name)) {}

  def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]) = {
    val rule = new StatefulRule(h,b,g,scope)
    processRuleHead(rule)
    processRuleBody(rule)
    rule
  }

  def executeRules(){
    while (rules.exists(r => executeRule(r))){}
  }

  def executeRule(rule:Rule):Boolean = rule.execute match{
    case Some(reaction) =>
      applyReaction(reaction)
      true
    case _ => false
  }

  def introduceAtom(atom: Atom){
    atom.reduce()
    notifyRelationObservers(atom)
  }

  def removeAtom(atom: Atom) {
    atom.setActive(false)
    notifyRelationObservers(atom)
  }

  private def notifyRelationObservers(atom: Atom){
    atom.relation match{
      case or:ObservedRelation => or.notifyObservers(atom)
      case _ =>
    }
  }

  private def processRuleHead(rule:StatefulRule) {
    if (!rule.isAxiom)
      rule.head.foreach{ a => addObserver(a.relation, rule)}
  }

  private def processRuleBody(rule:StatefulRule)  {
    rule.guard match{
      case cg:CriojoGuard =>
        cg.observed.foreach{ relation => addObserver(relation,cg) }
      case _ =>
    }
  }

  private def addObserver(relation:Relation, observer:RelationObserver){
    relation match{
      case or:ObservedRelation => or.addObserver(observer)
      case _ =>
    }
  }

  class StatefulRule(val head:List[Atom], val body:List[Atom], val guard:Guard, scope:Set[Variable])
    extends Rule with RelationObserver{ //with StateMachine {

    //init(head.toArray)
    val stateMachine = new StateMachine(head.toArray)

    def receiveUpdate(atom: Atom){
      if (atom.isActive)
        stateMachine.addAtom(atom)
      else
        stateMachine.removeAtom(atom)
    }

    def execute:Option[Reaction] = {
      val finalState = stateMachine.states(stateMachine.size - 1)
      if(finalState.hasExecutions){
        finalState.removeExecution(pe => guard.eval(pe.valuation)) match{
          case Some(pe:PartialExecution) => {
            applyReaction(pe)
          }
          case _ => None
        }
      }
      None
    }

    def applyReaction(finalExecution:PartialExecution):Option[Reaction] = {
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

    override def toString = {
      var str = super.toString + ": \n" +
        "\tPartial Executions: \n"
      for(s <- stateMachine.states){
        if (!s.executions.isEmpty)
          str += "\t"+ s +":"+ s.executions.mkString(";") + "\n"
      }
      str
    }
  }

}
