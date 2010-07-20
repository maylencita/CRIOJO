// $ANTLR 3.2 Sep 23, 2009 12:02:23 test_grammar/X.g 2010-06-08 16:55:58

package grammar_test;


import org.antlr.runtime._;
import BaseRecognizer.HIDDEN
import java.util.Stack;
//import java.util.List;
import java.util.ArrayList;

class XLexer(input:CharStream, state:SRecognizerSharedState) 
extends SLexer(input, state) {
	
	def this(input: CharStream){
		this(input, new SRecognizerSharedState())
	}



//MEMOIZE?
		//delegates
		// direct delegates


    	val EOF:Int = -1
    	val ZERO:Int = 4

    override def getGrammarFileName = "test_grammar/X.g"

    // $ANTLR start "ZERO"
    @throws(classOf[RecognitionException])
     final def mZERO() /*throws RecognitionException*/ {
        try {
            var _type = ZERO;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/X.g:16:5: ( 'O' )
            // test_grammar/X.g:16:6: 'O'
            {
            smatch('O'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ZERO"

    @throws(classOf[RecognitionException])
     def mTokens() /*throws RecognitionException */{
        // test_grammar/X.g:1:8: ( ZERO )
        // test_grammar/X.g:1:10: ZERO
        {
        mZERO(); 

        }


    }


 

}