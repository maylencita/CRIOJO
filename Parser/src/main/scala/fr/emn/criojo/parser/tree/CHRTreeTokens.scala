package fr.emn.criojo.parser.tree

import org.antlr.runtime.{Token,CommonToken}

class CHRTreeTokens(tokNames: Array[String])  {
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

  private[this] def getToken(tokName: String): Token ={
		if (tokNames != null)
			new CommonToken(tokNames.indexOf(tokName), tokName)
		else
			null
	}
	
	implicit def string2Token(tokName: String): Token = getToken(tokName)
}
