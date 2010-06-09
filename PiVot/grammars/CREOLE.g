grammar CREOLE;

options {language=Scala;output=AST;ASTLabelType=CHRTree;backtrack=true;}

tokens{
    START;
    ATOM;
    VARS;
    CONJ;
    RULE;
    HEAD;
    BODY;
    SCRIPT;
    SEQUENCE;
    REPEAT;
    PARL;
}

@lexer::header {
package fr.emn.creole.parser;

import fr.emn.creole.parser.tree._
}

@parser::header {
package fr.emn.creole.parser;

import fr.emn.creole.parser.tree._
}

start
    :   script -> ^(SCRIPT script)
    ;

script
    :   bang op bang -> ^(op bang bang)
    |   bang
    ;
op
    :   BAR
    |   SEMI
    ;

bang
    :   primitive
    |   BANG primitive -> ^(BANG primitive)
    ;

primitive
    :   rule
    |   LPAREN script RPAREN  -> script
    ;

rule
    :   conjunction RARROW nu? conjunction  ->
            ^(RULE ^(HEAD conjunction) ^(BODY conjunction) nu?)
    ;

nu
    :   NU LPAREN variable (COMMA variable)* RPAREN -> ^(NU variable*)
    ;

conjunction
    :   atom (COMMA atom)* -> atom*
    ;

atom
    :   relation varlist?  ->
            ^(ATOM relation varlist?)
    ;

varlist
    :   LPAREN variable (COMMA variable)* RPAREN ->   ^(VARS variable*)
    ;

relation
    :   ID
    ;

variable
    :   ID
    ;
    
/***************************************************************************************
	TOKENS
***************************************************************************************/
NU
    :   'new'
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
RARROW
	:	'=>'
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
BANG
	:   '!'
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
