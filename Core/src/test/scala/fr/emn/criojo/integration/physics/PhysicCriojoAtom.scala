package fr.emn.criojo.integration.physics

import fr.emn.criojo.core.Term
import fr.emn.criojo.lang.CrjAtom


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 3/16/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */

object PhysicCriojoAtom {

  var nextId:Int = 0
}

class PhysicCriojoAtom(relName:String, terms: List[Term]) extends CrjAtom(relName, terms) {

  var Id:Int = -1

  def getId():Int = Id
  
  var parentViewObject:PhysicAtom= _

  def setParentViewObject(p:PhysicAtom) {
    parentViewObject = p
  }
}
