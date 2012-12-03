package fr.emn.criojo.parallel

import fr.emn.criojo.core.model.Atom

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
  case class Ready(rule:ParRule)
  object Ok
  object Fire //To ask if a reaction is ready to fire
  object NotOk //The rule was executed
}
