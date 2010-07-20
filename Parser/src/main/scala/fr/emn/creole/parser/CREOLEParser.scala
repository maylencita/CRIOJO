// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/main/antlr3/fr/emn/creole/parser/CREOLE.g 2010-07-12 14:47:58

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
 class CREOLEParser(input: TokenStream, state:SRecognizerSharedState) 
 extends SParser(input,state) {
 
 	def this(input: TokenStream){
 		this(input, new SRecognizerSharedState())
 	}
 
    val tokenNames:Array[String] = Array[String] (
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SCRIPT", "ATOM", "VARS", "RULE", "HEAD", "BODY", "MULTI", "DECLARATION", "EMPTYLIST", "PUBLIC", "PRIVATE", "PROCESS", "GUARD", "EMPTY", "INT_ATOM", "LPAREN", "COLON", "SEMI", "RPAREN", "COMMA", "UNDEF", "R_ID", "TILDE", "RARROW", "LBRACK", "RBRACK", "IMARK", "NU", "ZERO", "TRUE", "FALSE", "V_ID", "LET", "IN", "EQ_OP", "LT", "LTEQ", "PLUS", "MINUS", "LCURL", "RCURL", "POINT", "SLASH", "BAR", "BANG", "INT", "COMMENT", "WS", "ESC_SEQ", "STRING", "CHAR", "EXPONENT", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "'public'", "'private'"
    )
      val EXPONENT:Int = 55;
      val LT:Int = 39;
      val LBRACK:Int = 28;
      val EQ_OP:Int = 38;
      val POINT:Int = 45;
      val HEAD:Int = 8;
      val OCTAL_ESC:Int = 58;
      val LTEQ:Int = 40;
      val CHAR:Int = 54;
      val MULTI:Int = 10;
      val ATOM:Int = 5;
      val EOF:Int = -1;
      val T__60:Int = 60;
      val DECLARATION:Int = 11;
      val LPAREN:Int = 19;
      val ZERO:Int = 32;
      val INT_ATOM:Int = 18;
      val RPAREN:Int = 22;
      val ESC_SEQ:Int = 52;
      val SLASH:Int = 46;
      val IN:Int = 37;
      val COMMA:Int = 23;
      val T__59:Int = 59;
      val TILDE:Int = 26;
      val PLUS:Int = 41;
      val BODY:Int = 9;
      val UNDEF:Int = 24;
      val COMMENT:Int = 50;
      val IMARK:Int = 30;
      val RBRACK:Int = 29;
      val SCRIPT:Int = 4;
      val RULE:Int = 7;
      val NU:Int = 31;
      val R_ID:Int = 25;
      val PRIVATE:Int = 14;
      val VARS:Int = 6;
      val UNICODE_ESC:Int = 57;
      val RARROW:Int = 27;
      val HEX_DIGIT:Int = 56;
      val V_ID:Int = 35;
      val INT:Int = 49;
      val BANG:Int = 48;
      val MINUS:Int = 42;
      val SEMI:Int = 21;
      val TRUE:Int = 33;
      val EMPTY:Int = 17;
      val COLON:Int = 20;
      val LCURL:Int = 43;
      val WS:Int = 51;
      val RCURL:Int = 44;
      val EMPTYLIST:Int = 12;
      val GUARD:Int = 16;
      val PROCESS:Int = 15;
      val FALSE:Int = 34;
      val PUBLIC:Int = 13;
      val BAR:Int = 47;
      val LET:Int = 36;
      val STRING:Int = 53;

    // delegates
    // delegators


        
    protected var adaptor:TreeAdaptor = new CommonTreeAdaptor()

    def setTreeAdaptor(adaptor:TreeAdaptor) {
        this.adaptor = adaptor
    }
    def  getTreeAdaptor():TreeAdaptor = adaptor

    override def getTokenNames: Array[String] = /*CREOLEParser.*/tokenNames 
    override def getGrammarFileName(): String = "src/main/antlr3/fr/emn/creole/parser/CREOLE.g"


    	def getCHRTreeTokens = new CHRTreeTokens(this.tokenNames)


      class start_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "start" rule(...)
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:41:1: start : process ;
    @throws(classOf[RecognitionException])
     final def start():/*CREOLEParser.*/start_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/start_return  = new /*CREOLEParser.*/start_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var process1:/*CREOLEParser.*/process_return = null



        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:42:5: ( process )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:42:9: process
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            pushFollow(FOLLOW_process_in_start186);
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:45:1: process : declaration script -> ^( PROCESS declaration ^( SCRIPT script ) ) ;
    @throws(classOf[RecognitionException])
     final def process():/*CREOLEParser.*/process_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/process_return  = new /*CREOLEParser.*/process_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var declaration2:/*CREOLEParser.*/declaration_return = null

        var script3:/*CREOLEParser.*/script_return = null


        var stream_declaration:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        var stream_script:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule script");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:46:5: ( declaration script -> ^( PROCESS declaration ^( SCRIPT script ) ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:46:9: declaration script
            {
            pushFollow(FOLLOW_declaration_in_process205);
            declaration2=declaration();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declaration.add(declaration2.getTree());
            pushFollow(FOLLOW_script_in_process207);
            script3=script();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_script.add(script3.getTree());


            // AST REWRITE
            // elements: declaration, script
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
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:46:31: ^( PROCESS declaration ^( SCRIPT script ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(PROCESS, "PROCESS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                adaptor.addChild(root_1, stream_declaration.nextTree());
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:46:53: ^( SCRIPT script )
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:49:1: declaration : LPAREN 'public' COLON rlist SEMI 'private' COLON rlist RPAREN -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) ) ;
    @throws(classOf[RecognitionException])
     final def declaration():/*CREOLEParser.*/declaration_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/declaration_return  = new /*CREOLEParser.*/declaration_return();
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
        var rlist7:/*CREOLEParser.*/rlist_return = null

        var rlist11:/*CREOLEParser.*/rlist_return = null


        var LPAREN4_tree:CHRTree=null
        var string_literal5_tree:CHRTree=null
        var COLON6_tree:CHRTree=null
        var SEMI8_tree:CHRTree=null
        var string_literal9_tree:CHRTree=null
        var COLON10_tree:CHRTree=null
        var RPAREN12_tree:CHRTree=null
        var stream_COLON:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COLON")
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_59:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 59")
        var stream_SEMI:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SEMI")
        var stream_60:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 60")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_rlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rlist");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:50:5: ( LPAREN 'public' COLON rlist SEMI 'private' COLON rlist RPAREN -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:50:9: LPAREN 'public' COLON rlist SEMI 'private' COLON rlist RPAREN
            {
            LPAREN4=smatch(input,LPAREN,FOLLOW_LPAREN_in_declaration240).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN4);

            string_literal5=smatch(input,59,FOLLOW_59_in_declaration242).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_59.add(string_literal5);

            COLON6=smatch(input,COLON,FOLLOW_COLON_in_declaration244).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON6);

            pushFollow(FOLLOW_rlist_in_declaration246);
            rlist7=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist7.getTree());
            SEMI8=smatch(input,SEMI,FOLLOW_SEMI_in_declaration248).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI8);

            string_literal9=smatch(input,60,FOLLOW_60_in_declaration250).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_60.add(string_literal9);

            COLON10=smatch(input,COLON,FOLLOW_COLON_in_declaration252).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON10);

            pushFollow(FOLLOW_rlist_in_declaration254);
            rlist11=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist11.getTree());
            RPAREN12=smatch(input,RPAREN,FOLLOW_RPAREN_in_declaration256).asInstanceOf[Token]; if (state.failed) return retval; 
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
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:51:13: ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(DECLARATION, "DECLARATION").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:51:27: ^( PUBLIC rlist )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(PUBLIC, "PUBLIC").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_rlist.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:51:43: ^( PRIVATE rlist )
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:54:1: rlist : ( rdeclaration ( COMMA rdeclaration )* -> ( rdeclaration )* | UNDEF -> EMPTYLIST );
    @throws(classOf[RecognitionException])
     final def rlist():/*CREOLEParser.*/rlist_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/rlist_return  = new /*CREOLEParser.*/rlist_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var COMMA14: Token =null
        var UNDEF16: Token =null
        var rdeclaration13:/*CREOLEParser.*/rdeclaration_return = null

        var rdeclaration15:/*CREOLEParser.*/rdeclaration_return = null


        var COMMA14_tree:CHRTree=null
        var UNDEF16_tree:CHRTree=null
        var stream_UNDEF:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token UNDEF")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_rdeclaration:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rdeclaration");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:55:5: ( rdeclaration ( COMMA rdeclaration )* -> ( rdeclaration )* | UNDEF -> EMPTYLIST )
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:55:9: rdeclaration ( COMMA rdeclaration )*
                    {
                    pushFollow(FOLLOW_rdeclaration_in_rlist305);
                    rdeclaration13=rdeclaration();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rdeclaration.add(rdeclaration13.getTree());
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:55:22: ( COMMA rdeclaration )*
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
                    		    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:55:23: COMMA rdeclaration
                    		    {
                    		    COMMA14=smatch(input,COMMA,FOLLOW_COMMA_in_rlist308).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA14);

                    		    pushFollow(FOLLOW_rdeclaration_in_rlist310);
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
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:55:47: ( rdeclaration )*
                        while ( stream_rdeclaration.hasNext() ) {
                            adaptor.addChild(root_0, stream_rdeclaration.nextTree());

                        }
                        stream_rdeclaration.reset();

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:56:9: UNDEF
                    {
                    UNDEF16=smatch(input,UNDEF,FOLLOW_UNDEF_in_rlist327).asInstanceOf[Token]; if (state.failed) return retval; 
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:59:1: rdeclaration : ( R_ID | TILDE R_ID -> ^( MULTI R_ID ) );
    @throws(classOf[RecognitionException])
     final def rdeclaration():/*CREOLEParser.*/rdeclaration_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/rdeclaration_return  = new /*CREOLEParser.*/rdeclaration_return();
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
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:60:5: ( R_ID | TILDE R_ID -> ^( MULTI R_ID ) )
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
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:60:9: R_ID
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    R_ID17=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration350).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    R_ID17_tree = adaptor.create(R_ID17).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, R_ID17_tree);
                    }

                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:61:9: TILDE R_ID
                    {
                    TILDE18=smatch(input,TILDE,FOLLOW_TILDE_in_rdeclaration360).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TILDE.add(TILDE18);

                    R_ID19=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration362).asInstanceOf[Token]; if (state.failed) return retval; 
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
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:61:23: ^( MULTI R_ID )
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:64:1: script : ( rule )* ;
    @throws(classOf[RecognitionException])
     final def script():/*CREOLEParser.*/script_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/script_return  = new /*CREOLEParser.*/script_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var rule20:/*CREOLEParser.*/rule_return = null



        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:65:5: ( ( rule )* )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:66:8: ( rule )*
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:66:8: ( rule )*
            //loop4:
            var guard = true
            while(guard) {
                var alt4=2;
                var LA4_0 = input.LA(1);

                if ( (LA4_0==R_ID||(LA4_0>=ZERO && LA4_0<=FALSE)) ) {
                    alt4=1;
                }


                alt4 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:0:0: rule
            		    {
            		    pushFollow(FOLLOW_rule_in_script394);
            		    rule20=rule();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) adaptor.addChild(root_0, rule20.getTree());

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

      class rule_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "rule" rule(...)
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:86:1: rule : conjunction RARROW ( guard )? ( nu )? conjunction -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? ) ;
    @throws(classOf[RecognitionException])
     final def rule():/*CREOLEParser.*/rule_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/rule_return  = new /*CREOLEParser.*/rule_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var RARROW22: Token =null
        var conjunction21:/*CREOLEParser.*/conjunction_return = null

        var guard23:/*CREOLEParser.*/guard_return = null

        var nu24:/*CREOLEParser.*/nu_return = null

        var conjunction25:/*CREOLEParser.*/conjunction_return = null


        var RARROW22_tree:CHRTree=null
        var stream_RARROW:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RARROW")
        var stream_nu:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule nu");
        var stream_guard:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule guard");
        var stream_conjunction:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule conjunction");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:87:5: ( conjunction RARROW ( guard )? ( nu )? conjunction -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:87:9: conjunction RARROW ( guard )? ( nu )? conjunction
            {
            pushFollow(FOLLOW_conjunction_in_rule417);
            conjunction21=conjunction();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_conjunction.add(conjunction21.getTree());
            RARROW22=smatch(input,RARROW,FOLLOW_RARROW_in_rule419).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RARROW.add(RARROW22);

            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:87:28: ( guard )?
            var alt5=2;
            var LA5_0 = input.LA(1);

            if ( (LA5_0==LBRACK) ) {
                alt5=1;
            }
            alt5 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:0:0: guard
                    {
                    pushFollow(FOLLOW_guard_in_rule421);
                    guard23=guard();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_guard.add(guard23.getTree());

                    }
                case _ => //Do nothing
            }

            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:87:35: ( nu )?
            var alt6=2;
            var LA6_0 = input.LA(1);

            if ( (LA6_0==NU) ) {
                alt6=1;
            }
            alt6 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:0:0: nu
                    {
                    pushFollow(FOLLOW_nu_in_rule424);
                    nu24=nu();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_nu.add(nu24.getTree());

                    }
                case _ => //Do nothing
            }

            pushFollow(FOLLOW_conjunction_in_rule427);
            conjunction25=conjunction();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_conjunction.add(conjunction25.getTree());


            // AST REWRITE
            // elements: nu, conjunction, conjunction, guard
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 87:52: -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? )
            {
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:88:13: ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(RULE, "RULE").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:88:20: ( guard )?
                if ( stream_guard.hasNext() ) {
                    adaptor.addChild(root_1, stream_guard.nextTree());

                }
                stream_guard.reset();
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:88:27: ^( HEAD conjunction )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(HEAD, "HEAD").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_conjunction.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:88:47: ^( BODY conjunction )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(BODY, "BODY").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_conjunction.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:88:67: ( nu )?
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:91:1: guard : LBRACK ( rule )* RBRACK IMARK -> ^( GUARD ( rule )* ) ;
    @throws(classOf[RecognitionException])
     final def guard():/*CREOLEParser.*/guard_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/guard_return  = new /*CREOLEParser.*/guard_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LBRACK26: Token =null
        var RBRACK28: Token =null
        var IMARK29: Token =null
        var rule27:/*CREOLEParser.*/rule_return = null


        var LBRACK26_tree:CHRTree=null
        var RBRACK28_tree:CHRTree=null
        var IMARK29_tree:CHRTree=null
        var stream_RBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RBRACK")
        var stream_LBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LBRACK")
        var stream_IMARK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token IMARK")
        var stream_rule:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rule");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:92:5: ( LBRACK ( rule )* RBRACK IMARK -> ^( GUARD ( rule )* ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:92:9: LBRACK ( rule )* RBRACK IMARK
            {
            LBRACK26=smatch(input,LBRACK,FOLLOW_LBRACK_in_guard483).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK26);

            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:92:16: ( rule )*
            //loop7:
            var guard = true
            while(guard) {
                var alt7=2;
                var LA7_0 = input.LA(1);

                if ( (LA7_0==R_ID||(LA7_0>=ZERO && LA7_0<=FALSE)) ) {
                    alt7=1;
                }


                alt7 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:0:0: rule
            		    {
            		    pushFollow(FOLLOW_rule_in_guard485);
            		    rule27=rule();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_rule.add(rule27.getTree());

            		    }
            		case _ =>
            		    guard = false //loop7;
                }
            } //while (true);

            RBRACK28=smatch(input,RBRACK,FOLLOW_RBRACK_in_guard488).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK28);

            IMARK29=smatch(input,IMARK,FOLLOW_IMARK_in_guard490).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IMARK.add(IMARK29);



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
            // 92:35: -> ^( GUARD ( rule )* )
            {
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:92:38: ^( GUARD ( rule )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(GUARD, "GUARD").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:92:46: ( rule )*
                while ( stream_rule.hasNext() ) {
                    adaptor.addChild(root_1, stream_rule.nextTree());

                }
                stream_rule.reset();

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
    // $ANTLR end "guard"

      class nu_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "nu" rule(...)
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:95:1: nu : NU LPAREN variable ( COMMA variable )* RPAREN -> ^( NU ( variable )* ) ;
    @throws(classOf[RecognitionException])
     final def nu():/*CREOLEParser.*/nu_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/nu_return  = new /*CREOLEParser.*/nu_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var NU30: Token =null
        var LPAREN31: Token =null
        var COMMA33: Token =null
        var RPAREN35: Token =null
        var variable32:/*CREOLEParser.*/variable_return = null

        var variable34:/*CREOLEParser.*/variable_return = null


        var NU30_tree:CHRTree=null
        var LPAREN31_tree:CHRTree=null
        var COMMA33_tree:CHRTree=null
        var RPAREN35_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_NU:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token NU")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_variable:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule variable");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:96:5: ( NU LPAREN variable ( COMMA variable )* RPAREN -> ^( NU ( variable )* ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:96:9: NU LPAREN variable ( COMMA variable )* RPAREN
            {
            NU30=smatch(input,NU,FOLLOW_NU_in_nu522).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NU.add(NU30);

            LPAREN31=smatch(input,LPAREN,FOLLOW_LPAREN_in_nu524).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN31);

            pushFollow(FOLLOW_variable_in_nu526);
            variable32=variable();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variable.add(variable32.getTree());
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:96:28: ( COMMA variable )*
            //loop8:
            var guard = true
            while(guard) {
                var alt8=2;
                var LA8_0 = input.LA(1);

                if ( (LA8_0==COMMA) ) {
                    alt8=1;
                }


                alt8 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:96:29: COMMA variable
            		    {
            		    COMMA33=smatch(input,COMMA,FOLLOW_COMMA_in_nu529).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA33);

            		    pushFollow(FOLLOW_variable_in_nu531);
            		    variable34=variable();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_variable.add(variable34.getTree());

            		    }
            		case _ =>
            		    guard = false //loop8;
                }
            } //while (true);

            RPAREN35=smatch(input,RPAREN,FOLLOW_RPAREN_in_nu535).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN35);



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
            // 96:53: -> ^( NU ( variable )* )
            {
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:96:56: ^( NU ( variable )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(stream_NU.nextNode(), root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:96:61: ( variable )*
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:99:1: conjunction : ( ZERO -> EMPTY | atom ( COMMA atom )* -> ( atom )* );
    @throws(classOf[RecognitionException])
     final def conjunction():/*CREOLEParser.*/conjunction_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/conjunction_return  = new /*CREOLEParser.*/conjunction_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var ZERO36: Token =null
        var COMMA38: Token =null
        var atom37:/*CREOLEParser.*/atom_return = null

        var atom39:/*CREOLEParser.*/atom_return = null


        var ZERO36_tree:CHRTree=null
        var COMMA38_tree:CHRTree=null
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_ZERO:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ZERO")
        var stream_atom:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule atom");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:100:5: ( ZERO -> EMPTY | atom ( COMMA atom )* -> ( atom )* )
            var alt10=2;
            var LA10_0 = input.LA(1);

            if ( (LA10_0==ZERO) ) {
                alt10=1;
            }
            else if ( (LA10_0==R_ID||(LA10_0>=TRUE && LA10_0<=FALSE)) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            alt10 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:100:9: ZERO
                    {
                    ZERO36=smatch(input,ZERO,FOLLOW_ZERO_in_conjunction563).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ZERO.add(ZERO36);



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
                    // 100:14: -> EMPTY
                    {
                        adaptor.addChild(root_0, adaptor.create(EMPTY, "EMPTY").asInstanceOf[CHRTree]);

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:101:9: atom ( COMMA atom )*
                    {
                    pushFollow(FOLLOW_atom_in_conjunction577);
                    atom37=atom();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom37.getTree());
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:101:14: ( COMMA atom )*
                    //loop9:
                    var guard = true
                    while(guard) {
                        var alt9=2;
                        var LA9_0 = input.LA(1);

                        if ( (LA9_0==COMMA) ) {
                            alt9=1;
                        }


                        alt9 match{
                    		case 1 =>
                    		    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:101:15: COMMA atom
                    		    {
                    		    COMMA38=smatch(input,COMMA,FOLLOW_COMMA_in_conjunction580).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA38);

                    		    pushFollow(FOLLOW_atom_in_conjunction582);
                    		    atom39=atom();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_atom.add(atom39.getTree());

                    		    }
                    		case _ =>
                    		    guard = false //loop9;
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
                    // 101:28: -> ( atom )*
                    {
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:101:31: ( atom )*
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:104:1: atom : ( TRUE | FALSE | relation ( varlist )? -> ^( ATOM relation ( varlist )? ) );
    @throws(classOf[RecognitionException])
     final def atom():/*CREOLEParser.*/atom_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/atom_return  = new /*CREOLEParser.*/atom_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var TRUE40: Token =null
        var FALSE41: Token =null
        var relation42:/*CREOLEParser.*/relation_return = null

        var varlist43:/*CREOLEParser.*/varlist_return = null


        var TRUE40_tree:CHRTree=null
        var FALSE41_tree:CHRTree=null
        var stream_varlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule varlist");
        var stream_relation:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule relation");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:105:5: ( TRUE | FALSE | relation ( varlist )? -> ^( ATOM relation ( varlist )? ) )
            var alt12=3;
            input.LA(1) match{
            	case TRUE => alt12=1;    
            	case FALSE => alt12=2;    
            	case R_ID => alt12=3;    
            	case _ =>
            		    if (state.backtracking>0) {state.failed=true; return retval;}
            		    val nvae =
            		        new NoViableAltException("", 12, 0, input);

            		    throw nvae;

            }

            alt12 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:105:9: TRUE
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    TRUE40=smatch(input,TRUE,FOLLOW_TRUE_in_atom608).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TRUE40_tree = adaptor.create(TRUE40).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, TRUE40_tree);
                    }

                    }case 2 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:106:9: FALSE
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    FALSE41=smatch(input,FALSE,FOLLOW_FALSE_in_atom618).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FALSE41_tree = adaptor.create(FALSE41).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, FALSE41_tree);
                    }

                    }case 3 =>
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:107:9: relation ( varlist )?
                    {
                    pushFollow(FOLLOW_relation_in_atom628);
                    relation42=relation();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_relation.add(relation42.getTree());
                    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:107:18: ( varlist )?
                    var alt11=2;
                    var LA11_0 = input.LA(1);

                    if ( (LA11_0==LPAREN) ) {
                        alt11=1;
                    }
                    alt11 match{
                        case 1 =>
                            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:0:0: varlist
                            {
                            pushFollow(FOLLOW_varlist_in_atom630);
                            varlist43=varlist();

                            state._fsp -= 1 
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_varlist.add(varlist43.getTree());

                            }
                        case _ => //Do nothing
                    }



                    // AST REWRITE
                    // elements: relation, varlist
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 107:28: -> ^( ATOM relation ( varlist )? )
                    {
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:108:13: ^( ATOM relation ( varlist )? )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(ATOM, "ATOM").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_relation.nextTree());
                        // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:108:29: ( varlist )?
                        if ( stream_varlist.hasNext() ) {
                            adaptor.addChild(root_1, stream_varlist.nextTree());

                        }
                        stream_varlist.reset();

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

      class varlist_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "varlist" rule(...)
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:111:1: varlist : LPAREN variable ( COMMA variable )* RPAREN -> ^( VARS ( variable )* ) ;
    @throws(classOf[RecognitionException])
     final def varlist():/*CREOLEParser.*/varlist_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/varlist_return  = new /*CREOLEParser.*/varlist_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN44: Token =null
        var COMMA46: Token =null
        var RPAREN48: Token =null
        var variable45:/*CREOLEParser.*/variable_return = null

        var variable47:/*CREOLEParser.*/variable_return = null


        var LPAREN44_tree:CHRTree=null
        var COMMA46_tree:CHRTree=null
        var RPAREN48_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_variable:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule variable");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:112:5: ( LPAREN variable ( COMMA variable )* RPAREN -> ^( VARS ( variable )* ) )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:112:9: LPAREN variable ( COMMA variable )* RPAREN
            {
            LPAREN44=smatch(input,LPAREN,FOLLOW_LPAREN_in_varlist674).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN44);

            pushFollow(FOLLOW_variable_in_varlist676);
            variable45=variable();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variable.add(variable45.getTree());
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:112:25: ( COMMA variable )*
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
            		    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:112:26: COMMA variable
            		    {
            		    COMMA46=smatch(input,COMMA,FOLLOW_COMMA_in_varlist679).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA46);

            		    pushFollow(FOLLOW_variable_in_varlist681);
            		    variable47=variable();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_variable.add(variable47.getTree());

            		    }
            		case _ =>
            		    guard = false //loop13;
                }
            } //while (true);

            RPAREN48=smatch(input,RPAREN,FOLLOW_RPAREN_in_varlist685).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN48);



            // AST REWRITE
            // elements: variable
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 112:50: -> ^( VARS ( variable )* )
            {
                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:112:55: ^( VARS ( variable )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(VARS, "VARS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:112:62: ( variable )*
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
    // $ANTLR end "varlist"

      class relation_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "relation" rule(...)
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:115:1: relation : R_ID ;
    @throws(classOf[RecognitionException])
     final def relation():/*CREOLEParser.*/relation_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/relation_return  = new /*CREOLEParser.*/relation_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var R_ID49: Token =null

        var R_ID49_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:116:5: ( R_ID )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:116:9: R_ID
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            R_ID49=smatch(input,R_ID,FOLLOW_R_ID_in_relation715).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            R_ID49_tree = adaptor.create(R_ID49).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, R_ID49_tree);
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
    // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:119:1: variable : ( V_ID | R_ID );
    @throws(classOf[RecognitionException])
     final def variable():/*CREOLEParser.*/variable_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/variable_return  = new /*CREOLEParser.*/variable_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var set50: Token =null

        var set50_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:120:5: ( V_ID | R_ID )
            // src/main/antlr3/fr/emn/creole/parser/CREOLE.g:
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            set50=input.LT(1).asInstanceOf[Token]
            if ( input.LA(1)==R_ID||input.LA(1)==V_ID ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, adaptor.create(set50).asInstanceOf[CHRTree]);
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

    // Delegated rules


 

      final val FOLLOW_process_in_start186:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_declaration_in_process205:BitSet = new BitSet( Array[Long](0x0000000702000000L))
      //new long[]{0x0000000702000000L});
      final val FOLLOW_script_in_process207:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_declaration240:BitSet = new BitSet( Array[Long](0x0800000000000000L))
      //new long[]{0x0800000000000000L});
      final val FOLLOW_59_in_declaration242:BitSet = new BitSet( Array[Long](0x0000000000100000L))
      //new long[]{0x0000000000100000L});
      final val FOLLOW_COLON_in_declaration244:BitSet = new BitSet( Array[Long](0x0000000007000000L))
      //new long[]{0x0000000007000000L});
      final val FOLLOW_rlist_in_declaration246:BitSet = new BitSet( Array[Long](0x0000000000200000L))
      //new long[]{0x0000000000200000L});
      final val FOLLOW_SEMI_in_declaration248:BitSet = new BitSet( Array[Long](0x1000000000000000L))
      //new long[]{0x1000000000000000L});
      final val FOLLOW_60_in_declaration250:BitSet = new BitSet( Array[Long](0x0000000000100000L))
      //new long[]{0x0000000000100000L});
      final val FOLLOW_COLON_in_declaration252:BitSet = new BitSet( Array[Long](0x0000000007000000L))
      //new long[]{0x0000000007000000L});
      final val FOLLOW_rlist_in_declaration254:BitSet = new BitSet( Array[Long](0x0000000000400000L))
      //new long[]{0x0000000000400000L});
      final val FOLLOW_RPAREN_in_declaration256:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_rdeclaration_in_rlist305:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_COMMA_in_rlist308:BitSet = new BitSet( Array[Long](0x0000000006000000L))
      //new long[]{0x0000000006000000L});
      final val FOLLOW_rdeclaration_in_rlist310:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_UNDEF_in_rlist327:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_rdeclaration350:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_TILDE_in_rdeclaration360:BitSet = new BitSet( Array[Long](0x0000000002000000L))
      //new long[]{0x0000000002000000L});
      final val FOLLOW_R_ID_in_rdeclaration362:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_rule_in_script394:BitSet = new BitSet( Array[Long](0x0000000702000002L))
      //new long[]{0x0000000702000002L});
      final val FOLLOW_conjunction_in_rule417:BitSet = new BitSet( Array[Long](0x0000000008000000L))
      //new long[]{0x0000000008000000L});
      final val FOLLOW_RARROW_in_rule419:BitSet = new BitSet( Array[Long](0x0000000792000000L))
      //new long[]{0x0000000792000000L});
      final val FOLLOW_guard_in_rule421:BitSet = new BitSet( Array[Long](0x0000000782000000L))
      //new long[]{0x0000000782000000L});
      final val FOLLOW_nu_in_rule424:BitSet = new BitSet( Array[Long](0x0000000702000000L))
      //new long[]{0x0000000702000000L});
      final val FOLLOW_conjunction_in_rule427:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LBRACK_in_guard483:BitSet = new BitSet( Array[Long](0x0000000722000000L))
      //new long[]{0x0000000722000000L});
      final val FOLLOW_rule_in_guard485:BitSet = new BitSet( Array[Long](0x0000000722000000L))
      //new long[]{0x0000000722000000L});
      final val FOLLOW_RBRACK_in_guard488:BitSet = new BitSet( Array[Long](0x0000000040000000L))
      //new long[]{0x0000000040000000L});
      final val FOLLOW_IMARK_in_guard490:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_NU_in_nu522:BitSet = new BitSet( Array[Long](0x0000000000080000L))
      //new long[]{0x0000000000080000L});
      final val FOLLOW_LPAREN_in_nu524:BitSet = new BitSet( Array[Long](0x0000000802000000L))
      //new long[]{0x0000000802000000L});
      final val FOLLOW_variable_in_nu526:BitSet = new BitSet( Array[Long](0x0000000000C00000L))
      //new long[]{0x0000000000C00000L});
      final val FOLLOW_COMMA_in_nu529:BitSet = new BitSet( Array[Long](0x0000000802000000L))
      //new long[]{0x0000000802000000L});
      final val FOLLOW_variable_in_nu531:BitSet = new BitSet( Array[Long](0x0000000000C00000L))
      //new long[]{0x0000000000C00000L});
      final val FOLLOW_RPAREN_in_nu535:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ZERO_in_conjunction563:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_atom_in_conjunction577:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_COMMA_in_conjunction580:BitSet = new BitSet( Array[Long](0x0000000702000000L))
      //new long[]{0x0000000702000000L});
      final val FOLLOW_atom_in_conjunction582:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_TRUE_in_atom608:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_FALSE_in_atom618:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_relation_in_atom628:BitSet = new BitSet( Array[Long](0x0000000000080002L))
      //new long[]{0x0000000000080002L});
      final val FOLLOW_varlist_in_atom630:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_varlist674:BitSet = new BitSet( Array[Long](0x0000000802000000L))
      //new long[]{0x0000000802000000L});
      final val FOLLOW_variable_in_varlist676:BitSet = new BitSet( Array[Long](0x0000000000C00000L))
      //new long[]{0x0000000000C00000L});
      final val FOLLOW_COMMA_in_varlist679:BitSet = new BitSet( Array[Long](0x0000000802000000L))
      //new long[]{0x0000000802000000L});
      final val FOLLOW_variable_in_varlist681:BitSet = new BitSet( Array[Long](0x0000000000C00000L))
      //new long[]{0x0000000000C00000L});
      final val FOLLOW_RPAREN_in_varlist685:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_relation715:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_set_in_variable0:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});

}