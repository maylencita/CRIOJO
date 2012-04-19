package fr.emn.criojo.core

import fr.emn.criojo.ext.expression.Expression
import fr.emn.criojo.ext.expression.types.StringVariable

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 26/10/11
 * Time: 15:30
 */

trait Term {
  def name:String
  def matches(that:Term):Boolean
  def applyValuation(valuation:Valuation):Expression

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation
}

case class PatternNotMatchingException() extends Exception("not a good pattern") {

}

/**
 * A representation of a function.
 * Ex. the successor function: s(x)
 * 3 = s(s(s(0)))
 * @param name Name of the function
 * @param params Parameters of the function
 */
//case class Function(name:String, params: List[Term]) extends Term {
//
//  def applyValuation(valuation:Valuation):Expression = {
//    //TODO Implement
//    this
//  }
//
//  def getValuation(t:Term):Valuation = t match{
//    case f:Function if(this.params.size == f.params.size) =>
//      params.zip(f.params).foldLeft(Valuation()){(v,p) =>
//        v union p._1.getValuation(p._2)
//      }
//    case _ => Valuation()
//  }
//
//  //Inheriting classes must override this method
//  def apply(params: List[Term]):Function = new Function(name, params)
//
//  def matches(that:Term) = that match{
//    case Function(n, t) if(n == name && params.zip(t).forall(p => p._1.matches(p._2))) => true
//    case _ => false
//  }
//
//  override def toString =
//    name + (if(params.isEmpty) "" else params.mkString("(",",",")") )
//}

/**
 * A constant identifier.
 * Ids are needed for session management.
 * @param name String identifier
 */
//case class IdTerm(name:String) extends Term {
//  override def matches(that:Term):Boolean = that match{
//    case IdTerm(n) => n == name
//    case _ => false
//  }
//  def applyValuation(valuation:Valuation):Term = this
//
//  def getValuation(t:Term):Valuation = Valuation(StringVariable(this.name)->t)
//
//  override def toString = name
//}



