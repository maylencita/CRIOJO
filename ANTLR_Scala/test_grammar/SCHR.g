grammar SCHR;

//options {language=Scala;output=AST;ASTLabelType=CHRTree;backtrack=true;memoize=true;k=10;}
options {language=Scala;output=AST;ASTLabelType=CHRTree;backtrack=true;}
/*
	k=*;	
	backtrack=true;
	memoize=true;
	output=AST;	
	ASTLabelType=CommonTree;
}
*/

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
	ALIAS;
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
	:	(ruleId ARROBAS)? constraintList '<=>' constraintList SEMI 
		-> ^(RULE ruleId? ^(HEAD constraintList) ^(BODY constraintList))
	;
		
constraintList
	:	constraint (COMMA constraint)* -> constraint*
	;	
	
constraint
	:	(solvId POINT)?  consId ( LPAREN term (COMMA term)* RPAREN )?
		-> ^(CONSTRAINT ^(SOLVER solvId?) ^(NAME consId) ^(VALUES term*) )
	|	builtinconstraint	
	;
		
builtinconstraint
	:	term op term -> ^(CONSTRAINT ^(NAME op) ^(VALUES term term ) )
	|	term IS term op term -> ^(CONSTRAINT ^(NAME op) ^(VALUES term term term) )
	;	

op
	:	EQ | LT | LTEQ | PLUS | MINUS
	;
	
constraintsdef
	:	CONSTRAINT constraintdef (COMMA constraintdef)* SEMI 
		-> ^(CONSDEF constraintdef*)
	;
		
constraintdef
	:	ID SLASH INT -> ^(CONSTRAINT ^(NAME ID) ^(ARITY INT))
	;	
	
axiom	:	constraint SEMI -> constraint
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
	;

solvparams
	:	LPAREN ID (COMMA ID)*  RPAREN
		-> ^(PARAMS ID*)
	;

solvname
	:	
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
EQ
	:	'='
	;	
EQ_OP
	:	'=='
	;
LT
	:	'<'
	;
LTEQ
	:	'=<'
	;		
PLUS	
	:	'+'
	;
MINUS
	:	'-'
	;			
LPAREN 	:	'('
	;
	
RPAREN	:	')'
	;

LCURL	:	'{'
	;

RCURL	:	'}'
	;

RBRACK
	:	']'
	;
LBRACK
	:	'['
	;
			
COMMA	:	','	
	;
	
POINT	:	'.'
	;
	
SEMI	:	';'
	;	
SLASH	: 	'/'
	;
BAR		: 	'|'
	;
								
ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT :	'0'..'9'+
    ;
/*
FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;
*/
COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
