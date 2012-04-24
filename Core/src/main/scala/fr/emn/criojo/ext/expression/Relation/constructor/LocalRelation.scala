package fr.emn.criojo.ext.expression.Relation.constructor

import fr.emn.criojo.ext.expression.Relation.Relation
import java.util.UUID
import fr.emn.criojo.lang.Molecule
import fr.emn.criojo.core.datatype.Term
import fr.emn.criojo.core.Atom

case class LocalRelation(name: String) extends Relation {
  def this() = this("LocalRelation"+UUID.randomUUID().toString)

  def apply(vars:Term*):Molecule = Molecule(new Atom(this, vars.toList) :: Nil)
}
