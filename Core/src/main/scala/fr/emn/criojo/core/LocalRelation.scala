package fr.emn.criojo.core

import collection.immutable.HashSet

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 22, 2010
 * Time: 10:18:29 AM
 * To change this template use File | Settings | File Templates.
 */


object LocalRelation{
  def apply(name:String):Relation = new LocalRelation(name, false)  
}

@serializable
class LocalRelation(val name:String, val public:Boolean, val isMultiRel:Boolean=true)
extends Relation{
  def this(n:String)={
    this(n,false)
  }
  
  var observers:HashSet[RelationObserver] = HashSet() //List[RelationObserver] = List()

  def addObserver(observer:RelationObserver){
    if (!observers.contains(observer) )
      observers += observer
  }
  
  def notifyObservers(a: Atom){
    observers.foreach{o =>
//      if (a.isActive){
        o.receiveUpdate(a)
//      }
    }
  }

  def copy(sol:Solution):Relation =
    new LocalRelation(this.name, this.public, this.isMultiRel)

  override def equals(that:Any) = that match{
    case r:Relation => !this.isMultiRel && this.name == r.name
    case _ => false
  }

}

