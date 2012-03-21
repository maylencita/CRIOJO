package fr.emn.criojo.core

import Criojo.Substitution

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 16, 2010
 * Time: 11:32:59 AM
 * To change this template use File | Settings | File Templates.
 */

trait Guard {
  def empty:Boolean
  @deprecated ("use: eval(Valuation)")
  def eval(sol:Solution, subs:List[Substitution]):Boolean = false

  def eval(valuation:Valuation):Boolean
}

object EmptyGuard extends Guard{
  val starter = Top()
  override def empty = true
  override def eval(vals:Valuation):Boolean = true

  def initRelations(){}
}

// TODO Are these really necessary?
object Top{
  def relName = "true"
  def apply():Top = new Top(List())
  def apply(vseq:Variable*):Top = new Top(vseq.toList)
}

class Top(vlst:List[Term]) extends Atom ("true", vlst){
  def this() = this(List())
  
  relation = new LocalRelation("true", false, false)
  override def isTrue:Boolean = true
  override def isFalse:Boolean = false

  override def hashCode = "true".hashCode
  override def clone = this
}

object False extends Atom ("false", List()){
  relation = new LocalRelation("false", false, false)
  override def isTrue:Boolean = false
  override def isFalse:Boolean = true

  override def applySubstitutions(vals:Valuation) = this
  override def hashCode = "false".hashCode
  override def clone = this
}