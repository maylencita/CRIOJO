package fr.emn.criojo.proxy

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 20, 2010
 * Time: 11:34:33 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.util.Logger._

import javax.ws.rs._
import javax.ws.rs.core._

import scala.collection.mutable.Map

@Path("/")
class ProxyService(@Context @scala.reflect.BeanProperty var uriInfo:UriInfo){

  @GET
  @Path("/register")
  @Produces (Array("text/plain"))
  def registerClient():String = {
    ProxyService.load
    val clientId = ProxyService.addClient
    ProxyService.save
    uriInfo.getBaseUriBuilder().path(clientId.toString).build().toString
  }

  @GET
  @Path("/{id}")
  @Produces (Array("text/plain"))
  def getMessages(@PathParam("id") clientId:Int):String ={
    ProxyService.load
    val messages = ProxyService.getMessages(clientId).mkString("[",",","]")
    ProxyService.save
    messages
  }

  @POST
  @Path("/{clientId}/{relation}")
  @Produces (Array("text/plain"))
  def postMessage(@PathParam("clientId") clientId:Int, @PathParam("relation") relation:String, in: Array[Byte]):String = {
    ProxyService.load
    ProxyService.addMessage(clientId, new String(in))
    ProxyService.save
    "OK"
  }

  @GET
  @Path("/admin")
  @Produces(Array("text/plain"))
  def admin:String = {
    ProxyService.load
    ProxyService.clients.mkString("","\n","")
  }

}

import com.google.appengine.api.memcache._
import fr.emn.criojo.virtualmachine.UnavailableChacheException

object ProxyService{
  private var curr_id = 0

  var clients:Map[Int, List[String]] = Map()
  val cache = //LocalCache
    try{
      MemcacheServiceFactory.getMemcacheService
    }catch{
      case e =>
        log(WARNING, this.getClass, "init()", "Unable to get cache: " + e)
        throw new UnavailableChacheException
    }

  def load{
    cache.get("ProxyClients") match {
      case clientMap:Map[Int,List[String]] =>
        clients = clientMap
      case _ => Map[Int,List[String]]()
    }
  }

  def save{
    cache.put("ProxyClients", clients)
  }

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
    clients.get(clientId) match{
      case Some(msgLst) => clients.put(clientId, msgLst :+ message)
      case _ => //Do Nothing
    }
//    clients.put(clientId, clients(clientId) :+ message)
  }

  def currentId = {
    curr_id += 1
    curr_id
  }
}