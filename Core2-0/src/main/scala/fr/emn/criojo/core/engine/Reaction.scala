package fr.emn.criojo.core.engine

import fr.emn.criojo.core.model._

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/26/12
 * Time: 6:00 PM
 *
 */

/**
 * A Reaction is the result of the execution of a Rule.
 * It contains a Set of atoms to be removed and a Set of atoms to be inserted
 */
case class Reaction(toRemove:Iterable[Atom], toInsert:Iterable[Atom]) {}
