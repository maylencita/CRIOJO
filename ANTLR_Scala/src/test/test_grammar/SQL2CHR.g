tree grammar SQL2CHR;

//options{	tokenVocab = SQL;	backtrack = true;	output=Template;	ASTLabelType=CommonTree;}
options{ language=Java; tokenVocab = SQL;	ASTLabelType = CHRTree;	output=AST;}

//import SCHRTokens;

//***************************************************************************************
//	SCHR Tokens
//***************************************************************************************/

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
	
	INT;
	STRING;	
	LET;
	SOLVER;
}

/*
scope Global{
Program program;
}
*/
@header {
package fr.emn.fullers.parser

import fr.emn.fullers.parser.tree._
//import java.util.Iterator;
//import java.util.Set;
//import java.util.HashSet;
//import fr.emn.fullers.sql.model.*;
}

@members{
	val chrAdaptor = new CHRTreeAdaptor //adaptor.asInstanceOf[CHRTreeAdaptor];
	chrAdaptor.setTokenNames(this.tokenNames)	
}

program 
	:	p0 = prog_element (SEMI p+=prog_element)* -> 
		^(START 
			^(IMPORT {adaptor.create(STRING, "fr.emn.fullers.db.TableSolver")} ) 
			^(PROGRAM $p0 $p))
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

union_query	:	^(UNION sql_query sql_query)
			;

sql_query	
	:	^(QUERY base_query condition?) -> ^(LET ^(NAME {adaptor.create(ID, "_ts")}) ^(DEF ^(SOLVER {adaptor.create(ID, "TableSolver")})) 
											^(IN ^(PROGRAM condition? base_query) ))		
//	|	^(QUERY base_query ) -> ^(LET ^(NAME {adaptor.create(ID, "_ts")}) ^(DEF ^(SOLVER {adaptor.create(ID, "TableSolver")})) 
//											^(IN ^(PROGRAM base_query) ))
	;

sub_query
	:	^(SUB_QUERY query)
	;
	
condition
	:	^(CONDITION search_condition) 
	;

search_condition
	:	^(comp_op term term) //-> 
//			^(LET ^(NAME {adaptor.create(ID, "_cond_i")})
//				^(IN ^(PROGRAM {adaptor.create(STRING, "code here")})))
	|	^(NOT search_condition)	
	|	^(boolean_op search_condition search_condition)
	|	^(EXISTS sub_query)
	|	^(quantifier ^(comp_op term sub_query))
	|	^(IN term sub_query)
	;

term 
	:	attribute_spec
	|	constant
	;

base_query
	:	selector relation_list -> relation_list {chrAdaptor.createCopyRules($relation_list.relList, $relation_list.aliasList)} 
	;
	
selector
	:	^(SELECTOR (s+=select_spec)*) 
	|	^(SELECTOR ASTERIX)  
	;

select_spec
	:	(function_spec) => function_spec
	|	attribute_spec
	|	constant
	|	^(FUNCTION COUNT) 
	;

relation_list returns [List[String\] relList, List[String\] aliasList]
@init{
$relList = List[String]()
$aliasList = List[String]()
}
	:	^(RELATION_LIST (relation_spec{$relList = $relList ::: List ($relation_spec.relName); $aliasList = $aliasList ::: List($relation_spec.al) })*) -> 
			^(CONSDEF relation_spec* 
				^(CONSTRAINT ^(NAME {adaptor.create(ID, "run")}) ^(ARITY {adaptor.create(INT, "0")}))
				^(CONSTRAINT ^(NAME {adaptor.create(ID, "result")}) ^(ARITY {adaptor.create(INT, "1")}))
				^(CONSTRAINT ^(NAME {adaptor.create(ID, "f")}) ^(ARITY {adaptor.create(INT, "1")})))
				
	;

relation_spec returns [String relName, String al]
@init{
var rel:String = ""
var as: String = ""
}
@after{
$relName = rel
$al = as
}
	:	^(RELATION relation{rel = $relation.text} (alias{as=$alias.text; if(as != null) as = "_as" + as else as = ""})?) -> 
			^(CONSTRAINT ^(NAME {adaptor.create(ID, rel + as) }) ^(ARITY {adaptor.create(INT, "1")}))
	;

function_spec
	:	^(FUNCTION function attribute_spec)
	;

attribute_spec
	:	^(ATTRIBUTE_SPEC ^(RELATION relation) ^(ATTRIBUTE attribute))
	;
	
comp_op
	:	EQ 
	| 	NOT_EQ 
	| 	LTH 
	| 	LEQ 
	| 	GTH 
	| 	GEQ 
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

