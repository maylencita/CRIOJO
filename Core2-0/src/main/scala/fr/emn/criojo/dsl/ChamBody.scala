package fr.emn.criojo.dsl

import fr.emn.criojo.core.engine._
import impure.NativeRelation
import fr.emn.criojo.core.model._
import fr.emn.criojo.core.engine.NotGuard
import fr.emn.criojo.core.engine.PresenceGuard
import fr.emn.criojo.core.engine.AbsGuard
import fr.emn.criojo.core.engine.ExistsGuard
import relation.ChannelLocation

/**
* Created with IntelliJ IDEA.
* User: mayleen
* Date: 11/23/12
* Time: 6:16 PM
* To change this template use File | Settings | File Templates.
*/

abstract class ChamBody{
  this: Cham =>

  type RuleDef = RuleFactory => Rule

  protected trait Channel extends ChannelLocation

  def rules(ruleDefs:(RuleFactory => Rule)*) { initRules(ruleDefs.toList) }

  /**
   * Returns a NativeRelation, whose function returns a value
   * @param f
   * @return
   */
  def NativeFun(f:(List[Term]) => List[Atom]):NativeRelation = {
    val natRel = new NativeRelation("NativeRel@" +
      ChamBody.getNativeRelInstanceNum, f)
    natRel
  }

  /**
   * Returns a NativeRelation, whose function does not return a value
   * @param f
   * @return
   */
  def NativeRel(f:(List[Term]) => Unit):NativeRelation = {
    val f2 = (terms:List[Term]) => {f(terms); List[Atom]()}
    val natRel = new NativeRelation("NativeRel@" +
      ChamBody.getNativeRelInstanceNum, f2)
    natRel
  }

  /**
   * Returns a variable suitable for type T
   * @tparam T
   */
  def Var[T <: Expression](implicit m:Manifest[T]):TypedVar[T] = new TypedVar[T]("Var@" + ChamBody.getInstanceNum) {
    override def toString = name
  }

  def Abs(atoms:Atom*):ChamGuard =  new AbsGuard(atoms.toList) with ChamGuard

  def Ex(x:Variable, g:ChamGuard):ChamGuard = new ExistsGuard(g,x) with ChamGuard

  def Ex(vlst:List[Variable], g:ChamGuard):ChamGuard = vlst match{
    case Nil => g
    case h::tl => Ex(h, Ex(tl, g))
  }

  def ForAll(x:Variable, g:ChamGuard):ChamGuard = new ForAllGuard(g,x) with ChamGuard

  def ForAll(vlst:List[Variable], g:ChamGuard):ChamGuard = vlst match{
    case Nil => g
    case h::tl => ForAll(h, ForAll(tl, g))
  }

  def Prs(atoms:Atom*):ChamGuard =  new PresenceGuard(atoms.toList) with ChamGuard

  def Not(g:ChamGuard):ChamGuard = new NotGuard(g) with ChamGuard

  def introduceMolecule(molecule:Molecule){
    molecule.toList.foreach(introduceAtom(_))
  }

  class RuleBody(val conj:Molecule, val guard:Guard = EmptyGuard){}

  implicit def atomToMolecule(atom:Atom):Molecule = Molecule(List(atom))
  implicit def moleculeToRuleBody(mol:Molecule):RuleBody = new RuleBody(mol)
  //implicit def mol2atom(mol:Molecule):Atom = mol.head
}

object ChamBody {
  private var nativeRelInstanceNum = 0
  private var instanceNum = 0

  def getNativeRelInstanceNum() = {
    nativeRelInstanceNum += 1
    nativeRelInstanceNum
  }

  def getInstanceNum = {
    instanceNum += 1
    instanceNum
  }
}
