package fr.emn.creole.parser.tree

import org.antlr.runtime.{Token}
import org.antlr.runtime.tree.{Tree, CommonTreeAdaptor}

class SQL2CHRTreeConstructor(tokNames: Array[String]) extends CHRTreeAdaptor{
	val tokens = new CHRTreeTokens(tokNames)
	
	import tokens._
  import tokens.ID
	
	def chrCreate(tokenType:Int, text:String): ^ ={
		val fromToken = new CHRToken(tokenType, text);
		new ^(fromToken)
	}
	
	def createId(text:String): ^ = new ^(chrCreate(ID.getType, text))
	
	def createCopyRules(relNames: List[String], alias: List[String]): ^ = {
		var root = nil().asInstanceOf[^]
		var i = 0
		for(r <- relNames.iterator){
			val runCons = constraint(null, "env", List(createId("Z")))
			//val runCons2 = constraint(null, "env", List(createId("Z")))
			var relCons = constraint("_ts", r, List(createId("X")))
//			var relCons2 = constraint(null, "temp", List(createId("X")))
			var copyCons = constraint(null, alias(i), List(createId("X")))
			
//			var relTree = ^(RULE, ^(HEAD, List(relCons, runCons)) :: ^(BODY, List(copyCons, relCons2, runCons2)) :: Nil)
			var relTree = ^(RULE, ^(HEAD, List(relCons, runCons)) :: ^(BODY, List(copyCons)) :: Nil)
			addChild(root, relTree)

//			var restoreTree = ^(RULE, ^(HEAD, List(constraint(null, "temp", List(createId("X"))))) :: ^(BODY, List(constraint("_ts", r, List(createId("X"))))) :: Nil)
//			addChild(root, restoreTree)
			
			i += 1
		}
		root
	}

	def createConditionRule(cond:Boolean, alias: List[String]): ^ ={
		var root = nil().asInstanceOf[^]
		// R_as_Ri(X,Z), S_as_Si(X,Z),... <=> cond.env(Y), select(T, Z, Y');  // Y' = Y - Z
		if(cond){
			var head:List[^] = List()
			for (a <- alias.iterator){
				head = head ::: List(constraint(null, a, List(createId("X")))) 
			}
			head ++= List(constraint(null, "env", List(createId("Z")))) 
			val body = constraint("_cond", "env", List(createId("Y"))) ::
							constraint(null, "j0", List(createId("X"),createId("Z"))) :: Nil
							
			root = ^(RULE, ^(HEAD, head) :: ^(BODY, body) :: Nil)
		}
		root //.asInstanceOf[CHRTree]
	}
	
	def createSelectRule(cond:Boolean): ^ = {
		var root = nil().asInstanceOf[^]
		// select(T, Z, Y'), cond.t(Y) <=> result(T, Z);
		val body = List( constraint(null, "sol", List(createId("T"), createId("Z"))) ) 
		var head = List( constraint(null, "j0", List(createId("X"),createId("Z"))) )
		if(cond){
			head = head ::: List ( constraint("_cond", "t", List(createId("Y"))) )
		}
		root = ^(RULE, ^(HEAD, head) :: ^(BODY, body) :: Nil)
		root
	}
	
	def constraint(solver:String, name:String, terms:List[^]): ^ ={
		def createTermList(): ^ = {
			var root = nil().asInstanceOf[^]
			if (terms != null)
				for(t <- terms.iterator){
					addChild(root, t)
				}
			root
		}		
		val solvNode =
			if (solver != null)
				^(SOLVER, ^(createToken(ID.getType, solver), null)::Nil)
			else
				^(SOLVER, null)
		val nameNode = ^(NAME, ^(createToken(ID.getType, name), null)::Nil)
		val valNode = if (terms != null) 
						^(VALUES,  terms)//List(createTermList()))
					else
						^(VALUES, null)
		var cTree = ^(CONSTRAINT, solvNode::nameNode::valNode::Nil)
		cTree//.asInstanceOf[CHRTree]
	}

	
//	implicit def up2CHRTree(tree: ^): CHRTree = new CHRTree(tree)
}
