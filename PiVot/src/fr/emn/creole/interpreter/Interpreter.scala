package fr.emn.creole.interpreter


import fr.emn.creole.parser.tree._

import fr.emn.creole.core._

import scala.collection.mutable.HashMap

import org.antlr.runtime.Token;



class Interpreter(tokens:CHRTreeTokens) {
	import tokens._

  val machine:VirtualMachine = new VirtualMachine() 

  def runScript(tree: ^){
    process(tree)
    machine.start
  }

  def process(t: ^){
    t match{
      case ^(SCRIPT, s) => process(s.head)
      case ^(BAR, s1::s2::Nil) => process(s1) ; process(s2)   
      case ^(SEMI, s1::s2::Nil) => process(s1) ; process(s2)
      case ^(BANG, s) => process(s.head)
      case ^(RULE, r) => machine.addRule(processRule(r))
      case _ => println("Gna! not a valid script!")
    }
  }

  def processRule(r: List[^]):Rule ={
    var head:List[Atom] = List()
    var body:List[Atom] = List()
    var scope:List[Variable] = List()

    processRuleIter(r)

    def processRuleIter(lst: List[^]){
      lst match{
        case ^(HEAD, alst) :: res => head = alst.map(a => processAtom(a)) ; processRuleIter(res)
        case ^(BODY, alst) :: res => body = alst.map(a => processAtom(a)) ; processRuleIter(res)
        case ^(NU, vlst) :: res => scope = vlst.map(v => processVar(v)) ; processRuleIter(res)
        case Nil => //finished
        case _ => println("invalid rule"); null  //TODO manage invalid rule error
      }
    }

    def processAtom(atom: ^): Atom = atom match{
      case ^(TRUE, _) => new Atom("true", List())
      case ^(ATOM, name :: ^(VARS, vlist) :: Nil) => new Atom(name.getText, vlist.map(v => processVar(v)))
      case ^(ATOM, name :: Nil) => new Atom(name.getText, List())
      case _ => println("invalid atom"); null  //TODO manage invalid atom error
    }

    def processVar(variable: ^): Variable = variable match{
      case ^(V_ID, _) => new Variable(variable.getText)
      case ^(R_ID, _) => new RelVariable(variable.getText)
      case _ => println("invalid var"); null //TODO manage invalid variable error
    }

    val rule = new Rule(head,body)
    if (!scope.isEmpty)
      rule.setScope(scope)

    println("Got a rule: " + rule) 

    rule
    
  }
}

