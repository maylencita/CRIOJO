tree grammar SQL2CHR;

//options{	tokenVocab = SQL;	backtrack = true;	output=Template;	ASTLabelType=CommonTree;}
options{ language=Scala; tokenVocab = SQL;	ASTLabelType = CHRTree;	output=AST;}

//import SCHRTokens;

//***************************************************************************************
//	SCHR Tokens
//***************************************************************************************/

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
package fr.emn.fullers.parser.sql

import fr.emn.fullers.parser.tree._
}

@members{
	val chrAdaptor = new SQL2CHRTreeConstructor(this.tokenNames) //new CHRTreeAdaptor 
//	chrAdaptor.setTokenNames(this.tokenNames)	
	
	def getCHRTreeTokens = chrAdaptor.tokens
}

program 
	:	p0 = prog_element (SEMI p+=prog_element)* -> 
		^(START 
			^(IMPORT {adaptor.create(STRING, "fr.emn.fullers.db.TableSolver")} ) 
			^(PROGRAM $p0 $p))
	;	

prog_element
	:	query -> ^(LET ^(NAME {adaptor.create(ID, "_ts")}) ^(DEF ^(NEW ^(SOLVER {adaptor.create(ID, "TableSolver")}))) 
											^(IN query ))
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
	:	^(QUERY base_query[false] ) -> ^(LET ^(NAME {adaptor.create(ID, "_ts")}) ^(DEF ^(NEW ^(SOLVER {adaptor.create(ID, "TableSolver")}))) 
											^(IN ^(PROGRAM base_query {chrAdaptor.constraint(null, "run", null)}) ))
    |	^(QUERY base_query[true] condition) -> ^(PROGRAM  
												^(LET ^(NAME {adaptor.create(ID, "_cond")}) ^(DEF condition)
													^(IN ^(PROGRAM base_query {chrAdaptor.constraint(null, "run", null)}))))												
	;

sub_query
	:	^(SUB_QUERY query) -> query
	;
	
condition
	:	^(CONDITION search_condition) -> search_condition
	;

search_condition
	:	^(comp_op term term) -> ^(NEW ^(SOLVER {adaptor.create(ID, $comp_op.solvName)}))
	|	^(NOT search_condition)	-> ^(NEW ^(SOLVER {adaptor.create(ID, "NotSolver")} ^(PARAMS search_condition)))
	|	^(boolean_op s1=search_condition s2=search_condition) -> 
			^(NEW ^(SOLVER {adaptor.create(ID, $boolean_op.solvName)} ^(PARAMS search_condition search_condition)))
	|	^(EXISTS sub_query) -> sub_query
	|	^(quantifier ^(comp_op term sub_query))
	|	^(IN term sub_query)
	;

term 
	:	attribute_spec
	|	constant
	;

base_query [Boolean condition]
@init{
var relList:List[String] = null
var alList:List[String] = null
}
	:	selector relation_list{relList = $relation_list.relList; alList = $relation_list.aliasList} -> 
			relation_list 
			{chrAdaptor.createCopyRules(relList, alList)}
			{chrAdaptor.createConditionRule($condition, alList) }
			{chrAdaptor.createSelectRule($condition)}	 
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
				^(CONSTRAINT ^(NAME {adaptor.create(ID, "temp")}) ^(ARITY {adaptor.create(INT, "1")}))
				^(CONSTRAINT ^(NAME {adaptor.create(ID, "env")}) ^(ARITY {adaptor.create(INT, "1")}))
				^(CONSTRAINT ^(NAME {adaptor.create(ID, "j0")}) ^(ARITY {adaptor.create(INT, "1")}))
				^(CONSTRAINT ^(NAME {adaptor.create(ID, "j1")}) ^(ARITY {adaptor.create(INT, "1")}))
				^(CONSTRAINT ^(NAME {adaptor.create(ID, "sol")}) ^(ARITY {adaptor.create(INT, "1")})))
				
	;

relation_spec returns [String relName, String al]
@init{
var rel:String = ""
var as: String = ""
}
@after{
$relName = rel
$al = rel + as
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
	
comp_op returns [String solvName]
	:	EQ{$solvName="EqSolver"} 
	| 	NOT_EQ{$solvName="NotEqSolver"} 
	|	LTH{$solvName="LtSolver"} 
	| 	LEQ{$solvName="LeqSolver"} 
	| 	GTH{$solvName="GtSolver"} 
	| 	GEQ{$solvName="GeqSolver"} 
	;
	
boolean_op returns [String solvName]
	:	AND {$solvName = "AndSolver"} 
	|	OR {$solvName = "OrSolver"}
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

