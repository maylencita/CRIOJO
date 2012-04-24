package json_criojo

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 15, 2010
 * Time: 11:47:52 AM
 * To change this template use File | Settings | File Templates.
 */

import sjson.json.JsonSerialization._
import fr.emn.criojo.core._
import datatype.Undef

//import fr.emn.criojo.actors.ActorLocalRelation

object JSONUtil {
  import json_criojo.protocols._
  import dispatch.json._

  /**
   * Serializes objects as JSON string
   * @param obj object to be serialized
   */
  def serialize(obj: AnyRef): String = obj match{
    case Atom(relName,terms) =>
      val jatom = JsonAtom(relName,terms.map{
        case rv:RelVariable => JsonTerm("channel",rv.name)
        case Variable(name) => JsonTerm("var",name)
        case ValueTerm(num:Int) => JsonTerm("num",num.toString)
        case ValueTerm(str:String) => JsonTerm("str",str)
        case t => JsonTerm("obj",t.toString)
      })
      tojson[JsonAtom](jatom).toString()

    case _ => ""
  }


  /**
   * Deserializes objects from JSON
   * @param jsonMsg JSON serialized message
   */
  def deserialize(jsonMsg:String):Option[AnyRef] = {
    fromjson[JsonAtom](Js(jsonMsg)) match{
      case JsonAtom(relName,terms) => Some(new Atom(relName, terms.map{
        case JsonTerm("channel",c) => new RelVariable(c)
        case JsonTerm("var",v) => Variable(v)
        case JsonTerm("num",n) => ValueTerm[Int](n.toInt)
        case JsonTerm("str",s) => ValueTerm[String](s)
        case _ => Undef
      }))
      case _ => None
    }
  }

}