package fr.emn.criojo.core.model

import relation.Relation
import fr.emn.criojo.core.ProhibitedOperation

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:03 PM
 */

/**
 * An object to make reference to the rest of the solution.
 * Serves to reset rules when executed
 */
object $Sol extends Atom{
  def relation = throw new ProhibitedOperation("relation of Solution variable")

  def terms = throw new ProhibitedOperation("terms of Solution variable")

  override def applyValuation(valuation:Valuation):Atom = this

  override def correspondsTo(that: Atom) = that == $Sol

  override def getValuation(that: Atom) = Valuation()

  override def toString = "$Sol"
}

/**
 * The Atom class
 * @define THIS Atom
 */
trait Atom{
  var persistent:Boolean = false
  protected var active:Boolean = true

  def relation:Relation
  def terms: List[Term]

  def arity = terms.size

  def setActive(active:Boolean) { this.active = active }
  def isActive:Boolean = this.active

  def unary_! = {
    val newAtom = this.relation.newAtom(terms)
    newAtom.persistent = true
    newAtom
  }

  /**
   * Returns a new instance of this Atom, where its terms have been reduced
   * @return
   */
  def reduce() = {
    this.relation.newAtom(terms.map(t => t.reduce()))
  }

  /**
   * Applies a valuation to the terms of this Atom, and returns a new instance
   * @param valuation
   * @return
   */
  def applyValuation(valuation:Valuation):Atom = {
    this.relation.newAtom(
      terms.map({ pattern =>
        pattern.applyValuation(valuation).reduce()
      }))
  }

  /** Returns true if the given atom correspondsTo this atom.
    *
    * @param that an [[criojo.core.model.Atom]]
    * @return true if the matching is positive
    */
  def correspondsTo(that: Atom) : Boolean = {
    this.relation.name == that.relation.name &&
      this.arity == that.arity &&
      this.terms.zip(that.terms).forall(p => p._1.matches(p._2.asInstanceOf[Expression]))
  }

  /**
   * Calculates a valuation from the comparison of this Atom against another Atom
   * @param that
   * @return
  */
  def getValuation(that: Atom):Valuation = {
    def getVal(t1:Term, t2:Term):Valuation =  t1 match {
      case pattern:Pattern => t2 match {
        case expr:Expression => pattern.getValuation(expr)
        case _ => throw new Error("Term " + t2 + " in atom " + this + " is not an expression.")
      }
      case _ => throw new Error("Term " + t1 + " in atom " + this + " is not a pattern.")
    }

    this.terms.zip(that.terms).foldLeft(Valuation())((v,p)=>v union getVal(p._1,p._2))
  }

  override def toString =
    (if (active) "" else "-") +
      relation +
      (if (terms.isEmpty) "" else terms.mkString("(",",",")"))

}

