package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: Jonathan
 * Date: 28/06/12
 * Time: 09:35
 */

import collection.mutable.{ListBuffer, HashMap}
import datatype.{ BottomValuation, Valuation, Variable}

/**
 * The NormalEngine trait
 * @define THIS A
 * @define PARENT no other class
 * @define RESULT 3
 */
trait NormalEngine extends Engine {

  var isRunning:Boolean = false

  val solution:Solution = new Solution()

  var indexAtomPartialStateExecution:HashMap[Atom, ListBuffer[PartialStateExecution]] = HashMap()

  def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]) = new NormalRule(h, b, g, scope, this)

  override def reactionStrategy: ReactionStrategy = new LocalReactionStrategy()

  def addToExecutionIndex(a:Atom, pe:PartialStateExecution) {
    if(!indexAtomPartialStateExecution.contains(a)) {
      indexAtomPartialStateExecution.put(a, ListBuffer())
    }

    indexAtomPartialStateExecution.get(a).get += pe
  }

  def removeFromExecutionIndex(atom:Atom) {
    if(indexAtomPartialStateExecution.contains(atom)) {
      indexAtomPartialStateExecution.get(atom).get.foreach( pse => {
        if(pse.isFinal())
          pse.state.rule.removeFinalExecution(pse)
        pse.state.executions -= pse
      })
      indexAtomPartialStateExecution.remove(atom)
    }
  }

  def executeRules() {
    while (rules.exists(r => r.execute)) {}
  }

  def introduceAtom(atom: Atom) {
    solution.addAtom(atom)

    mapAtomRules.get(atom.relation.name) match {
      case s:Some[ListBuffer[NormalRule]] => s.get.foreach( r => r.addToExecution(atom))
      case None =>
    }
  }

  def removeAtom(atom: Atom) {
    solution.removeAtom(atom)
  }

  val mapAtomRules:HashMap[String, ListBuffer[NormalRule]] = HashMap()

  object NormalRule {

    def createPartialStateExecution (atoms:ListBuffer[Atom], state:PartialState) =
      new PartialStateExecution(atoms, state)
  }
}

