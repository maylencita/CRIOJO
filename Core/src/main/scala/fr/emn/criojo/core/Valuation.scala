package fr.emn.criojo.core

import collection.immutable.HashMap

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 04/03/12
 * Time: 19:04
 */

class Valuation extends HashMap[Variable,Term]{

  /*
  Partial defined union:
  if this(x)=v1 and that(x)=v2,
  if v1 != v2 then the resulting valuation is empty
   */
  def union(that:Valuation):Valuation = {
    new Valuation()
  }

  /**
   * Returns the intersection of two valuations.
   * If the union of the two valuations is empty,
   * their intersection is also empty.
   */
  def intersection(that:Valuation):Valuation = {
    new Valuation()
  }

  /**
   * Defines the default value computation for the map, returned when a key is not found.
   * @param key
   */
  override def default(key:Variable):Term = Undef
}