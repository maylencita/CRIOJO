package fr.emn.creole.client

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 12, 2010
 * Time: 2:25:05 PM
 * To change this template use File | Settings | File Templates.
 */

//import javax.ws.rs.core.UriBuilder
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client

//import com.sun.jersey.api._, client._, config._
import sjson.json._

object SimpleClient extends Application{

  override def main(args:Array[String]){
//    val config = new DefaultClientConfig
//    val client = Client.create(config)
    val client = Client.create
    val service = client.resource("http://localhost:9998/WangaWanga")

    val vlst = """{"lst":[{"nom":"X"},{"nom":"a"}]}"""

    val resp = try{
      service.entity(vlst.getBytes, MediaType.APPLICATION_JSON_TYPE).
              put(classOf[String])
    }catch{
      case e => println("Error: " + e)
    }

    println("Response: " + resp)

  }
}