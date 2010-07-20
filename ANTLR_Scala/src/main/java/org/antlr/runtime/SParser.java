package org.antlr.runtime;

public class SParser extends Parser{

	public SParser(TokenStream input) {
		super(input); // highlight that we go to super to set state object
    }

	public SParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
    }
	
	public Object smatch(IntStream input, int ttype, BitSet follow)
	throws RecognitionException
	{
		return super.match(input, ttype, follow);
	}
}
