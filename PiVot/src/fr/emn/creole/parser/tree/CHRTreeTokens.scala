package fr.emn.fullers.parser.tree

import org.antlr.runtime.{Token,CommonToken}

class CHRTreeTokens(tokNames: Array[String])  {
	
	val NEW: Token = "NEW"
	val SOLVERCLASS: Token = "SOLVERCLASS"
	val PROGRAM: Token = "PROGRAM"
	val RULE: Token = "RULE"
	val CONSTRAINT: Token = "CONSTRAINT"
	val HEAD: Token = "HEAD"
	val BODY: Token = "BODY"
	val CONSDEF: Token = "CONSDEF"
	val NAME: Token = "NAME"
	val ARITY: Token = "ARITY"
	val VALUES: Token = "VALUES"
	val IMPORT: Token = "IMPORT"
	val ALIAS: Token = "ALIAS"
	val DEF: Token = "DEF"
	val PARAMS: Token = "PARAMS"
	val START: Token = "START"
	val LIST: Token = "LIST" 
	val EMPTYLIST: Token = "EMPTYLIST"
	val TAIL: Token = "TAIL"
	val SOLVER: Token = "SOLVER"
	val ID: Token = "ID"
	val LET: Token = "LET"
	val IN: Token = "IN"
	val EQ_OP: Token = "EQ_OP"
	val LT: Token = "LT"
	val LTEQ: Token = "LTEQ"
	val PLUS: Token = "PLUS"
	val MINUS: Token = "MINUS"
	val INT: Token = "INT"
	val BOOL: Token = "BOOL"
	val STRING: Token = "STRING"
	val NOT: Token = "NOT"
	val OR: Token = "OR"
	val BAR: Token = "BAR"
	val FUN: Token = "FUN"
	val TILDE: Token = "TILDE"
	val INNERRULE: Token = "INNERRULE"
//	val IN: Token = "IN"
	val OUT: Token = "OUT"
	
	private[this] def getToken(tokName: String): Token ={
		if (tokNames != null)
			new CommonToken(tokNames.indexOf(tokName), tokName)
		else
			null
	}
	
	implicit def string2Token(tokName: String): Token = getToken(tokName)
}
