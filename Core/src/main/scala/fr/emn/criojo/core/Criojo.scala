package fr.emn.criojo.core

import collection.immutable.HashMap

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 4:53:46 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * The Criojo singleton
 * @define THIS Atom
 */
object Criojo{
  var id = 0

  @deprecated("Use: Valuation")
  type Substitution = Pair[Variable, Term]//Variable]

  /** Increments and returns an Id
   *
   * @return a incremented [[scala.Int]] Id
   */
  def newId = {
    id += 1
    id
  }

  def getValuation(l1:List[Term], l2:List[Term]):Valuation = {
    def getVal(t1:Term, t2:Term):Valuation = {
      t1.getValuation(t2)
    }

    l1.zip(l2).foldLeft(Valuation())((v,p)=>v union getVal(p._1,p._2))
  }
}

/**
 * The Indexator singleton
 * @define THIS Atom
 */
object Indexator{
  var index=0

  /** Increments and returns an index
   *
   * @return a incremented [[scala.Int]] index
   */
  def getIndex = {
     index +=1
     index
  }
}
