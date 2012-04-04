package fr.emn.criojo.actors

import actors.Actor
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
import scala.actors.remote.Node
import json_criojo.JSONUtil._
import fr.emn.criojo.core.{RelVariable, Atom}
import fr.emn.criojo.lang.{Applicable, ChamRel, Cham}

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/03/12
 * Time: 11:24
 */
trait MessageHandler{
  def handleMessage(msg:String) {}
}

abstract class ActorCham(host:String,port:Int,name:String) extends Cham
with ActorRelationFactory with Actor with MessageHandler{
  override val chamLocation = host+":"+port+":"+name
  RemoteActor.classLoader = getClass().getClassLoader()

  def act(){
    alive(port)
    register(Symbol(name),this)

    loop{
      react{
        case msg:String =>
          handleMessage(msg)
        case _ => sender ! "Unknown format"
      }
    }
  }

  override def handleMessage(msg:String){
    deserialize(msg) match{
      case Some(a:Atom) => a
      val atom = processAtom(a)
      findRelation(atom.relName) match{
        case Some(r) => atom.relation = r
        introduceAtom(atom)
        executeRules()
        case _ => sender ! "Unsupported operation " + a
      }
      case _ => println(this + " - Unknown format: " + msg)
      sender ! "ERROR: Unknown format"
    }
  }

  def processAtom(a:Atom):Atom = {
    new Atom(a.relName, a.terms.map{
      case rv:RelVariable =>
        val params = rv.name.split(":")
        val loc = params(0)+":"+params(1)+":"+params(2)
        val relName = params(3)
        val channel = createChannel(relName,loc)
        val newRv = RelVariable(channel)
        newRv
      case t => t
    })
  }

  override def toString:String = this.chamLocation
}

object ChamRunner{
  def main(args:Array[String]){
    //TODO Load from configuration
  }
}