package fr.emn.criojo.core.engine

import fr.emn.criojo.core._
import collection.mutable.ListBuffer
import collection.mutable.HashMap
import fr.emn.criojo.core.datatype.Variable
import scala.Some


/**
 * An implementation of Cham that is using the CriojoEngine.
 *
 */
class CriojoCham extends CriojoEngine {

  def rules(ruleDefs:(RuleFactory => CriojoRule)*) { initRules(ruleDefs.toList) }
}

/**
 * The CriojoEngine trait.
 *
 * This engine is generic: specific implementation is contained in the rules
 *
 */
trait CriojoEngine extends Engine {

  var isRunning: Boolean = false

  val solution: Solution = new Solution()

  def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]):CriojoRule = new PartialStateRule(h, b, g, scope, this)

  override def reactionStrategy: ReactionStrategy = new LocalReactionStrategy()

  def removeFromExecutionIndex(atom: Atom) {
    rules.foreach( r => r.removeFromExecutionIndex(atom))
  }

  def executeRules() {
    isRunning = true
    while (rules.exists(r => r.execute)) {}
  }

  def introduceAtom(atom: Atom) {
    solution.addAtom(atom)

    mapAtomRules.get(atom.relation.name) match {
      case s: Some[ListBuffer[CriojoRule]] => s.get.foreach(r => r.addToExecution(atom))
      case None =>
    }
  }

  def removeAtom(atom: Atom) {
    solution.removeAtom(atom)
  }

  val mapAtomRules: HashMap[String, ListBuffer[CriojoRule]] = HashMap()
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