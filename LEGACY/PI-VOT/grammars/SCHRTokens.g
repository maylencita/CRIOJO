lexer grammar SCHRTokens;

options{language=Scala;}
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

@header {
package fr.emn.fullers.parser.schr;

//import basicCHR.interpreter.tree.*;
//import fr.emn.fullers.parser.tree._
}

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
/*
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
*/
ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT :	'0'..'9'+
    ;
/*
STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
    ;
*/
COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;
/*
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

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;
*/
/*
FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;
WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;
fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

*/