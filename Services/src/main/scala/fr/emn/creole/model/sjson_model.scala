package fr.emn.creole.model

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 13, 2010
 * Time: 5:50:59 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._

import scala.reflect.BeanInfo
import scala.collection.mutable.LinkedList
import sjson.json._
import javax.ws.rs.core.UriBuilder

trait DomainClass{
  def isNotEmptyString(str: String) = str != null && str.length > 0
}

@BeanInfo
case class WebVariable(name:String,@JSONTypeHint(classOf[WebRelation]) relation:WebRelation) extends DomainClass{
  private def this() = this(null,null)

  def this(variable:Variable) = {
    this(variable.name, null)
  }

  def getVariable:Variable={
    if (relation == null)
      new Variable(name)
    else{
      val rv = RelVariable(name)
      rv.relation = relation.getRelation
      rv
    }
//    if (relName == null)
//      new Variable(name)
//    else{
//      val rv = new RelVariable(name)
//      rv.relation =
//    }
  }
}

@BeanInfo
case class WebRelation(name:String,url:String) extends DomainClass{
  private def this() = this(null, null)

  def getRelation:Relation = {
    new RemoteRelation(name, UriBuilder.fromUri(url).build())
  }
}

@BeanInfo
case class VarList(@JSONTypeHint(classOf[WebVariable]) vlst: List[WebVariable]) extends DomainClass {
  private def this() = this(List[WebVariable]())

  def this(atom:Atom) = {
    this(
      for(a <- atom.vars) yield{
        new WebVariable(a)
      })
  }

  def length = vlst.length

  def getVarList:List[Variable] = {
    for(v <- vlst) yield {
      v.getVariable
    }
  }

  override def toString = {
    var str = ""
    for(v <- vlst)
      str += v.toString

    str
  }

  override def equals(that:Any)= that match{
    case v: VarList =>
      var same = true
      for (i <- 0 until length)
        same = same && (i < v.length) && (this.vlst(i) == v.vlst(i))
      same
    case _ => false
  }

//  def getVarList:List[Variable] = this.varLst
}