package fr.emn.criojo.lang

import fr.emn.criojo.core._
import factory.{RelationFactory}
import fr.emn.criojo.ext.expression.ScalaString.VarScalaString
import fr.emn.criojo.ext.expression.ScalaInt.VarScalaInt
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 30/09/11
* Time: 14:59
*/
class Cham extends StatefulEngine //with DefaultFactory
{
  //TODO change Cham where used
  this:RelationFactory =>

  type RuleDef = RuleFactory => Rule

//  def Var:VarExpression = {
//    index += 1
//    new VarExpression("x"+index)
//  }

  def rules(ruleDefs:(RuleFactory => Rule)*) { initRules(ruleDefs.toList) }

  //TODO replaced by NativeRel
  def NativeRelation(n:String)(f:(Atom,Solution) => Unit)= {
    val natRel = new NativeRelation(n,this.solution,f)
//    val natRel = createNativeRelation(n,f)
    addRelation(natRel)
    natRel
   }

//  def NativeRel(f:(List[Term]) => Unit) = {
//    val natRel = createNativeRelation("NR@"+nextIndex,f)
//    addRelation(natRel)
//    new VarRelationWrapper(natRel)
//  }

  def If(guard: Guard)(body: => Molecule):RuleBody = {
    new RuleBody(body, guard)
  }

  def Abs(atoms:Atom*):ChamGuard =  new AbsGuard(atoms.toList) with ChamGuard

  def Ex(x:Variable,g:ChamGuard):ChamGuard =  new ExistsGuard(g,x) with ChamGuard

  def Prs(atoms:Atom*):ChamGuard =  new PresenceGuard(atoms.toList) with ChamGuard

  def Not(g:ChamGuard):ChamGuard = new NotGuard(g) with ChamGuard

  def when(head:Molecule)(body: RuleBody):RuleDef = {
    val ruleDef =
      if(body.guard == EmptyGuard)
        head --> body.conj
      else
        head --> (body.guard, body.conj)

    ruleDef
  }

//  def axiom(fact:Molecule){
//    val AxiomTok = Tok()
//  }
//
//  def facts(flst:Molecule*){
//    flst.toList.foreach{fact => axiom(fact)}
//  }

  def introduceMolecule(molecule:Molecule){
    molecule.toList.foreach(introduceAtom(_))
  }

  def nextIndex:Int = {
    index += 1
    index
  }

  implicit def moleculeToRuleBody(mol:Molecule):RuleBody = new RuleBody(mol)
  implicit def mol2atom(mol:Molecule):Atom = mol.head
//  implicit def relToVar(r:Relation):VarChannel = {
//    val vr = new VarChannel(r.name)
//    vr.relation = r
//    vr
//  }

  // A variable of type Relation
//  case class VarR(override val name:String) extends VarChannel(name){
//
//    def apply(tlst:Term*):CrjAtom = {
//      val at = new CrjAtom(this.name, tlst.toList)
//      at.relation = this.relation
//      at
//    }
//  }

//  case class Tok() extends LocalRelation("$X"+index,true){
//    index += 1
//    def apply(vars:Term*) = new CrjAtom(name, vars.toList)
//  }

//  case class Fun(name:String){
//    def apply(patterns:Term*) = new Function(name, patterns.toList)
//  }

  class RuleBody(val conj:Molecule, val guard:Guard = EmptyGuard){}

  def VarString:VarScalaString = {
    new VarScalaString("VarScalaString"+nextIndex)
  }

  def VarInt:VarScalaInt = {
    new VarScalaInt("VarInt"+nextIndex)
  }
}

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

/**
 * An object of type Applicable applies a function to one or more Terms to obtain a Molecule
 */
trait Applicable/*(name:String,f:List[Term] => Molecule)* extends LocalRelation(name, true)*/{
  def function:(List[Term]) => Molecule
  def apply(vars:Term*):Molecule = function(vars.toList)
}

trait ChamGuard extends CriojoGuard {
  def && (guard:CriojoGuard):ChamGuard = new AndGuard(this, guard) with ChamGuard

  def || (guard:CriojoGuard):ChamGuard = new OrGuard(this, guard) with ChamGuard
}