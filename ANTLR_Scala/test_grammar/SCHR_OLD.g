grammar SCHR;

//options {output=AST;ASTLabelType=CHRTree;backtrack=true;memoize=true;k=10;}
options {language=Scala;output=AST;ASTLabelType=CHRTree;backtrack=true;}

import BasicTokens;

tokens{
	SOLVERCLASS;
	PROGRAM;
	RULE;
	CONSTRAINT;
	HEAD;
	BODY;
	CONSDEF;
	NAME;
	ARITY;
	VALUES;
	IMPORT;
	SOLVER;
	ALIAS;
//	IN;
	DEF;
	PARAMS;
	START;
	LIST;
	EMPTYLIST;
	HEAD;
	TAIL;	
}

@lexer::header {
package fr.emn.fullers.parser.schr;

//import basicCHR.interpreter.tree.*;
import fr.emn.fullers.parser.tree._
}
@parser::header {
package fr.emn.fullers.parser.schr;

//import basicCHR.interpreter.tree.*;
import fr.emn.fullers.parser.tree._
}


start returns [String solvname]
	:	packagedef? importdef* body{$solvname= $body.solvname;} -> ^(START packagedef? importdef* body)		
	;

packagedef
	:	PACKAGE STRING SEMI -> STRING
	;
	
body returns [String solvname]
	:	gensolver {$solvname = $gensolver.solvname;}
	|	program
	;
	
gensolver returns [String solvname]
	:	SOLVER ID{$solvname = $ID.text;} solvparams? LCURL program RCURL -> ^(SOLVERCLASS ID? solvparams? program)
	;

program	
	:	expression expression* -> ^(PROGRAM expression*)
	;
	
ruledef
	:	(ruleId ARROBAS)? constraintList CHR_SIMP constraintList SEMI //POINT
		-> ^(RULE ruleId? ^(HEAD constraintList) ^(BODY constraintList))
	;
		
constraintList
	:	constraint (COMMA constraint)* -> constraint*
	;	
	
constraint
	:	(solvId DOT)?  consId ( LPAREN term (COMMA term)* RPAREN )?
		-> ^(CONSTRAINT ^(SOLVER solvId?) ^(NAME consId) ^(VALUES term*) )
	|	builtinconstraint	
	;
		
builtinconstraint
	:	(term op term) => term op term -> ^(CONSTRAINT ^(NAME op) ^(VALUES term term ) )
	|	term IS term op term -> ^(CONSTRAINT ^(NAME op) ^(VALUES term term term) )
	;	

op
	:	EQ_OP | LTH | LEQ | PLUS | MINUS
	;
	
constraintsdef
	:	CONSTRAINT constraintdef (COMMA constraintdef)* SEMI //POINT
		-> ^(CONSDEF constraintdef*)
	;
		
constraintdef
	:	ID DIVIDE INT -> ^(CONSTRAINT ^(NAME ID) ^(ARITY INT))
	;	
	
axiom	:	constraint SEMI /*POINT*/ -> constraint
	;			

expression
	:	constraintsdef
	|	ruledef
	|	axiom
	|	letexpr
//	|	importdef
	;

letexpr
	:	
		LET solvId EQ solvdef IN LCURL program RCURL	
		-> ^(LET ^(NAME solvId) ^(DEF solvdef) ^(IN program) )		
	;

solvdef
	:	
		solvinstance | program   
	;	

importdef
	:	USE solvname SEMI -> ^(IMPORT solvname )
//		solvname solvparams? SEMI -> ^(SOLVER solvname solvparams?) 
	;

solvparams
	:	LPAREN ID (COMMA ID)*  RPAREN
		-> ^(PARAMS ID*)
	;

solvname
	:	
//		ID (DOT ID)* -> ID*
//		ID
		STRING 
	;

solvinstance
	:	ID solvparams? -> ^(SOLVER ID solvparams?)
	;

term
	:	simpleterm
	|	termlist
	;
	
termlist
	:	LBRACK RBRACK -> EMPTYLIST
	|	LBRACK simpleterm BAR variable RBRACK -> ^(LIST ^(HEAD simpleterm) ^(TAIL variable))
	|	LBRACK term (COMMA term)* RBRACK -> ^(LIST term*)
	;
			
simpleterm
	:	constant
	|	variable
	;

constant
	:	INT
	|	STRING
	;

consId	:	ID
	;

ruleId	:	ID
	;	
		
variable	:	ID
	;

solvId	:	ID
	;

/***************************************************************************************
	TOKENS
***************************************************************************************/

STRING
	:	DOUBLEQUOTED_STRING
	;

PACKAGE
	:	'package'
	;
SOLVER
	:	'solver'
	;	
ARROBAS
	:	'@'
	;	
CHR_SIMP
	:	'<=>'
	;	
IS
	:	'is'
	;		
CONSTRAINT
	:	'constraint'
	;
LET
	:	'let'
	;
IN
	:	'in'
	;	
USE
	:	'use'
	;	
EQ_OP
	:	'=='
	;

ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT :	(DIGIT)+ //'0'..'9'+
    ;
    
COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;
			