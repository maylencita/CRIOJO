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

class ActorCham(host:String,port:Int,name:String) extends Cham with ActorRelationFactory with Actor{
  val chamLocation = host+":"+port+":"+name
  RemoteActor.classLoader = getClass().getClassLoader()

  def act(){
    alive(port)
    register(Symbol(name),this)
println("ActorCham Listening...")

    loop{
      react{
        case msg:String =>
println(this.toString + " - Message received: " + msg)
          deserialize(msg) match{
            case Some(a:Atom) => a
              val atom = processAtom(a)
println(this.toString + " - Atom received: " + atom)
              findRelation(atom.relName) match{
                case Some(r) => atom.relation = r
                  introduceAtom(atom)
                  executeRules()
                case _ => sender ! "Unsupported operation " + a
              }
            case _ => println("Unknown format: " + msg)
              sender ! "ERROR"
          }
        case _ => sender ! "Unknown format"
      }
    }
  }

  def Channel(name:String,loc:String) = {
    val channel = createChannel(name,loc)
    addRelation(channel)
    new ChamRel(channel)
  }

  def processAtom(a:Atom):Atom = {
    new Atom(a.relName, a.terms.map{
      case rv:RelVariable =>
        val nameloc = rv.name.splitAt(rv.name.lastIndexOf(':'))
        val channel = createChannel(rv.name,nameloc._1)
        val newRv = RelVariable(channel)
        newRv
      case t => t
    })
  }

  override def Rel:Applicable = Rel("@R"+nextIndex)

  override def Rel(n:String): ChamRel = {
    val r = createLocalRelation(this.chamLocation+":"+n)
    addRelation(r)
    new ChamRel(r)
  }

  override def toString:String = this.chamLocation
}

object ChamRunner{
  def main(args:Array[String]){
    //TODO Load from configuration
    val host = "localhost"
    val port = 9999
    val name = "Agent1"

    val cham = new ActorCham(host,port,name)
    cham.start()
  }
}