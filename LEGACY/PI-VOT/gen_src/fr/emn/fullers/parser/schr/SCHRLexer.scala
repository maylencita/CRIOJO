// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g 2010-01-11 15:14:55

package fr.emn.fullers.parser.schr;

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


    	val T__68:Int = 68
    	val PACKAGE:Int = 29
    	val EXPONENT:Int = 64
    	val LT:Int = 46
    	val EQ_OP:Int = 45
    	val LBRACK:Int = 56
    	val DEF:Int = 17
    	val POINT:Int = 42
    	val HEAD:Int = 9
    	val LTEQ:Int = 47
    	val OCTAL_ESC:Int = 67
    	val NEW:Int = 4
    	val CHAR:Int = 63
    	val EXCL:Int = 41
    	val NOT:Int = 23
    	val ID:Int = 33
    	val EOF:Int = -1
    	val LPAREN:Int = 38
    	val TAIL:Int = 22
    	val ARROBAS:Int = 36
    	val RPAREN:Int = 40
    	val IMPORT:Int = 15
    	val NAME:Int = 12
    	val ESC_SEQ:Int = 62
    	val SLASH:Int = 51
    	val IN:Int = 27
    	val COMMA:Int = 37
    	val IS:Int = 44
    	val TILDE:Int = 50
    	val PLUS:Int = 48
    	val BODY:Int = 10
    	val EQ:Int = 54
    	val COMMENT:Int = 60
    	val SOLVERCLASS:Int = 5
    	val PARAMS:Int = 18
    	val RBRACK:Int = 57
    	val IMPLIES:Int = 43
    	val RULE:Int = 7
    	val UNICODE_ESC:Int = 66
    	val BOOL:Int = 58
    	val VALUES:Int = 14
    	val HEX_DIGIT:Int = 65
    	val INT:Int = 52
    	val MINUS:Int = 49
    	val LIST:Int = 20
    	val SEMI:Int = 31
    	val LCURL:Int = 34
    	val WS:Int = 61
    	val OUT:Int = 28
    	val CONSDEF:Int = 11
    	val RCURL:Int = 35
    	val OR:Int = 24
    	val ALIAS:Int = 16
    	val EMPTYLIST:Int = 21
    	val SOLVER:Int = 32
    	val START:Int = 19
    	val PROGRAM:Int = 6
    	val ARITY:Int = 13
    	val USE:Int = 55
    	val FUN:Int = 25
    	val CONSTRAINT:Int = 8
    	val BAR:Int = 39
    	val INNERRULE:Int = 26
    	val LET:Int = 53
    	val STRING:Int = 30
    	val CHR_SIMP:Int = 59

    override def getGrammarFileName = "/Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g"

    // $ANTLR start "T__68"
    @throws(classOf[RecognitionException])
     final def mT__68() /*throws RecognitionException*/ {
        try {
            var _type = T__68;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:13:7: ( 'new' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:13:9: 'new'
            {
            smatch("new"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "PACKAGE"
    @throws(classOf[RecognitionException])
     final def mPACKAGE() /*throws RecognitionException*/ {
        try {
            var _type = PACKAGE;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:239:2: ( 'package' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:239:4: 'package'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:242:2: ( 'solver' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:242:4: 'solver'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:245:2: ( '@' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:245:4: '@'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:248:2: ( '<=>' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:248:4: '<=>'
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

    // $ANTLR start "IMPLIES"
    @throws(classOf[RecognitionException])
     final def mIMPLIES() /*throws RecognitionException*/ {
        try {
            var _type = IMPLIES;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:251:2: ( '->' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:251:4: '->'
            {
            smatch("->"); 


            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPLIES"

    // $ANTLR start "IS"
    @throws(classOf[RecognitionException])
     final def mIS() /*throws RecognitionException*/ {
        try {
            var _type = IS;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:254:2: ( 'is' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:254:4: 'is'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:257:2: ( 'constraint' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:257:4: 'constraint'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:260:2: ( 'let' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:260:4: 'let'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:263:2: ( 'in' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:263:4: 'in'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:266:2: ( 'use' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:266:4: 'use'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:269:2: ( '=' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:269:4: '='
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:272:2: ( '==' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:272:4: '=='
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:275:2: ( '<' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:275:4: '<'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:278:2: ( '=<' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:278:4: '=<'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:281:2: ( '+' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:281:4: '+'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:284:2: ( '-' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:284:4: '-'
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

    // $ANTLR start "TILDE"
    @throws(classOf[RecognitionException])
     final def mTILDE() /*throws RecognitionException*/ {
        try {
            var _type = TILDE;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:287:2: ( '~' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:287:4: '~'
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

    // $ANTLR start "EXCL"
    @throws(classOf[RecognitionException])
     final def mEXCL() /*throws RecognitionException*/ {
        try {
            var _type = EXCL;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:290:2: ( '!' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:290:4: '!'
            {
            smatch('!'); 

            }

            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXCL"

    // $ANTLR start "LPAREN"
    @throws(classOf[RecognitionException])
     final def mLPAREN() /*throws RecognitionException*/ {
        try {
            var _type = LPAREN;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:292:9: ( '(' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:292:11: '('
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:295:8: ( ')' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:295:10: ')'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:298:7: ( '{' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:298:9: '{'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:301:7: ( '}' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:301:9: '}'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:305:2: ( ']' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:305:4: ']'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:308:2: ( '[' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:308:4: '['
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:311:7: ( ',' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:311:9: ','
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:314:7: ( '.' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:314:9: '.'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:317:6: ( ';' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:317:8: ';'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:319:7: ( '/' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:319:10: '/'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:321:6: ( '|' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:321:9: '|'
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

    // $ANTLR start "BOOL"
    @throws(classOf[RecognitionException])
     final def mBOOL() /*throws RecognitionException*/ {
        try {
            var _type = BOOL;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:324:2: ( 'true' | 'false' )
            var alt1=2;
            var LA1_0 = input.LA(1);

            if ( (LA1_0=='t') ) {
                alt1=1;
            }
            else if ( (LA1_0=='f') ) {
                alt1=2;
            }
            else {
                val nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            alt1 match{
                case 1 =>
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:324:4: 'true'
                    {
                    smatch("true"); 


                    }case 2 =>
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:324:13: 'false'
                    {
                    smatch("false"); 


                    }
                case _ => //Do nothing
            }
            state.setType (_type)
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOL"

    // $ANTLR start "ID"
    @throws(classOf[RecognitionException])
     final def mID() /*throws RecognitionException*/ {
        try {
            var _type = ID;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:326:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:326:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                val mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:326:31: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
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
            		    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:
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
    // $ANTLR end "ID"

    // $ANTLR start "INT"
    @throws(classOf[RecognitionException])
     final def mINT() /*throws RecognitionException*/ {
        try {
            var _type = INT;
            var _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:329:5: ( ( '0' .. '9' )+ )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:329:7: ( '0' .. '9' )+
            {
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:329:7: ( '0' .. '9' )+
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
            		    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:329:7: '0' .. '9'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:340:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
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
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:340:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    smatch("//"); 

                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:340:14: (~ ( '\\n' | '\\r' ) )*
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
                    		    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:340:14: ~ ( '\\n' | '\\r' )
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

                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:340:28: ( '\\r' )?
                    var alt5=2;
                    var LA5_0 = input.LA(1);

                    if ( (LA5_0=='\r') ) {
                        alt5=1;
                    }
                    alt5 match{
                        case 1 =>
                            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:340:28: '\\r'
                            {
                            smatch('\r'); 

                            }
                        case _ => //Do nothing
                    }

                    smatch('\n'); 
                    _channel=HIDDEN;

                    }case 2 =>
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:341:9: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    smatch("/*"); 

                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:341:14: ( options {greedy=false; } : . )*
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
                    		    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:341:42: .
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:344:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:344:9: ( ' ' | '\\t' | '\\r' | '\\n' )
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:352:5: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:352:8: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            smatch('\"'); 
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:352:12: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
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
            		    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:352:14: ESC_SEQ
            		    {
            		    mESC_SEQ(); 

            		    }case 2 =>
            		    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:352:24: ~ ( '\\\\' | '\"' )
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:355:5: ( '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:355:8: '\\'' ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            smatch('\''); 
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:355:13: ( ESC_SEQ | ~ ( '\\'' | '\\\\' ) )
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
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:355:15: ESC_SEQ
                    {
                    mESC_SEQ(); 

                    }case 2 =>
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:355:25: ~ ( '\\'' | '\\\\' )
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:359:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:359:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                val mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:359:22: ( '+' | '-' )?
            var alt10=2;
            var LA10_0 = input.LA(1);

            if ( (LA10_0=='+'||LA10_0=='-') ) {
                alt10=1;
            }
            alt10 match{
                case 1 =>
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:
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

            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:359:33: ( '0' .. '9' )+
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
            		    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:359:34: '0' .. '9'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:362:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:362:13: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:366:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
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
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:366:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:367:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }case 3 =>
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:368:9: OCTAL_ESC
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:373:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
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
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:373:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:373:14: ( '0' .. '3' )
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:373:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:373:25: ( '0' .. '7' )
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:373:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:373:36: ( '0' .. '7' )
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:373:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }case 2 =>
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:374:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:374:14: ( '0' .. '7' )
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:374:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:374:25: ( '0' .. '7' )
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:374:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }case 3 =>
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:375:9: '\\\\' ( '0' .. '7' )
                    {
                    smatch('\\'); 
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:375:14: ( '0' .. '7' )
                    // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:375:15: '0' .. '7'
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
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:380:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:380:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
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
        // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:8: ( T__68 | PACKAGE | SOLVER | ARROBAS | CHR_SIMP | IMPLIES | IS | CONSTRAINT | LET | IN | USE | EQ | EQ_OP | LT | LTEQ | PLUS | MINUS | TILDE | EXCL | LPAREN | RPAREN | LCURL | RCURL | RBRACK | LBRACK | COMMA | POINT | SEMI | SLASH | BAR | BOOL | ID | INT | COMMENT | WS | STRING | CHAR )
        var alt14=37;
        alt14 = dfa14.predict(input);
        alt14 match{
            case 1 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:10: T__68
                {
                mT__68(); 

                }case 2 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:16: PACKAGE
                {
                mPACKAGE(); 

                }case 3 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:24: SOLVER
                {
                mSOLVER(); 

                }case 4 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:31: ARROBAS
                {
                mARROBAS(); 

                }case 5 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:39: CHR_SIMP
                {
                mCHR_SIMP(); 

                }case 6 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:48: IMPLIES
                {
                mIMPLIES(); 

                }case 7 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:56: IS
                {
                mIS(); 

                }case 8 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:59: CONSTRAINT
                {
                mCONSTRAINT(); 

                }case 9 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:70: LET
                {
                mLET(); 

                }case 10 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:74: IN
                {
                mIN(); 

                }case 11 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:77: USE
                {
                mUSE(); 

                }case 12 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:81: EQ
                {
                mEQ(); 

                }case 13 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:84: EQ_OP
                {
                mEQ_OP(); 

                }case 14 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:90: LT
                {
                mLT(); 

                }case 15 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:93: LTEQ
                {
                mLTEQ(); 

                }case 16 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:98: PLUS
                {
                mPLUS(); 

                }case 17 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:103: MINUS
                {
                mMINUS(); 

                }case 18 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:109: TILDE
                {
                mTILDE(); 

                }case 19 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:115: EXCL
                {
                mEXCL(); 

                }case 20 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:120: LPAREN
                {
                mLPAREN(); 

                }case 21 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:127: RPAREN
                {
                mRPAREN(); 

                }case 22 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:134: LCURL
                {
                mLCURL(); 

                }case 23 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:140: RCURL
                {
                mRCURL(); 

                }case 24 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:146: RBRACK
                {
                mRBRACK(); 

                }case 25 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:153: LBRACK
                {
                mLBRACK(); 

                }case 26 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:160: COMMA
                {
                mCOMMA(); 

                }case 27 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:166: POINT
                {
                mPOINT(); 

                }case 28 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:172: SEMI
                {
                mSEMI(); 

                }case 29 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:177: SLASH
                {
                mSLASH(); 

                }case 30 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:183: BAR
                {
                mBAR(); 

                }case 31 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:187: BOOL
                {
                mBOOL(); 

                }case 32 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:192: ID
                {
                mID(); 

                }case 33 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:195: INT
                {
                mINT(); 

                }case 34 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:199: COMMENT
                {
                mCOMMENT(); 

                }case 35 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:207: WS
                {
                mWS(); 

                }case 36 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:210: STRING
                {
                mSTRING(); 

                }case 37 =>
                // /Users/mayleen/THESE/WS_ANTLR_SCALA/PI-VOT/grammars/SCHR.g:1:217: CHAR
                {
                mCHAR(); 

                }
            case _ => //Do nothing
        }

    }


    protected val /*DFA14*/ dfa14 = new DFA14(this)
    /*
    final val DFA14_eotS =
        "\1\uffff\3\34\1\uffff\1\45\1\47\4\34\1\57\14\uffff\1\61\1\uffff"+
        "\2\34\5\uffff\3\34\4\uffff\1\67\1\70\3\34\5\uffff\2\34\1\76\2\34"+
        "\2\uffff\1\34\1\102\1\103\2\34\1\uffff\3\34\2\uffff\1\111\4\34\1"+
        "\uffff\1\111\1\34\1\117\1\34\1\121\1\uffff\1\34\1\uffff\2\34\1\125"+
        "\1\uffff";
    final val DFA14_eofS =
        "\126\uffff";
    final val DFA14_minS =
        "\1\11\1\145\1\141\1\157\1\uffff\1\75\1\76\1\156\1\157\1\145\1\163"+
        "\1\74\14\uffff\1\52\1\uffff\1\162\1\141\5\uffff\1\167\1\143\1\154"+
        "\4\uffff\2\60\1\156\1\164\1\145\5\uffff\1\165\1\154\1\60\1\153\1"+
        "\166\2\uffff\1\163\2\60\1\145\1\163\1\uffff\1\141\1\145\1\164\2"+
        "\uffff\1\60\1\145\1\147\2\162\1\uffff\1\60\1\145\1\60\1\141\1\60"+
        "\1\uffff\1\151\1\uffff\1\156\1\164\1\60\1\uffff";
    final val DFA14_maxS =
        "\1\176\1\145\1\141\1\157\1\uffff\1\75\1\76\1\163\1\157\1\145\1\163"+
        "\1\75\14\uffff\1\57\1\uffff\1\162\1\141\5\uffff\1\167\1\143\1\154"+
        "\4\uffff\2\172\1\156\1\164\1\145\5\uffff\1\165\1\154\1\172\1\153"+
        "\1\166\2\uffff\1\163\2\172\1\145\1\163\1\uffff\1\141\1\145\1\164"+
        "\2\uffff\1\172\1\145\1\147\2\162\1\uffff\1\172\1\145\1\172\1\141"+
        "\1\172\1\uffff\1\151\1\uffff\1\156\1\164\1\172\1\uffff";
    final val DFA14_acceptS =
        "\4\uffff\1\4\7\uffff\1\20\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32\1\33\1\34\1\uffff\1\36\2\uffff\1\40\1\41\1\43\1\44\1\45\3"+
        "\uffff\1\5\1\16\1\6\1\21\5\uffff\1\15\1\17\1\14\1\42\1\35\5\uffff"+
        "\1\7\1\12\5\uffff\1\1\3\uffff\1\11\1\13\5\uffff\1\37\5\uffff\1\3"+
        "\1\uffff\1\2\3\uffff\1\10";
    val DFA14_specialS =
        "\126\uffff}>";
    final val DFA14_transitionS = Array[String](
        "\2\36\2\uffff\1\36\22\uffff\1\36\1\16\1\37\4\uffff\1\40\1\17\1\20"+
        "\1\uffff\1\14\1\25\1\6\1\26\1\30\12\35\1\uffff\1\27\1\5\1\13\2\uffff"+
        "\1\4\32\34\1\24\1\uffff\1\23\1\uffff\1\34\1\uffff\2\34\1\10\2\34"+
        "\1\33\2\34\1\7\2\34\1\11\1\34\1\1\1\34\1\2\2\34\1\3\1\32\1\12\5"+
        "\34\1\21\1\31\1\22\1\15",
        "\1\41",
        "\1\42",
        "\1\43",
        "",
        "\1\44",
        "\1\46",
        "\1\51\4\uffff\1\50",
        "\1\52",
        "\1\53",
        "\1\54",
        "\1\56\1\55",
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
        "\1\60\4\uffff\1\60",
        "",
        "\1\62",
        "\1\63",
        "",
        "",
        "",
        "",
        "",
        "\1\64",
        "\1\65",
        "\1\66",
        "",
        "",
        "",
        "",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\1\71",
        "\1\72",
        "\1\73",
        "",
        "",
        "",
        "",
        "",
        "\1\74",
        "\1\75",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\1\77",
        "\1\100",
        "",
        "",
        "\1\101",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\1\104",
        "\1\105",
        "",
        "\1\106",
        "\1\107",
        "\1\110",
        "",
        "",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\1\112",
        "\1\113",
        "\1\114",
        "\1\115",
        "",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\1\116",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\1\120",
        "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "",
        "\1\122",
        "",
        "\1\123",
        "\1\124",
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
    		    "\1\uffff\3\34\1\uffff\1\45\1\47\4\34\1\57\14\uffff\1\61\1\uffff"+
        "\2\34\5\uffff\3\34\4\uffff\1\67\1\70\3\34\5\uffff\2\34\1\76\2\34"+
        "\2\uffff\1\34\1\102\1\103\2\34\1\uffff\3\34\2\uffff\1\111\4\34\1"+
        "\uffff\1\111\1\34\1\117\1\34\1\121\1\uffff\1\34\1\uffff\2\34\1\125"+
        "\1\uffff";
    		final val DFA14_eofS =
    		    "\126\uffff";
    		final val DFA14_minS =
    		    "\1\11\1\145\1\141\1\157\1\uffff\1\75\1\76\1\156\1\157\1\145\1"+
        "\163\1\74\14\uffff\1\52\1\uffff\1\162\1\141\5\uffff\1\167\1\143"+
        "\1\154\4\uffff\2\60\1\156\1\164\1\145\5\uffff\1\165\1\154\1\60\1"+
        "\153\1\166\2\uffff\1\163\2\60\1\145\1\163\1\uffff\1\141\1\145\1"+
        "\164\2\uffff\1\60\1\145\1\147\2\162\1\uffff\1\60\1\145\1\60\1\141"+
        "\1\60\1\uffff\1\151\1\uffff\1\156\1\164\1\60\1\uffff";
    		final val DFA14_maxS =
    		    "\1\176\1\145\1\141\1\157\1\uffff\1\75\1\76\1\163\1\157\1\145\1"+
        "\163\1\75\14\uffff\1\57\1\uffff\1\162\1\141\5\uffff\1\167\1\143"+
        "\1\154\4\uffff\2\172\1\156\1\164\1\145\5\uffff\1\165\1\154\1\172"+
        "\1\153\1\166\2\uffff\1\163\2\172\1\145\1\163\1\uffff\1\141\1\145"+
        "\1\164\2\uffff\1\172\1\145\1\147\2\162\1\uffff\1\172\1\145\1\172"+
        "\1\141\1\172\1\uffff\1\151\1\uffff\1\156\1\164\1\172\1\uffff";
    		final val DFA14_acceptS =
    		    "\4\uffff\1\4\7\uffff\1\20\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1"+
        "\31\1\32\1\33\1\34\1\uffff\1\36\2\uffff\1\40\1\41\1\43\1\44\1\45"+
        "\3\uffff\1\5\1\16\1\6\1\21\5\uffff\1\15\1\17\1\14\1\42\1\35\5\uffff"+
        "\1\7\1\12\5\uffff\1\1\3\uffff\1\11\1\13\5\uffff\1\37\5\uffff\1\3"+
        "\1\uffff\1\2\3\uffff\1\10";
    		val DFA14_specialS =
    		    "\126\uffff}>";
    		final val DFA14_transitionS = Array[String](
    		    "\2\36\2\uffff\1\36\22\uffff\1\36\1\16\1\37\4\uffff\1\40\1\17\1"+
    		    "\20\1\uffff\1\14\1\25\1\6\1\26\1\30\12\35\1\uffff\1\27\1\5\1\13"+
    		    "\2\uffff\1\4\32\34\1\24\1\uffff\1\23\1\uffff\1\34\1\uffff\2\34"+
    		    "\1\10\2\34\1\33\2\34\1\7\2\34\1\11\1\34\1\1\1\34\1\2\2\34\1\3"+
    		    "\1\32\1\12\5\34\1\21\1\31\1\22\1\15",
    		    "\1\41",
    		    "\1\42",
    		    "\1\43",
    		    "",
    		    "\1\44",
    		    "\1\46",
    		    "\1\51\4\uffff\1\50",
    		    "\1\52",
    		    "\1\53",
    		    "\1\54",
    		    "\1\56\1\55",
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
    		    "\1\60\4\uffff\1\60",
    		    "",
    		    "\1\62",
    		    "\1\63",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "\1\64",
    		    "\1\65",
    		    "\1\66",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\1\71",
    		    "\1\72",
    		    "\1\73",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "",
    		    "\1\74",
    		    "\1\75",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\1\77",
    		    "\1\100",
    		    "",
    		    "",
    		    "\1\101",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\1\104",
    		    "\1\105",
    		    "",
    		    "\1\106",
    		    "\1\107",
    		    "\1\110",
    		    "",
    		    "",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\1\112",
    		    "\1\113",
    		    "\1\114",
    		    "\1\115",
    		    "",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\1\116",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "\1\120",
    		    "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
    		    "",
    		    "\1\122",
    		    "",
    		    "\1\123",
    		    "\1\124",
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

        override def getDescription(): String = "1:1: Tokens : ( T__68 | PACKAGE | SOLVER | ARROBAS | CHR_SIMP | IMPLIES | IS | CONSTRAINT | LET | IN | USE | EQ | EQ_OP | LT | LTEQ | PLUS | MINUS | TILDE | EXCL | LPAREN | RPAREN | LCURL | RCURL | RBRACK | LBRACK | COMMA | POINT | SEMI | SLASH | BAR | BOOL | ID | INT | COMMENT | WS | STRING | CHAR );"
        
    }
 

}