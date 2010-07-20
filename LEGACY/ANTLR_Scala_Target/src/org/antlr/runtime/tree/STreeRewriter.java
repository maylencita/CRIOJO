package org.antlr.runtime.tree;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class STreeRewriter extends TreeRewriter {

	public STreeRewriter(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
		// TODO Auto-generated constructor stub
	}

	public STreeRewriter(TreeNodeStream input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	public Object smatch(IntStream input, int ttype, BitSet follow)
			throws RecognitionException {
		return super.match(input, ttype, follow);
	}

	
}
