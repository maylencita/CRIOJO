package fr.emn.creole.client

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 18, 2010
 * Time: 2:26:31 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core.Atom
import fr.emn.creole.model._
import fr.emn.creole.util._

import java.net.URI
import javax.ws.rs.core.MediaType

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.config.DefaultClientConfig
import sjson.json._

object CreoleClient {

  val myclient:Client = Client.create(new DefaultClientConfig)

  def exportAtom(atom:Atom, url:URI){
//    val config = new DefaultClientConfig
//    val client = Client.create(config)
    val myservice = myclient.resource(url).path(atom.relName)

    val content = JSONUtil.serialize(new VarList(atom))

    val resp = try{
      myservice.entity(content.getBytes, MediaType.APPLICATION_JSON_TYPE).
              put(classOf[String])
    }catch{
      case e => Logger.log(this.getClass, "exportAtom","Error: " + e)
    }

    Logger.log(this.getClass, "exportAtom","Response: " + resp)
  }

}