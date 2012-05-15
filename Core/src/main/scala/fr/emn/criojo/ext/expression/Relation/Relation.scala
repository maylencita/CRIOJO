package fr.emn.criojo.ext.expression.Relation

import fr.emn.criojo.core._
import collection.mutable.HashSet
import datatype.Term
import fr.emn.criojo.lang.Molecule


/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 11, 2010
 * Time: 2:16:47 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * The Relation trait
 * @define THIS Relation
 */
trait Relation {

  var observers:HashSet[RelationObserver] = HashSet() //List[RelationObserver] = List()

  def addObserver(observer:RelationObserver){
    if (!observers.contains(observer) )
      observers += observer
  }

  def notifyObservers(a: Atom){
    observers.foreach{ o => o.receiveUpdate(a) }
  }

  def name: String

  //def copy(sol: Solution): Relation
  def apply(vars:Term*):Molecule = Molecule(new Atom(this, vars.toList) :: Nil)

  override def toString = name
}

