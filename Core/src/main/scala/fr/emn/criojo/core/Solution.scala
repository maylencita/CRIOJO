package fr.emn.criojo.core

import Criojo.Substitution
import collection.mutable.MutableList

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 4:27:10 PM
 * To change this template use File | Settings | File Templates.
 */
object Solution{
  def apply(cham:CHAM, atoms:Atom*) = new SolutionImpl(cham, atoms.toList)
  def createDefault(atoms:List[Atom]) = new SolutionImpl(null, atoms)
}

class InvalidStateError(msg:String) extends Exception(msg)

trait Solution{
  def createBackUp()
  def reverse()

  def elems:List[Atom]

  def addAtom(atom:Atom)

  def addMolecule(molecule:List[Atom])

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

  def clear()

  def cleanup()

  def revert(){ elems.foreach(_.setActive(true))  }

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
          val i = matches.iterator
          while (i.hasNext && results.isEmpty){
            val m = i.next
            inactivate(m)
            results = findMatchesRec(rest, subs.union(h.vars.zip(m.terms)), acum :+ m)
            if(results.isEmpty)
              activate(m)
          }
          results
        }
    }

    findMatchesRec(conjunction, substitutions, List())
  }

  def copy(newOwner:CHAM):Solution

  protected def findMatches(atom:Atom, subs:List[Substitution]): List[Atom] = {
    if (subs.isEmpty){
      filter(_.relName == atom.relName).toList
    }else{
      val test = atom.applySubstitutions(subs)
//      filter(a => a.isActive && a.matches(test)).toList
      filter(a => a.isActive && test.matches(a)).toList
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

  def notifyCHAM(newAtom:Atom)
}

class SolutionImpl(owner:CHAM, var elems:List[Atom]) extends Solution{
  def this()= this(null, List[Atom]())

  private var oldElements:List[Atom] = List()

  def remove(a:Atom) { elems = elems.filterNot(_ == a)}
  def clear() {   elems = List[Atom]()  }
  def copy(newOwner:CHAM):Solution = new SolutionImpl(newOwner,elems.map(a => a.clone))
  def addAtom(atom:Atom){
    elems :+= atom
    notifyCHAM(atom)
  }
  def addMolecule(molecule:List[Atom]){
    elems ++= molecule
  }

  def cleanup(){
    elems = elems.filter(_.isActive)
  }
  def update(newsol: Solution){
    if (newsol.contains(False) || newsol.isEmpty){
      clear()
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
  override def clone:Solution = new SolutionImpl(this.owner, List[Atom]() ++ this.elems)

  def notifyCHAM(newAtom:Atom){
    owner.receiveUpdate(newAtom)
  }

  def createBackUp(){
    oldElements = elems
  }
  def reverse(){
    elems = oldElements
  }
}



