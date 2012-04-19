package fr.emn.criojo.core

import fr.emn.criojo.ext.expression.Expression

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:52:26 PM
 * To change this template use File | Settings | File Templates.
 */

@serializable
trait Variable extends Term {

  def getName():String

  def applyValuation(valuation:Valuation):Expression = {
    var value:Term = null
    valuation.forall(v => {
      if(v._1.equals(this)) {
        value = v._2
        false
      }
      else {
        true
      }
    })

    value match {
      case exp:Expression => exp
      case _:Any => throw new Exception("Boom!")
    }
  }

  def matches(that:Term) = true

//  def +(index:String):IdTerm = {
//     new IdTerm(this.getName()+index)
//  }

  override def hashCode = this.name.hashCode

  override def equals(that: Any):Boolean = that match{
    case v:Variable => this.name == v.getName()
    case _ => false
  }

  override def toString = name

  def toInt:Int = toString.toInt

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = Valuation(this->t)
}


@serializable
case class ChannelVariable(name:String) extends Variable {

  def getName():String = name

  @transient
  var relation:Relation = _ //TODO Initialize in constructor -> make method CHAM.newRelation()

  override def toString = if (relation == null) this.name else relation.toString
}

object Undef extends Variable(){
  //Undef matches anything
  def name = "UndefinedVariable"
  def getName():String = name
  
  override def matches(that:Term) = true
}