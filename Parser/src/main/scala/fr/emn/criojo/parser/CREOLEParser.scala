// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/main/antlr3/fr/emn/criojo/parser/CREOLE.g 2010-11-02 18:17:38

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
 class CREOLEParser(input: TokenStream, state:SRecognizerSharedState) 
 extends SParser(input,state) {
 
 	def this(input: TokenStream){
 		this(input, new SRecognizerSharedState())
 	}
 
    val tokenNames:Array[String] = Array[String] (
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SCRIPT", "ATOM", "VARS", "RULE", "HEAD", "BODY", "MULTI", "DECLARATION", "EMPTYLIST", "PUBLIC", "REQUIRED", "PRIVATE", "PROCESS", "GUARD", "EMPTY", "INT_ATOM", "LPAREN", "COLON", "SEMI", "RPAREN", "COMMA", "UNDEF", "R_ID", "TILDE", "RARROW", "LBRACK", "RBRACK", "IMARK", "NU", "ZERO", "TRUE", "FALSE", "V_ID", "LET", "IN", "EQ_OP", "LT", "LTEQ", "PLUS", "MINUS", "LCURL", "RCURL", "POINT", "SLASH", "BAR", "BANG", "INT", "COMMENT", "WS", "ESC_SEQ", "STRING", "CHAR", "EXPONENT", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "'provided'", "'local'", "'required'"
    )
      val EXPONENT:Int = 56;
      val LT:Int = 40;
      val LBRACK:Int = 29;
      val EQ_OP:Int = 39;
      val T__62:Int = 62;
      val POINT:Int = 46;
      val HEAD:Int = 8;
      val OCTAL_ESC:Int = 59;
      val LTEQ:Int = 41;
      val CHAR:Int = 55;
      val MULTI:Int = 10;
      val ATOM:Int = 5;
      val T__61:Int = 61;
      val EOF:Int = -1;
      val T__60:Int = 60;
      val DECLARATION:Int = 11;
      val LPAREN:Int = 20;
      val ZERO:Int = 33;
      val INT_ATOM:Int = 19;
      val RPAREN:Int = 23;
      val ESC_SEQ:Int = 53;
      val SLASH:Int = 47;
      val IN:Int = 38;
      val COMMA:Int = 24;
      val TILDE:Int = 27;
      val PLUS:Int = 42;
      val BODY:Int = 9;
      val UNDEF:Int = 25;
      val COMMENT:Int = 51;
      val IMARK:Int = 31;
      val RBRACK:Int = 30;
      val SCRIPT:Int = 4;
      val RULE:Int = 7;
      val NU:Int = 32;
      val R_ID:Int = 26;
      val PRIVATE:Int = 15;
      val VARS:Int = 6;
      val UNICODE_ESC:Int = 58;
      val RARROW:Int = 28;
      val HEX_DIGIT:Int = 57;
      val V_ID:Int = 36;
      val INT:Int = 50;
      val BANG:Int = 49;
      val MINUS:Int = 43;
      val REQUIRED:Int = 14;
      val SEMI:Int = 22;
      val TRUE:Int = 34;
      val EMPTY:Int = 18;
      val COLON:Int = 21;
      val LCURL:Int = 44;
      val WS:Int = 52;
      val RCURL:Int = 45;
      val EMPTYLIST:Int = 12;
      val GUARD:Int = 17;
      val PROCESS:Int = 16;
      val FALSE:Int = 35;
      val PUBLIC:Int = 13;
      val BAR:Int = 48;
      val LET:Int = 37;
      val STRING:Int = 54;

    // delegates
    // delegators


        
    protected var adaptor:TreeAdaptor = new CommonTreeAdaptor()

    def setTreeAdaptor(adaptor:TreeAdaptor) {
        this.adaptor = adaptor
    }
    def  getTreeAdaptor():TreeAdaptor = adaptor

    override def getTokenNames: Array[String] = /*CREOLEParser.*/tokenNames 
    override def getGrammarFileName(): String = "src/main/antlr3/fr/emn/criojo/parser/CREOLE.g"


    	def getCHRTreeTokens = new CHRTreeTokens(this.tokenNames)


      class start_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "start" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:42:1: start : process ;
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:43:5: ( process )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:43:9: process
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            pushFollow(FOLLOW_process_in_start193);
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:46:1: process : declaration script -> ^( PROCESS declaration ^( SCRIPT script ) ) ;
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:47:5: ( declaration script -> ^( PROCESS declaration ^( SCRIPT script ) ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:47:9: declaration script
            {
            pushFollow(FOLLOW_declaration_in_process212);
            declaration2=declaration();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declaration.add(declaration2.getTree());
            pushFollow(FOLLOW_script_in_process214);
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
            // 47:28: -> ^( PROCESS declaration ^( SCRIPT script ) )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:47:31: ^( PROCESS declaration ^( SCRIPT script ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(PROCESS, "PROCESS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                adaptor.addChild(root_1, stream_declaration.nextTree());
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:47:53: ^( SCRIPT script )
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:50:1: declaration : LPAREN 'provided' COLON rlist SEMI 'local' COLON rlist SEMI 'required' COLON rlist RPAREN -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) ^( REQUIRED rlist ) ) ;
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
        var SEMI12: Token =null
        var string_literal13: Token =null
        var COLON14: Token =null
        var RPAREN16: Token =null
        var rlist7:/*CREOLEParser.*/rlist_return = null

        var rlist11:/*CREOLEParser.*/rlist_return = null

        var rlist15:/*CREOLEParser.*/rlist_return = null


        var LPAREN4_tree:CHRTree=null
        var string_literal5_tree:CHRTree=null
        var COLON6_tree:CHRTree=null
        var SEMI8_tree:CHRTree=null
        var string_literal9_tree:CHRTree=null
        var COLON10_tree:CHRTree=null
        var SEMI12_tree:CHRTree=null
        var string_literal13_tree:CHRTree=null
        var COLON14_tree:CHRTree=null
        var RPAREN16_tree:CHRTree=null
        var stream_COLON:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COLON")
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_62:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 62")
        var stream_SEMI:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SEMI")
        var stream_60:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 60")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_61:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 61")
        var stream_rlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rlist");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:51:5: ( LPAREN 'provided' COLON rlist SEMI 'local' COLON rlist SEMI 'required' COLON rlist RPAREN -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) ^( REQUIRED rlist ) ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:51:9: LPAREN 'provided' COLON rlist SEMI 'local' COLON rlist SEMI 'required' COLON rlist RPAREN
            {
            LPAREN4=smatch(input,LPAREN,FOLLOW_LPAREN_in_declaration247).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN4);

            string_literal5=smatch(input,60,FOLLOW_60_in_declaration249).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_60.add(string_literal5);

            COLON6=smatch(input,COLON,FOLLOW_COLON_in_declaration251).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON6);

            pushFollow(FOLLOW_rlist_in_declaration253);
            rlist7=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist7.getTree());
            SEMI8=smatch(input,SEMI,FOLLOW_SEMI_in_declaration255).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI8);

            string_literal9=smatch(input,61,FOLLOW_61_in_declaration257).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_61.add(string_literal9);

            COLON10=smatch(input,COLON,FOLLOW_COLON_in_declaration259).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON10);

            pushFollow(FOLLOW_rlist_in_declaration261);
            rlist11=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist11.getTree());
            SEMI12=smatch(input,SEMI,FOLLOW_SEMI_in_declaration263).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI12);

            string_literal13=smatch(input,62,FOLLOW_62_in_declaration265).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_62.add(string_literal13);

            COLON14=smatch(input,COLON,FOLLOW_COLON_in_declaration267).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON14);

            pushFollow(FOLLOW_rlist_in_declaration269);
            rlist15=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist15.getTree());
            RPAREN16=smatch(input,RPAREN,FOLLOW_RPAREN_in_declaration271).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN16);



            // AST REWRITE
            // elements: rlist, rlist, rlist
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 51:99: -> ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) ^( REQUIRED rlist ) )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:52:13: ^( DECLARATION ^( PUBLIC rlist ) ^( PRIVATE rlist ) ^( REQUIRED rlist ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(DECLARATION, "DECLARATION").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:52:27: ^( PUBLIC rlist )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(PUBLIC, "PUBLIC").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_rlist.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:52:43: ^( PRIVATE rlist )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(PRIVATE, "PRIVATE").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_rlist.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:52:60: ^( REQUIRED rlist )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(REQUIRED, "REQUIRED").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:55:1: rlist : ( rdeclaration ( COMMA rdeclaration )* -> ( rdeclaration )* | UNDEF -> EMPTYLIST );
    @throws(classOf[RecognitionException])
     final def rlist():/*CREOLEParser.*/rlist_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/rlist_return  = new /*CREOLEParser.*/rlist_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var COMMA18: Token =null
        var UNDEF20: Token =null
        var rdeclaration17:/*CREOLEParser.*/rdeclaration_return = null

        var rdeclaration19:/*CREOLEParser.*/rdeclaration_return = null


        var COMMA18_tree:CHRTree=null
        var UNDEF20_tree:CHRTree=null
        var stream_UNDEF:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token UNDEF")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_rdeclaration:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rdeclaration");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:56:5: ( rdeclaration ( COMMA rdeclaration )* -> ( rdeclaration )* | UNDEF -> EMPTYLIST )
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:56:9: rdeclaration ( COMMA rdeclaration )*
                    {
                    pushFollow(FOLLOW_rdeclaration_in_rlist327);
                    rdeclaration17=rdeclaration();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rdeclaration.add(rdeclaration17.getTree());
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:56:22: ( COMMA rdeclaration )*
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
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:56:23: COMMA rdeclaration
                    		    {
                    		    COMMA18=smatch(input,COMMA,FOLLOW_COMMA_in_rlist330).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA18);

                    		    pushFollow(FOLLOW_rdeclaration_in_rlist332);
                    		    rdeclaration19=rdeclaration();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_rdeclaration.add(rdeclaration19.getTree());

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
                    // 56:44: -> ( rdeclaration )*
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:56:47: ( rdeclaration )*
                        while ( stream_rdeclaration.hasNext() ) {
                            adaptor.addChild(root_0, stream_rdeclaration.nextTree());

                        }
                        stream_rdeclaration.reset();

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:57:9: UNDEF
                    {
                    UNDEF20=smatch(input,UNDEF,FOLLOW_UNDEF_in_rlist349).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UNDEF.add(UNDEF20);



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
                    // 57:15: -> EMPTYLIST
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:60:1: rdeclaration : ( R_ID | TILDE R_ID -> ^( MULTI R_ID ) );
    @throws(classOf[RecognitionException])
     final def rdeclaration():/*CREOLEParser.*/rdeclaration_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/rdeclaration_return  = new /*CREOLEParser.*/rdeclaration_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var R_ID21: Token =null
        var TILDE22: Token =null
        var R_ID23: Token =null

        var R_ID21_tree:CHRTree=null
        var TILDE22_tree:CHRTree=null
        var R_ID23_tree:CHRTree=null
        var stream_R_ID:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token R_ID")
        var stream_TILDE:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token TILDE")

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:61:5: ( R_ID | TILDE R_ID -> ^( MULTI R_ID ) )
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:61:9: R_ID
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    R_ID21=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration372).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    R_ID21_tree = adaptor.create(R_ID21).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, R_ID21_tree);
                    }

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:62:9: TILDE R_ID
                    {
                    TILDE22=smatch(input,TILDE,FOLLOW_TILDE_in_rdeclaration382).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TILDE.add(TILDE22);

                    R_ID23=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration384).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_R_ID.add(R_ID23);



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
                    // 62:20: -> ^( MULTI R_ID )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:62:23: ^( MULTI R_ID )
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:65:1: script : ( rule )* ;
    @throws(classOf[RecognitionException])
     final def script():/*CREOLEParser.*/script_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/script_return  = new /*CREOLEParser.*/script_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var rule24:/*CREOLEParser.*/rule_return = null



        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:66:5: ( ( rule )* )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:67:8: ( rule )*
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:67:8: ( rule )*
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
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:0:0: rule
            		    {
            		    pushFollow(FOLLOW_rule_in_script416);
            		    rule24=rule();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) adaptor.addChild(root_0, rule24.getTree());

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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:87:1: rule : conjunction RARROW ( guard )? ( nu )? conjunction -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? ) ;
    @throws(classOf[RecognitionException])
     final def rule():/*CREOLEParser.*/rule_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/rule_return  = new /*CREOLEParser.*/rule_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var RARROW26: Token =null
        var conjunction25:/*CREOLEParser.*/conjunction_return = null

        var guard27:/*CREOLEParser.*/guard_return = null

        var nu28:/*CREOLEParser.*/nu_return = null

        var conjunction29:/*CREOLEParser.*/conjunction_return = null


        var RARROW26_tree:CHRTree=null
        var stream_RARROW:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RARROW")
        var stream_nu:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule nu");
        var stream_guard:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule guard");
        var stream_conjunction:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule conjunction");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:88:5: ( conjunction RARROW ( guard )? ( nu )? conjunction -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:88:9: conjunction RARROW ( guard )? ( nu )? conjunction
            {
            pushFollow(FOLLOW_conjunction_in_rule439);
            conjunction25=conjunction();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_conjunction.add(conjunction25.getTree());
            RARROW26=smatch(input,RARROW,FOLLOW_RARROW_in_rule441).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RARROW.add(RARROW26);

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:88:28: ( guard )?
            var alt5=2;
            var LA5_0 = input.LA(1);

            if ( (LA5_0==LBRACK) ) {
                alt5=1;
            }
            alt5 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:0:0: guard
                    {
                    pushFollow(FOLLOW_guard_in_rule443);
                    guard27=guard();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_guard.add(guard27.getTree());

                    }
                case _ => //Do nothing
            }

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:88:35: ( nu )?
            var alt6=2;
            var LA6_0 = input.LA(1);

            if ( (LA6_0==NU) ) {
                alt6=1;
            }
            alt6 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:0:0: nu
                    {
                    pushFollow(FOLLOW_nu_in_rule446);
                    nu28=nu();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_nu.add(nu28.getTree());

                    }
                case _ => //Do nothing
            }

            pushFollow(FOLLOW_conjunction_in_rule449);
            conjunction29=conjunction();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_conjunction.add(conjunction29.getTree());


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
            // 88:52: -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:89:13: ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(RULE, "RULE").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:89:20: ( guard )?
                if ( stream_guard.hasNext() ) {
                    adaptor.addChild(root_1, stream_guard.nextTree());

                }
                stream_guard.reset();
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:89:27: ^( HEAD conjunction )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(HEAD, "HEAD").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_conjunction.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:89:47: ^( BODY conjunction )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(BODY, "BODY").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_conjunction.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:89:67: ( nu )?
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:92:1: guard : LBRACK ( rule )* RBRACK IMARK -> ^( GUARD ( rule )* ) ;
    @throws(classOf[RecognitionException])
     final def guard():/*CREOLEParser.*/guard_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/guard_return  = new /*CREOLEParser.*/guard_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LBRACK30: Token =null
        var RBRACK32: Token =null
        var IMARK33: Token =null
        var rule31:/*CREOLEParser.*/rule_return = null


        var LBRACK30_tree:CHRTree=null
        var RBRACK32_tree:CHRTree=null
        var IMARK33_tree:CHRTree=null
        var stream_RBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RBRACK")
        var stream_LBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LBRACK")
        var stream_IMARK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token IMARK")
        var stream_rule:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rule");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:93:5: ( LBRACK ( rule )* RBRACK IMARK -> ^( GUARD ( rule )* ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:93:9: LBRACK ( rule )* RBRACK IMARK
            {
            LBRACK30=smatch(input,LBRACK,FOLLOW_LBRACK_in_guard505).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK30);

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:93:16: ( rule )*
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
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:0:0: rule
            		    {
            		    pushFollow(FOLLOW_rule_in_guard507);
            		    rule31=rule();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_rule.add(rule31.getTree());

            		    }
            		case _ =>
            		    guard = false //loop7;
                }
            } //while (true);

            RBRACK32=smatch(input,RBRACK,FOLLOW_RBRACK_in_guard510).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK32);

            IMARK33=smatch(input,IMARK,FOLLOW_IMARK_in_guard512).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IMARK.add(IMARK33);



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
            // 93:35: -> ^( GUARD ( rule )* )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:93:38: ^( GUARD ( rule )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(GUARD, "GUARD").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:93:46: ( rule )*
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:96:1: nu : NU LPAREN variable ( COMMA variable )* RPAREN -> ^( NU ( variable )* ) ;
    @throws(classOf[RecognitionException])
     final def nu():/*CREOLEParser.*/nu_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/nu_return  = new /*CREOLEParser.*/nu_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var NU34: Token =null
        var LPAREN35: Token =null
        var COMMA37: Token =null
        var RPAREN39: Token =null
        var variable36:/*CREOLEParser.*/variable_return = null

        var variable38:/*CREOLEParser.*/variable_return = null


        var NU34_tree:CHRTree=null
        var LPAREN35_tree:CHRTree=null
        var COMMA37_tree:CHRTree=null
        var RPAREN39_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_NU:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token NU")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_variable:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule variable");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:97:5: ( NU LPAREN variable ( COMMA variable )* RPAREN -> ^( NU ( variable )* ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:97:9: NU LPAREN variable ( COMMA variable )* RPAREN
            {
            NU34=smatch(input,NU,FOLLOW_NU_in_nu544).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NU.add(NU34);

            LPAREN35=smatch(input,LPAREN,FOLLOW_LPAREN_in_nu546).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN35);

            pushFollow(FOLLOW_variable_in_nu548);
            variable36=variable();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variable.add(variable36.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:97:28: ( COMMA variable )*
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
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:97:29: COMMA variable
            		    {
            		    COMMA37=smatch(input,COMMA,FOLLOW_COMMA_in_nu551).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA37);

            		    pushFollow(FOLLOW_variable_in_nu553);
            		    variable38=variable();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_variable.add(variable38.getTree());

            		    }
            		case _ =>
            		    guard = false //loop8;
                }
            } //while (true);

            RPAREN39=smatch(input,RPAREN,FOLLOW_RPAREN_in_nu557).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN39);



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
            // 97:53: -> ^( NU ( variable )* )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:97:56: ^( NU ( variable )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(stream_NU.nextNode(), root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:97:61: ( variable )*
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:100:1: conjunction : ( ZERO -> EMPTY | atom ( COMMA atom )* -> ( atom )* );
    @throws(classOf[RecognitionException])
     final def conjunction():/*CREOLEParser.*/conjunction_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/conjunction_return  = new /*CREOLEParser.*/conjunction_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var ZERO40: Token =null
        var COMMA42: Token =null
        var atom41:/*CREOLEParser.*/atom_return = null

        var atom43:/*CREOLEParser.*/atom_return = null


        var ZERO40_tree:CHRTree=null
        var COMMA42_tree:CHRTree=null
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_ZERO:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ZERO")
        var stream_atom:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule atom");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:101:5: ( ZERO -> EMPTY | atom ( COMMA atom )* -> ( atom )* )
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:101:9: ZERO
                    {
                    ZERO40=smatch(input,ZERO,FOLLOW_ZERO_in_conjunction585).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ZERO.add(ZERO40);



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
                    // 101:14: -> EMPTY
                    {
                        adaptor.addChild(root_0, adaptor.create(EMPTY, "EMPTY").asInstanceOf[CHRTree]);

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:102:9: atom ( COMMA atom )*
                    {
                    pushFollow(FOLLOW_atom_in_conjunction599);
                    atom41=atom();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom41.getTree());
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:102:14: ( COMMA atom )*
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
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:102:15: COMMA atom
                    		    {
                    		    COMMA42=smatch(input,COMMA,FOLLOW_COMMA_in_conjunction602).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA42);

                    		    pushFollow(FOLLOW_atom_in_conjunction604);
                    		    atom43=atom();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_atom.add(atom43.getTree());

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
                    // 102:28: -> ( atom )*
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:102:31: ( atom )*
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:105:1: atom : ( TRUE | FALSE | relation ( varlist )? -> ^( ATOM relation ( varlist )? ) );
    @throws(classOf[RecognitionException])
     final def atom():/*CREOLEParser.*/atom_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/atom_return  = new /*CREOLEParser.*/atom_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var TRUE44: Token =null
        var FALSE45: Token =null
        var relation46:/*CREOLEParser.*/relation_return = null

        var varlist47:/*CREOLEParser.*/varlist_return = null


        var TRUE44_tree:CHRTree=null
        var FALSE45_tree:CHRTree=null
        var stream_varlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule varlist");
        var stream_relation:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule relation");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:106:5: ( TRUE | FALSE | relation ( varlist )? -> ^( ATOM relation ( varlist )? ) )
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:106:9: TRUE
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    TRUE44=smatch(input,TRUE,FOLLOW_TRUE_in_atom630).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TRUE44_tree = adaptor.create(TRUE44).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, TRUE44_tree);
                    }

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:107:9: FALSE
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    FALSE45=smatch(input,FALSE,FOLLOW_FALSE_in_atom640).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FALSE45_tree = adaptor.create(FALSE45).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, FALSE45_tree);
                    }

                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:108:9: relation ( varlist )?
                    {
                    pushFollow(FOLLOW_relation_in_atom650);
                    relation46=relation();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_relation.add(relation46.getTree());
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:108:18: ( varlist )?
                    var alt11=2;
                    var LA11_0 = input.LA(1);

                    if ( (LA11_0==LPAREN) ) {
                        alt11=1;
                    }
                    alt11 match{
                        case 1 =>
                            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:0:0: varlist
                            {
                            pushFollow(FOLLOW_varlist_in_atom652);
                            varlist47=varlist();

                            state._fsp -= 1 
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_varlist.add(varlist47.getTree());

                            }
                        case _ => //Do nothing
                    }



                    // AST REWRITE
                    // elements: varlist, relation
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 108:28: -> ^( ATOM relation ( varlist )? )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:109:13: ^( ATOM relation ( varlist )? )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(ATOM, "ATOM").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_relation.nextTree());
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:109:29: ( varlist )?
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:112:1: varlist : LPAREN variable ( COMMA variable )* RPAREN -> ^( VARS ( variable )* ) ;
    @throws(classOf[RecognitionException])
     final def varlist():/*CREOLEParser.*/varlist_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/varlist_return  = new /*CREOLEParser.*/varlist_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN48: Token =null
        var COMMA50: Token =null
        var RPAREN52: Token =null
        var variable49:/*CREOLEParser.*/variable_return = null

        var variable51:/*CREOLEParser.*/variable_return = null


        var LPAREN48_tree:CHRTree=null
        var COMMA50_tree:CHRTree=null
        var RPAREN52_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_variable:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule variable");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:113:5: ( LPAREN variable ( COMMA variable )* RPAREN -> ^( VARS ( variable )* ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:113:9: LPAREN variable ( COMMA variable )* RPAREN
            {
            LPAREN48=smatch(input,LPAREN,FOLLOW_LPAREN_in_varlist696).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN48);

            pushFollow(FOLLOW_variable_in_varlist698);
            variable49=variable();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variable.add(variable49.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:113:25: ( COMMA variable )*
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
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:113:26: COMMA variable
            		    {
            		    COMMA50=smatch(input,COMMA,FOLLOW_COMMA_in_varlist701).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA50);

            		    pushFollow(FOLLOW_variable_in_varlist703);
            		    variable51=variable();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_variable.add(variable51.getTree());

            		    }
            		case _ =>
            		    guard = false //loop13;
                }
            } //while (true);

            RPAREN52=smatch(input,RPAREN,FOLLOW_RPAREN_in_varlist707).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN52);



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
            // 113:50: -> ^( VARS ( variable )* )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:113:55: ^( VARS ( variable )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(VARS, "VARS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:113:62: ( variable )*
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:116:1: relation : R_ID ;
    @throws(classOf[RecognitionException])
     final def relation():/*CREOLEParser.*/relation_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/relation_return  = new /*CREOLEParser.*/relation_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var R_ID53: Token =null

        var R_ID53_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:117:5: ( R_ID )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:117:9: R_ID
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            R_ID53=smatch(input,R_ID,FOLLOW_R_ID_in_relation737).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            R_ID53_tree = adaptor.create(R_ID53).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, R_ID53_tree);
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:120:1: variable : ( V_ID | R_ID );
    @throws(classOf[RecognitionException])
     final def variable():/*CREOLEParser.*/variable_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLEParser.*/variable_return  = new /*CREOLEParser.*/variable_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var set54: Token =null

        var set54_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:121:5: ( V_ID | R_ID )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE.g:
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            set54=input.LT(1).asInstanceOf[Token]
            if ( input.LA(1)==R_ID||input.LA(1)==V_ID ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, adaptor.create(set54).asInstanceOf[CHRTree]);
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


 

      final val FOLLOW_process_in_start193:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_declaration_in_process212:BitSet = new BitSet( Array[Long](0x0000000E04000000L))
      //new long[]{0x0000000E04000000L});
      final val FOLLOW_script_in_process214:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_declaration247:BitSet = new BitSet( Array[Long](0x1000000000000000L))
      //new long[]{0x1000000000000000L});
      final val FOLLOW_60_in_declaration249:BitSet = new BitSet( Array[Long](0x0000000000200000L))
      //new long[]{0x0000000000200000L});
      final val FOLLOW_COLON_in_declaration251:BitSet = new BitSet( Array[Long](0x000000000E000000L))
      //new long[]{0x000000000E000000L});
      final val FOLLOW_rlist_in_declaration253:BitSet = new BitSet( Array[Long](0x0000000000400000L))
      //new long[]{0x0000000000400000L});
      final val FOLLOW_SEMI_in_declaration255:BitSet = new BitSet( Array[Long](0x2000000000000000L))
      //new long[]{0x2000000000000000L});
      final val FOLLOW_61_in_declaration257:BitSet = new BitSet( Array[Long](0x0000000000200000L))
      //new long[]{0x0000000000200000L});
      final val FOLLOW_COLON_in_declaration259:BitSet = new BitSet( Array[Long](0x000000000E000000L))
      //new long[]{0x000000000E000000L});
      final val FOLLOW_rlist_in_declaration261:BitSet = new BitSet( Array[Long](0x0000000000400000L))
      //new long[]{0x0000000000400000L});
      final val FOLLOW_SEMI_in_declaration263:BitSet = new BitSet( Array[Long](0x4000000000000000L))
      //new long[]{0x4000000000000000L});
      final val FOLLOW_62_in_declaration265:BitSet = new BitSet( Array[Long](0x0000000000200000L))
      //new long[]{0x0000000000200000L});
      final val FOLLOW_COLON_in_declaration267:BitSet = new BitSet( Array[Long](0x000000000E000000L))
      //new long[]{0x000000000E000000L});
      final val FOLLOW_rlist_in_declaration269:BitSet = new BitSet( Array[Long](0x0000000000800000L))
      //new long[]{0x0000000000800000L});
      final val FOLLOW_RPAREN_in_declaration271:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_rdeclaration_in_rlist327:BitSet = new BitSet( Array[Long](0x0000000001000002L))
      //new long[]{0x0000000001000002L});
      final val FOLLOW_COMMA_in_rlist330:BitSet = new BitSet( Array[Long](0x000000000C000000L))
      //new long[]{0x000000000C000000L});
      final val FOLLOW_rdeclaration_in_rlist332:BitSet = new BitSet( Array[Long](0x0000000001000002L))
      //new long[]{0x0000000001000002L});
      final val FOLLOW_UNDEF_in_rlist349:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_rdeclaration372:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_TILDE_in_rdeclaration382:BitSet = new BitSet( Array[Long](0x0000000004000000L))
      //new long[]{0x0000000004000000L});
      final val FOLLOW_R_ID_in_rdeclaration384:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_rule_in_script416:BitSet = new BitSet( Array[Long](0x0000000E04000002L))
      //new long[]{0x0000000E04000002L});
      final val FOLLOW_conjunction_in_rule439:BitSet = new BitSet( Array[Long](0x0000000010000000L))
      //new long[]{0x0000000010000000L});
      final val FOLLOW_RARROW_in_rule441:BitSet = new BitSet( Array[Long](0x0000000F24000000L))
      //new long[]{0x0000000F24000000L});
      final val FOLLOW_guard_in_rule443:BitSet = new BitSet( Array[Long](0x0000000F04000000L))
      //new long[]{0x0000000F04000000L});
      final val FOLLOW_nu_in_rule446:BitSet = new BitSet( Array[Long](0x0000000E04000000L))
      //new long[]{0x0000000E04000000L});
      final val FOLLOW_conjunction_in_rule449:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LBRACK_in_guard505:BitSet = new BitSet( Array[Long](0x0000000E44000000L))
      //new long[]{0x0000000E44000000L});
      final val FOLLOW_rule_in_guard507:BitSet = new BitSet( Array[Long](0x0000000E44000000L))
      //new long[]{0x0000000E44000000L});
      final val FOLLOW_RBRACK_in_guard510:BitSet = new BitSet( Array[Long](0x0000000080000000L))
      //new long[]{0x0000000080000000L});
      final val FOLLOW_IMARK_in_guard512:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_NU_in_nu544:BitSet = new BitSet( Array[Long](0x0000000000100000L))
      //new long[]{0x0000000000100000L});
      final val FOLLOW_LPAREN_in_nu546:BitSet = new BitSet( Array[Long](0x0000001004000000L))
      //new long[]{0x0000001004000000L});
      final val FOLLOW_variable_in_nu548:BitSet = new BitSet( Array[Long](0x0000000001800000L))
      //new long[]{0x0000000001800000L});
      final val FOLLOW_COMMA_in_nu551:BitSet = new BitSet( Array[Long](0x0000001004000000L))
      //new long[]{0x0000001004000000L});
      final val FOLLOW_variable_in_nu553:BitSet = new BitSet( Array[Long](0x0000000001800000L))
      //new long[]{0x0000000001800000L});
      final val FOLLOW_RPAREN_in_nu557:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ZERO_in_conjunction585:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_atom_in_conjunction599:BitSet = new BitSet( Array[Long](0x0000000001000002L))
      //new long[]{0x0000000001000002L});
      final val FOLLOW_COMMA_in_conjunction602:BitSet = new BitSet( Array[Long](0x0000000E04000000L))
      //new long[]{0x0000000E04000000L});
      final val FOLLOW_atom_in_conjunction604:BitSet = new BitSet( Array[Long](0x0000000001000002L))
      //new long[]{0x0000000001000002L});
      final val FOLLOW_TRUE_in_atom630:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_FALSE_in_atom640:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_relation_in_atom650:BitSet = new BitSet( Array[Long](0x0000000000100002L))
      //new long[]{0x0000000000100002L});
      final val FOLLOW_varlist_in_atom652:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_varlist696:BitSet = new BitSet( Array[Long](0x0000001004000000L))
      //new long[]{0x0000001004000000L});
      final val FOLLOW_variable_in_varlist698:BitSet = new BitSet( Array[Long](0x0000000001800000L))
      //new long[]{0x0000000001800000L});
      final val FOLLOW_COMMA_in_varlist701:BitSet = new BitSet( Array[Long](0x0000001004000000L))
      //new long[]{0x0000001004000000L});
      final val FOLLOW_variable_in_varlist703:BitSet = new BitSet( Array[Long](0x0000000001800000L))
      //new long[]{0x0000000001800000L});
      final val FOLLOW_RPAREN_in_varlist707:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_relation737:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_set_in_variable0:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});

}