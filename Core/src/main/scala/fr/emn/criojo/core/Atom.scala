package fr.emn.criojo.core

import Creole.Substitution

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:03 PM
 * To change this template use File | Settings | File Templates.
 */

object Atom{
  def apply(rn:String, lst:Variable*):Atom = new Atom(rn, lst.toList)
  def apply(rel:Relation, lst:Variable*):Atom = {
    val a = new Atom(rel.name, lst.toList)
    a.relation = rel
    a
  }
}

case class Atom (val relName:String, val vars: List[Variable]) {

  var active:Boolean = true
  var relation:Relation = _

  def isTrue:Boolean = false
  def isFalse:Boolean = false

  def arity = vars.size

  def inactivate{
    active = false
  }  

  def apply(n:Int):Variable = vars(n) 

  def applySubstitutions(subs:List[Substitution]):Atom = {
    var nuRel:Relation = subs.find(s => s._1.name == this.relName) match{
      case Some(sub) => sub match{
        case (_, rv:RelVariable) => rv.relation
        case _ => this.relation
      }
      case _ => this.relation
    }

    var nuRelName:String = subs.find(s => s._1.name == this.relName) match{
      case Some(nv) => nv._2.name
      case _ => this.relName
    }

    def replace(variable:Variable):Variable = {
      val newVar = subs.find(s => s._1.name == variable.name)
      newVar match{
        case Some(nv) => nv._2
        case _ =>
          variable match{
            case rv:RelVariable => if (rv.relation != null) rv else Undef
            case _ =>
              if (variable.name.startsWith("$"))
                variable
              else
                Undef
          }
      }
    }

    val newVars = this.vars.map {v => replace(v)}

    val atom = new Atom(/*nuRel.name*/ nuRelName, newVars)
    atom.relation = nuRel
    atom
  }

  def matches(that: Atom) : Boolean = {
    this.relName == that.relName &&
    this.arity == that.arity &&
    this.vars.zip(that.vars).forall(p => p._1.matches(p._2))
  }

  def hasVariable(v: Variable): Boolean = {
    this.vars.contains(v)
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
        this.relation == a.relation && this.vars.zip(a.vars).forall(p => p._1 == p._2)
    case _ => false
  }

  override def toString =
    (if (active) "" else "!") + relName + (if (vars.isEmpty) "" else vars.mkString("(",",",")"))

  override def clone:Atom = {
    val a = new Atom(relName,vars)
    a.active = this.active
    a.relation = this.relation
    a
  }
}

//object True extends Atom ("true", List()){
case class True extends Atom ("true", List()){ 
  relation = new LocalRelation("True", false, false)
  override def isTrue:Boolean = true
  override def isFalse:Boolean = false

  override def applySubstitutions(subs:List[Substitution]) = this
  override def hashCode = "true".hashCode
  override def clone = this
}

object False extends Atom ("false", List()){
  relation = new LocalRelation("False", false, false)
  override def isTrue:Boolean = false
  override def isFalse:Boolean = true

  override def applySubstitutions(subs:List[Substitution]) = this
  override def hashCode = "false".hashCode
  override def clone = this
//  override def equals(that:Any) = that match{
//    case False => true
//    case _ => false
//  }
}