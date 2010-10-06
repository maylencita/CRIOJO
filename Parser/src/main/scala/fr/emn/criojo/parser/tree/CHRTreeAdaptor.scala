package fr.emn.criojo.parser.tree

import org.antlr.runtime.Token
import org.antlr.runtime.tree.{Tree, CommonTreeAdaptor}

class CHRTreeAdaptor extends CommonTreeAdaptor{
	
	override def create(payload: Token):Object = {
		if (payload != null)			
			new ^(new CHRToken(payload))
		else
			new ^()
	}
	
	override def addChild(t:Object, child:Object) {
		t match{
			case chrtree: ^ =>
				child match{
					case tt: Tree => chrtree.addChild(tt)
					case lt: List[Tree] => chrtree.addChildren(lt)
					case _ =>
				}
			case _ =>
		}
	}

	override def createToken(tokenType:Int, text:String):Token = {
		new CHRToken(tokenType, text)
	}

	override def createToken(fromToken:Token):Token = {
		new CHRToken(fromToken)
	}

}
