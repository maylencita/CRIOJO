package fr.emn.criojo.lang

import fr.emn.criojo.core._
import fr.emn.criojo.ext._

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 30/09/11
* Time: 14:59
*/
class Cham extends CHAM{
  type MoleculeBuilder = (Variable*) => Molecule

  val T = Rel("true")
  val F = Rel("false")

  def Var:Variable = {
    index += 1
    new Variable("x"+index)
  }

  def rules(ruleDefs:(RuleFactory => Rule)*) { initRules(ruleDefs.toList) }

  def NativeRelation(n:String)(f:(Atom,Solution) => Unit)= {
    val natRel = new NativeRelation(n,this.solution,f)
    addRelation(natRel)
    natRel
  }

  def guard(sttr:Atom, ruleDefs:(RuleFactory => Rule)*):Guard = new CriojoGuard(sttr, ruleDefs.toList, this.relations)

  def If(sttr:Atom, ruleDefs:(RuleFactory => Rule)*)(body: => Molecule):RuleBody = {
    val guard = new CriojoGuard(sttr, ruleDefs.toList, this.relations)
    new RuleBody(body, guard)
  }

  def If(guard: Guard)(body: => Molecule):RuleBody = {
    new RuleBody(body, guard)
  }

  def Abs(atom:Atom):Guard =
    new CriojoGuard(new Top(atom.terms), List((atom &: new CrjAtom(Top.relName, atom.terms)) --> F()), this.relations)

  def when(head:Molecule)(body: RuleBody){
    if(body.guard == EmptyGuard)
      initRule(head --> body.conj)
    else
      initRule(head --> (body.guard, body.conj))
  }

  def Rel(n:String): ApplicableRel = {
    val r = new ApplicableRel(n,
      (terms:List[Term])=>new CrjAtom(n, terms.toList))
    addRelation(r)
    r
  }

  def Rel(relName:String, f:(List[Term]=>Molecule)): ApplicableRel = {
    val r = new ApplicableRel(relName, f)
    addRelation(r)
    r
  }

  implicit def moleculeToRuleBody(mol:Molecule):RuleBody = new RuleBody(mol)
  implicit def mol2atom(mol:Molecule):Atom = mol.head
  implicit def relToVar(r:Relation):RelVariable = {
    val vr = new RelVariable(r.name)
    vr.relation = r
    vr
  }

  // A variable of type Relation
  case class VarR(override val name:String) extends RelVariable(name){
    def apply(tlst:Term*):CrjAtom = {
      val at = new CrjAtom(this.name, tlst.toList)
      at.relation = this.relation
      at
    }
  }

  case class Tok() extends LocalRelation("$X"+index,true){
    index += 1
    def apply(vars:Term*) = new CrjAtom(name, vars.toList)
  }

  case class Fun(name:String){
    def apply(terms:Term*) = new Function(name, terms.toList)
  }

  class RuleBody(val conj:Molecule, val guard:Guard = EmptyGuard){}

  class ApplicableRel(name:String,f:List[Term] => Molecule) extends LocalRelation(name, true){
    def apply(vars:Term*):Molecule = f(vars.toList)
  }
}
