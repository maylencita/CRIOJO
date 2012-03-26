package json_criojo

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

object protocols {
  case class JsonAtom(relName:String,terms:List[JsonTerm])
  case class JsonTerm(tipo:String,value:String)

  implicit val TermFormat: Format[JsonTerm] =
    asProduct2("tipo","value")(JsonTerm)(JsonTerm.unapply(_).get)

  implicit val JsonAtomFormat: Format[JsonAtom] =
    asProduct2("relName","terms")(JsonAtom)(JsonAtom.unapply(_).get)

/*
  object Atom extends DefaultProtocol{
    import dispatch.json._
    import JsonSerialization._

    implicit object AtomFormat extends Format[Atom]{
      def reads(jobj:JsValue):Atom = new Atom("nu",List())
      def writes(atom:Atom):JsValue = {
        val distTermLst = atom.terms.map{
          case v:Variable => DistribTerm("var",v.name)
          case ValueTerm(num:Int) => DistribTerm("num",num.toString)
          case ValueTerm(str:String) => DistribTerm("str",str)
          case _ => throw new RuntimeException("Unsupported Term")
        }
        JsObject(List(
          (tojson("relName").asInstanceOf[JsString],tojson(atom.relName)),
          (tojson("terms").asInstanceOf[JsString], tojson(distTermLst)) // new JsArray(distTermLst.map(tojson(_))))
        ))
      }
    }
  }

  implicit val DistribTermFormat : Format[DistribTerm] = new Format[DistribTerm]{
    def reads(json:JsValue):DistribTerm = json match{
      case JsObject(m) =>
        DistribTerm(
          fromjson[String](m(JsString("type"))),
          fromjson[String](m(JsString("value")))
        )
      case _ => throw new RuntimeException("Invalid Term Type")
    }
    def writes(t:DistribTerm):JsValue =
      JsObject(List(
        (tojson("type").asInstanceOf[JsString],tojson(t.tipo)),
        (tojson("value").asInstanceOf[JsString],tojson(t.value))
      ))
  }
*/
//  object DistribTerm extends DefaultProtocol{
//    import JsonSerialization._
//    implicit object DistribTermFormat extends Format[DistribTerm]{
//      def reads(json:JsValue):DistribTerm = json match{
//        case JsObject(m) =>
//          DistribTerm(
//            fromjson[String](m(JsString("type"))),
//            fromjson[String](m(JsString("value")))
//          )
//        case _ => throw new RuntimeException("Invalid Term Type")
//      }
//      def write(t:DistribTerm):JsValue =
//        JsObject(List(
//          (tojson("type").asInstanceOf[JsString],tojson(t.tipo)),
//          (tojson("value").asInstanceOf[JsString],tojson(t.value))
//        ))
//    }
//  }

}