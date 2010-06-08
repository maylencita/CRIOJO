tree grammar CHR2Text;

options{
	language = Scala;
	tokenVocab = SQL2CHR;
	ASTLabelType = CommonTree;
	backtrack = true;
	output = template;
}

@header{
package fr.emn.fullers.parser;	
}

@members{

override def getErrorMessage(e:RecognitionException, tokenNames:Array[String]):String =
{
	val stack = BaseRecognizer.getRuleInvocationStack(e, this.getClass().getName());
	var msg = e match{
		case nvae:NoViableAltException => 
					" no viable alt; token="+e.token+
					" (decision="+nvae.decisionNumber+
					" state "+nvae.stateNumber+")"+
					" decision=<<"+nvae.grammarDecisionDescription+">>";
		case _ => super.getErrorMessage(e, tokenNames)	
	}
	stack+" "+msg
}

override def getTokenErrorDisplay(t:Token):String = t.toString()

}

/***************************************************************************************
	RULES
***************************************************************************************/

start
	:	^(START packagedef? (id+=importdef)* body) -> start(packdef={$packagedef.st}, impdef={$id}, body={$body.st})		
	;

packagedef
	:	STRING -> simpleText(text={$STRING.text})
	;
	
body
	:	gensolver
	|	program	-> {$program.st}
	;
	
gensolver
	:	^(SOLVERCLASS ID? solvparams? program)
	;

program	
	:	^(PROGRAM (exps+=expression)*) -> program(exps={$exps})
	;
	
ruledef
	:	^(RULE ruleId? ^(HEAD cl1=constraintList) ^(BODY cl2=constraintList)) -> 
			rule(name={$ruleId.st}, head={$cl1.st}, body={$cl2.st})
	;
		
constraintList
	:	(cl+=constraint)* -> consList(consList={$cl})
	;	
	
constraint
	:	^(CONSTRAINT ^(SOLVER solvId?) ^(NAME consId) ^(VALUES (tl+=term)*) ) -> constraint(solv={$solvId.st}, name={$consId.st}, termList={$tl})
	|	builtinconstraint -> {$builtinconstraint.st}	
	;
		
builtinconstraint
	:	^(CONSTRAINT ^(NAME op) ^(VALUES t1=term t2=term ) ) -> binOp(op={$op.name}, term1={$t1.st}, term2={$t2.st})
	|	^(CONSTRAINT ^(NAME op) ^(VALUES t1=term t2=term t3=term) ) -> triOp(op={$op.name}, term1={$t1.st}, term2={$t2.st}, term3={$t3.st})
	;	

op returns [String name]
	:	EQ_OP{$name = "=="} | LT{$name="<"} | LTEQ{$name="<="} | PLUS{$name="+"} | MINUS{$name="-"}
	;
	
constraintsdef
	:	^(CONSDEF (cl += constraintdef)*) -> constraintdeflist(cl={$cl})
	;
		
constraintdef
	:	^(CONSTRAINT ^(NAME ID) ^(ARITY INT)) -> constraintdef(name={$ID.text}, arity={$INT.text})
	;	
	
axiom	:	constraint -> {$constraint.st}
	;			

expression
	:	constraintsdef -> {$constraintsdef.st}
	|	ruledef -> {$ruledef.st}
	|	axiom -> {$axiom.st}
	|	letexpr -> {$letexpr.st}
//	|	importdef
	;

letexpr
	:	^(LET ^(NAME solvId) ^(DEF solvdef) ^(IN program) )	-> 	letexpr(solvId={$solvId.st}, solvdef={$solvdef.st}, program={$program.st})
	;

solvdef
	:	solvinstance -> {$solvinstance.st} 
	| 	program -> {$program.st}   
	;	

importdef
	:	^(IMPORT solvname ) -> impdef(solvname={$solvname.sname})
	;

solvparams
	:	^(PARAMS (idlt+=ID)*) -> paramList(parList={$idlt})
	;

solvname returns[String sname]
	:	
		STRING{$sname = $STRING.text}
	;

solvinstance
	:	^(SOLVER ID solvparams?) -> solvinstance(solvName={$ID.text}, params={$solvparams.st})
	;

term
	:	simpleterm -> {$simpleterm.st}
	|	termlist -> {$termlist.st} 
	;
	
termlist
	:	EMPTYLIST -> emptyList()
	|	^(LIST ^(HEAD simpleterm) ^(TAIL variable)) 
	|	^(LIST (tl+=term)*) -> list(termList={$tl})
	;
			
simpleterm
	:	constant -> {$constant.st}
	|	variable -> {$variable.st}
	;

constant
	:	INT -> simpleText(text={$INT.text})
	|	STRING -> simpleText(text={$STRING.text})
	;

consId	:	ID -> simpleText(text={$ID.text})
	;

ruleId	:	ID -> simpleText(text={$ID.text})
	;	
		
variable	:	ID -> simpleText(text={$ID.text})
	;

solvId	:	ID -> simpleText(text={$ID.text})
	;
	

/*	
rule
	:	^(RULE ID head body gate? ) -> rule(name={$ID.text}, head={$head.st}, body={$body.st}, gate={$gate.st})		
	;	

head
	:	^(HEAD (rl+=relation)* ) -> head(relList={$rl})
	;
	
body
	:	^(BODY (rl+=relation)* ) -> body(relList={$rl})
	;	
	
relation
	:	^(RELATION rel_id ^(ATTRS attr_list)) -> relation(relName={($rel_id.name).toLowerCase()}, attrList={$attr_list.st})
	;

attr_list
	:	(al+=attribute_spec)* -> template(al={$al})  "<al; separator=\",\">"
	|	ASTERIX -> template() "*"
	;
	
gate
	:	^(GATE predicate ) -> gate(predicate={$predicate.st})
	;	
	
predicate
	:	^(comp_op lt=attribute_spec rt=right_term ) -> predicate(left_term={$lt.st}, right_term={$rt.st}, op={$comp_op.st})
	|	^(boolean_op lp=predicate rp=predicate) -> predicate(left_term={$lp.st}, right_term={$rp.st}, op={$boolean_op.st})
	;	

right_term
	:	attribute_spec -> template(attr={$attribute_spec.st})  "<attr>"
	|	constant -> template(id={$constant.text}) "<id>" 
	;

attribute_spec
	:	^(ATTRIBUTE_SPEC ^(RELATION rid=ID) ^(ATTRIBUTE aid=ID)) -> attribute(relName={$rid.text}, attrName={$aid.text})
	;	
		
comp_op
	:	(op=EQ | op=NOT_EQ | op=LTH | op=LEQ | op=GTH | op=GEQ) -> template(op={$op.text}) "<op>" 
	;
	
boolean_op
	:	AND -> template() "," 
	|	 OR -> template() ";"
	;

rel_id returns [String name]
	:	(id=ID){$name = $id.text;} -> template(id={$id.text}) "<id>" 
	;		

constant
	:	NUMBER 
	|	DOUBLEQUOTED_STRING
	;
*/