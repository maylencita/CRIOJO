grammar SQL;

options {language=Scala; output=AST;	ASTLabelType=CommonTree;}
//options{	k=5;	backtrack=true;	memoize=true;	language=Scala;	output=AST;	ASTLabelType=CHRTree;}
import BasicTokens;//, SqlUpdateSimple;

tokens {
	PROGRAM;
	QUERY;
	CONDITION;
	NOT_CONDITION;
	UNION;
	
	SIMPLE_QUERY;
	BINARY_QUERY;
	SELECTOR;
	ATTRIBUTE;
	ATTRIBUTES;
	ATTRIBUTE_SPEC;
	FUNCTION;
	RELATION;
	RELATION_LIST;
	COUNT;
	COMPOUND_QUERY;
	NOT_EXIST_QUERY;
	EXISTS_QUERY;
	CONSTANT_SET;
	ATTRIBUTE_LIST;
	COMPLEX_QUERY;
	SIMPLE_PREDICATE;
	COMPOUND_PREDICATE;
	COMPLEX_PREDICATE;
	EXISTS_PREDICATE; 
	SELECT_LIST;
	SUB_QUERY;
	IN_PREDICATE;
}

@lexer::header {
package fr.emn.fullers.parser;
}

@parser::header {
package fr.emn.fullers.parser;
}

program  
	:	prog_element (SEMI prog_element)* //-> ^(PROGRAM prog_element*)
	;	

prog_element
	:	query
	|	update
	;
	
/***************************************************************************************
	QUERY
***************************************************************************************/

query
	:	sql_query
	|	union_query
	;

union_query	:	LPAREN sql_query RPAREN UNION LPAREN sql_query RPAREN
				-> ^(UNION sql_query sql_query)
			;

sql_query	
	:	base_query condition? -> ^(QUERY base_query condition?)
	;

sub_query
	:	LPAREN query RPAREN -> ^(SUB_QUERY query)
	;
	
condition
	:	WR search_condition -> ^(CONDITION search_condition)
	;

search_condition
	:	term comp_op term -> ^(comp_op term term) 
	|	LPAREN NOT search_condition RPAREN -> ^(NOT search_condition)	
	|	LPAREN search_condition RPAREN boolean_op LPAREN search_condition RPAREN -> ^(boolean_op search_condition search_condition)
	|	EXISTS sub_query -> ^(EXISTS sub_query)
	|	term comp_op quantifier sub_query -> ^(quantifier ^(comp_op term sub_query))
	|	term IN sub_query -> ^(IN term sub_query)
	;

term 
	:	attribute_spec
	|	constant
	;

base_query
	:	SL selector FR relation_list -> selector relation_list
	;
	
selector
	:	select_spec (COMMA select_spec)*  -> ^(SELECTOR select_spec*)
	|	ASTERIX -> ^(SELECTOR ASTERIX)
	;

select_spec
	:	(function_spec) => function_spec
	|	attribute_spec
	|	constant
	|	'COUNT(*)' -> ^(FUNCTION COUNT) 
	;

