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

  override def skipWhitespace = false
  
  var currentChamEnvironment:ChamEnvironment = new ChamEnvironment()
  var currentFirewallEnvironment:FirewallEnvironment = new FirewallEnvironment()
  var currentServerEnvironment:ServerEnvironment = new ServerEnvironment()

  var treeOfNames:List[String] = List()

  def getName:String = treeOfNames.foldLeft(""){ case(v,c) => if (v != "") { v+"."+c } else { v+c } }

  def pushName(n:String) {
    treeOfNames = List(n) ::: treeOfNames
  }

  def popName() {
    treeOfNames = treeOfNames match {
      case Nil => Nil
      case a::l => l
    }
  }

  def parse(s:String):String = parseAll(program,s).get.toString

  val fpt = floatingPointNumber
  val id =  withSpace(ident)
  val num = wholeNumber
  val dec = decimalNumber
  val str = stringLiteral

  def spaces: Parser[Any] = rep(" " | "\t" | "\n") ^^ {
    case _ => ""
  }
  
  def withSpace[T](inputParser:Parser[T]): Parser[Any] = spaces~>inputParser<~spaces
  
  def program: Parser[Any] = withSpace(rep(server)) ^^ {
    case servers:List[Any] => ObjectToScala.mainProgram(servers)
  }

  def server: Parser[Any] = withSpace(id~"{"~withSpace(rep(cham | firewall))~"}") ^^ {
    case (idServer:String)~_~(chams:List[Any])~_ => {
      env.mapOfServers.put(idServer, currentServerEnvironment)
      currentServerEnvironment = new ServerEnvironment()
      ObjectToScala.serverWithVariableDeclariation(idServer, chams, env.mapOfServers.get(idServer).get)
    }
  }

  def firewall: Parser[Any] = withSpace(withSpace(id~"("~withSpace(repsep(channelOpening, ","))~")")~"{"~withSpace(rep(criojoObject))<~"}") ^^ {
    case (idFirewall:String)~_~(assignations:List[Any])~_~_~children => {
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

  def cham: Parser[Any] = withSpace(id~"{"~withSpace(rep(rule))<~"}") ^^ {
    case (idCham:String)~_~(rules:List[Any]) => {
      currentServerEnvironment.mapOfChams.put(idCham, currentChamEnvironment)
      currentFirewallEnvironment.mapOfObjects.put(idCham, currentChamEnvironment)
      currentChamEnvironment = new ChamEnvironment()
      currentServerEnvironment.mapOfChams.get(idCham).get.rules = rules
      ObjectToScala.chamWithVariableDeclariation(idCham, currentServerEnvironment.mapOfChams.get(idCham).get)
    }
  }

  def rule: Parser[Any] = withSpace(atomsLeft~"->"~atomsRight) ^^ {
    case (atomsLeft:List[Any])~_~(atomsRight:List[Any]) => ObjectToScala.rule(atomsLeft, atomsRight)
  }


  // FOR GENERIC ATOMS
  def atoms: Parser[List[Any]] = repsep(atom, ",")

  def atom: Parser[Any] = withSpace(atomId~"("~exps~")") ^^ {
    case idAtom~_~(expressions:List[Any])~_ => idAtom+"("+ObjectToScala.arguments(expressions)+")"
  }

  def exps: Parser[List[Any]] = repsep(exp, ",")
  def exp: Parser[Any] = fpt | str | dec | criojoId | num

  // ATOMS DEFINITION
  // FOR LEFT ATOMS
  def atomsLeft: Parser[List[Any]] = repsep(atomLeft, ",")

  def atomLeft: Parser[Any] = withSpace((inChannelId | atomId)~"("~expsLeft~")") ^^ {
    case idAtom~_~(expressions:List[Any])~_ => idAtom+"("+ObjectToScala.arguments(expressions)+")"
  }

  def expsLeft: Parser[List[Any]] = repsep(expLeft, ",")
  def expLeft: Parser[Any] = withSpace(fpt | str | dec | outChannelId | criojoId | num)

  // FOR RIGHT ATOMS
  def atomsRight: Parser[List[Any]] = repsep(atomRight, ",")

  def atomRight: Parser[Any] = withSpace((VarChannelChamdId | outChannelId | atomId)~"("~expsRight~")") ^^ {
    case idAtom~_~(expressions:List[Any])~_ => idAtom+"("+ObjectToScala.arguments(expressions)+")"
  } | nativeCode

  def expsRight: Parser[List[Any]] = repsep(expRight, ",")
  def expRight: Parser[Any] = withSpace(fpt | str | dec | inChannelId | outChannelId | varId | num)

  // END OF ATOMS DEFINITION

  def criojoId = id ^^ { case idVar:String => {
      if(!currentChamEnvironment.mapOfInChannel.contains(idVar) && !currentFirewallEnvironment.mapOfInChannel.contains(idVar)) {
        currentChamEnvironment.mapOfVariables.put(idVar, ":LocalRelation = new LocalRelation(\""+idVar+"\")")
        currentFirewallEnvironment.mapOfVariables.put(idVar, ":LocalRelation = new LocalRelation(\""+idVar+"\")")
      }
      idVar
    }
  }

  def atomId = id ^^ { case idVar:String => {
      if(!currentChamEnvironment.mapOfInChannel.contains(idVar) && !currentFirewallEnvironment.mapOfInChannel.contains(idVar)) {
        currentChamEnvironment.mapOfVariables.put(idVar, ":LocalRelation = new LocalRelation(\""+idVar+"\")")
        currentFirewallEnvironment.mapOfVariables.put(idVar, ":LocalRelation = new LocalRelation(\""+idVar+"\")")
      }
      idVar
    }
  }

  def varId = id ^^ { case idVar:String => {
      if(!currentChamEnvironment.mapOfInChannel.contains(idVar) && !currentFirewallEnvironment.mapOfInChannel.contains(idVar)) {
        currentChamEnvironment.mapOfVariables.put(idVar, " = VarScalaString(\""+idVar+"\")")
        currentFirewallEnvironment.mapOfVariables.put(idVar, " = VarScalaString(\""+idVar+"\")")
      }
      idVar
    }
  }

  def inChannelId: Parser[Any] = "@"~id ^^ { case _~(atomName:String) => {

      if(currentChamEnvironment.mapOfVarChannel.contains(atomName)) {
        currentChamEnvironment.mapOfVarChannel.remove(atomName)
      }

      currentChamEnvironment.mapOfInChannel.put(atomName, ":Channel = new Channel(parentName+\""+atomName+"\")")
      currentFirewallEnvironment.mapOfInChannel.put(atomName, ":Channel = new Channel(parentName+\""+atomName+"\")")
      atomName
    }
  }

  def inChannelFirewallAssignationId: Parser[Any] = id~".@"~id ^^ { case chamName~_~atomName => {
      chamName+"."+atomName
    }
  }

  def outChannelId: Parser[Any] = VarChannelChamdId | remoteChannelId

  def VarChannelChamdId: Parser[Any] = "@"~id ^^ { case _~(atomName:String) => {

      if(!currentChamEnvironment.mapOfInChannel.contains(atomName)) {

        currentChamEnvironment.mapOfVarChannel.put(atomName, ":VarChannel = VarChannel(\""+atomName+"\")")
        currentFirewallEnvironment.mapOfVarChannel.put(atomName, ":VarChannel = VarChannel(\""+atomName+"\")")

      }

      atomName
    }
  }

  def remoteChannelId: Parser[Any] = addressPrefix~"@"~id ^^ { case prefixe~_~(atomName:String) => {
      currentChamEnvironment.mapOfOutChannel.put((prefixe+atomName).replace(".","To"), ":OutChannel = new OutChannel(\""+prefixe+atomName+"\")")
      currentFirewallEnvironment.mapOfOutChannel.put((prefixe+atomName).replace(".","To"), ":OutChannel = new OutChannel(\""+prefixe+atomName+"\")")
      (prefixe+atomName).replace(".","To")
    }
  }

  def addressPrefix: Parser[Any] = rep(prefix) ^^ {case idPrefs => idPrefs.foldLeft(""){ case (v,c) => v+c } }
  def prefix :Parser[Any] = id~"." ^^ { case prefix~_ => prefix+"." }

  def nativeCode: Parser[Any] = withSpace("("~>exps<~")") ~ withSpace(scalaCode) ~ withSpace("("~>exps<~")") ^^ {

    case (args1:List[Any])~code~(args2:List[Any]) => {
      val arguments1:String = args1.foldLeft(""){ case(v,c) => if (v != "") { v+"::"+c } else { v+c } }
      val arguments2:String = args2.foldLeft(""){ case(v,c) => if (v != "") { v+","+c } else { v+c } }
      "NativeRel { case ("+arguments1+"::Nil) => "+code+"; case _ => }("+arguments2+")"
    }
  }

  def scalaCode: Parser[Any] = "{}" ^^ { case _ => "{}" } | "{"~rep(normalToken|scalaCode)~"}" ^^ {
    case _~l~_ => "{"+l.foldLeft("") {case (v,c) => v+c}+"}"
  }

  def normalToken: Parser[Any] = "[^\\{|\\}]+".r ^^ {
    case l => l.foldLeft("") {case (v,c) => v+c}
  }
}
