package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 14, 2010
 * Time: 9:39:20 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.ext.RemoteRelation
import fr.emn.criojo.util.Logger
import fr.emn.criojo.util.ConfigProperties._

import fr.emn.criojo.model._
import fr.emn.criojo.util._

import javax.ws.rs._
import javax.ws.rs.core._

@Path("/{atomName}")
class RelationResource(@Context @scala.reflect.BeanProperty var uriInfo:UriInfo) extends Configurator(uriInfo){

  if (!VirtualMachineService.started){
    VirtualMachineService.runScript(DEFAULT_SCRIPT)
    VirtualMachineService.start 
  }

  @POST
  @Produces (Array("text/plain"))
  def receiveAtom(@PathParam("atomName") aName:String, @Context headers:HttpHeaders, in: Array[Byte]):String = {

    Json2Criojo(VirtualMachineService).parseAtomList(new String(in)) match{
//    Json2Criojo(VirtualMachineService).parseAtom(new String(in)) match{
//      case Some(atom) =>
      case atom::_ =>
        Logger.log("============================================================================")
        Logger.log(this.getClass,"receiveAtom","in: " + atom)
        Logger.levelDown
        val clientUrl:String = headers.getRequestHeader("X-Client-URL").get(0)
println("ClientUrl: " + clientUrl)
        val clientProxy = new ClientProxy(UriBuilder.fromUri(clientUrl).build())
//        atom.vars.foreach{
//          case rv:RelVariable if(rv.relation != null && rv.relation.isInstanceOf[RemoteRelation]) =>
//            if (rv.relation.asInstanceOf[RemoteRelation].url.toString == clientUrl)
//              rv.relation.addObserver(clientProxy)
//          case _ =>
//        }

        VirtualMachineService.addAtom(atom)
        Logger.levelUp

        if(clientProxy.response isEmpty) "OK" else JSONUtil.serialize(new AtomList(null, clientProxy.response))       
//      case None =>
        case _ =>
        Logger.log(this.getClass, "receiveAtom", "Invalid argument: " + new String(in))
        "ERROR"
    }

  }

  @GET
  @Produces (Array("text/plain"))
  def getRelation():String = {
    "Solution: \n" +
    VirtualMachineService.getSolution    
  }
}