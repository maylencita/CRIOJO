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
 * The CriojoEngine trait.
 *
 * This engine is generic: specific implementation is contained in the rules
 *
 */
trait CriojoEngine extends Engine {

  var isRunning:Boolean = false

  val solution:Solution = new Solution()

  var indexAtomPartialStateExecution:HashMap[Atom, ListBuffer[PartialStateExecution]] = HashMap()

  def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]) = new PartialStateRule(h, b, g, scope, this)

  override def reactionStrategy: ReactionStrategy = new LocalReactionStrategy()

  def addToExecutionIndex(a:Atom, pe:PartialStateExecution) {
    if(!indexAtomPartialStateExecution.contains(a))
      indexAtomPartialStateExecution.put(a, ListBuffer())

    indexAtomPartialStateExecution.get(a).get += pe
  }

  def removeFromExecutionIndex(atom:Atom) {
    if(indexAtomPartialStateExecution.contains(atom)) {

      indexAtomPartialStateExecution.get(atom).get.foreach( pse => {

        if(pse.isFinal)
          pse.state.rule.removeFinalExecution(pse)

        pse.state.executions -= pse
      })
      indexAtomPartialStateExecution.remove(atom)
    }
  }

  def executeRules() {
    isRunning = true
    while (rules.exists(r => r.execute)) {}
  }

  def introduceAtom(atom: Atom) {
    solution.addAtom(atom)

    mapAtomRules.get(atom.relation.name) match {
      case s:Some[ListBuffer[PartialStateRule]] => s.get.foreach( r => r.addToExecution(atom))
      case None =>
    }
  }

  def removeAtom(atom: Atom) {
    solution.removeAtom(atom)
  }

  val mapAtomRules:HashMap[String, ListBuffer[PartialStateRule]] = HashMap()

  object NormalRule {

    def createPartialStateExecution (atoms:ListBuffer[Atom], state:PartialState) =
      new PartialStateExecution(atoms, state)
  }
}


/**
 * PartialStateRule is an implementation of Rule, that use partial-states machines: each Relation contained in a Rule has
 * it's own partial-states machine. With this principle, memory consumption should be improved.
 *
 *  @param head
 *  list of atoms contained in the left-part of the rule
 *
 *   @param body
 *  list of atoms contained in the right-part of the rule
 *
 *   @param guard
 *  a guard, that will be evaluated
 *
 *   @param scope
 *  a set of variable
 *
 *   @param engine
 *  a link to the engine that is using this rule
 */
