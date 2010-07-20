// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g 2010-07-11 13:36:56

package fr.emn.creole.parser;

import fr.emn.creole.parser.tree._


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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SCRIPT", "ATOM", "VARS", "RULE", "HEAD", "BODY", "MULTI", "DECLARATION", "EMPTYLIST", "PUBLIC", "PRIVATE", "PROCESS", "GUARD", "EMPTY", "INT_ATOM", "LPAREN", "COLON", "SEMI", "RPAREN", "COMMA", "UNDEF", "R_ID", "TILDE", "BAR", "BANG", "RARROW", "LBRACK", "RBRACK", "IMARK", "ABS", "NU", "TRUE", "FALSE", "V_ID", "INT", "STRING", "EQ_OP", "LT", "LTEQ", "PLUS", "MINUS", "LCURL", "RCURL", "POINT", "SLASH", "COMMENT", "WS", "ESC_SEQ", "CHAR", "EXPONENT", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "'public'", "'private'", "'T'"
    )
      val EXPONENT:Int = 53;
      val LT:Int = 41;
      val LBRACK:Int = 30;
      val EQ_OP:Int = 40;
      val POINT:Int = 47;
      val HEAD:Int = 8;
      val OCTAL_ESC:Int = 56;
      val LTEQ:Int = 42;
      val CHAR:Int = 52;
      val MULTI:Int = 10;
      val ATOM:Int = 5;
      val EOF:Int = -1;
      val DECLARATION:Int = 11;
      val LPAREN:Int = 19;
      val INT_ATOM:Int = 18;
      val T__57:Int = 57;
      val RPAREN:Int = 22;
      val T__58:Int = 58;
      val ESC_SEQ:Int = 51;
      val SLASH:Int = 48;
      val COMMA:Int = 23;
      val T__59:Int = 59;
      val TILDE:Int = 26;
      val PLUS:Int = 43;
      val BODY:Int = 9;
      val UNDEF:Int = 24;
      val COMMENT:Int = 49;
      val IMARK:Int = 32;
      val RBRACK:Int = 31;
      val SCRIPT:Int = 4;
      val RULE:Int = 7;
      val NU:Int = 34;
      val R_ID:Int = 25;
      val PRIVATE:Int = 14;
      val VARS:Int = 6;
      val UNICODE_ESC:Int = 55;
      val RARROW:Int = 29;
      val HEX_DIGIT:Int = 54;
      val V_ID:Int = 37;
      val BANG:Int = 28;
      val INT:Int = 38;
      val MINUS:Int = 44;
      val SEMI:Int = 21;
      val TRUE:Int = 35;
      val EMPTY:Int = 17;
      val COLON:Int = 20;
      val ABS:Int = 33;
      val LCURL:Int = 45;
      val WS:Int = 50;
      val RCURL:Int = 46;
      val EMPTYLIST:Int = 12;
      val GUARD:Int = 16;
      val PROCESS:Int = 15;
      val FALSE:Int = 36;
      val PUBLIC:Int = 13;
      val BAR:Int = 27;
      val STRING:Int = 39;

    // delegates
    // delegators


        
    protected var adaptor:TreeAdaptor = new CommonTreeAdaptor()

    def setTreeAdaptor(adaptor:TreeAdaptor) {
        this.adaptor = adaptor
    }
    def  getTreeAdaptor():TreeAdaptor = adaptor

    override def getTokenNames: Array[String] = /*CREOLE_XParser.*/tokenNames 
    override def getGrammarFileName(): String = "src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g"


    	def getCHRTreeTokens = new CHRTreeTokens(this.tokenNames)


      class start_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "start" rule(...)
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:41:1: start : process ;
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:42:5: ( process )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:42:9: process
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            pushFollow(FOLLOW_process_in_start171);
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:45:1: process : declaration script -> ^( PROCESS declaration ^( SCRIPT script ) ) ;
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:46:5: ( declaration script -> ^( PROCESS declaration ^( SCRIPT script ) ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:46:9: declaration script
            {
            pushFollow(FOLLOW_declaration_in_process190);
            declaration2=declaration();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declaration.add(declaration2.getTree());
            pushFollow(FOLLOW_script_in_process192);
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
            // 46:28: -> ^( PROCESS declaration ^( SCRIPT script ) )
            {
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:46:31: ^( PROCESS declaration ^( SCRIPT script ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(PROCESS, "PROCESS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                adaptor.addChild(root_1, stream_declaration.nextTree());
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:46:53: ^( SCRIPT script )
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:49:1: declaration : LPAREN 'public' COLON rlist SEMI 'private' COLON rlist RPAREN -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) ) ;
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
        var stream_58:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 58")
        var stream_57:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 57")
        var stream_SEMI:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SEMI")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_rlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rlist");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:50:5: ( LPAREN 'public' COLON rlist SEMI 'private' COLON rlist RPAREN -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:50:9: LPAREN 'public' COLON rlist SEMI 'private' COLON rlist RPAREN
            {
            LPAREN4=smatch(input,LPAREN,FOLLOW_LPAREN_in_declaration225).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN4);

            string_literal5=smatch(input,57,FOLLOW_57_in_declaration227).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_57.add(string_literal5);

            COLON6=smatch(input,COLON,FOLLOW_COLON_in_declaration229).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON6);

            pushFollow(FOLLOW_rlist_in_declaration231);
            rlist7=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist7.getTree());
            SEMI8=smatch(input,SEMI,FOLLOW_SEMI_in_declaration233).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI8);

            string_literal9=smatch(input,58,FOLLOW_58_in_declaration235).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_58.add(string_literal9);

            COLON10=smatch(input,COLON,FOLLOW_COLON_in_declaration237).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON10);

            pushFollow(FOLLOW_rlist_in_declaration239);
            rlist11=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist11.getTree());
            RPAREN12=smatch(input,RPAREN,FOLLOW_RPAREN_in_declaration241).asInstanceOf[Token]; if (state.failed) return retval; 
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
            // 50:71: -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) )
            {
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:51:13: ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(DECLARATION, "DECLARATION").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:51:27: ^( PUBLIC rlist )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(PUBLIC, "PUBLIC").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_rlist.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:51:43: ^( PRIVATE rlist )
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:54:1: rlist : ( rdeclaration ( COMMA rdeclaration )* -> ( rdeclaration )* | UNDEF -> EMPTYLIST );
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:55:5: ( rdeclaration ( COMMA rdeclaration )* -> ( rdeclaration )* | UNDEF -> EMPTYLIST )
            var alt2=2;
            var LA2_0 = input.LA(1);

            if ( ((LA2_0>=R_ID && LA2_0<=TILDE)) ) {
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:55:9: rdeclaration ( COMMA rdeclaration )*
                    {
                    pushFollow(FOLLOW_rdeclaration_in_rlist290);
                    rdeclaration13=rdeclaration();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rdeclaration.add(rdeclaration13.getTree());
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:55:22: ( COMMA rdeclaration )*
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
                    		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:55:23: COMMA rdeclaration
                    		    {
                    		    COMMA14=smatch(input,COMMA,FOLLOW_COMMA_in_rlist293).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA14);

                    		    pushFollow(FOLLOW_rdeclaration_in_rlist295);
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
                    // 55:44: -> ( rdeclaration )*
                    {
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:55:47: ( rdeclaration )*
                        while ( stream_rdeclaration.hasNext() ) {
                            adaptor.addChild(root_0, stream_rdeclaration.nextTree());

                        }
                        stream_rdeclaration.reset();

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:56:9: UNDEF
                    {
                    UNDEF16=smatch(input,UNDEF,FOLLOW_UNDEF_in_rlist312).asInstanceOf[Token]; if (state.failed) return retval; 
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
                    // 56:15: -> EMPTYLIST
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:59:1: rdeclaration : ( R_ID | TILDE R_ID -> ^( MULTI R_ID ) );
    @throws(classOf[RecognitionException])
     final def rdeclaration():/*CREOLE_XParser.*/rdeclaration_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/rdeclaration_return  = new /*CREOLE_XParser.*/rdeclaration_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var R_ID17: Token =null
        var TILDE18: Token =null
        var R_ID19: Token =null

        var R_ID17_tree:CHRTree=null
        var TILDE18_tree:CHRTree=null
        var R_ID19_tree:CHRTree=null
        var stream_R_ID:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token R_ID")
        var stream_TILDE:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token TILDE")

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:60:5: ( R_ID | TILDE R_ID -> ^( MULTI R_ID ) )
            var alt3=2;
            var LA3_0 = input.LA(1);

            if ( (LA3_0==R_ID) ) {
                alt3=1;
            }
            else if ( (LA3_0==TILDE) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            alt3 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:60:9: R_ID
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    R_ID17=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration335).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    R_ID17_tree = adaptor.create(R_ID17).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, R_ID17_tree);
                    }

                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:61:9: TILDE R_ID
                    {
                    TILDE18=smatch(input,TILDE,FOLLOW_TILDE_in_rdeclaration345).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TILDE.add(TILDE18);

                    R_ID19=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration347).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_R_ID.add(R_ID19);



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
                    // 61:20: -> ^( MULTI R_ID )
                    {
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:61:23: ^( MULTI R_ID )
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:64:1: script : parallel ( SEMI parallel )* ;
    @throws(classOf[RecognitionException])
     final def script():/*CREOLE_XParser.*/script_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/script_return  = new /*CREOLE_XParser.*/script_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var SEMI21: Token =null
        var parallel20:/*CREOLE_XParser.*/parallel_return = null

        var parallel22:/*CREOLE_XParser.*/parallel_return = null


        var SEMI21_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:65:5: ( parallel ( SEMI parallel )* )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:65:9: parallel ( SEMI parallel )*
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            pushFollow(FOLLOW_parallel_in_script374);
            parallel20=parallel();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, parallel20.getTree());
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:65:18: ( SEMI parallel )*
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
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:65:19: SEMI parallel
            		    {
            		    SEMI21=smatch(input,SEMI,FOLLOW_SEMI_in_script377).asInstanceOf[Token]; if (state.failed) return retval;
            		    if ( state.backtracking==0 ) {
            		    SEMI21_tree = adaptor.create(SEMI21).asInstanceOf[CHRTree];
            		    root_0 = adaptor.becomeRoot(SEMI21_tree, root_0).asInstanceOf[CHRTree]
            		    }
            		    pushFollow(FOLLOW_parallel_in_script380);
            		    parallel22=parallel();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) adaptor.addChild(root_0, parallel22.getTree());

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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:68:1: parallel : bang ( BAR bang )* ;
    @throws(classOf[RecognitionException])
     final def parallel():/*CREOLE_XParser.*/parallel_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/parallel_return  = new /*CREOLE_XParser.*/parallel_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var BAR24: Token =null
        var bang23:/*CREOLE_XParser.*/bang_return = null

        var bang25:/*CREOLE_XParser.*/bang_return = null


        var BAR24_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:69:5: ( bang ( BAR bang )* )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:69:9: bang ( BAR bang )*
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            pushFollow(FOLLOW_bang_in_parallel401);
            bang23=bang();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bang23.getTree());
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:69:14: ( BAR bang )*
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
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:69:15: BAR bang
            		    {
            		    BAR24=smatch(input,BAR,FOLLOW_BAR_in_parallel404).asInstanceOf[Token]; if (state.failed) return retval;
            		    if ( state.backtracking==0 ) {
            		    BAR24_tree = adaptor.create(BAR24).asInstanceOf[CHRTree];
            		    root_0 = adaptor.becomeRoot(BAR24_tree, root_0).asInstanceOf[CHRTree]
            		    }
            		    pushFollow(FOLLOW_bang_in_parallel407);
            		    bang25=bang();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) adaptor.addChild(root_0, bang25.getTree());

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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:72:1: bang : ( simple_script | BANG simple_script -> ^( BANG simple_script ) );
    @throws(classOf[RecognitionException])
     final def bang():/*CREOLE_XParser.*/bang_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/bang_return  = new /*CREOLE_XParser.*/bang_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var BANG27: Token =null
        var simple_script26:/*CREOLE_XParser.*/simple_script_return = null

        var simple_script28:/*CREOLE_XParser.*/simple_script_return = null


        var BANG27_tree:CHRTree=null
        var stream_BANG:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token BANG")
        var stream_simple_script:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule simple_script");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:73:5: ( simple_script | BANG simple_script -> ^( BANG simple_script ) )
            var alt6=2;
            var LA6_0 = input.LA(1);

            if ( (LA6_0==LPAREN||LA6_0==R_ID||(LA6_0>=TRUE && LA6_0<=FALSE)||LA6_0==59) ) {
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:73:9: simple_script
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_simple_script_in_bang428);
                    simple_script26=simple_script();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_script26.getTree());

                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:74:9: BANG simple_script
                    {
                    BANG27=smatch(input,BANG,FOLLOW_BANG_in_bang438).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BANG.add(BANG27);

                    pushFollow(FOLLOW_simple_script_in_bang440);
                    simple_script28=simple_script();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_simple_script.add(simple_script28.getTree());


                    // AST REWRITE
                    // elements: BANG, simple_script
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 74:28: -> ^( BANG simple_script )
                    {
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:74:31: ^( BANG simple_script )
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:77:1: simple_script : ( rule | LPAREN script RPAREN -> script );
    @throws(classOf[RecognitionException])
     final def simple_script():/*CREOLE_XParser.*/simple_script_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/simple_script_return  = new /*CREOLE_XParser.*/simple_script_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN30: Token =null
        var RPAREN32: Token =null
        var rule29:/*CREOLE_XParser.*/rule_return = null

        var script31:/*CREOLE_XParser.*/script_return = null


        var LPAREN30_tree:CHRTree=null
        var RPAREN32_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_script:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule script");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:78:5: ( rule | LPAREN script RPAREN -> script )
            var alt7=2;
            var LA7_0 = input.LA(1);

            if ( (LA7_0==R_ID||(LA7_0>=TRUE && LA7_0<=FALSE)||LA7_0==59) ) {
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:78:9: rule
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_rule_in_simple_script468);
                    rule29=rule();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rule29.getTree());

                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:79:9: LPAREN script RPAREN
                    {
                    LPAREN30=smatch(input,LPAREN,FOLLOW_LPAREN_in_simple_script478).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN30);

                    pushFollow(FOLLOW_script_in_simple_script480);
                    script31=script();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_script.add(script31.getTree());
                    RPAREN32=smatch(input,RPAREN,FOLLOW_RPAREN_in_simple_script482).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN32);



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
                    // 79:31: -> script
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:98:1: rule : conjunction RARROW ( guard )? ( nu )? conjunction -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? ) ;
    @throws(classOf[RecognitionException])
     final def rule():/*CREOLE_XParser.*/rule_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/rule_return  = new /*CREOLE_XParser.*/rule_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var RARROW34: Token =null
        var conjunction33:/*CREOLE_XParser.*/conjunction_return = null

        var guard35:/*CREOLE_XParser.*/guard_return = null

        var nu36:/*CREOLE_XParser.*/nu_return = null

        var conjunction37:/*CREOLE_XParser.*/conjunction_return = null


        var RARROW34_tree:CHRTree=null
        var stream_RARROW:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RARROW")
        var stream_nu:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule nu");
        var stream_guard:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule guard");
        var stream_conjunction:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule conjunction");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:99:5: ( conjunction RARROW ( guard )? ( nu )? conjunction -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:99:9: conjunction RARROW ( guard )? ( nu )? conjunction
            {
            pushFollow(FOLLOW_conjunction_in_rule509);
            conjunction33=conjunction();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_conjunction.add(conjunction33.getTree());
            RARROW34=smatch(input,RARROW,FOLLOW_RARROW_in_rule511).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RARROW.add(RARROW34);

            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:99:28: ( guard )?
            var alt8=2;
            var LA8_0 = input.LA(1);

            if ( (LA8_0==LBRACK||LA8_0==ABS) ) {
                alt8=1;
            }
            alt8 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:0:0: guard
                    {
                    pushFollow(FOLLOW_guard_in_rule513);
                    guard35=guard();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_guard.add(guard35.getTree());

                    }
                case _ => //Do nothing
            }

            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:99:35: ( nu )?
            var alt9=2;
            var LA9_0 = input.LA(1);

            if ( (LA9_0==NU) ) {
                alt9=1;
            }
            alt9 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:0:0: nu
                    {
                    pushFollow(FOLLOW_nu_in_rule516);
                    nu36=nu();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_nu.add(nu36.getTree());

                    }
                case _ => //Do nothing
            }

            pushFollow(FOLLOW_conjunction_in_rule519);
            conjunction37=conjunction();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_conjunction.add(conjunction37.getTree());


            // AST REWRITE
            // elements: conjunction, guard, nu, conjunction
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 99:52: -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? )
            {
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:100:13: ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(RULE, "RULE").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:100:20: ( guard )?
                if ( stream_guard.hasNext() ) {
                    adaptor.addChild(root_1, stream_guard.nextTree());

                }
                stream_guard.reset();
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:100:27: ^( HEAD conjunction )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(HEAD, "HEAD").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_conjunction.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:100:47: ^( BODY conjunction )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(BODY, "BODY").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_conjunction.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:100:67: ( nu )?
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:103:1: guard : ( LBRACK ( rule )* RBRACK IMARK -> ^( GUARD ( rule )* ) | ABS LBRACK conjunction RBRACK IMARK -> ^( GUARD ^( ABS conjunction ) ) );
    @throws(classOf[RecognitionException])
     final def guard():/*CREOLE_XParser.*/guard_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/guard_return  = new /*CREOLE_XParser.*/guard_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LBRACK38: Token =null
        var RBRACK40: Token =null
        var IMARK41: Token =null
        var ABS42: Token =null
        var LBRACK43: Token =null
        var RBRACK45: Token =null
        var IMARK46: Token =null
        var rule39:/*CREOLE_XParser.*/rule_return = null

        var conjunction44:/*CREOLE_XParser.*/conjunction_return = null


        var LBRACK38_tree:CHRTree=null
        var RBRACK40_tree:CHRTree=null
        var IMARK41_tree:CHRTree=null
        var ABS42_tree:CHRTree=null
        var LBRACK43_tree:CHRTree=null
        var RBRACK45_tree:CHRTree=null
        var IMARK46_tree:CHRTree=null
        var stream_ABS:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ABS")
        var stream_RBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RBRACK")
        var stream_LBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LBRACK")
        var stream_IMARK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token IMARK")
        var stream_rule:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rule");
        var stream_conjunction:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule conjunction");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:104:5: ( LBRACK ( rule )* RBRACK IMARK -> ^( GUARD ( rule )* ) | ABS LBRACK conjunction RBRACK IMARK -> ^( GUARD ^( ABS conjunction ) ) )
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:104:9: LBRACK ( rule )* RBRACK IMARK
                    {
                    LBRACK38=smatch(input,LBRACK,FOLLOW_LBRACK_in_guard575).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK38);

                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:104:16: ( rule )*
                    //loop10:
                    var guard = true
                    while(guard) {
                        var alt10=2;
                        var LA10_0 = input.LA(1);

                        if ( (LA10_0==R_ID||(LA10_0>=TRUE && LA10_0<=FALSE)||LA10_0==59) ) {
                            alt10=1;
                        }


                        alt10 match{
                    		case 1 =>
                    		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:0:0: rule
                    		    {
                    		    pushFollow(FOLLOW_rule_in_guard577);
                    		    rule39=rule();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_rule.add(rule39.getTree());

                    		    }
                    		case _ =>
                    		    guard = false //loop10;
                        }
                    } //while (true);

                    RBRACK40=smatch(input,RBRACK,FOLLOW_RBRACK_in_guard580).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK40);

                    IMARK41=smatch(input,IMARK,FOLLOW_IMARK_in_guard582).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IMARK.add(IMARK41);



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
                    // 104:35: -> ^( GUARD ( rule )* )
                    {
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:104:38: ^( GUARD ( rule )* )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(GUARD, "GUARD").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:104:46: ( rule )*
                        while ( stream_rule.hasNext() ) {
                            adaptor.addChild(root_1, stream_rule.nextTree());

                        }
                        stream_rule.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:105:9: ABS LBRACK conjunction RBRACK IMARK
                    {
                    ABS42=smatch(input,ABS,FOLLOW_ABS_in_guard601).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ABS.add(ABS42);

                    LBRACK43=smatch(input,LBRACK,FOLLOW_LBRACK_in_guard603).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK43);

                    pushFollow(FOLLOW_conjunction_in_guard605);
                    conjunction44=conjunction();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_conjunction.add(conjunction44.getTree());
                    RBRACK45=smatch(input,RBRACK,FOLLOW_RBRACK_in_guard607).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK45);

                    IMARK46=smatch(input,IMARK,FOLLOW_IMARK_in_guard609).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IMARK.add(IMARK46);



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
                    // 105:45: -> ^( GUARD ^( ABS conjunction ) )
                    {
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:105:48: ^( GUARD ^( ABS conjunction ) )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(GUARD, "GUARD").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:105:56: ^( ABS conjunction )
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:108:1: nu : NU LPAREN variable ( COMMA variable )* RPAREN -> ^( NU ( variable )* ) ;
    @throws(classOf[RecognitionException])
     final def nu():/*CREOLE_XParser.*/nu_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/nu_return  = new /*CREOLE_XParser.*/nu_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var NU47: Token =null
        var LPAREN48: Token =null
        var COMMA50: Token =null
        var RPAREN52: Token =null
        var variable49:/*CREOLE_XParser.*/variable_return = null

        var variable51:/*CREOLE_XParser.*/variable_return = null


        var NU47_tree:CHRTree=null
        var LPAREN48_tree:CHRTree=null
        var COMMA50_tree:CHRTree=null
        var RPAREN52_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_NU:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token NU")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_variable:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule variable");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:109:5: ( NU LPAREN variable ( COMMA variable )* RPAREN -> ^( NU ( variable )* ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:109:9: NU LPAREN variable ( COMMA variable )* RPAREN
            {
            NU47=smatch(input,NU,FOLLOW_NU_in_nu640).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NU.add(NU47);

            LPAREN48=smatch(input,LPAREN,FOLLOW_LPAREN_in_nu642).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN48);

            pushFollow(FOLLOW_variable_in_nu644);
            variable49=variable();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variable.add(variable49.getTree());
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:109:28: ( COMMA variable )*
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
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:109:29: COMMA variable
            		    {
            		    COMMA50=smatch(input,COMMA,FOLLOW_COMMA_in_nu647).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA50);

            		    pushFollow(FOLLOW_variable_in_nu649);
            		    variable51=variable();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_variable.add(variable51.getTree());

            		    }
            		case _ =>
            		    guard = false //loop12;
                }
            } //while (true);

            RPAREN52=smatch(input,RPAREN,FOLLOW_RPAREN_in_nu653).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN52);



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
            // 109:53: -> ^( NU ( variable )* )
            {
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:109:56: ^( NU ( variable )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(stream_NU.nextNode(), root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:109:61: ( variable )*
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:112:1: conjunction : ( 'T' -> EMPTY | atom ( COMMA atom )* -> ( atom )* );
    @throws(classOf[RecognitionException])
     final def conjunction():/*CREOLE_XParser.*/conjunction_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/conjunction_return  = new /*CREOLE_XParser.*/conjunction_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var char_literal53: Token =null
        var COMMA55: Token =null
        var atom54:/*CREOLE_XParser.*/atom_return = null

        var atom56:/*CREOLE_XParser.*/atom_return = null


        var char_literal53_tree:CHRTree=null
        var COMMA55_tree:CHRTree=null
        var stream_59:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 59")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_atom:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule atom");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:113:5: ( 'T' -> EMPTY | atom ( COMMA atom )* -> ( atom )* )
            var alt14=2;
            var LA14_0 = input.LA(1);

            if ( (LA14_0==59) ) {
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:113:9: 'T'
                    {
                    char_literal53=smatch(input,59,FOLLOW_59_in_conjunction681).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_59.add(char_literal53);



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
                    // 113:13: -> EMPTY
                    {
                        adaptor.addChild(root_0, adaptor.create(EMPTY, "EMPTY").asInstanceOf[CHRTree]);

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:114:9: atom ( COMMA atom )*
                    {
                    pushFollow(FOLLOW_atom_in_conjunction695);
                    atom54=atom();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom54.getTree());
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:114:14: ( COMMA atom )*
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
                    		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:114:15: COMMA atom
                    		    {
                    		    COMMA55=smatch(input,COMMA,FOLLOW_COMMA_in_conjunction698).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA55);

                    		    pushFollow(FOLLOW_atom_in_conjunction700);
                    		    atom56=atom();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_atom.add(atom56.getTree());

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
                    // 114:28: -> ( atom )*
                    {
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:114:31: ( atom )*
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:117:1: atom : ( TRUE | FALSE | relation ( termlist )? -> ^( ATOM relation ( termlist )? ) );
    @throws(classOf[RecognitionException])
     final def atom():/*CREOLE_XParser.*/atom_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/atom_return  = new /*CREOLE_XParser.*/atom_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var TRUE57: Token =null
        var FALSE58: Token =null
        var relation59:/*CREOLE_XParser.*/relation_return = null

        var termlist60:/*CREOLE_XParser.*/termlist_return = null


        var TRUE57_tree:CHRTree=null
        var FALSE58_tree:CHRTree=null
        var stream_termlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule termlist");
        var stream_relation:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule relation");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:118:5: ( TRUE | FALSE | relation ( termlist )? -> ^( ATOM relation ( termlist )? ) )
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:118:9: TRUE
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    TRUE57=smatch(input,TRUE,FOLLOW_TRUE_in_atom726).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TRUE57_tree = adaptor.create(TRUE57).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, TRUE57_tree);
                    }

                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:119:9: FALSE
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    FALSE58=smatch(input,FALSE,FOLLOW_FALSE_in_atom736).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FALSE58_tree = adaptor.create(FALSE58).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, FALSE58_tree);
                    }

                    }case 3 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:120:9: relation ( termlist )?
                    {
                    pushFollow(FOLLOW_relation_in_atom746);
                    relation59=relation();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_relation.add(relation59.getTree());
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:120:18: ( termlist )?
                    var alt15=2;
                    var LA15_0 = input.LA(1);

                    if ( (LA15_0==LPAREN) ) {
                        alt15=1;
                    }
                    alt15 match{
                        case 1 =>
                            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:0:0: termlist
                            {
                            pushFollow(FOLLOW_termlist_in_atom748);
                            termlist60=termlist();

                            state._fsp -= 1 
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_termlist.add(termlist60.getTree());

                            }
                        case _ => //Do nothing
                    }



                    // AST REWRITE
                    // elements: termlist, relation
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 120:29: -> ^( ATOM relation ( termlist )? )
                    {
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:121:13: ^( ATOM relation ( termlist )? )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(ATOM, "ATOM").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_relation.nextTree());
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:121:29: ( termlist )?
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:124:1: termlist : LPAREN term ( COMMA term )* RPAREN -> ^( VARS ( term )* ) ;
    @throws(classOf[RecognitionException])
     final def termlist():/*CREOLE_XParser.*/termlist_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/termlist_return  = new /*CREOLE_XParser.*/termlist_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN61: Token =null
        var COMMA63: Token =null
        var RPAREN65: Token =null
        var term62:/*CREOLE_XParser.*/term_return = null

        var term64:/*CREOLE_XParser.*/term_return = null


        var LPAREN61_tree:CHRTree=null
        var COMMA63_tree:CHRTree=null
        var RPAREN65_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_term:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule term");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:125:5: ( LPAREN term ( COMMA term )* RPAREN -> ^( VARS ( term )* ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:125:9: LPAREN term ( COMMA term )* RPAREN
            {
            LPAREN61=smatch(input,LPAREN,FOLLOW_LPAREN_in_termlist792).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN61);

            pushFollow(FOLLOW_term_in_termlist794);
            term62=term();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_term.add(term62.getTree());
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:125:21: ( COMMA term )*
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
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:125:22: COMMA term
            		    {
            		    COMMA63=smatch(input,COMMA,FOLLOW_COMMA_in_termlist797).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA63);

            		    pushFollow(FOLLOW_term_in_termlist799);
            		    term64=term();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_term.add(term64.getTree());

            		    }
            		case _ =>
            		    guard = false //loop17;
                }
            } //while (true);

            RPAREN65=smatch(input,RPAREN,FOLLOW_RPAREN_in_termlist803).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN65);



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
            // 125:42: -> ^( VARS ( term )* )
            {
                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:125:47: ^( VARS ( term )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(VARS, "VARS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:125:54: ( term )*
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:128:1: term : ( constant | variable );
    @throws(classOf[RecognitionException])
     final def term():/*CREOLE_XParser.*/term_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/term_return  = new /*CREOLE_XParser.*/term_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var constant66:/*CREOLE_XParser.*/constant_return = null

        var variable67:/*CREOLE_XParser.*/variable_return = null



        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:129:2: ( constant | variable )
            var alt18=2;
            var LA18_0 = input.LA(1);

            if ( ((LA18_0>=INT && LA18_0<=STRING)) ) {
                alt18=1;
            }
            else if ( (LA18_0==R_ID||LA18_0==V_ID) ) {
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:129:4: constant
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_constant_in_term828);
                    constant66=constant();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constant66.getTree());

                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:130:4: variable
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_variable_in_term833);
                    variable67=variable();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variable67.getTree());

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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:133:1: relation : R_ID ;
    @throws(classOf[RecognitionException])
     final def relation():/*CREOLE_XParser.*/relation_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/relation_return  = new /*CREOLE_XParser.*/relation_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var R_ID68: Token =null

        var R_ID68_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:134:5: ( R_ID )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:134:9: R_ID
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            R_ID68=smatch(input,R_ID,FOLLOW_R_ID_in_relation849).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            R_ID68_tree = adaptor.create(R_ID68).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, R_ID68_tree);
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:137:1: variable : ( V_ID | R_ID );
    @throws(classOf[RecognitionException])
     final def variable():/*CREOLE_XParser.*/variable_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/variable_return  = new /*CREOLE_XParser.*/variable_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var set69: Token =null

        var set69_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:138:5: ( V_ID | R_ID )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            set69=input.LT(1).asInstanceOf[Token]
            if ( input.LA(1)==R_ID||input.LA(1)==V_ID ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, adaptor.create(set69).asInstanceOf[CHRTree]);
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:141:1: constant : ( INT | STRING );
    @throws(classOf[RecognitionException])
     final def constant():/*CREOLE_XParser.*/constant_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/constant_return  = new /*CREOLE_XParser.*/constant_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var set70: Token =null

        var set70_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:142:2: ( INT | STRING )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE_X.g:
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            set70=input.LT(1).asInstanceOf[Token]
            if ( (input.LA(1)>=INT && input.LA(1)<=STRING) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, adaptor.create(set70).asInstanceOf[CHRTree]);
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

    // Delegated rules


 

      final val FOLLOW_process_in_start171:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_declaration_in_process190:BitSet = new BitSet( Array[Long](0x0800001812080000L))
      //new long[]{0x0800001812080000L});
      final val FOLLOW_script_in_process192:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_declaration225:BitSet = new BitSet( Array[Long](0x0200000000000000L))
      //new long[]{0x0200000000000000L});
      final val FOLLOW_57_in_declaration227:BitSet = new BitSet( Array[Long](0x0000000000100000L))
      //new long[]{0x0000000000100000L});
      final val FOLLOW_COLON_in_declaration229:BitSet = new BitSet( Array[Long](0x0000000007000000L))
      //new long[]{0x0000000007000000L});
      final val FOLLOW_rlist_in_declaration231:BitSet = new BitSet( Array[Long](0x0000000000200000L))
      //new long[]{0x0000000000200000L});
      final val FOLLOW_SEMI_in_declaration233:BitSet = new BitSet( Array[Long](0x0400000000000000L))
      //new long[]{0x0400000000000000L});
      final val FOLLOW_58_in_declaration235:BitSet = new BitSet( Array[Long](0x0000000000100000L))
      //new long[]{0x0000000000100000L});
      final val FOLLOW_COLON_in_declaration237:BitSet = new BitSet( Array[Long](0x0000000007000000L))
      //new long[]{0x0000000007000000L});
      final val FOLLOW_rlist_in_declaration239:BitSet = new BitSet( Array[Long](0x0000000000400000L))
      //new long[]{0x0000000000400000L});
      final val FOLLOW_RPAREN_in_declaration241:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_rdeclaration_in_rlist290:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_COMMA_in_rlist293:BitSet = new BitSet( Array[Long](0x0000000006000000L))
      //new long[]{0x0000000006000000L});
      final val FOLLOW_rdeclaration_in_rlist295:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_UNDEF_in_rlist312:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_rdeclaration335:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_TILDE_in_rdeclaration345:BitSet = new BitSet( Array[Long](0x0000000002000000L))
      //new long[]{0x0000000002000000L});
      final val FOLLOW_R_ID_in_rdeclaration347:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_parallel_in_script374:BitSet = new BitSet( Array[Long](0x0000000000200002L))
      //new long[]{0x0000000000200002L});
      final val FOLLOW_SEMI_in_script377:BitSet = new BitSet( Array[Long](0x0800001812080000L))
      //new long[]{0x0800001812080000L});
      final val FOLLOW_parallel_in_script380:BitSet = new BitSet( Array[Long](0x0000000000200002L))
      //new long[]{0x0000000000200002L});
      final val FOLLOW_bang_in_parallel401:BitSet = new BitSet( Array[Long](0x0000000008000002L))
      //new long[]{0x0000000008000002L});
      final val FOLLOW_BAR_in_parallel404:BitSet = new BitSet( Array[Long](0x0800001812080000L))
      //new long[]{0x0800001812080000L});
      final val FOLLOW_bang_in_parallel407:BitSet = new BitSet( Array[Long](0x0000000008000002L))
      //new long[]{0x0000000008000002L});
      final val FOLLOW_simple_script_in_bang428:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_BANG_in_bang438:BitSet = new BitSet( Array[Long](0x0800001802080000L))
      //new long[]{0x0800001802080000L});
      final val FOLLOW_simple_script_in_bang440:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_rule_in_simple_script468:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_simple_script478:BitSet = new BitSet( Array[Long](0x0800001812080000L))
      //new long[]{0x0800001812080000L});
      final val FOLLOW_script_in_simple_script480:BitSet = new BitSet( Array[Long](0x0000000000400000L))
      //new long[]{0x0000000000400000L});
      final val FOLLOW_RPAREN_in_simple_script482:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_conjunction_in_rule509:BitSet = new BitSet( Array[Long](0x0000000020000000L))
      //new long[]{0x0000000020000000L});
      final val FOLLOW_RARROW_in_rule511:BitSet = new BitSet( Array[Long](0x0800001E42000000L))
      //new long[]{0x0800001E42000000L});
      final val FOLLOW_guard_in_rule513:BitSet = new BitSet( Array[Long](0x0800001C02000000L))
      //new long[]{0x0800001C02000000L});
      final val FOLLOW_nu_in_rule516:BitSet = new BitSet( Array[Long](0x0800001802000000L))
      //new long[]{0x0800001802000000L});
      final val FOLLOW_conjunction_in_rule519:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LBRACK_in_guard575:BitSet = new BitSet( Array[Long](0x0800001882000000L))
      //new long[]{0x0800001882000000L});
      final val FOLLOW_rule_in_guard577:BitSet = new BitSet( Array[Long](0x0800001882000000L))
      //new long[]{0x0800001882000000L});
      final val FOLLOW_RBRACK_in_guard580:BitSet = new BitSet( Array[Long](0x0000000100000000L))
      //new long[]{0x0000000100000000L});
      final val FOLLOW_IMARK_in_guard582:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ABS_in_guard601:BitSet = new BitSet( Array[Long](0x0000000040000000L))
      //new long[]{0x0000000040000000L});
      final val FOLLOW_LBRACK_in_guard603:BitSet = new BitSet( Array[Long](0x0800001802000000L))
      //new long[]{0x0800001802000000L});
      final val FOLLOW_conjunction_in_guard605:BitSet = new BitSet( Array[Long](0x0000000080000000L))
      //new long[]{0x0000000080000000L});
      final val FOLLOW_RBRACK_in_guard607:BitSet = new BitSet( Array[Long](0x0000000100000000L))
      //new long[]{0x0000000100000000L});
      final val FOLLOW_IMARK_in_guard609:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_NU_in_nu640:BitSet = new BitSet( Array[Long](0x0000000000080000L))
      //new long[]{0x0000000000080000L});
      final val FOLLOW_LPAREN_in_nu642:BitSet = new BitSet( Array[Long](0x0000002002000000L))
      //new long[]{0x0000002002000000L});
      final val FOLLOW_variable_in_nu644:BitSet = new BitSet( Array[Long](0x0000000000C00000L))
      //new long[]{0x0000000000C00000L});
      final val FOLLOW_COMMA_in_nu647:BitSet = new BitSet( Array[Long](0x0000002002000000L))
      //new long[]{0x0000002002000000L});
      final val FOLLOW_variable_in_nu649:BitSet = new BitSet( Array[Long](0x0000000000C00000L))
      //new long[]{0x0000000000C00000L});
      final val FOLLOW_RPAREN_in_nu653:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_59_in_conjunction681:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_atom_in_conjunction695:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_COMMA_in_conjunction698:BitSet = new BitSet( Array[Long](0x0800001802000000L))
      //new long[]{0x0800001802000000L});
      final val FOLLOW_atom_in_conjunction700:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_TRUE_in_atom726:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_FALSE_in_atom736:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_relation_in_atom746:BitSet = new BitSet( Array[Long](0x0000000000080002L))
      //new long[]{0x0000000000080002L});
      final val FOLLOW_termlist_in_atom748:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_termlist792:BitSet = new BitSet( Array[Long](0x000000E002000000L))
      //new long[]{0x000000E002000000L});
      final val FOLLOW_term_in_termlist794:BitSet = new BitSet( Array[Long](0x0000000000C00000L))
      //new long[]{0x0000000000C00000L});
      final val FOLLOW_COMMA_in_termlist797:BitSet = new BitSet( Array[Long](0x000000E002000000L))
      //new long[]{0x000000E002000000L});
      final val FOLLOW_term_in_termlist799:BitSet = new BitSet( Array[Long](0x0000000000C00000L))
      //new long[]{0x0000000000C00000L});
      final val FOLLOW_RPAREN_in_termlist803:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_constant_in_term828:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_variable_in_term833:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_relation849:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_set_in_variable0:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_set_in_constant0:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});

}