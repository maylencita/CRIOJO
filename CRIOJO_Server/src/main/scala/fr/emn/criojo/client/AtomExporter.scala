package fr.emn.criojo.client

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 20, 2010
 * Time: 3:48:31 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core.Atom
import fr.emn.criojo.util.Logger

import fr.emn.criojo.model._
import fr.emn.criojo.util._
import fr.emn.criojo.virtualmachine.VirtualMachineService

import java.net.URI
import javax.ws.rs.core.MediaType

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.config.DefaultClientConfig


object AtomExporter {
  val myclient:Client = Client.create(new DefaultClientConfig)

  def export(atom:Atom, url:URI){
    val myservice = myclient.resource(url).path(atom.relName)
    val content = JSONUtil.serialize(new WebAtom(atom))

    try{
      val resp:String = myservice.entity(content.getBytes, MediaType.APPLICATION_JSON_TYPE).
              post(classOf[String])
      Logger.log(this.getClass, "exportAtom","Response: " + resp)
    }catch{
      case e => Logger.log(this.getClass, "exportAtom","Error: " + e)
    }

  }
}