package fr.emn.creole.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 22, 2010
 * Time: 10:18:29 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.util.Logger

object LocalRelation{
  def apply(name:String):Relation = new LocalRelation(name, false)  
}

class LocalRelation(val name:String, val public:Boolean, val isMultiRel:Boolean=true)//(implicit multirelation:Boolean = true)
extends Relation{
  def this(n:String)={
    this(n,false)
  }
  
  var observers:List[RelationObserver] = List()

//  def isMultiRel = this.multirelation

  def addObserver(observer:RelationObserver){
    if (!observers.contains(observer) )
      observers :+= observer
  }
  
  def notifyObservers(a: Atom){
    observers.foreach{o =>Logger.log("[Relation("+name+").notifyObservers]"+ o + " notified by " + a); o.receiveUpdate(a)}
  }

  override def equals(that:Any) = that match{
    case r:Relation => !this.isMultiRel && this.name == r.name
    case _ => false
  }

}