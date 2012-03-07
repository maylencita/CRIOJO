package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 26/10/11
 * Time: 15:30
 */

trait Term {
  def name:String
  def matches(that:Term):Boolean
  def applyValuation(valuation:Valuation):Term
}

/**
 * A representation of a function.
 * Ex. the successor function: s(x)
 * 3 = s(s(s(0)))
 * @param name Name of the function
 * @param params Parameters of the function
 */
case class Function(name:String, params: List[Term]) extends Term {

  def applyValuation(valuation:Valuation):Term = {
    //TODO Implement
    this
  }

  //Inheriting classes must override this method
  def apply(params: List[Term]):Function = new Function(name, params)

  def matches(that:Term) = that match{
    case Function(n, t) if(n == name && params.zip(t).forall(p => p._1.matches(p._2))) => true
    case _ => false
  }

  override def toString =
    name + (if(params.isEmpty) "" else params.mkString("(",",",")") )
}

/**
 * A constant identifier.
 * Ids are needed for session management.
 * @param name String identifier
 */
case class IdTerm(name:String) extends Term {
  override def matches(that:Term):Boolean = that match{
    case IdTerm(n) => n == name
    case _ => false
  }
  def applyValuation(valuation:Valuation):Term = this

  override def toString = name
}



