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

  def createRelation(n: String): Relation
  def createRelation(n: String, f:(List[Term]=>Molecule)): Relation

  def createChannel(n: String): Relation
  def createChannel(n: String, f:(List[Term]=>Molecule)): Relation

  def createVariable(): Variable
}

trait StatefulFactory {
  var index: Int = 0

  def createRelation(n: String): Relation = new ApplicableRel(n, (terms: List[Term]) => new CrjAtom(n, terms.toList))
  def createRelation(n: String, f:(List[Term]=>Molecule)): Relation = new ApplicableRel(n, f)

  def createChannel(n: String): Relation = new ApplicableRel(n, (terms: List[Term]) => new CrjAtom(n, terms.toList))
  def createChannel(n: String, f:(List[Term]=>Molecule)): Relation = new ApplicableRel(n, f)

  def createVariable(): Variable = {
    index += 1; new Variable("x" + index)
  }
}