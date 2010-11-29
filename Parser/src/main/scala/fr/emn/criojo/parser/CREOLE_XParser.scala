// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g 2010-11-04 15:22:43

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SCRIPT", "ATOM", "VARS", "RULE", "HEAD", "BODY", "MULTI", "DECLARATION", "EMPTYLIST", "PUBLIC", "PRIVATE", "REQUIRED", "PROCESS", "GUARD", "EMPTY", "INT_ATOM", "STR_ATOM", "NULL_ATOM", "LPAREN", "SEMI", "RPAREN", "COLON", "COMMA", "UNDEF", "R_ID", "ARROBAS", "TILDE", "BAR", "BANG", "RARROW", "LBRACK", "RBRACK", "IMARK", "ABS", "EQ", "NOT", "NU", "TRUE", "FALSE", "NULL", "V_ID", "INT", "STRING", "LT", "LTEQ", "PLUS", "MINUS", "LCURL", "RCURL", "POINT", "SLASH", "COMMENT", "WS", "ESC_SEQ", "CHAR", "EXPONENT", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "'provided'", "'required'", "'local'", "'Null'", "'T'"
    )
      val T__66:Int = 66;
      val NULL_ATOM:Int = 21;
      val EXPONENT:Int = 59;
      val LT:Int = 47;
      val T__67:Int = 67;
      val T__64:Int = 64;
      val LBRACK:Int = 34;
      val T__65:Int = 65;
      val T__63:Int = 63;
      val POINT:Int = 53;
      val HEAD:Int = 8;
      val OCTAL_ESC:Int = 62;
      val LTEQ:Int = 48;
      val CHAR:Int = 58;
      val MULTI:Int = 10;
      val NOT:Int = 39;
      val ATOM:Int = 5;
      val EOF:Int = -1;
      val DECLARATION:Int = 11;
      val LPAREN:Int = 22;
      val ARROBAS:Int = 29;
      val INT_ATOM:Int = 19;
      val RPAREN:Int = 24;
      val ESC_SEQ:Int = 57;
      val SLASH:Int = 54;
      val COMMA:Int = 26;
      val TILDE:Int = 30;
      val PLUS:Int = 49;
      val BODY:Int = 9;
      val UNDEF:Int = 27;
      val EQ:Int = 38;
      val COMMENT:Int = 55;
      val IMARK:Int = 36;
      val RBRACK:Int = 35;
      val SCRIPT:Int = 4;
      val RULE:Int = 7;
      val NU:Int = 40;
      val R_ID:Int = 28;
      val PRIVATE:Int = 14;
      val VARS:Int = 6;
      val UNICODE_ESC:Int = 61;
      val NULL:Int = 43;
      val RARROW:Int = 33;
      val HEX_DIGIT:Int = 60;
      val V_ID:Int = 44;
      val STR_ATOM:Int = 20;
      val INT:Int = 45;
      val BANG:Int = 32;
      val MINUS:Int = 50;
      val REQUIRED:Int = 15;
      val TRUE:Int = 41;
      val SEMI:Int = 23;
      val EMPTY:Int = 18;
      val COLON:Int = 25;
      val ABS:Int = 37;
      val LCURL:Int = 51;
      val WS:Int = 56;
      val RCURL:Int = 52;
      val EMPTYLIST:Int = 12;
      val GUARD:Int = 17;
      val PROCESS:Int = 16;
      val FALSE:Int = 42;
      val PUBLIC:Int = 13;
      val BAR:Int = 31;
      val STRING:Int = 46;

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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:45:1: start : process ;
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:46:5: ( process )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:46:9: process
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:49:1: process : declaration script -> ^( PROCESS declaration ^( SCRIPT script ) ) ;
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
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:50:5: ( declaration script -> ^( PROCESS declaration ^( SCRIPT script ) ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:50:9: declaration script
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
            // 50:28: -> ^( PROCESS declaration ^( SCRIPT script ) )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:50:31: ^( PROCESS declaration ^( SCRIPT script ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(PROCESS, "PROCESS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                adaptor.addChild(root_1, stream_declaration.nextTree());
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:50:53: ^( SCRIPT script )
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:53:1: declaration : LPAREN decl ( SEMI decl )* RPAREN -> ^( DECLARATION ( decl )* ) ;
    @throws(classOf[RecognitionException])
     final def declaration():/*CREOLE_XParser.*/declaration_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/declaration_return  = new /*CREOLE_XParser.*/declaration_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN4: Token =null
        var SEMI6: Token =null
        var RPAREN8: Token =null
        var decl5:/*CREOLE_XParser.*/decl_return = null

        var decl7:/*CREOLE_XParser.*/decl_return = null


        var LPAREN4_tree:CHRTree=null
        var SEMI6_tree:CHRTree=null
        var RPAREN8_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_SEMI:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SEMI")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_decl:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule decl");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:54:5: ( LPAREN decl ( SEMI decl )* RPAREN -> ^( DECLARATION ( decl )* ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:54:9: LPAREN decl ( SEMI decl )* RPAREN
            {
            LPAREN4=smatch(input,LPAREN,FOLLOW_LPAREN_in_declaration247).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN4);

            pushFollow(FOLLOW_decl_in_declaration249);
            decl5=decl();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_decl.add(decl5.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:54:21: ( SEMI decl )*
            //loop1:
            var guard = true
            while(guard) {
                var alt1=2;
                var LA1_0 = input.LA(1);

                if ( (LA1_0==SEMI) ) {
                    alt1=1;
                }


                alt1 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:54:22: SEMI decl
            		    {
            		    SEMI6=smatch(input,SEMI,FOLLOW_SEMI_in_declaration252).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_SEMI.add(SEMI6);

            		    pushFollow(FOLLOW_decl_in_declaration254);
            		    decl7=decl();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_decl.add(decl7.getTree());

            		    }
            		case _ =>
            		    guard = false //loop1;
                }
            } //while (true);

            RPAREN8=smatch(input,RPAREN,FOLLOW_RPAREN_in_declaration258).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN8);



            // AST REWRITE
            // elements: decl
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 54:41: -> ^( DECLARATION ( decl )* )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:54:44: ^( DECLARATION ( decl )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(DECLARATION, "DECLARATION").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:54:58: ( decl )*
                while ( stream_decl.hasNext() ) {
                    adaptor.addChild(root_1, stream_decl.nextTree());

                }
                stream_decl.reset();

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

      class decl_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "decl" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:57:1: decl : ( dec_prov | dec_req | dec_loc );
    @throws(classOf[RecognitionException])
     final def decl():/*CREOLE_XParser.*/decl_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/decl_return  = new /*CREOLE_XParser.*/decl_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var dec_prov9:/*CREOLE_XParser.*/dec_prov_return = null

        var dec_req10:/*CREOLE_XParser.*/dec_req_return = null

        var dec_loc11:/*CREOLE_XParser.*/dec_loc_return = null



        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:58:5: ( dec_prov | dec_req | dec_loc )
            var alt2=3;
            input.LA(1) match{
            	case 63 => alt2=1;    
            	case 64 => alt2=2;    
            	case 65 => alt2=3;    
            	case _ =>
            		    if (state.backtracking>0) {state.failed=true; return retval;}
            		    val nvae =
            		        new NoViableAltException("", 2, 0, input);

            		    throw nvae;

            }

            alt2 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:58:9: dec_prov
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_dec_prov_in_decl286);
                    dec_prov9=dec_prov();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, dec_prov9.getTree());

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:58:20: dec_req
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_dec_req_in_decl290);
                    dec_req10=dec_req();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, dec_req10.getTree());

                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:58:30: dec_loc
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_dec_loc_in_decl294);
                    dec_loc11=dec_loc();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, dec_loc11.getTree());

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
    // $ANTLR end "decl"

      class dec_prov_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "dec_prov" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:61:1: dec_prov : 'provided' COLON rlist -> ^( PUBLIC rlist ) ;
    @throws(classOf[RecognitionException])
     final def dec_prov():/*CREOLE_XParser.*/dec_prov_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/dec_prov_return  = new /*CREOLE_XParser.*/dec_prov_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var string_literal12: Token =null
        var COLON13: Token =null
        var rlist14:/*CREOLE_XParser.*/rlist_return = null


        var string_literal12_tree:CHRTree=null
        var COLON13_tree:CHRTree=null
        var stream_COLON:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COLON")
        var stream_63:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 63")
        var stream_rlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rlist");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:62:5: ( 'provided' COLON rlist -> ^( PUBLIC rlist ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:62:9: 'provided' COLON rlist
            {
            string_literal12=smatch(input,63,FOLLOW_63_in_dec_prov313).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_63.add(string_literal12);

            COLON13=smatch(input,COLON,FOLLOW_COLON_in_dec_prov315).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON13);

            pushFollow(FOLLOW_rlist_in_dec_prov317);
            rlist14=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist14.getTree());


            // AST REWRITE
            // elements: rlist
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 62:32: -> ^( PUBLIC rlist )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:62:35: ^( PUBLIC rlist )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(PUBLIC, "PUBLIC").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                adaptor.addChild(root_1, stream_rlist.nextTree());

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
    // $ANTLR end "dec_prov"

      class dec_req_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "dec_req" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:65:1: dec_req : 'required' COLON rlist -> ^( REQUIRED rlist ) ;
    @throws(classOf[RecognitionException])
     final def dec_req():/*CREOLE_XParser.*/dec_req_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/dec_req_return  = new /*CREOLE_XParser.*/dec_req_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var string_literal15: Token =null
        var COLON16: Token =null
        var rlist17:/*CREOLE_XParser.*/rlist_return = null


        var string_literal15_tree:CHRTree=null
        var COLON16_tree:CHRTree=null
        var stream_COLON:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COLON")
        var stream_64:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 64")
        var stream_rlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rlist");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:66:5: ( 'required' COLON rlist -> ^( REQUIRED rlist ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:66:9: 'required' COLON rlist
            {
            string_literal15=smatch(input,64,FOLLOW_64_in_dec_req344).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_64.add(string_literal15);

            COLON16=smatch(input,COLON,FOLLOW_COLON_in_dec_req346).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON16);

            pushFollow(FOLLOW_rlist_in_dec_req348);
            rlist17=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist17.getTree());


            // AST REWRITE
            // elements: rlist
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 66:32: -> ^( REQUIRED rlist )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:66:35: ^( REQUIRED rlist )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(REQUIRED, "REQUIRED").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                adaptor.addChild(root_1, stream_rlist.nextTree());

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
    // $ANTLR end "dec_req"

      class dec_loc_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "dec_loc" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:69:1: dec_loc : 'local' COLON rlist -> ^( PRIVATE rlist ) ;
    @throws(classOf[RecognitionException])
     final def dec_loc():/*CREOLE_XParser.*/dec_loc_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/dec_loc_return  = new /*CREOLE_XParser.*/dec_loc_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var string_literal18: Token =null
        var COLON19: Token =null
        var rlist20:/*CREOLE_XParser.*/rlist_return = null


        var string_literal18_tree:CHRTree=null
        var COLON19_tree:CHRTree=null
        var stream_COLON:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COLON")
        var stream_65:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 65")
        var stream_rlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rlist");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:70:5: ( 'local' COLON rlist -> ^( PRIVATE rlist ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:70:9: 'local' COLON rlist
            {
            string_literal18=smatch(input,65,FOLLOW_65_in_dec_loc375).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_65.add(string_literal18);

            COLON19=smatch(input,COLON,FOLLOW_COLON_in_dec_loc377).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON19);

            pushFollow(FOLLOW_rlist_in_dec_loc379);
            rlist20=rlist();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rlist.add(rlist20.getTree());


            // AST REWRITE
            // elements: rlist
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 70:29: -> ^( PRIVATE rlist )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:70:32: ^( PRIVATE rlist )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(PRIVATE, "PRIVATE").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                adaptor.addChild(root_1, stream_rlist.nextTree());

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
    // $ANTLR end "dec_loc"

      class rlist_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "rlist" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:73:1: rlist : ( rdeclaration ( COMMA rdeclaration )* -> ( rdeclaration )* | UNDEF -> EMPTYLIST );
    @throws(classOf[RecognitionException])
     final def rlist():/*CREOLE_XParser.*/rlist_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/rlist_return  = new /*CREOLE_XParser.*/rlist_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var COMMA22: Token =null
        var UNDEF24: Token =null
        var rdeclaration21:/*CREOLE_XParser.*/rdeclaration_return = null

        var rdeclaration23:/*CREOLE_XParser.*/rdeclaration_return = null


        var COMMA22_tree:CHRTree=null
        var UNDEF24_tree:CHRTree=null
        var stream_UNDEF:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token UNDEF")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_rdeclaration:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rdeclaration");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:74:5: ( rdeclaration ( COMMA rdeclaration )* -> ( rdeclaration )* | UNDEF -> EMPTYLIST )
            var alt4=2;
            var LA4_0 = input.LA(1);

            if ( (LA4_0==R_ID||LA4_0==TILDE) ) {
                alt4=1;
            }
            else if ( (LA4_0==UNDEF) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            alt4 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:74:9: rdeclaration ( COMMA rdeclaration )*
                    {
                    pushFollow(FOLLOW_rdeclaration_in_rlist406);
                    rdeclaration21=rdeclaration();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rdeclaration.add(rdeclaration21.getTree());
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:74:22: ( COMMA rdeclaration )*
                    //loop3:
                    var guard = true
                    while(guard) {
                        var alt3=2;
                        var LA3_0 = input.LA(1);

                        if ( (LA3_0==COMMA) ) {
                            alt3=1;
                        }


                        alt3 match{
                    		case 1 =>
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:74:23: COMMA rdeclaration
                    		    {
                    		    COMMA22=smatch(input,COMMA,FOLLOW_COMMA_in_rlist409).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA22);

                    		    pushFollow(FOLLOW_rdeclaration_in_rlist411);
                    		    rdeclaration23=rdeclaration();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_rdeclaration.add(rdeclaration23.getTree());

                    		    }
                    		case _ =>
                    		    guard = false //loop3;
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
                    // 74:44: -> ( rdeclaration )*
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:74:47: ( rdeclaration )*
                        while ( stream_rdeclaration.hasNext() ) {
                            adaptor.addChild(root_0, stream_rdeclaration.nextTree());

                        }
                        stream_rdeclaration.reset();

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:75:9: UNDEF
                    {
                    UNDEF24=smatch(input,UNDEF,FOLLOW_UNDEF_in_rlist428).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UNDEF.add(UNDEF24);



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
                    // 75:15: -> EMPTYLIST
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:78:1: rdeclaration : ( R_ID | R_ID ARROBAS url -> ^( ARROBAS R_ID url ) | TILDE R_ID -> ^( MULTI R_ID ) );
    @throws(classOf[RecognitionException])
     final def rdeclaration():/*CREOLE_XParser.*/rdeclaration_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/rdeclaration_return  = new /*CREOLE_XParser.*/rdeclaration_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var R_ID25: Token =null
        var R_ID26: Token =null
        var ARROBAS27: Token =null
        var TILDE29: Token =null
        var R_ID30: Token =null
        var url28:/*CREOLE_XParser.*/url_return = null


        var R_ID25_tree:CHRTree=null
        var R_ID26_tree:CHRTree=null
        var ARROBAS27_tree:CHRTree=null
        var TILDE29_tree:CHRTree=null
        var R_ID30_tree:CHRTree=null
        var stream_R_ID:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token R_ID")
        var stream_ARROBAS:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ARROBAS")
        var stream_TILDE:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token TILDE")
        var stream_url:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule url");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:79:5: ( R_ID | R_ID ARROBAS url -> ^( ARROBAS R_ID url ) | TILDE R_ID -> ^( MULTI R_ID ) )
            var alt5=3;
            var LA5_0 = input.LA(1);

            if ( (LA5_0==R_ID) ) {
                var LA5_1 = input.LA(2);

                if ( (LA5_1==ARROBAS) ) {
                    alt5=2;
                }
                else if ( (LA5_1==EOF||(LA5_1>=SEMI && LA5_1<=RPAREN)||LA5_1==COMMA) ) {
                    alt5=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    val nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA5_0==TILDE) ) {
                alt5=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            alt5 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:79:9: R_ID
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    R_ID25=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration451).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    R_ID25_tree = adaptor.create(R_ID25).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, R_ID25_tree);
                    }

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:80:9: R_ID ARROBAS url
                    {
                    R_ID26=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration461).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_R_ID.add(R_ID26);

                    ARROBAS27=smatch(input,ARROBAS,FOLLOW_ARROBAS_in_rdeclaration463).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ARROBAS.add(ARROBAS27);

                    pushFollow(FOLLOW_url_in_rdeclaration465);
                    url28=url();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_url.add(url28.getTree());


                    // AST REWRITE
                    // elements: ARROBAS, url, R_ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 80:26: -> ^( ARROBAS R_ID url )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:80:29: ^( ARROBAS R_ID url )
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
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:81:9: TILDE R_ID
                    {
                    TILDE29=smatch(input,TILDE,FOLLOW_TILDE_in_rdeclaration485).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TILDE.add(TILDE29);

                    R_ID30=smatch(input,R_ID,FOLLOW_R_ID_in_rdeclaration487).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_R_ID.add(R_ID30);



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
                    // 81:20: -> ^( MULTI R_ID )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:81:23: ^( MULTI R_ID )
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:84:1: script : parallel ( SEMI parallel )* ;
    @throws(classOf[RecognitionException])
     final def script():/*CREOLE_XParser.*/script_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/script_return  = new /*CREOLE_XParser.*/script_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var SEMI32: Token =null
        var parallel31:/*CREOLE_XParser.*/parallel_return = null

        var parallel33:/*CREOLE_XParser.*/parallel_return = null


        var SEMI32_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:85:5: ( parallel ( SEMI parallel )* )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:85:9: parallel ( SEMI parallel )*
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            pushFollow(FOLLOW_parallel_in_script514);
            parallel31=parallel();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, parallel31.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:85:18: ( SEMI parallel )*
            //loop6:
            var guard = true
            while(guard) {
                var alt6=2;
                var LA6_0 = input.LA(1);

                if ( (LA6_0==SEMI) ) {
                    alt6=1;
                }


                alt6 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:85:19: SEMI parallel
            		    {
            		    SEMI32=smatch(input,SEMI,FOLLOW_SEMI_in_script517).asInstanceOf[Token]; if (state.failed) return retval;
            		    if ( state.backtracking==0 ) {
            		    SEMI32_tree = adaptor.create(SEMI32).asInstanceOf[CHRTree];
            		    root_0 = adaptor.becomeRoot(SEMI32_tree, root_0).asInstanceOf[CHRTree]
            		    }
            		    pushFollow(FOLLOW_parallel_in_script520);
            		    parallel33=parallel();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) adaptor.addChild(root_0, parallel33.getTree());

            		    }
            		case _ =>
            		    guard = false //loop6;
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:88:1: parallel : bang ( BAR bang )* ;
    @throws(classOf[RecognitionException])
     final def parallel():/*CREOLE_XParser.*/parallel_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/parallel_return  = new /*CREOLE_XParser.*/parallel_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var BAR35: Token =null
        var bang34:/*CREOLE_XParser.*/bang_return = null

        var bang36:/*CREOLE_XParser.*/bang_return = null


        var BAR35_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:89:5: ( bang ( BAR bang )* )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:89:9: bang ( BAR bang )*
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            pushFollow(FOLLOW_bang_in_parallel541);
            bang34=bang();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bang34.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:89:14: ( BAR bang )*
            //loop7:
            var guard = true
            while(guard) {
                var alt7=2;
                var LA7_0 = input.LA(1);

                if ( (LA7_0==BAR) ) {
                    alt7=1;
                }


                alt7 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:89:15: BAR bang
            		    {
            		    BAR35=smatch(input,BAR,FOLLOW_BAR_in_parallel544).asInstanceOf[Token]; if (state.failed) return retval;
            		    if ( state.backtracking==0 ) {
            		    BAR35_tree = adaptor.create(BAR35).asInstanceOf[CHRTree];
            		    root_0 = adaptor.becomeRoot(BAR35_tree, root_0).asInstanceOf[CHRTree]
            		    }
            		    pushFollow(FOLLOW_bang_in_parallel547);
            		    bang36=bang();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) adaptor.addChild(root_0, bang36.getTree());

            		    }
            		case _ =>
            		    guard = false //loop7;
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:92:1: bang : ( simple_script | BANG simple_script -> ^( BANG simple_script ) );
    @throws(classOf[RecognitionException])
     final def bang():/*CREOLE_XParser.*/bang_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/bang_return  = new /*CREOLE_XParser.*/bang_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var BANG38: Token =null
        var simple_script37:/*CREOLE_XParser.*/simple_script_return = null

        var simple_script39:/*CREOLE_XParser.*/simple_script_return = null


        var BANG38_tree:CHRTree=null
        var stream_BANG:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token BANG")
        var stream_simple_script:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule simple_script");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:93:5: ( simple_script | BANG simple_script -> ^( BANG simple_script ) )
            var alt8=2;
            var LA8_0 = input.LA(1);

            if ( (LA8_0==LPAREN||LA8_0==R_ID||(LA8_0>=TRUE && LA8_0<=FALSE)||LA8_0==67) ) {
                alt8=1;
            }
            else if ( (LA8_0==BANG) ) {
                alt8=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            alt8 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:93:9: simple_script
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_simple_script_in_bang568);
                    simple_script37=simple_script();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_script37.getTree());

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:94:9: BANG simple_script
                    {
                    BANG38=smatch(input,BANG,FOLLOW_BANG_in_bang578).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BANG.add(BANG38);

                    pushFollow(FOLLOW_simple_script_in_bang580);
                    simple_script39=simple_script();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_simple_script.add(simple_script39.getTree());


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
                    // 94:28: -> ^( BANG simple_script )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:94:31: ^( BANG simple_script )
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:97:1: simple_script : ( rule | LPAREN script RPAREN -> script );
    @throws(classOf[RecognitionException])
     final def simple_script():/*CREOLE_XParser.*/simple_script_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/simple_script_return  = new /*CREOLE_XParser.*/simple_script_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN41: Token =null
        var RPAREN43: Token =null
        var rule40:/*CREOLE_XParser.*/rule_return = null

        var script42:/*CREOLE_XParser.*/script_return = null


        var LPAREN41_tree:CHRTree=null
        var RPAREN43_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_script:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule script");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:98:5: ( rule | LPAREN script RPAREN -> script )
            var alt9=2;
            var LA9_0 = input.LA(1);

            if ( (LA9_0==R_ID||(LA9_0>=TRUE && LA9_0<=FALSE)||LA9_0==67) ) {
                alt9=1;
            }
            else if ( (LA9_0==LPAREN) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            alt9 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:98:9: rule
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_rule_in_simple_script608);
                    rule40=rule();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rule40.getTree());

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:99:9: LPAREN script RPAREN
                    {
                    LPAREN41=smatch(input,LPAREN,FOLLOW_LPAREN_in_simple_script618).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN41);

                    pushFollow(FOLLOW_script_in_simple_script620);
                    script42=script();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_script.add(script42.getTree());
                    RPAREN43=smatch(input,RPAREN,FOLLOW_RPAREN_in_simple_script622).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN43);



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
                    // 99:31: -> script
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:102:1: rule : conjunction RARROW ( guard )? ( nu )? conjunction -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? ) ;
    @throws(classOf[RecognitionException])
     final def rule():/*CREOLE_XParser.*/rule_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/rule_return  = new /*CREOLE_XParser.*/rule_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var RARROW45: Token =null
        var conjunction44:/*CREOLE_XParser.*/conjunction_return = null

        var guard46:/*CREOLE_XParser.*/guard_return = null

        var nu47:/*CREOLE_XParser.*/nu_return = null

        var conjunction48:/*CREOLE_XParser.*/conjunction_return = null


        var RARROW45_tree:CHRTree=null
        var stream_RARROW:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RARROW")
        var stream_nu:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule nu");
        var stream_guard:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule guard");
        var stream_conjunction:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule conjunction");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:103:5: ( conjunction RARROW ( guard )? ( nu )? conjunction -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:103:9: conjunction RARROW ( guard )? ( nu )? conjunction
            {
            pushFollow(FOLLOW_conjunction_in_rule646);
            conjunction44=conjunction();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_conjunction.add(conjunction44.getTree());
            RARROW45=smatch(input,RARROW,FOLLOW_RARROW_in_rule648).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RARROW.add(RARROW45);

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:103:28: ( guard )?
            var alt10=2;
            var LA10_0 = input.LA(1);

            if ( (LA10_0==LBRACK||LA10_0==ABS) ) {
                alt10=1;
            }
            alt10 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:0:0: guard
                    {
                    pushFollow(FOLLOW_guard_in_rule650);
                    guard46=guard();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_guard.add(guard46.getTree());

                    }
                case _ => //Do nothing
            }

            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:103:35: ( nu )?
            var alt11=2;
            var LA11_0 = input.LA(1);

            if ( (LA11_0==NU) ) {
                alt11=1;
            }
            alt11 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:0:0: nu
                    {
                    pushFollow(FOLLOW_nu_in_rule653);
                    nu47=nu();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_nu.add(nu47.getTree());

                    }
                case _ => //Do nothing
            }

            pushFollow(FOLLOW_conjunction_in_rule656);
            conjunction48=conjunction();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_conjunction.add(conjunction48.getTree());


            // AST REWRITE
            // elements: guard, conjunction, nu, conjunction
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 103:52: -> ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:104:13: ^( RULE ( guard )? ^( HEAD conjunction ) ^( BODY conjunction ) ( nu )? )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(RULE, "RULE").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:104:20: ( guard )?
                if ( stream_guard.hasNext() ) {
                    adaptor.addChild(root_1, stream_guard.nextTree());

                }
                stream_guard.reset();
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:104:27: ^( HEAD conjunction )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(HEAD, "HEAD").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_conjunction.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:104:47: ^( BODY conjunction )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(BODY, "BODY").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_conjunction.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:104:67: ( nu )?
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:107:1: guard : ( LBRACK ( rule )* RBRACK IMARK -> ^( GUARD ( rule )* ) | ABS LBRACK conjunction RBRACK IMARK -> ^( GUARD ^( ABS conjunction ) ) | LBRACK guardExpr RBRACK IMARK -> ^( GUARD guardExpr ) );
    @throws(classOf[RecognitionException])
     final def guard():/*CREOLE_XParser.*/guard_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/guard_return  = new /*CREOLE_XParser.*/guard_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LBRACK49: Token =null
        var RBRACK51: Token =null
        var IMARK52: Token =null
        var ABS53: Token =null
        var LBRACK54: Token =null
        var RBRACK56: Token =null
        var IMARK57: Token =null
        var LBRACK58: Token =null
        var RBRACK60: Token =null
        var IMARK61: Token =null
        var rule50:/*CREOLE_XParser.*/rule_return = null

        var conjunction55:/*CREOLE_XParser.*/conjunction_return = null

        var guardExpr59:/*CREOLE_XParser.*/guardExpr_return = null


        var LBRACK49_tree:CHRTree=null
        var RBRACK51_tree:CHRTree=null
        var IMARK52_tree:CHRTree=null
        var ABS53_tree:CHRTree=null
        var LBRACK54_tree:CHRTree=null
        var RBRACK56_tree:CHRTree=null
        var IMARK57_tree:CHRTree=null
        var LBRACK58_tree:CHRTree=null
        var RBRACK60_tree:CHRTree=null
        var IMARK61_tree:CHRTree=null
        var stream_ABS:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ABS")
        var stream_RBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RBRACK")
        var stream_LBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LBRACK")
        var stream_IMARK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token IMARK")
        var stream_rule:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule rule");
        var stream_conjunction:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule conjunction");
        var stream_guardExpr:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule guardExpr");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:108:5: ( LBRACK ( rule )* RBRACK IMARK -> ^( GUARD ( rule )* ) | ABS LBRACK conjunction RBRACK IMARK -> ^( GUARD ^( ABS conjunction ) ) | LBRACK guardExpr RBRACK IMARK -> ^( GUARD guardExpr ) )
            var alt13=3;
            var LA13_0 = input.LA(1);

            if ( (LA13_0==LBRACK) ) {
                input.LA(2) match{
                	case RBRACK => alt13=1;
                	case TRUE => alt13=1;
                	case FALSE => alt13=1;
                	case 67 => alt13=1;    
                	case R_ID => var LA13_4 = input.LA(3);

                	if ( (LA13_4==EQ) ) {
                	    alt13=3;
                	}
                	else if ( (LA13_4==LPAREN||LA13_4==COMMA||LA13_4==RARROW) ) {
                	    alt13=1;
                	}
                	else {
                	    if (state.backtracking>0) {state.failed=true; return retval;}
                	    val nvae =
                	        new NoViableAltException("", 13, 4, input);

                	    throw nvae;
                	}    
                	case NOT => alt13=3;
                	case NULL => alt13=3;
                	case V_ID => alt13=3;
                	case 66 => alt13=3;    
                	case _ =>
                		    if (state.backtracking>0) {state.failed=true; return retval;}
                		    val nvae =
                		        new NoViableAltException("", 13, 1, input);

                		    throw nvae;

                }

            }
            else if ( (LA13_0==ABS) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            alt13 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:108:9: LBRACK ( rule )* RBRACK IMARK
                    {
                    LBRACK49=smatch(input,LBRACK,FOLLOW_LBRACK_in_guard712).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK49);

                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:108:16: ( rule )*
                    //loop12:
                    var guard = true
                    while(guard) {
                        var alt12=2;
                        var LA12_0 = input.LA(1);

                        if ( (LA12_0==R_ID||(LA12_0>=TRUE && LA12_0<=FALSE)||LA12_0==67) ) {
                            alt12=1;
                        }


                        alt12 match{
                    		case 1 =>
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:0:0: rule
                    		    {
                    		    pushFollow(FOLLOW_rule_in_guard714);
                    		    rule50=rule();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_rule.add(rule50.getTree());

                    		    }
                    		case _ =>
                    		    guard = false //loop12;
                        }
                    } //while (true);

                    RBRACK51=smatch(input,RBRACK,FOLLOW_RBRACK_in_guard717).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK51);

                    IMARK52=smatch(input,IMARK,FOLLOW_IMARK_in_guard719).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IMARK.add(IMARK52);



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
                    // 108:35: -> ^( GUARD ( rule )* )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:108:38: ^( GUARD ( rule )* )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(GUARD, "GUARD").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:108:46: ( rule )*
                        while ( stream_rule.hasNext() ) {
                            adaptor.addChild(root_1, stream_rule.nextTree());

                        }
                        stream_rule.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:109:9: ABS LBRACK conjunction RBRACK IMARK
                    {
                    ABS53=smatch(input,ABS,FOLLOW_ABS_in_guard738).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ABS.add(ABS53);

                    LBRACK54=smatch(input,LBRACK,FOLLOW_LBRACK_in_guard740).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK54);

                    pushFollow(FOLLOW_conjunction_in_guard742);
                    conjunction55=conjunction();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_conjunction.add(conjunction55.getTree());
                    RBRACK56=smatch(input,RBRACK,FOLLOW_RBRACK_in_guard744).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK56);

                    IMARK57=smatch(input,IMARK,FOLLOW_IMARK_in_guard746).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IMARK.add(IMARK57);



                    // AST REWRITE
                    // elements: conjunction, ABS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 109:45: -> ^( GUARD ^( ABS conjunction ) )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:109:48: ^( GUARD ^( ABS conjunction ) )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(GUARD, "GUARD").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:109:56: ^( ABS conjunction )
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
                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:110:9: LBRACK guardExpr RBRACK IMARK
                    {
                    LBRACK58=smatch(input,LBRACK,FOLLOW_LBRACK_in_guard768).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK58);

                    pushFollow(FOLLOW_guardExpr_in_guard770);
                    guardExpr59=guardExpr();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_guardExpr.add(guardExpr59.getTree());
                    RBRACK60=smatch(input,RBRACK,FOLLOW_RBRACK_in_guard772).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK60);

                    IMARK61=smatch(input,IMARK,FOLLOW_IMARK_in_guard774).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IMARK.add(IMARK61);



                    // AST REWRITE
                    // elements: guardExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 110:39: -> ^( GUARD guardExpr )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:110:42: ^( GUARD guardExpr )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(GUARD, "GUARD").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_guardExpr.nextTree());

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

      class guardExpr_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "guardExpr" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:113:1: guardExpr : ( variable EQ variable -> ^( EQ variable variable ) | 'Null' LPAREN variable RPAREN -> ^( NULL variable ) | NOT LPAREN guardExpr RPAREN -> ^( NOT guardExpr ) );
    @throws(classOf[RecognitionException])
     final def guardExpr():/*CREOLE_XParser.*/guardExpr_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/guardExpr_return  = new /*CREOLE_XParser.*/guardExpr_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var EQ63: Token =null
        var string_literal65: Token =null
        var LPAREN66: Token =null
        var RPAREN68: Token =null
        var NOT69: Token =null
        var LPAREN70: Token =null
        var RPAREN72: Token =null
        var variable62:/*CREOLE_XParser.*/variable_return = null

        var variable64:/*CREOLE_XParser.*/variable_return = null

        var variable67:/*CREOLE_XParser.*/variable_return = null

        var guardExpr71:/*CREOLE_XParser.*/guardExpr_return = null


        var EQ63_tree:CHRTree=null
        var string_literal65_tree:CHRTree=null
        var LPAREN66_tree:CHRTree=null
        var RPAREN68_tree:CHRTree=null
        var NOT69_tree:CHRTree=null
        var LPAREN70_tree:CHRTree=null
        var RPAREN72_tree:CHRTree=null
        var stream_66:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 66")
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_NOT:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token NOT")
        var stream_EQ:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token EQ")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_variable:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule variable");
        var stream_guardExpr:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule guardExpr");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:114:5: ( variable EQ variable -> ^( EQ variable variable ) | 'Null' LPAREN variable RPAREN -> ^( NULL variable ) | NOT LPAREN guardExpr RPAREN -> ^( NOT guardExpr ) )
            var alt14=3;
            input.LA(1) match{
            	case R_ID => alt14=1;
            	case NULL => alt14=1;
            	case V_ID => alt14=1;    
            	case 66 => alt14=2;    
            	case NOT => alt14=3;    
            	case _ =>
            		    if (state.backtracking>0) {state.failed=true; return retval;}
            		    val nvae =
            		        new NoViableAltException("", 14, 0, input);

            		    throw nvae;

            }

            alt14 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:114:9: variable EQ variable
                    {
                    pushFollow(FOLLOW_variable_in_guardExpr801);
                    variable62=variable();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variable.add(variable62.getTree());
                    EQ63=smatch(input,EQ,FOLLOW_EQ_in_guardExpr803).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_EQ.add(EQ63);

                    pushFollow(FOLLOW_variable_in_guardExpr805);
                    variable64=variable();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variable.add(variable64.getTree());


                    // AST REWRITE
                    // elements: EQ, variable, variable
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 114:30: -> ^( EQ variable variable )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:114:33: ^( EQ variable variable )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(stream_EQ.nextNode(), root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_variable.nextTree());
                        adaptor.addChild(root_1, stream_variable.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:115:9: 'Null' LPAREN variable RPAREN
                    {
                    string_literal65=smatch(input,66,FOLLOW_66_in_guardExpr825).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_66.add(string_literal65);

                    LPAREN66=smatch(input,LPAREN,FOLLOW_LPAREN_in_guardExpr827).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN66);

                    pushFollow(FOLLOW_variable_in_guardExpr829);
                    variable67=variable();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variable.add(variable67.getTree());
                    RPAREN68=smatch(input,RPAREN,FOLLOW_RPAREN_in_guardExpr831).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN68);



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
                    // 115:39: -> ^( NULL variable )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:115:42: ^( NULL variable )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(NULL, "NULL").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_variable.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:116:9: NOT LPAREN guardExpr RPAREN
                    {
                    NOT69=smatch(input,NOT,FOLLOW_NOT_in_guardExpr849).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NOT.add(NOT69);

                    LPAREN70=smatch(input,LPAREN,FOLLOW_LPAREN_in_guardExpr851).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN70);

                    pushFollow(FOLLOW_guardExpr_in_guardExpr853);
                    guardExpr71=guardExpr();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_guardExpr.add(guardExpr71.getTree());
                    RPAREN72=smatch(input,RPAREN,FOLLOW_RPAREN_in_guardExpr855).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN72);



                    // AST REWRITE
                    // elements: guardExpr, NOT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 116:37: -> ^( NOT guardExpr )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:116:40: ^( NOT guardExpr )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(stream_NOT.nextNode(), root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_guardExpr.nextTree());

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
    // $ANTLR end "guardExpr"

      class nu_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "nu" rule(...)
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:119:1: nu : NU LPAREN variable ( COMMA variable )* RPAREN -> ^( NU ( variable )* ) ;
    @throws(classOf[RecognitionException])
     final def nu():/*CREOLE_XParser.*/nu_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/nu_return  = new /*CREOLE_XParser.*/nu_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var NU73: Token =null
        var LPAREN74: Token =null
        var COMMA76: Token =null
        var RPAREN78: Token =null
        var variable75:/*CREOLE_XParser.*/variable_return = null

        var variable77:/*CREOLE_XParser.*/variable_return = null


        var NU73_tree:CHRTree=null
        var LPAREN74_tree:CHRTree=null
        var COMMA76_tree:CHRTree=null
        var RPAREN78_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_NU:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token NU")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_variable:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule variable");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:120:5: ( NU LPAREN variable ( COMMA variable )* RPAREN -> ^( NU ( variable )* ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:120:9: NU LPAREN variable ( COMMA variable )* RPAREN
            {
            NU73=smatch(input,NU,FOLLOW_NU_in_nu884).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NU.add(NU73);

            LPAREN74=smatch(input,LPAREN,FOLLOW_LPAREN_in_nu886).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN74);

            pushFollow(FOLLOW_variable_in_nu888);
            variable75=variable();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variable.add(variable75.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:120:28: ( COMMA variable )*
            //loop15:
            var guard = true
            while(guard) {
                var alt15=2;
                var LA15_0 = input.LA(1);

                if ( (LA15_0==COMMA) ) {
                    alt15=1;
                }


                alt15 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:120:29: COMMA variable
            		    {
            		    COMMA76=smatch(input,COMMA,FOLLOW_COMMA_in_nu891).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA76);

            		    pushFollow(FOLLOW_variable_in_nu893);
            		    variable77=variable();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_variable.add(variable77.getTree());

            		    }
            		case _ =>
            		    guard = false //loop15;
                }
            } //while (true);

            RPAREN78=smatch(input,RPAREN,FOLLOW_RPAREN_in_nu897).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN78);



            // AST REWRITE
            // elements: variable, NU
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 120:53: -> ^( NU ( variable )* )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:120:56: ^( NU ( variable )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(stream_NU.nextNode(), root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:120:61: ( variable )*
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:123:1: conjunction : ( 'T' -> EMPTY | atom ( COMMA atom )* -> ( atom )* );
    @throws(classOf[RecognitionException])
     final def conjunction():/*CREOLE_XParser.*/conjunction_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/conjunction_return  = new /*CREOLE_XParser.*/conjunction_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var char_literal79: Token =null
        var COMMA81: Token =null
        var atom80:/*CREOLE_XParser.*/atom_return = null

        var atom82:/*CREOLE_XParser.*/atom_return = null


        var char_literal79_tree:CHRTree=null
        var COMMA81_tree:CHRTree=null
        var stream_67:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token 67")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_atom:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule atom");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:124:5: ( 'T' -> EMPTY | atom ( COMMA atom )* -> ( atom )* )
            var alt17=2;
            var LA17_0 = input.LA(1);

            if ( (LA17_0==67) ) {
                alt17=1;
            }
            else if ( (LA17_0==R_ID||(LA17_0>=TRUE && LA17_0<=FALSE)) ) {
                alt17=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            alt17 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:124:9: 'T'
                    {
                    char_literal79=smatch(input,67,FOLLOW_67_in_conjunction925).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_67.add(char_literal79);



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
                    // 124:13: -> EMPTY
                    {
                        adaptor.addChild(root_0, adaptor.create(EMPTY, "EMPTY").asInstanceOf[CHRTree]);

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:125:9: atom ( COMMA atom )*
                    {
                    pushFollow(FOLLOW_atom_in_conjunction939);
                    atom80=atom();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom80.getTree());
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:125:14: ( COMMA atom )*
                    //loop16:
                    var guard = true
                    while(guard) {
                        var alt16=2;
                        var LA16_0 = input.LA(1);

                        if ( (LA16_0==COMMA) ) {
                            alt16=1;
                        }


                        alt16 match{
                    		case 1 =>
                    		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:125:15: COMMA atom
                    		    {
                    		    COMMA81=smatch(input,COMMA,FOLLOW_COMMA_in_conjunction942).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA81);

                    		    pushFollow(FOLLOW_atom_in_conjunction944);
                    		    atom82=atom();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_atom.add(atom82.getTree());

                    		    }
                    		case _ =>
                    		    guard = false //loop16;
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
                    // 125:28: -> ( atom )*
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:125:31: ( atom )*
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:128:1: atom : ( TRUE | FALSE | relation ( termlist )? -> ^( ATOM relation ( termlist )? ) );
    @throws(classOf[RecognitionException])
     final def atom():/*CREOLE_XParser.*/atom_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/atom_return  = new /*CREOLE_XParser.*/atom_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var TRUE83: Token =null
        var FALSE84: Token =null
        var relation85:/*CREOLE_XParser.*/relation_return = null

        var termlist86:/*CREOLE_XParser.*/termlist_return = null


        var TRUE83_tree:CHRTree=null
        var FALSE84_tree:CHRTree=null
        var stream_termlist:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule termlist");
        var stream_relation:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule relation");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:129:5: ( TRUE | FALSE | relation ( termlist )? -> ^( ATOM relation ( termlist )? ) )
            var alt19=3;
            input.LA(1) match{
            	case TRUE => alt19=1;    
            	case FALSE => alt19=2;    
            	case R_ID => alt19=3;    
            	case _ =>
            		    if (state.backtracking>0) {state.failed=true; return retval;}
            		    val nvae =
            		        new NoViableAltException("", 19, 0, input);

            		    throw nvae;

            }

            alt19 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:129:9: TRUE
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    TRUE83=smatch(input,TRUE,FOLLOW_TRUE_in_atom970).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TRUE83_tree = adaptor.create(TRUE83).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, TRUE83_tree);
                    }

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:130:9: FALSE
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    FALSE84=smatch(input,FALSE,FOLLOW_FALSE_in_atom980).asInstanceOf[Token]; if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FALSE84_tree = adaptor.create(FALSE84).asInstanceOf[CHRTree];
                    adaptor.addChild(root_0, FALSE84_tree);
                    }

                    }case 3 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:131:9: relation ( termlist )?
                    {
                    pushFollow(FOLLOW_relation_in_atom990);
                    relation85=relation();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_relation.add(relation85.getTree());
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:131:18: ( termlist )?
                    var alt18=2;
                    var LA18_0 = input.LA(1);

                    if ( (LA18_0==LPAREN) ) {
                        alt18=1;
                    }
                    alt18 match{
                        case 1 =>
                            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:0:0: termlist
                            {
                            pushFollow(FOLLOW_termlist_in_atom992);
                            termlist86=termlist();

                            state._fsp -= 1 
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_termlist.add(termlist86.getTree());

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
                    // 131:29: -> ^( ATOM relation ( termlist )? )
                    {
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:132:13: ^( ATOM relation ( termlist )? )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(ATOM, "ATOM").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        adaptor.addChild(root_1, stream_relation.nextTree());
                        // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:132:29: ( termlist )?
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:135:1: termlist : LPAREN term ( COMMA term )* RPAREN -> ^( VARS ( term )* ) ;
    @throws(classOf[RecognitionException])
     final def termlist():/*CREOLE_XParser.*/termlist_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/termlist_return  = new /*CREOLE_XParser.*/termlist_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN87: Token =null
        var COMMA89: Token =null
        var RPAREN91: Token =null
        var term88:/*CREOLE_XParser.*/term_return = null

        var term90:/*CREOLE_XParser.*/term_return = null


        var LPAREN87_tree:CHRTree=null
        var COMMA89_tree:CHRTree=null
        var RPAREN91_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_term:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule term");
        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:136:5: ( LPAREN term ( COMMA term )* RPAREN -> ^( VARS ( term )* ) )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:136:9: LPAREN term ( COMMA term )* RPAREN
            {
            LPAREN87=smatch(input,LPAREN,FOLLOW_LPAREN_in_termlist1036).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN87);

            pushFollow(FOLLOW_term_in_termlist1038);
            term88=term();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_term.add(term88.getTree());
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:136:21: ( COMMA term )*
            //loop20:
            var guard = true
            while(guard) {
                var alt20=2;
                var LA20_0 = input.LA(1);

                if ( (LA20_0==COMMA) ) {
                    alt20=1;
                }


                alt20 match{
            		case 1 =>
            		    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:136:22: COMMA term
            		    {
            		    COMMA89=smatch(input,COMMA,FOLLOW_COMMA_in_termlist1041).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA89);

            		    pushFollow(FOLLOW_term_in_termlist1043);
            		    term90=term();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_term.add(term90.getTree());

            		    }
            		case _ =>
            		    guard = false //loop20;
                }
            } //while (true);

            RPAREN91=smatch(input,RPAREN,FOLLOW_RPAREN_in_termlist1047).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN91);



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
            // 136:42: -> ^( VARS ( term )* )
            {
                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:136:47: ^( VARS ( term )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(VARS, "VARS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:136:54: ( term )*
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:139:1: term : ( constant | variable );
    @throws(classOf[RecognitionException])
     final def term():/*CREOLE_XParser.*/term_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/term_return  = new /*CREOLE_XParser.*/term_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var constant92:/*CREOLE_XParser.*/constant_return = null

        var variable93:/*CREOLE_XParser.*/variable_return = null



        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:140:2: ( constant | variable )
            var alt21=2;
            var LA21_0 = input.LA(1);

            if ( ((LA21_0>=INT && LA21_0<=STRING)) ) {
                alt21=1;
            }
            else if ( (LA21_0==R_ID||(LA21_0>=NULL && LA21_0<=V_ID)) ) {
                alt21=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            alt21 match{
                case 1 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:140:4: constant
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_constant_in_term1072);
                    constant92=constant();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constant92.getTree());

                    }case 2 =>
                    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:141:4: variable
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_variable_in_term1077);
                    variable93=variable();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variable93.getTree());

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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:144:1: relation : R_ID ;
    @throws(classOf[RecognitionException])
     final def relation():/*CREOLE_XParser.*/relation_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/relation_return  = new /*CREOLE_XParser.*/relation_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var R_ID94: Token =null

        var R_ID94_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:145:5: ( R_ID )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:145:9: R_ID
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            R_ID94=smatch(input,R_ID,FOLLOW_R_ID_in_relation1093).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            R_ID94_tree = adaptor.create(R_ID94).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, R_ID94_tree);
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:148:1: variable : ( NULL | V_ID | R_ID );
    @throws(classOf[RecognitionException])
     final def variable():/*CREOLE_XParser.*/variable_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/variable_return  = new /*CREOLE_XParser.*/variable_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var set95: Token =null

        var set95_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:149:5: ( NULL | V_ID | R_ID )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            set95=input.LT(1).asInstanceOf[Token]
            if ( input.LA(1)==R_ID||(input.LA(1)>=NULL && input.LA(1)<=V_ID) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, adaptor.create(set95).asInstanceOf[CHRTree]);
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:152:1: constant : ( INT | STRING );
    @throws(classOf[RecognitionException])
     final def constant():/*CREOLE_XParser.*/constant_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/constant_return  = new /*CREOLE_XParser.*/constant_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var set96: Token =null

        var set96_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:153:2: ( INT | STRING )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            set96=input.LT(1).asInstanceOf[Token]
            if ( (input.LA(1)>=INT && input.LA(1)<=STRING) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, adaptor.create(set96).asInstanceOf[CHRTree]);
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
    // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:157:1: url : STRING ;
    @throws(classOf[RecognitionException])
     final def url():/*CREOLE_XParser.*/url_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*CREOLE_XParser.*/url_return  = new /*CREOLE_XParser.*/url_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var STRING97: Token =null

        var STRING97_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:157:5: ( STRING )
            // src/main/antlr3/fr/emn/criojo/parser/CREOLE_X.g:157:7: STRING
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            STRING97=smatch(input,STRING,FOLLOW_STRING_in_url1149).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRING97_tree = adaptor.create(STRING97).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, STRING97_tree);
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


 

      final val FOLLOW_process_in_start193:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_declaration_in_process212:BitSet = new BitSet( Array[Long](0x0000060110400000L,0x0000000000000008L))
      //new long[]{0x0000060110400000L,0x0000000000000008L});
      final val FOLLOW_script_in_process214:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_declaration247:BitSet = new BitSet( Array[Long](0x8000000000000000L,0x0000000000000003L))
      //new long[]{0x8000000000000000L,0x0000000000000003L});
      final val FOLLOW_decl_in_declaration249:BitSet = new BitSet( Array[Long](0x0000000001800000L))
      //new long[]{0x0000000001800000L});
      final val FOLLOW_SEMI_in_declaration252:BitSet = new BitSet( Array[Long](0x8000000000000000L,0x0000000000000003L))
      //new long[]{0x8000000000000000L,0x0000000000000003L});
      final val FOLLOW_decl_in_declaration254:BitSet = new BitSet( Array[Long](0x0000000001800000L))
      //new long[]{0x0000000001800000L});
      final val FOLLOW_RPAREN_in_declaration258:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_dec_prov_in_decl286:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_dec_req_in_decl290:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_dec_loc_in_decl294:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_63_in_dec_prov313:BitSet = new BitSet( Array[Long](0x0000000002000000L))
      //new long[]{0x0000000002000000L});
      final val FOLLOW_COLON_in_dec_prov315:BitSet = new BitSet( Array[Long](0x0000000058000000L))
      //new long[]{0x0000000058000000L});
      final val FOLLOW_rlist_in_dec_prov317:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_64_in_dec_req344:BitSet = new BitSet( Array[Long](0x0000000002000000L))
      //new long[]{0x0000000002000000L});
      final val FOLLOW_COLON_in_dec_req346:BitSet = new BitSet( Array[Long](0x0000000058000000L))
      //new long[]{0x0000000058000000L});
      final val FOLLOW_rlist_in_dec_req348:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_65_in_dec_loc375:BitSet = new BitSet( Array[Long](0x0000000002000000L))
      //new long[]{0x0000000002000000L});
      final val FOLLOW_COLON_in_dec_loc377:BitSet = new BitSet( Array[Long](0x0000000058000000L))
      //new long[]{0x0000000058000000L});
      final val FOLLOW_rlist_in_dec_loc379:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_rdeclaration_in_rlist406:BitSet = new BitSet( Array[Long](0x0000000004000002L))
      //new long[]{0x0000000004000002L});
      final val FOLLOW_COMMA_in_rlist409:BitSet = new BitSet( Array[Long](0x0000000050000000L))
      //new long[]{0x0000000050000000L});
      final val FOLLOW_rdeclaration_in_rlist411:BitSet = new BitSet( Array[Long](0x0000000004000002L))
      //new long[]{0x0000000004000002L});
      final val FOLLOW_UNDEF_in_rlist428:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_rdeclaration451:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_rdeclaration461:BitSet = new BitSet( Array[Long](0x0000000020000000L))
      //new long[]{0x0000000020000000L});
      final val FOLLOW_ARROBAS_in_rdeclaration463:BitSet = new BitSet( Array[Long](0x0000400000000000L))
      //new long[]{0x0000400000000000L});
      final val FOLLOW_url_in_rdeclaration465:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_TILDE_in_rdeclaration485:BitSet = new BitSet( Array[Long](0x0000000010000000L))
      //new long[]{0x0000000010000000L});
      final val FOLLOW_R_ID_in_rdeclaration487:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_parallel_in_script514:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_SEMI_in_script517:BitSet = new BitSet( Array[Long](0x0000060110400000L,0x0000000000000008L))
      //new long[]{0x0000060110400000L,0x0000000000000008L});
      final val FOLLOW_parallel_in_script520:BitSet = new BitSet( Array[Long](0x0000000000800002L))
      //new long[]{0x0000000000800002L});
      final val FOLLOW_bang_in_parallel541:BitSet = new BitSet( Array[Long](0x0000000080000002L))
      //new long[]{0x0000000080000002L});
      final val FOLLOW_BAR_in_parallel544:BitSet = new BitSet( Array[Long](0x0000060110400000L,0x0000000000000008L))
      //new long[]{0x0000060110400000L,0x0000000000000008L});
      final val FOLLOW_bang_in_parallel547:BitSet = new BitSet( Array[Long](0x0000000080000002L))
      //new long[]{0x0000000080000002L});
      final val FOLLOW_simple_script_in_bang568:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_BANG_in_bang578:BitSet = new BitSet( Array[Long](0x0000060010400000L,0x0000000000000008L))
      //new long[]{0x0000060010400000L,0x0000000000000008L});
      final val FOLLOW_simple_script_in_bang580:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_rule_in_simple_script608:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_simple_script618:BitSet = new BitSet( Array[Long](0x0000060110400000L,0x0000000000000008L))
      //new long[]{0x0000060110400000L,0x0000000000000008L});
      final val FOLLOW_script_in_simple_script620:BitSet = new BitSet( Array[Long](0x0000000001000000L))
      //new long[]{0x0000000001000000L});
      final val FOLLOW_RPAREN_in_simple_script622:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_conjunction_in_rule646:BitSet = new BitSet( Array[Long](0x0000000200000000L))
      //new long[]{0x0000000200000000L});
      final val FOLLOW_RARROW_in_rule648:BitSet = new BitSet( Array[Long](0x0000072410000000L,0x0000000000000008L))
      //new long[]{0x0000072410000000L,0x0000000000000008L});
      final val FOLLOW_guard_in_rule650:BitSet = new BitSet( Array[Long](0x0000070010000000L,0x0000000000000008L))
      //new long[]{0x0000070010000000L,0x0000000000000008L});
      final val FOLLOW_nu_in_rule653:BitSet = new BitSet( Array[Long](0x0000060010000000L,0x0000000000000008L))
      //new long[]{0x0000060010000000L,0x0000000000000008L});
      final val FOLLOW_conjunction_in_rule656:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LBRACK_in_guard712:BitSet = new BitSet( Array[Long](0x0000060810000000L,0x0000000000000008L))
      //new long[]{0x0000060810000000L,0x0000000000000008L});
      final val FOLLOW_rule_in_guard714:BitSet = new BitSet( Array[Long](0x0000060810000000L,0x0000000000000008L))
      //new long[]{0x0000060810000000L,0x0000000000000008L});
      final val FOLLOW_RBRACK_in_guard717:BitSet = new BitSet( Array[Long](0x0000001000000000L))
      //new long[]{0x0000001000000000L});
      final val FOLLOW_IMARK_in_guard719:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ABS_in_guard738:BitSet = new BitSet( Array[Long](0x0000000400000000L))
      //new long[]{0x0000000400000000L});
      final val FOLLOW_LBRACK_in_guard740:BitSet = new BitSet( Array[Long](0x0000060010000000L,0x0000000000000008L))
      //new long[]{0x0000060010000000L,0x0000000000000008L});
      final val FOLLOW_conjunction_in_guard742:BitSet = new BitSet( Array[Long](0x0000000800000000L))
      //new long[]{0x0000000800000000L});
      final val FOLLOW_RBRACK_in_guard744:BitSet = new BitSet( Array[Long](0x0000001000000000L))
      //new long[]{0x0000001000000000L});
      final val FOLLOW_IMARK_in_guard746:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LBRACK_in_guard768:BitSet = new BitSet( Array[Long](0x0000188010000000L,0x0000000000000004L))
      //new long[]{0x0000188010000000L,0x0000000000000004L});
      final val FOLLOW_guardExpr_in_guard770:BitSet = new BitSet( Array[Long](0x0000000800000000L))
      //new long[]{0x0000000800000000L});
      final val FOLLOW_RBRACK_in_guard772:BitSet = new BitSet( Array[Long](0x0000001000000000L))
      //new long[]{0x0000001000000000L});
      final val FOLLOW_IMARK_in_guard774:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_variable_in_guardExpr801:BitSet = new BitSet( Array[Long](0x0000004000000000L))
      //new long[]{0x0000004000000000L});
      final val FOLLOW_EQ_in_guardExpr803:BitSet = new BitSet( Array[Long](0x0000180010000000L))
      //new long[]{0x0000180010000000L});
      final val FOLLOW_variable_in_guardExpr805:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_66_in_guardExpr825:BitSet = new BitSet( Array[Long](0x0000000000400000L))
      //new long[]{0x0000000000400000L});
      final val FOLLOW_LPAREN_in_guardExpr827:BitSet = new BitSet( Array[Long](0x0000180010000000L))
      //new long[]{0x0000180010000000L});
      final val FOLLOW_variable_in_guardExpr829:BitSet = new BitSet( Array[Long](0x0000000001000000L))
      //new long[]{0x0000000001000000L});
      final val FOLLOW_RPAREN_in_guardExpr831:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_NOT_in_guardExpr849:BitSet = new BitSet( Array[Long](0x0000000000400000L))
      //new long[]{0x0000000000400000L});
      final val FOLLOW_LPAREN_in_guardExpr851:BitSet = new BitSet( Array[Long](0x0000188010000000L,0x0000000000000004L))
      //new long[]{0x0000188010000000L,0x0000000000000004L});
      final val FOLLOW_guardExpr_in_guardExpr853:BitSet = new BitSet( Array[Long](0x0000000001000000L))
      //new long[]{0x0000000001000000L});
      final val FOLLOW_RPAREN_in_guardExpr855:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_NU_in_nu884:BitSet = new BitSet( Array[Long](0x0000000000400000L))
      //new long[]{0x0000000000400000L});
      final val FOLLOW_LPAREN_in_nu886:BitSet = new BitSet( Array[Long](0x0000180010000000L))
      //new long[]{0x0000180010000000L});
      final val FOLLOW_variable_in_nu888:BitSet = new BitSet( Array[Long](0x0000000005000000L))
      //new long[]{0x0000000005000000L});
      final val FOLLOW_COMMA_in_nu891:BitSet = new BitSet( Array[Long](0x0000180010000000L))
      //new long[]{0x0000180010000000L});
      final val FOLLOW_variable_in_nu893:BitSet = new BitSet( Array[Long](0x0000000005000000L))
      //new long[]{0x0000000005000000L});
      final val FOLLOW_RPAREN_in_nu897:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_67_in_conjunction925:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_atom_in_conjunction939:BitSet = new BitSet( Array[Long](0x0000000004000002L))
      //new long[]{0x0000000004000002L});
      final val FOLLOW_COMMA_in_conjunction942:BitSet = new BitSet( Array[Long](0x0000060010000000L,0x0000000000000008L))
      //new long[]{0x0000060010000000L,0x0000000000000008L});
      final val FOLLOW_atom_in_conjunction944:BitSet = new BitSet( Array[Long](0x0000000004000002L))
      //new long[]{0x0000000004000002L});
      final val FOLLOW_TRUE_in_atom970:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_FALSE_in_atom980:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_relation_in_atom990:BitSet = new BitSet( Array[Long](0x0000000000400002L))
      //new long[]{0x0000000000400002L});
      final val FOLLOW_termlist_in_atom992:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_termlist1036:BitSet = new BitSet( Array[Long](0x0000780010000000L))
      //new long[]{0x0000780010000000L});
      final val FOLLOW_term_in_termlist1038:BitSet = new BitSet( Array[Long](0x0000000005000000L))
      //new long[]{0x0000000005000000L});
      final val FOLLOW_COMMA_in_termlist1041:BitSet = new BitSet( Array[Long](0x0000780010000000L))
      //new long[]{0x0000780010000000L});
      final val FOLLOW_term_in_termlist1043:BitSet = new BitSet( Array[Long](0x0000000005000000L))
      //new long[]{0x0000000005000000L});
      final val FOLLOW_RPAREN_in_termlist1047:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_constant_in_term1072:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_variable_in_term1077:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_R_ID_in_relation1093:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_set_in_variable0:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_set_in_constant0:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_STRING_in_url1149:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});

}