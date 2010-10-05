package fr.emn.creole.resources

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 15, 2010
 * Time: 11:46:11 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._
import fr.emn.creole.virtualmachine._
import fr.emn.creole.model._
import fr.emn.creole.util._

import java.net.URI
import javax.ws.rs._
import javax.ws.rs.core._

//@Path("/PhotoCloning")
@Path("/{atomName}")
class RelationResource{

  @PUT
  @Produces (Array("text/plain"))
  def receivePhotoCloning(@PathParam("atomName") aName:String, @Context headers:HttpHeaders, in: Array[Byte]):String = {
    val varList = JSONUtil.deserialize[VarList](in).asInstanceOf[VarList]
    Logger.log(this.getClass, "receiveAtom","in: " + new String(in))

    VirtualMachineService.addAtom(new Atom(aName, varList.getVarList))
    VirtualMachineService.getSolution
  }

  @GET
  @Produces (Array("text/plain"))
  def printAtom(@Context uriInfo : UriInfo, @PathParam("atomName") aName:String):String = {
//    VirtualMachineService.getRelation(aName)
//    //aName + "()"
//    uriInfo.getBaseUri().resolve("..").resolve("relation").toString
    VirtualMachineService.getSolution
  }

}