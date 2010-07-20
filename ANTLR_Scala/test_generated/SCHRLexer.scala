// $ANTLR 3.2 Sep 23, 2009 12:02:23 test_grammar/SCHR.g 2010-06-08 16:45:58

package fr.emn.fullers.parser.schr;

//import basicCHR.interpreter.tree.*;
import fr.emn.fullers.parser.tree._


import org.antlr.runtime._;
import BaseRecognizer.HIDDEN
import java.util.Stack;
//import java.util.List;
import java.util.ArrayList;

class SCHRLexer(input:CharStream, state:SRecognizerSharedState) 
extends SLexer(input, state) {
	
	def this(input: CharStream){
		this(input, new SRecognizerSharedState())
	}



//MEMOIZE?
		//delegates
		// direct delegates


    	val PACKAGE:Int = 22
    	val EXPONENT:Int = 54
    	val LT:Int = 36
    	val LBRACK:Int = 45
    	val EQ_OP:Int = 49
    	val DEF:Int = 16
    	val POINT:Int = 31
    	val HEAD:Int = 8
    	val LTEQ:Int = 37
    	val OCTAL_ESC:Int = 57
    	val CHAR:Int = 53
    	val ID:Int = 26
    	val EOF:Int = -1
    	val LPAREN:Int = 32
    	val ARROBAS:Int = 29
    	val TAIL:Int = 21
    	val RPAREN:Int = 33
    	val IMPORT:Int = 14
    	val NAME:Int = 11
    	val ESC_SEQ:Int = 52
    	val SLASH:Int = 40
    	val IN:Int = 43
    	val COMMA:Int = 30
    	val IS:Int = 34
    	val PLUS:Int = 38
    	val BODY:Int = 9
    	val EQ:Int = 35
    	val COMMENT:Int = 50
    	val SOLVERCLASS:Int = 4
    	val PARAMS:Int = 17
    	val RBRACK:Int = 46
    	val RULE:Int = 6
    	val UNICODE_ESC:Int = 56
    	val VALUES:Int = 13
    	val HEX_DIGIT:Int = 55
    	val INT:Int = 41
    	val MINUS:Int = 39
    	val LIST:Int = 19
    	val SEMI:Int = 24
    	val LCURL:Int = 27
    	val WS:Int = 51
    	val CONSDEF:Int = 10
    	val RCURL:Int = 28
    	val ALIAS:Int = 15
    	val EMPTYLIST:Int = 20
    	val SOLVER:Int = 25
    	val START:Int = 18
    	val PROGRAM:Int = 5
    	val ARITY:Int = 12
    	val USE:Int = 44
    	val CONSTRAINT:Int = 7
    	val LET:Int = 42
    	val BAR:Int = 47
    	val STRING:Int = 23
    	val CHR_SIMP:Int = 48

    override def getGrammarFileName = "test_grammar/SCHR.g"

    // $ANTLR start "PACKAGE"
    @throws(classOf[RecognitionException])
     final def mPACKAGE() /*throws RecognitionException*/ {
        try {
            var _type = PACKAGE;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:181:2: ( 'package' )
            // test_grammar/SCHR.g:181:4: 'package'
            {
            smatch("package"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PACKAGE"

    // $ANTLR start "SOLVER"
    @throws(classOf[RecognitionException])
     final def mSOLVER() /*throws RecognitionException*/ {
        try {
            var _type = SOLVER;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:184:2: ( 'solver' )
            // test_grammar/SCHR.g:184:4: 'solver'
            {
            smatch("solver"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SOLVER"

    // $ANTLR start "ARROBAS"
    @throws(classOf[RecognitionException])
     final def mARROBAS() /*throws RecognitionException*/ {
        try {
            var _type = ARROBAS;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:187:2: ( '@' )
            // test_grammar/SCHR.g:187:4: '@'
            {
            smatch('@'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ARROBAS"

    // $ANTLR start "CHR_SIMP"
    @throws(classOf[RecognitionException])
     final def mCHR_SIMP() /*throws RecognitionException*/ {
        try {
            var _type = CHR_SIMP;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:190:2: ( '<=>' )
            // test_grammar/SCHR.g:190:4: '<=>'
            {
            smatch("<=>"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHR_SIMP"

    // $ANTLR start "IS"
    @throws(classOf[RecognitionException])
     final def mIS() /*throws RecognitionException*/ {
        try {
            var _type = IS;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:193:2: ( 'is' )
            // test_grammar/SCHR.g:193:4: 'is'
            {
            smatch("is"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IS"

    // $ANTLR start "CONSTRAINT"
    @throws(classOf[RecognitionException])
     final def mCONSTRAINT() /*throws RecognitionException*/ {
        try {
            var _type = CONSTRAINT;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:196:2: ( 'constraint' )
            // test_grammar/SCHR.g:196:4: 'constraint'
            {
            smatch("constraint"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CONSTRAINT"

    // $ANTLR start "LET"
    @throws(classOf[RecognitionException])
     final def mLET() /*throws RecognitionException*/ {
        try {
            var _type = LET;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:199:2: ( 'let' )
            // test_grammar/SCHR.g:199:4: 'let'
            {
            smatch("let"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LET"

    // $ANTLR start "IN"
    @throws(classOf[RecognitionException])
     final def mIN() /*throws RecognitionException*/ {
        try {
            var _type = IN;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:202:2: ( 'in' )
            // test_grammar/SCHR.g:202:4: 'in'
            {
            smatch("in"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IN"

    // $ANTLR start "USE"
    @throws(classOf[RecognitionException])
     final def mUSE() /*throws RecognitionException*/ {
        try {
            var _type = USE;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:205:2: ( 'use' )
            // test_grammar/SCHR.g:205:4: 'use'
            {
            smatch("use"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "USE"

    // $ANTLR start "EQ"
    @throws(classOf[RecognitionException])
     final def mEQ() /*throws RecognitionException*/ {
        try {
            var _type = EQ;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:208:2: ( '=' )
            // test_grammar/SCHR.g:208:4: '='
            {
            smatch('='); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "EQ_OP"
    @throws(classOf[RecognitionException])
     final def mEQ_OP() /*throws RecognitionException*/ {
        try {
            var _type = EQ_OP;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:211:2: ( '==' )
            // test_grammar/SCHR.g:211:4: '=='
            {
            smatch("=="); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ_OP"

    // $ANTLR start "LT"
    @throws(classOf[RecognitionException])
     final def mLT() /*throws RecognitionException*/ {
        try {
            var _type = LT;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:214:2: ( '<' )
            // test_grammar/SCHR.g:214:4: '<'
            {
            smatch('<'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "LTEQ"
    @throws(classOf[RecognitionException])
     final def mLTEQ() /*throws RecognitionException*/ {
        try {
            var _type = LTEQ;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:217:2: ( '=<' )
            // test_grammar/SCHR.g:217:4: '=<'
            {
            smatch("=<"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LTEQ"

    // $ANTLR start "PLUS"
    @throws(classOf[RecognitionException])
     final def mPLUS() /*throws RecognitionException*/ {
        try {
            var _type = PLUS;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:220:2: ( '+' )
            // test_grammar/SCHR.g:220:4: '+'
            {
            smatch('+'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    @throws(classOf[RecognitionException])
     final def mMINUS() /*throws RecognitionException*/ {
        try {
            var _type = MINUS;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:223:2: ( '-' )
            // test_grammar/SCHR.g:223:4: '-'
            {
            smatch('-'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "LPAREN"
    @throws(classOf[RecognitionException])
     final def mLPAREN() /*throws RecognitionException*/ {
        try {
            var _type = LPAREN;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:225:9: ( '(' )
            // test_grammar/SCHR.g:225:11: '('
            {
            smatch('('); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    @throws(classOf[RecognitionException])
     final def mRPAREN() /*throws RecognitionException*/ {
        try {
            var _type = RPAREN;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:228:8: ( ')' )
            // test_grammar/SCHR.g:228:10: ')'
            {
            smatch(')'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LCURL"
    @throws(classOf[RecognitionException])
     final def mLCURL() /*throws RecognitionException*/ {
        try {
            var _type = LCURL;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:231:7: ( '{' )
            // test_grammar/SCHR.g:231:9: '{'
            {
            smatch('{'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LCURL"

    // $ANTLR start "RCURL"
    @throws(classOf[RecognitionException])
     final def mRCURL() /*throws RecognitionException*/ {
        try {
            var _type = RCURL;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:234:7: ( '}' )
            // test_grammar/SCHR.g:234:9: '}'
            {
            smatch('}'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RCURL"

    // $ANTLR start "RBRACK"
    @throws(classOf[RecognitionException])
     final def mRBRACK() /*throws RecognitionException*/ {
        try {
            var _type = RBRACK;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:238:2: ( ']' )
            // test_grammar/SCHR.g:238:4: ']'
            {
            smatch(']'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACK"

    // $ANTLR start "LBRACK"
    @throws(classOf[RecognitionException])
     final def mLBRACK() /*throws RecognitionException*/ {
        try {
            var _type = LBRACK;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:241:2: ( '[' )
            // test_grammar/SCHR.g:241:4: '['
            {
            smatch('['); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACK"

    // $ANTLR start "COMMA"
    @throws(classOf[RecognitionException])
     final def mCOMMA() /*throws RecognitionException*/ {
        try {
            var _type = COMMA;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:244:7: ( ',' )
            // test_grammar/SCHR.g:244:9: ','
            {
            smatch(','); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "POINT"
    @throws(classOf[RecognitionException])
     final def mPOINT() /*throws RecognitionException*/ {
        try {
            var _type = POINT;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:247:7: ( '.' )
            // test_grammar/SCHR.g:247:9: '.'
            {
            smatch('.'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "POINT"

    // $ANTLR start "SEMI"
    @throws(classOf[RecognitionException])
     final def mSEMI() /*throws RecognitionException*/ {
        try {
            var _type = SEMI;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:250:6: ( ';' )
            // test_grammar/SCHR.g:250:8: ';'
            {
            smatch(';'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "SLASH"
    @throws(classOf[RecognitionException])
     final def mSLASH() /*throws RecognitionException*/ {
        try {
            var _type = SLASH;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:252:7: ( '/' )
            // test_grammar/SCHR.g:252:10: '/'
            {
            smatch('/'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SLASH"

    // $ANTLR start "BAR"
    @throws(classOf[RecognitionException])
     final def mBAR() /*throws RecognitionException*/ {
        try {
            var _type = BAR;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:254:6: ( '|' )
            // test_grammar/SCHR.g:254:9: '|'
            {
            smatch('|'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BAR"

    // $ANTLR start "ID"
    @throws(classOf[RecognitionException])
     final def mID() /*throws RecognitionException*/ {
        try {
            var _type = ID;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:257:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // test_grammar/SCHR.g:257:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                val mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // test_grammar/SCHR.g:257:31: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            //loop1:
            var guard = true
            while(guard) {
                var alt1=2;
                var LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                alt1 match{
            		case 1 =>
            		    // test_grammar/SCHR.g:
            		    {
            		    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            		        input.consume();

            		    }
            		    else {
            		        val mse = new MismatchedSetException(null,input);
            		        recover(mse);
            		        throw mse;}


            		    }
            		case _ =>
            		    guard = false //loop1;
                }
            } //while (true);


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "INT"
    @throws(classOf[RecognitionException])
     final def mINT() /*throws RecognitionException*/ {
        try {
            var _type = INT;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:260:5: ( ( '0' .. '9' )+ )
            // test_grammar/SCHR.g:260:7: ( '0' .. '9' )+
            {
            // test_grammar/SCHR.g:260:7: ( '0' .. '9' )+
            var cnt2=0;
            //loop2:
            var guard = true
            while(guard) {
                var alt2=2
                var LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                alt2 match{
            		case 1 =>
            		    // test_grammar/SCHR.g:260:7: '0' .. '9'
            		    {
            		    matchRange('0','9'); 

            		    }
            		case _ =>
            		    if ( cnt2 >= 1 ) 
            		    	guard = false
            		    else{	
            	            val eee =
            	                new EarlyExitException(2, input);
            	            throw eee
            	        }
                }
              
            	cnt2 = cnt2 + 1
            }


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "COMMENT"
    @throws(classOf[RecognitionException])
     final def mCOMMENT() /*throws RecognitionException*/ {
        try {
            var _type = COMMENT;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:270:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
            var alt6=2;
            var LA6_0 = input.LA(1);

            if ( (LA6_0=='/') ) {
                var LA6_1 = input.LA(2);

                if ( (LA6_1=='/') ) {
                    alt6=1;
                }
                else if ( (LA6_1=='*') ) {
                    alt6=2;
                }
                else {
                    val nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else {
                val nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            alt6 match{
                case 1 =>
                    // test_grammar/SCHR.g:270:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    smatch("//"); 

                    // test_grammar/SCHR.g:270:14: (~ ( '\\n' | '\\r' ) )*
                    //loop3:
                    var guard = true
                    while(guard) {
                        var alt3=2;
                        var LA3_0 = input.LA(1);

                        if ( ((LA3_0>='\u0000' && LA3_0<='\t')||(LA3_0>='\u000B' && LA3_0<='\f')||(LA3_0>='\u000E' && LA3_0<='\uFFFF')) ) {
                            alt3=1;
                        }


                        alt3 match{
                    		case 1 =>
                    		    // test_grammar/SCHR.g:270:14: ~ ( '\\n' | '\\r' )
                    		    {
                    		    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    		        input.consume();

                    		    }
                    		    else {
                    		        val mse = new MismatchedSetException(null,input);
                    		        recover(mse);
                    		        throw mse;}


                    		    }
                    		case _ =>
                    		    guard = false //loop3;
                        }
                    } //while (true);

                    // test_grammar/SCHR.g:270:28: ( '\\r' )?
                    var alt4=2;
                    var LA4_0 = input.LA(1);

                    if ( (LA4_0=='\r') ) {
                        alt4=1;
                    }
                    alt4 match{
                        case 1 =>
                            // test_grammar/SCHR.g:270:28: '\\r'
                            {
                            smatch('\r'); 

                            }
                        case _ => //Do nothing
                    }

                    smatch('\n'); 
                    _channel=HIDDEN;

                    }case 2 =>
                    // test_grammar/SCHR.g:271:9: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    smatch("/*"); 

                    // test_grammar/SCHR.g:271:14: ( options {greedy=false; } : . )*
                    //loop5:
                    var guard = true
                    while(guard) {
                        var alt5=2;
                        var LA5_0 = input.LA(1);

                        if ( (LA5_0=='*') ) {
                            var LA5_1 = input.LA(2);

                            if ( (LA5_1=='/') ) {
                                alt5=2;
                            }
                            else if ( ((LA5_1>='\u0000' && LA5_1<='.')||(LA5_1>='0' && LA5_1<='\uFFFF')) ) {
                                alt5=1;
                            }


                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<=')')||(LA5_0>='+' && LA5_0<='\uFFFF')) ) {
                            alt5=1;
                        }


                        alt5 match{
                    		case 1 =>
                    		    // test_grammar/SCHR.g:271:42: .
                    		    {
                    		    matchAny(); 

                    		    }
                    		case _ =>
                    		    guard = false //loop5;
                        }
                    } //while (true);

                    smatch("*/"); 

                    _channel=HIDDEN;

                    }
                case _ => //Do nothing
            }
            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    @throws(classOf[RecognitionException])
     final def mWS() /*throws RecognitionException*/ {
        try {
            var _type = WS;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:274:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // test_grammar/SCHR.g:274:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                val mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "STRING"
    @throws(classOf[RecognitionException])
     final def mSTRING() /*throws RecognitionException*/ {
        try {
            var _type = STRING;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:282:5: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // test_grammar/SCHR.g:282:8: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            smatch('\"'); 
            // test_grammar/SCHR.g:282:12: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
            //loop7:
            var guard = true
            while(guard) {
                var alt7=3;
                var LA7_0 = input.LA(1);

                if ( (LA7_0=='\\') ) {
                    alt7=1;
                }
                else if ( ((LA7_0>='\u0000' && LA7_0<='!')||(LA7_0>='#' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                    alt7=2;
                }


                alt7 match{
            		case 1 =>
            		    // test_grammar/SCHR.g:282:14: ESC_SEQ
            		    {
            		    mESC_SEQ(); 

            		    }case 2 =>
            		    // test_grammar/SCHR.g:282:24: ~ ( '\\\\' | '\"' )
            		    {
            		    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            		        input.consume();

            		    }
            		    else {
            		        val mse = new MismatchedSetException(null,input);
            		        recover(mse);
            		        throw mse;}


            		    }
            		case _ =>
            		    guard = false //loop7;
                }
            } //while (true);

            smatch('\"'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "CHAR"
    @throws(classOf[RecognitionException])
     final def mCHAR() /*throws RecognitionException*/ {
        try {
            var _type = CHAR;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // test_grammar/SCHR.g:285:5: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // test_grammar/SCHR.g:285:8: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            smatch('\''); 
            // test_grammar/SCHR.g:285:13: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
            var alt8=2;
            var LA8_0 = input.LA(1);

            if ( (LA8_0=='\\') ) {
                alt8=1;
            }
            else if ( ((LA8_0>='\u0000' && LA8_0<='&')||(LA8_0>='(' && LA8_0<='[')||(LA8_0>=']' && LA8_0<='\uFFFF')) ) {
                alt8=2;
            }
            else {
                val nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            alt8 match{
                case 1 =>
                    // test_grammar/SCHR.g:285:15: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }case 2 =>
                    // test_grammar/SCHR.g:285:25: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        val mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                case _ => //Do nothing
            }

            smatch('\''); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "EXPONENT"
    @throws(classOf[RecognitionException])
     final def mEXPONENT() /*throws RecognitionException*/ {
        try {
            // test_grammar/SCHR.g:289:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // test_grammar/SCHR.g:289:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                val mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // test_grammar/SCHR.g:289:22: ( '+' | '-' )?
            var alt9=2;
            var LA9_0 = input.LA(1);

            if ( (LA9_0=='+'||LA9_0=='-') ) {
                alt9=1;
            }
            alt9 match{
                case 1 =>
                    // test_grammar/SCHR.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        val mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                case _ => //Do nothing
            }

            // test_grammar/SCHR.g:289:33: ( '0' .. '9' )+
            var cnt10=0;
            //loop10:
            var guard = true
            while(guard) {
                var alt10=2
                var LA10_0 = input.LA(1);

                if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                    alt10=1;
                }


                alt10 match{
            		case 1 =>
            		    // test_grammar/SCHR.g:289:34: '0' .. '9'
            		    {
            		    matchRange('0','9'); 

            		    }
            		case _ =>
            		    if ( cnt10 >= 1 ) 
            		    	guard = false
            		    else{	
            	            val eee =
            	                new EarlyExitException(10, input);
            	            throw eee
            	        }
                }
              
            	cnt10 = cnt10 + 1
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "HEX_DIGIT"
    @throws(classOf[RecognitionException])
     final def mHEX_DIGIT() /*throws RecognitionException*/ {
        try {
            // test_grammar/SCHR.g:292:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // test_grammar/SCHR.g:292:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                val mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "ESC_SEQ"
    @throws(classOf[RecognitionException])
     final def mESC_SEQ() /*throws RecognitionException*/ {
        try {
            // test_grammar/SCHR.g:296:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            var alt11=3;
            var LA11_0 = input.LA(1);

            if ( (LA11_0=='\\') ) {
                input.LA(2) match{
                	case '\"' => alt11=1;
                	case '\'' => alt11=1;
                	case '\\' => alt11=1;
                	case 'b' => alt11=1;
                	case 'f' => alt11=1;
                	case 'n' => alt11=1;
                	case 'r' => alt11=1;
                	case 't' => alt11=1;    
                	case 'u' => alt11=2;    
                	case '0' => alt11=3;
                	case '1' => alt11=3;
                	case '2' => alt11=3;
                	case '3' => alt11=3;
                	case '4' => alt11=3;
                	case '5' => alt11=3;
                	case '6' => alt11=3;
                	case '7' => alt11=3;    
                	case _ =>
                		    val nvae =
                		        new NoViableAltException("", 11, 1, input);

                		    throw nvae;

                }

            }
            else {
                val nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            alt11 match{
                case 1 =>
                    // test_grammar/SCHR.g:296:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    smatch('\\'); 
                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();

                    }
                    else {
                        val mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }case 2 =>
                    // test_grammar/SCHR.g:297:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }case 3 =>
                    // test_grammar/SCHR.g:298:9: OCTAL_ESC
                    {
                    mOCTAL_ESC(); 

                    }
                case _ => //Do nothing
            }
        }
        finally {
        }
    }
    // $ANTLR end "ESC_SEQ"

    // $ANTLR start "OCTAL_ESC"
    @throws(classOf[RecognitionException])
     final def mOCTAL_ESC() /*throws RecognitionException*/ {
        try {
            // test_grammar/SCHR.g:303:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            var alt12=3;
            var LA12_0 = input.LA(1);

            if ( (LA12_0=='\\') ) {
                var LA12_1 = input.LA(2);

                if ( ((LA12_1>='0' && LA12_1<='3')) ) {
                    var LA12_2 = input.LA(3);

                    if ( ((LA12_2>='0' && LA12_2<='7')) ) {
                        var LA12_4 = input.LA(4);

                        if ( ((LA12_4>='0' && LA12_4<='7')) ) {
                            alt12=1;
                        }
                        else {
                            alt12=2;}
                    }
                    else {
                        alt12=3;}
                }
                else if ( ((LA12_1>='4' && LA12_1<='7')) ) {
                    var LA12_3 = input.LA(3);

                    if ( ((LA12_3>='0' && LA12_3<='7')) ) {
                        alt12=2;
                    }
                    else {
                        alt12=3;}
                }
                else {
                    val nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;
                }
            }
            else {
                val nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            alt12 match{
                case 1 =>
                    // test_grammar/SCHR.g:303:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // test_grammar/SCHR.g:303:14: ( '0' .. '3' )
                    // test_grammar/SCHR.g:303:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // test_grammar/SCHR.g:303:25: ( '0' .. '7' )
                    // test_grammar/SCHR.g:303:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // test_grammar/SCHR.g:303:36: ( '0' .. '7' )
                    // test_grammar/SCHR.g:303:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }case 2 =>
                    // test_grammar/SCHR.g:304:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // test_grammar/SCHR.g:304:14: ( '0' .. '7' )
                    // test_grammar/SCHR.g:304:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // test_grammar/SCHR.g:304:25: ( '0' .. '7' )
                    // test_grammar/SCHR.g:304:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }case 3 =>
                    // test_grammar/SCHR.g:305:9: '\\\\' ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // test_grammar/SCHR.g:305:14: ( '0' .. '7' )
                    // test_grammar/SCHR.g:305:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                case _ => //Do nothing
            }
        }
        finally {
        }
    }
    // $ANTLR end "OCTAL_ESC"

    // $ANTLR start "UNICODE_ESC"
    @throws(classOf[RecognitionException])
     final def mUNICODE_ESC() /*throws RecognitionException*/ {
        try {
            // test_grammar/SCHR.g:310:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // test_grammar/SCHR.g:310:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            smatch('\\'); 
            smatch('u'); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "UNICODE_ESC"

    @throws(classOf[RecognitionException])
     def mTokens() /*throws RecognitionException */{
        // test_grammar/SCHR.g:1:8: ( PACKAGE | SOLVER | ARROBAS | CHR_SIMP | IS | CONSTRAINT | LET | IN | USE | EQ | EQ_OP | LT | LTEQ | PLUS | MINUS | LPAREN | RPAREN | LCURL | RCURL | RBRACK | LBRACK | COMMA | POINT | SEMI | SLASH | BAR | ID | INT | COMMENT | WS | STRING | CHAR )
        var alt13=32;
        alt13 = dfa13.predict(input);
        alt13 match{
            case 1 =>
                // test_grammar/SCHR.g:1:10: PACKAGE
                {
                mPACKAGE(); 

                }case 2 =>
                // test_grammar/SCHR.g:1:18: SOLVER
                {
                mSOLVER(); 

                }case 3 =>
                // test_grammar/SCHR.g:1:25: ARROBAS
                {
                mARROBAS(); 

                }case 4 =>
                // test_grammar/SCHR.g:1:33: CHR_SIMP
                {
                mCHR_SIMP(); 

                }case 5 =>
                // test_grammar/SCHR.g:1:42: IS
                {
                mIS(); 

                }case 6 =>
                // test_grammar/SCHR.g:1:45: CONSTRAINT
                {
                mCONSTRAINT(); 

                }case 7 =>
                // test_grammar/SCHR.g:1:56: LET
                {
                mLET(); 

                }case 8 =>
                // test_grammar/SCHR.g:1:60: IN
                {
                mIN(); 

                }case 9 =>
                // test_grammar/SCHR.g:1:63: USE
                {
                mUSE(); 

                }case 10 =>
                // test_grammar/SCHR.g:1:67: EQ
                {
                mEQ(); 

                }case 11 =>
                // test_grammar/SCHR.g:1:70: EQ_OP
                {
                mEQ_OP(); 

                }case 12 =>
                // test_grammar/SCHR.g:1:76: LT
                {
                mLT(); 

                }case 13 =>
                // test_grammar/SCHR.g:1:79: LTEQ
                {
                mLTEQ(); 

                }case 14 =>
                // test_grammar/SCHR.g:1:84: PLUS
                {
                mPLUS(); 

                }case 15 =>
                // test_grammar/SCHR.g:1:89: MINUS
                {
                mMINUS(); 

                }case 16 =>
                // test_grammar/SCHR.g:1:95: LPAREN
                {
                mLPAREN(); 

                }case 17 =>
                // test_grammar/SCHR.g:1:102: RPAREN
                {
                mRPAREN(); 

                }case 18 =>
                // test_grammar/SCHR.g:1:109: LCURL
                {
                mLCURL(); 

                }case 19 =>
                // test_grammar/SCHR.g:1:115: RCURL
                {
                mRCURL(); 

                }case 20 =>
                // test_grammar/SCHR.g:1:121: RBRACK
                {
                mRBRACK(); 

                }case 21 =>
                // test_grammar/SCHR.g:1:128: LBRACK
                {
                mLBRACK(); 

                }case 22 =>
                // test_grammar/SCHR.g:1:135: COMMA
                {
                mCOMMA(); 

                }case 23 =>
                // test_grammar/SCHR.g:1:141: POINT
                {
                mPOINT(); 

                }case 24 =>
                // test_grammar/SCHR.g:1:147: SEMI
                {
                mSEMI(); 

                }case 25 =>
                // test_grammar/SCHR.g:1:152: SLASH
                {
                mSLASH(); 

                }case 26 =>
                // test_grammar/SCHR.g:1:158: BAR
                {
                mBAR(); 

                }case 27 =>
                // test_grammar/SCHR.g:1:162: ID
                {
                mID(); 

                }case 28 =>
                // test_grammar/SCHR.g:1:165: INT
                {
                mINT(); 

                }case 29 =>
                // test_grammar/SCHR.g:1:169: COMMENT
                {
                mCOMMENT(); 

                }case 30 =>
                // test_grammar/SCHR.g:1:177: WS
                {
                mWS(); 

                }case 31 =>
                // test_grammar/SCHR.g:1:180: STRING
                {
                mSTRING(); 

                }case 32 =>
                // test_grammar/SCHR.g:1:187: CHAR
                {
                mCHAR(); 

                }
            case _ => //Do nothing
        }

    }


    protected val /*DFA13*/ dfa13 = new DFA13(this)
    /*
    final val DFA13_eotS =
        "\1\uffff\2\27\1\uffff\1\37\4\27\1\47\13\uffff\1\51\6\uffff\2\27"+
        "\2\uffff\1\54\1\55\3\27\5\uffff\2\27\2\uffff\1\27\1\64\1\65\3\27"+
        "\2\uffff\4\27\1\75\1\27\1\77\1\uffff\1\27\1\uffff\2\27\1\103\1\uffff";
    final val DFA13_eofS =
        "\104\uffff";
    final val DFA13_minS =
        "\1\11\1\141\1\157\1\uffff\1\75\1\156\1\157\1\145\1\163\1\74\13\uffff"+
        "\1\52\6\uffff\1\143\1\154\2\uffff\2\60\1\156\1\164\1\145\5\uffff"+
        "\1\153\1\166\2\uffff\1\163\2\60\1\141\1\145\1\164\2\uffff\1\147"+
        "\2\162\1\145\1\60\1\141\1\60\1\uffff\1\151\1\uffff\1\156\1\164\1"+
        "\60\1\uffff";
    final val DFA13_maxS =
        "\1\175\1\141\1\157\1\uffff\1\75\1\163\1\157\1\145\1\163\1\75\13"+
        "\uffff\1\57\6\uffff\1\143\1\154\2\uffff\2\172\1\156\1\164\1\145"+
        "\5\uffff\1\153\1\166\2\uffff\1\163\2\172\1\141\1\145\1\164\2\uffff"+
        "\1\147\2\162\1\145\1\172\1\141\1\172\1\uffff\1\151\1\uffff\1\156"+
        "\1\164\1\172\1\uffff";
    final val DFA13_acceptS =
        "\3\uffff\1\3\6\uffff\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
        "\1\27\1\30\1\uffff\1\32\1\33\1\34\1\36\1\37\1\40\2\uffff\1\4\1\14"+
        "\5\uffff\1\13\1\15\1\12\1\35\1\31\2\uffff\1\5\1\10\6\uffff\1\7\1"+
        "\11\7\uffff\1\2\1\uffff\1\1\3\uffff\1\6";
    val DFA13_specialS =
        "\104\uffff}>";
    final val DFA13_transitionS = Array[String](
        "\2\31\2\uffff\1\31\22\uffff\1\31\1\uffff\1\32\4\uffff\1\33\1\14"+
        "\1\15\1\uffff\1\12\1\22\1\13\1\23\1\25\12\30\1\uffff\1\24\1\4\1"+
        "\11\2\uffff\1\3\32\27\1\21\1\uffff\1\20\1\uffff\1\27\1\uffff\2\27"+
        "\1\6\5\27\1\5\2\27\1\7\3\27\1\1\2\27\1\2\1\27\1\10\5\27\1\16\1\26"+
        "\1\17",
        "\1\34",
        "\1\35",
        "",
        "\1\36",
        "\1\41\4\uffff\1\40",
        "\1\42",
        "\1\43",
        "\1\44",
        "\1\46\1\45",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "\1\50\4\uffff\1\50",
        "",
        "",
        "",
        "",
        "",
        "",
        "\1\52",
        "\1\53",
        "",
        "",
        "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
        "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
        "\1\56",
        "\1\57",
        "\1\60",
        "",
        "",
        "",
        "",
        "",
        "\1\61",
        "\1\62",
        "",
        "",
        "\1\63",
        "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
        "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
        "\1\66",
        "\1\67",
        "\1\70",
        "",
        "",
        "\1\71",
        "\1\72",
        "\1\73",
        "\1\74",
        "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
        "\1\76",
        "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
        "",
        "\1\100",
        "",
        "\1\101",
        "\1\102",
        "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
        ""
    )

    final val DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    final val DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    final val DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    final val DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    final val DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    final val DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    final val DFA13_transition = new Array[Array[Short]](DFA13_transitionS.length)

    {
        for (i <- 0 to DFA13_transition.length - 1) {
            DFA13_transition(i) = DFA.unpackEncodedString(DFA13_transitionS(i))
        }
    }
    */
    class DFA13(recognizer:BaseRecognizer ) extends DFA {
    		final val DFA13_eotS =
    		    "\1\uffff\2\27\1\uffff\1\37\4\27\1\47\13\uffff\1\51\6\uffff\2\27"+
        "\2\uffff\1\54\1\55\3\27\5\uffff\2\27\2\uffff\1\27\1\64\1\65\3\27"+
        "\2\uffff\4\27\1\75\1\27\1\77\1\uffff\1\27\1\uffff\2\27\1\103\1\uffff";
    		final val DFA13_eofS =
    		    "\104\uffff";
    		final val DFA13_minS =
    		    "\1\11\1\141\1\157\1\uffff\1\75\1\156\1\157\1\145\1\163\1\74\13"+
        "\uffff\1\52\6\uffff\1\143\1\154\2\uffff\2\60\1\156\1\164\1\145\5"+
        "\uffff\1\153\1\166\2\uffff\1\163\2\60\1\141\1\145\1\164\2\uffff"+
        "\1\147\2\162\1\145\1\60\1\141\1\60\1\uffff\1\151\1\uffff\1\156\1"+
        "\164\1\60\1\uffff";
    		final val DFA13_maxS =
    		    "\1\175\1\141\1\157\1\uffff\1\75\1\163\1\157\1\145\1\163\1\75\13"+
        "\uffff\1\57\6\uffff\1\143\1\154\2\uffff\2\172\1\156\1\164\1\145"+
        "\5\uffff\1\153\1\166\2\uffff\1\163\2\172\1\141\1\145\1\164\2\uffff"+
        "\1\147\2\162\1\145\1\172\1\141\1\172\1\uffff\1\151\1\uffff\1\156"+
        "\1\164\1\172\1\uffff";
    		final val DFA13_acceptS =
    		    "\3\uffff\1\3\6\uffff\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1"+
        "\26\1\27\1\30\1\uffff\1\32\1\33\1\34\1\36\1\37\1\40\2\uffff\1\4"+
        "\1\14\5\uffff\1\13\1\15\1\12\1\35\1\31\2\uffff\1\5\1\10\6\uffff"+
        "\1\7\1\11\7\uffff\1\2\1\uffff\1\1\3\uffff\1\6";
    		val DFA13_specialS =
    		    "\104\uffff}>";
    		final val DFA13_transitionS = Array[String](
    		    "\2\31\2\uffff\1\31\22\uffff\1\31\1\uffff\1\32\4\uffff\1\33\1\14"+
    		    "\1\15\1\uffff\1\12\1\22\1\13\1\23\1\25\12\30\1\uffff\1\24\1\4"+
    		    "\1\11\2\uffff\1\3\32\27\1\21\1\uffff\1\20\1\uffff\1\27\1\uffff"+
    		    "\2\27\1\6\5\27\1\5\2\27\1\7\3\27\1\1\2\27\1\2\1\27\1\10\5\27\1"+
    		    "\16\1\26\1\17",
    		    "\1\34",
    		    "\1\35",
    		    "",
    		    "\1\36",
    		    "\1\41\4\uffff\1\40",
    		    "\1\42",
    		    "\1\43",
    		    "\1\44",
    		    "\1\46\1\45",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "\1\50\4\uffff\1\50",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "\1\52",
    		    "\1\53",
    		    "",
    		    "",
    		    "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
    		    "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
    		    "\1\56",
    		    "\1\57",
    		    "\1\60",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "\1\61",
    		    "\1\62",
    		    "",
    		    "",
    		    "\1\63",
    		    "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
    		    "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
    		    "\1\66",
    		    "\1\67",
    		    "\1\70",
    		    "",
    		    "",
    		    "\1\71",
    		    "\1\72",
    		    "\1\73",
    		    "\1\74",
    		    "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
    		    "\1\76",
    		    "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
    		    "",
    		    "\1\100",
    		    "",
    		    "\1\101",
    		    "\1\102",
    		    "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
    		    ""
    		)

            this.decisionNumber = 13;
            this.eot = DFA.unpackEncodedString(DFA13_eotS) //DFA13_eot;
            this.eof = DFA.unpackEncodedString(DFA13_eofS) //DFA13_eof;
            this.min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS) //DFA13_min;
            this.max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS) //DFA13_max;
            this.accept = DFA.unpackEncodedString(DFA13_acceptS) //DFA13_accept;
            this.special = DFA.unpackEncodedString(DFA13_specialS); //DFA13_special;
            this.transition = new Array[Array[Short]](DFA13_transitionS.length) //DFA13_transition;

    		{
    		    for (i <- 0 to DFA13_transitionS.length - 1) {
    		        this.transition(i) = DFA.unpackEncodedString(DFA13_transitionS(i))
    		    }
    		}

        override def getDescription(): String = "1:1: Tokens : ( PACKAGE | SOLVER | ARROBAS | CHR_SIMP | IS | CONSTRAINT | LET | IN | USE | EQ | EQ_OP | LT | LTEQ | PLUS | MINUS | LPAREN | RPAREN | LCURL | RCURL | RBRACK | LBRACK | COMMA | POINT | SEMI | SLASH | BAR | ID | INT | COMMENT | WS | STRING | CHAR );"
        
    }
 

}