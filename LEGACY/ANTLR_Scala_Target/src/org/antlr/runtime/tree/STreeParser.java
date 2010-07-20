package org.antlr.runtime.tree;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.tree.TreeParser;

public class STreeParser extends TreeParser {

	public STreeParser(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	public STreeParser(TreeNodeStream input) {
		super(input);
	}

	public Object smatch(IntStream input, int ttype, BitSet follow)
			throws RecognitionException {
		return super.match(input, ttype, follow);
	}
	
}
