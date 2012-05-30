/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/21/12
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */

object ObjectToScala {

  def serverVariableHeader(env:ServerEnvironment):String = {
    env.mapOfChams.foldLeft(""){ case(v,(k,p)) => v+"\t\tvar "+ k + ":Cham \n"}
    env.mapOfFirewalls.foldLeft(""){ case(v,(k,p)) => v+"\t\tvar "+ k + ":Firewall \n"}
  }

  def chamVariableHeader(env:ChamEnvironment):String = {
    env.mapOfVariables.foldLeft(""){ case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfOutChannel.foldLeft(""){ case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfInChannel.foldLeft(""){ case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}
  }

  def firewallVariableHeader(env:FirewallEnvironment):String = {

    env.mapOfObjects.foldLeft(""){ case(v,(k,p)) =>
      p.getType() match {
        case "cham" => v+"\t\tvar "+ k + ":Cham \n"
        case "firewall" => v+"\t\tvar "+ k + ":Firewall \n"
      }
    }

    env.mapOfVariables.foldLeft(""){ case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfOutChannel.foldLeft(""){ case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}+
    env.mapOfInChannel.foldLeft(""){ case(v,(k,p)) => v+"\t\t\tvar "+k+p+"\n"}
  }

  def mainProgram(servers:List[Any]):String = servers.foldLeft(""){case(v,s) => v+s}

  def serverWithVariableDeclariation(name:String, chams:List[Any], env:ServerEnvironment):String = {
    "//file "+name+".criojo\n" +
    "class criojoMain {\n " +
    "\tdef main() {\n" +
    serverVariableHeader(env)+server(name, chams)+"\n\t}\n"+
    "}\n"
  }

  def server(name:String, chams:List[Any]):String = chams.foldLeft(""){case(v,c) => v+c}+"\n"

  def firewallWithVariableDeclariation(name:String, env:FirewallEnvironment):String = {
    "\t\t"+name+" = new Firewall {\n"+
    firewallVariableHeader(env)+"\t\t\t" +
    "\n\t"+
    env.assignations.foldLeft("\t\t\t") {
      case(v,c) => if (v != "\t\t\t") { v+"\t\t\t\t,"+c } else { v+c }
    }+
    env.mapOfObjects.foldLeft(""){ case(v,(k,p)) => p match {
      case f:FirewallEnvironment => "\t\t\tvar "+k+":FirewallEnvironment\n"+firewallWithVariableDeclariation (k,f)+"\n"
      case c:ChamEnvironment => "\t\t\tvar "+k+":ChamEnvironment\n"+chamWithVariableDeclariation(k,c)+"\n"
    }}+
    "\n\t\t}\n"
  }

  def chamWithVariableDeclariation(name:String, env:ChamEnvironment):String = {
    "\t\t"+name+" = new Cham with IntegerCham with DebugCham {\n"+chamVariableHeader(env)+"\t\t\trules(\n\t"+env.rules.foldLeft("\t\t\t") {
      case(v,c) => if (v != "\t\t\t") { v+"\t\t\t\t,"+c } else { v+c }
    }+"\t\t\t)\n\t\t}\n"
  }

  def channelOpening(idChannel:String):String = {
    "\""+idChannel+"\""
  }

  def cham(name:String, rules:List[Any]):String = {
    "\t\t"+name+" = new Cham with IntegerCham with DebugCham {\n\t\t\trules(\n\t"+rules.foldLeft("\t\t\t") {
      case(v,c) => if (v != "\t\t\t") { v+"\t\t\t\t,"+c } else { v+c }
    }+"\t\t\t)\n\t\t}\n"
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
