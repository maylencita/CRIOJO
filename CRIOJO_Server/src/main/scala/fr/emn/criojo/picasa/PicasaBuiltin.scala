package fr.emn.criojo.picasa

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 23, 2010
 * Time: 10:23:43 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.ext.RemoteRelation
import fr.emn.criojo.core._
import fr.emn.criojo.builtin.{PicasaVM, PicasaParams}
import fr.emn.criojo.util._
import fr.emn.criojo.util.Logger._
import fr.emn.criojo.virtualmachine.ClientProxy
import fr.emn.criojo.model._
                              
import java.util.logging.Logger
import java.util.logging.Level._
import javax.ws.rs._
import javax.ws.rs.core._

@Path("/VM/{atomName}")
class PicasaBuiltin(@Context @scala.reflect.BeanProperty var uriInfo:UriInfo){ //extends Configurator(uriInfo){

  PicasaParams.url = uriInfo.getBaseUri().resolve("PVM")
//  logLevel = DEBUG
  val log = Logger.getLogger(this.getClass.getName())
  
  @POST
  @Produces (Array("text/plain"))
  def receiveRelation(@PathParam("atomName") aName:String, @Context headers:HttpHeaders, in: Array[Byte]):String={
    log.info("in: " + new String(in))

//    Json2Criojo(PicasaVM).parseAtom(new String(in)) match{
    Json2Criojo(PicasaVM).parseAtomList(new String(in)) match{
//      case Some(atom) =>
      case atom::_ =>
//        Logger.log(this.getClass, "receiveAtom", "in: " + atom)
        log.info("in: " + atom)
        val clientUrl:String = headers.getRequestHeader("X-Client-URL").get(0)
        val clientProxy = new ClientProxy(UriBuilder.fromUri(clientUrl).build())
        atom.vars.foreach{
          case rv:RelVariable if(rv.relation != null && rv.relation.isInstanceOf[RemoteRelation]) =>
            if (rv.relation.asInstanceOf[RemoteRelation].url.toString == clientUrl)
              rv.relation.addObserver(clientProxy)
          case _ =>
        }

        PicasaVM.loadSolution
        PicasaVM.addAtom(atom)
        if(clientProxy.response isEmpty) "OK" else JSONUtil.serialize(new AtomList(null, clientProxy.response))

//      case None =>
      case _ =>
//        Logger.log(this.getClass, "receiveAtom", "Invalid argument: " + new String(in))
        log.log(SEVERE, "Invalid argument: " + new String(in))
        "ERROR"
    }
  }

  @GET
  @Produces (Array("text/plain"))
  def getRelation(): String = {
    PicasaVM.loadSolution
    "This is Picasa's Built in Virtual Machine : \n" + 
            PicasaVM.relations + "\n" + PicasaVM.rules.mkString("","\n","")  + "\n" +
            PicasaVM.prettyPrint
  }
}