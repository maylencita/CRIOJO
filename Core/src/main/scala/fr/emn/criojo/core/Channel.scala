package fr.emn.criojo.core

import collection.immutable.HashSet

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 25/03/12
 * Time: 10:05
 * To change this template use File | Settings | File Templates.
 */

abstract class Channel extends Relation{
  var observers:HashSet[RelationObserver] = HashSet()

  def location:String

  def public = true

  def isMultiRel = true

  //Doesn't have any observers
  def addObserver(observer: RelationObserver){}
}
