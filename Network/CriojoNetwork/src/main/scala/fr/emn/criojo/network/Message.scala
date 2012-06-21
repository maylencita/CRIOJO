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

trait PropertyValue {
}

case class PropertySingleValue(name:String, value:Term) extends PropertyValue{
  override def toString():String = value.toString
}
case class PropertyListValue(name:String, value:List[Term]) extends PropertyValue{
  override def toString():String = "["+value.foldLeft("") {case (v,c) => if(v=="") v+c.toString else v+","+c.toString}+"]"
}

object MessageParser {

  def termToString(t:Term):String = t match {
    case WrapScalaBoolean(b) => b.toString
    case WrapScalaInt(i) => i.toString
    case WrapScalaString(s) => "\""+s+"\""
    case cl:ChannelLocation => "\"@"+cl.url+"\""
  }

  def parseExp(s:String):Term = {

    val parser:MessageParser = MessageParser()
    val result = parser.parseAll(parser.exp, s).get

    result match {
      case t:Term => t
    }
  }

  def parseProp(s:String):PropertyValue = {

    val parser:MessageParser = MessageParser()
    val result = parser.parseAll(parser.property, s).get

    result match {
      case p:PropertyValue => p
    }
  }

  def parseArgs(s:String):List[Term] = {

    val parser:MessageParser = MessageParser()
    val result = parser.parseAll(parser.args, s).get

    result match {
      case l:List[Term] => l
      case _ => List[Term]()
    }
  }

  def parse(s:String):HashMap[String, PropertyValue] = {

    val parser:MessageParser = MessageParser()
    val result = parser.parseAll(parser.message, s).get

    result match {
      case h:HashMap[String, PropertyValue] => h
      case _ => HashMap[String, PropertyValue]()
    }
  }
}

case class MessageParser() extends JavaTokenParsers {

  val fpt = floatingPointNumber
  val id =  ident
  val num = wholeNumber
  val dec = decimalNumber
  val str = stringLiteral
  val bool: Parser[String] = "[true|false]".r

//  def url: Parser[String] = id ^^ {case (idChannel:String) => idChannel } | (urlPrefix.+)~id ^^ {case (u:List[String])~(s:String) => u.foldLeft("") {case (v,c) => v+c }+s }
//  def urlPrefix : Parser[String] = id~"." ^^ {case (s:String)~_ => s+"." }

  def url:Parser[String] = repsep(id,".") ^^ {case (u:List[String]) => u.foldLeft("") {case (v,c) => if(v=="") v+c else v+"."+c } }

  def args: Parser[List[Term]] = "["~>repsep(exp,",")<~"]" ^^ { case exps => exps.foldLeft(List[Term]()){ (v,e) => v:::List(e) } }
  def exp: Parser[Term] =
    "true"          ^^ { case s => WrapScalaBoolean(s.toBoolean) } |
    "false"         ^^ { case s => WrapScalaBoolean(s.toBoolean) } |
    fpt             ^^ { case s => WrapScalaInt(s.toFloat.toInt) } |
    "\"@"~url~"\""  ^^ { case _~(s:String)~_ => new ChannelLocation(s) } |
    str             ^^ { case s => WrapScalaString(s.substring(1, s.size-1))} |
    dec             ^^ { case s => WrapScalaInt(s.toFloat.toInt) } |
    num             ^^ { case s => WrapScalaInt(s.toDouble.toInt) }

  def message:Parser[HashMap[String, PropertyValue]] = "{"~>repsep(property,",")<~"}" ^^ {
    case parsedProperties => {
      var result:HashMap[String, PropertyValue] = new HashMap()

      parsedProperties.foreach( {
        p:PropertyValue => p match {
          case ps:PropertySingleValue => result.put(ps.name, ps)
          case pl:PropertyListValue => result.put(pl.name, pl)
        }
      })
      result
    }
  }

  def property:Parser[PropertyValue] = "\"args\":"~>args ^^ {case values => PropertyListValue("args", values)} |
    "\"to\":"~>exp       ^^ {case value => PropertySingleValue("to", value)} |
    "\"from\":"~>exp      ^^ {case value => PropertySingleValue("from", value)} |
    "\"channel\":"~>exp   ^^ {case value => PropertySingleValue("channel", value)}

}

object Message {
  def parse(s:String):Message = {
    var message:Message = new Message()
    message.properties = MessageParser.parse(s)
    message
  }

  def parseArgs(s:String):List[Term] = {
    MessageParser.parseArgs(s)
  }

  def atomToMessage(from:String, to:String, a:Atom):String = {

    def patternsToString(pattern:List[Term]):String = pattern match {
      case (head:Term)::l if l!=Nil => MessageParser.termToString(head)+","+patternsToString(l)
      case (head:Term)::l if l==Nil => MessageParser.termToString(head)
      case Nil => ""
    }

    "{\"to\":\""+to+"\", \"from\":\""+from+"\", \"channel\":\""+a.relation.name+"\",\"args\":["+patternsToString(a.patterns)+"]}"
  }
}

class Message {

  var properties:HashMap[String, PropertyValue] = new HashMap[String, PropertyValue]()

  def to:Option[String] = properties.get("to") match {
    case Some(ps:PropertySingleValue) => Some(ps.toString)
    case None => None
  }
  def from:Option[String] = properties.get("from") match {
    case Some(ps:PropertySingleValue) => Some(ps.toString)
    case None => None
  }
  def channel:Option[String] = properties.get("channel") match {
    case Some(ps:PropertySingleValue) => Some(ps.toString)
    case None => None
  }
  def args:Option[PropertyListValue] = properties.get("args") match {
    case Some(pl:PropertyListValue) => Some(pl)
    case None => None
  }

  def atom:Option[Atom] = {

    (channel.get, args.get) match {
      case (sChanel:String, sArgs:PropertyListValue) => Some(Atom.apply(LocalRelation(sChanel), sArgs.value))
    }
  }
}
