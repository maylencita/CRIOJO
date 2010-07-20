package org.antlr.runtime;

public class SRecognizerSharedState extends RecognizerSharedState {

	/** The token type for the current token -- changes name*/
	public int stype;
	
	public SRecognizerSharedState() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SRecognizerSharedState(RecognizerSharedState state) {
		super(state);
        this.stype = state.type;
	}

	public void setType(int type){
		this.stype = type;
		this.type = type;
	}
}
