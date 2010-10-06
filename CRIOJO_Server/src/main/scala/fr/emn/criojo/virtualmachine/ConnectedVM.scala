package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 1, 2010
 * Time: 2:48:32 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._
import fr.emn.criojo.ext.{VirtualMachine,RemoteRelation}
import fr.emn.criojo.util.Logger._
import fr.emn.criojo.util._
import fr.emn.criojo.model._

//import fr.emn.criojo.model.RemoteRelationImpl

import java.net.URI
import javax.ws.rs.core.{UriBuilder,MediaType}

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.config.DefaultClientConfig

case class PublicRelation(override val name:String, url:URI) extends LocalRelation(name, true)

class ConnectedVM(val url:URI) extends VirtualMachine{
  assert(url != null)

  override def addRelation(relation:Relation) = {
    super.addRelation(
      if (relation.public)
        new PublicRelation(relation.name, url)
      else
        relation
    )
  }

  def newRemoteRelation(remoteName:String,remoteUrl:String):RemoteRelation = {
    val uri = UriBuilder.fromUri(remoteUrl).build()
    val r = new RemoteRelationImpl(remoteName,uri)
    addRelation(r)
    r
  }

  class RemoteRelationImpl(val name:String,val url:URI) extends RemoteRelation{
    val myclient:Client = Client.create(new DefaultClientConfig)

    override def notifyObservers(a: Atom){
      val subs = a.vars.map{
        v => getValue(v) match{
          case value:Value[_] => (v, value)
          case NoValue => (v,v)
        }
      }

      log(this.getClass,"notifyObservers"," subs= " + subs)

      val newAtom = a.applySubstitutions(subs)

      exportAtom(newAtom)//, url)
      log(this.getClass,"notifyObservers"," Atom exported =" + newAtom)
    }

    def exportAtom(atom:Atom){//, url:URI){
      val myservice = myclient.resource(url).path(atom.relName)
      val content = JSONUtil.serialize(new WebAtom(atom))

      try{
        val resp:String = myservice.entity(content.getBytes, MediaType.APPLICATION_JSON_TYPE).
                post(classOf[String])
        log(this.getClass, "exportAtom","Response: " + resp)
      }catch{
        case e => log(this.getClass, "exportAtom","Error: " + e)
      }
    }
  }
}