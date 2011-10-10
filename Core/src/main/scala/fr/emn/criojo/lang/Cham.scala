package fr.emn.criojo.lang

import fr.emn.criojo.core._
import fr.emn.criojo.core.Criojo._
import fr.emn.criojo.util.Logger._

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 30/09/11
* Time: 14:59
*/
class Cham extends CHAM{

  val T = Rel("true") //new Top(){ def apply(vlst:Variable*):Molecule = new CrjAtom(name, vars.toList) }
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
    new CriojoGuard(new Top(atom.vars), List((new CrjAtom(Top.relName, atom.vars) & atom) --> F()), this.relations)

  def when(head:Molecule)(body: RuleBody){
    if(body.guard == EmptyGuard)
      initRule(head --> body.conj)
    else
      initRule(head --> (body.guard, body.conj))
  }

//  implicit def atomToConjunction(a:Atom):Conjunction = new &:(a, Empty)
//  implicit def atomToRuleBody(a:Atom):RuleBody = new RuleBody(new &:(a,Empty))
  implicit def moleculeToRuleBody(mol:Molecule):RuleBody = new RuleBody(mol)
  implicit def mol2atom(mol:Molecule):Atom = mol.head

  // A variable of type Relation
  case class VarR(override val name:String) extends RelVariable(name){
    def apply(vlst:Variable*):CrjAtom = {
      val at = new CrjAtom(this.name, vlst.toList)
      at.relation = this.relation
      at
    }
  }

  case class Tok() extends LocalRelation("$X"+index,true){
    index += 1
    def apply(vars:Variable*) = new CrjAtom(name, vars.toList)
  }

  case class Rel(n:String) extends LocalRelation(n, true) {
    addRelation(this)

//    def apply(vars:Variable*):Atom = new Atom(name, vars.toList)
    def apply(vars:Variable*):Molecule = {

      new CrjAtom(name, vars.toList)
    }

    override def equals(that:Any):Boolean = that match{
      case r:Relation => this.name == r.name
      case _ => false
    }
  }

  class RuleBody(val conj:Molecule, val guard:Guard = EmptyGuard){

  }

}
