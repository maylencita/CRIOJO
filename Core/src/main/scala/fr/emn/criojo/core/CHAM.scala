package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 8, 2010
 * Time: 11:15:43 AM
 * To change this template use File | Settings | File Templates.
 */

import Creole._

abstract class CHAM extends AbstractMachine{

  val solution = new Solution
  var rules:List[Rule] = List()
  var relations:List[Relation] = List()

  def introduceAtom(atom:Atom){
    solution.addAtom(atom)
    findRelation(atom.relName) match{
      case r:Relation =>
        atom.relation = r
        r.notifyObservers(atom)
      case _ => //skip
    }
  }

  def addRelation(relation:Relation) = relations :+= relation

  def addRule(rule:Rule) = rules :+= rule

  def query(conj:List[Atom], subs:List[Substitution]):List[Atom] = solution.findMatches(conj, subs)

  implicit def atomToConjunction(a:Atom):Conjunction = new &:(a, Empty)

  abstract class Conjunction{
    def empty:Boolean
    def head:Atom
    def tail:Conjunction

//    def scope:List[Variable]
    var scope = List[Variable]()

    def &: (a:Atom) = new &: (a, this)

    def ?: (g:Guard): (Guard,Conjunction) = (g, this)

    def #: (vlst : Variable*): Conjunction = {this.scope = vlst.toList; this}

    def toList:List[Atom] = head :: tail.toList

    def ==> (c2:Conjunction):Rule = {
      val r = newRule(this.toList, c2.toList)
      r.setScope(c2.scope)
      r
    }

    def ==> (gc: (Guard, Conjunction)):Rule = {
      val g = gc._1; val c2 = gc._2
      val r = newRule(this.toList, c2.toList,g)
      r.setScope(c2.scope)
      r
    }

    override def toString = head + (if (tail.empty) "" else  " & " + tail)
  }

  case class Nu(varLst:Variable*){// extends Conjunction{
    val scope = varLst.toList
//    var empty = true
//    var h:Atom = null
//    var t:Conjunction = Empty
//
//    def head = if (h == null) throw new NoSuchElementException("head of empty conjunction") else h
//    def tail = if (t == Empty) throw new NoSuchElementException("tail of empty conjunction") else t

    def apply(conj:Conjunction):Conjunction = {
      conj.scope = this.scope
      conj
//      this.empty = conj.empty
//      this.h = conj.head
//      this.t = conj.tail
//
//      this
    }
  }

  case object Empty extends Conjunction{
    def empty = true
    def head = throw new NoSuchElementException("head of empty conjunction")
    def tail = throw new NoSuchElementException("tail of empty conjunction")
    /*def*/ scope = List()

    override def toList = List()
    override def toString = ""
  }

  final case class &: (hd:Atom, tl:Conjunction) extends Conjunction{
    def head = hd
    def tail = tl
    def empty = false
    /*def*/ scope = List()
  }

}