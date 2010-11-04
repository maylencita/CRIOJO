package fr.emn.criojo.interpreter

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 23, 2010
 * Time: 3:47:47 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.parser.tree._
import fr.emn.criojo.util.Logger

import org.antlr.runtime.Token

class Translator(tokens:CHRTreeTokens){
  import tokens._

  var declaration: ^ = _
  var variableIndex: Int = _
  
  def translate(tree: ^): ^ = {
    process(tree)
  }

  def process(tree: ^): ^ = tree match{
      case ^(PROCESS, decl :: script :: Nil) =>
        this.declaration = decl
        val newScript = ^(SCRIPT, processScript(script))
        ^(PROCESS, declaration :: newScript :: Nil)
      case ^(SCRIPT, _) => ^(SCRIPT, processScript(tree))
      case ^(RULE, r) => processRule(r)
      case _ => Logger.log(this.getClass, "process","Gna! not a valid script!"); null
  }

  def processScript(s: ^):List[^] = s match{
    case ^(SCRIPT, scrpt::_) => processScript(scrpt)
    case ^(RULE, r) => processRule(r) :: Nil
    case ^(BAR, s1 :: s2 :: Nil) => processScript(s1) ::: processScript(s2)
    case ^(SEMI, s1::s2::Nil) => processSequence(processScript(s1),processScript(s2))
    case ^(BANG, s :: Nil) => processBang(processScript(s))
    case _ => List()
  }

  def newTokenAtom = {
    val x = "$X" + getVariableIndex
    addDeclaration(x)
    ^(ATOM, ^(new CHRToken(R_ID.getType, x), Nil)::Nil)
  }

  def processBang(rlst: List[^]):List[^] = {

    rlst.map{
      case ^(RULE, rdef) =>
        val token = newTokenAtom
        var hasGuard = false
        ^(RULE, rdef.map{
          case ^(GUARD, glst) => hasGuard=true; ^(GUARD, processAbs(token::Nil)::glst)
          case ^(BODY, blst) => ^(BODY, token :: blst)
          case ^(HEAD, h) => ^(HEAD, h)
          case ^(NU, vlst) => ^(NU, vlst)
          case _ => null
        }:::(if(!hasGuard) ^(GUARD, processAbs(token)::Nil)::Nil else Nil))
      case _ => null
    }
  }

  def processSequence(s1: List[^], s2: List[^]): List[^] = {
    val x1 = "$X"+getVariableIndex
    val x2 = "$X"+getVariableIndex
    val x3 = "$X"+getVariableIndex
    addDeclaration(x1); addDeclaration(x2); addDeclaration(x3)
    def atom(nomAtom:String) = ^(ATOM, ^(new CHRToken(R_ID.getType, nomAtom), Nil)::Nil)

    s1.map(s=>addToken(x1, s)) :::
    s2.map(s=>addToken(x2, s)) :::
    (^(RULE, ^(HEAD, atom(x1)::Nil) :: ^(GUARD, s1.map(processCad(_))) :: ^(BODY, atom(x2)::Nil) :: Nil) ::
    ^(RULE, ^(HEAD, ^(EMPTY,Nil)::Nil) :: ^(GUARD, processAbs(atom(x3)::Nil)::Nil) :: ^(BODY, atom(x1)::atom(x3)::Nil) :: Nil) :: Nil)
  }

  def addDeclaration(newRelName:String){
    def newRel = ^(new CHRToken(R_ID.getType, newRelName), Nil)
    this.declaration match{
      case ^(DECLARATION, public :: ^(PRIVATE, plst) :: _) =>
        this.declaration = ^(DECLARATION, public :: ^(PRIVATE, newRel::plst) :: Nil)
      case _ => //skip
    }
  }

  def addToken(tok: String, s: ^): ^ = {
    def atok = ^(ATOM, ^(new CHRToken(R_ID.getType, tok), Nil)::Nil)
    s match{
    case ^(RULE, rdef) => ^(RULE, rdef.map{
        rpart => rpart match{
          case ^(HEAD, h) => h match{
            case ^(EMPTY, _) :: Nil => ^(HEAD, ^(EMPTY, Nil)::Nil)
            case _ => ^(HEAD, atok::processAtoms(h))
          }
          case ^(BODY, b) => ^(BODY, atok::processAtoms(b))
          case _ => rpart
        }
      })
    case _ => null
  }
  }

  def processRule(r: ^): ^ = r match{
    case ^(RULE, rdef) => processRule(rdef)
    case _ => null
  }

