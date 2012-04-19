package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/11/11
 * Time: 10:05
 */

import collection.immutable.HashSet
import statemachine.{StateMachine, PartialExecution}
import fr.emn.criojo.ext.expression.types.StringVariable

/**
 * The StatefulEngine trait
 * @define THIS A
 * @define PARENT no other class
 * @define RESULT 3
 */
trait StatefulEngine extends Engine{

  def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]) = new StatefulRule(h,b,g,scope)

  def initSolution = new HashSolution()

  def executeRules(){
    while (rules.exists(r => r.execute)){}
  }

  def introduceAtom(atom: Atom){

    notifyRelationObservers(atom)
  }

  def removeAtom(atom: Atom){
    atom.setActive(false)
    notifyRelationObservers(atom)
  }

  def getSolution:Solution = this.solution //EmptySolution

  class StatefulRule(val head:List[Atom], val body:List[Atom], val guard:Guard, scope:Set[Variable])
    extends Rule with StateMachine{

    init(head.toArray)

    def receiveUpdate(atom: Atom){
      if (atom.isActive)
        addExecution(atom)
      else
        removeExecution(atom)
    }

    override def execute() = {
      var executed = false
      val finalState = states(size - 1)
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

    def applyReaction(finalExecution:PartialExecution) {
      val finalValuation = scope.foldLeft(finalExecution.valuation){(vals,sv) =>
        val i = Indexator.getIndex
        vals union Valuation(sv -> StringVariable(sv.name+"@"+i))
      }

      if(!finalValuation.isEmpty) {

        val newAtoms = this.body.map(_.applyValuation(finalValuation))

        val removeAtoms = for (i <- 0 until head.size; if !head(i).persistent) yield{
          finalExecution.atom(i)
        }

        removeAtoms.foreach{a => removeAtom(a)}
        newAtoms.foreach(a => introduceAtom(a))
      }
    }

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

  def displaySolution() {
    var firstPrint:Boolean = true
    print("<")
    
    var cpt:Int = 0
    
    elements.foreach( a => {
      if(a.relName.charAt(0)!='$') {
        if(!firstPrint)
          print(",")
        cpt = (cpt+1)%10
        if(cpt!=9)
          print(a)
        else
          println(a)
        firstPrint = false
      }
    })
    println(">")
  }

  def createBackUp() {
    null
  }

  def reverse() {
    null
  }

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

