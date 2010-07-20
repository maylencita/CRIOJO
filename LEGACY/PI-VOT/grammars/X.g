grammar X;

options {language=Scala;}

@lexer::header {
package grammar_test;
}

@parser::header {
package grammar_test;
}

start	:	ZERO	
		;
		
ZERO:'O';