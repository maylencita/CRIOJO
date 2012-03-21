package fr.emn.criojo.core


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
  
  var NextHashCode:Int = 0
  
  def apply(rn:String, lst:Term*):Atom = new Atom(rn, lst.toList)
  def apply(rel:Relation, lst:Term*):Atom = {
    val a = new Atom(rel.name, lst.toList)
    a.relation = rel
    a
  }
}

/**
 * The Atom class
 * @define THIS Atom
 */
case class Atom(relName:String, terms: List[Term]) {

  // todo: the reason why 2 identical atoms could not be in the same solution was the way hashCode was implemented.
  // now we increment a variable that will be used to differentiate two identical atoms.
  val hashCodeId = {
    Atom.NextHashCode = Atom.NextHashCode + 1

    if (relation != null && relation.isMultiRel)
      super.hashCode+Atom.NextHashCode
    else
      toString.hashCode+Atom.NextHashCode
  }

  val vars = terms.map{case v:Variable => v; case _ => Undef}

  var persistent:Boolean = false
  protected var active:Boolean = true
  @transient
  var relation:Relation = _

  //TODO Remove this
  def isTrue:Boolean = false
  def isFalse:Boolean = false

  def arity = terms.size

  @deprecated ("Use: setActive(false)")
  def inactivate(){
    active = false
  }  

  def setActive(active:Boolean) { this.active = active }
  def isActive:Boolean = this.active

  /** Returns the term at position n.
   *
   * @param n the position of the term
   * @return the term at position n
   */
  def apply(n:Int):Term = terms(n)

  def applyValuation(valuation:Valuation):Atom = {
    val nuRel:Relation = valuation(Variable(this.relName)) match{
      case rv:RelVariable => rv.relation
      case _ => this.relation
    }
    val newTerms = terms.map(_.applyValuation(valuation))

    val newAtom = new Atom((if(nuRel !=null) nuRel.name else this.relName), newTerms)
    newAtom.relation = nuRel
    newAtom
  }

  /** Applies the given substitutions to the atom.
   *
   * @param vals a Valuation
   * @return an [[fr.emn.criojo.core.Atom]]
   */
  @deprecated("use: applyValuation")
  def applySubstitutions(vals:Valuation):Atom = this

  /** Returns true if the given atom matches this atom.
   *
   * @param that an [[fr.emn.criojo.core.Atom]]
   * @return true if the matching is positive
   */
  def matches(that: Atom) : Boolean = {
    this.relName == that.relName &&
    this.arity == that.arity &&
    this.terms.zip(that.terms).forall(p => p._1.matches(p._2))
  }

  /** Returns true if the given variable is in this atom.
   *
   * @param v a [[fr.emn.criojo.core.Variable]]
   * @return true if the variable is in the list of terms
   */
  def hasVariable(v: Variable): Boolean = {
    this.terms.contains(v)
  }

  // constant hashcode
  override def hashCode = hashCodeId

  override def equals(that: Any):Boolean = that match{
    case a:Atom =>
      if (relation != null && relation.isMultiRel)
        super.equals(a)
      else
        this.relName == a.relName && this.terms.zip(a.terms).forall(p => p._1 == p._2)
    case _ => false
  }

  override def toString =
    (if (active) "" else "-") +
            (if (relation != null) relation else relName) +
            (if (terms.isEmpty) "" else terms.mkString("(",",",")"))

  override def clone:Atom = {
    val a = new Atom(relName,terms)
    a.active = this.active
    a.relation = this.relation
    a
  }
}





