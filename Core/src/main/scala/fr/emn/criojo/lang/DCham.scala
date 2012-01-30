package fr.emn.criojo.lang

import fr.emn.criojo.core._

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 08/11/11
 * Time: 15:16
 */
trait DCham extends SimpleEngine{

//  private var seqFlag = false
//  private var nextTok:Tok = null
//  private var prevTok:Tok = null
//
//  class GuardedCommand(guard:Guard, cmmd:Statement*)
//
//
////  class If(set:GuardedCommand*) extends Statement
//
////  class Do(set:GuardedCommand*) extends Statement
//
////  class Rul(rule:Rule) extends Statement
//
////s := if{ gStmt (|| gStmt)* }  | do{ gStmt (|| gStmt)*} | rule
////gStmt := g --> Seq
////Seq := s;s | s
////if{T() --> Seq} ~= seq{Seq}
//// Statement: => List[RuleDef]
//
//  type RuleDef = RuleFactory => Rule
//
//  trait Statement{
//    def definitions:List[RuleDef]
//  }
//
//  class RuleStatement(head:Molecule,body: RuleBody) extends Statement{
//    def definitions = List(head --> (body.guard, body.conj))
//  }
//
//  def seq(stmts:List[Statement]):Statement = {
//    val tok1 = Tok()
//    stmts(0).append(tok1)
//    seq(stmts.tail)
//    fact(tok1)
//  }
//
//  def when(head:Molecule)(body: RuleBody):Statement = {
//    new RuleStatement(head,body)
//  }
//
//  def fact(f:Molecule){
//    val axiomTok = Tok()
//    initRule(Empty --> Abs(axiomTok()) ?: (f & axiomTok()))
//  }
//
////  def when(head:Molecule)(body: RuleBody){
////    val rdf = head --> (body.guard, body.conj)
////    val rule = rdf(this)
////    val newrule =
////      if(seqFlag){
////        nextTok = Tok()
////        addRelation(new LocalRelation(nextTok.name))
////
////        val nurule = createRule(
////          if(prevTok == null) rule.head else rule.head :+ prevTok(),
////          rule.body :+ nextTok(), rule.guard, rule.scope
////        )
////        prevTok = nextTok
////        nurule
////      }else{
////        rule
////      }
////
////    processRuleBody(processRuleHead(newrule), newrule)
////    addRule(newrule)
////  }
//
//  case class Tok() extends LocalRelation("$X"+index,true){
//    index += 1
//    def apply(vars:Term*) = new CrjAtom(name, vars.toList)
//  }
}

//class RuleBody(val conj:Molecule, val guard:Guard = EmptyGuard)

