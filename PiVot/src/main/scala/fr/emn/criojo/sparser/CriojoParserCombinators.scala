package fr.emn.criojo.sparser
/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/02/11
 * Time: 15:06
 */
import fr.emn.criojo.core._
import scala.util.parsing.combinator._
import fr.emn.criojo.parser.tree.{CHRToken, ^}

class CriojoParserCombinators extends JavaTokenParsers{/*
  import Tokens._
  type RuleDef = RuleFactory => Rule

  def script:Parser[^] = rep(declaration) ~ rep(rule) ^^ {
    case dlst ~ rlst => ^(PROCESS,
      ^ (DECLARATION, dlst),
      ^ (SCRIPT, rlst))
  }

  def declaration:Parser[^] = "rel" ~> relId ^^ {
    case rid => ^(ID, ^(new CHRToken(R_ID.getType, rid), Nil))
  }

  def rule:Parser[^] = head ~ ("=>" ~> body ) ^^ {
    case h ~ b => h ==> b
  }

  def head:Parser[^] = atomList ^^ { items =>
    val primero:Conjunction = Empty
    items.foldLeft (primero) {(c,a) => a  &: c }
  }

  def body:Parser[^] = opt(guard) ~ atomList ^^ {
    case op ~ atoms =>
    val primero:Conjunction = Empty
    (op match{
      case Some(g) => g
      case _ => new EmptyGuard
      },
      atoms.foldLeft  (primero) {case (c:Conjunction, a:Atom) => a &: c})
  }

  def guard:Parser[^] = "[" ~> repsep(rule, "|") <~ "]" <~ "?"  ^^ { items =>
    new EmptyGuard
  }

  def atomList = repsep(atom, ",")

  def atom:Parser[^] = relationName ~ ("(" ~> termList <~ ")" )  ^^ {
    case rn ~ tlst => new Atom(rn, tlst)
  }

  def termList:Parser[List[^]] = repsep(term, ",") ^^ {terms =>
    terms.map(new Variable(_))
  }

  def relationName = ident

  def term = variable

  def variable = ident

  def relId = ident
}

import org.antlr.runtime.Token

object Tokens{
  val LT: Token = "LT"
  val EQ_OP: Token = "EQ_OP"
  val HEAD: Token = "HEAD"
  val LTEQ: Token = "LTEQ"
  val ATOM: Token = "ATOM"
  val ID: Token = "ID"
  val R_ID: Token = "R_ID"
  val V_ID: Token = "V_ID"
  val IN: Token = "IN"
  val PLUS: Token = "PLUS"
  val BODY: Token = "BODY"
  val SCRIPT: Token = "SCRIPT"
  val RULE: Token = "RULE"
  val NU: Token = "NU"
  val VARS: Token = "VARS"
  val BANG: Token = "BANG"
  val INT: Token = "INT"
  val MINUS: Token = "MINUS"
  val TRUE: Token = "TRUE"
  val FALSE: Token = "FALSE"
  val BAR: Token = "BAR"
  val LET: Token = "LET"
  val STRING: Token = "STRING"
  val SEMI:Token = "SEMI"
  val ARROBAS: Token = "ARROBAS"

  val MULTI:Token = "MULTI"
  val DECLARATION:Token = "DECLARATION"
  val EMPTYLIST:Token = "EMPTYLIST"
  val PUBLIC:Token = "PUBLIC"
  val PRIVATE:Token = "PRIVATE"
  val REQUIRED:Token = "REQUIRED"
  val PROCESS:Token = "PROCESS"

  val GUARD:Token = "GUARD"
  val EMPTY:Token = "EMPTY"

  val ABS:Token = "ABS"
  val EQ:Token = "EQ"

  val INT_ATOM:Token = "INT_ATOM"
  val STR_ATOM:Token = "STR_ATOM"
  val NULL_ATOM:Token = "NULL_ATOM"
  val NULL:Token = "NULL"
  val NOT:Token = "NOT"
}

object CriojoParserDemo extends Application{
  val p = new CriojoParserCombinators

  val input = """
    A(),B() => C(),D()
    B(),C() => A()
  """
  p.parseAll(p.script, input) match{
    case p.Success(r,_) => println("Success: " + r.toString)
    case x => println("Error: " + x)
  }*/
}