class PartialStateRule(val head: List[Atom], val body: List[Atom], val guard: Guard, scope: Set[Variable], var engine:CriojoEngine)
  extends Rule {



  var variableNameList:List[String] = List()

  // indexes that will increase the speed of the algorithms.
  var mapOfAtoms:HashMap[String, ListBuffer[Atom]] = HashMap()
  var stateMachinesPerAtom:HashMap[String, ListBuffer[PartialState]] = HashMap()
  var finalExecutionPerAtom:HashMap[String, ListBuffer[PartialStateExecution]] = HashMap()

  // create the indexes
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
  })

  // construction of the partial state machines
  mapOfAtoms.foreach(c => {

    val permutations = c._2.permutations.toList
    var states:ListBuffer[PartialState] = ListBuffer()

    permutations.foreach(p => {
      states += new PartialState(p.toList, ListBuffer(), this, c._1)
    })

    stateMachinesPerAtom.put(c._1, states)

    if (!engine.mapAtomRules.contains(c._1))
      engine.mapAtomRules.put(c._1, ListBuffer[PartialStateRule]())

    engine.mapAtomRules.get(c._1).get += this
  })

  def addFinalExecution(pe:PartialStateExecution) {
    if (!finalExecutionPerAtom.contains(pe.state.atomName))
      finalExecutionPerAtom.put(pe.state.atomName, ListBuffer())

    finalExecutionPerAtom.get(pe.state.atomName).get += pe
  }

  def removeFinalExecution(pe:PartialStateExecution) {
    if (finalExecutionPerAtom.contains(pe.state.atomName))
      finalExecutionPerAtom.get(pe.state.atomName).get -= pe

    // to prevent large number of call to garbage collector, a rule should be executed many times if there is a lot of
    // finalExecution for this rule.
    if(finalExecutionPerAtom.contains(pe.state.atomName) && finalExecutionPerAtom.get(pe.state.atomName).get.size > 22) {

      //todo: optimization, may cause crash in case of stackoverflow
      if(engine.rules.length>1)
        while(execute) {}
    }
  }

  def addToExecution(atom:Atom) {
    stateMachinesPerAtom.get(atom.relation.name).get.foreach {s => s.addAtomToExecutions(atom)}
  }

  def clean(atom:Atom) {
    engine.removeFromExecutionIndex(atom)
  }

  // check basically if the pattern and the solution are compatible
  def canBeOk(solution:Solution):Boolean = mapOfAtoms.forall( c =>
    solution.indexOfAtoms.get(c._1) != None && solution.indexOfAtoms.get(c._1).get.size >= c._2.size)


  /**
   * PartialStateRule is an implementation of Rule, that use partial-states machines: each Relation contained in a Rule has
   * it's own partial-states machine. With this principle, memory consumption should be improved.
   *
   *   @return a boolean (true if the search is positive), a list of (Valuation, List[PartialStateExecution]) that
   *           contains only the first combination that can be executed by the engine.
   */

  def findOneCombination():(Boolean,ListBuffer[(Valuation, List[PartialStateExecution])]) = {

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
        listsOfListOfPartialExecution += c._2
      }}
    }

    val combination:Combinatory[PartialStateExecution] = Combinatory.from(listsOfListOfPartialExecution)
    var valuation:Valuation = null
    var listOfPartialExecution:List[PartialStateExecution] = null

    var listBis:ListBuffer[(Valuation, List[PartialStateExecution])] = ListBuffer()

    if(combination != null) {
      var continue = combination.get() != Nil

      while(continue) {

        listOfPartialExecution = combination.get()
        if(isOneMatchingCombination(listOfPartialExecution)) {
          valuation = flattenCombination(listOfPartialExecution)

          var atomsNotConsumed = listOfPartialExecution.forall(pe => !pe.consumed)

          if(atomsNotConsumed && guard.eval(valuation)) {
            executed = true

            var item = (valuation, listOfPartialExecution)
            listBis += item

            // alternative (1) : if the function return the first combination (quicker)
            continue = false

            // alternative (2) : if the function return the list of all good combination (slower)
//            combination.next()
//            continue = combination.get() != Nil
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

    (executed, listBis)
  }

  def getValuation(listOfAtoms:ListBuffer[Atom]):Valuation = Valuation()

  def receiveUpdate(atom: Atom) {
  }

  override def execute = {
    var result = false



    var listAtoms:ListBuffer[Atom] = ListBuffer()

    if(canBeOk(engine.solution)) {
      findOneCombination() match {
        case (true, l:ListBuffer[(Valuation, List[PartialStateExecution])]) => {
          l.foreach( c => if (applyReaction(c._2, c._1, listAtoms)) result = true )
        }
        case _ =>
      }
    }
    result
  }

  def applyReaction(pes:List[PartialStateExecution], valuation:Valuation, listAtoms:ListBuffer[Atom] = null, oneTime:Boolean = false):Boolean = {

    var executeOk:Boolean = true

    if(listAtoms != null) {

      pes.foreach( pe => {

        if(pe.consumed)
          executeOk = false
        else
          pe.consumed = true

        pe.atoms.foreach(a => {

          if(a.consumed)
            executeOk = false

          if(listAtoms.contains(a))
            executeOk = false
        })
      })
    }

    if(executeOk) {

      pes.foreach( pe => {
        pe.atoms.foreach(a => {
          a.consumed = true
          engine.removeAtom(a)
          clean(a)
          if(listAtoms != null) {
            listAtoms += a
          }
        })
        pe.state.removeExecution(pe)
      })

      val newAtoms = this.body.map(_.applyValuation(valuation))

      newAtoms.foreach(a => engine.reactionStrategy.applyReaction(engine, a))
    }

    executeOk
  }
}


/**
 * PartialState represents one part of the state of a rule's state-machine. Each state is related to one specific Relation
 * and each state contains a list of finalExecutions.
 *
 *  @param pattern
 *  List of atoms (with variables) that are contained in the state.
 *
 *   @param executions
 *  the partialState that is using this PartialStateExecution. Default value is ListBuffer().
 *
 *   @param rule
 *  the rule related to this PartialState.
 *
 *   @param atomName
 *  the name of the Relation represented by this state.
 *
 */
class PartialState (var pattern:List[Atom], var executions:ListBuffer[PartialStateExecution] = ListBuffer(), var rule:PartialStateRule, var atomName:String) {

  var finalExecutions:ListBuffer[PartialStateExecution] = ListBuffer()



  def addFinalExecution(pse:PartialStateExecution) {
    rule.addFinalExecution(pse)
  }

  def removeExecution(pse:PartialStateExecution) {
    rule.removeFinalExecution(pse)
  }

  def addAtomToExecutions(atom:Atom){

    var listOfCopies:ListBuffer[PartialStateExecution] = ListBuffer()
    executions.foreach {e => if (!e.isFinal) {
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


/**
 * PartialStateExecution is one execution of a PartialState. When PartialStateExecution becomes final, the PartialState
 * is notified and check if the rule can be executed. If so, the rule is executed and the PartialStateExecution is removed
 * from the PartialState.
 *
 *  @param atoms
 *  list of atoms that are used by this PartialStateExecution
 *
 *   @param state
 *  the partialState that is using this PartialStateExecution
 *
 */

class PartialStateExecution (var atoms:ListBuffer[Atom], var state:PartialState){

  var consumed = false

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

    if(isFinal) {
      computeValuation()

      // check if valuation is empty and variables were exepected
      if(vals.isEmpty || (vals == Valuation() && state.variablesName.size>0))
        state.removeExecution(this)
      else
        state.addFinalExecution(this)
    }
  }

  override def toString():String = atoms.mkString("[",",","]")

  def isFinal:Boolean = atoms.size == state.pattern.size

  var isCorrect = true
  var vals = Valuation()

}

/**
 * An implementation of Cham that is using the CriojoEngine.
 *
 */
class CriojoCham extends CriojoEngine {

  def rules(ruleDefs:(RuleFactory => Rule)*) { initRules(ruleDefs.toList) }
}

/**
 * Solution is an object that will represent the solution of atoms. This class has an index of Relation.name -> Atoms
 * to increase some search algorithms.
 *
 */
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


/**
 * Singleton of Combinatory.
 *
 */
object Combinatory {


  /**
   * Create a chain of Combinatory from a list of list of A.
   *
   * @param list a list of list of A.
   *
   * @return a chain of Combinatory.
   */
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

/**
 * Combinatory is an inductive way to make lazy combination of elements. The comb parameter is another Combinatory: it
 * enables chain of combinatory. Using the next() function gives the next combination of elements.
 *
 * For example: Combinatory( l1, Combinatory( l2, Combinatory( l3, null) ) )
 *
 * @param list
	 * The list of elements
 * @param comb
	 * The next combinatory (this enables chain of combinatory)
 */
class Combinatory[A](var list:ListBuffer[A] = ListBuffer(), var comb:Combinatory[A] = null) {
  var index:Int = 0

  def reset() {
    index = 0
  }

  /**
   * True when the index of this Combinatory is out of bounds (reset function should be called).
   *
   * @return True when the index of this Combinatory is not out of bounds.
   */
  def isOverflowed:Boolean = index >= list.size


  /**
   * Put the index at the next combination.
   */
  def next() {
    if(comb != null) {
      if(!(isOverflowed)) {
        comb.next()

        if (comb.isOverflowed) {
          comb.reset()
          index += 1
        }
      }
    }
    else {
      if (!isOverflowed) {
        index += 1
      }
    }
  }

  /**
   * Return the actual combination.
   *
   * @return The actual combination.
   */
  def get():List[A] = {
    if(!(isOverflowed)) {
      if(comb != null) {
        if(!comb.isOverflowed) {
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

  /**
   * True when the index of this Combinatory and the index next Combinatories are not out of bounds (reset function should be called).
   *
   * @return True when the iindex of this Combinatory and the index next Combinatories are not out of bounds.
   */
  def hasNext:Boolean = {
    if(comb != null) {
      !isOverflowed || comb.hasNext
    }
    else {
      !isOverflowed
    }
  }
}