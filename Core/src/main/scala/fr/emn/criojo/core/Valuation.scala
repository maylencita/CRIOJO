package fr.emn.criojo.core

import collection.immutable.HashMap

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 04/03/12
 * Time: 19:04
 */

object Valuation{
  def apply():Valuation = new Valuation(HashMap())
  def apply(elems: (Variable,Term)*) = new Valuation(Map(elems:_*))
}

class Valuation(kv:Map[Variable,Term]){

  protected def keyValues = kv
  var failed = false

  /**
   * Retrieves the value of the associated variable.
   * If no value exists for the given variable, it returns the Undef variable
   * @param key
   * @return
   */
  def apply(key:Variable):Term = keyValues.get(key) match{
    case Some(value) => value
    case _ => Undef
  }

  /*
  Partial defined union:
  if this(x)=v1 and that(x)=v2,
  if v1 != v2 then the resulting valuation is empty
   */
  def union(that:Valuation):Valuation = {
    val domIntersec = this.domain & that.domain
    if (domIntersec.isEmpty || domIntersec.forall(x => this(x) == that(x))){
      new Valuation(this.keyValues ++ that.keyValues)
    }else {
      var valuation = Valuation()
      valuation.failed = true
      valuation
    }

  }

  /**
   * Returns the intersection of two valuations.
   * If the union of the two valuations is empty,
   * their intersection is also empty.
   */
  def intersection(that:Valuation):Valuation = {
    Valuation()
  }

  /**
   * Returns the domain of this valuation
   */
  def domain = keyValues.keySet

  def sameElements(that:Valuation):Boolean = this.keyValues.sameElements(that.keyValues)

  def isEmpty = keyValues.isEmpty

  override def toString = keyValues.toString

  /*
  Methods to avoid compilation errors while Valuation is adopted in all the code
   */
  def find(p:((Variable,Term)) => Boolean) = keyValues.find(p)
  def forall(p:((Variable,Term)) => Boolean) = keyValues.forall(p)
  def toSet = keyValues.toSet
}