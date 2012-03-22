package fr.emn.criojo.core

import Criojo._
import fr.emn.criojo.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:58 PM
 * To change this template use File | Settings | File Templates.
 */


abstract class Rule extends RelationObserver{
  var linear = false;
  var active = true;
  var scope:List[Variable] = List()

  def head:List[Atom]
  def body:List[Atom]
  def guard:Guard

  def inactivate(){
    active = false;
  }

  def setLinear(){
    linear = true
  }

  def setScope(newScope: List[Variable]){
    this.scope = newScope
  }

  def isAxiom:Boolean = head.isEmpty

  def toList:List[Rule] = List(this)
  
  def execute():Boolean =  execute(Valuation())

  def execute (vals:Valuation):Boolean

  def notifyCham(atom:Atom)

  override def equals(that:Any) = {
    def eq2(a1:Atom, a2:Atom):Boolean = a1.relName == a2.relName && a1.terms == a2.terms
    def eq(lst1:List[Atom], lst2:List[Atom]) =  lst1.forall(a1=>lst2.exists(a2 => eq2(a1,a2)))

    that match{
      case r:Rule => eq(this.head,r.head) && eq(this.body,r.body) 
      case _ => false
    }
  }
  override def toString = head.mkString("", "," ,"") + "=>" +
          (if(!scope.isEmpty) "v"+scope.mkString("(",",",")")+"." else "") +
          (if(!guard.empty) "[" + guard + "]?" else "") +
          body.mkString("", "," ,"")

}