package fr.emn.creole.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 20, 2010
 * Time: 11:00:07 AM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.creole.util._
import fr.emn.creole.model._

import scala.reflect.BeanProperty

import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.spi.resource.Singleton;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs._
import javax.ws.rs.core._
import com.sun.jersey.api.representation.Form

@Path("/")
@Singleton
class Console{

  @BeanProperty
  var script:String = _

  @POST
  @Consumes(Array("application/x-www-form-urlencoded"))
  @Produces (Array("text/plain"))
  def runScript(@Context uriInfo : UriInfo, /*@QueryParam("script")*/ formData: Form): String ={
    if (formData == null){
      Logger.log(this.getClass, "runScript","Received Empty Script")
      ""
    }else{
      try{
        this.script = formData.getFirst("script")

        VirtualMachineService.reset
        VirtualMachineService.url = uriInfo.getBaseUri().resolve("..")        
        val server = formData.getFirst("remote_vm")
        val relName = formData.getFirst("remote_relation")

        if (server != "" && relName != ""){
          val remoteRelation = new RemoteRelation(relName, UriBuilder.fromUri(server).build())
          VirtualMachineService.machine.addRelation(remoteRelation)
        }

        Logger.log(this.getClass, "runScript","script: " + script)
        VirtualMachineService.runScript(script)
        VirtualMachineService.getSolution
      }catch{
        case e =>
          e.printStackTrace
          "Could not process script: " + e
      }
    }
  }
}