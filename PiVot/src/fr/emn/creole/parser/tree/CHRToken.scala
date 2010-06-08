package fr.emn.fullers.parser.tree

import org.antlr.runtime.{Token, CommonToken}

class CHRToken(`type`:Int, text:String) extends CommonToken(`type`, text){

	def this(oldToken: Token){
		this(oldToken.getType, oldToken.getText)
	}
	
	override def equals(that: Any):Boolean={
		that match{
			case t2: Token => t2.getType == this.getType
			case _ => false
		}
	}

}
