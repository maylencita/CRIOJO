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
    env.mapOfVariables.foldLeft("") { case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfOutChannel.foldLeft(""){ case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfInChannel.foldLeft("") { case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfVarChannel.foldLeft("") { case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}
  }

  def firewallVariableHeader(env:FirewallEnvironment):String = {

    env.mapOfObjects.foldLeft(""){ case(v,(k,p)) =>
      p.getType() match {
        case "cham"     => v+"\t\tvar "+ k + ":ActorCham = null\n"
        case "firewall" => v+"\t\tvar "+ k + ":Firewall = null\n"
      }
    }

    env.mapOfVariables.foldLeft("") { case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfOutChannel.foldLeft(""){ case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfInChannel.foldLeft("") { case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfVarChannel.foldLeft("") { case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}
  }

  def mainProgram(servers:List[Any]):String = servers.foldLeft(""){case(v,s) => v+s}

  def serverWithVariableDeclariation(name:String, chams:List[Any], env:ServerEnvironment):String = {
    "//file "+name+"\n" +
    "package application\n" +
    "\n"+
    "import fr.emn.criojo.core.Converters._\n"+
    "import fr.emn.criojo.ext.expression.Relation.constructor.{OutChannel, LocalRelation, Channel}\n" +
    "import fr.emn.criojo.network.{ActorChamDebug, ActorCham, Firewall, BusManager}\n" +
    "import fr.emn.criojo.ext.expression.Relation.VarChannel\n"+
    "import fr.emn.criojo.ext.expression.ScalaString.VarScalaString\n"+
    "\n\n"+
    "object criojoMain {\n " +
    "\tdef main(args:Array[String]) {\n" +
    "\t\tval busManager:BusManager = new BusManager()\n"+
    serverVariableHeader(env)+server(name, chams)+"\n\t}\n"+
    "}\n"
  }

  def server(name:String, chams:List[Any]):String = chams.foldLeft(""){case(v,c) => v+c}+"\n"

  def firewallWithVariableDeclariation(name:String, env:FirewallEnvironment):String = {
    "\t\t"+name+" = new Firewall {\n"+
    "\n\t\t\tfilterRules=List("+
    env.assignations.foldLeft("") {
      case(v,c) => if (v != "") { v+","+c } else { v+c }
    }+
    ")\n"+
    env.mapOfObjects.foldLeft(""){
      case(v,(k,p)) => p match {
        case f:FirewallEnvironment => "\t\t\tvar "+k+":Firewall = null\n"+firewallWithVariableDeclariation (k,f)+"\n"+v
        case c:ChamEnvironment     => "\t\t\tvar "+k+":ActorCham = null\n"+chamWithVariableDeclariation(k,c)+"\n"+v
      }
    }+
    "\n\t\t\tchildren = List("+
    env.mapOfObjects.foldLeft(""){ case(v,(k,p)) => if (v != "") { v+","+k } else { v+k } }+
    ")"+
    "\n\t\t}\n"+
    "\t\t"+name+".sendFilterRules()\n"
  }

  def chamWithVariableDeclariation(name:String, env:ChamEnvironment):String = {
    "\t\t\t"+name+" = new ActorCham(\""+name+"\", busManager) with ActorChamDebug {\n"+
    chamVariableHeader(env)+"\t\t\trules(\n\t"+env.rules.foldLeft("\t\t\t") {
      case(v,c) => if (v != "\t\t\t") { v+"\t\t\t\t,"+c } else { v+c }
    }+
    "\t\t\t)"+
    "\n"+
    "\t\t}\n"
  }

  def channelOpening(idChannel:String):String = {
    "\""+idChannel+"\""
  }

  def cham(name:String, rules:List[Any]):String = {
    "\t\t\t"+name+" = new ActorCham(\\\"\"+name+\"\\\", busManager) with ActorChamDebug {\n"+
    "\t\t\trules(\n\t"+
    rules.foldLeft("\t\t\t") {
      case(v,c) => if (v != "\t\t\t") { v+"\t\t\t\t,"+c } else { v+c }
    }+
    "\t\t\t)"+
    "\n\t\t}\n"
  }

  def rule(atomsLeft:List[Any], atomsRight:List[Any]) = {

    val leftBody = atomsLeft.foldLeft(""){ case(v1,c1) => if (v1 != "") { v1+","+c1 } else { v1+c1 } }
    val rightBody = atomsRight.foldLeft(""){ case(v2,c2) => if (v2 != "") { v2+","+c2 } else { v2+c2 } }
    "("+leftBody+") --> ("+rightBody+")\n"
  }

  def arguments(args:List[Any]) = {
    args.foldLeft(""){ case(v,c) => if (v != "") { v+","+c } else { v+c } }
  }

}
