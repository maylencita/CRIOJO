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
  def apply(atoms:Atom*) = new Solution(atoms.toSet) 
}

class Solution(var elems:Set[Atom]){
  def this()= this(Set[Atom]())

  def addAtom(atom:Atom){ elems += atom }

  def addMolecule(molecule:List[Atom]){ elems ++= molecule}

  def size = elems.size

  def isEmpty = elems.isEmpty

  def contains(a:Atom) = elems.contains(a)

  def isTrue:Boolean = !elems.exists(_.isFalse) && elems.exists(_.isTrue)

  def filter(p: (Atom) => Boolean) = elems.filter(p)

  def find(p: (Atom) => Boolean) = elems.find(p)

  def foreach(f: (Atom) => Unit) {elems.foreach(f)}

  def remove(a:Atom) { elems -= a}

  def exists(f: (Atom)=>Boolean) = elems.exists(f)

  def clear{
    elems = Set[Atom]()
  }

  def cleanup{
    elems = elems.filter(_.active)  
  }

  def revert{
    elems.foreach(_.active = true)
  }

  def update(newsol: Solution){
    if (newsol.contains(False) || newsol.isEmpty){
      clear
    }else{
      this.elems = newsol.elems
    }
  }

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
            m.active = false
            results = findMatchesRec(rest, subs.union(h.vars.zip(m.vars)), acum :+ m)
            m.active = results.isEmpty
          }
          results
        }
    }

    findMatchesRec(conjunction, substitutions, List())
  }

  def copy:Solution = new Solution(elems.map(a => a.clone))

  protected def findMatches(atom:Atom, subs:List[Substitution]): List[Atom] = {
    if (subs.isEmpty){
      filter(_.relName == atom.relName).toList
    }else{
      val test = atom.applySubstitutions(subs)
      filter(a => a.active && a.matches(test)).toList
    }
  }

  override def clone:Solution = new Solution(Set[Atom]() ++ this.elems)

  override def equals(that:Any)= that match{
    case thatS:Solution => this.size == thatS.size && (this.elems & thatS.elems).size == this.size
    case _ => false
  }

  override def toString = {
    elems.mkString("<",",",">")
  }

}

/*
class Solution extends HashSet[Atom]
{

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
            m.active = false
            results = findMatchesRec(rest, subs.union(h.vars.zip(m.vars)), acum :+ m)
            m.active = results.isEmpty
          }
          results
        }
    }

    findMatchesRec(conjunction, substitutions, List())
  }

  protected def findMatches(relation:Atom, subs:List[Substitution]): List[Atom] = {
    if (subs.isEmpty){
      filter(_.relName == relation.relName).toList
    }else{
      val test = relation.applySubstitutions(subs)
      filter(a => a.active && a.matches(test)).toList
    }
  }

  def addMolecule(molecule:List[Atom]){
    molecule.foreach(this.addAtom(_))
  }

  def addAtom(atom:Atom):Boolean ={
    this.add(atom)
  }

  //Removes inactive atoms
  def cleanup{
    retain(_.active)
  }

  //Puts everything back to normal
  def revert{
    foreach(_.active = true)
  }

  def update(newsol: Solution){
    if (newsol.contains(False) || newsol.isEmpty){
      clear
    }else{
//      cleanup
      for(a <- this.toList){
        if(!a.active)
          this -= a
      }
       newsol.foreach(add(_))
    }
  }

  def isTrue:Boolean = !this.exists(_.isFalse) && this.exists(_.isTrue)

  override def equals(that:Any):Boolean = that match{
    case thatSol:Solution => thatSol.size == size &&
      (thatSol filterNot (this contains)).isEmpty 
    case _ => false
  }

  override def clone:Solution = new Solution ++= this
//  override def clone:Solution = {
//    val Solution = new Solution()
////    foreach(a => Solution.add(a.clone))
//    foreach(Solution.add(_))
//    Solution
//  }

//  override def containsEntry(elem: Atom): Boolean = {
////    var h = index(elemHashCode(elem))
////	    var entry = table(h)
////	    while (null != entry && entry != elem) {
////	      h = (h + 1) % table.length
////	      entry = table(h)
////	    }
////	    null != entry
//    val i = iterator
//    var entry:Atom = null
//    while(i.hasNext && entry != elem){
//      entry = i.next
//    }
//    entry == elem
//  }

  override def toString = {
       mkString("<",",",">")
  }

}
*/