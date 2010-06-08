package fr.emn.fullers.parser.tree

import java.util.Iterator
import org.antlr.runtime.Token
import org.antlr.runtime.tree.{Tree, TreeIterator, CommonTreeAdaptor}
import org.antlr.runtime.misc.FastQueue;

class SCHRTreeIterator(var tree: ^) extends Iterator[^]{

    protected val adaptor = new CHRTreeAdaptor
    protected var root: ^ = tree
    protected var firstTime = true;

    // navigation nodes to return during walk and at end
    val up: ^ =  adaptor.create(Token.DOWN, "UP").asInstanceOf[^]
    val down: ^ = adaptor.create(Token.UP, "DOWN").asInstanceOf[^]
    val eof: ^ = adaptor.create(Token.EOF, "EOF").asInstanceOf[^]

    /** If we emit UP/DOWN nodes, we need to spit out multiple nodes per
     *  next() call.
     */
    protected val nodes:FastQueue[^] = new FastQueue[^]
	
	def reset{
        firstTime = true;
        tree = root;
        nodes.clear();   	
    }
	
    override def hasNext:Boolean = {
        if ( firstTime ) return root!=null;
        if ( nodes!=null && nodes.size()>0 ) return true;
        if ( tree==null ) return false;
        if ( adaptor.getChildCount(tree)>0 ) return true;
        return adaptor.getParent(tree)!=null; // back at root?
    }
    
	override def next: ^ = {
		if ( firstTime ) { // initial condition
			firstTime = false;
			if ( adaptor.getChildCount(tree)==0 ) { // single node tree (special)
				nodes.add(eof);
				tree
			}
			tree;
		}
		// if any queued up, use those first
		if ( nodes!=null && nodes.size()>0 ) 
			return nodes.remove()

		// no nodes left?
		if ( tree==null ) 
			return eof;

		// next node will be child 0 if any children
		if ( adaptor.getChildCount(tree)>0 ) {
			tree = adaptor.getChild(tree, 0).asInstanceOf[^]
			nodes.add(tree); // real node is next after DOWN
			return down;
		}
		// if no children, look for next sibling of tree or ancestor
		var parent: ^ = adaptor.getParent(tree).asInstanceOf[^]
		// while we're out of siblings, keep popping back up towards root
		while ( parent!=null &&
				adaptor.getChildIndex(tree)+1 >= adaptor.getChildCount(parent) )
		{
			nodes.add(up); // we're moving back up
			tree = parent
			parent = adaptor.getParent(tree).asInstanceOf[^]
		}
		// no nodes left?
		if ( parent==null ) {
			tree = null; // back at root? nothing left then
			nodes.add(eof); // add to queue, might have UP nodes in there
			return nodes.remove//.asInstanceOf[^]
		}

		// must have found a node with an unvisited sibling
		// move to it and return it
		val nextSiblingIndex = adaptor.getChildIndex(tree) + 1;
		tree = adaptor.getChild(parent, nextSiblingIndex).asInstanceOf[^]
		nodes.add(tree); // add to queue, might have UP nodes in there
		nodes.remove//.asInstanceOf[^]
	}
	
	 def remove { throw new UnsupportedOperationException(); }
}
