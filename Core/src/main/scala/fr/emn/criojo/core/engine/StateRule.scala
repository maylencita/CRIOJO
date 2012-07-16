package fr.emn.criojo.core.engine

import fr.emn.criojo.core._
import fr.emn.criojo.core.datatype.{BottomValuation, Valuation, Variable}
import collection.mutable.{ListBuffer, HashMap}
import collection.mutable

/**
 * Created with IntelliJ IDEA.
 * User: jonathan
 * Date: 7/13/12
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * StateRule is an implementation of Rule, that use partial-states machines: each Relation contained in a Rule has
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
case class StateRule(val head: List[Atom], val body: List[Atom], val guard: Guard, scope: Set[Variable], var engine:CriojoEngine)
  extends CriojoRuleImplementation[State, StateExecution] {



  var variableNameList:List[String] = List()

  // indexes that will increase the speed of the algorithms.
  var mapOfAtoms:HashMap[String, ListBuffer[Atom]] = HashMap()
  var stateMachines:ListBuffer[State] = ListBuffer()
  var finalExecutions:ListBuffer[StateExecution] = ListBuffer()

  var indexAtomStateExecution: mutable.HashMap[Atom, ListBuffer[CriojoStateExecution]] = HashMap()

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

  // construction of the complete state machines

  val permutations = head.permutations.toList
  var states:ListBuffer[State] = ListBuffer()

  permutations.foreach(p => {
    states += new State(p.toList, ListBuffer(), this)
  })

  stateMachines = states

  mapOfAtoms.foreach(c => {

    if (!engine.mapAtomRules.contains(c._1))
      engine.mapAtomRules.put(c._1, ListBuffer[CriojoRule]())

    engine.mapAtomRules.get(c._1).get += this
  })


  def addToExecutionIndex(a: Atom, cse: CriojoStateExecution) {
    cse match {
      case pe:StateExecution => addToExecutionIndex(a, pe)
      case _ =>
    }
  }

  def addToExecutionIndex(a: Atom, pe: StateExecution) {
    if (!indexAtomStateExecution.contains(a))
      indexAtomStateExecution.put(a, ListBuffer())

    indexAtomStateExecution.get(a).get += pe
  }

  def removeFromExecutionIndex(atom: Atom) {
    if (indexAtomStateExecution.contains(atom)) {

      indexAtomStateExecution.get(atom).get.foreach(se => se match {
        case pse:StateExecution => {
          if (pse.isFinal)
            pse.state.rule.removeFinalExecution(pse)

          pse.state.executions -= pse
        }
        case _ =>
      })
      indexAtomStateExecution.remove(atom)
    }
  }

  def addFinalExecution(pe:CriojoStateExecution) {
    pe match {
      case pse:StateExecution => addFinalExecution(pse)
      case _ =>
    }
  }

  def addFinalExecution(pe:StateExecution) {
    finalExecutions += pe
  }

  def removeFinalExecution(pe:CriojoStateExecution) {
    pe match {
      case pse:StateExecution => removeFinalExecution(pse)
      case _ =>
    }
  }

  def removeFinalExecution(pe:StateExecution) {
    if (finalExecutions.contains(pe))
      finalExecutions -= pe

    // to prevent large number of call to garbage collector, a rule should be executed many times if there is a lot of
    // finalExecution for this rule.
    if(finalExecutions.size > 22) {

      //todo: optimization, may cause crash in case of stackoverflow
//      if(engine.rules.length>1)
//        while(execute) {}
    }
  }

  def addToExecution(atom:Atom) {
    stateMachines.foreach {s => s.addAtomToExecutions(atom)}
  }

  def clean(atom:Atom) {
    engine.removeFromExecutionIndex(atom)
  }

  // check basically if the pattern and the solution are compatible
  def canBeOk(solution:Solution):Boolean = mapOfAtoms.forall( c =>
    solution.indexOfAtoms.get(c._1) != None && solution.indexOfAtoms.get(c._1).get.size >= c._2.size)


  /**
   * StateRule is an implementation of Rule, that use complete-states machines.
   * With this principle, execution time should be improved.
   *
   *   @return a boolean (true if the search is positive), a list of (Valuation, List[StateExecution]) that
   *           contains only the first combination that can be executed by the engine.
   */

  def findOneCombination():(Boolean,ListBuffer[(Valuation, List[StateExecution])]) = {

    var executed = false
    var valuation:Valuation = null

    var listBis:ListBuffer[(Valuation, List[StateExecution])] = ListBuffer()

    finalExecutions.forall( se => {
      valuation = se.vals

      if(!se.consumed && guard.eval(valuation)) {
        executed = true

        var item = (valuation, List(se))
        listBis += item
        false
      }
      else {
        true
      }
    })

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
        case (true, l:ListBuffer[(Valuation, List[StateExecution])]) => {
          l.foreach( c => if (applyReaction(c._2, c._1, listAtoms)) result = true )
        }
        case _ =>
      }
    }
    result
  }

  def applyReaction(pes:List[StateExecution], valuation:Valuation, listAtoms:ListBuffer[Atom] = null, oneTime:Boolean = false):Boolean = {

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
 * State represents one part of the state of a rule's state-machine. Each state is related to one specific Relation
 * and each state contains a list of finalExecutions.
 *
 *  @param pattern
 *  List of atoms (with variables) that are contained in the state.
 *
 *   @param executions
 *  the State that is using this StateExecution. Default value is ListBuffer().
 *
 *   @param rule
 *  the rule related to this State.
 *
 */
class State (var pattern:List[Atom], var executions:ListBuffer[StateExecution] = ListBuffer(),
                    var rule:StateRule) extends CriojoState {

  var finalExecutions:ListBuffer[StateExecution] = ListBuffer()



  def addFinalExecution(pse:CriojoStateExecution) {
    rule.addFinalExecution(pse)
  }

  def removeExecution(pse:CriojoStateExecution) {
    rule.removeFinalExecution(pse)
  }

  def addAtomToExecutions(atom:Atom){

    var listOfCopies:ListBuffer[StateExecution] = ListBuffer()
    executions.foreach {e => if (!e.isFinal) {
      var copy = e.copy()
      //e.atoms.foreach(a => rule.engine.addToExecutionIndex(a, copy))
      e.atoms.foreach(a => rule.addToExecutionIndex(a, copy))
      listOfCopies += copy
      e.addAtom(atom)
    }}

    listOfCopies.foreach( se => executions += se)
    //executions.insertAll(0,listOfCopies)

    val pse = new StateExecution(ListBuffer(), this)
    pse.addAtom(atom)

    if(pse.atoms.size > 0)
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

  override def toString():String = pattern.mkString("[",";","]")+":"+executions.size
}


/**
 * StateExecution is one execution of a State. When StateExecution becomes final, the State
 * is notified and check if the rule can be executed. If so, the rule is executed and the StateExecution is removed
 * from the State.
 *
 *  @param atoms
 *  list of atoms that are used by this StateExecution
 *
 *   @param state
 *  the State that is using this StateExecution
 *
 */

class StateExecution (var atoms:ListBuffer[Atom], var state:State) extends CriojoStateExecution {

  var consumed = false

  def copy():StateExecution = new StateExecution(atoms.clone(), state)

  def computeValuation() {
    var result = Valuation()
    atoms.zip(state.pattern).foreach( c => {
      result = result.union(c._2.getValuation(c._1))
    })
    isCorrect = true
    vals = result
  }

  def addAtom(atom:Atom){
    if(atoms.size < state.pattern.size && state.pattern(atoms.size).relation.name == atom.relation.name) {
      atoms += atom
      //      this.state.rule.engine.addToExecutionIndex(atom, this)
      this.state.rule.addToExecutionIndex(atom, this)
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