class NormalRule(val head: List[Atom], val body: List[Atom], val guard: Guard, scope: Set[Variable], var engine:NormalEngine)
  extends Rule {



  var variableNameList:List[String] = List()
  var mapOfAtoms:HashMap[String, ListBuffer[Atom]] = HashMap()

  var stateMachinesPerAtom:HashMap[String, ListBuffer[PartialState]] = HashMap()
  var finalExecutionPerAtom:HashMap[String, ListBuffer[PartialStateExecution]] = HashMap()

  head.foreach( a => {

    // add all the variables to the needVariableList
    a.patterns.foreach( p => p match {
      case v:Variable => if(!variableNameList.contains(v.name))
        variableNameList = v.name :: variableNameList
      case _ =>
    })

    if (!mapOfAtoms.contains(a.relation.name))
      mapOfAtoms.put(a.relation.name, ListBuffer())

    mapOfAtoms.get(a.relation.name).get += a
    // create the
  })

  // construction of the partial state machines
  mapOfAtoms.foreach(c => {
    var permutations = c._2.permutations.toList
    var states:ListBuffer[PartialState] = ListBuffer()
    permutations.foreach(p => {
      states += new PartialState(p.toList, ListBuffer(), this, c._1)
    })
    stateMachinesPerAtom.put(c._1, states)

    if (!engine.mapAtomRules.contains(c._1))
      engine.mapAtomRules.put(c._1, ListBuffer[NormalRule]())
    engine.mapAtomRules.get(c._1).get += this
  })

  def addFinalExecution(pe:PartialStateExecution) {
    if (!finalExecutionPerAtom.contains(pe.state.atomName))
      finalExecutionPerAtom.put(pe.state.atomName, ListBuffer())

    finalExecutionPerAtom.get(pe.state.atomName).get += pe

    if(finalExecutionPerAtom.get(pe.state.atomName).get.size > 30) {
      execute()
    }
  }

  def removeFinalExecution(pe:PartialStateExecution) {
    if (finalExecutionPerAtom.contains(pe.state.atomName))
      finalExecutionPerAtom.get(pe.state.atomName).get -= pe
  }

  def addToExecution(atom:Atom) {
    stateMachinesPerAtom.get(atom.relation.name).get.foreach {s => s.addAtomToExecutions(atom)}
  }

  def clean(atom:Atom) {

    engine.removeFromExecutionIndex(atom)
  }

  def canBeOk(solution:Solution):Boolean = {

    mapOfAtoms.forall( c => {

      solution.indexOfAtoms.get(c._1) != None && solution.indexOfAtoms.get(c._1).get.size >= c._2.size
    } )
  }

  def findOneCombination(solution:Solution, combination:ListBuffer[Atom], guard:Guard):Boolean = {

    var executed = false

    def flattenCombination(listOfPartialStates:List[PartialStateExecution]):Valuation =
      listOfPartialStates.foldLeft(Valuation()) { case(v,c) => v.union(c.vals) }

    def isOneMatchingCombination(listOfPartialStates:List[PartialStateExecution]):Boolean =
      listOfPartialStates.foldLeft(Valuation()) { case(v,c) => v.union(c.vals) } match {
        case BottomValuation => false
        case _ => true
      }

    var listsOfListOfPartialExecution:ListBuffer[ListBuffer[PartialStateExecution]] = ListBuffer()

    // A, B, C ....
    if(finalExecutionPerAtom.forall {c => c._2.size>0} ) {
      finalExecutionPerAtom.foreach {c => {
        listsOfListOfPartialExecution += c._2;
      }}
    }

    val combination:Combinatory[PartialStateExecution] = Combinatory.from(listsOfListOfPartialExecution)



    if(combination != null) {
      var continue = combination.get() != Nil

      while(continue) {
        val listOfPartialExecution:List[PartialStateExecution] = combination.get()
        if(isOneMatchingCombination(listOfPartialExecution)) {
          var result:Valuation= flattenCombination(listOfPartialExecution)
          var i = Atom.atomCount
          if(guard.eval(result)) {
            applyReaction(listOfPartialExecution, result)
            executed = true
            continue = false
          }
          else {
            combination.next()
            continue = combination.get() != Nil
          }
        }
        else {
          combination.next()
          continue = combination.get() != Nil
        }
      }
    }

    executed
  }

  def getValuation(listOfAtoms:ListBuffer[Atom]):Valuation = Valuation()

  def receiveUpdate(atom: Atom) {
  }

  override def execute() = {
    var result = canBeOk(engine.solution) && findOneCombination(engine.solution, ListBuffer[Atom](), guard)
    result
  }

  def applyReaction(pes:List[PartialStateExecution], valuation:Valuation) {


    pes.foreach( pe => {
      pe.atoms.foreach(a => {
        clean(a)
        engine.removeAtom(a)
      })
      pe.state.removeExecution(pe)
    })

    val newAtoms = this.body.map(_.applyValuation(valuation))

    newAtoms.foreach(a => engine.reactionStrategy.applyReaction(engine, a))
//    println(i)
//    i+=1
//    engine.isRunning = true
    engine.executeRules()
  }
}

class PartialStateExecution (var atoms:ListBuffer[Atom], var state:PartialState){

  def copy():PartialStateExecution = new PartialStateExecution(atoms.clone(), state)

  def computeValuation() {
    var result = Valuation()
    atoms.zip(state.pattern).foreach( c => {
      result = result.union(c._2.getValuation(c._1))
    })
    isCorrect = true
    vals = result
  }

  def addAtom(atom:Atom){
    if(atoms.size < state.pattern.size) {
      atoms += atom
      this.state.rule.engine.addToExecutionIndex(atom, this)
    }

    if(isFinal()) {
      computeValuation()

      // check if valuation is empty and variables were exepected
      if(vals == Valuation() && state.variablesName.size>0)
        state.removeExecution(this)
      else
        state.addFinalExecution(this)
    }
  }

  override def toString():String = atoms.mkString("[",",","]")

  def isFinal():Boolean = atoms.size == state.pattern.size

