package fr.emn.criojo.integration.physics

import fr.emn.criojo.core.Atom
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.HashSet

import scala.collection.JavaConversions._

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 3/16/12
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */

class PhysicAtom {
  var x:Float = 0
  var y:Float = 0
  var radius:Float = 0
  var xSpeed:Float = 0
  var ySpeed:Float = 0

  var view:PhysicsSketch = _
  var atom:PhysicCriojoAtom = _

  def setX(x:Float) {
    this.x = x
  }

  def setY(y:Float) {
    this.y = y
  }

  def setRadius(radius:Float) {
    this.radius = radius
  }

  def setXSpeed(xSpeed:Float) {
    this.xSpeed = xSpeed
  }

  def setYSpeed(ySpeed:Float) {
    this.ySpeed = ySpeed
  }

  def setAtom(a:PhysicCriojoAtom) {
    this.atom = a
    a match {
      case crjAtom:PhysicCriojoAtom => crjAtom.setParentViewObject(this)
      case _ =>
    }
  }

  def setView(v:PhysicsSketch) {
    this.view = v
  }

  var linkedTo:ConcurrentLinkedQueue[PhysicAtom] = new ConcurrentLinkedQueue()

  def getLinkedTo():ConcurrentLinkedQueue[PhysicAtom] = linkedTo

  def getSolution(s:ConcurrentLinkedQueue[PhysicAtom]):ConcurrentLinkedQueue[PhysicAtom] = {

    var result:ConcurrentLinkedQueue[PhysicAtom] = linkedTo
    result.add(this)

    if(!s.contains(this))
      s.add(this)

    linkedTo.foreach(a => {
      if(!s.contains(a))
        result.addAll(a.getSolution(s))
    })

    result
  }

  def getSolution():ConcurrentLinkedQueue[PhysicAtom] = {

    getSolution(new ConcurrentLinkedQueue())
  }
}
