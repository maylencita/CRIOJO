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
  val chamLocation = host+":"+port+":"+name
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

  //TODO Implement with a configuration file
  def lookupChannel(name:String):String

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

  def Channel(name:String,loc:String) = {
    val channel = createChannel(name,loc)
    addRelation(channel)
    new ChamRel(channel)
  }

  def InChannel(name:String) = {
    val k = createLocalRelation(this.chamLocation+":"+name)
    addRelation(k)
    new ChamRel(k)
  }

  def OutChannel(name:String) = {
    val loc = lookupChannel(name)
    val k = createChannel(name,loc)
    addRelation(k)
    new ChamRel(k)
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

//  override def Rel:Applicable = Rel("@R"+nextIndex)
//
//  override def Rel(n:String): ChamRel = {
//    val r = createLocalRelation(this.chamLocation+":"+n)
//    addRelation(r)
//    new ChamRel(r)
//  }

  override def toString:String = this.chamLocation
}

object ChamRunner{
  def main(args:Array[String]){
    //TODO Load from configuration
  }
}