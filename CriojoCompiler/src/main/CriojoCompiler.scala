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
  var mapOfVarChannel:HashMap[String, String] = HashMap()
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

  def firewall: Parser[Any] = id~"("~repsep(channelOpening, ",")~")"~"{"~rep(criojoObject)<~"}" ^^ {
    case idFirewall~_~assignations~_~_~children => {
      currentServerEnvironment.mapOfFirewalls.put(idFirewall, currentFirewallEnvironment)
      currentChamEnvironment = new FirewallEnvironment()
      currentServerEnvironment.mapOfFirewalls.get(idFirewall).get.assignations = assignations
      ObjectToScala.firewallWithVariableDeclariation(idFirewall, currentServerEnvironment.mapOfFirewalls.get(idFirewall).get)
    }
  }

  def criojoObject:Parser[Any] = firewall | cham
  
  def channelOpening: Parser[Any] = inChannelFirewallAssignationId ^^ {
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

  def rule: Parser[Any] = atomsLeft~"->"~atomsRight ^^ {
    case atomsLeft~_~atomsRight => ObjectToScala.rule(atomsLeft, atomsRight)
  }


  // FOR GENERIC ATOMS
  def atoms: Parser[List[Any]] = repsep(atom, ",")

  def atom: Parser[Any] = atomId~"("~exps~")" ^^ {
    case idAtom~_~expressions~_ => idAtom+"("+ObjectToScala.arguments(expressions)+")"
  }

  def exps: Parser[List[Any]] = repsep(exp, ",")
  def exp: Parser[Any] = fpt | str | dec | criojoId | num

  // ATOMS DEFINITION
  // FOR LEFT ATOMS
  def atomsLeft: Parser[List[Any]] = repsep(atomLeft, ",")

  def atomLeft: Parser[Any] = (inChannelId | atomId)~"("~expsLeft~")" ^^ {
    case idAtom~_~expressions~_ => idAtom+"("+ObjectToScala.arguments(expressions)+")"
  }

  def expsLeft: Parser[List[Any]] = repsep(expLeft, ",")
  def expLeft: Parser[Any] = fpt | str | dec | outChannelId | criojoId | num

  // FOR RIGHT ATOMS
  def atomsRight: Parser[List[Any]] = repsep(atomRight, ",")

  def atomRight: Parser[Any] = (VarChannelChamdId | outChannelId | atomId)~"("~expsRight~")" ^^ {
    case idAtom~_~expressions~_ => idAtom+"("+ObjectToScala.arguments(expressions)+")"
  }

  def expsRight: Parser[List[Any]] = repsep(expRight, ",")
  def expRight: Parser[Any] = fpt | str | dec | inChannelId | outChannelId | varId | num

  // END OF ATOMS DEFINITION

  def criojoId = id ^^ { case idVar => {
      if(!currentChamEnvironment.mapOfInChannel.contains(idVar) && !currentFirewallEnvironment.mapOfInChannel.contains(idVar)) {
        currentChamEnvironment.mapOfVariables.put(idVar, ":LocalRelation = new LocalRelation(\""+idVar+"\")")
        currentFirewallEnvironment.mapOfVariables.put(idVar, ":LocalRelation = new LocalRelation(\""+idVar+"\")")
      }
      idVar
    }
  }

  def atomId = id ^^ { case idVar => {
      if(!currentChamEnvironment.mapOfInChannel.contains(idVar) && !currentFirewallEnvironment.mapOfInChannel.contains(idVar)) {
        currentChamEnvironment.mapOfVariables.put(idVar, ":LocalRelation = new LocalRelation(\""+idVar+"\")")
        currentFirewallEnvironment.mapOfVariables.put(idVar, ":LocalRelation = new LocalRelation(\""+idVar+"\")")
      }
      idVar
    }
  }

  def varId = id ^^ { case idVar => {
      if(!currentChamEnvironment.mapOfInChannel.contains(idVar) && !currentFirewallEnvironment.mapOfInChannel.contains(idVar)) {
        currentChamEnvironment.mapOfVariables.put(idVar, " = VarScalaString(\""+idVar+"\")")
        currentFirewallEnvironment.mapOfVariables.put(idVar, " = VarScalaString(\""+idVar+"\")")
      }
      idVar
    }
  }

  def inChannelId: Parser[Any] = "@"~id ^^ { case _~atomName => {

      if(currentChamEnvironment.mapOfVarChannel.contains(atomName)) {
        currentChamEnvironment.mapOfVarChannel.remove(atomName)
      }

      currentChamEnvironment.mapOfInChannel.put(atomName, ":Channel = new Channel(\""+atomName+"\")")
      currentFirewallEnvironment.mapOfInChannel.put(atomName, ":Channel = new Channel(\""+atomName+"\")")
      atomName
    }
  }

  def inChannelFirewallAssignationId: Parser[Any] = id~".@"~id ^^ { case chamName~_~atomName => {
      chamName+"."+atomName
    }
  }

  def outChannelId: Parser[Any] = VarChannelChamdId | remoteChannelChamdId | remoteServerChannelChamdId

  def VarChannelChamdId: Parser[Any] = "@"~id ^^ { case _~atomName => {

      if(!currentChamEnvironment.mapOfInChannel.contains(atomName)) {

        currentChamEnvironment.mapOfVarChannel.put(atomName, ":VarChannel = VarChannel(\""+atomName+"\")")
        currentFirewallEnvironment.mapOfVarChannel.put(atomName, ":VarChannel = VarChannel(\""+atomName+"\")")

      }

      atomName
    }
  }

  def remoteChannelChamdId: Parser[Any] = id~".@"~id ^^ { case chamName~_~atomName => {
      currentChamEnvironment.mapOfOutChannel.put(chamName+"To"+atomName, ":OutChannel = new OutChannel(\""+chamName+"."+atomName+"\")")
      currentFirewallEnvironment.mapOfOutChannel.put(chamName+"To"+atomName, ":OutChannel = new OutChannel(\""+chamName+"."+atomName+"\")")
      chamName+"To"+atomName
    }
  }

  def remoteServerChannelChamdId: Parser[Any] = id~"."~id~".@"~id ^^ { case serverName~_~chamName~_~atomName => {
      currentChamEnvironment.mapOfOutChannel.put(chamName+"At"+serverName+"To"+atomName, ":OutChannel = new OutChannel(\""+serverName+"."+chamName+"."+atomName+"\")")
      currentFirewallEnvironment.mapOfOutChannel.put(chamName+"At"+serverName+"To"+atomName, ":OutChannel = new OutChannel(\""+serverName+"."+chamName+"."+atomName+"\")")
      chamName+"At"+serverName+"To"+atomName
    }
  }
}
