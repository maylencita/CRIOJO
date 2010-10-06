package fr.emn.criojo.client

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 18, 2010
 * Time: 2:26:31 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core.Atom
import fr.emn.criojo.util.Logger
import Logger._

import fr.emn.criojo.model._
import fr.emn.criojo.virtualmachine.VirtualMachineService
import fr.emn.criojo.util._

import java.net.URI
import javax.ws.rs.core.MediaType
import java.io.{FileInputStream, IOException}
import java.net.InetAddress

import javax.ws.rs.core._
import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.config.DefaultClientConfig

object CreoleClient {
/*
//  val PROXY_URL = "http://criojo.appspot.com/proxy"
  val PROXY_URL = "http://localhost:8080/proxy"
  val myclient:Client = Client.create(new DefaultClientConfig)

  logLevel = DEBUG
  
  def main(args:Array[String]){
    if (args.size < 1){
      println("USAGE: CreoleClient <script_file> [<remote_relation>@<remote_server>]")
      exit()
    }else{
      try{
        VirtualMachineService.reset
        VirtualMachineService.url = registerClient

        val script = io.Source.fromFile(args(0)).mkString
        if (args.size > 1){
          val relation = args(1).split("@")
          println("Remote Relation: " + relation(0) + " at "+ relation(1))

          val remoteRelation = new RemoteRelationImpl(relation(0), UriBuilder.fromUri(relation(1)).build())
          VirtualMachineService.machine.addRelation(remoteRelation)
        }

        log(this.getClass, "runScript","script: " + script)
        log(this.getClass, "runScript","relations: " + VirtualMachineService.machine.relations)
        VirtualMachineService.runScript(script)
        VirtualMachineService.start

        //Loop to receive atoms
        while(true){
          getMessages
          Thread.sleep(5000)
        }
      }catch{
        case ioe:IOException => println("File error: " + ioe)
        case e => println("Unexpected error: " + e)
          e.printStackTrace
      }
    }
  }

//  @throws(classOf[Exception])
  def registerClient:URI = {
    try{
      val proxyservice = myclient.resource(PROXY_URL).path("register")
      val resp:String = proxyservice.get(classOf[String])
      println("Virtual address: " + resp)

      UriBuilder.fromUri(resp).build()
    }catch{
      case e =>
        log(WARNING, this.getClass, "registerClient", "Error connecting to the proxy. Working offline.")
        UriBuilder.fromUri("localhost:8080").build()
    }
  }

  @throws(classOf[Exception])
  def getMessages:String = {
    val proxyservice = myclient.resource(VirtualMachineService.url)
    val resp = proxyservice.get(classOf[String])

    if (resp != "[]"){
      for (a <- Json2Criojo(null).parseList(resp)){
        VirtualMachineService.addAtom(a)  
      }
    }
    resp
  }
  */
}

