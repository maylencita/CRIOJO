package fr.emn.creole.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:58 PM
 * To change this template use File | Settings | File Templates.
 */

class Rule(val head:List[Atom], val body:List[Atom]){
  var active = true;
  var scope:List[Variable] = List()

  def inactivate{
    active = false;
  }

  def setScope(newScope: List[Variable]){
    this.scope = newScope
  }

  override def toString = head.mkString("", "&" ,"") + "=>" + body.mkString("", "&" ,"")
}