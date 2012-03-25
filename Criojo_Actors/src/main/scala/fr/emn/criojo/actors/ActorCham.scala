package fr.emn.criojo.actors

import fr.emn.criojo.lang.Cham
import actors.Actor
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
import scala.actors.remote.Node

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/03/12
 * Time: 11:24
 */

class ActorCham(port:Int,name:String) extends Cham with ActorRelationFactory with Actor{
  //TODO Create a configuration file with this parameters

  def act(){
    alive(port)
    register(Symbol(name),this)

    loop{
      react{
        case msg =>
println("Message received: " + msg)
          sender ! "OK"
          exit()
      }
    }
  }
}