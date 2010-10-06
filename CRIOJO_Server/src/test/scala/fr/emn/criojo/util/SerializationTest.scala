package fr.emn.criojo.util


/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 15, 2010
 * Time: 4:47:01 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.model._
import fr.emn.criojo.util._

object SerializationTest{
  def main(args:Array[String]){
    val atom = new Atom("R", List(new Variable("x")))
    println("Serialization result: " + JSONUtil.serialize(new WebAtom(atom)))
  }
}