/*	
query 
	:	gb_query
	|	ngb_query
	;

sub_query
	:	LPAREN query RPAREN //-> query
	;

gb_query
	:	unary_query GB gb_attr hav_condition?
//		-> ^(unary_query ^(GB gb_attr) hav_condition? )
	;

ngb_query
	:	unary_query
	|	binary_query
	;

unary_query
	:	simple_query 
	|	exists_query
	|	compound_query
	|	not_exists_query
	;

simple_query
	:	base_query (WR simple_predicate)? //-> ^(SIMPLE_QUERY base_query simple_predicate?)
	;

binary_query
	:	sub_query set_op sub_query //-> ^(BINARY_QUERY ^(set_op sub_query sub_query))
	;

compound_query
	:	base_query WR compound_predicate //-> ^(COMPOUND_QUERY base_query compound_predicate)
	|	SL selector FR sub_query //LPAREN query ngb_query  RPAREN 
//			-> ^(COMPOUND_QUERY selector ^(SUB_QUERY sub_query) ) 
	;

exists_query
	:	base_query WR exists_predicate
//			-> ^(EXISTS_QUERY base_query exists_predicate)
	;

not_exists_query
	:	base_query WR NOT exists_predicate //-> ^(NOT_EXIST_QUERY base_query ^(NOT exists_predicate))
	;

predicate
	:	simple_predicate
	|	compound_predicate
	|	exists_predicate
	;

simple_predicate
	:	attribute_spec comp_op (attribute_spec | constant)
			-> ^(SIMPLE_PREDICATE ^(comp_op attribute_spec attribute_spec? constant? ))
	|	LPAREN simple_predicate RPAREN boolean_op LPAREN simple_predicate RPAREN		
			-> ^(SIMPLE_PREDICATE ^(boolean_op simple_predicate simple_predicate))
	;

exists_predicate
	:	//EXISTS ngb_query -> ^(EXISTS_PREDICATE ngb_query)
		EXISTS query -> ^(EXISTS_PREDICATE query)
	|	EXISTS sub_query -> ^(EXISTS_PREDICATE sub_query)	
	;

compound_predicate
	:	
	|	attribute_spec comp_op ANY sub_query //LPAREN ngb_query RPAREN 
			-> ^(COMPOUND_PREDICATE ^(ANY ^(comp_op attribute_spec sub_query)))
	|	attribute_spec comp_op ALL sub_query //LPAREN ngb_query RPAREN 
			-> ^(COMPOUND_PREDICATE ^(ALL ^(comp_op attribute_spec sub_query)))	
	|	attribute_spec NOT? IN sub_query //LPAREN ngb_query RPAREN 
			-> ^(IN_PREDICATE NOT? attribute_spec sub_query )
	|	LPAREN predicate RPAREN boolean_op LPAREN predicate RPAREN
			-> ^(COMPOUND_PREDICATE ^(boolean_op predicate predicate))		
	;


hav_condition
	:	HVNG function_spec comp_op constant -> ^(HVNG ^(comp_op function_spec constant))
	|	HVNG function_spec comp_op ngb_query -> ^(HVNG ^(comp_op function_spec ngb_query))
	;

gb_attr	
	:	attribute_spec (COMMA attribute_spec)* -> ^(ATTRIBUTE_LIST attribute_spec*)
	;
*/

relation_list
	:	relation_spec (COMMA relation_spec)* 
			-> ^(RELATION_LIST relation_spec*)
	;

relation_spec
	:	relation (AS alias)? -> ^(RELATION relation alias?)
	;

function_spec
	:	function LPAREN attribute_spec RPAREN
			-> ^(FUNCTION function attribute_spec)
	;

attribute_spec
	:	relation DOT attribute -> ^(ATTRIBUTE_SPEC ^(RELATION relation) ^(ATTRIBUTE attribute))
	;
	
comp_op
	:	EQ | NOT_EQ | LTH | LEQ | GTH | GEQ 
	;
	
boolean_op
	:	AND | OR
	;

quantifier
	:	ANY | ALL
	;
	
set_op
	:	UNION | INTERSECT | SET_MINUS
	;
			
function
	:	SUM | MIN | MAX | AVERAGE
	;

attribute
	:	ID
	;

relation
	:	ID	
	;

alias
	:	ID
	;
	
constant
	:	NUMBER 
	|	DOUBLEQUOTED_STRING
	;

	
/***************************************************************************************
	UPDATE
***************************************************************************************/

update
	:	
	;	
	
/***************************************************************************************
	LEXER
***************************************************************************************/
		
SL
	:	'SELECT' | 'select'
	;

FR	
	:	'FROM' | 'from'
	;
	
WR	:	'WHERE' | 'where'
	;	
	
AND
	:	'and' | 'AND'
	;		
	
OR
	:	'OR' | 'or'
	;
	
EXISTS
	:	'EXISTS' | 'exists'
	;		
	
GB	
	: 	'GROUP BY'
	;	
	
HVNG	
	:	'HAVING'
	;	
	
UNION
	:	'UNION' | 'union'
	;	
	
INTERSECT
	:	'INTERSECT' | 'intersect'
	;	
	
SET_MINUS
	:	'MINUS' | 'minus'	
	;	
	
CONTAINS
	:	'CONTAINS' | 'contains'
	;
	
NCONTAINS
	:	'DOES NOT CONTAIN' | 'does not contain'
	;		
	
NOT
	:	'NOT' | 'not'
	;	
	
ALL
	:	'ALL'
	;	
	
ANY
	:	'ANY'
	;	
	
IN
	:	'IN'
	;	

NOT_IN
	:	'NOT_IN'
	;
	
SUM
	:	'SUM'
	;
	
MIN
	:	'MIN'
	;	
	
MAX
	:	'MAX'
	;	
	
AVERAGE
	:	'AVERAGE' | 'AVG'
	;		

AS
	:	'AS' | 'as'
	;	
	
ID	:	LETTER (LETTER | DIGIT)*
	|	DOUBLEQUOTED_STRING
	;
	
WS	:	(' '|'\r'|'\t'|'\n') {$channel=HIDDEN;}
	;

ASTERIX	:	 '*'
	;
	