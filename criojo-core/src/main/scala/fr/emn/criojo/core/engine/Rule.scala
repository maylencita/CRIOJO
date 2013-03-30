package fr.emn.criojo.core.engine

import fr.emn.criojo.core.model._

/** Rule defined transformation on Atoms.
  *
  * @author mayleen
  * Date: Jun 9, 2010
  * Time: 5:47:58 PM
  */
trait Rule{
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
    * the reaction is applied, nothing otherwise.
    *
    * @return an option with the result of the execution, None if the rule could not be executed.
    */
  def execute: Option[Reaction]

  override def equals(that: Any) = {
    def innerEq2(a1:Atom, a2:Atom):Boolean = {
      if (a1 == $Sol || a2 == $Sol)
        a1 == a2
      else
        a1.relation.name == a2.relation.name && a1.terms == a2.terms
    }
    def innerEq(lst1:List[Atom], lst2:List[Atom]) =  lst1.forall(a1=>lst2.exists(a2 => innerEq2(a1,a2)))

    that match{
      case r:Rule => innerEq(this.head,r.head) && innerEq(this.body,r.body)
      case _ => false
    }
  }

  override def toString = head.mkString("", "," ,"") + "=>" +
          (if(!scope.isEmpty) "v"+scope.mkString("(",",",")")+"." else "") +
          (if(!guard.empty) "[" + guard + "]?" else "") +
          body.mkString("", "," ,"")
}
