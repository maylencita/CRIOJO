package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/11/11
 * Time: 10:05
 */
import fr.emn.criojo.core.Criojo._
import collection.immutable.HashSet
import statemachine.{StateMachine, PartialExecution}

/**
 * The StatefulEngine trait
 * @define THIS A
 * @define PARENT no other class
 * @define RESULT 3
 */
trait StatefulEngine extends Engine{

  def createRule(h: Head, b: Body, g: Guard, scope: List[Variable]) = new StatefulRule(h,b,g,scope)

  def initSolution = new HashSolution()

  def executeRules(){
    while (rules.exists(r => r.execute())){}
  }

  def introduceAtom(atom: Atom){
    solution.addAtom(atom)
    notifyRelationObservers(atom)
  }

  def removeAtom(atom: Atom){
    solution.remove(atom)
    atom.setActive(false)
    notifyRelationObservers(atom)
  }

  def getSolution = this.solution //EmptySolution

  class StatefulRule(val head:List[Atom], val body:List[Atom], val guard:Guard, scope:List[Variable])
    extends Rule with StateMachine{

    var ready:Boolean = false

    init(head.toArray)

    def receiveUpdate(atom: Atom){
      if (atom.isActive)
        addExecution(atom)
      else
        removeExecution(atom)
    }

    def execute(subs: List[Substitution]) = {
      var executed = false
      val finalState = states(size - 1)
      if(finalState.hasExecutions){
        finalState.removeExecution(pe => guard.eval(getSolution,pe.subs)) match{
          case Some(pe:PartialExecution) => applyReaction(pe); executed = true
          case _ => //Skip
        }
      }
      executed
    }

    def applyReaction(finalExecution:PartialExecution) {
      val scopeSubs = scope.map{v => val i=Indexator.getIndex; (v,v+("@"+i))}
      val newAtoms = this.body.map(_.applySubstitutions(finalExecution.subs.union(scopeSubs)))
      val removeAtoms = finalExecution.atoms

      removeAtoms.foreach{a => removeAtom(a)}
      newAtoms.foreach(a => introduceAtom(a))
    }

    def notifyCham(atom: Atom){}

    override def toString = {
      var str = super.toString + ": \n" +
        "\tPartial Executions: \n"
      for(s <- states){
        if (!s.executions.isEmpty)
          str += "\t"+ s +":"+ s.executions.mkString(";") + "\n"
      }
      str
    }
  }
}

class HashSolution extends Solution{
  var elements = new HashSet[Atom]()

  def createBackUp() = null

  def reverse() = null

  def elems = elements.toList

  def addAtom(atom: Atom){
    elements += atom
  }

  def addMolecule(molecule: List[Atom]){
    elements ++= molecule
  }

  def remove(atom: Atom){
    elements = elements.filterNot(a=> atom == a)
  }

  override def contains(atom:Atom) = elements.exists{a =>
    (a.relName == atom.relName) &&
      a.arity == atom.arity &&
      a.terms.zip(atom.terms).forall{t =>
        t._1 == t._2
      }
  }

  def clear(){}

  def cleanup(){}

  def update(newsol: Solution){}

  def inactivate(a: Atom){}

  def activate(a: Atom){}

  def notifyCHAM(newAtom: Atom){}
}

