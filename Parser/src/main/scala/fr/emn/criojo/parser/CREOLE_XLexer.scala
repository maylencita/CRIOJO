// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g 2010-11-04 15:22:44

package fr.emn.criojo.parser;

import fr.emn.criojo.parser.tree._


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


    	val T__66:Int = 66
    	val NULL_ATOM:Int = 21
    	val T__67:Int = 67
    	val EXPONENT:Int = 59
    	val LT:Int = 47
    	val LBRACK:Int = 34
    	val T__64:Int = 64
    	val T__65:Int = 65
    	val T__63:Int = 63
    	val POINT:Int = 53
    	val HEAD:Int = 8
    	val LTEQ:Int = 48
    	val OCTAL_ESC:Int = 62
    	val CHAR:Int = 58
    	val MULTI:Int = 10
    	val NOT:Int = 39
    	val ATOM:Int = 5
    	val EOF:Int = -1
    	val DECLARATION:Int = 11
    	val LPAREN:Int = 22
    	val ARROBAS:Int = 29
    	val INT_ATOM:Int = 19
    	val RPAREN:Int = 24
    	val ESC_SEQ:Int = 57
    	val SLASH:Int = 54
    	val COMMA:Int = 26
    	val TILDE:Int = 30
    	val PLUS:Int = 49
    	val BODY:Int = 9
    	val UNDEF:Int = 27
    	val EQ:Int = 38
    	val COMMENT:Int = 55
    	val IMARK:Int = 36
    	val RBRACK:Int = 35
    	val SCRIPT:Int = 4
    	val RULE:Int = 7
    	val NU:Int = 40
    	val R_ID:Int = 28
    	val PRIVATE:Int = 14
    	val VARS:Int = 6
    	val RARROW:Int = 33
    	val NULL:Int = 43
    	val UNICODE_ESC:Int = 61
    	val STR_ATOM:Int = 20
    	val V_ID:Int = 44
    	val HEX_DIGIT:Int = 60
    	val BANG:Int = 32
    	val INT:Int = 45
    	val REQUIRED:Int = 15
    	val MINUS:Int = 50
    	val SEMI:Int = 23
    	val TRUE:Int = 41
    	val EMPTY:Int = 18
    	val ABS:Int = 37
    	val COLON:Int = 25
    	val LCURL:Int = 51
    	val WS:Int = 56
    	val RCURL:Int = 52
    	val EMPTYLIST:Int = 12
    	val GUARD:Int = 17
    	val PROCESS:Int = 16
    	val FALSE:Int = 42
    	val PUBLIC:Int = 13
    	val BAR:Int = 31
    	val STRING:Int = 46

    override def getGrammarFileName = "src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g"

    // $ANTLR start "T__63"
    @throws(classOf[RecognitionException])
     final def mT__63() /*throws RecognitionException*/ {
        try {
            var _type = T__63;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:13:7: ( 'provided' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:13:9: 'provided'
            {
            smatch("provided"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    @throws(classOf[RecognitionException])
     final def mT__64() /*throws RecognitionException*/ {
        try {
            var _type = T__64;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:14:7: ( 'required' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:14:9: 'required'
            {
            smatch("required"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    @throws(classOf[RecognitionException])
     final def mT__65() /*throws RecognitionException*/ {
        try {
            var _type = T__65;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:15:7: ( 'local' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:15:9: 'local'
            {
            smatch("local"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    @throws(classOf[RecognitionException])
     final def mT__66() /*throws RecognitionException*/ {
        try {
            var _type = T__66;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:16:7: ( 'Null' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:16:9: 'Null'
            {
            smatch("Null"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    @throws(classOf[RecognitionException])
     final def mT__67() /*throws RecognitionException*/ {
        try {
            var _type = T__67;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:17:7: ( 'T' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:17:9: 'T'
            {
            smatch('T'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "NU"
    @throws(classOf[RecognitionException])
     final def mNU() /*throws RecognitionException*/ {
        try {
            var _type = NU;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:163:5: ( 'new' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:163:9: 'new'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:166:5: ( 'true' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:166:9: 'true'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:169:5: ( 'false' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:169:9: 'false'
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

    // $ANTLR start "NULL"
    @throws(classOf[RecognitionException])
     final def mNULL() /*throws RecognitionException*/ {
        try {
            var _type = NULL;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:172:5: ( 'null' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:172:9: 'null'
            {
            smatch("null"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "NOT"
    @throws(classOf[RecognitionException])
     final def mNOT() /*throws RecognitionException*/ {
        try {
            var _type = NOT;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:175:5: ( 'Not' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:175:9: 'Not'
            {
            smatch("Not"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "ARROBAS"
    @throws(classOf[RecognitionException])
     final def mARROBAS() /*throws RecognitionException*/ {
        try {
            var _type = ARROBAS;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:178:2: ( '@' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:178:4: '@'
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

    // $ANTLR start "ABS"
    @throws(classOf[RecognitionException])
     final def mABS() /*throws RecognitionException*/ {
        try {
            var _type = ABS;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:181:5: ( 'Abs' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:181:9: 'Abs'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:184:2: ( '=>' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:184:4: '=>'
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

    // $ANTLR start "EQ"
    @throws(classOf[RecognitionException])
     final def mEQ() /*throws RecognitionException*/ {
        try {
            var _type = EQ;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:187:2: ( '=' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:187:4: '='
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

    // $ANTLR start "LT"
    @throws(classOf[RecognitionException])
     final def mLT() /*throws RecognitionException*/ {
        try {
            var _type = LT;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:195:2: ( '<' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:195:4: '<'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:198:2: ( '=<' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:198:4: '=<'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:201:2: ( '+' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:201:4: '+'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:204:2: ( '-' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:204:4: '-'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:206:9: ( '(' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:206:11: '('
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:209:8: ( ')' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:209:10: ')'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:212:7: ( '{' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:212:9: '{'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:215:7: ( '}' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:215:9: '}'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:219:2: ( ']' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:219:4: ']'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:222:2: ( '[' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:222:4: '['
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:225:7: ( ',' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:225:9: ','
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:228:7: ( '.' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:228:9: '.'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:231:6: ( ';' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:231:8: ';'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:233:7: ( ':' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:233:9: ':'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:235:7: ( '/' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:235:10: '/'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:237:6: ( '|' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:237:9: '|'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:240:2: ( '!' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:240:6: '!'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:243:5: ( '~' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:243:9: '~'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:246:5: ( '_' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:246:9: '_'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:249:5: ( '?' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:249:9: '?'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:252:5: ( ( 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:252:9: ( 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:252:9: ( 'A' .. 'Z' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:252:10: 'A' .. 'Z'
            {
            matchRange('A','Z'); 

            }

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:252:19: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
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
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:254:7: ( ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:254:9: ( 'a' .. 'z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:254:9: ( 'a' .. 'z' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:254:10: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:254:19: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
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
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:260:5: ( ( '0' .. '9' )+ )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:260:7: ( '0' .. '9' )+
            {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:260:7: ( '0' .. '9' )+
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
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:260:7: '0' .. '9'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:270:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:270:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    smatch("//"); 

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:270:14: (~ ( '\\n' | '\\r' ) )*
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
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:270:14: ~ ( '\\n' | '\\r' )
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

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:270:28: ( '\\r' )?
                    var alt5=2;
                    var LA5_0 = input.LA(1);

                    if ( (LA5_0=='\r') ) {
                        alt5=1;
                    }
                    alt5 match{
                        case 1 =>
                            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:270:28: '\\r'
                            {
                            smatch('\r'); 

                            }
                        case _ => //Do nothing
                    }

                    smatch('\n'); 
                    _channel=HIDDEN;

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:271:9: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    smatch("/*"); 

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:271:14: ( options {greedy=false; } : . )*
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
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:271:42: .
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:274:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:274:9: ( ' ' | '\\t' | '\\r' | '\\n' )
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:282:5: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:282:8: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            smatch('\"'); 
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:282:12: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
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
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:282:14: ESC_SEQ
            		    {
            		    mESC_SEQ(); 

            		    }case 2 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:282:24: ~ ( '\\\\' | '\"' )
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:285:5: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:285:8: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            smatch('\''); 
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:285:13: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:285:15: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:285:25: ~ ( '\\'' | '\\\\' )
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:289:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:289:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                val mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:289:22: ( '+' | '-' )?
            var alt10=2;
            var LA10_0 = input.LA(1);

            if ( (LA10_0=='+'||LA10_0=='-') ) {
                alt10=1;
            }
            alt10 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:
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

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:289:33: ( '0' .. '9' )+
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
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:289:34: '0' .. '9'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:292:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:292:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:296:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:296:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:297:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:298:9: OCTAL_ESC
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:303:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            var alt13=3;
            var LA13_0 = input.LA(1);

            if ( (LA13_0=='\\') ) {
                var LA13_1 = input.LA(2);

                if ( ((LA13_1>='0' && LA13_1<='3')) ) {
                    var LA13_2 = input.LA(3);

                    if ( ((LA13_2>='0' && LA13_2<='7')) ) {
                        var LA13_5 = input.LA(4);

                        if ( ((LA13_5>='0' && LA13_5<='7')) ) {
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:303:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:303:14: ( '0' .. '3' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:303:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:303:25: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:303:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:303:36: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:303:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:304:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:304:14: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:304:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:304:25: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:304:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:305:9: '\\\\' ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:305:14: ( '0' .. '7' )
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:305:15: '0' .. '7'
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:310:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:310:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
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
        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:8: ( T__63 | T__64 | T__65 | T__66 | T__67 | NU | TRUE | FALSE | NULL | NOT | ARROBAS | ABS | RARROW | EQ | LT | LTEQ | PLUS | MINUS | LPAREN | RPAREN | LCURL | RCURL | RBRACK | LBRACK | COMMA | POINT | SEMI | COLON | SLASH | BAR | BANG | TILDE | UNDEF | IMARK | R_ID | V_ID | INT | COMMENT | WS | STRING | CHAR )
        var alt14=41;
        alt14 = dfa14.predict(input);
        alt14 match{
            case 1 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:10: T__63
                {
                mT__63(); 

                }case 2 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:16: T__64
                {
                mT__64(); 

                }case 3 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:22: T__65
                {
                mT__65(); 

                }case 4 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:28: T__66
                {
                mT__66(); 

                }case 5 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:34: T__67
                {
                mT__67(); 

                }case 6 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:40: NU
                {
                mNU(); 

                }case 7 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:43: TRUE
                {
                mTRUE(); 

                }case 8 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:48: FALSE
                {
                mFALSE(); 

                }case 9 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:54: NULL
                {
                mNULL(); 

                }case 10 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:59: NOT
                {
                mNOT(); 

                }case 11 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:63: ARROBAS
                {
                mARROBAS(); 

                }case 12 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:71: ABS
                {
                mABS(); 

                }case 13 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:75: RARROW
                {
                mRARROW(); 

                }case 14 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:82: EQ
                {
                mEQ(); 

                }case 15 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:85: LT
                {
                mLT(); 

                }case 16 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:88: LTEQ
                {
                mLTEQ(); 

                }case 17 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:93: PLUS
                {
                mPLUS(); 

                }case 18 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:98: MINUS
                {
                mMINUS(); 

                }case 19 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:104: LPAREN
                {
                mLPAREN(); 

                }case 20 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:111: RPAREN
                {
                mRPAREN(); 

                }case 21 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:118: LCURL
                {
                mLCURL(); 

                }case 22 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:124: RCURL
                {
                mRCURL(); 

                }case 23 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:130: RBRACK
                {
                mRBRACK(); 

                }case 24 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:137: LBRACK
                {
                mLBRACK(); 

                }case 25 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:144: COMMA
                {
                mCOMMA(); 

                }case 26 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:150: POINT
                {
                mPOINT(); 

                }case 27 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:156: SEMI
                {
                mSEMI(); 

                }case 28 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:161: COLON
                {
                mCOLON(); 

                }case 29 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:167: SLASH
                {
                mSLASH(); 

                }case 30 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:173: BAR
                {
                mBAR(); 

                }case 31 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:177: BANG
                {
                mBANG(); 

                }case 32 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:182: TILDE
                {
                mTILDE(); 

                }case 33 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:188: UNDEF
                {
                mUNDEF(); 

                }case 34 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:194: IMARK
                {
                mIMARK(); 

                }case 35 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:200: R_ID
                {
                mR_ID(); 

                }case 36 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:205: V_ID
                {
                mV_ID(); 

                }case 37 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:210: INT
                {
                mINT(); 

                }case 38 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:214: COMMENT
                {
                mCOMMENT(); 

                }case 39 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:222: WS
                {
                mWS(); 

                }case 40 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:225: STRING
                {
                mSTRING(); 

                }case 41 =>
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:1:232: CHAR
                {
                mCHAR(); 

                }
            case _ => //Do nothing
        }

    }


    protected val /*DFA14*/ dfa14 = new DFA14(this)
    /*
    final val DFA14_eotS =
        "\1\uffff\3\40\1\37\1\52\3\40\1\uffff\1\37\1\62\15\uffff\1\64\13"+
        "\uffff\3\40\2\37\1\uffff\4\40\1\37\5\uffff\3\40\1\37\1\103\1\104"+
        "\3\40\1\110\3\40\1\114\2\uffff\1\115\1\116\1\40\1\uffff\2\40\1\122"+
        "\3\uffff\1\123\2\40\2\uffff\2\40\1\130\1\131\2\uffff";
    final val DFA14_eofS =
        "\132\uffff";
    final val DFA14_minS =
        "\1\11\1\162\1\145\2\157\1\60\1\145\1\162\1\141\1\uffff\1\142\1\74"+
        "\15\uffff\1\52\13\uffff\1\157\1\161\1\143\1\154\1\164\1\uffff\1"+
        "\167\1\154\1\165\1\154\1\163\5\uffff\1\166\1\165\1\141\1\154\2\60"+
        "\1\154\1\145\1\163\1\60\2\151\1\154\1\60\2\uffff\2\60\1\145\1\uffff"+
        "\1\144\1\162\1\60\3\uffff\1\60\2\145\2\uffff\2\144\2\60\2\uffff";
    final val DFA14_maxS =
        "\1\176\1\162\1\145\1\157\1\165\1\172\1\165\1\162\1\141\1\uffff\1"+
        "\142\1\76\15\uffff\1\57\13\uffff\1\157\1\161\1\143\1\154\1\164\1"+
        "\uffff\1\167\1\154\1\165\1\154\1\163\5\uffff\1\166\1\165\1\141\1"+
        "\154\2\172\1\154\1\145\1\163\1\172\2\151\1\154\1\172\2\uffff\2\172"+
        "\1\145\1\uffff\1\144\1\162\1\172\3\uffff\1\172\2\145\2\uffff\2\144"+
        "\2\172\2\uffff";
    final val DFA14_acceptS =
        "\11\uffff\1\13\2\uffff\1\17\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1"+
        "\30\1\31\1\32\1\33\1\34\1\uffff\1\36\1\37\1\40\1\41\1\42\1\43\1"+
        "\44\1\45\1\47\1\50\1\51\5\uffff\1\5\5\uffff\1\15\1\20\1\16\1\46"+
        "\1\35\16\uffff\1\12\1\6\3\uffff\1\14\3\uffff\1\4\1\11\1\7\3\uffff"+
        "\1\3\1\10\4\uffff\1\1\1\2";
    val DFA14_specialS =
        "\132\uffff}>";
    final val DFA14_transitionS = Array[String](
        "\2\42\2\uffff\1\42\22\uffff\1\42\1\33\1\43\4\uffff\1\44\1\17\1\20"+
        "\1\uffff\1\15\1\25\1\16\1\26\1\31\12\41\1\30\1\27\1\14\1\13\1\uffff"+
        "\1\36\1\11\1\12\14\37\1\4\5\37\1\5\6\37\1\24\1\uffff\1\23\1\uffff"+
        "\1\35\1\uffff\5\40\1\10\5\40\1\3\1\40\1\6\1\40\1\1\1\40\1\2\1\40"+
        "\1\7\6\40\1\21\1\32\1\22\1\34",
        "\1\45",
        "\1\46",
        "\1\47",
        "\1\51\5\uffff\1\50",
        "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
        "\1\53\17\uffff\1\54",
        "\1\55",
        "\1\56",
        "",
        "\1\57",
        "\1\61\1\uffff\1\60",
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
        "\1\63\4\uffff\1\63",
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
        "\1\65",
        "\1\66",
        "\1\67",
        "\1\70",
        "\1\71",
        "",
        "\1\72",
        "\1\73",
        "\1\74",
        "\1\75",
        "\1\76",
        "",
        "",
        "",
        "",
        "",
        "\1\77",
        "\1\100",
        "\1\101",
        "\1\102",
        "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
        "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
        "\1\105",
        "\1\106",
        "\1\107",
        "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
        "\1\111",
        "\1\112",
        "\1\113",
        "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
        "",
        "",
        "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
        "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
        "\1\117",
        "",
        "\1\120",
        "\1\121",
        "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
        "",
        "",
        "",
        "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
        "\1\124",
        "\1\125",
        "",
        "",
        "\1\126",
        "\1\127",
        "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
        "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
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
    		    "\1\uffff\3\40\1\37\1\52\3\40\1\uffff\1\37\1\62\15\uffff\1\64\13"+
        "\uffff\3\40\2\37\1\uffff\4\40\1\37\5\uffff\3\40\1\37\1\103\1\104"+
        "\3\40\1\110\3\40\1\114\2\uffff\1\115\1\116\1\40\1\uffff\2\40\1\122"+
        "\3\uffff\1\123\2\40\2\uffff\2\40\1\130\1\131\2\uffff";
    		final val DFA14_eofS =
    		    "\132\uffff";
    		final val DFA14_minS =
    		    "\1\11\1\162\1\145\2\157\1\60\1\145\1\162\1\141\1\uffff\1\142\1"+
        "\74\15\uffff\1\52\13\uffff\1\157\1\161\1\143\1\154\1\164\1\uffff"+
        "\1\167\1\154\1\165\1\154\1\163\5\uffff\1\166\1\165\1\141\1\154\2"+
        "\60\1\154\1\145\1\163\1\60\2\151\1\154\1\60\2\uffff\2\60\1\145\1"+
        "\uffff\1\144\1\162\1\60\3\uffff\1\60\2\145\2\uffff\2\144\2\60\2"+
        "\uffff";
    		final val DFA14_maxS =
    		    "\1\176\1\162\1\145\1\157\1\165\1\172\1\165\1\162\1\141\1\uffff"+
        "\1\142\1\76\15\uffff\1\57\13\uffff\1\157\1\161\1\143\1\154\1\164"+
        "\1\uffff\1\167\1\154\1\165\1\154\1\163\5\uffff\1\166\1\165\1\141"+
        "\1\154\2\172\1\154\1\145\1\163\1\172\2\151\1\154\1\172\2\uffff\2"+
        "\172\1\145\1\uffff\1\144\1\162\1\172\3\uffff\1\172\2\145\2\uffff"+
        "\2\144\2\172\2\uffff";
    		final val DFA14_acceptS =
    		    "\11\uffff\1\13\2\uffff\1\17\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
        "\1\30\1\31\1\32\1\33\1\34\1\uffff\1\36\1\37\1\40\1\41\1\42\1\43"+
        "\1\44\1\45\1\47\1\50\1\51\5\uffff\1\5\5\uffff\1\15\1\20\1\16\1\46"+
        "\1\35\16\uffff\1\12\1\6\3\uffff\1\14\3\uffff\1\4\1\11\1\7\3\uffff"+
        "\1\3\1\10\4\uffff\1\1\1\2";
    		val DFA14_specialS =
    		    "\132\uffff}>";
    		final val DFA14_transitionS = Array[String](
    		    "\2\42\2\uffff\1\42\22\uffff\1\42\1\33\1\43\4\uffff\1\44\1\17\1"+
    		    "\20\1\uffff\1\15\1\25\1\16\1\26\1\31\12\41\1\30\1\27\1\14\1\13"+
    		    "\1\uffff\1\36\1\11\1\12\14\37\1\4\5\37\1\5\6\37\1\24\1\uffff\1"+
    		    "\23\1\uffff\1\35\1\uffff\5\40\1\10\5\40\1\3\1\40\1\6\1\40\1\1"+
    		    "\1\40\1\2\1\40\1\7\6\40\1\21\1\32\1\22\1\34",
    		    "\1\45",
    		    "\1\46",
    		    "\1\47",
    		    "\1\51\5\uffff\1\50",
    		    "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
    		    "\1\53\17\uffff\1\54",
    		    "\1\55",
    		    "\1\56",
    		    "",
    		    "\1\57",
    		    "\1\61\1\uffff\1\60",
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
    		    "\1\63\4\uffff\1\63",
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
    		    "\1\65",
    		    "\1\66",
    		    "\1\67",
    		    "\1\70",
    		    "\1\71",
    		    "",
    		    "\1\72",
    		    "\1\73",
    		    "\1\74",
    		    "\1\75",
    		    "\1\76",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "\1\77",
    		    "\1\100",
    		    "\1\101",
    		    "\1\102",
    		    "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
    		    "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
    		    "\1\105",
    		    "\1\106",
    		    "\1\107",
    		    "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
    		    "\1\111",
    		    "\1\112",
    		    "\1\113",
    		    "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
    		    "",
    		    "",
    		    "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
    		    "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
    		    "\1\117",
    		    "",
    		    "\1\120",
    		    "\1\121",
    		    "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
    		    "",
    		    "",
    		    "",
    		    "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
    		    "\1\124",
    		    "\1\125",
    		    "",
    		    "",
    		    "\1\126",
    		    "\1\127",
    		    "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
    		    "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
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

        override def getDescription(): String = "1:1: Tokens : ( T__63 | T__64 | T__65 | T__66 | T__67 | NU | TRUE | FALSE | NULL | NOT | ARROBAS | ABS | RARROW | EQ | LT | LTEQ | PLUS | MINUS | LPAREN | RPAREN | LCURL | RCURL | RBRACK | LBRACK | COMMA | POINT | SEMI | COLON | SLASH | BAR | BANG | TILDE | UNDEF | IMARK | R_ID | V_ID | INT | COMMENT | WS | STRING | CHAR );"
        
    }
 

}