  def processRule(r: List[^]): ^ = {

    def processRuleIter(lst: List[^], acum: List[^]): List[^] = lst match{
        case Nil => acum
        case ^(GUARD, glst) :: res => processRuleIter(res, processGuard(glst) ::: acum)
        case head :: res => processRuleIter(res, processRulePart(head) :: acum)
    }

    def processGuard(lst: List[^]): List[^] = lst match{
      case ^(ABS, alst) :: _ => ^(GUARD, processAbs(processAtoms(alst)) :: Nil) :: Nil
      case ^(EQ, v1::v2::_) :: _  => ^(GUARD, processEq(v1,v2)) :: Nil
      case _ => ^(GUARD, lst) :: Nil //lst
    }

    def processRulePart(rp: ^): ^ = rp match{
      case ^(HEAD, h) => ^(HEAD, processAtoms(h))
      case ^(BODY, b) => ^(BODY, processAtoms(b)) 
      case _ => rp
    }
    ^(RULE, processRuleIter(r, List()))

  }

  def processEq(v1: ^ , v2: ^):List[^] = {
    val s = ^(new CHRToken(V_ID.getType,"s"), Nil )
    val t = ^(new CHRToken(R_ID.getType,"true"), Nil )
    val nu = ^(NU, s::Nil)
    val x = newTokenAtom
    val guard = ^(GUARD, processAbs(x::Nil)::Nil)

    ^(RULE, ^(HEAD, ^(TRUE, v1::v2::Nil)::Nil) :: nu :: guard :: 
            ^(BODY, x :: newAtom("Eq_ask", s::v1::v2::t::Nil) :: Nil) :: Nil) :: Nil
  }

  def processAbs(a: ^): ^ = processAbs(List(a))

  def processAbs(alst: List[^]): ^ = {
    val falseAtom = ^(new CHRToken(FALSE),Nil)
    val nualst = processAtoms(alst)
    ^(RULE, ^(HEAD, ^(TRUE,Nil)::nualst) :: ^(BODY, falseAtom::Nil) :: Nil)
  }

  def processCad(s: ^): ^ = {
    val falseAtom = ^(new CHRToken(FALSE),Nil)
    val trueAtom = ^(new CHRToken(TRUE),Nil) 

    s match{
      case ^(RULE, rdef) => ^(RULE, rdef.map{
        case ^(HEAD, h) => ^(HEAD, (h match{
          case ^(EMPTY,_) :: _ => trueAtom :: Nil
          case _ =>  trueAtom :: processAtoms(h)
        }))
        case ^(BODY, b) => ^(BODY, falseAtom::Nil)
        case x: ^ => x
      })
      case _ => null
    }
  }

  def processAtoms(alst:List[^]):List[^] = {
    alst.flatMap{
      case ^(ATOM, name :: ^(VARS, vars) :: _) =>
        val newVarList = vars.zip(vars.map(v => processVariable(v)) )
        newVarList.foldLeft(List[^]()){
          case (lst, (n @ ^(INT, _), v)) => newAtom(INT_ATOM, n.getText, v) +: lst
          case (lst, (s @ ^(STRING, _), v)) => newAtom(STR_ATOM, s.getText, v) +: lst
          case (lst, (^(NULL, _), v)) => newAtom(NULL_ATOM, "Null", v) +: lst
          case (lst, _) => lst
        } :+
        ^(ATOM, name.dupNode.asInstanceOf[^] ::
          ^(VARS, newVarList.map{
            case (v, null) => v.dupNode.asInstanceOf[^]
            case (_, v) => v
            }) :: Nil)
      case ^(ATOM, name :: Nil) => ^(ATOM, name.dupNode.asInstanceOf[^] :: Nil) :: Nil
      case x => x :: Nil
    }
  }

  def newAtom(tok:Token, name:String, varNode: ^): ^ = {
    ^(tok, ^(new CHRToken(R_ID.getType, name), Nil) :: ^(VARS, varNode::Nil) :: Nil )
  }

  def newAtom(name:String, vars: List[^]): ^ = {
    ^(ATOM, ^(new CHRToken(R_ID.getType, name), Nil) :: ^(VARS, vars) :: Nil )
  }

  def processVariable(variable: ^): ^ = variable match{
    case ^(INT, _) => variableGenerator(V_ID.getType)
    case ^(STRING, _) => variableGenerator(V_ID.getType)
    case ^(NULL, _) => variableGenerator(V_ID.getType)
    case _ => variable
  }

  def processAtom(name:String, vars:List[^]):List[^] = {
    def processAtomIter(vlst:List[^], acum:List[^]):List[^] = vlst match{
      case List() => acum
      case ^(INT, _) :: res =>
        val intVar = variableGenerator(V_ID.getType)
        ^(ATOM, ^(new CHRToken(R_ID.getType, "int_"+vars.head), Nil) :: ^(VARS, intVar::Nil) :: Nil ) :: Nil
      case _ => vlst
    }
    processAtomIter(vars,List())
  }

  private def variableGenerator(vtype:Int): ^ = {
    variableIndex += 1
    val name = "$x"+variableIndex

    ^(new CHRToken(vtype,name), Nil )
  }

  private def getVariableIndex: Int = {
    variableIndex += 1
    variableIndex
  }
}