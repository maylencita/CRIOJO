grammar SQL;

options {language=Scala;	output=AST;	ASTLabelType=CommonTree;}
//options{	k=5;	backtrack=true;	memoize=true;	language=Scala;	output=AST;	ASTLabelType=CommonTree;}
import BasicTokens;//, SqlUpdateSimple;

tokens {
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
package fr.emn.fullers.sql.parser;
}

@parser::header {
package fr.emn.fullers.sql.parser;
}

program
	:	prog_element (SEMI prog_element)*
	;	

prog_element
	:	query
	|	update
	;
	
/***************************************************************************************
	QUERY
***************************************************************************************/

query 
	:	gb_query
	|	ngb_query
	;

sub_query
	:	LPAREN query RPAREN -> query
	;

gb_query
	:	unary_query GB gb_attr hav_condition?
		-> ^(unary_query ^(GB gb_attr) hav_condition? )
	;

ngb_query
	:	unary_query
	|	binary_query
//	|	LPAREN unary_query RPAREN -> unary_query
	;

unary_query
	:	simple_query 
	|	exists_query
//	|	complex_query
	|	compound_query
	|	not_exists_query
	;

simple_query
	:	base_query (WR simple_predicate)? -> ^(SIMPLE_QUERY base_query simple_predicate?)
	;

//complex_query
//	:	
//		base_query WR complex_predicate 
//			-> ^(COMPLEX_QUERY base_query complex_predicate)
//	|	SL selector FR LPAREN query /*ngb_query*/  RPAREN 
//			-> ^(COMPOUND_QUERY selector 
//				^(SUB_QUERY query) ) //^(SUB_QUERY ngb_query) )
//	;
	
binary_query
	:	sub_query set_op sub_query -> ^(BINARY_QUERY ^(set_op sub_query sub_query))
		//LPAREN ngb_query RPAREN set_op LPAREN ngb_query RPAREN -> ^(BINARY_QUERY ^(set_op ngb_query ngb_query))
	;

compound_query
	:	base_query WR compound_predicate -> ^(COMPOUND_QUERY base_query compound_predicate)
	|	SL selector FR sub_query //LPAREN query ngb_query  RPAREN 
			-> ^(COMPOUND_QUERY selector 
				^(SUB_QUERY sub_query) ) //^(SUB_QUERY ngb_query) )
	;

exists_query
	:	base_query WR exists_predicate
			-> ^(EXISTS_QUERY base_query exists_predicate)
	;

not_exists_query
	:	base_query WR NOT exists_predicate -> ^(NOT_EXIST_QUERY base_query ^(NOT exists_predicate))
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
			//-> ^(COMPOUND_PREDICATE ^(ANY ^(comp_op attribute_spec ngb_query)))
	|	attribute_spec comp_op ALL sub_query //LPAREN ngb_query RPAREN 
			-> ^(COMPOUND_PREDICATE ^(ALL ^(comp_op attribute_spec sub_query)))	
			//-> ^(COMPOUND_PREDICATE ^(ALL ^(comp_op attribute_spec ngb_query)))
//	|	attribute_spec IN LPAREN constant (COMMA constant)* RPAREN 
//			-> ^(COMPOUND_PREDICATE ^(IN attribute_spec ^(CONSTANT_SET constant*)))
	|	attribute_spec NOT? IN sub_query //LPAREN ngb_query RPAREN 
			-> ^(IN_PREDICATE NOT? attribute_spec sub_query )
			//-> ^(COMPOUND_PREDICATE ^(IN NOT? attribute_spec ngb_query ))
	|	LPAREN predicate RPAREN boolean_op LPAREN predicate RPAREN
			-> ^(COMPOUND_PREDICATE ^(boolean_op predicate predicate))		
/*			
	|	LPAREN simple_predicate RPAREN boolean_op LPAREN complex_part RPAREN
			-> ^(COMPOUND_PREDICATE ^(boolean_op simple_predicate complex_part))
	|	LPAREN complex_part RPAREN boolean_op LPAREN complex_part RPAREN
			-> ^(COMPOUND_PREDICATE ^(boolean_op complex_part complex_part))
	|	LPAREN compound_predicate RPAREN boolean_op LPAREN compound_predicate RPAREN 	
			-> ^(COMPOUND_PREDICATE ^(boolean_op compound_predicate compound_predicate))
	|	LPAREN ngb_query RPAREN NCONTAINS LPAREN ngb_query RPAREN
			-> ^(COMPOUND_PREDICATE ^(NCONTAINS ngb_query ngb_query))		
	|	LPAREN ngb_query RPAREN CONTAINS LPAREN ngb_query RPAREN
			-> ^(COMPOUND_PREDICATE ^(CONTAINS ngb_query ngb_query))	
*/						
	;

/*
complex_part
	:	exists_predicate 
	|	complex_predicate
	;
*/
/*
complex_predicate
	:	attribute_spec comp_op ngb_query -> ^(COMPLEX_PREDICATE ^(comp_op attribute_spec ngb_query))
	;
*/

selector
	:	select_spec (COMMA select_spec)*  ->
			^(SELECTOR select_spec*)
	|	ASTERIX -> ^(SELECTOR ASTERIX)
	;

hav_condition
	:	HVNG function_spec comp_op constant -> ^(HVNG ^(comp_op function_spec constant))
	|	HVNG function_spec comp_op ngb_query -> ^(HVNG ^(comp_op function_spec ngb_query))
	;

relation_list
	:	relation (COMMA relation)* 
			-> ^(RELATION_LIST relation*)
	;

gb_attr	
	:	attribute_spec (COMMA attribute_spec)* -> ^(ATTRIBUTE_LIST attribute_spec*)
	;

function_spec
	:	function LPAREN attribute_spec RPAREN
			-> ^(FUNCTION function attribute_spec)
	;

select_spec
	:	(function_spec) => function_spec
	|	attribute_spec
	|	constant
	|	'COUNT(*)' -> ^(FUNCTION COUNT) 
	;

comp_op
	:	EQ | NOT_EQ | LTH | LEQ | GTH | GEQ 
	;
	
boolean_op
	:	AND | OR
	;
	
set_op
	:	UNION | INTERSECT | SET_MINUS
	;
			
function
	:	SUM | MIN | MAX | AVERAGE
	;

attribute_spec
	:	relation DOT attribute -> ^(ATTRIBUTE_SPEC ^(RELATION relation) ^(ATTRIBUTE attribute))
	;

attribute
	:	ID
	;

relation
	:	ID	
	;

constant
	:	NUMBER 
	|	DOUBLEQUOTED_STRING
	;

base_query
	:	SL selector FR relation_list -> selector relation_list
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
