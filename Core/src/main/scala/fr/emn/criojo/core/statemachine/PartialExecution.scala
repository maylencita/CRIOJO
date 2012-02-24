package fr.emn.criojo.core.statemachine

import fr.emn.criojo.core.Criojo.Substitution
import fr.emn.criojo.core.Atom
import scala._
import collection.immutable.HashMap

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 29/11/11
* Time: 14:17
*/

class PartialExecution(atomMap:HashMap[Int,Atom],val subs:List[Substitution]){
  def this(pos:Int, atom:Atom, subs:List[Substitution])={
    this(HashMap(pos -> atom),subs)
  }

  def atoms = this.atomMap.values

  def atom(pos:Int) = atomMap(pos)

  def newExecution(pos:Int, newAtom:Atom, subs:List[Substitution]): PartialExecution = {
    new PartialExecution(this.atomMap + (pos -> newAtom), this.subs.union(subs))
  }

  def containsAtom(atom:Atom): Boolean = atomMap.exists(p => p._2 == atom)

  override def toString = atoms.mkString("{",",","}")
}





