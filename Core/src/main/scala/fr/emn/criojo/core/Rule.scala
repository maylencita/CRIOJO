package fr.emn.criojo.core

import datatype.{Valuation, Variable}
import collection.mutable.ListBuffer
import engine.{CriojoEngine}

/** Rule defined transformation on Atoms.
  *
  * @author mayleen
  * Date: Jun 9, 2010
  * Time: 5:47:58 PM
  */
abstract class Rule {

  /** Returns rule header.
    *
    * '''A(x) & B(y)''' --> x < y ?: C(x, y)
    * @return Rule header.
    */
  def head: List[Atom]

  /** Returns rule body.
    *
    * A(x) & B(y) --> x < y ?: '''C(x, y)'''
    * @return Rule body.
    */
  def body: List[Atom]

  /** Returns rule guard.
    *
    * A(x) & B(y) --> '''x < y''' ?: C(x, y)
    * @return Rule guard.
    */
  def guard: Guard

  /** Tests if rule is axiom.
    *
    * @return true if rule is axiom, false otherwise.
    */
  def isAxiom: Boolean = head.isEmpty

  /** Executes rule.
    *
    * Test if the rule has to be executed. If the rule has to be executed,
    * the reaction is applying, nothing otherwise.
    *
    * @return  true if rule is executed, false otherwise.
    */
  def execute: Boolean

  override def equals(that: Any) = {
    def innerEq2(a1:Atom, a2:Atom):Boolean = a1.relation.name == a2.relation.name && a1.patterns == a2.patterns
    def innerEq(lst1:List[Atom], lst2:List[Atom]) =  lst1.forall(a1=>lst2.exists(a2 => innerEq2(a1,a2)))

    that match{
      case r:Rule => innerEq(this.head,r.head) && innerEq(this.body,r.body)
      case _ => false
    }
  }

  override def toString = head.mkString("", "," ,"") + "=>" +
          (if(!guard.empty) "[" + guard + "]?" else "") +
          body.mkString("", "," ,"")
}

trait CriojoRule extends Rule {

  var engine:CriojoEngine

  def addToExecutionIndex(a: Atom, pe: CriojoStateExecution)
  def addToExecution(atom:Atom)
  def removeFromExecutionIndex(atom: Atom)

}

abstract class CriojoRuleImplementation[A,B] extends CriojoRule {
  def applyReaction(pes:List[B], valuation:Valuation, listAtoms:ListBuffer[Atom] = null, oneTime:Boolean = false):Boolean
}

trait CriojoState {}

trait CriojoStateExecution {
//  def isFinal:Boolean
//  var consumed = false
//  var atoms:ListBuffer[Atom]
}
