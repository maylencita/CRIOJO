grammar CREOLE_X;

options {language=Scala;output=AST;ASTLabelType=CHRTree;backtrack=true;}

tokens{
    SCRIPT;
    ATOM;
    VARS;
    RULE;
    HEAD;
    BODY;

    MULTI;
    DECLARATION;
    EMPTYLIST;
    PUBLIC;
    PRIVATE;
    PROCESS;

    GUARD;
    EMPTY;

    INT_ATOM;
    STR_ATOM;
    NULL_ATOM;
}

@lexer::header {
package fr.emn.criojo.parser;

import fr.emn.criojo.parser.tree._
}

@parser::header {
package fr.emn.criojo.parser;

import fr.emn.criojo.parser.tree._
}

@members{
	def getCHRTreeTokens = new CHRTreeTokens(this.tokenNames)
}
start
    :   process
    ;

process
    :   declaration script -> ^(PROCESS declaration ^(SCRIPT script))
    ;

declaration
    :   LPAREN 'public' COLON rlist SEMI 'private' COLON rlist RPAREN ->
            ^(DECLARATION ^(PUBLIC rlist) ^(PRIVATE rlist))
    ;

rlist
    :   rdeclaration (COMMA rdeclaration)* -> rdeclaration*
    |   UNDEF -> EMPTYLIST
    ;

rdeclaration
    :   R_ID
    |   R_ID ARROBAS url -> ^(ARROBAS R_ID url)
    |   TILDE R_ID -> ^(MULTI R_ID)
    ;

script
    :   parallel (SEMI^ parallel)*
    ;

parallel
    :   bang (BAR^ bang)*
    ;

bang
    :   simple_script
    |   BANG simple_script -> ^(BANG simple_script) 
    ;

simple_script
    :   rule
    |   LPAREN script RPAREN  -> script
    ;

/*
primitive
    :   rule BAR primitive -> ^(BAR rule primitive)
    |   rule
    |   LPAREN script RPAREN  -> script
    ;
op
    :   BAR
    |   SEMI
    ;
bang
    :   primitive
    |   BANG primitive -> ^(BANG primitive)
    ;
*/

rule
    :   conjunction RARROW guard? nu? conjunction  ->
            ^(RULE guard? ^(HEAD conjunction) ^(BODY conjunction) nu?)
    ;

guard
    :   LBRACK rule* RBRACK IMARK -> ^(GUARD rule*)
    |   ABS LBRACK conjunction RBRACK IMARK -> ^(GUARD ^(ABS conjunction))
    ;

nu
    :   NU LPAREN variable (COMMA variable)* RPAREN -> ^(NU variable*)
    ;

conjunction
    :   'T' -> EMPTY
    |   atom (COMMA atom)* -> atom*
    ;

atom
    :   TRUE
    |   FALSE
    |   relation termlist?  ->
            ^(ATOM relation termlist?)
    ;

termlist
    :   LPAREN term (COMMA term)* RPAREN ->   ^(VARS term*)
    ;

term
	:	constant
	|	variable
	;

relation
    :   R_ID
    ;

variable
    :   NULL | V_ID | R_ID
    ;

constant
	:	INT
	|	STRING
	;

url : STRING;

/***************************************************************************************
	TOKENS
***************************************************************************************/
NU
    :   'new'
    ;
TRUE
    :   'true'
    ;
FALSE
    :   'false'
    ;
NULL
    :   'null'
    ;
ARROBAS
	:	'@'
	;
/*
ZERO
    :   '0'
    ;
*/
ABS
    :   'Abs'
    ;
/*
PACKAGE
	:	'package'
	;
SOLVER
	:	'solver'
	;
*/
RARROW
	:	'=>'
	;
/*
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
*/
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
COLON	:	':'
	;
SLASH	: 	'/'
	;
BAR		: 	'|'
	;
BANG
	:   '!'
	;
TILDE
    :   '~'
    ;
UNDEF
    :   '_'
    ;
IMARK
    :   '?'
    ;
R_ID
    :   ('A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;
V_ID  :	('a'..'z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

//ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
//    ;

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
