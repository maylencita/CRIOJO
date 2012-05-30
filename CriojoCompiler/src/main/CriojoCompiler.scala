/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/10/12
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */

import collection.mutable.HashMap
import java.io.{BufferedWriter, FileWriter}
import util.parsing.combinator.JavaTokenParsers


class ProgramEnvironment {
  var mapOfServers:HashMap[String, ServerEnvironment] = HashMap()
}

class ServerEnvironment {
  var mapOfChams:HashMap[String, ChamEnvironment] = HashMap()
  var mapOfFirewalls:HashMap[String, FirewallEnvironment] = HashMap()
}

class ChamEnvironment {

  def getType():String = "cham"

  var rules:List[Any] = List()

  var mapOfVariables:HashMap[String, String] = HashMap()
  var mapOfInChannel:HashMap[String, String] = HashMap()
  var mapOfOutChannel:HashMap[String, String] = HashMap()
}

class FirewallEnvironment extends ChamEnvironment {

  override def getType():String = "firewall"

  var assignations:List[Any] = List()
  var mapOfObjects:HashMap[String, ChamEnvironment] = HashMap()
}



object CriojoCompiler {
  def main(args: Array[String]) {

    if(args.length < 1) {
      println("CriojoCompiler <input file> [output file]")
    }
    else {
      val inputProgram = scala.io.Source.fromFile(args(0)).mkString
      val s:String = new CriojoCompiler().parse(inputProgram)

      if(args.length > 1) {
        val fstream:FileWriter = new FileWriter(args(1));
        val out:BufferedWriter = new BufferedWriter(fstream);
        out.write(s);
        out.close();
      }
      else {
        println(s)
      }
    }
  }
}

class CriojoCompiler extends JavaTokenParsers {


  var env:ProgramEnvironment = new ProgramEnvironment()

  var currentChamEnvironment:ChamEnvironment = new ChamEnvironment()
  var currentFirewallEnvironment:FirewallEnvironment = new FirewallEnvironment()
  var currentServerEnvironment:ServerEnvironment = new ServerEnvironment()

  def parse(s:String):String = parseAll(program,s).get.toString

  val fpt = floatingPointNumber
  val id =  ident
  val num = wholeNumber
  val dec = decimalNumber
  val str = stringLiteral

  def program: Parser[Any] = rep(server) ^^ {
    case servers => ObjectToScala.mainProgram(servers)
  }

  def server: Parser[Any] = id~"{"~rep(cham | firewall)<~"}" ^^ {
    case idServer~_~chams => {
      env.mapOfServers.put(idServer, currentServerEnvironment)
      currentServerEnvironment = new ServerEnvironment()
      ObjectToScala.serverWithVariableDeclariation(idServer, chams, env.mapOfServers.get(idServer).get)
    }
  }

  def firewall: Parser[Any] = id~"("~repsep(channelOpening, ",")~"){"~repsep(criojoObject, ",")<~"}" ^^ {
    case idFirewall~_~assignations~_~children => {
      currentServerEnvironment.mapOfFirewalls.put(idFirewall, currentFirewallEnvironment)
      currentChamEnvironment = new FirewallEnvironment()
      currentServerEnvironment.mapOfFirewalls.get(idFirewall).get.assignations = assignations
      ObjectToScala.firewallWithVariableDeclariation(idFirewall, currentServerEnvironment.mapOfFirewalls.get(idFirewall).get)
    }
  }

  def criojoObject:Parser[Any] = firewall | cham
  
  def channelOpening: Parser[Any] = criojoId ^^ {
    case idChannel => ObjectToScala.channelOpening(idChannel.toString)
  }

  def cham: Parser[Any] = id~"{"~rep(rule)<~"}" ^^ {
    case idCham~_~rules => {
      currentServerEnvironment.mapOfChams.put(idCham, currentChamEnvironment)
      currentFirewallEnvironment.mapOfObjects.put(idCham, currentChamEnvironment)
      currentChamEnvironment = new ChamEnvironment()
      currentServerEnvironment.mapOfChams.get(idCham).get.rules = rules
      ObjectToScala.chamWithVariableDeclariation(idCham, currentServerEnvironment.mapOfChams.get(idCham).get)
    }
  }

  def rule: Parser[Any] = atoms~"->"~atoms ^^ {
    case atomsLeft~_~atomsRight => ObjectToScala.rule(atomsLeft, atomsRight)
  }

  def atoms: Parser[List[Any]] = repsep(atom, ",")

  def atom: Parser[Any] = criojoId~"("~exps~")" ^^ {
    case idAtom~_~expressions~_ => idAtom+"("+ObjectToScala.arguments(expressions)+")"
  }
  
  def exps: Parser[List[Any]] = repsep(exp, ",")
  def exp: Parser[Any] = fpt | str | dec | criojoId | num
  
  
  def criojoId = remoteChamdId | localChamdId | inChannelId | id ^^ { case idVar => {
      currentChamEnvironment.mapOfVariables.put(idVar, ":LocalRelation = new LocalRelation(\""+idVar+"\")")
      currentFirewallEnvironment.mapOfVariables.put(idVar, ":LocalRelation = new LocalRelation(\""+idVar+"\")")
      idVar
    }

  }

  def inChannelId: Parser[Any] = "@"~id ^^ { case _~atomName => {
    currentChamEnvironment.mapOfInChannel.put(atomName, ":InChannel = InChannel(\""+atomName+"\")")
    currentFirewallEnvironment.mapOfInChannel.put(atomName, ":InChannel = InChannel(\""+atomName+"\")")
    atomName
  }
  }
  def localChamdId: Parser[Any] = id~"."~id ^^ { case chamName~_~atomName => {
      currentChamEnvironment.mapOfOutChannel.put(chamName+"To"+atomName, ":OutChannel = OutChannel(\""+chamName+"."+atomName+"\")")
      currentFirewallEnvironment.mapOfOutChannel.put(chamName+"To"+atomName, ":OutChannel = OutChannel(\""+chamName+"."+atomName+"\")")
      "@"+chamName+"."+atomName
    }
  }
  def remoteChamdId: Parser[Any] = id~"."~id~"."~id ^^ { case serverName~_~chamName~"."~atomName => {
      currentChamEnvironment.mapOfOutChannel.put(chamName+"At"+serverName+"To"+atomName, ":OutChannel = OutChannel(\""+serverName+"."+chamName+"."+atomName+"\")")
      currentFirewallEnvironment.mapOfOutChannel.put(chamName+"At"+serverName+"To"+atomName, ":OutChannel = OutChannel(\""+serverName+"."+chamName+"."+atomName+"\")")
      "@"+serverName+"."+chamName+"."+atomName
    }
  }
}
