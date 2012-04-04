package fr.emn.criojo.core

import Criojo._
import fr.emn.criojo.util.Logger
import statemachine.PartialExecution

/** Rule defined transformation on Atoms.
  *
  * @author mayleen
  * Date: Jun 9, 2010
  * Time: 5:47:58 PM
  */
abstract class Rule extends RelationObserver {
  var scope: List[Variable] = List()

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

  /** Sets the rule scope.
   *
   * @param newScope  The new scope
   */
  def setScope(newScope: List[Variable]) {
    this.scope = newScope
  }

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

  def applyReaction(pe: PartialExecution): Unit

  override def equals(that: Any) = {
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

  /* FIXME: Ensure is deprecated */
  @deprecated var linear = false
  @deprecated var active = true
  @deprecated def inactivate() { active = false }
  @deprecated def setLinear() { linear = true }
  @deprecated def toList:List[Rule] = List(this)
  @deprecated def notifyCham(atom:Atom)
}