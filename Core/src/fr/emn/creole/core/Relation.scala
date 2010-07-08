package fr.emn.creole.core

import fr.emn.creole.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 11, 2010
 * Time: 2:16:47 PM
 * To change this template use File | Settings | File Templates.
 */

class Relation(val name:String, val public:Boolean)(implicit multirelation:Boolean = true){
  var observers:List[RelationObserver] = List()

  def isMultiRel = this.multirelation

  def addObserver(observer:RelationObserver){
    observers :+= observer
  }
  def notifyObservers(a: Atom){
    observers.foreach{o =>Logger.log("[Relation.notifyObservers]"+ o + " notified by " + name); o.receiveUpdate(a)}    
  }

  override def equals(that:Any) = that match{
    case r:Relation => !this.multirelation && this.name == r.name
    case _ => false
  }

}