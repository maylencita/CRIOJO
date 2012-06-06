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

  var header:Option[String] = None
  var footer:Option[String] = None

  var mapOfChams:HashMap[String, ChamEnvironment] = HashMap()
  var mapOfFirewalls:HashMap[String, FirewallEnvironment] = HashMap()
}

class ChamEnvironment {

  def getType():String = "cham"

  var rules:List[Any] = List()

  var header:Option[String] = None

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

  // PROGRAM :=  (SERVER)*
  def program: Parser[Any] = withSpace(rep(server)) ^^ {
    case servers:List[Any] => ObjectToScala.mainProgram(servers)
  }

  // SERVER := ID (CHAM | FIREWALL)*
  def server: Parser[Any] = withSpace(id~"{"~(withSpace(scalaCode).?)~withSpace(rep(cham | firewall))~(withSpace(scalaCode).?)~"}") ^^ {
    case (idServer:String)~_~(header:Option[String])~(chams:List[Any])~(footer:Option[String])~_ => {
      currentServerEnvironment.header = header
      currentServerEnvironment.footer = footer
      env.mapOfServers.put(idServer, currentServerEnvironment)
      currentServerEnvironment = new ServerEnvironment()
      ObjectToScala.server(idServer, chams, env.mapOfServers.get(idServer).get)
    }
  }

  // FIREWALL := ID (CHANNEL)* (CRIOJO_OBJECT)*
  def firewall: Parser[Any] = withSpace(withSpace(id~"("~withSpace(repsep(channelOpening, ","))~")")~"{"~withSpace(rep(criojoObject))<~"}") ^^ {
    case (idFirewall:String)~_~(assignations:List[Any])~_~_~children => {
      currentServerEnvironment.mapOfFirewalls.put(idFirewall, currentFirewallEnvironment)
      currentChamEnvironment = new FirewallEnvironment()
      currentServerEnvironment.mapOfFirewalls.get(idFirewall).get.assignations = assignations
      ObjectToScala.firewall(idFirewall, currentServerEnvironment.mapOfFirewalls.get(idFirewall).get)
    }
  }

  // CRIOJO_OBJECT := (FIREWALL | CHAM)*
  def criojoObject:Parser[Any] = firewall | cham

  // CHANNEL := ID
  def channelOpening: Parser[Any] = inChannelFirewallAssignationId ^^ {
    case idChannel => ObjectToScala.channelOpening(idChannel.toString)
  }

  // CHAM := ID (SCALA_CODE)? (RULE)*
  def cham: Parser[Any] = withSpace(id~"{"~(withSpace(declarations).?)~(withSpace(scalaCode).?)~withSpace(rep(rule))<~"}") ^^ {
    case (idCham:String)~_~(scalaDeclaration:Option[String])~(scalaHeader:Option[String])~(rules:List[Any]) => {
      currentServerEnvironment.mapOfChams.put(idCham, currentChamEnvironment)
      currentChamEnvironment.header = scalaHeader
      currentFirewallEnvironment.mapOfObjects.put(idCham, currentChamEnvironment)
      currentChamEnvironment = new ChamEnvironment()
      currentServerEnvironment.mapOfChams.get(idCham).get.rules = rules
      ObjectToScala.cham(idCham, currentServerEnvironment.mapOfChams.get(idCham).get)
    }
  }

  // RULE := (ATOM_LEFT)* (ATOM_RIGHT)*
  def rule: Parser[Any] = withSpace(atomsLeft~"->"~atomsRight) ^^ {
    case (atomsLeft:List[Any])~_~(atomsRight:List[Any]) => ObjectToScala.rule(atomsLeft, atomsRight)
  }


  // FOR GENERIC ATOMS

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
  } | withSpace(nativeCode)

  def expsRight: Parser[List[Any]] = repsep(expRight, ",")
  def expRight: Parser[Any] = withSpace(fpt | str | dec | inChannelId | outChannelId | varId | num)

  // END OF ATOMS DEFINITION

  def criojoId = id ^^ { case idVar:String => {
    if(     !currentChamEnvironment.mapOfInChannel.contains(idVar) && !currentFirewallEnvironment.mapOfInChannel.contains(idVar)
         && !currentChamEnvironment.mapOfVariables.contains(idVar) && !currentFirewallEnvironment.mapOfVariables.contains(idVar)
      ) {
        currentChamEnvironment.mapOfVariables.put(idVar, "relation")
        currentFirewallEnvironment.mapOfVariables.put(idVar, "relation")
      }
      idVar
    }
  }

  def atomId = id ^^ { case idVar:String => {
      if( !currentChamEnvironment.mapOfInChannel.contains(idVar) && !currentFirewallEnvironment.mapOfInChannel.contains(idVar)
        && !currentChamEnvironment.mapOfVariables.contains(idVar) && !currentFirewallEnvironment.mapOfVariables.contains(idVar)
      ) {
        currentChamEnvironment.mapOfVariables.put(idVar, "relation")
        currentFirewallEnvironment.mapOfVariables.put(idVar, "relation")
      }
      idVar
    }
  }

  def varId = id ^^ { case idVar:String => {
      if( !currentChamEnvironment.mapOfInChannel.contains(idVar) && !currentFirewallEnvironment.mapOfInChannel.contains(idVar)
       && !currentChamEnvironment.mapOfVariables.contains(idVar) && !currentFirewallEnvironment.mapOfVariables.contains(idVar)
      ) {
        currentChamEnvironment.mapOfVariables.put(idVar, "string")
        currentFirewallEnvironment.mapOfVariables.put(idVar, "string")
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

  def declarations: Parser[Any] = "["~repsep(declaration,",")~"]" ^^ { case _ => ""}
  def declaration: Parser[Any] = id~":"~id ^^ {
    case (varName:String)~_~(varType:String) => {
      currentChamEnvironment.mapOfVariables.put(varName, varType)
      currentFirewallEnvironment.mapOfVariables.put(varName, varType)
    }
    ""
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
