package fr.emn.creole.parser.tree

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.{CommonTree,Tree}

//class CHRTree(typ:Int, childList:List[^]) extends ^(typ, childList){
class CHRTree(token:Token) extends CommonTree(token){ //^(token, null){

//	def this(){
//		this(null.asInstanceOf[Token])
//	}	
	
//	def this(old: ^){
//		this(old.token)//, old.childList)
//		this.childList = old.childList
//		this.childList.foreach{c => c.setParent(this); c.setChildIndex(childList.indexOf(c))}
//		this.startIndex = old.getStartIndex;
//		this.stopIndex = old.getStopIndex;
//
//		this.childIndex = old.childIndex
//		this.parent = old.parent
//	}
	
//	def this(old: CHRTree){
//		this(old.token) //, old.childList)
//		this.startIndex = old.startIndex;
//		this.stopIndex = old.stopIndex;
//	}
	
//	def getSuper: ^ = ^(this.token, childList) //this.asInstanceOf[^]		                                   
	                                    
//	override def dupNode: Tree = new CHRTree(this)
		
//	def getStartIndex = startIndex	
//	def stopIndex = stopIndex
//	
//	def setStartIndex(startIndex: Int)
//		startIndex = startIndex
//		
//	def setStopIndex(stopIndex: Int)
//		stopIndex = stopIndex
}

//case class ^(typ:Int, var childList:List[^]) extends CommonTree(){
case class ^(tok:Token, var childList:List[^]) extends CHRTree(tok){ //CommonTree(tok){
	
	def this(){
		this(null, null)
	}	
	
	def this(tok: Token){
		this(tok, null)
	}
	
	def this(old: ^){
		this(old.token) //, old.childList)
		this.startIndex = old.startIndex
		this.stopIndex = old.stopIndex
//		setStartIndex(old.getStartIndex) //old.startIndex;
//		setStopIndex(old.getStopIndex) //old.stopIndex;
	}

	if (childList != null){
		var i = 0
		childList.foreach{
			k => k.setParent(this)
			k.setChildIndex(i)
			i += 1
		}//	registerChild(k)}
	}	

	override def dupNode: Tree = new ^(this)
	
	/** Add all elements of kids list as children of this node */
	def addChildren(kids: List[Object]) {
		for (i <- kids.iterator){
			i match{
				case t: ^ => addChild(t)
				case _ =>
			}
		}
	}	

//	override def dupNode: Tree = new ^(this)
	
	/** Add t as child of this node.
	 *
	 *  Warning: if t has no children, but child does
	 *  and child isNil then this routine moves children to t via
	 *  t.children = child.children; i.e., without copying the array.
	 */
	override def addChild(t: Tree) {
		t match{
			case ct: ^ =>
				if (ct.isNil){
					if (ct.childList != null){
						if ( this.childList!=null && this.childList == ct.childList ) {
							throw new RuntimeException("attempt to add child list to itself");
						}else{
							if (this.childList != null){
								for(c <- ct.childList.iterator){
									this.childList = this.childList ::: List(c)
									c.setParent(this)
									c.setChildIndex(childList.size - 1)
								}
							}else{
								this.childList = ct.childList
								freshenParentAndChildIndexes
							}
						}						
					}
				}else{
					initChildList
					//TODO Delete
//					children.add(ct)
					
					childList = childList ::: List(ct)
					ct.setParent(this)
					ct.setChildIndex(childList.size - 1)
				}
			case _ => 
		}
	}	
	
	override def getChild(i:Int):Tree = {
		if ( childList==null || i>=childList.size ) {
			null
		}else
			childList(i)
	}

	override def getChildren: java.util.List[_] = {
		for (child <- childList.iterator){
			children.add(child.asInstanceOf)
		}
		super.getChildren
	}
	
	override def getFirstChildWithType(typ:Int):Tree = {
		for (child <- childList){
			if (child.getType == typ)
				return child
		}
		return null
	}

	override def getChildCount(): Int = {
		if ( childList==null )
			0
		else 
			childList.size
	}
	
	def getStartIndex = startIndex
	def getStopIndex = stopIndex
	
	def initChildList{
		if(childList == null)
			childList = List()
		
		//TODO Delete	
//		if(children == null)
//			createChildrenList
	}
	
	override def setChild(i:Int, t:Tree) {
		if (t != null){
			if (t.isNil)
				throw new IllegalArgumentException("Can't set single child to a list")
			else{
//				initChildList
//				childList(i) = t
//				t.setParent(this)
				addChild(t)
				t.setChildIndex(i)
			}
		}
	}
	
	override def deleteChild(i:Int):Object = {
		if ( childList==null ) {
			null
		}else{
			childList.find(_.equals(i)) match{
				case Some(c) => 
					childList = childList.filterNot(_ == c)
					this.freshenParentAndChildIndexes(i);
					c					
				case _ => null
			}
		}
	}

	override def replaceChildren(startChildIndex: Int, stopChildIndex: Int, t:Object) {
		println("Warning! method replaceChildren called on super")
		super.replaceChildren(startChildIndex, stopChildIndex, t)
	}

    /** Print out a whole tree not just a node */
    override def toStringTree(): String = {
		if ( childList==null || childList.size==0 ) {
			this.toString()
		}else{
			val buf = new StringBuffer();
			if ( !isNil() ) {
				buf.append("(");
				buf.append(this.toString());
				buf.append(' ');
			}
			var first = true
			childList.foreach{
				c => if ( first ){first = false}else{buf.append(' ')}	
				buf.append(c.toStringTree())
			}
			if ( !isNil() ) {
				buf.append(")")
			}
			buf.toString
		}
	}

    /** For every node in this subtree, make sure it's start/stop token's
     *  are set.  Walk depth first, visit bottom up.  Only updates nodes
     *  with at least one token index < 0.
     */
    override def setUnknownTokenBoundaries {
        if ( childList==null ) {
            if ( startIndex<0 || stopIndex<0 ) {
                startIndex = token.getTokenIndex()
                stopIndex = token.getTokenIndex()
            }
            return;
        }
        for(child <- childList.iterator){
        	child.setUnknownTokenBoundaries
        }
        if ( startIndex>=0 && stopIndex>=0 ) return; // already set
        if ( childList.size > 0 ) {
            val firstChild = childList(0)
            val lastChild = childList(childList.size - 1)
            startIndex = firstChild.getTokenStartIndex()
            stopIndex = lastChild.getTokenStopIndex()
        }
    }
    
//	def getChildList = this.childList
	
//	override def toString = {
//		if (this.childList == null)
//			super.toString
//		else
//			"(" + this.token.getText + " " + childList.foreach(_.toString) + ")"
//	}
	
}

