package fr.emn.criojo.ext.debug

import fr.emn.criojo.lang.Molecule
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.core.{Atom, Engine}
import fr.emn.criojo.core.datatype.Valuation

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 4:27:10 PM
 * To change this template use File | Settings | File Templates.
 */
object Solution {
  def apply(chamEngine: Engine, atoms: Atom*) = new SolutionImpl(chamEngine, atoms.toList)

  def createDefault(atoms: List[Atom]) = new SolutionImpl(null, atoms)
}

object EmptySolution extends SolutionImpl(null, List[Atom]())

class InvalidStateError(msg: String) extends Exception(msg)

trait Solution {

  def displaySolution()

  def createBackUp()

  def reverse()

  def elems: List[Atom]

  def addAtom(atom: Atom)

  def addMolecule(molecule: List[Atom])

  def size = elems.size

  def isEmpty = elems.isEmpty

  def contains(a: Atom) = elems.contains(a)

  def containsAtom(a: Atom, n: Int): Boolean = {
    (elems.count(atom => atom.correspondsTo(a)) == n)
  }

  def containsAtom(a: LocalRelation, n: Int): Boolean = {
    (elems.count(atom => a.name == atom.relation.name) == n)
  }

  def containsMolecule(m: Molecule): Boolean = {
    containsMolecule(m, 1)
  }

  def containsMolecule(m: Molecule, n: Int): Boolean = {
    containsAtom(m.head, n)
  }

  def isTrue: Boolean = !elems.exists(_.isFalse) && elems.exists(_.isTrue)

  def filter(p: (Atom) => Boolean) = elems.filter(p)

  def find(p: (Atom) => Boolean) = elems.find(p)

  def foreach(f: (Atom) => Unit) {
    elems.foreach(f)
  }

  def remove(a: Atom)

  def exists(f: (Atom) => Boolean) = elems.exists(f)

  def map[B](f: (Atom) => B) = elems.map(f)

  def clear()

  def cleanup()

  def revert() {
    elems.foreach(_.setActive(true))
  }

  def update(newsol: Solution)

  /**
   * Inactivates an atom in the solution
   */
  def inactivate(a: Atom)

  def activate(a: Atom)

  /**
   * Finds atoms matching a conjunction (set of atoms), after applying an initial set of substitutions
   */

  protected def findMatches(atom: Atom, vals: Valuation): List[Atom] = {
    if (vals.isEmpty) {
      filter(_.relation.name == atom.relation.name).toList
    } else {
      val test = atom.applyValuation(vals)
      //val test = atom.applySubstitutions(vals)
      //      filter(a => a.isActive && a.correspondsTo(test)).toList
      filter(a => a.isActive && test.correspondsTo(a)).toList
    }
  }

  override def clone: Solution = Solution.createDefault(List[Atom]() ++ this.elems)

  override def equals(that: Any) = that match {
    case thatS: Solution => this.size == thatS.size && (this.elems intersect thatS.elems).size == this.size
    case _ => false
  }

  override def toString = {
    elems.mkString("<", ",", ">")
  }

  def notifyCHAM(newAtom: Atom)
}

class SolutionImpl(owner: Engine, var elems: List[Atom]) extends Solution {
  def this() = this(null, List[Atom]())

  def displaySolution() {
    var firstPrint: Boolean = true
    print("<")
    elems.foreach(a => {
      if (a.relation.name.charAt(0) != '$') {
        if (!firstPrint)
          print(",")
        print(a)
        firstPrint = false
      }
    })
    println(">")
  }

  private var oldElements: List[Atom] = List()

  def remove(a: Atom) {
    elems = elems.filterNot(_ == a)
  }

  def clear() {
    elems = List[Atom]()
  }

  def addAtom(atom: Atom) {
    elems :+= atom
    notifyCHAM(atom)
  }

  def addMolecule(molecule: List[Atom]) {
    elems ++= molecule
  }

  def cleanup() {
    elems = elems.filter(_.isActive)
  }

  def update(newsol: Solution) {
    if (newsol.isEmpty) {
      clear()
    } else {
      this.elems = newsol.elems
    }
  }

  def inactivate(a: Atom) {
    a.setActive(false)
  }

  def activate(a: Atom) {
    a.setActive(true)
  }

  override def clone: Solution = new SolutionImpl(this.owner, List[Atom]() ++ this.elems)

  def notifyCHAM(newAtom: Atom) {
    if (owner != null)
      owner.notifyRelationObservers(newAtom)
  }

  def createBackUp() {
    oldElements = elems
  }

  def reverse() {
    elems = oldElements
  }
}


