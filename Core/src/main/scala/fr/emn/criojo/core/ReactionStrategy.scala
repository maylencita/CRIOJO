package fr.emn.criojo.core

import fr.emn.criojo.ext.expression.Relation.constructor.{OutChannel, Channel, LocalRelation}
import impur.NativeRelation

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 6/6/12
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */

trait ReactionStrategy {
  def applyReaction(engine:Engine, atom:Atom)
}

class LocalReactionStrategy extends ReactionStrategy {
  def applyReaction(engine:Engine, atom:Atom) {

    atom.relation match {
      case l:LocalRelation => l match {
        case r:NativeRelation => r.execute(atom)
        case _ => engine.introduceAtom(atom)
      }
      case c:OutChannel => engine.introduceAtom(atom)
      case c:Channel => engine.introduceAtom(atom)
    }

  }
}
