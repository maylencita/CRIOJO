package fr.emn.criojo.util

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 21, 2010
 * Time: 11:37:41 AM
 * To change this template use File | Settings | File Templates.
 */

//import fr.emn.criojo.util.Logger

import fr.emn.criojo.virtualmachine._
import javax.ws.rs._
import javax.ws.rs.core._
     
class Configurator(var URIInfo:UriInfo){

  Logger.logLevel = Logger.DEBUG

  if (VirtualMachineConfigurator.url == null)
    VirtualMachineConfigurator.url = URIInfo.getBaseUri()//.resolve("relation")

  Logger.log(this.getClass, "init", "Virtual machine URL: " + VirtualMachineConfigurator.url)

//  if (!VirtualMachineService.started){
//    VirtualMachineService.loadScript
//  }
}

object ConfigProperties{
  val ROUTER = "http://criojo.appspot.com/router"
  val DEFAULT_SCRIPT = "(provided:Ping; required:S) Ping(x,K) => K(x) | !T => new(x)S(x)"

//  val PROXY_URL = "http://criojo.appspot.com/proxy"
  val PROXY_URL = "http://localhost:8080/proxy"
  
}