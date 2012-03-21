package fr.emn.criojo.lang

import fr.emn.criojo.core._
import fr.emn.criojo.core.Valuation
import fr.emn.criojo.ext._
import collection.mutable.Buffer
import expressions.VarExpression

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 30/09/11
* Time: 14:59
*/
class Cham extends
StatefulEngine
{

  type MoleculeBuilder = (Variable*) => Molecule
  type RuleDef = RuleFactory => Rule

  val T = Rel("true")
  val F = Rel("false")

  var ruleDefList = List[RuleDef]()

  def Var:VarExpression = {
    index += 1
    new VarExpression("x"+index)
  }

  def rules(ruleDefs:(RuleFactory => Rule)*) { initRules(ruleDefs.toList) }

  def NativeRelation(n:String)(f:(Atom,Solution) => Unit)= {
    val natRel = new NativeRelation(n,this.solution,f)
    addRelation(natRel)
    natRel
  }

  def If(guard: Guard)(body: => Molecule):RuleBody = {
    new RuleBody(body, guard)
  }

  def Abs(atoms:Atom*):ChamGuard =  new AbsGuard(atoms.toList) with ChamGuard

  def Ex(atoms:Atom*):ChamGuard =  new ExistGuard(atoms.toList) with ChamGuard

  def Prs(atoms:Atom*):ChamGuard =  new PresenceGuard(atoms.toList) with ChamGuard

  def when(head:Molecule)(body: RuleBody):RuleDef = {
    val ruleDef =
      if(body.guard == EmptyGuard)
        head --> body.conj
      else
        head --> (body.guard, body.conj)

    ruleDefList :+= ruleDef
    ruleDef
  }

  def seq(ruleDefs:RuleDef*){
    var prevTok:Tok = null
    ruleDefs.foreach{rdf =>
      ruleDefList = ruleDefList.filterNot(_ == rdf)
      val nextTok = Tok()
      addRelation(new LocalRelation(nextTok.name))
      val rule = rdf(this)
      val newrule =
        if(prevTok == null)
          createRule(rule.head, rule.body :+ nextTok(), rule.guard, rule.scope.toSet)
        else
          createRule(rule.head :+ prevTok(), rule.body :+ nextTok(), rule.guard, rule.scope.toSet)

      processRuleBody(processRuleHead(newrule), newrule)
      addRule(newrule)
      prevTok = nextTok
    }
  }

  def Do(ruleDefs:RuleDef*){

  }

  def axiom(fact:Molecule){
    val AxiomTok = Tok()
    ruleDefList :+= (Empty --> Abs(AxiomTok()) ?: (fact & AxiomTok()))
  }

  def facts(flst:Molecule*){
    flst.toList.foreach{fact => axiom(fact)}
  }

  def Rel:ApplicableRel = Rel("@R"+nextIndex)

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

  def introduceMolecule(molecule:Molecule){
    molecule.toList.foreach(introduceAtom(_))
  }

  def nextIndex:Int = {
    index += 1
    index
  }

  override def executeRules(){
    ruleDefList.foreach(initRule(_))
    super.executeRules()
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

trait ChamGuard extends CriojoGuard {
  def && (guard:CriojoGuard):ChamGuard = new AndGuard(this, guard) with ChamGuard

  def || (guard:CriojoGuard):ChamGuard = new OrGuard(this, guard) with ChamGuard
}