package fr.emn.criojo.lang

import fr.emn.criojo.core._

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 30/09/11
* Time: 14:59
*/
class Cham extends StatefulEngine with StatefulFactory {

  type MoleculeBuilder = (Variable*) => Molecule
  type RuleDef = RuleFactory => Rule

  val T = createRelation("true")
  val F = createRelation("false")

  var ruleDefList = List[RuleDef]()

//  def Var:Variable = {
//    index += 1
//    new Variable("x"+index)
//  }

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
//    initRule(Empty --> Abs(AxiomTok()) ?: (fact & AxiomTok()))
    ruleDefList :+= (Empty --> Abs(AxiomTok()) ?: (fact & AxiomTok()))
  }

  def facts(flst:Molecule*){
    flst.toList.foreach{fact => axiom(fact)}
  }

//  def createAndAddRelation:ApplicableRel = createAndAddRelation("@R"+nextIndex)

  def createAndAddRelation(n:String): ApplicableRel = {
    val r = createRelation(n,
      (terms:List[Term])=>new CrjAtom(n, terms.toList)).asInstanceOf[ApplicableRel]
    addRelation(r)
    r
  }

  def createAndAddRelation(relName:String, f:(List[Term]=>Molecule)): ApplicableRel = {
    val r = createRelation(relName, f).asInstanceOf[ApplicableRel]
    addRelation(r)
    r
  }

  def introduceMolecule(molecule:Molecule){
    molecule.toList.foreach(introduceAtom(_))
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
}

trait ChamGuard extends CriojoGuard {
  def && (guard:CriojoGuard):ChamGuard = new AndGuard(this, guard) with ChamGuard

  def || (guard:CriojoGuard):ChamGuard = new OrGuard(this, guard) with ChamGuard
}