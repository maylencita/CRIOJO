package fr.emn.criojo.proxy

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 20, 2010
 * Time: 11:34:33 AM
 * To change this template use File | Settings | File Templates.
 */

import javax.ws.rs._
import javax.ws.rs.core._

import scala.collection.mutable.Map

@Path("/")
class ProxyService(@Context @scala.reflect.BeanProperty var uriInfo:UriInfo){

  @GET
  @Path("/register")
  @Produces (Array("text/plain"))
  def registerClient():String = {

    val clientId = ProxyService.addClient
    uriInfo.getBaseUriBuilder().path(clientId.toString).build().toString
  }

  @GET
  @Path("/{id}")
  @Produces (Array("text/plain"))
  def getMessages(@PathParam("id") clientId:Int):String =
    ProxyService.getMessages(clientId).mkString("[",",","]")

  @POST
  @Path("/{clientId}/{relation}")
  @Produces (Array("text/plain"))
  def postMessage(@PathParam("clientId") clientId:Int, @PathParam("relation") relation:String, in: Array[Byte]):String = {
    ProxyService.addMessage(clientId, new String(in))
    "OK"
  }

}

object ProxyService{
  private var curr_id = 0

  var clients:Map[Int, List[String]] = Map()

  def addClient:Int = {
    val newClientId = currentId
    clients.put(newClientId, List[String]())
    newClientId
  }

  def getMessages(clientId:Int):List[String]= clients.get(clientId) match{
      case Some(lst) =>
        clients.update(clientId, List())
        lst
      case _ => List[String]()
  }

  def addMessage(clientId:Int, message:String){
    clients.put(clientId, clients(clientId) :+ message)
  }

  def currentId = {
    curr_id += 1
    curr_id
  }
}