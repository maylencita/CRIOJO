package fr.emn.criojo.lang

import fr.emn.criojo.core._
import datatype.{Term, Variable}
import fr.emn.criojo.ext.expression.Relation.Relation

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 18, 2010
 * Time: 2:08:14 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * The Molecule singleton
 * @define THIS Atom
 */

object Molecule{
  def apply(lst:List[Atom]):Molecule = lst match{
    case List() => Empty
    case head :: tail => &:(head, Molecule(tail))
  }
  def apply(scope:List[Variable], lst:List[Atom]):Molecule = {
    val molecule = Molecule(lst)
    molecule.scope = scope
    molecule
  }
}

/**
 * Molecule objects implement a recursive representation for a conjunction of atoms,
 * which are used in the construction of rules.
 * Thus, a molecule is defined as:
 *  m := atom | m & m
 * And a rule is:
 *  r := m --> guard ? m
 * @define THIS A
 */
trait Molecule {
  def empty:Boolean
  def head:Atom
  def tail:Molecule

  var scope = List[Variable]()

  def &: (a:Atom) = {
    val molecule = new &: (a, this)
    molecule.scope = this.scope
    molecule
  }

  def & (a:Molecule) = {
    val newList = this.toList ++ a.toList
    Molecule(this.scope ++ a.scope, newList)
  }

  def toList:List[Atom] = head :: tail.toList

  def ?: (g:Guard):(Guard,Molecule)={
    (g,this)
  }

  @Deprecated
  def ==> (c2:Molecule): RuleFactory => Rule ={
    val f = (rf:RuleFactory) => rf.createRule(this.toList,c2.toList,EmptyGuard,c2.scope.toSet)
    f
  }

  def --> (c2:Molecule): RuleFactory => Rule ={
    val f = (rf:RuleFactory) => rf.createRule(this.toList,c2.toList,EmptyGuard,c2.scope.toSet)
    f
  }
  @Deprecated
  def ==> (gc: (_, _)):RuleFactory => Rule = gc match {
    case (g:Guard, conj:Molecule) => getRuleBuilder(g,conj)
    case _ => null
  }

  def --> (gc: (_, _)):RuleFactory => Rule = gc match {
    case (g:Guard, conj:Molecule) =>
      (rf:RuleFactory) => rf.createRule(this.toList,conj.toList,g,conj.scope.toSet)
    case _ => null
  }

  def getRuleBuilder (g:Guard,conj:Molecule): RuleFactory => Rule = {
    val f = (rf:RuleFactory) => rf.createRule(this.toList,conj.toList,g,conj.scope.toSet)
    f
  }

}

/**
 * The class CrjAtom that is an atom and molecule at the same time
 * @define THIS CrjAtom
 */

class CrjAtom(relation:Relation, terms: List[Term]) extends Atom(relation, terms) with Molecule {
  def empty:Boolean = false

  def head:Atom = this

  def tail:Molecule = Empty
}

/**
 * A helper class to define new free variables in a molecule
 * @param varLst : The list of new variables
 */
case class Nu(varLst:Variable*){
  val scope = varLst.toList

  def apply(conj:Molecule):Molecule = {
    conj.scope = this.scope
    conj
  }
}

/**
 * An empty molecule
 */
case object Empty extends Molecule{
  def empty = true
  def head = throw new NoSuchElementException("head of empty conjunction")
  def tail = throw new NoSuchElementException("tail of empty conjunction")
  scope = List()
  def unary_! : CrjAtom = throw new NoSuchElementException("Bang of empty molecule")

  override def toList = List()
  override def toString = ""
}

final case class &: (hd:Atom, tl:Molecule) extends Molecule{
  def head = hd
  def tail = tl
  def empty = false
  scope = List()

  def unary_! : CrjAtom = throw new NoSuchElementException("Bang of empty molecule")
  override def toString = head + (if (tail.empty) "" else  " & " + tail)

}

