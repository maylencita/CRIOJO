package fr.emn.criojo.dsl

import fr.emn.criojo.core.engine.{CriojoGuard,AndGuard,OrGuard}


/**
 * A wrapper for a relation that is also a variable
 */
//class ChamChannel(val delegate:Relation,val function:(List[Term]) => Molecule)
//  extends VarChannel(delegate.name) with Applicable{
//
//  this.relation = delegate
//
//  def this(del:Relation) = {
//    this(del,(terms:List[Term])=> {
//      val atom = new CrjAtom(del.name, terms.toList)
//      atom.relation = del
//      atom
//    })
//  }
//}


trait ChamGuard extends CriojoGuard {
  def && (guard:CriojoGuard):ChamGuard = new AndGuard(this, guard) with ChamGuard

  def || (guard:CriojoGuard):ChamGuard = new OrGuard(this, guard) with ChamGuard
}