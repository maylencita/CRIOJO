package fr.emn.criojo.interpreter


import fr.emn.criojo.parser.tree._

import fr.emn.criojo._, core._ , ext._
import fr.emn.criojo.ext._
import fr.emn.criojo.util._
import Logger._

import scala.collection.mutable.HashMap

import org.antlr.runtime.Token;



class Interpreter(machine:VirtualMachine, tokens:CHRTreeTokens) {

  def this(tokens:CHRTreeTokens)={
    this (new LocalVM, tokens)
  }

	import tokens._

//  val machine:VirtualMachine = new VirtualMachine()

  def runScript(tree: ^){
    process(tree)
    Logger.log("[Interpreter.runScript] script: \n" + machine)
//    machine.execute
//    Logger.log(this.getClass, "runScript","finished with solution: " + machine.solution)
  }

  def process(t: ^){
    t match{
      case ^(PROCESS, declaration :: script :: Nil) => processDeclaration(declaration); process(script)
      case ^(SCRIPT, rlst) => rlst.foreach(r => process(r))
      case ^(BAR, s1::s2::Nil) => process(s1) ; process(s2)   
      case ^(SEMI, s1::s2::Nil) => process(s1) ; process(s2)
      case ^(BANG, s) => process(s.head)
      case ^(RULE, r) => processRule(r,machine)
      case _ => Logger.log(this.getClass, "process","Gna! not a valid script!")
    }
  }

  def processDeclaration(d: ^){
    import fr.emn.criojo.ext._
    def processRelList(public:Boolean, lst: List[^]){
      lst match{
        case List() => //skip
        case (h @ ^(R_ID, _)) :: res =>
          machine.addRelation(machine.newLocalRelation(h.getText, public))
          processRelList(public,res)
        case ^(MULTI, rid :: _) :: res =>
          machine.addRelation(machine.newLocalRelation(rid.getText, public))
          processRelList(public,res)
        case ^(EMPTYLIST, _) :: _ => //SKIP
        case _ =>
      }
    }

    def processRequired(lst: List[^]):Unit = lst match{
      case List() => //skip
      case ^(ARROBAS, rid :: url :: _) :: res =>
        machine.addRelation(machine.newRemoteRelation(rid.getText, url.getText.replaceAll("\"","")))
        processRequired(res)
      case (h @ ^(R_ID, _)) :: res =>
        machine.newRemoteRelation(h.getText, "")
        processRequired(res)
      case _ => 
    }

    def processDecl(lst: List[^]):Unit = lst match{
      case ^(PUBLIC, pulst) :: rest => processRelList(true, pulst); processDecl(rest)
      case ^(PRIVATE, prlst) :: rest => processRelList(false,prlst); processDecl(rest)
      case ^(REQUIRED, rqlst) :: rest => processRequired(rqlst); processDecl(rest)
      case _ =>
    }

    d match{
//    case ^(DECLARATION, ^(PUBLIC, pulst) :: ^(PRIVATE, prlst) :: ^(REQUIRED, rqlst) :: Nil) =>
      case ^(DECLARATION, decl) => processDecl(decl)
//      processRelList(true, pulst)
//      processRelList(false,prlst)
//      processRequired(rqlst)
    case _ =>
    }
  }

  def processRule(r: ^, mv:CHAM=machine):Rule = r match{
    case ^(RULE, rdef) => processRule(rdef,mv)
    case _ => null
  }

  def processRule(r: List[^], mv:CHAM):Rule ={
    var head:List[Atom] = List()
    var body:List[Atom] = List()
    var scope:List[Variable] = List()
    var guard = new ExtendedGuard(mv.asInstanceOf[ExtendedCHAM]) //new Guard

    processRuleIter(r)

    def processRuleIter(lst: List[^]){
      lst match{
        case ^(HEAD, alst) :: res => head = processHead(alst) ; processRuleIter(res)
        case ^(BODY, alst) :: res => body = alst.map(a => processAtom(a)) ; processRuleIter(res)
        case ^(NU, vlst) :: res => scope = vlst.map(v => processVar(v)) ; processRuleIter(res)
        case ^(GUARD, glst) :: res =>
          //(glst.map(g => processRule(g))) ;
          glst.map(g=>processRule(g,guard))
          processRuleIter(res)
        case Nil => //finished
        case _ => Logger.log(Logger.WARNING, this.getClass, "processRuleIter","invalid rule part: " + lst)//; null  //TODO manage invalid rule error
      }
    }

    def processHead(lst: List[^]):List[Atom] = lst match{
      case ^(EMPTY,_) :: _ => List()
      case _ => lst.map(a => processAtom(a))
    }
    
    def processAtom(atom: ^): Atom = atom match{
      case ^(TRUE, vlst) => new Top(vlst map (processVar(_)))
      case ^(FALSE, _) => False
      case ^(ATOM, name :: ^(VARS, vlist) :: Nil) => new Atom(name.getText, vlist.map(v => processVar(v)))
      case ^(ATOM, name :: Nil) => new Atom(name.getText, List())
      case ^(INT_ATOM, name :: ^(VARS, vlist) :: Nil) =>
        new IntAtom(name.getText.toInt, processVar(vlist(0)))
      case ^(STR_ATOM, name :: ^(VARS, vlist) :: Nil) =>
        new StringAtom(name.getText.replaceAll("\"",""), processVar(vlist(0)))
      case ^(NULL_ATOM, name :: ^(VARS, vlist) :: Nil) =>
        new NullAtom(processVar(vlist(0)))
      case _ => Logger.log(this.getClass, "processAtom","invalid atom"); null  //TODO manage invalid atom error
    }

    def processVar(variable: ^): Variable = variable match{
      case ^(V_ID, _) => new Variable(variable.getText)
      case ^(R_ID, _) => new RelVariable(variable.getText)
      case _ => Logger.log(this.getClass, "processVar","invalid var " + variable); null //TODO manage invalid variable error
    }

//    val rule = new Rule(head,body,guard)
    val rule = mv.newRule(head,body,guard)
    if (!scope.isEmpty)
      rule.setScope(scope)

    rule
    
  }
}

