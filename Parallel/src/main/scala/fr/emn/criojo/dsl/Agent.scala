package fr.emn.criojo.dsl

import fr.emn.criojo.core._
import datatype.{Term, Variable}
import impur.NativeRelation
import fr.emn.criojo.parallel._
import fr.emn.criojo.{ext, lang}
import lang.Molecule
import ext.expression.Relation.constructor.LocalRelation

/**
* Created with IntelliJ IDEA.
* User: mayleen
* Date: 11/23/12
* Time: 6:16 PM
* To change this template use File | Settings | File Templates.
*/

object Agent {
  private var nativeRelInstanceNum = 0

  def getNativeRelInstanceNum() = {
    nativeRelInstanceNum += 1
    nativeRelInstanceNum
  }
}

class Agent extends Dispatcher{
  import fr.emn.criojo.lang.ChamGuard

  type RuleDef = RuleFactory => Rule

  def rules(ruleDefs:(RuleFactory => Rule)*) { initRules(ruleDefs.toList) }

  def NativeRel(f:(List[Term]) => Unit) = {
    val natRel = new NativeRelation("NativeRel@" +
      Agent.getNativeRelInstanceNum, f)
    addRelation(natRel)
    natRel
  }

  def Abs(atoms:Atom*):ChamGuard =  new AbsGuard(atoms.toList) with ChamGuard

  def Ex(x:Variable,g:ChamGuard):ChamGuard =  new ExistsGuard(g,x) with ChamGuard

  def Prs(atoms:Atom*):ChamGuard =  new PresenceGuard(atoms.toList) with ChamGuard

  def Not(g:ChamGuard):ChamGuard = new NotGuard(g) with ChamGuard

  def introduceMolecule(molecule:Molecule){
    molecule.toList.foreach(introduceAtom(_))
  }

  class RuleBody(val conj:Molecule, val guard:Guard = EmptyGuard){}

  implicit def moleculeToRuleBody(mol:Molecule):RuleBody = new RuleBody(mol)
  implicit def mol2atom(mol:Molecule):Atom = mol.head
}
