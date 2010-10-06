package fr.emn.criojo.client

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 14, 2010
 * Time: 10:28:11 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core.Atom
import fr.emn.criojo.util.Logger

import fr.emn.criojo.model._
import fr.emn.criojo.util._
import fr.emn.criojo.virtualmachine.VirtualMachineService

import scala.actors.Actor

import java.net.URI
import javax.ws.rs.core.MediaType

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.config.DefaultClientConfig

case class ExportAtom(atom:Atom, url:URI) extends Actor{
  def act{
//    val myclient:Client = Client.create(new DefaultClientConfig)
//    val myservice = myclient.resource(url).path(atom.relName)
//
//    val content = JSONUtil.serialize(new WebAtom(atom)) //.serialize(new VarList(atom))
//
//    try{
//      val resp:String = myservice.entity(content.getBytes, MediaType.APPLICATION_JSON_TYPE).
//              post(classOf[String])
//      Logger.log(this.getClass, "exportAtom","Response: " + resp)
//      Json2Criojo.parseAtom(resp) match{
//        case Some(receivedAtom) =>
//          Logger.log(this.getClass, "exportAtom","Response(Atom): " + receivedAtom)
//          VirtualMachineService.addAtom(receivedAtom)
//          Logger.log(this.getClass, "exportAtom","finised with solution: " + VirtualMachineService.getSolution)
//        case _ =>
//      }
//    }catch{
//      case e => Logger.log(this.getClass, "exportAtom","Error: " + e)
//      null
//    }

  }
}