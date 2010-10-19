package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 20, 2010
 * Time: 11:00:07 AM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.util._
import fr.emn.criojo.util._
import fr.emn.criojo.model._

import scala.reflect.BeanProperty

import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs._
import javax.ws.rs.core._
import com.sun.jersey.api.representation.Form

@Path("/console")
//@Singleton
class Console (@Context @scala.reflect.BeanProperty var uriInfo:UriInfo) extends Configurator(uriInfo){

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

        Logger.log(this.getClass, "runScript","script: " + script)
        VirtualMachineService.runScript(script)
        VirtualMachineService.start
        VirtualMachineService.getSolution
      }catch{
        case e =>
          e.printStackTrace
          "Could not process script: " + e
      }
    }
  }
}