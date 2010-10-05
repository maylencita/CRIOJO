package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 23, 2010
 * Time: 10:23:43 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.builtin.{PicasaVM, PicasaParams}
import fr.emn.criojo.util._
import fr.emn.creole.util.Logger

import javax.ws.rs._
import javax.ws.rs.core._

@Path("/P-VM/{atomName}")
class PicasaBuiltin(@Context @scala.reflect.BeanProperty var uriInfo:UriInfo){ //extends Configurator(uriInfo){

  PicasaParams.url = uriInfo.getBaseUri()
  
  @POST
  @Produces (Array("text/plain"))
  def receiveRelation(@PathParam("atomName") aName:String, in: Array[Byte]):String={
    Json2Criojo(PicasaVM).parseAtom(new String(in)) match{
      case Some(atom) =>
        Logger.log(this.getClass, "receiveAtom", "in: " + atom)
        PicasaVM.addAtom(atom)
        "OK"
      case None =>
        Logger.log(this.getClass, "receiveAtom", "Invalid argument: " + new String(in))
        "ERROR"
    }
  }

  @GET
  @Produces (Array("text/plain"))
  def getRelation(): String = {
    "This is Picasa's Built in Virtual Machine :" + "\n" + 
            PicasaVM.relations + "\n" + PicasaVM.rules.mkString("","\n","")
  }
}