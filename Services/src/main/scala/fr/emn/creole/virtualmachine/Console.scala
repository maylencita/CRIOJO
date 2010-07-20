package fr.emn.creole.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 20, 2010
 * Time: 11:00:07 AM
 * To change this template use File | Settings | File Templates.
 */

import scala.reflect.BeanProperty

import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.spi.resource.Singleton;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs._
import com.sun.jersey.api.representation.Form

@Path("/")
@Singleton
class Console{

  @BeanProperty
  var script:String = _

  @POST
  @Consumes(Array("application/x-www-form-urlencoded"))
  def runScript(/*@QueryParam("script")*/ formData: Form): Console ={
    if (formData == null){
      println("Received Empty Script")
      this
    }else{
      this.script = formData.getFirst("script")
//      println("script: " + script)
//      VirtualMachineService.runScript(script)
//      VirtualMachineService.getSolution
      this
    }
  }
}