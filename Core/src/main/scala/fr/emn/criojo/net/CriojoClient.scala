package fr.emn.criojo.net

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Dec 1, 2010
 * Time: 4:32:25 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.util.json.Json2Criojo
import fr.emn.criojo.util.Logger._

import java.net.URI
import javax.ws.rs.core._
import java.util.logging.Logger

import com.sun.jersey.api.client._
import com.sun.jersey.api.client.config.DefaultClientConfig

import actors._
import collection.mutable.HashMap

object CriojoClient{
  val myclient:Client = Client.create(new DefaultClientConfig)

  @throws(classOf[Exception])
  def registerClient(proxyUrl:String):URI = {
      val proxyservice = myclient.resource(proxyUrl).path("register")
      val resp:String = proxyservice.get(classOf[String])
      println("Virtual address: " + resp)

      UriBuilder.fromUri(resp).build()
  }

  def main(args:Array[String]){
    val dClient = new DirectClient("local",9999)
    dClient.start
  }
}

class ProxiedClient(url:URI) extends ConnectedCHAM(url) with Actor{

  def act{
    while(true){
      try{
        getMessages
      }catch{
        case e=>
          log(WARNING, this.getClass,"act","Could not connect to proxy: " + e)
      }

      Thread.sleep(500)
    }
  }

  @throws(classOf[Exception])
  private def getMessages:String = {
    val proxyservice = CriojoClient.myclient.resource(url)
    val resp = proxyservice.get(classOf[String])

    if (resp != "[]"){
      for (a <- Json2Criojo(this).parseList(resp)){
        debug(this.getClass,"getMessages", "Received atom: " + a)
        addAtom(a)
      }
    }
    resp
  }
}

import java.net._
import java.io._

case class Connection(socket:Socket)

class DirectClient(name:String, port:Int)  extends
ConnectedCHAM(UriBuilder.fromUri("http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/" + name).build()) with Actor{

  def act() {
    info(this.getClass,"act", "The client is running at " + vmUrl)
    val s_socket = new ServerSocket(port)
    while(true){
      val conn = s_socket.accept()
      logger.info("Connection accepted.")
      val ch = new ConnectionHandler(this)
      ch.start
      ch ! new Connection(conn)
    }
  }
}

  class ConnectionHandler(owner:DirectClient) extends Actor{
    val logger = Logger.getLogger(this.getClass.getName())
    
    private val Post = "^(POST /.*)$".r
    private val okAnswer = """|HTTP/1.1 200 OK
                              |Server: Criojo Client
                              |Accept-Ranges: bytes
                              |Connection: close
                              |Content-Type: text/html
                              |Content-Length: 2
                              |
                              |""".stripMargin + "OK" + (-1).toChar

    private val errorAnswer = "HTTP/1.1 405 Method Not Allowed".stripMargin

    def act(){
      loop{
        react{
          case conn:Connection =>
            handleConnection(conn.socket)    
          case _ => logger.warning("Message ignored.")
        }
      }
    }

    def handleConnection(socket:Socket){
      val os = socket.getOutputStream
      val writer = new OutputStreamWriter(os)

      val is = socket.getInputStream
      val reader = new BufferedReader(new InputStreamReader(is))

      val line = reader.readLine()
      if(line != null){
        line.trim match{
          case Post(s) =>
            var message = getMessage(reader)
            writer.write(okAnswer)
            writer.flush()
            writer.close()
            socket.close()
            logger.info("Message received: " + message)
            handleMessage(message)
          case msg =>
            log(WARNING, this.getClass, "handleConnection", "Invalid operation: " + msg)
            writer.write(errorAnswer)
            writer.flush()
            writer.close()
            socket.close()
        }
      }
    }

    def handleMessage(message:String){
      for (a <- Json2Criojo(owner).parseAtomList(message)){
        owner.addAtom(a)
        logger.info("Atom " + a + " successfully added.")
      }
    }

    def getHeaders(reader:BufferedReader):HashMap[String,String] = {
      val headers = new HashMap[String,String](){
        override def default(key:String) = "-1"
      }

      var line = reader.readLine()
      while(line != ""){
        val header = line.split(':')
        headers.put(header(0).trim, header(1).trim)
        line = reader.readLine()
      }

      headers
    }

    def getMessage(reader:BufferedReader):String = {
      val headers = getHeaders(reader)
      val length = java.lang.Integer.parseInt(headers("Content-Length"))

      var i=0
      var message = ""
      while(i < length){
        message += reader.read().toChar
        i += 1
      }

      message
    }

  }