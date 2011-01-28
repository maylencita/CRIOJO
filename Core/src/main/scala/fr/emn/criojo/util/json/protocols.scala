package fr.emn.criojo.util.json

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 16/12/10
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._

import dispatch.json._
import sjson.json.{Format, JsonSerialization, DefaultProtocol}
import JsonSerialization._
import DefaultProtocol._

object protocols{

  implicit val VarFormat: Format[Variable] = wrap[Variable, String]("name")(_.name, Variable(_))

  object AtomProtocol extends DefaultProtocol{
    implicit object AtomFormat extends Format[Atom]{
      def reads(value: JsValue): Atom = value match{
        case JsObject(m) =>
          new Atom(fromjson[String](m(JsString("relName"))), fromjson[List[Variable]](m(JsString("vars"))))
        case _ => throw new RuntimeException("JsObject expected")
      }
      def writes(p: Atom): JsValue = {
        JsObject(List(
          (tojson("relName").asInstanceOf[JsString], tojson(p.relName)),
          (tojson("vars").asInstanceOf[JsString], tojson(p.vars))
        ))
      }
    }
  }

}