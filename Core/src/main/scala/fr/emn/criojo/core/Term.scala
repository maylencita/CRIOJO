package fr.emn.criojo.core

import Criojo.Substitution

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 26/10/11
 * Time: 15:30
 */

trait Term {
  def name:String
  def matches(that:Term):Boolean


}

case class Function(name:String, params: List[Term]) extends Term{

  //Inheriting classes must override this method
  def apply(params: List[Term]):Function = new Function(name, params)

  def matches(that:Term) = that match{
    case Function(n, t) if(n == name && params.zip(t).forall(p => p._1.matches(p._2))) => true
    case _ => false
  }

  override def toString =
    name + (if(params.isEmpty) "" else params.mkString("(",",",")") )
}

case class IdTerm(n:String) extends Variable(n){
  override def matches(that:Term):Boolean = that match{
    case IdTerm(n) => n == name
    case _ => false
  }

  override def toString = name
}



