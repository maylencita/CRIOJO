package fr.emn.criojo.core.engine

import fr.emn.criojo.core.{Rule, Guard, Atom}
import fr.emn.criojo.core.datatype.{BottomValuation, Valuation, Variable}
import collection.mutable.{ListBuffer, HashMap}

/**
 * Created with IntelliJ IDEA.
 * User: jonathan
 * Date: 7/13/12
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */

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