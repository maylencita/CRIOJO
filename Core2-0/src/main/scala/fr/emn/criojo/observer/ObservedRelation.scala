package fr.emn.criojo.observer

import fr.emn.criojo.core.model.Atom
import fr.emn.criojo.core.model.relation.Relation

import collection.mutable.HashSet

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/27/12
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
trait ObservedRelation extends Relation{
  var observers:HashSet[RelationObserver] = HashSet() //List[RelationObserver] = List()

  def addObserver(observer:RelationObserver){
    if (!observers.contains(observer) )
      observers += observer
  }

  def notifyObservers(a: Atom){
    observers.foreach{ o => o.receiveUpdate(a) }
  }
}
