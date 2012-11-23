package fr.emn.criojo.parallel

import fr.emn.criojo.core.Atom

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/23/12
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
package object messages {
  case class Put(a:Atom) //Introuduces a new atom to the reaction
  case class Remove(a:Atom) //Removes an atom from the reaction
  case class Ready() //To ask if a reaction is ready to fire
  case class Ok() //Ok ready response message
  case class NotOk() //Not ready response message
}
