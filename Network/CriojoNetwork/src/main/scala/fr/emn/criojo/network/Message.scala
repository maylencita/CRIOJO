package fr.emn.criojo.network

import collection.mutable.HashMap
import scala.util.parsing.combinator.JavaTokenParsers
import fr.emn.criojo.core.model.{Atom, Term}
import fr.emn.criojo.expression.scala.{WrapScalaInt, WrapScalaString, WrapScalaBoolean}
import fr.emn.criojo.core.model.relation.{AdhocChannel, Channel, ChannelLocation}

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/14/12
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */

object MessageArgsParser {
  def parse(s:String):List[Term] = {

    val argsParser:MessageArgsParser = MessageArgsParser(Nil)
    val result = argsParser.parseAll(argsParser.args, s).get

    result match {
      case l:List[Term] => l
      case _ => List[Term]()
    }
  }
}

case class MessageArgsParser(var e:List[Term]) extends JavaTokenParsers {

  val fpt = floatingPointNumber
  val id =  ident
  val num = wholeNumber
  val dec = decimalNumber
  val str = stringLiteral
  val bool: Parser[String] = "[true|false]".r

  def url: Parser[List[String]] = ("@"~id ^^ {case _~(idChannel:String) => List[String](idChannel) }
  | (urlPrefix.+)~id ^^ {case (u:List[String])~(s:String) => u:+s } //.foldLeft("") {case (v,c) => v+c }+s }
  )

  def urlPrefix : Parser[String] = id~"." ^^ {case (s:String)~_ => s} //+"." }

  def args: Parser[List[Term]] = repsep(exp,",") ^^ { case exps => exps.foldLeft(List[Term]()){ (v,e) => v:::List(e) } }

  def exp: Parser[Expression] = (
    "true" ^^ { case s => WrapScalaBoolean(s.toBoolean) }
  | "false" ^^ { case s => WrapScalaBoolean(s.toBoolean) }
  | fpt ^^ { case s => WrapScalaInt(s.toFloat.toInt) }
  | dec ^^ { case s => WrapScalaInt(s.toFloat.toInt) }
  | num ^^ { case s => WrapScalaInt(s.toDouble.toInt) }
  | url ^^ { case s:List[String] => ChannelLocation(s.mkString("."), s.last) }
  | id ^^ { case s => WrapScalaString(s) }
  | str ^^ { case s => WrapScalaString(s) }
  )

}

object Message {
  def parse(s:String):Message = {
    var message:Message = new Message()

    s.replace("{","").replace("}","").split(";").foreach(association => {
      var array:Array[String] = association.split(":")
      message.properties.put(array(0).toLowerCase.replace("\"","").replace("\'","").replace(" ",""), array(1).replace("\'",""))
    })

    message
  }

  def parseArgs(s:String):List[Term] = {
    MessageArgsParser.parse(s)
  }

  def atomToMessage(from:String, to:String, a:Atom):String = {

//    def patternsToString(pattern:List[Term]):String = pattern match {
//      case Nil => ""
//      case (head:Term)::l if l!=Nil => head.toString+","+patternsToString(l)
//      case (head:Term)::l if l==Nil => head.toString
//    }

    "{'to':'"+to+"'; 'from':'"+from+"'; 'channel':'"+a.relation.name+"';'args':'"+a.terms.mkString(",")+"'}"
  }
}

class Message {

  var properties:HashMap[String, String] = new HashMap[String, String]()

  def to:Option[String] = properties.get("to")
  def from:Option[String] = properties.get("from")
  def channel:Option[String] = properties.get("channel")
  def args:Option[String] = properties.get("args")
  
  def atom:Option[Atom] = (channel.get, args.get) match {
    case (sChannel:String, sArgs:String) =>
      Some(
        new AdhocChannel(sChannel, ChannelLocation(to.getOrElse(""),sChannel)).newAtom(
          Message.parseArgs(sArgs))
      )
//      Some(Atom.apply(LocalRelation(sChanel), Message.parseArgs(sArgs)))
  }
}
