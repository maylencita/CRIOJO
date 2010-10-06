// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g 2010-10-01 12:34:31

package fr.emn.criojo.parser;

import fr.emn.criojo.parser.tree._


import org.antlr.runtime._;
import BaseRecognizer.HIDDEN
import java.util.Stack;
//import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree._

import org.antlr.runtime.Token.{DOWN, UP}
 class CREOLE_XParser(input: TokenStream, state:SRecognizerSharedState) 
 extends SParser(input,state) {
 
 	def this(input: TokenStream){
 		this(input, new SRecognizerSharedState())
 	}
 
    val tokenNames:Array[String] = Array[String] (
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SCRIPT", "ATOM", "VARS", "RULE", "HEAD", "BODY", "MULTI", "DECLARATION", "EMPTYLIST", "PUBLIC", "PRIVATE", "PROCESS", "GUARD", "EMPTY", "INT_ATOM", "STR_ATOM", "NULL_ATOM", "LPAREN", "COLON", "SEMI", "RPAREN", "COMMA", "UNDEF", "R_ID", "ARROBAS", "TILDE", "BAR", "BANG", "RARROW", "LBRACK", "RBRACK", "IMARK", "ABS", "NU", "TRUE", "FALSE", "NULL", "V_ID", "INT", "STRING", "EQ_OP", "LT", "LTEQ", "PLUS", "MINUS", "LCURL", "RCURL", "POINT", "SLASH", "COMMENT", "WS", "ESC_SEQ", "CHAR", "EXPONENT", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "'public'", "'private'", "'T'"
    )
      val NULL_ATOM:Int = 20;
      val EXPONENT:Int = 57;
      val LT:Int = 45;
      val EQ_OP:Int = 44;
      val LBRACK:Int = 33;
      val T__62:Int = 62;
      val T__63:Int = 63;
      val POINT:Int = 51;
      val HEAD:Int = 8;
      val OCTAL_ESC:Int = 60;
      val LTEQ:Int = 46;
      val CHAR:Int = 56;
      val MULTI:Int = 10;
      val ATOM:Int = 5;
      val T__61:Int = 61;
      val EOF:Int = -1;
      val DECLARATION:Int = 11;
      val LPAREN:Int = 21;
      val ARROBAS:Int = 28;
      val INT_ATOM:Int = 18;
      val RPAREN:Int = 24;
      val ESC_SEQ:Int = 55;
      val SLASH:Int = 52;
      val COMMA:Int = 25;
      val TILDE:Int = 29;
      val PLUS:Int = 47;
      val BODY:Int = 9;
      val UNDEF:Int = 26;
      val COMMENT:Int = 53;
      val IMARK:Int = 35;
      val RBRACK:Int = 34;
      val SCRIPT:Int = 4;
      val RULE:Int = 7;
      val NU:Int = 37;
      val R_ID:Int = 27;
      val PRIVATE:Int = 14;
      val VARS:Int = 6;
      val UNICODE_ESC:Int = 59;
      val RARROW:Int = 32;
      val NULL:Int = 40;
      val HEX_DIGIT:Int = 58;
      val V_ID:Int = 41;
      val STR_ATOM:Int = 19;
      val INT:Int = 42;
      val BANG:Int = 31;
      val MINUS:Int = 48;
      val SEMI:Int = 23;
      val TRUE:Int = 38;
      val EMPTY:Int = 17;
      val COLON:Int = 22;
      val ABS:Int = 36;
      val LCURL:Int = 49;
      val WS:Int = 54;
      val RCURL:Int = 50;
      val EMPTYLIST:Int = 12;
      val GUARD:Int = 16;
      val PROCESS:Int = 15;
      val FALSE:Int = 39;
      val PUBLIC:Int = 13;
      val BAR:Int = 30;
      val STRING:Int = 43;

    // delegates
    // delegators


        
    protected var adaptor:TreeAdaptor = new CommonTreeAdaptor()

    def setTreeAdaptor(adaptor:TreeAdaptor) {
        this.adaptor = adaptor
    }
    def  getTreeAdaptor():TreeAdaptor = adaptor

    override def getTokenNames: Array[String] = /*CREOLE_XParser.*/tokenNames 
    override def getGrammarFileName(): String = "src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g"


    	def getCHRTreeTokens = new CHRTreeTokens(this.tokenNames)


      class start_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "start" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:43:1: start : process ;
    @throws(classOf[RecognitionException])
     final def start():/*CREOLE_XParser.*/start_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/start_return  = new /*CREOLE_XParser.*/start_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var process1:/*CREOLE_XParser.*/process_return = null



        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:44:5: ( process )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:44:9: process
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            pushFollow(FOLLOW_process_in_start185);
            process1=process();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, process1.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "start"

      class process_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "process" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:47:1: process : declaration script -> ^( PROCESS declaration ^( SCRIPT script ) ) ;
    @throws(classOf[RecognitionException])
     final def process():/*CREOLE_XParser.*/process_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/process_return  = new /*CREOLE_XParser.*/process_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var declaration2:/*CREOLE_XParser.*/declaration_return = null

        var script3:/*CREOLE_XParser.*/script_return = null


        var stream_declaration:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        var stream_script:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule script");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:48:5: ( declaration script -> ^( PROCESS declaration ^( SCRIPT script ) ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:48:9: declaration script
            {
            pushFollow(FOLLOW_declaration_in_process204);
            declaration2=declaration();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declaration.add(declaration2.getTree());
            pushFollow(FOLLOW_script_in_process206);
            script3=script();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_script.add(script3.getTree());


            // AST REWRITE
            // elements: script, declaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 48:28: -> ^( PROCESS declaration ^( SCRIPT script ) )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:48:31: ^( PROCESS declaration ^( SCRIPT script ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(PROCESS, "PROCESS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                adaptor.addChild(root_1, stream_declaration.nextTree());
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:48:53: ^( SCRIPT script )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(SCRIPT, "SCRIPT").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_script.nextTree());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "process"

      class declaration_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "declaration" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:51:1: declaration : LPAREN 'public' COLON rlist SEMI 'private' COLON rlist RPAREN -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) ) ;
    @throws(classOf[RecognitionException])
     final def declaration():/*CREOLE_XParser.*/declaration_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/declaration_return  = new /*CREOLE_XParser.*/declaration_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN4: Token =null
        var string_literal5: Token =null
        var COLON6: Token =null
        var SEMI8: Token =null
        var string_literal9: Token =null
        var COLON10: Token =null
        var RPAREN12: Token =null
        var rlist7:/*CREOLE_XParser.*/rlist_return = null

        var rlist11:/*CREOLE_XParser.*/rlist_return = null


        var LPAREN4_tree:CHRTree=null
        var string_literal5_tree:CHRTree=null
        var COLON6_tree:CHRTree=null
        var SEMI8_tree:CHRTree=null
        var string_literal9_tree:CHRTree=null
        var COLON10_tree:CHRTree=null
        var RPAREN12_tree:CHRTree=null
        var stream_COLON:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COLON")
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_SEMI:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SEMI")
        var stream_62:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 62")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_61:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 61")
        var stream_rlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rlist");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:52:5: ( LPAREN 'public' COLON rlist SEMI 'private' COLON rlist RPAREN -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:52:9: LPAREN 'public' COLON rlist SEMI 'private' COLON rlist RPAREN
            {
            LPAREN4=smatch(input,LPAREN,FOLLOW_LPAREN_in_declaration239).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN4);

            string_literal5=smatch(input,61,FOLLOW_61_in_declaration241).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_61.add(string_literal5);

            COLON6=smatch(input,COLON,FOLLOW_COLON_in_declaration243).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON6);

            pushFollow(FOLLOW_rlist_in_declaration245);
            rlist7=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist7.getTree());
            SEMI8=smatch(input,SEMI,FOLLOW_SEMI_in_declaration247).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI8);

            string_literal9=smatch(input,62,FOLLOW_62_in_declaration249).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_62.add(string_literal9);

            COLON10=smatch(input,COLON,FOLLOW_COLON_in_declaration251).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON10);

            pushFollow(FOLLOW_rlist_in_declaration253);
            rlist11=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist11.getTree());
            RPAREN12=smatch(input,RPAREN,FOLLOW_RPAREN_in_declaration255).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN12);



            // AST REWRITE
            // elements: rlist, rlist
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 52:71: -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:53:13: ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(DECLARATION, "DECLARATION").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:53:27: ^( PUBLIC rlist )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(PUBLIC, "PUBLIC").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_rlist.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:53:43: ^( PRIVATE rlist )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(PRIVATE, "PRIVATE").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_rlist.nextTree());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "declaration"

      class rlist_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "rlist" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:56:1: rlist : ( rdeclaration ( COMMA rdeclaration )* -> ( rdeclaration )* | UNDEF -> EMPTYLIST );
    @throws(classOf[RecognitionException])
     final def rlist():/*CREOLE_XParser.*/rlist_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/rlist_return  = new /*CREOLE_XParser.*/rlist_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var COMMA14: Token =null
        var UNDEF16: Token =null
        var rdeclaration13:/*CREOLE_XParser.*/rdeclaration_return = null

        var rdeclaration15:/*CREOLE_XParser.*/rdeclaration_return = null


        var COMMA14_tree:CHRTree=null
        var UNDEF16_tree:CHRTree=null
        var stream_UNDEF:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token UNDEF")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_rdeclaration:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rdeclaration");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:57:5: ( rdeclaration ( COMMA rdeclaration )* -> ( rdeclaration )* | UNDEF -> EMPTYLIST )
            var alt2=2;
            var LA2_0 = input.LA(1);

            if ( (LA2_0==R_ID||LA2_0==TILDE) ) {
                alt2=1;
            }
            else if ( (LA2_0==UNDEF) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            alt2 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:57:9: rdeclaration ( COMMA rdeclaration )*
                    {
                    pushFollow(FOLLOW_rdeclaration_in_rlist304);
                    rdeclaration13=rdeclaration();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rdeclaration.add(rdeclaration13.getTree());
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:57:22: ( COMMA rdeclaration )*
                    //loop1:
                    var guard = true
                    while(guard) {
                        var alt1=2;
                        var LA1_0 = input.LA(1);

                        if ( (LA1_0==COMMA) ) {
                            alt1=1;
                        }


                        alt1 match{
                    		case 1 =>
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:57:23: COMMA rdeclaration
                    		    {
                    		    COMMA14=smatch(input,COMMA,FOLLOW_COMMA_in_rlist307).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA14);

                    		    pushFollow(FOLLOW_rdeclaration_in_rlist309);
                    		    rdeclaration15=rdeclaration();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_rdeclaration.add(rdeclaration15.getTree());

                    		    }
                    		case _ =>
                    		    guard = false //loop1;
                        }
                    } //while (true);



                    // AST REWRITE
                    // elements: rdeclaration
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 57:44: -> ( rdeclaration )*
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:57:47: ( rdeclaration )*
                        while ( stream_rdeclaration.hasNext() ) {
                            adaptor.addChild(root_0, stream_rdeclaration.nextTree());

                        }
                        stream_rdeclaration.reset();

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:58:9: UNDEF
                    {
                    UNDEF16=smatch(input,UNDEF,FOLLOW_UNDEF_in_rlist326).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UNDEF.add(UNDEF16);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 58:15: -> EMPTYLIST
                    {
                        adaptor.addChild(root_0, adaptor.create(EMPTYLIST, "EMPTYLIST").asInstanceOf[CHRTree]);

                    }

                    retval.tree = root_0;}
                    }
                case _ => //Do nothing
            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "rlist"

      class rdeclaration_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "rdeclaration" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:61:1: rdeclaration : ( R_ID | R_ID ARROBAS url -> ^( ARROBAS R_ID url ) | TILDE R_ID -> ^( MULTI R_ID ) );
    @throws(classOf[RecognitionException])
     final def rdeclaration():/*CREOLE_XParser.*/rdeclaration_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/rdeclaration_return  = new /*CREOLE_XParser.*/rdeclaration_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var R_ID17: Token =null
        var R_ID18: Token =null
        var ARROBAS19: Token =null
        var TILDE21: Token =null
        var R_ID22: Token =null
        var url20:/*CREOLE_XParser.*/url_return = null


        var R_ID17_tree:CHRTree=null
        var R_ID18_tree:CHRTree=null
        var ARROBAS19_tree:CHRTree=null
        var TILDE21_tree:CHRTree=null
        var R_ID22_tree:CHRTree=null
        var stream_R_ID:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token R_ID")
        var stream_ARROBAS:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ARROBAS")
        var stream_TILDE:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token TILDE")
        var stream_url:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule url");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:62:5: ( R_ID | R_ID ARROBAS url -> ^( ARROBAS R_ID url ) | TILDE R_ID -> ^( MULTI R_ID ) )
            var alt3=3;
            var LA3_0 = input.LA(1);

            if ( (LA3_0==R_ID) ) {
                var LA3_1 = input.LA(2);

                if ( (LA3_1==ARROBAS) ) {
                    alt3=2;
                }
                else if ( (LA3_1==EOF||(LA3_1>=SEMI && LA3_1<=COMMA)) ) {
                    alt3=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    val nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA3_0==TILDE) ) {
                alt3=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            alt3 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:62:9: R_ID
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    R_ID17=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration349).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    R_ID17_tree = adaptor.create(R_ID17).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, R_ID17_tree);
                    }

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:63:9: R_ID ARROBAS url
                    {
                    R_ID18=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration359).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_R_ID.add(R_ID18);

                    ARROBAS19=smatch(input,ARROBAS,FOLLOW_ARROBAS_in_rdeclaration361).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ARROBAS.add(ARROBAS19);

                    pushFollow(FOLLOW_url_in_rdeclaration363);
                    url20=url();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_url.add(url20.getTree());


                    // AST REWRITE
                    // elements: url, ARROBAS, R_ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 63:26: -> ^( ARROBAS R_ID url )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:63:29: ^( ARROBAS R_ID url )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(stream_ARROBAS.nextNode(), root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_R_ID.nextNode());
                        adaptor.addChild(root_1, stream_url.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:64:9: TILDE R_ID
                    {
                    TILDE21=smatch(input,TILDE,FOLLOW_TILDE_in_rdeclaration383).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TILDE.add(TILDE21);

                    R_ID22=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration385).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_R_ID.add(R_ID22);



                    // AST REWRITE
                    // elements: R_ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 64:20: -> ^( MULTI R_ID )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:64:23: ^( MULTI R_ID )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(MULTI, "MULTI").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_R_ID.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                case _ => //Do nothing
            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "rdeclaration"

      class script_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "script" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:67:1: script : parallel ( SEMI parallel )* ;
    @throws(classOf[RecognitionException])
     final def script():/*CREOLE_XParser.*/script_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/script_return  = new /*CREOLE_XParser.*/script_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var SEMI24: Token =null
        var parallel23:/*CREOLE_XParser.*/parallel_return = null

        var parallel25:/*CREOLE_XParser.*/parallel_return = null


        var SEMI24_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:68:5: ( parallel ( SEMI parallel )* )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:68:9: parallel ( SEMI parallel )*
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            pushFollow(FOLLOW_parallel_in_script412);
            parallel23=parallel();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, parallel23.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:68:18: ( SEMI parallel )*
            //loop4:
            var guard = true
            while(guard) {
                var alt4=2;
                var LA4_0 = input.LA(1);

                if ( (LA4_0==SEMI) ) {
                    alt4=1;
                }


                alt4 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:68:19: SEMI parallel
            		    {
            		    SEMI24=smatch(input,SEMI,FOLLOW_SEMI_in_script415).asInstanceOf[Token]; if (state.failed) return retval;
            		    if ( state.backtracking==0 ) {
            		    SEMI24_tree = adaptor.create(SEMI24).asInstanceOf[CHRTree];
            		    root_0 = adaptor.becomeRoot(SEMI24_tree, root_0).asInstanceOf[CHRTree]
            		    }
            		    pushFollow(FOLLOW_parallel_in_script418);
            		    parallel25=parallel();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) adaptor.addChild(root_0, parallel25.getTree());

            		    }
            		case _ =>
            		    guard = false //loop4;
                }
            } //while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "script"

      class parallel_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "parallel" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:71:1: parallel : bang ( BAR bang )* ;
    @throws(classOf[RecognitionException])
     final def parallel():/*CREOLE_XParser.*/parallel_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/parallel_return  = new /*CREOLE_XParser.*/parallel_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var BAR27: Token =null
        var bang26:/*CREOLE_XParser.*/bang_return = null

        var bang28:/*CREOLE_XParser.*/bang_return = null


        var BAR27_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:72:5: ( bang ( BAR bang )* )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:72:9: bang ( BAR bang )*
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            pushFollow(FOLLOW_bang_in_parallel439);
            bang26=bang();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bang26.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:72:14: ( BAR bang )*
            //loop5:
            var guard = true
            while(guard) {
                var alt5=2;
                var LA5_0 = input.LA(1);

                if ( (LA5_0==BAR) ) {
                    alt5=1;
                }


                alt5 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:72:15: BAR bang
            		    {
            		    BAR27=smatch(input,BAR,FOLLOW_BAR_in_parallel442).asInstanceOf[Token]; if (state.failed) return retval;
            		    if ( state.backtracking==0 ) {
            		    BAR27_tree = adaptor.create(BAR27).asInstanceOf[CHRTree];
            		    root_0 = adaptor.becomeRoot(BAR27_tree, root_0).asInstanceOf[CHRTree]
            		    }
            		    pushFollow(FOLLOW_bang_in_parallel445);
            		    bang28=bang();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) adaptor.addChild(root_0, bang28.getTree());

            		    }
            		case _ =>
            		    guard = false //loop5;
                }
            } //while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "parallel"

      class bang_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "bang" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:75:1: bang : ( simple_script | BANG simple_script -> ^( BANG simple_script ) );
    @throws(classOf[RecognitionException])
     final def bang():/*CREOLE_XParser.*/bang_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/bang_return  = new /*CREOLE_XParser.*/bang_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var BANG30: Token =null
        var simple_script29:/*CREOLE_XParser.*/simple_script_return = null

        var simple_script31:/*CREOLE_XParser.*/simple_script_return = null


        var BANG30_tree:CHRTree=null
        var stream_BANG:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token BANG")
        var stream_simple_script:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule simple_script");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:76:5: ( simple_script | BANG simple_script -> ^( BANG simple_script ) )
            var alt6=2;
            var LA6_0 = input.LA(1);

            if ( (LA6_0==LPAREN||LA6_0==R_ID||(LA6_0>=TRUE && LA6_0<=FALSE)||LA6_0==63) ) {
                alt6=1;
            }
            else if ( (LA6_0==BANG) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            alt6 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:76:9: simple_script
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_simple_script_in_bang466);
                    simple_script29=simple_script();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_script29.getTree());

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:77:9: BANG simple_script
                    {
                    BANG30=smatch(input,BANG,FOLLOW_BANG_in_bang476).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BANG.add(BANG30);

                    pushFollow(FOLLOW_simple_script_in_bang478);
                    simple_script31=simple_script();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_simple_script.add(simple_script31.getTree());


                    // AST REWRITE
                    // elements: simple_script, BANG
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 77:28: -> ^( BANG simple_script )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:77:31: ^( BANG simple_script )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(stream_BANG.nextNode(), root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_simple_script.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                case _ => //Do nothing
            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "bang"

      class simple_script_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "simple_script" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:80:1: simple_script : ( rule | LPAREN script RPAREN -> script );
    @throws(classOf[RecognitionException])
     final def simple_script():/*CREOLE_XParser.*/simple_script_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/simple_script_return  = new /*CREOLE_XParser.*/simple_script_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN33: Token =null
        var RPAREN35: Token =null
        var rule32:/*CREOLE_XParser.*/rule_return = null

        var script34:/*CREOLE_XParser.*/script_return = null


        var LPAREN33_tree:CHRTree=null
        var RPAREN35_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_script:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule script");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:81:5: ( rule | LPAREN script RPAREN -> script )
            var alt7=2;
            var LA7_0 = input.LA(1);

            if ( (LA7_0==R_ID||(LA7_0>=TRUE && LA7_0<=FALSE)||LA7_0==63) ) {
                alt7=1;
            }
            else if ( (LA7_0==LPAREN) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            alt7 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:81:9: rule
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_rule_in_simple_script506);
                    rule32=rule();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rule32.getTree());

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:82:9: LPAREN script RPAREN
                    {
                    LPAREN33=smatch(input,LPAREN,FOLLOW_LPAREN_in_simple_script516).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN33);

                    pushFollow(FOLLOW_script_in_simple_script518);
                    script34=script();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_script.add(script34.getTree());
                    RPAREN35=smatch(input,RPAREN,FOLLOW_RPAREN_in_simple_script520).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN35);



                    // AST REWRITE
                    // elements: script
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 82:31: -> script
                    {
                        adaptor.addChild(root_0, stream_script.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                case _ => //Do nothing
            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "simple_script"

      class rule_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "rule" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:101:1: rule : conjunction RARROW ( guard )? ( nu )? conjunction -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? ) ;
    @throws(classOf[RecognitionException])
     final def rule():/*CREOLE_XParser.*/rule_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/rule_return  = new /*CREOLE_XParser.*/rule_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var RARROW37: Token =null
        var conjunction36:/*CREOLE_XParser.*/conjunction_return = null

        var guard38:/*CREOLE_XParser.*/guard_return = null

        var nu39:/*CREOLE_XParser.*/nu_return = null

        var conjunction40:/*CREOLE_XParser.*/conjunction_return = null


        var RARROW37_tree:CHRTree=null
        var stream_RARROW:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RARROW")
        var stream_nu:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule nu");
        var stream_guard:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule guard");
        var stream_conjunction:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule conjunction");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:102:5: ( conjunction RARROW ( guard )? ( nu )? conjunction -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:102:9: conjunction RARROW ( guard )? ( nu )? conjunction
            {
            pushFollow(FOLLOW_conjunction_in_rule547);
            conjunction36=conjunction();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_conjunction.add(conjunction36.getTree());
            RARROW37=smatch(input,RARROW,FOLLOW_RARROW_in_rule549).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RARROW.add(RARROW37);

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:102:28: ( guard )?
            var alt8=2;
            var LA8_0 = input.LA(1);

            if ( (LA8_0==LBRACK||LA8_0==ABS) ) {
                alt8=1;
            }
            alt8 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:0:0: guard
                    {
                    pushFollow(FOLLOW_guard_in_rule551);
                    guard38=guard();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_guard.add(guard38.getTree());

                    }
                case _ => //Do nothing
            }

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:102:35: ( nu )?
            var alt9=2;
            var LA9_0 = input.LA(1);

            if ( (LA9_0==NU) ) {
                alt9=1;
            }
            alt9 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:0:0: nu
                    {
                    pushFollow(FOLLOW_nu_in_rule554);
                    nu39=nu();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_nu.add(nu39.getTree());

                    }
                case _ => //Do nothing
            }

            pushFollow(FOLLOW_conjunction_in_rule557);
            conjunction40=conjunction();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_conjunction.add(conjunction40.getTree());


            // AST REWRITE
            // elements: nu, conjunction, guard, conjunction
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 102:52: -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:103:13: ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(RULE, "RULE").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:103:20: ( guard )?
                if ( stream_guard.hasNext() ) {
                    adaptor.addChild(root_1, stream_guard.nextTree());

                }
                stream_guard.reset();
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:103:27: ^( HEAD conjunction )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(HEAD, "HEAD").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_conjunction.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:103:47: ^( BODY conjunction )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(BODY, "BODY").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_conjunction.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:103:67: ( nu )?
                if ( stream_nu.hasNext() ) {
                    adaptor.addChild(root_1, stream_nu.nextTree());

                }
                stream_nu.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "rule"

      class guard_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "guard" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:106:1: guard : ( LBRACK ( rule )* RBRACK IMARK -> ^( GUARD ( rule )* ) | ABS LBRACK conjunction RBRACK IMARK -> ^( GUARD ^( ABS conjunction ) ) );
    @throws(classOf[RecognitionException])
     final def guard():/*CREOLE_XParser.*/guard_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/guard_return  = new /*CREOLE_XParser.*/guard_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LBRACK41: Token =null
        var RBRACK43: Token =null
        var IMARK44: Token =null
        var ABS45: Token =null
        var LBRACK46: Token =null
        var RBRACK48: Token =null
        var IMARK49: Token =null
        var rule42:/*CREOLE_XParser.*/rule_return = null

        var conjunction47:/*CREOLE_XParser.*/conjunction_return = null


        var LBRACK41_tree:CHRTree=null
        var RBRACK43_tree:CHRTree=null
        var IMARK44_tree:CHRTree=null
        var ABS45_tree:CHRTree=null
        var LBRACK46_tree:CHRTree=null
        var RBRACK48_tree:CHRTree=null
        var IMARK49_tree:CHRTree=null
        var stream_ABS:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ABS")
        var stream_RBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RBRACK")
        var stream_LBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LBRACK")
        var stream_IMARK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token IMARK")
        var stream_rule:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rule");
        var stream_conjunction:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule conjunction");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:107:5: ( LBRACK ( rule )* RBRACK IMARK -> ^( GUARD ( rule )* ) | ABS LBRACK conjunction RBRACK IMARK -> ^( GUARD ^( ABS conjunction ) ) )
            var alt11=2;
            var LA11_0 = input.LA(1);

            if ( (LA11_0==LBRACK) ) {
                alt11=1;
            }
            else if ( (LA11_0==ABS) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            alt11 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:107:9: LBRACK ( rule )* RBRACK IMARK
                    {
                    LBRACK41=smatch(input,LBRACK,FOLLOW_LBRACK_in_guard613).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK41);

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:107:16: ( rule )*
                    //loop10:
                    var guard = true
                    while(guard) {
                        var alt10=2;
                        var LA10_0 = input.LA(1);

                        if ( (LA10_0==R_ID||(LA10_0>=TRUE && LA10_0<=FALSE)||LA10_0==63) ) {
                            alt10=1;
                        }


                        alt10 match{
                    		case 1 =>
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:0:0: rule
                    		    {
                    		    pushFollow(FOLLOW_rule_in_guard615);
                    		    rule42=rule();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_rule.add(rule42.getTree());

                    		    }
                    		case _ =>
                    		    guard = false //loop10;
                        }
                    } //while (true);

                    RBRACK43=smatch(input,RBRACK,FOLLOW_RBRACK_in_guard618).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK43);

                    IMARK44=smatch(input,IMARK,FOLLOW_IMARK_in_guard620).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IMARK.add(IMARK44);



                    // AST REWRITE
                    // elements: rule
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 107:35: -> ^( GUARD ( rule )* )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:107:38: ^( GUARD ( rule )* )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(GUARD, "GUARD").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:107:46: ( rule )*
                        while ( stream_rule.hasNext() ) {
                            adaptor.addChild(root_1, stream_rule.nextTree());

                        }
                        stream_rule.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:108:9: ABS LBRACK conjunction RBRACK IMARK
                    {
                    ABS45=smatch(input,ABS,FOLLOW_ABS_in_guard639).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ABS.add(ABS45);

                    LBRACK46=smatch(input,LBRACK,FOLLOW_LBRACK_in_guard641).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK46);

                    pushFollow(FOLLOW_conjunction_in_guard643);
                    conjunction47=conjunction();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_conjunction.add(conjunction47.getTree());
                    RBRACK48=smatch(input,RBRACK,FOLLOW_RBRACK_in_guard645).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK48);

                    IMARK49=smatch(input,IMARK,FOLLOW_IMARK_in_guard647).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IMARK.add(IMARK49);



                    // AST REWRITE
                    // elements: ABS, conjunction
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 108:45: -> ^( GUARD ^( ABS conjunction ) )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:108:48: ^( GUARD ^( ABS conjunction ) )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(GUARD, "GUARD").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:108:56: ^( ABS conjunction )
                        {
                        var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_2 = adaptor.becomeRoot(stream_ABS.nextNode(), root_2).asInstanceOf[CHRTree]

                        adaptor.addChild(root_2, stream_conjunction.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                case _ => //Do nothing
            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "guard"

      class nu_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "nu" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:111:1: nu : NU LPAREN variable ( COMMA variable )* RPAREN -> ^( NU ( variable )* ) ;
    @throws(classOf[RecognitionException])
     final def nu():/*CREOLE_XParser.*/nu_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/nu_return  = new /*CREOLE_XParser.*/nu_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var NU50: Token =null
        var LPAREN51: Token =null
        var COMMA53: Token =null
        var RPAREN55: Token =null
        var variable52:/*CREOLE_XParser.*/variable_return = null

        var variable54:/*CREOLE_XParser.*/variable_return = null


        var NU50_tree:CHRTree=null
        var LPAREN51_tree:CHRTree=null
        var COMMA53_tree:CHRTree=null
        var RPAREN55_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_NU:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token NU")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_variable:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule variable");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:112:5: ( NU LPAREN variable ( COMMA variable )* RPAREN -> ^( NU ( variable )* ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:112:9: NU LPAREN variable ( COMMA variable )* RPAREN
            {
            NU50=smatch(input,NU,FOLLOW_NU_in_nu678).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NU.add(NU50);

            LPAREN51=smatch(input,LPAREN,FOLLOW_LPAREN_in_nu680).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN51);

            pushFollow(FOLLOW_variable_in_nu682);
            variable52=variable();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variable.add(variable52.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:112:28: ( COMMA variable )*
            //loop12:
            var guard = true
            while(guard) {
                var alt12=2;
                var LA12_0 = input.LA(1);

                if ( (LA12_0==COMMA) ) {
                    alt12=1;
                }


                alt12 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:112:29: COMMA variable
            		    {
            		    COMMA53=smatch(input,COMMA,FOLLOW_COMMA_in_nu685).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA53);

            		    pushFollow(FOLLOW_variable_in_nu687);
            		    variable54=variable();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_variable.add(variable54.getTree());

            		    }
            		case _ =>
            		    guard = false //loop12;
                }
            } //while (true);

            RPAREN55=smatch(input,RPAREN,FOLLOW_RPAREN_in_nu691).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN55);



            // AST REWRITE
            // elements: NU, variable
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 112:53: -> ^( NU ( variable )* )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:112:56: ^( NU ( variable )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(stream_NU.nextNode(), root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:112:61: ( variable )*
                while ( stream_variable.hasNext() ) {
                    adaptor.addChild(root_1, stream_variable.nextTree());

                }
                stream_variable.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "nu"

      class conjunction_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "conjunction" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:115:1: conjunction : ( 'T' -> EMPTY | atom ( COMMA atom )* -> ( atom )* );
    @throws(classOf[RecognitionException])
     final def conjunction():/*CREOLE_XParser.*/conjunction_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/conjunction_return  = new /*CREOLE_XParser.*/conjunction_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var char_literal56: Token =null
        var COMMA58: Token =null
        var atom57:/*CREOLE_XParser.*/atom_return = null

        var atom59:/*CREOLE_XParser.*/atom_return = null


        var char_literal56_tree:CHRTree=null
        var COMMA58_tree:CHRTree=null
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_63:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 63")
        var stream_atom:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule atom");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:116:5: ( 'T' -> EMPTY | atom ( COMMA atom )* -> ( atom )* )
            var alt14=2;
            var LA14_0 = input.LA(1);

            if ( (LA14_0==63) ) {
                alt14=1;
            }
            else if ( (LA14_0==R_ID||(LA14_0>=TRUE && LA14_0<=FALSE)) ) {
                alt14=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            alt14 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:116:9: 'T'
                    {
                    char_literal56=smatch(input,63,FOLLOW_63_in_conjunction719).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_63.add(char_literal56);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 116:13: -> EMPTY
                    {
                        adaptor.addChild(root_0, adaptor.create(EMPTY, "EMPTY").asInstanceOf[CHRTree]);

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:117:9: atom ( COMMA atom )*
                    {
                    pushFollow(FOLLOW_atom_in_conjunction733);
                    atom57=atom();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom57.getTree());
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:117:14: ( COMMA atom )*
                    //loop13:
                    var guard = true
                    while(guard) {
                        var alt13=2;
                        var LA13_0 = input.LA(1);

                        if ( (LA13_0==COMMA) ) {
                            alt13=1;
                        }


                        alt13 match{
                    		case 1 =>
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:117:15: COMMA atom
                    		    {
                    		    COMMA58=smatch(input,COMMA,FOLLOW_COMMA_in_conjunction736).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA58);

                    		    pushFollow(FOLLOW_atom_in_conjunction738);
                    		    atom59=atom();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_atom.add(atom59.getTree());

                    		    }
                    		case _ =>
                    		    guard = false //loop13;
                        }
                    } //while (true);



                    // AST REWRITE
                    // elements: atom
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 117:28: -> ( atom )*
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:117:31: ( atom )*
                        while ( stream_atom.hasNext() ) {
                            adaptor.addChild(root_0, stream_atom.nextTree());

                        }
                        stream_atom.reset();

                    }

                    retval.tree = root_0;}
                    }
                case _ => //Do nothing
            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "conjunction"

      class atom_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "atom" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:120:1: atom : ( TRUE | FALSE | relation ( termlist )? -> ^( ATOM relation ( termlist )? ) );
    @throws(classOf[RecognitionException])
     final def atom():/*CREOLE_XParser.*/atom_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/atom_return  = new /*CREOLE_XParser.*/atom_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var TRUE60: Token =null
        var FALSE61: Token =null
        var relation62:/*CREOLE_XParser.*/relation_return = null

        var termlist63:/*CREOLE_XParser.*/termlist_return = null


        var TRUE60_tree:CHRTree=null
        var FALSE61_tree:CHRTree=null
        var stream_termlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule termlist");
        var stream_relation:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule relation");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:121:5: ( TRUE | FALSE | relation ( termlist )? -> ^( ATOM relation ( termlist )? ) )
            var alt16=3;
            input.LA(1) match{
            	case TRUE => alt16=1;    
            	case FALSE => alt16=2;    
            	case R_ID => alt16=3;    
            	case _ =>
            		    if (state.backtracking>0) {state.failed=true; return retval;}
            		    val nvae =
            		        new NoViableAltException("", 16, 0, input);

            		    throw nvae;

            }

            alt16 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:121:9: TRUE
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    TRUE60=smatch(input,TRUE,FOLLOW_TRUE_in_atom764).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TRUE60_tree = adaptor.create(TRUE60).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, TRUE60_tree);
                    }

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:122:9: FALSE
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    FALSE61=smatch(input,FALSE,FOLLOW_FALSE_in_atom774).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FALSE61_tree = adaptor.create(FALSE61).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, FALSE61_tree);
                    }

                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:123:9: relation ( termlist )?
                    {
                    pushFollow(FOLLOW_relation_in_atom784);
                    relation62=relation();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_relation.add(relation62.getTree());
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:123:18: ( termlist )?
                    var alt15=2;
                    var LA15_0 = input.LA(1);

                    if ( (LA15_0==LPAREN) ) {
                        alt15=1;
                    }
                    alt15 match{
                        case 1 =>
                            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:0:0: termlist
                            {
                            pushFollow(FOLLOW_termlist_in_atom786);
                            termlist63=termlist();

                            state._fsp -= 1 
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_termlist.add(termlist63.getTree());

                            }
                        case _ => //Do nothing
                    }



                    // AST REWRITE
                    // elements: relation, termlist
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 123:29: -> ^( ATOM relation ( termlist )? )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:124:13: ^( ATOM relation ( termlist )? )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(ATOM, "ATOM").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_relation.nextTree());
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:124:29: ( termlist )?
                        if ( stream_termlist.hasNext() ) {
                            adaptor.addChild(root_1, stream_termlist.nextTree());

                        }
                        stream_termlist.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                case _ => //Do nothing
            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "atom"

      class termlist_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "termlist" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:127:1: termlist : LPAREN term ( COMMA term )* RPAREN -> ^( VARS ( term )* ) ;
    @throws(classOf[RecognitionException])
     final def termlist():/*CREOLE_XParser.*/termlist_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/termlist_return  = new /*CREOLE_XParser.*/termlist_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN64: Token =null
        var COMMA66: Token =null
        var RPAREN68: Token =null
        var term65:/*CREOLE_XParser.*/term_return = null

        var term67:/*CREOLE_XParser.*/term_return = null


        var LPAREN64_tree:CHRTree=null
        var COMMA66_tree:CHRTree=null
        var RPAREN68_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_term:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule term");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:128:5: ( LPAREN term ( COMMA term )* RPAREN -> ^( VARS ( term )* ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:128:9: LPAREN term ( COMMA term )* RPAREN
            {
            LPAREN64=smatch(input,LPAREN,FOLLOW_LPAREN_in_termlist830).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN64);

            pushFollow(FOLLOW_term_in_termlist832);
            term65=term();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_term.add(term65.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:128:21: ( COMMA term )*
            //loop17:
            var guard = true
            while(guard) {
                var alt17=2;
                var LA17_0 = input.LA(1);

                if ( (LA17_0==COMMA) ) {
                    alt17=1;
                }


                alt17 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:128:22: COMMA term
            		    {
            		    COMMA66=smatch(input,COMMA,FOLLOW_COMMA_in_termlist835).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA66);

            		    pushFollow(FOLLOW_term_in_termlist837);
            		    term67=term();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_term.add(term67.getTree());

            		    }
            		case _ =>
            		    guard = false //loop17;
                }
            } //while (true);

            RPAREN68=smatch(input,RPAREN,FOLLOW_RPAREN_in_termlist841).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN68);



            // AST REWRITE
            // elements: term
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 128:42: -> ^( VARS ( term )* )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:128:47: ^( VARS ( term )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(VARS, "VARS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:128:54: ( term )*
                while ( stream_term.hasNext() ) {
                    adaptor.addChild(root_1, stream_term.nextTree());

                }
                stream_term.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "termlist"

      class term_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "term" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:131:1: term : ( constant | variable );
    @throws(classOf[RecognitionException])
     final def term():/*CREOLE_XParser.*/term_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/term_return  = new /*CREOLE_XParser.*/term_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var constant69:/*CREOLE_XParser.*/constant_return = null

        var variable70:/*CREOLE_XParser.*/variable_return = null



        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:132:2: ( constant | variable )
            var alt18=2;
            var LA18_0 = input.LA(1);

            if ( ((LA18_0>=INT && LA18_0<=STRING)) ) {
                alt18=1;
            }
            else if ( (LA18_0==R_ID||(LA18_0>=NULL && LA18_0<=V_ID)) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            alt18 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:132:4: constant
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_constant_in_term866);
                    constant69=constant();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constant69.getTree());

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:133:4: variable
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_variable_in_term871);
                    variable70=variable();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variable70.getTree());

                    }
                case _ => //Do nothing
            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "term"

      class relation_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "relation" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:136:1: relation : R_ID ;
    @throws(classOf[RecognitionException])
     final def relation():/*CREOLE_XParser.*/relation_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/relation_return  = new /*CREOLE_XParser.*/relation_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var R_ID71: Token =null

        var R_ID71_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:137:5: ( R_ID )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:137:9: R_ID
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            R_ID71=smatch(input,R_ID,FOLLOW_R_ID_in_relation887).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            R_ID71_tree = adaptor.create(R_ID71).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, R_ID71_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "relation"

      class variable_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "variable" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:140:1: variable : ( NULL | V_ID | R_ID );
    @throws(classOf[RecognitionException])
     final def variable():/*CREOLE_XParser.*/variable_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/variable_return  = new /*CREOLE_XParser.*/variable_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var set72: Token =null

        var set72_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:141:5: ( NULL | V_ID | R_ID )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            set72=input.LT(1).asInstanceOf[Token]
            if ( input.LA(1)==R_ID||(input.LA(1)>=NULL && input.LA(1)<=V_ID) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, adaptor.create(set72).asInstanceOf[CHRTree]);
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "variable"

      class constant_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "constant" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:144:1: constant : ( INT | STRING );
    @throws(classOf[RecognitionException])
     final def constant():/*CREOLE_XParser.*/constant_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/constant_return  = new /*CREOLE_XParser.*/constant_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var set73: Token =null

        var set73_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:145:2: ( INT | STRING )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            set73=input.LT(1).asInstanceOf[Token]
            if ( (input.LA(1)>=INT && input.LA(1)<=STRING) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, adaptor.create(set73).asInstanceOf[CHRTree]);
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "constant"

      class url_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "url" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:149:1: url : STRING ;
    @throws(classOf[RecognitionException])
     final def url():/*CREOLE_XParser.*/url_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/url_return  = new /*CREOLE_XParser.*/url_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var STRING74: Token =null

        var STRING74_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:149:5: ( STRING )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:149:7: STRING
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            STRING74=smatch(input,STRING,FOLLOW_STRING_in_url943).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRING74_tree = adaptor.create(STRING74).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, STRING74_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[CHRTree]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[CHRTree]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "url"

    // Delegated rules


 

      final val FOLLOW_process_in_start185:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_declaration_in_process204:BitSet = new BitSet( Array[Long](0x800000C088200000L))
      //new long[]{0x800000C088200000L});
      final val FOLLOW_script_in_process206:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_declaration239:BitSet = new BitSet( Array[Long](0x2000000000000000L))
      //new long[]{0x2000000000000000L});
      final val FOLLOW_61_in_declaration241:BitSet = new BitSet( Array[Long](0x0000000000400000L))
      //new long[]{0x0000000000400000L});
      final val FOLLOW_COLON_in_declaration243:BitSet = new BitSet( Array[Long](0x000000002C000000L))
      //new long[]{0x000000002C000000L});
      final val FOLLOW_rlist_in_declaration245:BitSet = new BitSet( Array[Long](0x0000000000800000L))
      //new long[]{0x0000000000800000L});
      final val FOLLOW_SEMI_in_declaration247:BitSet = new BitSet( Array[Long](0x4000000000000000L))
      //new long[]{0x4000000000000000L});
      final val FOLLOW_62_in_declaration249:BitSet = new BitSet( Array[Long](0x0000000000400000L))
      //new long[]{0x0000000000400000L});
      final val FOLLOW_COLON_in_declaration251:BitSet = new BitSet( Array[Long](0x000000002C000000L))
      //new long[]{0x000000002C000000L});
      final val FOLLOW_rlist_in_declaration253:BitSet = new BitSet( Array[Long](0x0000000001000000L))
      //new long[]{0x0000000001000000L});
      final val FOLLOW_RPAREN_in_declaration255:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_rdeclaration_in_rlist304:BitSet = new BitSet( Array[Long](0x0000000002000002L))
      //new long[]{0x0000000002000002L});
      final val FOLLOW_COMMA_in_rlist307:BitSet = new BitSet( Array[Long](0x0000000028000000L))
      //new long[]{0x0000000028000000L});
      final val FOLLOW_rdeclaration_in_rlist309:BitSet = new BitSet( Array[Long](0x0000000002000002L))
      //new long[]{0x0000000002000002L});
      final val FOLLOW_UNDEF_in_rlist326:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_rdeclaration349:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_rdeclaration359:BitSet = new BitSet( Array[Long](0x0000000010000000L))
      //new long[]{0x0000000010000000L});
      final val FOLLOW_ARROBAS_in_rdeclaration361:BitSet = new BitSet( Array[Long](0x0000080000000000L))
      //new long[]{0x0000080000000000L});
      final val FOLLOW_url_in_rdeclaration363:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_TILDE_in_rdeclaration383:BitSet = new BitSet( Array[Long](0x0000000008000000L))
      //new long[]{0x0000000008000000L});
      final val FOLLOW_R_ID_in_rdeclaration385:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_parallel_in_script412:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_SEMI_in_script415:BitSet = new BitSet( Array[Long](0x800000C088200000L))
      //new long[]{0x800000C088200000L});
      final val FOLLOW_parallel_in_script418:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_bang_in_parallel439:BitSet = new BitSet( Array[Long](0x0000000040000002L))
      //new long[]{0x0000000040000002L});
      final val FOLLOW_BAR_in_parallel442:BitSet = new BitSet( Array[Long](0x800000C088200000L))
      //new long[]{0x800000C088200000L});
      final val FOLLOW_bang_in_parallel445:BitSet = new BitSet( Array[Long](0x0000000040000002L))
      //new long[]{0x0000000040000002L});
      final val FOLLOW_simple_script_in_bang466:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_BANG_in_bang476:BitSet = new BitSet( Array[Long](0x800000C008200000L))
      //new long[]{0x800000C008200000L});
      final val FOLLOW_simple_script_in_bang478:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_rule_in_simple_script506:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_simple_script516:BitSet = new BitSet( Array[Long](0x800000C088200000L))
      //new long[]{0x800000C088200000L});
      final val FOLLOW_script_in_simple_script518:BitSet = new BitSet( Array[Long](0x0000000001000000L))
      //new long[]{0x0000000001000000L});
      final val FOLLOW_RPAREN_in_simple_script520:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_conjunction_in_rule547:BitSet = new BitSet( Array[Long](0x0000000100000000L))
      //new long[]{0x0000000100000000L});
      final val FOLLOW_RARROW_in_rule549:BitSet = new BitSet( Array[Long](0x800000F208000000L))
      //new long[]{0x800000F208000000L});
      final val FOLLOW_guard_in_rule551:BitSet = new BitSet( Array[Long](0x800000E008000000L))
      //new long[]{0x800000E008000000L});
      final val FOLLOW_nu_in_rule554:BitSet = new BitSet( Array[Long](0x800000C008000000L))
      //new long[]{0x800000C008000000L});
      final val FOLLOW_conjunction_in_rule557:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LBRACK_in_guard613:BitSet = new BitSet( Array[Long](0x800000C408000000L))
      //new long[]{0x800000C408000000L});
      final val FOLLOW_rule_in_guard615:BitSet = new BitSet( Array[Long](0x800000C408000000L))
      //new long[]{0x800000C408000000L});
      final val FOLLOW_RBRACK_in_guard618:BitSet = new BitSet( Array[Long](0x0000000800000000L))
      //new long[]{0x0000000800000000L});
      final val FOLLOW_IMARK_in_guard620:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ABS_in_guard639:BitSet = new BitSet( Array[Long](0x0000000200000000L))
      //new long[]{0x0000000200000000L});
      final val FOLLOW_LBRACK_in_guard641:BitSet = new BitSet( Array[Long](0x800000C008000000L))
      //new long[]{0x800000C008000000L});
      final val FOLLOW_conjunction_in_guard643:BitSet = new BitSet( Array[Long](0x0000000400000000L))
      //new long[]{0x0000000400000000L});
      final val FOLLOW_RBRACK_in_guard645:BitSet = new BitSet( Array[Long](0x0000000800000000L))
      //new long[]{0x0000000800000000L});
      final val FOLLOW_IMARK_in_guard647:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_NU_in_nu678:BitSet = new BitSet( Array[Long](0x0000000000200000L))
      //new long[]{0x0000000000200000L});
      final val FOLLOW_LPAREN_in_nu680:BitSet = new BitSet( Array[Long](0x0000030008000000L))
      //new long[]{0x0000030008000000L});
      final val FOLLOW_variable_in_nu682:BitSet = new BitSet( Array[Long](0x0000000003000000L))
      //new long[]{0x0000000003000000L});
      final val FOLLOW_COMMA_in_nu685:BitSet = new BitSet( Array[Long](0x0000030008000000L))
      //new long[]{0x0000030008000000L});
      final val FOLLOW_variable_in_nu687:BitSet = new BitSet( Array[Long](0x0000000003000000L))
      //new long[]{0x0000000003000000L});
      final val FOLLOW_RPAREN_in_nu691:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_63_in_conjunction719:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_atom_in_conjunction733:BitSet = new BitSet( Array[Long](0x0000000002000002L))
      //new long[]{0x0000000002000002L});
      final val FOLLOW_COMMA_in_conjunction736:BitSet = new BitSet( Array[Long](0x800000C008000000L))
      //new long[]{0x800000C008000000L});
      final val FOLLOW_atom_in_conjunction738:BitSet = new BitSet( Array[Long](0x0000000002000002L))
      //new long[]{0x0000000002000002L});
      final val FOLLOW_TRUE_in_atom764:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_FALSE_in_atom774:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_relation_in_atom784:BitSet = new BitSet( Array[Long](0x0000000000200002L))
      //new long[]{0x0000000000200002L});
      final val FOLLOW_termlist_in_atom786:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_termlist830:BitSet = new BitSet( Array[Long](0x00000F0008000000L))
      //new long[]{0x00000F0008000000L});
      final val FOLLOW_term_in_termlist832:BitSet = new BitSet( Array[Long](0x0000000003000000L))
      //new long[]{0x0000000003000000L});
      final val FOLLOW_COMMA_in_termlist835:BitSet = new BitSet( Array[Long](0x00000F0008000000L))
      //new long[]{0x00000F0008000000L});
      final val FOLLOW_term_in_termlist837:BitSet = new BitSet( Array[Long](0x0000000003000000L))
      //new long[]{0x0000000003000000L});
      final val FOLLOW_RPAREN_in_termlist841:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_constant_in_term866:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_variable_in_term871:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_relation887:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_set_in_variable0:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_set_in_constant0:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_STRING_in_url943:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});

}