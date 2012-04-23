package fr.emn.criojo.core

import fr.emn.criojo.ext.expression.Relation.{Relation}
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation


/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:03 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * The Atom singleton
 * @define THIS Atom
 */
@serializable
object Atom{

  def apply(rn:String, lst:Term*):Atom = new Atom(LocalRelation(rn), lst.toList)
  def apply(rel:Relation, lst:Term*):Atom = new Atom(rel, lst.toList)
}

/**
 * The Atom class
 * @define THIS Atom
 */
//TODO pass relation as parameter in construction
case class Atom(relation:Relation, patterns: List[Term]) {

  def this(relationName:String, patterns:List[Term]) = this(LocalRelation(relationName), patterns)

  var persistent:Boolean = false
  protected var active:Boolean = true

  //TODO Remove this
  def isTrue:Boolean = false
  def isFalse:Boolean = false

  def arity = patterns.size

  @deprecated ("Use: setActive(false)")
  def inactivate(){
    active = false
  }

  def setActive(active:Boolean) { this.active = active }
  def isActive:Boolean = this.active

  def applyValuation(valuation:Valuation):Atom = new Atom(relation, patterns.map({ pattern:Term => pattern.applyValuation(valuation).reduce() }))

  /** Applies the given substitutions to the atom.
   *
   * @param vals a Valuation
   * @return an [[fr.emn.criojo.core.Atom]]
   */
  @deprecated("use: applyValuation")
  def applySubstitutions(vals:Valuation):Atom = this

  /** Returns true if the given atom correspondsTo this atom.
   *
   * @param that an [[fr.emn.criojo.core.Atom]]
   * @return true if the matching is positive
   */
  def correspondsTo(that: Atom) : Boolean = {
      this.relation.name == that.relation.name &&
      this.arity == that.arity &&
      this.patterns.zip(that.patterns).forall(p => p._1.matches(p._2.asInstanceOf[Expression]))
  }

  // fixme: le premier atome contient une liste de patterns et le second une list d'expression
  def getValuation(that: Atom):Valuation = {
//    def getVal(t1:Term, t2:Term):Valuation = t2 match {
//      case exp:Expression => t1.getValuation(exp)
//      case _ => throw new PatternNotMatchingException
//    }
    def getVal(t1:Pattern, t2:Expression):Valuation = t1.getValuation(t2)
    this.patterns.zip(that.patterns).foldLeft(Valuation())((v,p)=>v union getVal(p._1.asInstanceOf[Pattern],p._2.asInstanceOf[Expression]))
  }


  /** Returns true if the given variable is in this atom.
   *
   * @param v a [[fr.emn.criojo.core.Variable]]
   * @return true if the variable is in the list of patterns
   */
  def hasVariable(v: Variable): Boolean = {
    this.patterns.contains(v)
  }

  // constant hashcode
  override def hashCode = super.hashCode()

  override def equals(that: Any):Boolean = that match{
    case a:Atom => this.relation.name == a.relation.name && this.patterns.zip(a.patterns).forall(p => p._1 eq  p._2)

    case _ => false
  }

  override def toString =
    (if (active) "" else "-") +
            relation +
            (if (patterns.isEmpty) "" else patterns.mkString("(",",",")"))

  override def clone:Atom = new Atom(relation,patterns)
}





