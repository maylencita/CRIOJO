package fr.emn.criojo.core

import Creole.Substitution

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 4:27:10 PM
 * To change this template use File | Settings | File Templates.
 */
object Solution{
  def apply(atoms:Atom*) = new SolutionImpl(atoms.toList)
  def createDefault(atoms:List[Atom]) = new SolutionImpl(atoms)
}

class InvalidStateError(msg:String) extends Exception(msg)

trait Solution{
  def elems:List[Atom]

  def addAtom(atom:Atom)

  def addMolecule(molecule:List[Atom]){ molecule foreach (addAtom(_)) }

  def size = elems.size

  def isEmpty = elems.isEmpty

  def contains(a:Atom) = elems.contains(a)

  def isTrue:Boolean = !elems.exists(_.isFalse) && elems.exists(_.isTrue)

  def filter(p: (Atom) => Boolean) = elems.filter(p)

  def find(p: (Atom) => Boolean) = elems.find(p)

  def foreach(f: (Atom) => Unit) {elems.foreach(f)}

  def remove(a:Atom)

  def exists(f: (Atom)=>Boolean) = elems.exists(f)

  def map[B](f: (Atom)=> B) = elems.map(f)

  def clear

  def cleanup

  def revert

  def update(newsol: Solution)

  /**
   * Inactivates an atom in the solution
   */
  def inactivate(a:Atom)

  def activate(a:Atom)

  /**
   * Finds atoms matching a conjunction (set of atoms), after applying an initial set of substitutions
   */
  def findMatches(conjunction:List[Atom], substitutions:List[Substitution]):List[Atom] = {
    def findMatchesRec(c:List[Atom], subs:List[Substitution], acum:List[Atom]):List[Atom] = c match{
      case List() => acum
      case h :: rest =>
        val matches = findMatches(h, subs)
        if (matches.isEmpty)
          List()
        else{
          var results = List[Atom]()
          var i = matches.iterator
          while (i.hasNext && results.isEmpty){
            val m = i.next
            inactivate(m)
            results = findMatchesRec(rest, subs.union(h.vars.zip(m.vars)), acum :+ m)
            if(results.isEmpty)
              activate(m)
          }
          results
        }
    }

    findMatchesRec(conjunction, substitutions, List())
  }

  def copy:Solution

  protected def findMatches(atom:Atom, subs:List[Substitution]): List[Atom] = {
    if (subs.isEmpty){
      filter(_.relName == atom.relName).toList
    }else{
      val test = atom.applySubstitutions(subs)
      filter(a => a.isActive && a.matches(test)).toList
    }
  }

  override def clone:Solution = Solution.createDefault(List[Atom]() ++ this.elems)

  override def equals(that:Any)= that match{
    case thatS:Solution => this.size == thatS.size && (this.elems intersect thatS.elems).size == this.size
    case _ => false
  }

  override def toString = {
    elems.mkString("<",",",">")
  }
}

class SolutionImpl(var elems:List[Atom]) extends Solution{
  def this()= this(List[Atom]())
  def addAtom(atom:Atom){ elems :+= atom }
  def remove(a:Atom) { elems -= a}
  def clear {   elems = List[Atom]()  }
  def copy:Solution = new SolutionImpl(elems.map(a => a.clone))
  def cleanup{
    elems = elems.filter(_.isActive)
  }
  def revert{
    elems.foreach(_.setActive(true))
  }
  def update(newsol: Solution){
    if (newsol.contains(False) || newsol.isEmpty){
      clear
    }else{
      this.elems = newsol.elems
    }
  }
  def inactivate(a:Atom){
    a.setActive(false)
  }
  def activate(a:Atom){
    a.setActive(true)
  }
  override def clone:Solution = new SolutionImpl(List[Atom]() ++ this.elems)

}
