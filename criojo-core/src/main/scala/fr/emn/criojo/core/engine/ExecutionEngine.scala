package fr.emn.criojo.core.engine

import fr.emn.criojo.core.model.Atom

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/26/12
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
trait ExecutionEngine {
  def addAtom(atom:Atom)
  def removeAtom(atom:Atom)
}
