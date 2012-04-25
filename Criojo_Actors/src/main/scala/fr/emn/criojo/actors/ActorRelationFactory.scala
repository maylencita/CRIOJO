package fr.emn.criojo.actors

import fr.emn.criojo.core._
import actors.Actor
import factory.{DefaultFactory, RelationFactory}
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
import actors.remote.{Node, RemoteActor}
import json_criojo.JSONUtil
import fr.emn.criojo.ext.debug.Solution

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 25/03/12
 * Time: 10:02
 */
trait ActorRelationFactory extends DefaultFactory{
  override def createChannel(name: String,location:String):Channel =
    new ActorChannel(name,location)
}

class ActorChannel(val name:String,val location:String) extends Channel{

  def notifyObservers(a: Atom){
    a.relation match{
      case c:Channel =>
        new Actor{
          RemoteActor.classLoader = getClass().getClassLoader()
          def act(){
            val params = location.split(':')
            val remote = select(Node(params(0),params(1).toInt),Symbol(params(2)))
            link(remote)
            val atom = new Atom(a.relation match{
              case ch:Channel => ch.location + ":" + ch.name
              case r => r.name
            },a.terms)
            remote ! JSONUtil.serialize(atom)
            var answer = false
            while(!answer){
              react{
                case msg =>
                  answer = true
              }
            }
          }
        }.start()
      case _ => println("Not a channel: " + a.relation)
    }
  }

  def copy(sol: Solution) = {
    val newChannel = new ActorChannel(name,location)
    this.observers.foreach(o => newChannel.addObserver(o))
    newChannel
  }

  override def toString:String = name
}