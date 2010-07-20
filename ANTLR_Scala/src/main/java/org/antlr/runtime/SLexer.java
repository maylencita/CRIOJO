package org.antlr.runtime;

public abstract class SLexer extends Lexer {

	public SLexer() {
	}

	public SLexer(CharStream input) {
		super(input);
	}

	public SLexer(CharStream input, RecognizerSharedState state) {
		super(input, state);
	}	

	public void smatch(String s) throws MismatchedTokenException {
		super.match(s);
	}
	
	public void smatch(int c) throws MismatchedTokenException {
		super.match(c);
	}
}
