package fr.emn.criojo.sequential

import fr.emn.criojo.dsl.ChamBody
import fr.emn.criojo.core.model.Atom
import fr.emn.criojo.core.engine.AtomGateway
import fr.emn.criojo.expression.Converters
import fr.emn.criojo.core.model.relation.RemoteMessage
import java.util.UUID
import fr.emn.criojo.expression.scala.ScalaTypesPredef

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/26/12
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */

class Agent(val location:String, val gateway:AtomGateway) extends ChamBody with ObserverPatternCham with Converters with ScalaTypesPredef{
  def this() = {
    this("Agent@"+UUID.randomUUID(), DummyGateway)
  }

  def ! (atom:Atom) {this.introduceAtom(atom); this.executeRules()}

  def ! (atoms:List[Atom]) {atoms.foreach(this.introduceAtom(_)); this.executeRules()}

  def start() {}
}

object DummyGateway extends AtomGateway{
  def exportAtom(atom: RemoteMessage) { /** Does Nothing **/ }
}
