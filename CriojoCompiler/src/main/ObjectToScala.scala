/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/21/12
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */

object ObjectToScala {

  def serverVariableHeader(env:ServerEnvironment):String = {
    env.mapOfChams.foldLeft("")     { case(v,(k,p)) => v+"\t\tvar "+ k + ":ActorCham  = null\n"}
    env.mapOfFirewalls.foldLeft("") { case(v,(k,p)) => v+"\t\tvar "+ k + ":Firewall = null \n"}
  }

  def chamVariableHeader(env:ChamEnvironment):String = {
    env.mapOfVariables.foldLeft("") { case(v,(k,p)) => v+"\t\t\t\t"+variable(k,p)+"\n"}+
    env.mapOfOutChannel.foldLeft(""){ case(v,(k,p)) => v+"\t\t\t\tvar "+k+p+"\n"}+
    env.mapOfInChannel.foldLeft("") { case(v,(k,p)) => v+"\t\t\t\tvar "+k+p+"\n"}+
    env.mapOfVarChannel.foldLeft("") { case(v,(k,p)) => v+"\t\t\t\tvar "+k+p+"\n"}
  }

  def firewallVariableHeader(env:FirewallEnvironment):String = {

    env.mapOfObjects.foldLeft(""){ case(v,(k,p)) =>
      p.getType() match {
        case "cham"     => v+"\t\tvar "+ k + ":ActorCham = null\n"
        case "firewall" => v+"\t\tvar "+ k + ":Firewall = null\n"
      }
    }

    env.mapOfVariables.foldLeft("") { case(v,(k,p)) => v+"\t\t\t"+variable(k,p)+"\n"}+
    env.mapOfOutChannel.foldLeft(""){ case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfInChannel.foldLeft("") { case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfVarChannel.foldLeft("") { case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}
  }

  def mainProgram(servers:List[Any]):String = servers.foldLeft(""){case(v,s) => v+s}

  def server(name:String, chams:List[Any], env:ServerEnvironment):String = {

    val headerString:String = env.header match {
      case None => ""
      case Some(s:String) => s.substring(1, s.length()-1)
    }

    val footerString:String = env.footer match {
      case None => ""
      case Some(s:String) => s.substring(1, s.length()-1)
    }

    "//file "+name+"\n" +
    "package application\n" +
    "\n"+
    "import fr.emn.criojo.core.Converters._\n"+
    "import fr.emn.criojo.ext.expression.Relation.constructor.{OutChannel, LocalRelation, Channel}\n" +
    "import fr.emn.criojo.network.{ActorChamDebug, ActorCham, Firewall, BusManager}\n" +
    "import fr.emn.criojo.ext.expression.Relation.VarChannel\n"+
    "import fr.emn.criojo.ext.expression.ScalaString.VarScalaString\n"+
    "import fr.emn.criojo.ext.expression.ScalaInt.VarScalaInt\n"+
    "import fr.emn.criojo.ext.expression.ScalaBoolean.VarScalaBoolean\n"+
    "\n\n"+
    "object criojoMain {\n " +
    "\tvar listOfNames:List[String] = List()\n"+
    "\tdef pushToNames(name:String) { listOfNames = List(name):::listOfNames }\n"+
    "\tdef popNames() {listOfNames match {case Nil => listOfNames=Nil; case a::l => listOfNames=l}}\n"+
    "\tdef getNames():String = listOfNames.foldLeft(\"\"){case (v,c) => c+\".\"+v}\n"+
    "\n\n"+
    "\tdef main(args:Array[String]) {\n" +
    "\t\tpushToNames(\""+name+"\")\n"+
    "\t\tvar parentName:String = getNames()\n\n"+
    "\t\tval busManager:BusManager = new BusManager()\n\n"+
    "\t\t"+headerString+"\n\n"+
      chams.foldLeft(""){case(v,c) => v+c}+"\n\n"+
    "\n\t\t"+footerString+"\n\n"+
    "\n\t\twhile(true){" +
    "\n\t\t\tThread.sleep(500)"+
    "\n\t\t}"+
    "\n\t\tpopNames()\n"+
    "\n\t}\n"+
    "}\n"
  }

  def firewall(name:String, env:FirewallEnvironment):String = {
    "\t\tval "+name+" = new Firewall {\n"+
    "\t\t\tpushToNames(\""+name+"\")\n"+
    "\t\t\tvar parentName:String = getNames()\n\n"+
    "\t\t\tfilterRules=List("+
    env.assignations.foldLeft("") {
      case(v,c) => if (v != "") { v+","+c } else { v+c }
    }+
    ")\n"+
    env.mapOfObjects.foldLeft(""){
      case(v,(k,p)) => p match {
        case f:FirewallEnvironment => "\t\t\tvar "+k+":Firewall = null\n"+firewall (k,f)+"\n"+v
        case c:ChamEnvironment     => "\t\t\tvar "+k+":ActorCham = null\n"+cham(k,c)+"\n"+v
      }
    }+
    "\n\t\t\tchildren = List("+
    env.mapOfObjects.foldLeft(""){ case(v,(k,p)) => if (v != "") { v+","+k } else { v+k } }+
    ")"+
    "\n\t\t\tpopNames()\n"+
    "\n\t\t}\n"+
    "\t\t"+name+".sendFilterRules()\n"
  }

  def cham(name:String, env:ChamEnvironment):String = {

    val headerString:String = env.header match {
      case None => ""
      case Some(s:String) => s.substring(1, s.length()-1)
    }

    "\t\t\tval "+name+" = new ActorCham(parentName+\""+name+"\", busManager) with ActorChamDebug {\n"+
    "\t\t\t\tpushToNames(\""+name+"\")\n"+
    "\t\t\t\tvar parentName:String = getNames()\n\n"+
    "\t\t\t\t"+headerString+"\n\n"+
    chamVariableHeader(env)+"\t\t\t\trules(\n\t"+env.rules.foldLeft("\t\t\t\t") {
      case(v,c) => if (v != "\t\t\t\t") { v+"\t\t\t\t\t,"+c } else { v+c }
    }+
    "\t\t\t\t)"+
    "\n"+
    "\n\t\t\t\tpopNames()\n"+
    "\t\t\t}\n"
  }

  def channelOpening(idChannel:String):String = {
    "\""+idChannel+"\""
  }

  def rule(atomsLeft:List[Any], atomsRight:List[Any]) = {

    val leftBody = atomsLeft.foldLeft(""){ case(v1,c1) => if (v1 != "") { v1+"&"+c1 } else { v1+c1 } }
    val rightBody = atomsRight.foldLeft(""){ case(v2,c2) => if (v2 != "") { v2+"&"+c2 } else { v2+c2 } }
    "("+leftBody+") --> ("+rightBody+")\n"
  }

  def arguments(args:List[Any]) = {
    args.foldLeft(""){ case(v,c) => if (v != "") { v+","+c } else { v+c } }
  }

  def variable(varName:String, varType:String):String = {
    "var "+varName+":"+(varType match {
      case "int" => "VarScalaInt"
      case "string" => "VarScalaString"
      case "boolean" => "VarScalaBoolean"
      case "relation" => "LocalRelation"
    })+" = "+(varType match {
      case "int" => "VarScalaInt(\""+varName+"\")"
      case "string" => "VarScalaString(\""+varName+"\")"
      case "boolean" => "VarScalaBoolean(\""+varName+"\")"
      case "relation" => "new LocalRelation(\""+varName+"\")"
    })
  }

}
