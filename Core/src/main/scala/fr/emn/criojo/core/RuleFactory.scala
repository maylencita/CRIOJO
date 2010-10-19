package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 15, 2010
 * Time: 2:46:19 PM
 * To change this template use File | Settings | File Templates.
 */

trait RuleFactory{
  type Body = List[Atom]
  type Head = List[Atom]

  def createRule(h:Head,b:Body,g:Guard):Rule = createRule(h,b,g,List()) 
  def createRule(h:Head,b:Body,g:Guard,scope:List[Variable]):Rule

  def createGuard(ruleDefs:List[RuleFactory => Rule]):Guard
}