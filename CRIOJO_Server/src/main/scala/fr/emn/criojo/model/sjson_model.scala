package fr.emn.criojo.model

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 13, 2010
 * Time: 5:50:59 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._

import fr.emn.criojo.virtualmachine._

import scala.reflect.BeanInfo
import sjson.json._

import java.net.URI

trait DomainClass{
  def isNotEmptyString(str: String) = str != null && str.length > 0
}

@BeanInfo
case class WebVariable(name:String, typ:String, value:String, @JSONTypeHint(classOf[WebRelation]) relation:WebRelation) extends DomainClass{
  private def this() = this(null,null,null,null)

  def this(variable:Variable) =
    this(variable.name,
      variable match{
        case Null => "Null"
        case Value(i:Int) => "Int"
        case Value(s:String) => "String"
        case _ => null
      },
      variable match{
        case Null => null
        case vv:Value[_] => vv.value.toString
        case _ => null
      },
      variable match{
        case rv: RelVariable =>
          new WebRelation(rv.name, rv.relation match{
            case PublicRelation(name, url) => url.toString
            case _ => ""
          })
        case _ => null
      })


//  def getVariable:Variable={
//    if (relation == null)
//      if (value == null || value == "null")
//        new Variable(name)
//      else
//        typ match{
//          case "Int" => Value[Int](value.toInt)
//          case "String" => Value[String](value)
//          case _ => Value[Any](value)
//        }
//    else{
//      val rv = RelVariable(name)
//      rv.relation = relation.getRelation
//      rv
//    }
//  }
}

@BeanInfo
case class WebRelation(name:String,url:String) extends DomainClass{
  private def this() = this(null, null)

//  def getRelation:Relation = {
//    new RemoteRelationImpl(name, new URI(url))
//  }
}

@BeanInfo
case class WebAtom(relName:String, @JSONTypeHint(classOf[WebVariable]) vlst: List[WebVariable]) extends DomainClass {
  private def this() = this(null, List[WebVariable]())

  def this(atom:Atom) = {
    this(atom.relName,
      for(a <- atom.vars) yield{
        new WebVariable(a)
      })
  }

  def length = vlst.length

//  def getVarList:List[Variable] = {
//    var varList = List[Variable]()
//    for(v <- vlst){
//      varList ::= v.getVariable
//    }
//    varList
//  }

  override def toString = {
    var str = ""
    for(v <- vlst)
      str += v.toString

    str
  }

  override def equals(that:Any)= that match{
    case v: WebAtom =>
      var same = true
      for (i <- 0 until length)
        same = same && (i < v.length) && (this.vlst(i) == v.vlst(i))
      same
    case _ => false
  }

//  def getVarList:List[Variable] = this.varLst
}