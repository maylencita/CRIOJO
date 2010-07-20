// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g 2010-07-11 13:36:57

package fr.emn.creole.parser;

import fr.emn.creole.parser.tree._


import org.antlr.runtime._;
import BaseRecognizer.HIDDEN
import java.util.Stack;
//import java.util.List;
import java.util.ArrayList;

class CREOLE_XLexer(input:CharStream, state:SRecognizerSharedState) 
extends SLexer(input, state) {
	
	def this(input: CharStream){
		this(input, new SRecognizerSharedState())
	}



//MEMOIZE?
		//delegates
		// direct delegates


    	val EXPONENT:Int = 53
    	val LT:Int = 41
    	val LBRACK:Int = 30
    	val EQ_OP:Int = 40
    	val POINT:Int = 47
    	val HEAD:Int = 8
    	val LTEQ:Int = 42
    	val OCTAL_ESC:Int = 56
    	val CHAR:Int = 52
    	val MULTI:Int = 10
    	val ATOM:Int = 5
    	val EOF:Int = -1
    	val DECLARATION:Int = 11
    	val LPAREN:Int = 19
    	val INT_ATOM:Int = 18
    	val RPAREN:Int = 22
    	val T__57:Int = 57
    	val T__58:Int = 58
    	val ESC_SEQ:Int = 51
    	val SLASH:Int = 48
    	val COMMA:Int = 23
    	val T__59:Int = 59
    	val TILDE:Int = 26
    	val PLUS:Int = 43
    	val BODY:Int = 9
    	val UNDEF:Int = 24
    	val COMMENT:Int = 49
    	val IMARK:Int = 32
    	val RBRACK:Int = 31
    	val SCRIPT:Int = 4
    	val RULE:Int = 7
    	val NU:Int = 34
    	val R_ID:Int = 25
    	val PRIVATE:Int = 14
    	val VARS:Int = 6
    	val UNICODE_ESC:Int = 55
    	val RARROW:Int = 29
    	val HEX_DIGIT:Int = 54
    	val V_ID:Int = 37
    	val BANG:Int = 28
    	val INT:Int = 38
    	val MINUS:Int = 44
    	val TRUE:Int = 35
    	val SEMI:Int = 21
    	val EMPTY:Int = 17
    	val ABS:Int = 33
    	val COLON:Int = 20
    	val LCURL:Int = 45
    	val WS:Int = 50
    	val RCURL:Int = 46
    	val EMPTYLIST:Int = 12
    	val GUARD:Int = 16
    	val PROCESS:Int = 15
    	val FALSE:Int = 36
    	val PUBLIC:Int = 13
    	val BAR:Int = 27
    	val STRING:Int = 39

    override def getGrammarFileName = "src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g"

    // $ANTLR start "T__57"
    @throws(classOf[RecognitionException])
     final def mT__57() /*throws RecognitionException*/ {
        try {
            var _type = T__57;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:13:7: ( 'public' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:13:9: 'public'
            {
            smatch("public"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    @throws(classOf[RecognitionException])
     final def mT__58() /*throws RecognitionException*/ {
        try {
            var _type = T__58;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:14:7: ( 'private' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:14:9: 'private'
            {
            smatch("private"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    @throws(classOf[RecognitionException])
     final def mT__59() /*throws RecognitionException*/ {
        try {
            var _type = T__59;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:15:7: ( 'T' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:15:9: 'T'
            {
            smatch('T'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "NU"
    @throws(classOf[RecognitionException])
     final def mNU() /*throws RecognitionException*/ {
        try {
            var _type = NU;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:150:5: ( 'new' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:150:9: 'new'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:153:5: ( 'true' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:153:9: 'true'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:156:5: ( 'false' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:156:9: 'false'
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

    // $ANTLR start "ABS"
    @throws(classOf[RecognitionException])
     final def mABS() /*throws RecognitionException*/ {
        try {
            var _type = ABS;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:164:5: ( 'Abs' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:164:9: 'Abs'
            {
            smatch("Abs"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ABS"

    // $ANTLR start "RARROW"
    @throws(classOf[RecognitionException])
     final def mRARROW() /*throws RecognitionException*/ {
        try {
            var _type = RARROW;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:178:2: ( '=>' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:178:4: '=>'
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

    // $ANTLR start "EQ_OP"
    @throws(classOf[RecognitionException])
     final def mEQ_OP() /*throws RecognitionException*/ {
        try {
            var _type = EQ_OP;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:201:2: ( '==' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:201:4: '=='
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:204:2: ( '<' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:204:4: '<'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:207:2: ( '=<' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:207:4: '=<'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:210:2: ( '+' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:210:4: '+'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:213:2: ( '-' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:213:4: '-'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:215:9: ( '(' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:215:11: '('
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:218:8: ( ')' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:218:10: ')'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:221:7: ( '{' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:221:9: '{'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:224:7: ( '}' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:224:9: '}'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:228:2: ( ']' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:228:4: ']'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:231:2: ( '[' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:231:4: '['
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:234:7: ( ',' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:234:9: ','
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:237:7: ( '.' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:237:9: '.'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:240:6: ( ';' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:240:8: ';'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:242:7: ( ':' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:242:9: ':'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:244:7: ( '/' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:244:10: '/'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:246:6: ( '|' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:246:9: '|'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:249:2: ( '!' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:249:6: '!'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:252:5: ( '~' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:252:9: '~'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:255:5: ( '_' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:255:9: '_'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:258:5: ( '?' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:258:9: '?'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:261:5: ( ( 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:261:9: ( 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:261:9: ( 'A' .. 'Z' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:261:10: 'A' .. 'Z'
            {
            matchRange('A','Z'); 

            }

            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:261:19: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
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
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:263:7: ( ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:263:9: ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:263:9: ( 'a' .. 'z' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:263:10: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:263:19: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
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
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:269:5: ( ( '0' .. '9' )+ )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:269:7: ( '0' .. '9' )+
            {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:269:7: ( '0' .. '9' )+
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
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:269:7: '0' .. '9'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:279:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:279:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    smatch("//"); 

                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:279:14: (~ ( '\\n' | '\\r' ) )*
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
                    		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:279:14: ~ ( '\\n' | '\\r' )
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

                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:279:28: ( '\\r' )?
                    var alt5=2;
                    var LA5_0 = input.LA(1);

                    if ( (LA5_0=='\r') ) {
                        alt5=1;
                    }
                    alt5 match{
                        case 1 =>
                            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:279:28: '\\r'
                            {
                            smatch('\r'); 

                            }
                        case _ => //Do nothing
                    }

                    smatch('\n'); 
                    _channel=HIDDEN;

                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:280:9: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    smatch("/*"); 

                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:280:14: ( options {greedy=false; } : . )*
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
                    		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:280:42: .
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:283:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:283:9: ( ' ' | '\\t' | '\\r' | '\\n' )
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:291:5: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:291:8: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            smatch('\"'); 
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:291:12: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
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
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:291:14: ESC_SEQ
            		    {
            		    mESC_SEQ(); 

            		    }case 2 =>
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:291:24: ~ ( '\\\\' | '\"' )
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:294:5: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:294:8: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            smatch('\''); 
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:294:13: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:294:15: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:294:25: ~ ( '\\'' | '\\\\' )
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:298:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:298:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                val mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:298:22: ( '+' | '-' )?
            var alt10=2;
            var LA10_0 = input.LA(1);

            if ( (LA10_0=='+'||LA10_0=='-') ) {
                alt10=1;
            }
            alt10 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:
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

            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:298:33: ( '0' .. '9' )+
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
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:298:34: '0' .. '9'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:301:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:301:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:305:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:305:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:306:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }case 3 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:307:9: OCTAL_ESC
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:312:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:312:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:312:14: ( '0' .. '3' )
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:312:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:312:25: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:312:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:312:36: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:312:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:313:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:313:14: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:313:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:313:25: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:313:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }case 3 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:314:9: '\\\\' ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:314:14: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:314:15: '0' .. '7'
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:319:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:319:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
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
        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:8: ( T__57 | T__58 | T__59 | NU | TRUE | FALSE | ABS | RARROW | EQ_OP | LT | LTEQ | PLUS | MINUS | LPAREN | RPAREN | LCURL | RCURL | RBRACK | LBRACK | COMMA | POINT | SEMI | COLON | SLASH | BAR | BANG | TILDE | UNDEF | IMARK | R_ID | V_ID | INT | COMMENT | WS | STRING | CHAR )
        var alt14=36;
        alt14 = dfa14.predict(input);
        alt14 match{
            case 1 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:10: T__57
                {
                mT__57(); 

                }case 2 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:16: T__58
                {
                mT__58(); 

                }case 3 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:22: T__59
                {
                mT__59(); 

                }case 4 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:28: NU
                {
                mNU(); 

                }case 5 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:31: TRUE
                {
                mTRUE(); 

                }case 6 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:36: FALSE
                {
                mFALSE(); 

                }case 7 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:42: ABS
                {
                mABS(); 

                }case 8 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:46: RARROW
                {
                mRARROW(); 

                }case 9 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:53: EQ_OP
                {
                mEQ_OP(); 

                }case 10 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:59: LT
                {
                mLT(); 

                }case 11 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:62: LTEQ
                {
                mLTEQ(); 

                }case 12 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:67: PLUS
                {
                mPLUS(); 

                }case 13 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:72: MINUS
                {
                mMINUS(); 

                }case 14 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:78: LPAREN
                {
                mLPAREN(); 

                }case 15 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:85: RPAREN
                {
                mRPAREN(); 

                }case 16 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:92: LCURL
                {
                mLCURL(); 

                }case 17 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:98: RCURL
                {
                mRCURL(); 

                }case 18 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:104: RBRACK
                {
                mRBRACK(); 

                }case 19 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:111: LBRACK
                {
                mLBRACK(); 

                }case 20 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:118: COMMA
                {
                mCOMMA(); 

                }case 21 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:124: POINT
                {
                mPOINT(); 

                }case 22 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:130: SEMI
                {
                mSEMI(); 

                }case 23 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:135: COLON
                {
                mCOLON(); 

                }case 24 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:141: SLASH
                {
                mSLASH(); 

                }case 25 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:147: BAR
                {
                mBAR(); 

                }case 26 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:151: BANG
                {
                mBANG(); 

                }case 27 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:156: TILDE
                {
                mTILDE(); 

                }case 28 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:162: UNDEF
                {
                mUNDEF(); 

                }case 29 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:168: IMARK
                {
                mIMARK(); 

                }case 30 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:174: R_ID
                {
                mR_ID(); 

                }case 31 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:179: V_ID
                {
                mV_ID(); 

                }case 32 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:184: INT
                {
                mINT(); 

                }case 33 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:188: COMMENT
                {
                mCOMMENT(); 

                }case 34 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:196: WS
                {
                mWS(); 

                }case 35 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:199: STRING
                {
                mSTRING(); 

                }case 36 =>
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:1:206: CHAR
                {
                mCHAR(); 

                }
            case _ => //Do nothing
        }

    }


    protected val /*DFA14*/ dfa14 = new DFA14(this)
    /*
    final val DFA14_eotS =
        "\1\uffff\1\34\1\43\3\34\1\33\16\uffff\1\54\13\uffff\2\34\1\uffff"+
        "\3\34\1\33\5\uffff\2\34\1\65\2\34\1\70\2\34\1\uffff\1\73\1\34\1"+
        "\uffff\2\34\1\uffff\1\77\1\100\1\34\2\uffff\1\102\1\uffff";
    final val DFA14_eofS =
        "\103\uffff";
    final val DFA14_minS =
        "\1\11\1\162\1\60\1\145\1\162\1\141\1\142\1\74\15\uffff\1\52\13\uffff"+
        "\1\142\1\151\1\uffff\1\167\1\165\1\154\1\163\5\uffff\1\154\1\166"+
        "\1\60\1\145\1\163\1\60\1\151\1\141\1\uffff\1\60\1\145\1\uffff\1"+
        "\143\1\164\1\uffff\2\60\1\145\2\uffff\1\60\1\uffff";
    final val DFA14_maxS =
        "\1\176\1\165\1\172\1\145\1\162\1\141\1\142\1\76\15\uffff\1\57\13"+
        "\uffff\1\142\1\151\1\uffff\1\167\1\165\1\154\1\163\5\uffff\1\154"+
        "\1\166\1\172\1\145\1\163\1\172\1\151\1\141\1\uffff\1\172\1\145\1"+
        "\uffff\1\143\1\164\1\uffff\2\172\1\145\2\uffff\1\172\1\uffff";
    final val DFA14_acceptS =
        "\10\uffff\1\12\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
        "\1\26\1\27\1\uffff\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\42"+
        "\1\43\1\44\2\uffff\1\3\4\uffff\1\10\1\11\1\13\1\41\1\30\10\uffff"+
        "\1\4\2\uffff\1\7\2\uffff\1\5\3\uffff\1\6\1\1\1\uffff\1\2";
    val DFA14_specialS =
        "\103\uffff}>";
    final val DFA14_transitionS = Array[String](
        "\2\36\2\uffff\1\36\22\uffff\1\36\1\27\1\37\4\uffff\1\40\1\13\1\14"+
        "\1\uffff\1\11\1\21\1\12\1\22\1\25\12\35\1\24\1\23\1\10\1\7\1\uffff"+
        "\1\32\1\uffff\1\6\22\33\1\2\6\33\1\20\1\uffff\1\17\1\uffff\1\31"+
        "\1\uffff\5\34\1\5\7\34\1\3\1\34\1\1\3\34\1\4\6\34\1\15\1\26\1\16"+
        "\1\30",
        "\1\42\2\uffff\1\41",
        "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
        "\1\44",
        "\1\45",
        "\1\46",
        "\1\47",
        "\1\52\1\51\1\50",
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
        "\1\53\4\uffff\1\53",
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
        "\1\55",
        "\1\56",
        "",
        "\1\57",
        "\1\60",
        "\1\61",
        "\1\62",
        "",
        "",
        "",
        "",
        "",
        "\1\63",
        "\1\64",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\1\66",
        "\1\67",
        "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
        "\1\71",
        "\1\72",
        "",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\1\74",
        "",
        "\1\75",
        "\1\76",
        "",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\1\101",
        "",
        "",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
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
    		    "\1\uffff\1\34\1\43\3\34\1\33\16\uffff\1\54\13\uffff\2\34\1\uffff"+
        "\3\34\1\33\5\uffff\2\34\1\65\2\34\1\70\2\34\1\uffff\1\73\1\34\1"+
        "\uffff\2\34\1\uffff\1\77\1\100\1\34\2\uffff\1\102\1\uffff";
    		final val DFA14_eofS =
    		    "\103\uffff";
    		final val DFA14_minS =
    		    "\1\11\1\162\1\60\1\145\1\162\1\141\1\142\1\74\15\uffff\1\52\13"+
        "\uffff\1\142\1\151\1\uffff\1\167\1\165\1\154\1\163\5\uffff\1\154"+
        "\1\166\1\60\1\145\1\163\1\60\1\151\1\141\1\uffff\1\60\1\145\1\uffff"+
        "\1\143\1\164\1\uffff\2\60\1\145\2\uffff\1\60\1\uffff";
    		final val DFA14_maxS =
    		    "\1\176\1\165\1\172\1\145\1\162\1\141\1\142\1\76\15\uffff\1\57"+
        "\13\uffff\1\142\1\151\1\uffff\1\167\1\165\1\154\1\163\5\uffff\1"+
        "\154\1\166\1\172\1\145\1\163\1\172\1\151\1\141\1\uffff\1\172\1\145"+
        "\1\uffff\1\143\1\164\1\uffff\2\172\1\145\2\uffff\1\172\1\uffff";
    		final val DFA14_acceptS =
    		    "\10\uffff\1\12\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1"+
        "\25\1\26\1\27\1\uffff\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1"+
        "\42\1\43\1\44\2\uffff\1\3\4\uffff\1\10\1\11\1\13\1\41\1\30\10\uffff"+
        "\1\4\2\uffff\1\7\2\uffff\1\5\3\uffff\1\6\1\1\1\uffff\1\2";
    		val DFA14_specialS =
    		    "\103\uffff}>";
    		final val DFA14_transitionS = Array[String](
    		    "\2\36\2\uffff\1\36\22\uffff\1\36\1\27\1\37\4\uffff\1\40\1\13\1"+
    		    "\14\1\uffff\1\11\1\21\1\12\1\22\1\25\12\35\1\24\1\23\1\10\1\7"+
    		    "\1\uffff\1\32\1\uffff\1\6\22\33\1\2\6\33\1\20\1\uffff\1\17\1\uffff"+
    		    "\1\31\1\uffff\5\34\1\5\7\34\1\3\1\34\1\1\3\34\1\4\6\34\1\15\1"+
    		    "\26\1\16\1\30",
    		    "\1\42\2\uffff\1\41",
    		    "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
    		    "\1\44",
    		    "\1\45",
    		    "\1\46",
    		    "\1\47",
    		    "\1\52\1\51\1\50",
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
    		    "\1\53\4\uffff\1\53",
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
    		    "\1\55",
    		    "\1\56",
    		    "",
    		    "\1\57",
    		    "\1\60",
    		    "\1\61",
    		    "\1\62",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "\1\63",
    		    "\1\64",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\1\66",
    		    "\1\67",
    		    "\12\33\7\uffff\32\33\4\uffff\1\33\1\uffff\32\33",
    		    "\1\71",
    		    "\1\72",
    		    "",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\1\74",
    		    "",
    		    "\1\75",
    		    "\1\76",
    		    "",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\1\101",
    		    "",
    		    "",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
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

        override def getDescription(): String = "1:1: Tokens : ( T__57 | T__58 | T__59 | NU | TRUE | FALSE | ABS | RARROW | EQ_OP | LT | LTEQ | PLUS | MINUS | LPAREN | RPAREN | LCURL | RCURL | RBRACK | LBRACK | COMMA | POINT | SEMI | COLON | SLASH | BAR | BANG | TILDE | UNDEF | IMARK | R_ID | V_ID | INT | COMMENT | WS | STRING | CHAR );"
        
    }
 

}