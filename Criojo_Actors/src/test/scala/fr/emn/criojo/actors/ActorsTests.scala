package fr.emn.criojo.actors

import org.junit._
import Assert._
import actors.Actor
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
import actors.remote.Node

import sjson.json._
import DefaultProtocol._
import JsonSerialization._
import json_criojo.protocols.{JsonTerm, JsonAtom}
import json_criojo.JSONUtil
import fr.emn.criojo.core.{ValueTerm, Variable, Atom}

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 25/03/12
 * Time: 10:36
 */

class ActorsTests {
  @Test
  def serializationTest(){
    import JSONUtil._
    val atom = Atom("R",Variable("x"),ValueTerm(4),ValueTerm("Hola"))
    val deserialized = deserialize(serialize(atom)).get

    assertEquals(atom.toString,deserialized.toString)
  }
}

