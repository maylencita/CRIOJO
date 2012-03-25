package fr.emn.criojo.actors

import org.junit._
import Assert._
import actors.Actor
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
import actors.remote.Node

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 25/03/12
 * Time: 10:36
 */

class ActorsTests {
  @Test
  def testConnection{
    val cham = new ActorCham(9999,"Agent1")
    cham.start()
  }
}

class TestActor(peer:Node,remoteName:String) extends Actor{
  def act(){
    RemoteActor.classLoader = getClass().getClassLoader()
    val sink = select(peer, Symbol(remoteName))
    link(sink)

    sink ! "Hola"

    while(true){
      receive{
        case msg => println("Response: " + msg)
        exit()
      }
    }
  }
}