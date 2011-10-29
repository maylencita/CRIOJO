package fr.emn.criojo.lang

import fr.emn.criojo.core._

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 18, 2010
 * Time: 2:08:14 PM
 * To change this template use File | Settings | File Templates.
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

trait Molecule{
  def empty:Boolean
  def head:Atom
  def tail:Molecule

  var scope = List[Variable]()

  def &: (a:Atom) = { //new &: (a, this)
    val molecule = new &: (a, this)
    molecule.scope = this.scope
    molecule
  }

  def & (a:Molecule) = {//new &: (a, this)
    val newList = this.toList ++ a.toList
    Molecule(this.scope ++ a.scope, newList)
  }

  def toList:List[Atom] = head :: tail.toList

  def ?: (g:Guard):(Guard,Molecule)={
    (g,this)
  }

  @Deprecated
  def ==> (c2:Molecule): RuleFactory => Rule ={
    val f = (rf:RuleFactory) => rf.createRule(this.toList,c2.toList,EmptyGuard,c2.scope)
    f
  }

  def --> (c2:Molecule): RuleFactory => Rule ={
    val f = (rf:RuleFactory) => rf.createRule(this.toList,c2.toList,EmptyGuard,c2.scope)
    f
  }
  @Deprecated
  def ==> (gc: Tuple2[_,_]):RuleFactory => Rule = gc match {
    case (g:Guard, conj:Molecule) => getRuleBuilder(g,conj)
    case _ => null
  }

  def --> (gc: Tuple2[_,_]):RuleFactory => Rule = gc match {
    case (g:Guard, conj:Molecule) => //getRuleBuilder(g,conj)
      (rf:RuleFactory) => rf.createRule(this.toList,conj.toList,g,conj.scope)
    case _ => null
  }

  def getRuleBuilder (g:Guard,conj:Molecule): RuleFactory => Rule = {
    val f = (rf:RuleFactory) => rf.createRule(this.toList,conj.toList,g,conj.scope)
    f
  }

}

class CrjAtom(relName:String, terms: List[Term]) extends Atom(relName, terms) with Molecule {
  def empty:Boolean = false

  def head:Atom = this

  def tail:Molecule = Empty

//  override def toString = relName + vars.mkString("(",",",")")
}

case class Nu(varLst:Variable*){
  val scope = varLst.toList

  def apply(conj:Molecule):Molecule = {
    conj.scope = this.scope
    conj
  }
}

case object Empty extends Molecule{//Conjunction{
  def empty = true
  def head = throw new NoSuchElementException("head of empty conjunction")
  def tail = throw new NoSuchElementException("tail of empty conjunction")
  scope = List()

  override def toList = List()
  override def toString = ""
}

//final case class &: (hd:Atom, tl:Conjunction) extends Conjunction{
final case class &: (hd:Atom, tl:Molecule) extends Molecule{
  def head = hd
  def tail = tl
  def empty = false
  scope = List()

  override def toString = head + (if (tail.empty) "" else  " & " + tail)

}

