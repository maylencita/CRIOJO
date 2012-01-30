package fr.emn.criojo.util.json

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 14, 2010
 * Time: 3:17:34 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.util.json.jsonparser.Json
import fr.emn.criojo.net.ConnectedCHAM

case class Json2Criojo(machine:ConnectedCHAM){

  def parseList(s:String):List[Atom] =  Json.parse(s) match {
    case atomList: List[_] => atomList.foldLeft(List[Atom]()){(z, b) =>
      parseAtom(b) match{
        case Some(a) => z :+ a
        case _ => z
      }  
    }
    case _ => List()
  }

  def parseAtomList(s:String):List[Atom] =  Json.parse(s) match {
    case atomList: Map[String, _] =>
      atomList("atoms") match{
        case alts:List[_] =>
          alts.foldLeft(List[Atom]()){(z, b) =>
            parseAtom(b) match{
              case Some(a) => z :+ a
              case _ => z
            }
          }
        case _ => List()
      }
    case _ => List()
  }

  def parseAtom(s:String):Option[Atom] = Json.parse(s) match{
      case atomMap: Map[String, _] =>
        Some(new Atom(atomMap("relName").asInstanceOf[String],
          parseList(atomMap("vlst"))))
      case _ => None
  }

  def parseVarList(s:String):List[Term] = {
    val jsonparse = Json.parse(s)
    jsonparse match{
      case atomMap: Map[String, _] => parseList(atomMap("vlst"))
      case _ => null
    }
  }

  def parseAtom(a:Any): Option[Atom] = a match{
    case atomMap: Map[String, _] =>
      Some(new Atom(atomMap("relName").asInstanceOf[String],
        parseList(atomMap("vlst"))))
    case _ => None       
  }

  def parseList(vlst: Any): List[Term] = vlst match{
    case List() => List()
    case varAttrs :: rest => parseVariable(varAttrs) :: parseList(rest)
    case _ => null
  }

  def parseVariable(varAttrs: Any):Term = varAttrs match{
    case attrs:Map[String, _] =>
      val relation = parseRelation(attrs("relation").asInstanceOf[Map[String,_]])
      if (relation == null){
        val value = attrs("value").asInstanceOf[String]
        attrs("typ") match{
          case "Int" => ValueTerm[Int](value.toInt)
          case "String" => ValueTerm[String](value)
          case "Null" => NullVal
          case null => new Variable(attrs("name").asInstanceOf[String])
          case _ => ValueTerm[Any](value)
        }
      }else{
        val rv = new RelVariable(attrs("name").asInstanceOf[String])
        rv.relation = relation
        rv
      }
    case _ => null
  }

  def parseRelation(rel:Map[String, _]): Relation = {
    if (rel != null){
      val relName = rel("name").asInstanceOf[String]
      val relAddress = rel("url").asInstanceOf[String]

//      new machine.RemoteRelationImpl(relName, new URI(relAddress))
      machine.newRemoteRelation(relName,relAddress)
    }else
      null
  }
}