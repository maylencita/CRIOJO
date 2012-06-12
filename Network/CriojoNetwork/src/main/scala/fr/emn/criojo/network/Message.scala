package fr.emn.criojo.network

import fr.emn.criojo.core.Atom
import collection.mutable.HashMap
import fr.emn.criojo.core.datatype.{Term, Expression}
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.ext.expression.ScalaString.constructor.WrapScalaString
import scala.util.parsing.combinator.JavaTokenParsers
import fr.emn.criojo.ext.expression.Relation.ChannelLocation

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

  def url: Parser[String] = "@"~id ^^ {case _~(idChannel:String) => idChannel } | (urlPrefix.+)~id ^^ {case (u:List[String])~(s:String) => u.foldLeft("") {case (v,c) => v+c }+s }
  def urlPrefix : Parser[String] = id~"." ^^ {case (s:String)~_ => s+"." }

  def args: Parser[List[Term]] = repsep(exp,",") ^^ { case exps => exps.foldLeft(List[Term]()){ (v,e) => v:::List(e) } }
  def exp: Parser[Term] = "true" ^^ { case s => WrapScalaBoolean(s.toBoolean) } |
    "false" ^^ { case s => WrapScalaBoolean(s.toBoolean) } | fpt ^^ { case s => WrapScalaInt(s.toFloat.toInt) } |
    str ^^ { case s => WrapScalaString(s) } | dec ^^ { case s => WrapScalaInt(s.toFloat.toInt) } | url ^^ { case s:String => new ChannelLocation(s) } |
    id ^^ { case s => WrapScalaString(s) } | num ^^ { case s => WrapScalaInt(s.toDouble.toInt) }

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

    def patternsToString(pattern:List[Term]):String = pattern match {
      case (head:Term)::l if l!=Nil => head.toString+","+patternsToString(l)
      case (head:Term)::l if l==Nil => head.toString
      case Nil => ""
    }

    "{'to':'"+to+"'; 'from':'"+from+"'; 'channel':'"+a.relation.name+"';'args':'"+patternsToString(a.patterns)+"'}"
  }
}

class Message {

  var properties:HashMap[String, String] = new HashMap[String, String]()

  def to:Option[String] = properties.get("to")
  def from:Option[String] = properties.get("from")
  def channel:Option[String] = properties.get("channel")
  def args:Option[String] = properties.get("args")
  
  def atom:Option[Atom] = (channel.get, args.get) match {
    case (sChanel:String, sArgs:String) => Some(Atom.apply(LocalRelation(sChanel), Message.parseArgs(sArgs)))
  }
}
