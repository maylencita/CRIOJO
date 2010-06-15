package fr.emn.creole.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 11, 2010
 * Time: 2:16:47 PM
 * To change this template use File | Settings | File Templates.
 */

class Relation(val name:String, multirelation:Boolean = false, val public:Boolean){
  var observers:List[RelationObserver] = List()

  def isMultiRel = this.multirelation

  def addObserver(observer:RelationObserver){
    observers :+= observer
  }
  def notifyObservers{
    observers.foreach(_.receiveUpdate)    
  }

  override def equals(that:Any) = that match{
    case r:Relation => !this.multirelation && this.name == r.name
    case _ => false
  }

}