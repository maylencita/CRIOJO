package fr.emn.criojo.core.engine.impure

import fr.emn.criojo.core.model.{relation, Term, Atom}
import relation.Relation

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/26/12
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates.
 */

trait NativeAtom extends Atom{
  def apply:List[Atom]
}

class NativeRelation(val name:String, nativeFun: (List[Term]) => List[Atom]) extends Relation{
  def newAtom(terms:List[Term]):Atom = new NativeAtomImpl(this, terms)

  private class NativeAtomImpl(val relation:Relation, val terms:List[Term]) extends NativeAtom{
    def apply():List[Atom] = {
      nativeFun(terms)
    }
  }
}
