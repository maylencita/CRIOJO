package fr.emn.criojo.relations

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 14, 2010
 * Time: 9:39:20 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger

import fr.emn.criojo.virtualmachine._
import fr.emn.criojo.model._
import fr.emn.criojo.util._

import javax.ws.rs._
import javax.ws.rs.core._

@Path("/{atomName}")
class RelationResource(@Context @scala.reflect.BeanProperty var uriInfo:UriInfo) extends Configurator(uriInfo){

  if (!VirtualMachineService.started){
    VirtualMachineService.runScript("""
     (public:Ping; private:_)
     Ping(x,K) => K(x)
            """)
  }

  @POST
  @Produces (Array("text/plain"))
  def receiveAtom(@PathParam("atomName") aName:String, @Context headers:HttpHeaders, in: Array[Byte]):String = {

    Json2Criojo(VirtualMachineService).parseAtom(new String(in)) match{
      case Some(atom) =>
        Logger.log("============================================================================")
        Logger.log(this.getClass,"receiveAtom","in: " + atom)
        Logger.levelDown
        VirtualMachineService.addAtom(atom)
        Logger.levelUp
        "OK"
      case None =>
        Logger.log(this.getClass, "receiveAtom", "Invalid argument: " + new String(in))
        "ERROR"
    }

//    new ClientProxy(atom).send match{
//      case Some(a) =>
//        Logger.log(this.getClass, "receiveAtom", "responseAtom= " + a)
//        JSONUtil.serialize(new WebAtom(a))
//      case None =>
//        Logger.log(this.getClass, "receiveAtom", "responseAtom= None")
//        ""
//    }
  }

  @GET
  @Produces (Array("text/plain"))
  def getRelation():String = {
    "Solution: \n" +
    VirtualMachineService.getSolution    
  }
}
