package fr.emn.criojo.core

import datatype.Variable

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 15, 2010
 * Time: 2:46:19 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * The RuleFactory trait
 * @define THIS RuleFactory
 */
trait RuleFactory{
  type Body = List[Atom]
  type Head = List[Atom]

  def createRule(h:Head,b:Body,g:Guard):Rule = createRule(h,b,g,Set())
  def createRule(h:Head,b:Body,g:Guard,scope:Set[Variable]):Rule

}