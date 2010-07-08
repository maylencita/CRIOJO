package fr.emn.creole.core

import fr.emn.creole.util.Logger

import Creole.Substitution
import scala.collection.mutable.HashSet

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 4:27:10 PM
 * To change this template use File | Settings | File Templates.
 */

class Solution extends HashSet[Atom]{

//  def findMatches(conjunction:List[Atom]):List[Atom] = {
//    def findMatchesRec(c:List[Atom], subs:List[Substitution], acum:List[Atom]):List[Atom] = c match{
//      case List() => acum
//      case h :: rest =>
//        val matches = findMatches(h, subs)
//        if (matches.isEmpty)
//          List()
//        else{
//          var results = List[Atom]()
//          var i = matches.iterator
//          while (i.hasNext && results.isEmpty){
//            val m = i.next
//            m.active = false
//            results = findMatchesRec(rest, subs.union(h.vars.zip(m.vars)), acum :+ m)
//            m.active = results.isEmpty
//          }
//          results
//        }
//    }
//
//
//    findMatchesRec(conjunction, List(), List())
//  }

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

  private def findMatches(relation:Atom, subs:List[Substitution]): List[Atom] = {
    if (subs.isEmpty){
      filter(_.relName == relation.relName).toList
    }else{
      val test = relation.applySubstitutions(subs)
      filter(a => a.active && a.matches(test)).toList
    }
  }

  def addMolecule(molecule:List[Atom]){
    molecule.foreach(this.add(_))
  }

  //This removes inactive atoms
  def cleanup{
    retain(_.active)
  }

  //This puts everything back to normal
  def revert{
    foreach(_.active = true)
  }

  def update(newsol: Solution){
    retain(newsol.contains(_))
    if (newsol.contains(False)){
      clear
//      add(False)
    }else{
      newsol.foreach(addEntry(_))
    }
  }

  def isTrue:Boolean = !this.exists(_.isFalse) && this.exists(_.isTrue)

  override def equals(that:Any):Boolean = that match{
    case thatSol:Solution => thatSol.size == size &&
      (thatSol filterNot (this contains)).isEmpty 
    case _ => false
  }

  override def clone:Solution = {
    val sol = new Solution()
    foreach(a => sol.add(a.clone))
    sol
  }

  override def containsEntry(elem: Atom): Boolean = {
    var h = index(elemHashCode(elem))
	    var entry = table(h)
	    while (null != entry && entry != elem) {
	      h = (h + 1) % table.length
	      entry = table(h)
	    }
	    null != entry
//    val i = iterator
//    var entry = i.next
//    while(i.hasNext && entry != elem){
//      entry = i.next
//    }
//    entry == elem
  }

  override def toString = {
    if (Logger.on){
       mkString("<",",",">")
    }else{
      def getValue(v:Variable):Variable={
        if(v.name.startsWith("$")){
          find(a => a.relName.startsWith("$") && a.hasVariable(v)) match{
            case Some(c) => new Variable(c.relName.substring(5))
            case _ => v
          }
        }else
          v
      }

      filterNot(_.relName.startsWith("$")).map(a => new Atom(a.relName, a.vars.map(getValue(_)))).mkString("<",",",">")   
    }

  }


//  override def toString = {
//    def getValue(v:Variable):Variable={
//      if(v.name.startsWith("$")){
//        find(a => a.relName.startsWith("$") && a.hasVariable(v)) match{
//          case Some(c) => new Variable(c.relName.substring(5))
//          case _ => v
//        }
//      }else
//        v
//    }
//
////    filterNot(_.relName.startsWith("$")).map(a => new Atom(a.relName, a.vars.map(getValue(_)))).mkString("<",",",">")
//    map(a => new Atom(a.relName, a.vars.map(getValue(_)))).mkString("<",",",">")
//  }

}