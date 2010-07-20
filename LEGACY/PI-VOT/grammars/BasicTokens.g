lexer grammar BasicTokens;

options {language=Scala;}

COMMA	: 	',' 
	;
SEMI	: 	';' 
	;
DOT	: 	'.'
	;
PLUS	: 	'+'
	;
MINUS	: 	'-'
	;
DIVIDE	: 	'/'
	;	
RPAREN
	:	')'
	;
LPAREN
	:	'('
	;
RBRACK
	:	']'
	;
LBRACK
	:	'['
	;
LCURL	:	'{'
	;

RCURL	:	'}'
	;

BAR
	:	'|'
	;
		
EQ
	:	'='
	;
NOT_EQ
	:	'<>' | '!=' | '^='
	;
LTH
	:	'<'
	;
LEQ
	:	'<='
	;
GTH
	:	'>'
	;
GEQ
	:	'>='
	;

QUOTED_STRING
	:	( 'n' )? '\'' ( '\'\'' | ~('\'') )* '\''
	;

fragment
DOUBLEQUOTED_STRING
	:	'"' ( ~('"') )* '"'
	;	

fragment
LETTER
	:	'a'..'z' | 'A'..'Z'
	;

fragment
DIGIT
	:	'0'..'9'
	;

NUMBER
	:	(DIGIT)+ ( DOT (DIGIT)*)? // (Exponent)? | Exponent)?
 	;
    		
WS	:	(' '|'\r'|'\t'|'\n') {$channel=HIDDEN;}
	;