  var isCorrect = true
  var vals = Valuation()

}

class PartialState (var pattern:List[Atom], var executions:ListBuffer[PartialStateExecution] = ListBuffer(), var rule:NormalRule, var atomName:String) {

  var finalExecutions:ListBuffer[PartialStateExecution] = ListBuffer()



  def addFinalExecution(pse:PartialStateExecution) {
    rule.addFinalExecution(pse)
  }

  def removeExecution(pse:PartialStateExecution) {
    rule.removeFinalExecution(pse)
  }

  def addAtomToExecutions(atom:Atom){

    var listOfCopies:ListBuffer[PartialStateExecution] = ListBuffer()
    executions.foreach {e => if (!e.isFinal()) {
      var copy = e.copy()
      e.atoms.foreach(a => rule.engine.addToExecutionIndex(a, copy))
      listOfCopies += copy
      e.addAtom(atom)
    }}

    executions.insertAll(0,listOfCopies)

    val pse = new PartialStateExecution(ListBuffer(), this)
    pse.addAtom(atom)
    executions += pse
  }

  var variablesName:ListBuffer[String] = ListBuffer()

  pattern.foreach( a => {

    // add all the variables to the needVariableList
    a.patterns.foreach( p => p match {
      case v:Variable => if(!variablesName.contains(v.name))
        variablesName += v.name
      case _ =>
    })
  } )
}

class NormalCham extends NormalEngine {

  def rules(ruleDefs:(RuleFactory => Rule)*) { initRules(ruleDefs.toList) }
}

class Solution {
  var listOfAtoms:ListBuffer[Atom] = ListBuffer()
  var indexOfAtoms:HashMap[String, ListBuffer[Atom]] = HashMap()

  def addAtom(atom: Atom) {
    listOfAtoms += atom

    if(!indexOfAtoms.contains(atom.relation.name))
      indexOfAtoms.put(atom.relation.name, ListBuffer())

    indexOfAtoms.get(atom.relation.name).get += atom
  }

  def removeAtom(atom: Atom) {
    listOfAtoms -= (atom)

    indexOfAtoms.get(atom.relation.name).get -= atom
  }

  def containsAll(listOfAtoms:List[Atom]):Boolean = listOfAtoms.forall( a => indexOfAtoms.contains(a.relation.name) && !indexOfAtoms.get(a.relation.name).get.forall(abis => !abis.correspondsTo(a)))
}

object Combinatory {

  def from[A](list:ListBuffer[ListBuffer[A]]):Combinatory[A] = {

    def from_list(list:ListBuffer[A]):Combinatory[A] = {
      new Combinatory[A](list, null)
    }

    var firstPointer:Combinatory[A] = null
    var currentPointer:Combinatory[A] = null

    list.foreach(l => {
      val newCombinatory:Combinatory[A] = from_list(l)

      if(firstPointer == null)
        firstPointer = newCombinatory

      if(currentPointer != null)
        currentPointer.comb = newCombinatory

      currentPointer = newCombinatory
    })

    firstPointer
  }
}

class Combinatory[A](var list:ListBuffer[A] = ListBuffer(), var comb:Combinatory[A] = null) {
  var index:Int = 0

  def reset() {
    index = 0
  }

  // index is superior or equal to the list's size
  def isOverflowed():Boolean = index >= list.size

  def next() {
    if(comb != null) {
      var truc = isOverflowed()
      if(!(isOverflowed())) {
        comb.next()

        if (comb.isOverflowed()) {
          comb.reset()
          index += 1
        }
      }
    }
    else {
      if (!isOverflowed()) {
        index += 1
      }
    }
  }

  def get():List[A] = {
    if(!(isOverflowed())) {
      if(comb != null) {
        if(!comb.isOverflowed()) {
          list(index) :: comb.get()
        }
        else {
          Nil
        }
      }
      else {
        List(list(index))
      }
    }
    else {
      Nil
    }
  }

  def hasNext():Boolean = {
    if(comb != null) {
      !isOverflowed() || comb.hasNext()
    }
    else {
      !isOverflowed()
    }
  }
}