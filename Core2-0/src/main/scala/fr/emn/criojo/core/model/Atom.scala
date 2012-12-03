package fr.emn.criojo.core.model

import relation.Relation


/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:03 PM
 */

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
    * @param that an [[model.Atom]]
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
    def getVal(t1:Term, t2:Expression):Valuation = t1.getValuation(t2)
    this.terms.zip(that.terms).foldLeft(Valuation())((v,p)=>v union getVal(p._1.asInstanceOf[Pattern],p._2.asInstanceOf[Expression]))
  }

  override def toString =
    (if (active) "" else "-") +
      relation +
      (if (terms.isEmpty) "" else terms.mkString("(",",",")"))

}

