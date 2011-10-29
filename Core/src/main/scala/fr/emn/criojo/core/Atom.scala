package fr.emn.criojo.core

import Criojo.Substitution

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:03 PM
 * To change this template use File | Settings | File Templates.
 */

@serializable
object Atom{
  def apply(rn:String, lst:Term*):Atom = new Atom(rn, lst.toList)
  def apply(rel:Relation, lst:Term*):Atom = {
    val a = new Atom(rel.name, lst.toList)
    a.relation = rel
    a
  }
}

case class Atom(relName:String, terms: List[Term]) {
//  def this(relName:String, terms: List[Variable]){
//    this(relName, List[Term](), terms)
//  }
//  def this(relName:String, terms: List[Term]){
//    this(relName, terms, List[Variable]())
//  }

  val vars = terms.map{case v:Variable => v; case _ => Undef}

  protected var active:Boolean = true
  @transient
  var relation:Relation = _

  def isTrue:Boolean = false
  def isFalse:Boolean = false

  def arity = terms.size

  @deprecated ("Use: Solution.inactivate")
  def inactivate(){
    active = false
  }  

  def setActive(active:Boolean) { this.active = active }
  def isActive:Boolean = this.active
  
  def apply(n:Int):Term = terms(n)

  def applySubstitutions(subs:List[Substitution]):Atom = {
    val nuRel:Relation = subs.find(s => s._1.name == this.relName) match{
      case Some(sub) => sub match{
        case (_, rv:RelVariable) => rv.relation
        case _ => this.relation
      }
      case _ => this.relation
    }

    val nuRelName:String = subs.find(s => s._1.name == this.relName) match{
      case Some(nv) => nv._2.name
      case _ => this.relName
    }

//    def replace(variable:Variable):Variable = {
//      val newVar = subs.find(s => s._1.name == variable.name)
//      newVar match{
//        case Some((v1:Variable,v2:Variable)) => v2//nv._2
//        case _ =>
//          variable match{
//            case rv:RelVariable => if (rv.relation != null) rv else Undef
//            case _ =>
//              if (variable.name.startsWith("$"))
//                variable
//              else
//                Undef
//          }
//      }
//    }

    def applySubstitution(term:Term):Term = term match{
      case v:Variable => findSubstitution(v)
      case v:ValTerm => v
      case Function(n, plst) => Function(n, plst.map(p => applySubstitution(p)))
      case _ => Undef
    }
    def findSubstitution(variable:Variable) =
      subs.find(s => s._1.name == variable.name) match{
        case Some((v,t)) => t
        case _ =>
          variable match{
            case rv:RelVariable if (rv.relation != null) => rv
            case rv:RelVariable if (rv.relation == null) => Undef
            case _ => Undef
          }
    }

    val newTerms = terms.map(applySubstitution(_))

    val newAtom = new Atom(nuRelName, newTerms)
    newAtom.relation = nuRel
    newAtom
  }

  def matches(that: Atom) : Boolean = {
    this.relName == that.relName &&
    this.arity == that.arity &&
    this.terms.zip(that.terms).forall(p => p._1.matches(p._2))
  }

  def hasVariable(v: Variable): Boolean = {
    this.terms.contains(v)
  }

  override def hashCode =
    if (relation != null && relation.isMultiRel)
      super.hashCode
    else
      toString.hashCode

  override def equals(that: Any):Boolean = that match{
    case a:Atom =>
      if (relation != null && relation.isMultiRel)
        super.equals(a)
      else
        this.relName == a.relName && this.terms.zip(a.terms).forall(p => p._1 == p._2)
    case _ => false
  }

  override def toString =
    (if (active) "" else "!") +
            (if (relation != null) relation else relName) +
            (if (terms.isEmpty) "" else terms.mkString("(",",",")"))

  override def clone:Atom = {
    val a = new Atom(relName,terms)
    a.active = this.active
    a.relation = this.relation
    a
  }
}

