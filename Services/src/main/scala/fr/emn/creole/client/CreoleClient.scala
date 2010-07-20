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
import fr.emn.creole.util.JSONUtil

import java.net.URI
import javax.ws.rs.core.MediaType

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.config.DefaultClientConfig
import sjson.json._

class CreoleClient {

  def exportAtom(atom:Atom, url:URI){
//    val config = new DefaultClientConfig
//    val client = Client.create(config)
    val myclient:Client = Client.create(new DefaultClientConfig)
    val myservice = myclient.resource(url).path(atom.relName)

    val content = JSONUtil.serialize(new VarList(atom))

    val resp = try{
      myservice.entity(content.getBytes, MediaType.APPLICATION_JSON_TYPE).
              put(classOf[String])
    }catch{
      case e => println("Error: " + e)
    }

    println("Response: " + resp)
  }

}