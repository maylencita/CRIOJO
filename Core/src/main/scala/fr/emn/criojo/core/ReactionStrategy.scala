package fr.emn.criojo.core

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
    engine.introduceAtom(atom)
  }
}
