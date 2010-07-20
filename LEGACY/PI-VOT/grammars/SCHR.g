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
	NEW;
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
	NOT;
	OR;
	FUN;
	INNERRULE;
	IN;
	OUT;
}

@lexer::header {
package fr.emn.fullers.parser.schr;

import fr.emn.fullers.parser.tree._
}
@parser::header {
package fr.emn.fullers.parser.schr;

import fr.emn.fullers.parser.tree._
}

@members{
//	val chrAdaptor = new SCHRTreeConstructor(this.tokenNames) 
	
	def getCHRTreeTokens = new CHRTreeTokens(this.tokenNames)
	
	var inConstraint = false
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
	:	(ruleId ARROBAS)? headcons (COMMA headcons)* '<=>' constraintList (COMMA disjlist)? SEMI 
		-> ^(RULE ruleId? ^(HEAD headcons*) ^(BODY constraintList) disjlist?)
	;

disjlist
	:	LPAREN jointlist (BAR jointlist)* RPAREN	-> ^(OR jointlist*)
	;

jointlist
	:	constraintList -> ^(BAR constraintList)
	;

headcons
	:	EXCL constraint -> ^(NOT constraint)
	|	constraint -> constraint 
	|	innerrule
	;

innerrule
	:	solvId POINT LCURL constraintList IMPLIES constraintList RCURL ->
			^(INNERRULE solvId ^(IN constraintList) ^(OUT constraintList))
	;
			
constraintList
	:	constraint (COMMA constraint)* -> constraint*
	;	
	
constraint 
@init{
inConstraint = true
}
@after{
inConstraint = false
}
	:	builtinconstraint
	|	(solvId POINT)?  consId ( LPAREN term (COMMA term)* RPAREN )?
		-> ^(CONSTRAINT ^(SOLVER solvId?) ^(NAME consId) ^(VALUES term*) )	
	;
		
builtinconstraint
	:	term op term -> ^(CONSTRAINT ^(NAME op) ^(VALUES term term ) )
	|	term IS term op term -> ^(CONSTRAINT ^(NAME op) ^(VALUES term term term) )
	;	

op
	:	EQ_OP 
	|	LT 
	| 	LTEQ 
	| 	PLUS 
	|	MINUS
	;
	
constraintsdef
	:	CONSTRAINT constraintdef (COMMA constraintdef)* SEMI 
		-> ^(CONSDEF constraintdef*)
	;
		
constraintdef
	:	TILDE? ID SLASH INT -> ^(CONSTRAINT TILDE? ^(NAME ID) ^(ARITY INT))
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
	|	LPAREN newSolv (COMMA newSolv)*  RPAREN	
		-> ^(PARAMS newSolv*)
	;

newSolv
	:	'new' solvinstance -> solvinstance //^(NEW solvinstance)
	;

solvname
	:	
		STRING 
	;

solvinstance
	:	ID solvparams? -> ^(NEW ^(SOLVER ID solvparams?))
	;

term
	:	simpleterm
	|	termlist
	|	function
	;
	
termlist
	:	LBRACK RBRACK -> EMPTYLIST
	|	LBRACK simpleterm BAR variable RBRACK -> ^(LIST ^(HEAD simpleterm) ^(TAIL variable))
	|	LBRACK term (COMMA term)* RBRACK -> ^(LIST term*)
	;
			
simpleterm
	:	constant /*(constant) =>*/
	|	variable
	;

function
	:	//{inConstraint}? 
		ID LPAREN term RPAREN -> ^(FUN ^(ID term))
	;

constant
	:	BOOL //(boolConst) => boolConst
	|	INT
	|	STRING
	;
/*
boolConst
	:	TRUE | FALSE
	;
*/
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
IMPLIES
	:	'->'
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
TILDE
	:	'~'
	;
EXCL
	:	'!'
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
BOOL
	:	'true' | 'false'
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
