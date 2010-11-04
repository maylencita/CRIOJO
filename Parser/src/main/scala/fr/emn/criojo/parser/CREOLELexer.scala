// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/main/antlr3/fr/emn/criojo/parser/CREOLE.g 2010-11-02 18:17:38

package fr.emn.criojo.parser;

import fr.emn.criojo.parser.tree._


import org.antlr.runtime._;
import BaseRecognizer.HIDDEN
import java.util.Stack;
//import java.util.List;
import java.util.ArrayList;

class CREOLELexer(input:CharStream, state:SRecognizerSharedState) 
extends SLexer(input, state) {
	
	def this(input: CharStream){
		this(input, new SRecognizerSharedState())
	}



//MEMOIZE?
		//delegates
		// direct delegates


    	val EXPONENT:Int = 56
    	val LT:Int = 40
    	val LBRACK:Int = 29
    	val EQ_OP:Int = 39
    	val T__62:Int = 62
    	val POINT:Int = 46
    	val HEAD:Int = 8
    	val LTEQ:Int = 41
    	val OCTAL_ESC:Int = 59
    	val CHAR:Int = 55
    	val MULTI:Int = 10
    	val ATOM:Int = 5
    	val T__61:Int = 61
    	val T__60:Int = 60
    	val EOF:Int = -1
    	val DECLARATION:Int = 11
    	val LPAREN:Int = 20
    	val ZERO:Int = 33
    	val INT_ATOM:Int = 19
    	val RPAREN:Int = 23
    	val ESC_SEQ:Int = 53
    	val SLASH:Int = 47
    	val IN:Int = 38
    	val COMMA:Int = 24
    	val TILDE:Int = 27
    	val PLUS:Int = 42
    	val BODY:Int = 9
    	val UNDEF:Int = 25
    	val COMMENT:Int = 51
    	val IMARK:Int = 31
    	val RBRACK:Int = 30
    	val SCRIPT:Int = 4
    	val RULE:Int = 7
    	val NU:Int = 32
    	val R_ID:Int = 26
    	val PRIVATE:Int = 15
    	val VARS:Int = 6
    	val UNICODE_ESC:Int = 58
    	val RARROW:Int = 28
    	val HEX_DIGIT:Int = 57
    	val V_ID:Int = 36
    	val BANG:Int = 49
    	val INT:Int = 50
    	val REQUIRED:Int = 14
    	val MINUS:Int = 43
    	val TRUE:Int = 34
    	val SEMI:Int = 22
    	val EMPTY:Int = 18
    	val COLON:Int = 21
    	val LCURL:Int = 44
    	val WS:Int = 52
    	val RCURL:Int = 45
    	val EMPTYLIST:Int = 12
    	val GUARD:Int = 17
    	val PROCESS:Int = 16
    	val FALSE:Int = 35
    	val PUBLIC:Int = 13
    	val LET:Int = 37
    	val BAR:Int = 48
    	val STRING:Int = 54

    override def getGrammarFileName = "src/main/antlr3/fr/emn/criojo/parser/CREOLE.g"

    // $ANTLR start "T__60"
    @throws(classOf[RecognitionException])
     final def mT__60() /*throws RecognitionException*/ {
        try {
            var _type = T__60;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:13:7: ( 'provided' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:13:9: 'provided'
            {
            smatch("provided"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    @throws(classOf[RecognitionException])
     final def mT__61() /*throws RecognitionException*/ {
        try {
            var _type = T__61;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:14:7: ( 'local' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:14:9: 'local'
            {
            smatch("local"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    @throws(classOf[RecognitionException])
     final def mT__62() /*throws RecognitionException*/ {
        try {
            var _type = T__62;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:15:7: ( 'required' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:15:9: 'required'
            {
            smatch("required"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "NU"
    @throws(classOf[RecognitionException])
     final def mNU() /*throws RecognitionException*/ {
        try {
            var _type = NU;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:128:5: ( 'new' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:128:9: 'new'
            {
            smatch("new"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NU"

    // $ANTLR start "TRUE"
    @throws(classOf[RecognitionException])
     final def mTRUE() /*throws RecognitionException*/ {
        try {
            var _type = TRUE;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:131:5: ( 'true' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:131:9: 'true'
            {
            smatch("true"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    @throws(classOf[RecognitionException])
     final def mFALSE() /*throws RecognitionException*/ {
        try {
            var _type = FALSE;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:134:5: ( 'false' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:134:9: 'false'
            {
            smatch("false"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "ZERO"
    @throws(classOf[RecognitionException])
     final def mZERO() /*throws RecognitionException*/ {
        try {
            var _type = ZERO;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:137:5: ( '0' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:137:9: '0'
            {
            smatch('0'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ZERO"

    // $ANTLR start "RARROW"
    @throws(classOf[RecognitionException])
     final def mRARROW() /*throws RecognitionException*/ {
        try {
            var _type = RARROW;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:151:2: ( '=>' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:151:4: '=>'
            {
            smatch("=>"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RARROW"

    // $ANTLR start "LET"
    @throws(classOf[RecognitionException])
     final def mLET() /*throws RecognitionException*/ {
        try {
            var _type = LET;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:162:2: ( 'let' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:162:4: 'let'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:165:2: ( 'in' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:165:4: 'in'
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

    // $ANTLR start "EQ_OP"
    @throws(classOf[RecognitionException])
     final def mEQ_OP() /*throws RecognitionException*/ {
        try {
            var _type = EQ_OP;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:176:2: ( '==' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:176:4: '=='
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:179:2: ( '<' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:179:4: '<'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:182:2: ( '=<' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:182:4: '=<'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:185:2: ( '+' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:185:4: '+'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:188:2: ( '-' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:188:4: '-'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:190:9: ( '(' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:190:11: '('
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:193:8: ( ')' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:193:10: ')'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:196:7: ( '{' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:196:9: '{'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:199:7: ( '}' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:199:9: '}'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:203:2: ( ']' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:203:4: ']'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:206:2: ( '[' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:206:4: '['
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:209:7: ( ',' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:209:9: ','
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:212:7: ( '.' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:212:9: '.'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:215:6: ( ';' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:215:8: ';'
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

    // $ANTLR start "COLON"
    @throws(classOf[RecognitionException])
     final def mCOLON() /*throws RecognitionException*/ {
        try {
            var _type = COLON;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:217:7: ( ':' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:217:9: ':'
            {
            smatch(':'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "SLASH"
    @throws(classOf[RecognitionException])
     final def mSLASH() /*throws RecognitionException*/ {
        try {
            var _type = SLASH;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:219:7: ( '/' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:219:10: '/'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:221:6: ( '|' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:221:9: '|'
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

    // $ANTLR start "BANG"
    @throws(classOf[RecognitionException])
     final def mBANG() /*throws RecognitionException*/ {
        try {
            var _type = BANG;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:224:2: ( '!' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:224:6: '!'
            {
            smatch('!'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BANG"

    // $ANTLR start "TILDE"
    @throws(classOf[RecognitionException])
     final def mTILDE() /*throws RecognitionException*/ {
        try {
            var _type = TILDE;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:227:5: ( '~' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:227:9: '~'
            {
            smatch('~'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TILDE"

    // $ANTLR start "UNDEF"
    @throws(classOf[RecognitionException])
     final def mUNDEF() /*throws RecognitionException*/ {
        try {
            var _type = UNDEF;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:230:5: ( '_' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:230:9: '_'
            {
            smatch('_'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNDEF"

    // $ANTLR start "IMARK"
    @throws(classOf[RecognitionException])
     final def mIMARK() /*throws RecognitionException*/ {
        try {
            var _type = IMARK;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:233:5: ( '?' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:233:9: '?'
            {
            smatch('?'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMARK"

    // $ANTLR start "R_ID"
    @throws(classOf[RecognitionException])
     final def mR_ID() /*throws RecognitionException*/ {
        try {
            var _type = R_ID;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:236:5: ( ( 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:236:9: ( 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:236:9: ( 'A' .. 'Z' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:236:10: 'A' .. 'Z'
            {
            matchRange('A','Z'); 

            }

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:236:19: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
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
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:
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
    // $ANTLR end "R_ID"

    // $ANTLR start "V_ID"
    @throws(classOf[RecognitionException])
     final def mV_ID() /*throws RecognitionException*/ {
        try {
            var _type = V_ID;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:238:7: ( ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:238:9: ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:238:9: ( 'a' .. 'z' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:238:10: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:238:19: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            //loop2:
            var guard = true
            while(guard) {
                var alt2=2;
                var LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                alt2 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:
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
            		    guard = false //loop2;
                }
            } //while (true);


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "V_ID"

    // $ANTLR start "INT"
    @throws(classOf[RecognitionException])
     final def mINT() /*throws RecognitionException*/ {
        try {
            var _type = INT;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:244:5: ( ( '0' .. '9' )+ )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:244:7: ( '0' .. '9' )+
            {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:244:7: ( '0' .. '9' )+
            var cnt3=0;
            //loop3:
            var guard = true
            while(guard) {
                var alt3=2
                var LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                alt3 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:244:7: '0' .. '9'
            		    {
            		    matchRange('0','9'); 

            		    }
            		case _ =>
            		    if ( cnt3 >= 1 ) 
            		    	guard = false
            		    else{	
            	            val eee =
            	                new EarlyExitException(3, input);
            	            throw eee
            	        }
                }
              
            	cnt3 = cnt3 + 1
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:254:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
            var alt7=2;
            var LA7_0 = input.LA(1);

            if ( (LA7_0=='/') ) {
                var LA7_1 = input.LA(2);

                if ( (LA7_1=='/') ) {
                    alt7=1;
                }
                else if ( (LA7_1=='*') ) {
                    alt7=2;
                }
                else {
                    val nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
            }
            else {
                val nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            alt7 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:254:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    smatch("//"); 

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:254:14: (~ ( '\\n' | '\\r' ) )*
                    //loop4:
                    var guard = true
                    while(guard) {
                        var alt4=2;
                        var LA4_0 = input.LA(1);

                        if ( ((LA4_0>='\u0000' && LA4_0<='\t')||(LA4_0>='\u000B' && LA4_0<='\f')||(LA4_0>='\u000E' && LA4_0<='\uFFFF')) ) {
                            alt4=1;
                        }


                        alt4 match{
                    		case 1 =>
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:254:14: ~ ( '\\n' | '\\r' )
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
                    		    guard = false //loop4;
                        }
                    } //while (true);

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:254:28: ( '\\r' )?
                    var alt5=2;
                    var LA5_0 = input.LA(1);

                    if ( (LA5_0=='\r') ) {
                        alt5=1;
                    }
                    alt5 match{
                        case 1 =>
                            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:254:28: '\\r'
                            {
                            smatch('\r'); 

                            }
                        case _ => //Do nothing
                    }

                    smatch('\n'); 
                    _channel=HIDDEN;

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:255:9: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    smatch("/*"); 

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:255:14: ( options {greedy=false; } : . )*
                    //loop6:
                    var guard = true
                    while(guard) {
                        var alt6=2;
                        var LA6_0 = input.LA(1);

                        if ( (LA6_0=='*') ) {
                            var LA6_1 = input.LA(2);

                            if ( (LA6_1=='/') ) {
                                alt6=2;
                            }
                            else if ( ((LA6_1>='\u0000' && LA6_1<='.')||(LA6_1>='0' && LA6_1<='\uFFFF')) ) {
                                alt6=1;
                            }


                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<=')')||(LA6_0>='+' && LA6_0<='\uFFFF')) ) {
                            alt6=1;
                        }


                        alt6 match{
                    		case 1 =>
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:255:42: .
                    		    {
                    		    matchAny(); 

                    		    }
                    		case _ =>
                    		    guard = false //loop6;
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:258:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:258:9: ( ' ' | '\\t' | '\\r' | '\\n' )
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:266:5: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:266:8: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            smatch('\"'); 
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:266:12: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
            //loop8:
            var guard = true
            while(guard) {
                var alt8=3;
                var LA8_0 = input.LA(1);

                if ( (LA8_0=='\\') ) {
                    alt8=1;
                }
                else if ( ((LA8_0>='\u0000' && LA8_0<='!')||(LA8_0>='#' && LA8_0<='[')||(LA8_0>=']' && LA8_0<='\uFFFF')) ) {
                    alt8=2;
                }


                alt8 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:266:14: ESC_SEQ
            		    {
            		    mESC_SEQ(); 

            		    }case 2 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:266:24: ~ ( '\\\\' | '\"' )
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
            		    guard = false //loop8;
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:269:5: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:269:8: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            smatch('\''); 
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:269:13: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
            var alt9=2;
            var LA9_0 = input.LA(1);

            if ( (LA9_0=='\\') ) {
                alt9=1;
            }
            else if ( ((LA9_0>='\u0000' && LA9_0<='&')||(LA9_0>='(' && LA9_0<='[')||(LA9_0>=']' && LA9_0<='\uFFFF')) ) {
                alt9=2;
            }
            else {
                val nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            alt9 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:269:15: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:269:25: ~ ( '\\'' | '\\\\' )
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:273:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:273:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                val mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:273:22: ( '+' | '-' )?
            var alt10=2;
            var LA10_0 = input.LA(1);

            if ( (LA10_0=='+'||LA10_0=='-') ) {
                alt10=1;
            }
            alt10 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:
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

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:273:33: ( '0' .. '9' )+
            var cnt11=0;
            //loop11:
            var guard = true
            while(guard) {
                var alt11=2
                var LA11_0 = input.LA(1);

                if ( ((LA11_0>='0' && LA11_0<='9')) ) {
                    alt11=1;
                }


                alt11 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:273:34: '0' .. '9'
            		    {
            		    matchRange('0','9'); 

            		    }
            		case _ =>
            		    if ( cnt11 >= 1 ) 
            		    	guard = false
            		    else{	
            	            val eee =
            	                new EarlyExitException(11, input);
            	            throw eee
            	        }
                }
              
            	cnt11 = cnt11 + 1
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:276:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:276:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:280:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            var alt12=3;
            var LA12_0 = input.LA(1);

            if ( (LA12_0=='\\') ) {
                input.LA(2) match{
                	case '\"' => alt12=1;
                	case '\'' => alt12=1;
                	case '\\' => alt12=1;
                	case 'b' => alt12=1;
                	case 'f' => alt12=1;
                	case 'n' => alt12=1;
                	case 'r' => alt12=1;
                	case 't' => alt12=1;    
                	case 'u' => alt12=2;    
                	case '0' => alt12=3;
                	case '1' => alt12=3;
                	case '2' => alt12=3;
                	case '3' => alt12=3;
                	case '4' => alt12=3;
                	case '5' => alt12=3;
                	case '6' => alt12=3;
                	case '7' => alt12=3;    
                	case _ =>
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:280:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:281:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:282:9: OCTAL_ESC
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:287:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            var alt13=3;
            var LA13_0 = input.LA(1);

            if ( (LA13_0=='\\') ) {
                var LA13_1 = input.LA(2);

                if ( ((LA13_1>='0' && LA13_1<='3')) ) {
                    var LA13_2 = input.LA(3);

                    if ( ((LA13_2>='0' && LA13_2<='7')) ) {
                        var LA13_4 = input.LA(4);

                        if ( ((LA13_4>='0' && LA13_4<='7')) ) {
                            alt13=1;
                        }
                        else {
                            alt13=2;}
                    }
                    else {
                        alt13=3;}
                }
                else if ( ((LA13_1>='4' && LA13_1<='7')) ) {
                    var LA13_3 = input.LA(3);

                    if ( ((LA13_3>='0' && LA13_3<='7')) ) {
                        alt13=2;
                    }
                    else {
                        alt13=3;}
                }
                else {
                    val nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
            }
            else {
                val nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            alt13 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:287:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:287:14: ( '0' .. '3' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:287:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:287:25: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:287:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:287:36: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:287:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:288:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:288:14: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:288:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:288:25: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:288:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:289:9: '\\\\' ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:289:14: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:289:15: '0' .. '7'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:294:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:294:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
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
        // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:8: ( T__60 | T__61 | T__62 | NU | TRUE | FALSE | ZERO | RARROW | LET | IN | EQ_OP | LT | LTEQ | PLUS | MINUS | LPAREN | RPAREN | LCURL | RCURL | RBRACK | LBRACK | COMMA | POINT | SEMI | COLON | SLASH | BAR | BANG | TILDE | UNDEF | IMARK | R_ID | V_ID | INT | COMMENT | WS | STRING | CHAR )
        var alt14=38;
        alt14 = dfa14.predict(input);
        alt14 match{
            case 1 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:10: T__60
                {
                mT__60(); 

                }case 2 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:16: T__61
                {
                mT__61(); 

                }case 3 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:22: T__62
                {
                mT__62(); 

                }case 4 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:28: NU
                {
                mNU(); 

                }case 5 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:31: TRUE
                {
                mTRUE(); 

                }case 6 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:36: FALSE
                {
                mFALSE(); 

                }case 7 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:42: ZERO
                {
                mZERO(); 

                }case 8 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:47: RARROW
                {
                mRARROW(); 

                }case 9 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:54: LET
                {
                mLET(); 

                }case 10 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:58: IN
                {
                mIN(); 

                }case 11 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:61: EQ_OP
                {
                mEQ_OP(); 

                }case 12 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:67: LT
                {
                mLT(); 

                }case 13 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:70: LTEQ
                {
                mLTEQ(); 

                }case 14 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:75: PLUS
                {
                mPLUS(); 

                }case 15 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:80: MINUS
                {
                mMINUS(); 

                }case 16 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:86: LPAREN
                {
                mLPAREN(); 

                }case 17 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:93: RPAREN
                {
                mRPAREN(); 

                }case 18 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:100: LCURL
                {
                mLCURL(); 

                }case 19 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:106: RCURL
                {
                mRCURL(); 

                }case 20 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:112: RBRACK
                {
                mRBRACK(); 

                }case 21 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:119: LBRACK
                {
                mLBRACK(); 

                }case 22 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:126: COMMA
                {
                mCOMMA(); 

                }case 23 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:132: POINT
                {
                mPOINT(); 

                }case 24 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:138: SEMI
                {
                mSEMI(); 

                }case 25 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:143: COLON
                {
                mCOLON(); 

                }case 26 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:149: SLASH
                {
                mSLASH(); 

                }case 27 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:155: BAR
                {
                mBAR(); 

                }case 28 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:159: BANG
                {
                mBANG(); 

                }case 29 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:164: TILDE
                {
                mTILDE(); 

                }case 30 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:170: UNDEF
                {
                mUNDEF(); 

                }case 31 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:176: IMARK
                {
                mIMARK(); 

                }case 32 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:182: R_ID
                {
                mR_ID(); 

                }case 33 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:187: V_ID
                {
                mV_ID(); 

                }case 34 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:192: INT
                {
                mINT(); 

                }case 35 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:196: COMMENT
                {
                mCOMMENT(); 

                }case 36 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:204: WS
                {
                mWS(); 

                }case 37 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:207: STRING
                {
                mSTRING(); 

                }case 38 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:1:214: CHAR
                {
                mCHAR(); 

                }
            case _ => //Do nothing
        }

    }


    protected val /*DFA14*/ dfa14 = new DFA14(this)
    /*
    final val DFA14_eotS =
        "\1\uffff\6\36\1\52\1\uffff\1\36\15\uffff\1\60\13\uffff\7\36\4\uffff"+
        "\1\70\2\uffff\2\36\1\73\1\36\1\75\2\36\1\uffff\2\36\1\uffff\1\36"+
        "\1\uffff\1\103\2\36\1\106\1\36\1\uffff\1\110\1\36\1\uffff\1\36\1"+
        "\uffff\2\36\1\115\1\116\2\uffff";
    final val DFA14_eofS =
        "\117\uffff";
    final val DFA14_minS =
        "\1\11\1\162\3\145\1\162\1\141\1\60\1\74\1\156\15\uffff\1\52\13\uffff"+
        "\1\157\1\143\1\164\1\161\1\167\1\165\1\154\4\uffff\1\60\2\uffff"+
        "\1\166\1\141\1\60\1\165\1\60\1\145\1\163\1\uffff\1\151\1\154\1\uffff"+
        "\1\151\1\uffff\1\60\1\145\1\144\1\60\1\162\1\uffff\1\60\1\145\1"+
        "\uffff\1\145\1\uffff\2\144\2\60\2\uffff";
    final val DFA14_maxS =
        "\1\176\1\162\1\157\2\145\1\162\1\141\1\71\1\76\1\156\15\uffff\1"+
        "\57\13\uffff\1\157\1\143\1\164\1\161\1\167\1\165\1\154\4\uffff\1"+
        "\172\2\uffff\1\166\1\141\1\172\1\165\1\172\1\145\1\163\1\uffff\1"+
        "\151\1\154\1\uffff\1\151\1\uffff\1\172\1\145\1\144\1\172\1\162\1"+
        "\uffff\1\172\1\145\1\uffff\1\145\1\uffff\2\144\2\172\2\uffff";
    final val DFA14_acceptS =
        "\12\uffff\1\14\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
        "\1\30\1\31\1\uffff\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\44"+
        "\1\45\1\46\7\uffff\1\7\1\10\1\13\1\15\1\uffff\1\43\1\32\7\uffff"+
        "\1\12\2\uffff\1\11\1\uffff\1\4\5\uffff\1\5\2\uffff\1\2\1\uffff\1"+
        "\6\4\uffff\1\1\1\3";
    val DFA14_specialS =
        "\117\uffff}>";
    final val DFA14_transitionS = Array[String](
        "\2\40\2\uffff\1\40\22\uffff\1\40\1\31\1\41\4\uffff\1\42\1\15\1\16"+
        "\1\uffff\1\13\1\23\1\14\1\24\1\27\1\7\11\37\1\26\1\25\1\12\1\10"+
        "\1\uffff\1\34\1\uffff\32\35\1\22\1\uffff\1\21\1\uffff\1\33\1\uffff"+
        "\5\36\1\6\2\36\1\11\2\36\1\2\1\36\1\4\1\36\1\1\1\36\1\3\1\36\1\5"+
        "\6\36\1\17\1\30\1\20\1\32",
        "\1\43",
        "\1\45\11\uffff\1\44",
        "\1\46",
        "\1\47",
        "\1\50",
        "\1\51",
        "\12\37",
        "\1\55\1\54\1\53",
        "\1\56",
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
        "",
        "",
        "\1\57\4\uffff\1\57",
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
        "\1\61",
        "\1\62",
        "\1\63",
        "\1\64",
        "\1\65",
        "\1\66",
        "\1\67",
        "",
        "",
        "",
        "",
        "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
        "",
        "",
        "\1\71",
        "\1\72",
        "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
        "\1\74",
        "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
        "\1\76",
        "\1\77",
        "",
        "\1\100",
        "\1\101",
        "",
        "\1\102",
        "",
        "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
        "\1\104",
        "\1\105",
        "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
        "\1\107",
        "",
        "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
        "\1\111",
        "",
        "\1\112",
        "",
        "\1\113",
        "\1\114",
        "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
        "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
        "",
        ""
    )

    final val DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    final val DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    final val DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    final val DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    final val DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    final val DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    final val DFA14_transition = new Array[Array[Short]](DFA14_transitionS.length)

    {
        for (i <- 0 to DFA14_transition.length - 1) {
            DFA14_transition(i) = DFA.unpackEncodedString(DFA14_transitionS(i))
        }
    }
    */
    class DFA14(recognizer:BaseRecognizer ) extends DFA {
    		final val DFA14_eotS =
    		    "\1\uffff\6\36\1\52\1\uffff\1\36\15\uffff\1\60\13\uffff\7\36\4"+
        "\uffff\1\70\2\uffff\2\36\1\73\1\36\1\75\2\36\1\uffff\2\36\1\uffff"+
        "\1\36\1\uffff\1\103\2\36\1\106\1\36\1\uffff\1\110\1\36\1\uffff\1"+
        "\36\1\uffff\2\36\1\115\1\116\2\uffff";
    		final val DFA14_eofS =
    		    "\117\uffff";
    		final val DFA14_minS =
    		    "\1\11\1\162\3\145\1\162\1\141\1\60\1\74\1\156\15\uffff\1\52\13"+
        "\uffff\1\157\1\143\1\164\1\161\1\167\1\165\1\154\4\uffff\1\60\2"+
        "\uffff\1\166\1\141\1\60\1\165\1\60\1\145\1\163\1\uffff\1\151\1\154"+
        "\1\uffff\1\151\1\uffff\1\60\1\145\1\144\1\60\1\162\1\uffff\1\60"+
        "\1\145\1\uffff\1\145\1\uffff\2\144\2\60\2\uffff";
    		final val DFA14_maxS =
    		    "\1\176\1\162\1\157\2\145\1\162\1\141\1\71\1\76\1\156\15\uffff"+
        "\1\57\13\uffff\1\157\1\143\1\164\1\161\1\167\1\165\1\154\4\uffff"+
        "\1\172\2\uffff\1\166\1\141\1\172\1\165\1\172\1\145\1\163\1\uffff"+
        "\1\151\1\154\1\uffff\1\151\1\uffff\1\172\1\145\1\144\1\172\1\162"+
        "\1\uffff\1\172\1\145\1\uffff\1\145\1\uffff\2\144\2\172\2\uffff";
    		final val DFA14_acceptS =
    		    "\12\uffff\1\14\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1"+
        "\27\1\30\1\31\1\uffff\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1"+
        "\44\1\45\1\46\7\uffff\1\7\1\10\1\13\1\15\1\uffff\1\43\1\32\7\uffff"+
        "\1\12\2\uffff\1\11\1\uffff\1\4\5\uffff\1\5\2\uffff\1\2\1\uffff\1"+
        "\6\4\uffff\1\1\1\3";
    		val DFA14_specialS =
    		    "\117\uffff}>";
    		final val DFA14_transitionS = Array[String](
    		    "\2\40\2\uffff\1\40\22\uffff\1\40\1\31\1\41\4\uffff\1\42\1\15\1"+
    		    "\16\1\uffff\1\13\1\23\1\14\1\24\1\27\1\7\11\37\1\26\1\25\1\12"+
    		    "\1\10\1\uffff\1\34\1\uffff\32\35\1\22\1\uffff\1\21\1\uffff\1\33"+
    		    "\1\uffff\5\36\1\6\2\36\1\11\2\36\1\2\1\36\1\4\1\36\1\1\1\36\1"+
    		    "\3\1\36\1\5\6\36\1\17\1\30\1\20\1\32",
    		    "\1\43",
    		    "\1\45\11\uffff\1\44",
    		    "\1\46",
    		    "\1\47",
    		    "\1\50",
    		    "\1\51",
    		    "\12\37",
    		    "\1\55\1\54\1\53",
    		    "\1\56",
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
    		    "",
    		    "",
    		    "\1\57\4\uffff\1\57",
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
    		    "\1\61",
    		    "\1\62",
    		    "\1\63",
    		    "\1\64",
    		    "\1\65",
    		    "\1\66",
    		    "\1\67",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
    		    "",
    		    "",
    		    "\1\71",
    		    "\1\72",
    		    "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
    		    "\1\74",
    		    "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
    		    "\1\76",
    		    "\1\77",
    		    "",
    		    "\1\100",
    		    "\1\101",
    		    "",
    		    "\1\102",
    		    "",
    		    "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
    		    "\1\104",
    		    "\1\105",
    		    "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
    		    "\1\107",
    		    "",
    		    "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
    		    "\1\111",
    		    "",
    		    "\1\112",
    		    "",
    		    "\1\113",
    		    "\1\114",
    		    "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
    		    "\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
    		    "",
    		    ""
    		)

            this.decisionNumber = 14;
            this.eot = DFA.unpackEncodedString(DFA14_eotS) //DFA14_eot;
            this.eof = DFA.unpackEncodedString(DFA14_eofS) //DFA14_eof;
            this.min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS) //DFA14_min;
            this.max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS) //DFA14_max;
            this.accept = DFA.unpackEncodedString(DFA14_acceptS) //DFA14_accept;
            this.special = DFA.unpackEncodedString(DFA14_specialS); //DFA14_special;
            this.transition = new Array[Array[Short]](DFA14_transitionS.length) //DFA14_transition;

    		{
    		    for (i <- 0 to DFA14_transitionS.length - 1) {
    		        this.transition(i) = DFA.unpackEncodedString(DFA14_transitionS(i))
    		    }
    		}

        override def getDescription(): String = "1:1: Tokens : ( T__60 | T__61 | T__62 | NU | TRUE | FALSE | ZERO | RARROW | LET | IN | EQ_OP | LT | LTEQ | PLUS | MINUS | LPAREN | RPAREN | LCURL | RCURL | RBRACK | LBRACK | COMMA | POINT | SEMI | COLON | SLASH | BAR | BANG | TILDE | UNDEF | IMARK | R_ID | V_ID | INT | COMMENT | WS | STRING | CHAR );"
        
    }
 

}