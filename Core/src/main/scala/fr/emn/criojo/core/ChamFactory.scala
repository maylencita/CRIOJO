package fr.emn.criojo.core

import fr.emn.criojo.lang.{Molecule, CrjAtom}


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 3/22/12
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */

trait ChamFactory {

  def Rel(n: String): Relation
  def Rel(n: String, f:(List[Term]=>Molecule)): Relation

  def Chan(n: String): Relation
  def Chan(n: String, f:(List[Term]=>Molecule)): Relation

  def Var(): Variable
}

trait StatefulFactory extends ChamFactory {
  var index: Int = 0

  def Rel(n: String): Relation = new ApplicableRel(n, (terms: List[Term]) => new CrjAtom(n, terms.toList))
  def Rel(n: String, f:(List[Term]=>Molecule)): Relation = new ApplicableRel(n, f)

  def Chan(n: String): Relation = new ApplicableRel(n, (terms: List[Term]) => new CrjAtom(n, terms.toList))
  def Chan(n: String, f:(List[Term]=>Molecule)): Relation = new ApplicableRel(n, f)

  def Var(): Variable = {
    index += 1; new Variable("x" + index)
  }
}