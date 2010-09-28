package fr.emn.creole.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 22, 2010
 * Time: 10:18:29 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.util.Logger

class LocalRelation(val localName:String, val localPublic:Boolean)(implicit multirelation:Boolean = true) extends Relation{
  def this(n:String)={
    this(n,false)
  }
  
  var observers:List[RelationObserver] = List()

  def name = this.localName

  def public = this.localPublic

  def isMultiRel = this.multirelation

  def addObserver(observer:RelationObserver){
    observers :+= observer
  }
  def notifyObservers(a: Atom){
    observers.foreach{o =>Logger.log("[Relation.notifyObservers]"+ o + " notified by " + a); o.receiveUpdate(a)}
  }

  override def equals(that:Any) = that match{
    case r:Relation => !this.multirelation && this.name == r.name
    case _ => false
  }

}