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

  def mainProgram(webserver:Option[String], servers:List[Any]):String = {

    val webserverString:String = webserver match {
      case None => ""
      case Some(s:String) => s
    }

    webserverString+"\n"+servers.foldLeft(""){case(v,s) => v+s}
  }

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
    "import fr.emn.criojo.ext.expression.Relation.{ChannelLocation, VarChannel}\n"+
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

  def webServer(path:String):String = {
    "//fileweb "+path+"\n" +
      "package application;\n" +
      "\n"+
      "import org.eclipse.jetty.server.Server;\n" +
      "import org.eclipse.jetty.webapp.WebAppContext;\n" +
      "import fr.emn.criojo.network.BusConnector;\n" +
      "import fr.emn.criojo.network.BusConnectorException;\n" +
      "import fr.emn.criojo.network.BusConnectorFactory;\n" +
      "import fr.emn.criojo.network.BusConnectorFactoryException;\n" +
      "import fr.emn.criojo.network.BusConnectorLocalHornetQWithManagementFactory;"+
      "\n\n"+
      "public class AppLauncher {\n "+
      "public static BusConnector startHornetQWithManagement()\n" +
      "\t\tthrows BusConnectorFactoryException, BusConnectorException {\n" +
      "\t\tBusConnectorFactory factory = new BusConnectorLocalHornetQWithManagementFactory();\n" +
      "\t\tBusConnector connector = factory.createConnector(\"5445:debug\");\n" +
      "\t\treturn connector;\n" +
      "\t}\n" +
      "\tprivate static Server startJetty() throws Exception {\n" +
      "\t\tServer server = new Server(8080);\n" +
      "\t\tWebAppContext context = new WebAppContext();\n" +
      "\t\tcontext.setDescriptor(\"/WEB-INF/web.xml\");\n" +
      "\t\tcontext.setResourceBase(System.getProperties().getProperty(\"user.dir\")\n" +
      "\t\t    + \"/src/main/java/webapp\");\n" +
      "\t\tcontext.setContextPath(\"/message-sender\");\n" +
      "\n" +
      "\t\tserver.setHandler(context);\n" +
      "\t\tserver.start();\n" +
      "\n" +
      "\t\treturn server;\n" +
      "\t}"+
      "\tpublic static void main(String[] args) {\n" +
      "\t\tBusConnector connector = null;\n" +
      "\t\tServer jetty = null;\n" +
      "\t\ttry {\n" +
      "\t\t\tconnector = startHornetQWithManagement();\n" +
      "\t\t\tjetty = startJetty();\n" +
      "\t\t\tSystem.out.println(\"Open http://localhost:8080/message-sender/\"\n" +
      "\t\t\t    + \" in your browser ...\");\n" +
      "\t\t\twhile(true) {Thread.sleep(500);}\n" +
      "\t\t} catch (BusConnectorFactoryException e) {\n" +
      "\t\t\te.printStackTrace();\n" +
      "\t\t} catch (BusConnectorException e) {\n" +
      "\t\t\te.printStackTrace();\n" +
      "\t\t} catch (Exception e) {\n" +
      "\t\t\te.printStackTrace();\n" +
      "\t\t} finally {\n" +
      "\t\t\tif (connector != null) {\n" +
      "\t\t\t\tconnector.disconnect();\n" +
      "\t\t\t}\n" +
      "\t\t\tif (jetty != null) {\n" +
      "\t\t\t\ttry {\n" +
      "\t\t\t\t\tjetty.stop();\n" +
      "\t\t\t\t} catch (Exception e) {\n" +
      "\t\t\t\t\te.printStackTrace();\n" +
      "\t\t\t\t}\n" +
      "\t\t\t}\n" +
      "\t\t}\n" +
      "\t}\n"+
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
