package fr.emn.criojo.builtin

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 21, 2010
 * Time: 3:35:48 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo._, core._, ext._
import fr.emn.criojo.util.Logger._
import fr.emn.criojo.virtualmachine.ConnectedVM
import fr.emn.criojo.client._

import java.io.File
import java.net.URL

import com.google.gdata.client._
import com.google.gdata.client.photos._
import com.google.gdata.data._
import com.google.gdata.data.media._
import com.google.gdata.data.photos._

import javax.ws.rs.core.UriBuilder
import javax.ws.rs.core.MediaType;
import java.net.URI
import com.sun.jersey.api.client._, config._
import com.sun.jersey.api.representation.Form

import scala.collection.mutable.HashMap

object PicasaParams{
  var url:URI=_
}

object PicasaVM extends ConnectedVM(PicasaParams.url){

//  var url:URI = _
  val Login = Rel("Login")
  val AlbumCloning = Rel("AlbumCloning")
  val Album = Rel("Album")
  val Session = Rel("Session")

  //Some used variables
  val (session,tok,user,pwd) = ("session","Tok","user","pwd")

  Login(session,tok,user,pwd) ==> HandleLogin(session,tok,user,pwd)

  def addAtom(a:Atom){
    introduceAtom(a)
  }

  override def introduceAtom(atom:Atom)= atom match{
    case Atom("Login", vars) => handleLogin(vars)
    case Atom("AlbumCloning", vars) => handleAlbum(vars)
    case _ =>
  }

  def handleLogin(vars:List[Variable]){
    if (vars.size == 4){
        val authToken = "DQAAAKAAAAC2fo_3dEqqiIFMggSLNv1hYa8rEJOlRs9qZNWOvc7fyDe0uoL3TfA7njSp9rLzIkBl9BcJ8Xt_CjIKUkV4QL6NnbN8mESk67xps59UKFB2JgnlB_ZsujcIivWp8XzHPnZV6lWVU_EyGpGXMNGrW0aq4e2qIat"
//      val authToken = PicasaClient.login(vars(2).toString, vars(3).toString)

      if ("" != authToken){
        val relVariable = vars(1).asInstanceOf[RelVariable]
        val tokAtom = new Atom(relVariable.name, List(vars(0), new Variable(authToken)))
        tokAtom.relation = relVariable.relation

        log(this.getClass, "handleLogin", "tokAtom= " + tokAtom)
        relVariable.relation.notifyObservers(tokAtom)
      }
    }
  }

  def handleAlbum(vars:List[Variable]){
      
  }

  object HandleLogin extends Rel("HandleLogin"){
    override def notifyObservers(a:Atom)= a match{
      case Atom("HandleLogin", vars) =>
        log("[Relation("+name+").notifyObservers] notified by " + a)
        handleLogin(vars)
      case _ => super.notifyObservers(a)
    }
  }

}

object PicasaClient{
  val client = Client.create(new DefaultClientConfig)

  def login(user:String, password:String):String = {
    val loginService = client.resource(UriBuilder.fromUri("https://www.google.com/accounts/ClientLogin").build())

    val form = new Form
    form.add("accountType","GOOGLE")
    form.add("Email",user)
    form.add("Passwd",password)
    form.add("service","lh2")
    form.add("source","mayleen-prueba-01")

    val response = loginService.
            post(classOf[ClientResponse], form).getEntity(classOf[String])

    println("response: " + response)
    getProperties(response).get("Auth") match{
      case Some(x) => x
      case _ => ""
    }
  }

  def getProperties(in:String):HashMap[String,String] = {
    var result = new HashMap[String,String]()
    val params = in.split('\n')
    for (p <- params){
      val keyval = p.split('=')
      result.put(keyval(0),keyval(1))
    }

    result
	}

}

