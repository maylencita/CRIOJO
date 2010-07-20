// $ANTLR 3.2 Sep 23, 2009 12:02:23 test_grammar/SCHR.g 2010-06-08 16:45:58

package fr.emn.fullers.parser.schr;

//import basicCHR.interpreter.tree.*;
import fr.emn.fullers.parser.tree._


import org.antlr.runtime._;
import BaseRecognizer.HIDDEN
import java.util.Stack;
//import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree._

import org.antlr.runtime.Token.{DOWN, UP}
 class SCHRParser(input: TokenStream, state:SRecognizerSharedState) 
 extends SParser(input,state) {
 
 	def this(input: TokenStream){
 		this(input, new SRecognizerSharedState())
 	}
 
    val tokenNames:Array[String] = Array[String] (
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SOLVERCLASS", "PROGRAM", "RULE", "CONSTRAINT", "HEAD", "BODY", "CONSDEF", "NAME", "ARITY", "VALUES", "IMPORT", "ALIAS", "DEF", "PARAMS", "START", "LIST", "EMPTYLIST", "TAIL", "PACKAGE", "STRING", "SEMI", "SOLVER", "ID", "LCURL", "RCURL", "ARROBAS", "COMMA", "POINT", "LPAREN", "RPAREN", "IS", "EQ", "LT", "LTEQ", "PLUS", "MINUS", "SLASH", "INT", "LET", "IN", "USE", "LBRACK", "RBRACK", "BAR", "CHR_SIMP", "EQ_OP", "COMMENT", "WS", "ESC_SEQ", "CHAR", "EXPONENT", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC"
    )
      val PACKAGE:Int = 22;
      val EXPONENT:Int = 54;
      val LT:Int = 36;
      val EQ_OP:Int = 49;
      val LBRACK:Int = 45;
      val DEF:Int = 16;
      val POINT:Int = 31;
      val HEAD:Int = 8;
      val OCTAL_ESC:Int = 57;
      val LTEQ:Int = 37;
      val CHAR:Int = 53;
      val ID:Int = 26;
      val EOF:Int = -1;
      val LPAREN:Int = 32;
      val TAIL:Int = 21;
      val ARROBAS:Int = 29;
      val RPAREN:Int = 33;
      val IMPORT:Int = 14;
      val NAME:Int = 11;
      val ESC_SEQ:Int = 52;
      val SLASH:Int = 40;
      val IN:Int = 43;
      val COMMA:Int = 30;
      val IS:Int = 34;
      val PLUS:Int = 38;
      val BODY:Int = 9;
      val EQ:Int = 35;
      val COMMENT:Int = 50;
      val SOLVERCLASS:Int = 4;
      val PARAMS:Int = 17;
      val RBRACK:Int = 46;
      val RULE:Int = 6;
      val UNICODE_ESC:Int = 56;
      val VALUES:Int = 13;
      val HEX_DIGIT:Int = 55;
      val INT:Int = 41;
      val MINUS:Int = 39;
      val LIST:Int = 19;
      val SEMI:Int = 24;
      val LCURL:Int = 27;
      val WS:Int = 51;
      val CONSDEF:Int = 10;
      val RCURL:Int = 28;
      val ALIAS:Int = 15;
      val EMPTYLIST:Int = 20;
      val START:Int = 18;
      val SOLVER:Int = 25;
      val ARITY:Int = 12;
      val PROGRAM:Int = 5;
      val USE:Int = 44;
      val CONSTRAINT:Int = 7;
      val BAR:Int = 47;
      val LET:Int = 42;
      val CHR_SIMP:Int = 48;
      val STRING:Int = 23;

    // delegates
    // delegators


        
    protected var adaptor:TreeAdaptor = new CommonTreeAdaptor()

    def setTreeAdaptor(adaptor:TreeAdaptor) {
        this.adaptor = adaptor
    }
    def  getTreeAdaptor():TreeAdaptor = adaptor

    override def getTokenNames: Array[String] = /*SCHRParser.*/tokenNames 
    override def getGrammarFileName(): String = "test_grammar/SCHR.g"


      class start_return extends ParserRuleReturnScope {
        var solvname:String = _ 
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "start" rule(...)
    // test_grammar/SCHR.g:50:1: start returns [String solvname] : ( packagedef )? ( importdef )* body -> ^( START ( packagedef )? ( importdef )* body ) ;
    @throws(classOf[RecognitionException])
     final def start():/*SCHRParser.*/start_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/start_return  = new /*SCHRParser.*/start_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var packagedef1:/*SCHRParser.*/packagedef_return = null

        var importdef2:/*SCHRParser.*/importdef_return = null

        var body3:/*SCHRParser.*/body_return = null


        var stream_body:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule body");
        var stream_importdef:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule importdef");
        var stream_packagedef:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule packagedef");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:51:2: ( ( packagedef )? ( importdef )* body -> ^( START ( packagedef )? ( importdef )* body ) )
            // test_grammar/SCHR.g:51:4: ( packagedef )? ( importdef )* body
            {
            // test_grammar/SCHR.g:51:4: ( packagedef )?
            var alt1=2;
            var LA1_0 = input.LA(1);

            if ( (LA1_0==PACKAGE) ) {
                alt1=1;
            }
            alt1 match{
                case 1 =>
                    // test_grammar/SCHR.g:0:0: packagedef
                    {
                    pushFollow(FOLLOW_packagedef_in_start138);
                    packagedef1=packagedef();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_packagedef.add(packagedef1.getTree());

                    }
                case _ => //Do nothing
            }

            // test_grammar/SCHR.g:51:16: ( importdef )*
            //loop2:
            var guard = true
            while(guard) {
                var alt2=2;
                var LA2_0 = input.LA(1);

                if ( (LA2_0==USE) ) {
                    alt2=1;
                }


                alt2 match{
            		case 1 =>
            		    // test_grammar/SCHR.g:0:0: importdef
            		    {
            		    pushFollow(FOLLOW_importdef_in_start141);
            		    importdef2=importdef();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_importdef.add(importdef2.getTree());

            		    }
            		case _ =>
            		    guard = false //loop2;
                }
            } //while (true);

            pushFollow(FOLLOW_body_in_start144);
            body3=body();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_body.add(body3.getTree());
            if ( state.backtracking==0 ) {
              retval.solvname = if(body3!=null){body3.solvname}else{null};
            }


            // AST REWRITE
            // elements: body, importdef, packagedef
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 51:60: -> ^( START ( packagedef )? ( importdef )* body )
            {
                // test_grammar/SCHR.g:51:63: ^( START ( packagedef )? ( importdef )* body )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(START, "START").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // test_grammar/SCHR.g:51:71: ( packagedef )?
                if ( stream_packagedef.hasNext() ) {
                    adaptor.addChild(root_1, stream_packagedef.nextTree());

                }
                stream_packagedef.reset();
                // test_grammar/SCHR.g:51:83: ( importdef )*
                while ( stream_importdef.hasNext() ) {
                    adaptor.addChild(root_1, stream_importdef.nextTree());

                }
                stream_importdef.reset();
                adaptor.addChild(root_1, stream_body.nextTree());

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
    // $ANTLR end "start"

      class packagedef_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "packagedef" rule(...)
    // test_grammar/SCHR.g:54:1: packagedef : PACKAGE STRING SEMI -> STRING ;
    @throws(classOf[RecognitionException])
     final def packagedef():/*SCHRParser.*/packagedef_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/packagedef_return  = new /*SCHRParser.*/packagedef_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var PACKAGE4: Token =null
        var STRING5: Token =null
        var SEMI6: Token =null

        var PACKAGE4_tree:CHRTree=null
        var STRING5_tree:CHRTree=null
        var SEMI6_tree:CHRTree=null
        var stream_PACKAGE:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token PACKAGE")
        var stream_SEMI:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SEMI")
        var stream_STRING:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token STRING")

        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:55:2: ( PACKAGE STRING SEMI -> STRING )
            // test_grammar/SCHR.g:55:4: PACKAGE STRING SEMI
            {
            PACKAGE4=smatch(input,PACKAGE,FOLLOW_PACKAGE_in_packagedef172).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_PACKAGE.add(PACKAGE4);

            STRING5=smatch(input,STRING,FOLLOW_STRING_in_packagedef174).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STRING.add(STRING5);

            SEMI6=smatch(input,SEMI,FOLLOW_SEMI_in_packagedef176).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI6);



            // AST REWRITE
            // elements: STRING
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 55:24: -> STRING
            {
                adaptor.addChild(root_0, stream_STRING.nextNode());

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
    // $ANTLR end "packagedef"

      class body_return extends ParserRuleReturnScope {
        var solvname:String = _ 
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "body" rule(...)
    // test_grammar/SCHR.g:58:1: body returns [String solvname] : ( gensolver | program );
    @throws(classOf[RecognitionException])
     final def body():/*SCHRParser.*/body_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/body_return  = new /*SCHRParser.*/body_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var gensolver7:/*SCHRParser.*/gensolver_return = null

        var program8:/*SCHRParser.*/program_return = null



        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:59:2: ( gensolver | program )
            var alt3=2;
            var LA3_0 = input.LA(1);

            if ( (LA3_0==SOLVER) ) {
                alt3=1;
            }
            else if ( (LA3_0==CONSTRAINT||LA3_0==STRING||LA3_0==ID||(LA3_0>=INT && LA3_0<=LET)||LA3_0==LBRACK) ) {
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
                    // test_grammar/SCHR.g:59:4: gensolver
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_gensolver_in_body196);
                    gensolver7=gensolver();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, gensolver7.getTree());
                    if ( state.backtracking==0 ) {
                      retval.solvname = if(gensolver7!=null){gensolver7.solvname}else{null};
                    }

                    }case 2 =>
                    // test_grammar/SCHR.g:60:4: program
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_program_in_body203);
                    program8=program();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, program8.getTree());

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
    // $ANTLR end "body"

      class gensolver_return extends ParserRuleReturnScope {
        var solvname:String = _ 
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "gensolver" rule(...)
    // test_grammar/SCHR.g:63:1: gensolver returns [String solvname] : SOLVER ID ( solvparams )? LCURL program RCURL -> ^( SOLVERCLASS ( ID )? ( solvparams )? program ) ;
    @throws(classOf[RecognitionException])
     final def gensolver():/*SCHRParser.*/gensolver_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/gensolver_return  = new /*SCHRParser.*/gensolver_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var SOLVER9: Token =null
        var ID10: Token =null
        var LCURL12: Token =null
        var RCURL14: Token =null
        var solvparams11:/*SCHRParser.*/solvparams_return = null

        var program13:/*SCHRParser.*/program_return = null


        var SOLVER9_tree:CHRTree=null
        var ID10_tree:CHRTree=null
        var LCURL12_tree:CHRTree=null
        var RCURL14_tree:CHRTree=null
        var stream_SOLVER:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SOLVER")
        var stream_LCURL:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LCURL")
        var stream_ID:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ID")
        var stream_RCURL:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RCURL")
        var stream_program:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule program");
        var stream_solvparams:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule solvparams");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:64:2: ( SOLVER ID ( solvparams )? LCURL program RCURL -> ^( SOLVERCLASS ( ID )? ( solvparams )? program ) )
            // test_grammar/SCHR.g:64:4: SOLVER ID ( solvparams )? LCURL program RCURL
            {
            SOLVER9=smatch(input,SOLVER,FOLLOW_SOLVER_in_gensolver219).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SOLVER.add(SOLVER9);

            ID10=smatch(input,ID,FOLLOW_ID_in_gensolver221).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID10);

            if ( state.backtracking==0 ) {
              retval.solvname = (if(ID10!=null) ID10.getText() else null);
            }
            // test_grammar/SCHR.g:64:37: ( solvparams )?
            var alt4=2;
            var LA4_0 = input.LA(1);

            if ( (LA4_0==LPAREN) ) {
                alt4=1;
            }
            alt4 match{
                case 1 =>
                    // test_grammar/SCHR.g:0:0: solvparams
                    {
                    pushFollow(FOLLOW_solvparams_in_gensolver224);
                    solvparams11=solvparams();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_solvparams.add(solvparams11.getTree());

                    }
                case _ => //Do nothing
            }

            LCURL12=smatch(input,LCURL,FOLLOW_LCURL_in_gensolver227).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURL.add(LCURL12);

            pushFollow(FOLLOW_program_in_gensolver229);
            program13=program();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_program.add(program13.getTree());
            RCURL14=smatch(input,RCURL,FOLLOW_RCURL_in_gensolver231).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURL.add(RCURL14);



            // AST REWRITE
            // elements: solvparams, program, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 64:69: -> ^( SOLVERCLASS ( ID )? ( solvparams )? program )
            {
                // test_grammar/SCHR.g:64:72: ^( SOLVERCLASS ( ID )? ( solvparams )? program )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(SOLVERCLASS, "SOLVERCLASS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // test_grammar/SCHR.g:64:86: ( ID )?
                if ( stream_ID.hasNext() ) {
                    adaptor.addChild(root_1, stream_ID.nextNode());

                }
                stream_ID.reset();
                // test_grammar/SCHR.g:64:90: ( solvparams )?
                if ( stream_solvparams.hasNext() ) {
                    adaptor.addChild(root_1, stream_solvparams.nextTree());

                }
                stream_solvparams.reset();
                adaptor.addChild(root_1, stream_program.nextTree());

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
    // $ANTLR end "gensolver"

      class program_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "program" rule(...)
    // test_grammar/SCHR.g:67:1: program : expression ( expression )* -> ^( PROGRAM ( expression )* ) ;
    @throws(classOf[RecognitionException])
     final def program():/*SCHRParser.*/program_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/program_return  = new /*SCHRParser.*/program_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var expression15:/*SCHRParser.*/expression_return = null

        var expression16:/*SCHRParser.*/expression_return = null


        var stream_expression:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule expression");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:68:2: ( expression ( expression )* -> ^( PROGRAM ( expression )* ) )
            // test_grammar/SCHR.g:68:4: expression ( expression )*
            {
            pushFollow(FOLLOW_expression_in_program257);
            expression15=expression();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression15.getTree());
            // test_grammar/SCHR.g:68:15: ( expression )*
            //loop5:
            var guard = true
            while(guard) {
                var alt5=2;
                var LA5_0 = input.LA(1);

                if ( (LA5_0==CONSTRAINT||LA5_0==STRING||LA5_0==ID||(LA5_0>=INT && LA5_0<=LET)||LA5_0==LBRACK) ) {
                    alt5=1;
                }


                alt5 match{
            		case 1 =>
            		    // test_grammar/SCHR.g:0:0: expression
            		    {
            		    pushFollow(FOLLOW_expression_in_program259);
            		    expression16=expression();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_expression.add(expression16.getTree());

            		    }
            		case _ =>
            		    guard = false //loop5;
                }
            } //while (true);



            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 68:27: -> ^( PROGRAM ( expression )* )
            {
                // test_grammar/SCHR.g:68:30: ^( PROGRAM ( expression )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(PROGRAM, "PROGRAM").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // test_grammar/SCHR.g:68:40: ( expression )*
                while ( stream_expression.hasNext() ) {
                    adaptor.addChild(root_1, stream_expression.nextTree());

                }
                stream_expression.reset();

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
    // $ANTLR end "program"

      class ruledef_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "ruledef" rule(...)
    // test_grammar/SCHR.g:71:1: ruledef : ( ruleId ARROBAS )? constraintList '<=>' constraintList SEMI -> ^( RULE ( ruleId )? ^( HEAD constraintList ) ^( BODY constraintList ) ) ;
    @throws(classOf[RecognitionException])
     final def ruledef():/*SCHRParser.*/ruledef_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/ruledef_return  = new /*SCHRParser.*/ruledef_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var ARROBAS18: Token =null
        var string_literal20: Token =null
        var SEMI22: Token =null
        var ruleId17:/*SCHRParser.*/ruleId_return = null

        var constraintList19:/*SCHRParser.*/constraintList_return = null

        var constraintList21:/*SCHRParser.*/constraintList_return = null


        var ARROBAS18_tree:CHRTree=null
        var string_literal20_tree:CHRTree=null
        var SEMI22_tree:CHRTree=null
        var stream_SEMI:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SEMI")
        var stream_ARROBAS:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ARROBAS")
        var stream_CHR_SIMP:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token CHR_SIMP")
        var stream_ruleId:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule ruleId");
        var stream_constraintList:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule constraintList");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:72:2: ( ( ruleId ARROBAS )? constraintList '<=>' constraintList SEMI -> ^( RULE ( ruleId )? ^( HEAD constraintList ) ^( BODY constraintList ) ) )
            // test_grammar/SCHR.g:72:4: ( ruleId ARROBAS )? constraintList '<=>' constraintList SEMI
            {
            // test_grammar/SCHR.g:72:4: ( ruleId ARROBAS )?
            var alt6=2;
            var LA6_0 = input.LA(1);

            if ( (LA6_0==ID) ) {
                var LA6_1 = input.LA(2);

                if ( (LA6_1==ARROBAS) ) {
                    alt6=1;
                }
            }
            alt6 match{
                case 1 =>
                    // test_grammar/SCHR.g:72:5: ruleId ARROBAS
                    {
                    pushFollow(FOLLOW_ruleId_in_ruledef282);
                    ruleId17=ruleId();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleId.add(ruleId17.getTree());
                    ARROBAS18=smatch(input,ARROBAS,FOLLOW_ARROBAS_in_ruledef284).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ARROBAS.add(ARROBAS18);


                    }
                case _ => //Do nothing
            }

            pushFollow(FOLLOW_constraintList_in_ruledef288);
            constraintList19=constraintList();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_constraintList.add(constraintList19.getTree());
            string_literal20=smatch(input,CHR_SIMP,FOLLOW_CHR_SIMP_in_ruledef290).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CHR_SIMP.add(string_literal20);

            pushFollow(FOLLOW_constraintList_in_ruledef292);
            constraintList21=constraintList();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_constraintList.add(constraintList21.getTree());
            SEMI22=smatch(input,SEMI,FOLLOW_SEMI_in_ruledef294).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI22);



            // AST REWRITE
            // elements: constraintList, ruleId, constraintList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 73:3: -> ^( RULE ( ruleId )? ^( HEAD constraintList ) ^( BODY constraintList ) )
            {
                // test_grammar/SCHR.g:73:6: ^( RULE ( ruleId )? ^( HEAD constraintList ) ^( BODY constraintList ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(RULE, "RULE").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // test_grammar/SCHR.g:73:13: ( ruleId )?
                if ( stream_ruleId.hasNext() ) {
                    adaptor.addChild(root_1, stream_ruleId.nextTree());

                }
                stream_ruleId.reset();
                // test_grammar/SCHR.g:73:21: ^( HEAD constraintList )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(HEAD, "HEAD").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_constraintList.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // test_grammar/SCHR.g:73:44: ^( BODY constraintList )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(BODY, "BODY").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_constraintList.nextTree());

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
    // $ANTLR end "ruledef"

      class constraintList_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "constraintList" rule(...)
    // test_grammar/SCHR.g:76:1: constraintList : constraint ( COMMA constraint )* -> ( constraint )* ;
    @throws(classOf[RecognitionException])
     final def constraintList():/*SCHRParser.*/constraintList_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/constraintList_return  = new /*SCHRParser.*/constraintList_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var COMMA24: Token =null
        var constraint23:/*SCHRParser.*/constraint_return = null

        var constraint25:/*SCHRParser.*/constraint_return = null


        var COMMA24_tree:CHRTree=null
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_constraint:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule constraint");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:77:2: ( constraint ( COMMA constraint )* -> ( constraint )* )
            // test_grammar/SCHR.g:77:4: constraint ( COMMA constraint )*
            {
            pushFollow(FOLLOW_constraint_in_constraintList331);
            constraint23=constraint();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_constraint.add(constraint23.getTree());
            // test_grammar/SCHR.g:77:15: ( COMMA constraint )*
            //loop7:
            var guard = true
            while(guard) {
                var alt7=2;
                var LA7_0 = input.LA(1);

                if ( (LA7_0==COMMA) ) {
                    alt7=1;
                }


                alt7 match{
            		case 1 =>
            		    // test_grammar/SCHR.g:77:16: COMMA constraint
            		    {
            		    COMMA24=smatch(input,COMMA,FOLLOW_COMMA_in_constraintList334).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA24);

            		    pushFollow(FOLLOW_constraint_in_constraintList336);
            		    constraint25=constraint();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_constraint.add(constraint25.getTree());

            		    }
            		case _ =>
            		    guard = false //loop7;
                }
            } //while (true);



            // AST REWRITE
            // elements: constraint
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 77:35: -> ( constraint )*
            {
                // test_grammar/SCHR.g:77:38: ( constraint )*
                while ( stream_constraint.hasNext() ) {
                    adaptor.addChild(root_0, stream_constraint.nextTree());

                }
                stream_constraint.reset();

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
    // $ANTLR end "constraintList"

      class constraint_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "constraint" rule(...)
    // test_grammar/SCHR.g:80:1: constraint : ( ( solvId POINT )? consId ( LPAREN term ( COMMA term )* RPAREN )? -> ^( CONSTRAINT ^( SOLVER ( solvId )? ) ^( NAME consId ) ^( VALUES ( term )* ) ) | builtinconstraint );
    @throws(classOf[RecognitionException])
     final def constraint():/*SCHRParser.*/constraint_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/constraint_return  = new /*SCHRParser.*/constraint_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var POINT27: Token =null
        var LPAREN29: Token =null
        var COMMA31: Token =null
        var RPAREN33: Token =null
        var solvId26:/*SCHRParser.*/solvId_return = null

        var consId28:/*SCHRParser.*/consId_return = null

        var term30:/*SCHRParser.*/term_return = null

        var term32:/*SCHRParser.*/term_return = null

        var builtinconstraint34:/*SCHRParser.*/builtinconstraint_return = null


        var POINT27_tree:CHRTree=null
        var LPAREN29_tree:CHRTree=null
        var COMMA31_tree:CHRTree=null
        var RPAREN33_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_POINT:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token POINT")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")
        var stream_solvId:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule solvId");
        var stream_consId:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule consId");
        var stream_term:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule term");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:81:2: ( ( solvId POINT )? consId ( LPAREN term ( COMMA term )* RPAREN )? -> ^( CONSTRAINT ^( SOLVER ( solvId )? ) ^( NAME consId ) ^( VALUES ( term )* ) ) | builtinconstraint )
            var alt11=2;
            var LA11_0 = input.LA(1);

            if ( (LA11_0==ID) ) {
                var LA11_1 = input.LA(2);

                if ( (LA11_1==EOF||LA11_1==SEMI||(LA11_1>=COMMA && LA11_1<=LPAREN)||LA11_1==CHR_SIMP) ) {
                    alt11=1;
                }
                else if ( ((LA11_1>=IS && LA11_1<=MINUS)) ) {
                    alt11=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    val nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA11_0==STRING||LA11_0==INT||LA11_0==LBRACK) ) {
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
                    // test_grammar/SCHR.g:81:4: ( solvId POINT )? consId ( LPAREN term ( COMMA term )* RPAREN )?
                    {
                    // test_grammar/SCHR.g:81:4: ( solvId POINT )?
                    var alt8=2;
                    var LA8_0 = input.LA(1);

                    if ( (LA8_0==ID) ) {
                        var LA8_1 = input.LA(2);

                        if ( (LA8_1==POINT) ) {
                            alt8=1;
                        }
                    }
                    alt8 match{
                        case 1 =>
                            // test_grammar/SCHR.g:81:5: solvId POINT
                            {
                            pushFollow(FOLLOW_solvId_in_constraint357);
                            solvId26=solvId();

                            state._fsp -= 1 
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_solvId.add(solvId26.getTree());
                            POINT27=smatch(input,POINT,FOLLOW_POINT_in_constraint359).asInstanceOf[Token]; if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_POINT.add(POINT27);


                            }
                        case _ => //Do nothing
                    }

                    pushFollow(FOLLOW_consId_in_constraint364);
                    consId28=consId();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_consId.add(consId28.getTree());
                    // test_grammar/SCHR.g:81:28: ( LPAREN term ( COMMA term )* RPAREN )?
                    var alt10=2;
                    var LA10_0 = input.LA(1);

                    if ( (LA10_0==LPAREN) ) {
                        alt10=1;
                    }
                    alt10 match{
                        case 1 =>
                            // test_grammar/SCHR.g:81:30: LPAREN term ( COMMA term )* RPAREN
                            {
                            LPAREN29=smatch(input,LPAREN,FOLLOW_LPAREN_in_constraint368).asInstanceOf[Token]; if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN29);

                            pushFollow(FOLLOW_term_in_constraint370);
                            term30=term();

                            state._fsp -= 1 
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_term.add(term30.getTree());
                            // test_grammar/SCHR.g:81:42: ( COMMA term )*
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
                            		    // test_grammar/SCHR.g:81:43: COMMA term
                            		    {
                            		    COMMA31=smatch(input,COMMA,FOLLOW_COMMA_in_constraint373).asInstanceOf[Token]; if (state.failed) return retval; 
                            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA31);

                            		    pushFollow(FOLLOW_term_in_constraint375);
                            		    term32=term();

                            		    state._fsp -= 1 
                            		    if (state.failed) return retval;
                            		    if ( state.backtracking==0 ) stream_term.add(term32.getTree());

                            		    }
                            		case _ =>
                            		    guard = false //loop9;
                                }
                            } //while (true);

                            RPAREN33=smatch(input,RPAREN,FOLLOW_RPAREN_in_constraint379).asInstanceOf[Token]; if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN33);


                            }
                        case _ => //Do nothing
                    }



                    // AST REWRITE
                    // elements: term, solvId, consId
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 82:3: -> ^( CONSTRAINT ^( SOLVER ( solvId )? ) ^( NAME consId ) ^( VALUES ( term )* ) )
                    {
                        // test_grammar/SCHR.g:82:6: ^( CONSTRAINT ^( SOLVER ( solvId )? ) ^( NAME consId ) ^( VALUES ( term )* ) )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(CONSTRAINT, "CONSTRAINT").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // test_grammar/SCHR.g:82:19: ^( SOLVER ( solvId )? )
                        {
                        var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_2 = adaptor.becomeRoot(adaptor.create(SOLVER, "SOLVER").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                        // test_grammar/SCHR.g:82:28: ( solvId )?
                        if ( stream_solvId.hasNext() ) {
                            adaptor.addChild(root_2, stream_solvId.nextTree());

                        }
                        stream_solvId.reset();

                        adaptor.addChild(root_1, root_2);
                        }
                        // test_grammar/SCHR.g:82:37: ^( NAME consId )
                        {
                        var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_2 = adaptor.becomeRoot(adaptor.create(NAME, "NAME").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                        adaptor.addChild(root_2, stream_consId.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }
                        // test_grammar/SCHR.g:82:52: ^( VALUES ( term )* )
                        {
                        var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_2 = adaptor.becomeRoot(adaptor.create(VALUES, "VALUES").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                        // test_grammar/SCHR.g:82:61: ( term )*
                        while ( stream_term.hasNext() ) {
                            adaptor.addChild(root_2, stream_term.nextTree());

                        }
                        stream_term.reset();

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // test_grammar/SCHR.g:83:4: builtinconstraint
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_builtinconstraint_in_constraint416);
                    builtinconstraint34=builtinconstraint();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, builtinconstraint34.getTree());

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
    // $ANTLR end "constraint"

      class builtinconstraint_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "builtinconstraint" rule(...)
    // test_grammar/SCHR.g:86:1: builtinconstraint : ( term op term -> ^( CONSTRAINT ^( NAME op ) ^( VALUES term term ) ) | term IS term op term -> ^( CONSTRAINT ^( NAME op ) ^( VALUES term term term ) ) );
    @throws(classOf[RecognitionException])
     final def builtinconstraint():/*SCHRParser.*/builtinconstraint_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/builtinconstraint_return  = new /*SCHRParser.*/builtinconstraint_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var IS39: Token =null
        var term35:/*SCHRParser.*/term_return = null

        var op36:/*SCHRParser.*/op_return = null

        var term37:/*SCHRParser.*/term_return = null

        var term38:/*SCHRParser.*/term_return = null

        var term40:/*SCHRParser.*/term_return = null

        var op41:/*SCHRParser.*/op_return = null

        var term42:/*SCHRParser.*/term_return = null


        var IS39_tree:CHRTree=null
        var stream_IS:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token IS")
        var stream_op:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule op");
        var stream_term:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule term");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:87:2: ( term op term -> ^( CONSTRAINT ^( NAME op ) ^( VALUES term term ) ) | term IS term op term -> ^( CONSTRAINT ^( NAME op ) ^( VALUES term term term ) ) )
            var alt12=2;
            input.LA(1) match{
            	case STRING => var LA12_1 = input.LA(2);

            	if ( (synpred12_SCHR()) ) {
            	    alt12=1;
            	}
            	else if ( (true) ) {
            	    alt12=2;
            	}
            	else {
            	    if (state.backtracking>0) {state.failed=true; return retval;}
            	    val nvae =
            	        new NoViableAltException("", 12, 1, input);

            	    throw nvae;
            	}
            	case INT => var LA12_1 = input.LA(2);

            	if ( (synpred12_SCHR()) ) {
            	    alt12=1;
            	}
            	else if ( (true) ) {
            	    alt12=2;
            	}
            	else {
            	    if (state.backtracking>0) {state.failed=true; return retval;}
            	    val nvae =
            	        new NoViableAltException("", 12, 1, input);

            	    throw nvae;
            	}    
            	case ID => var LA12_2 = input.LA(2);

            	if ( (synpred12_SCHR()) ) {
            	    alt12=1;
            	}
            	else if ( (true) ) {
            	    alt12=2;
            	}
            	else {
            	    if (state.backtracking>0) {state.failed=true; return retval;}
            	    val nvae =
            	        new NoViableAltException("", 12, 2, input);

            	    throw nvae;
            	}    
            	case LBRACK => var LA12_3 = input.LA(2);

            	if ( (synpred12_SCHR()) ) {
            	    alt12=1;
            	}
            	else if ( (true) ) {
            	    alt12=2;
            	}
            	else {
            	    if (state.backtracking>0) {state.failed=true; return retval;}
            	    val nvae =
            	        new NoViableAltException("", 12, 3, input);

            	    throw nvae;
            	}    
            	case _ =>
            		    if (state.backtracking>0) {state.failed=true; return retval;}
            		    val nvae =
            		        new NoViableAltException("", 12, 0, input);

            		    throw nvae;

            }

            alt12 match{
                case 1 =>
                    // test_grammar/SCHR.g:87:4: term op term
                    {
                    pushFollow(FOLLOW_term_in_builtinconstraint430);
                    term35=term();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_term.add(term35.getTree());
                    pushFollow(FOLLOW_op_in_builtinconstraint432);
                    op36=op();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_op.add(op36.getTree());
                    pushFollow(FOLLOW_term_in_builtinconstraint434);
                    term37=term();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_term.add(term37.getTree());


                    // AST REWRITE
                    // elements: term, term, op
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 87:17: -> ^( CONSTRAINT ^( NAME op ) ^( VALUES term term ) )
                    {
                        // test_grammar/SCHR.g:87:20: ^( CONSTRAINT ^( NAME op ) ^( VALUES term term ) )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(CONSTRAINT, "CONSTRAINT").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // test_grammar/SCHR.g:87:33: ^( NAME op )
                        {
                        var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_2 = adaptor.becomeRoot(adaptor.create(NAME, "NAME").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                        adaptor.addChild(root_2, stream_op.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }
                        // test_grammar/SCHR.g:87:44: ^( VALUES term term )
                        {
                        var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_2 = adaptor.becomeRoot(adaptor.create(VALUES, "VALUES").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                        adaptor.addChild(root_2, stream_term.nextTree());
                        adaptor.addChild(root_2, stream_term.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // test_grammar/SCHR.g:88:4: term IS term op term
                    {
                    pushFollow(FOLLOW_term_in_builtinconstraint461);
                    term38=term();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_term.add(term38.getTree());
                    IS39=smatch(input,IS,FOLLOW_IS_in_builtinconstraint463).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IS.add(IS39);

                    pushFollow(FOLLOW_term_in_builtinconstraint465);
                    term40=term();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_term.add(term40.getTree());
                    pushFollow(FOLLOW_op_in_builtinconstraint467);
                    op41=op();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_op.add(op41.getTree());
                    pushFollow(FOLLOW_term_in_builtinconstraint469);
                    term42=term();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_term.add(term42.getTree());


                    // AST REWRITE
                    // elements: term, term, op, term
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 88:25: -> ^( CONSTRAINT ^( NAME op ) ^( VALUES term term term ) )
                    {
                        // test_grammar/SCHR.g:88:28: ^( CONSTRAINT ^( NAME op ) ^( VALUES term term term ) )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(CONSTRAINT, "CONSTRAINT").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // test_grammar/SCHR.g:88:41: ^( NAME op )
                        {
                        var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_2 = adaptor.becomeRoot(adaptor.create(NAME, "NAME").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                        adaptor.addChild(root_2, stream_op.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }
                        // test_grammar/SCHR.g:88:52: ^( VALUES term term term )
                        {
                        var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_2 = adaptor.becomeRoot(adaptor.create(VALUES, "VALUES").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                        adaptor.addChild(root_2, stream_term.nextTree());
                        adaptor.addChild(root_2, stream_term.nextTree());
                        adaptor.addChild(root_2, stream_term.nextTree());

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
    // $ANTLR end "builtinconstraint"

      class op_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "op" rule(...)
    // test_grammar/SCHR.g:91:1: op : ( EQ | LT | LTEQ | PLUS | MINUS );
    @throws(classOf[RecognitionException])
     final def op():/*SCHRParser.*/op_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/op_return  = new /*SCHRParser.*/op_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var set43: Token =null

        var set43_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:92:2: ( EQ | LT | LTEQ | PLUS | MINUS )
            // test_grammar/SCHR.g:
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            set43=input.LT(1).asInstanceOf[Token]
            if ( (input.LA(1)>=EQ && input.LA(1)<=MINUS) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, adaptor.create(set43).asInstanceOf[CHRTree]);
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
    // $ANTLR end "op"

      class constraintsdef_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "constraintsdef" rule(...)
    // test_grammar/SCHR.g:95:1: constraintsdef : CONSTRAINT constraintdef ( COMMA constraintdef )* SEMI -> ^( CONSDEF ( constraintdef )* ) ;
    @throws(classOf[RecognitionException])
     final def constraintsdef():/*SCHRParser.*/constraintsdef_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/constraintsdef_return  = new /*SCHRParser.*/constraintsdef_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var CONSTRAINT44: Token =null
        var COMMA46: Token =null
        var SEMI48: Token =null
        var constraintdef45:/*SCHRParser.*/constraintdef_return = null

        var constraintdef47:/*SCHRParser.*/constraintdef_return = null


        var CONSTRAINT44_tree:CHRTree=null
        var COMMA46_tree:CHRTree=null
        var SEMI48_tree:CHRTree=null
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_CONSTRAINT:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token CONSTRAINT")
        var stream_SEMI:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SEMI")
        var stream_constraintdef:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule constraintdef");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:96:2: ( CONSTRAINT constraintdef ( COMMA constraintdef )* SEMI -> ^( CONSDEF ( constraintdef )* ) )
            // test_grammar/SCHR.g:96:4: CONSTRAINT constraintdef ( COMMA constraintdef )* SEMI
            {
            CONSTRAINT44=smatch(input,CONSTRAINT,FOLLOW_CONSTRAINT_in_constraintsdef532).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CONSTRAINT.add(CONSTRAINT44);

            pushFollow(FOLLOW_constraintdef_in_constraintsdef534);
            constraintdef45=constraintdef();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_constraintdef.add(constraintdef45.getTree());
            // test_grammar/SCHR.g:96:29: ( COMMA constraintdef )*
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
            		    // test_grammar/SCHR.g:96:30: COMMA constraintdef
            		    {
            		    COMMA46=smatch(input,COMMA,FOLLOW_COMMA_in_constraintsdef537).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA46);

            		    pushFollow(FOLLOW_constraintdef_in_constraintsdef539);
            		    constraintdef47=constraintdef();

            		    state._fsp -= 1 
            		    if (state.failed) return retval;
            		    if ( state.backtracking==0 ) stream_constraintdef.add(constraintdef47.getTree());

            		    }
            		case _ =>
            		    guard = false //loop13;
                }
            } //while (true);

            SEMI48=smatch(input,SEMI,FOLLOW_SEMI_in_constraintsdef543).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI48);



            // AST REWRITE
            // elements: constraintdef
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 97:3: -> ^( CONSDEF ( constraintdef )* )
            {
                // test_grammar/SCHR.g:97:6: ^( CONSDEF ( constraintdef )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(CONSDEF, "CONSDEF").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // test_grammar/SCHR.g:97:16: ( constraintdef )*
                while ( stream_constraintdef.hasNext() ) {
                    adaptor.addChild(root_1, stream_constraintdef.nextTree());

                }
                stream_constraintdef.reset();

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
    // $ANTLR end "constraintsdef"

      class constraintdef_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "constraintdef" rule(...)
    // test_grammar/SCHR.g:100:1: constraintdef : ID SLASH INT -> ^( CONSTRAINT ^( NAME ID ) ^( ARITY INT ) ) ;
    @throws(classOf[RecognitionException])
     final def constraintdef():/*SCHRParser.*/constraintdef_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/constraintdef_return  = new /*SCHRParser.*/constraintdef_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var ID49: Token =null
        var SLASH50: Token =null
        var INT51: Token =null

        var ID49_tree:CHRTree=null
        var SLASH50_tree:CHRTree=null
        var INT51_tree:CHRTree=null
        var stream_SLASH:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SLASH")
        var stream_INT:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token INT")
        var stream_ID:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ID")

        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:101:2: ( ID SLASH INT -> ^( CONSTRAINT ^( NAME ID ) ^( ARITY INT ) ) )
            // test_grammar/SCHR.g:101:4: ID SLASH INT
            {
            ID49=smatch(input,ID,FOLLOW_ID_in_constraintdef568).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID49);

            SLASH50=smatch(input,SLASH,FOLLOW_SLASH_in_constraintdef570).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SLASH.add(SLASH50);

            INT51=smatch(input,INT,FOLLOW_INT_in_constraintdef572).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_INT.add(INT51);



            // AST REWRITE
            // elements: INT, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 101:17: -> ^( CONSTRAINT ^( NAME ID ) ^( ARITY INT ) )
            {
                // test_grammar/SCHR.g:101:20: ^( CONSTRAINT ^( NAME ID ) ^( ARITY INT ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(CONSTRAINT, "CONSTRAINT").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // test_grammar/SCHR.g:101:33: ^( NAME ID )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(NAME, "NAME").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // test_grammar/SCHR.g:101:44: ^( ARITY INT )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(ARITY, "ARITY").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_INT.nextNode());

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
    // $ANTLR end "constraintdef"

      class axiom_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "axiom" rule(...)
    // test_grammar/SCHR.g:104:1: axiom : constraint SEMI -> constraint ;
    @throws(classOf[RecognitionException])
     final def axiom():/*SCHRParser.*/axiom_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/axiom_return  = new /*SCHRParser.*/axiom_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var SEMI53: Token =null
        var constraint52:/*SCHRParser.*/constraint_return = null


        var SEMI53_tree:CHRTree=null
        var stream_SEMI:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SEMI")
        var stream_constraint:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule constraint");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:104:7: ( constraint SEMI -> constraint )
            // test_grammar/SCHR.g:104:9: constraint SEMI
            {
            pushFollow(FOLLOW_constraint_in_axiom602);
            constraint52=constraint();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_constraint.add(constraint52.getTree());
            SEMI53=smatch(input,SEMI,FOLLOW_SEMI_in_axiom604).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI53);



            // AST REWRITE
            // elements: constraint
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 104:25: -> constraint
            {
                adaptor.addChild(root_0, stream_constraint.nextTree());

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
    // $ANTLR end "axiom"

      class expression_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "expression" rule(...)
    // test_grammar/SCHR.g:107:1: expression : ( constraintsdef | ruledef | axiom | letexpr );
    @throws(classOf[RecognitionException])
     final def expression():/*SCHRParser.*/expression_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/expression_return  = new /*SCHRParser.*/expression_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var constraintsdef54:/*SCHRParser.*/constraintsdef_return = null

        var ruledef55:/*SCHRParser.*/ruledef_return = null

        var axiom56:/*SCHRParser.*/axiom_return = null

        var letexpr57:/*SCHRParser.*/letexpr_return = null



        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:108:2: ( constraintsdef | ruledef | axiom | letexpr )
            var alt14=4;
            input.LA(1) match{
            	case CONSTRAINT => alt14=1;    
            	case ID => var LA14_2 = input.LA(2);

            	if ( (synpred19_SCHR()) ) {
            	    alt14=2;
            	}
            	else if ( (synpred20_SCHR()) ) {
            	    alt14=3;
            	}
            	else {
            	    if (state.backtracking>0) {state.failed=true; return retval;}
            	    val nvae =
            	        new NoViableAltException("", 14, 2, input);

            	    throw nvae;
            	}    
            	case STRING => var LA14_3 = input.LA(2);

            	if ( (synpred19_SCHR()) ) {
            	    alt14=2;
            	}
            	else if ( (synpred20_SCHR()) ) {
            	    alt14=3;
            	}
            	else {
            	    if (state.backtracking>0) {state.failed=true; return retval;}
            	    val nvae =
            	        new NoViableAltException("", 14, 3, input);

            	    throw nvae;
            	}
            	case INT => var LA14_3 = input.LA(2);

            	if ( (synpred19_SCHR()) ) {
            	    alt14=2;
            	}
            	else if ( (synpred20_SCHR()) ) {
            	    alt14=3;
            	}
            	else {
            	    if (state.backtracking>0) {state.failed=true; return retval;}
            	    val nvae =
            	        new NoViableAltException("", 14, 3, input);

            	    throw nvae;
            	}    
            	case LBRACK => var LA14_4 = input.LA(2);

            	if ( (synpred19_SCHR()) ) {
            	    alt14=2;
            	}
            	else if ( (synpred20_SCHR()) ) {
            	    alt14=3;
            	}
            	else {
            	    if (state.backtracking>0) {state.failed=true; return retval;}
            	    val nvae =
            	        new NoViableAltException("", 14, 4, input);

            	    throw nvae;
            	}    
            	case LET => alt14=4;    
            	case _ =>
            		    if (state.backtracking>0) {state.failed=true; return retval;}
            		    val nvae =
            		        new NoViableAltException("", 14, 0, input);

            		    throw nvae;

            }

            alt14 match{
                case 1 =>
                    // test_grammar/SCHR.g:108:4: constraintsdef
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_constraintsdef_in_expression622);
                    constraintsdef54=constraintsdef();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constraintsdef54.getTree());

                    }case 2 =>
                    // test_grammar/SCHR.g:109:4: ruledef
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_ruledef_in_expression627);
                    ruledef55=ruledef();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ruledef55.getTree());

                    }case 3 =>
                    // test_grammar/SCHR.g:110:4: axiom
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_axiom_in_expression632);
                    axiom56=axiom();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, axiom56.getTree());

                    }case 4 =>
                    // test_grammar/SCHR.g:111:4: letexpr
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_letexpr_in_expression637);
                    letexpr57=letexpr();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, letexpr57.getTree());

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
    // $ANTLR end "expression"

      class letexpr_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "letexpr" rule(...)
    // test_grammar/SCHR.g:115:1: letexpr : LET solvId EQ solvdef IN LCURL program RCURL -> ^( LET ^( NAME solvId ) ^( DEF solvdef ) ^( IN program ) ) ;
    @throws(classOf[RecognitionException])
     final def letexpr():/*SCHRParser.*/letexpr_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/letexpr_return  = new /*SCHRParser.*/letexpr_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LET58: Token =null
        var EQ60: Token =null
        var IN62: Token =null
        var LCURL63: Token =null
        var RCURL65: Token =null
        var solvId59:/*SCHRParser.*/solvId_return = null

        var solvdef61:/*SCHRParser.*/solvdef_return = null

        var program64:/*SCHRParser.*/program_return = null


        var LET58_tree:CHRTree=null
        var EQ60_tree:CHRTree=null
        var IN62_tree:CHRTree=null
        var LCURL63_tree:CHRTree=null
        var RCURL65_tree:CHRTree=null
        var stream_LCURL:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LCURL")
        var stream_IN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token IN")
        var stream_EQ:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token EQ")
        var stream_LET:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LET")
        var stream_RCURL:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RCURL")
        var stream_solvdef:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule solvdef");
        var stream_solvId:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule solvId");
        var stream_program:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule program");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:116:2: ( LET solvId EQ solvdef IN LCURL program RCURL -> ^( LET ^( NAME solvId ) ^( DEF solvdef ) ^( IN program ) ) )
            // test_grammar/SCHR.g:117:3: LET solvId EQ solvdef IN LCURL program RCURL
            {
            LET58=smatch(input,LET,FOLLOW_LET_in_letexpr652).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LET.add(LET58);

            pushFollow(FOLLOW_solvId_in_letexpr654);
            solvId59=solvId();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_solvId.add(solvId59.getTree());
            EQ60=smatch(input,EQ,FOLLOW_EQ_in_letexpr656).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQ.add(EQ60);

            pushFollow(FOLLOW_solvdef_in_letexpr658);
            solvdef61=solvdef();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_solvdef.add(solvdef61.getTree());
            IN62=smatch(input,IN,FOLLOW_IN_in_letexpr660).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IN.add(IN62);

            LCURL63=smatch(input,LCURL,FOLLOW_LCURL_in_letexpr662).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURL.add(LCURL63);

            pushFollow(FOLLOW_program_in_letexpr664);
            program64=program();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_program.add(program64.getTree());
            RCURL65=smatch(input,RCURL,FOLLOW_RCURL_in_letexpr666).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURL.add(RCURL65);



            // AST REWRITE
            // elements: program, LET, solvdef, IN, solvId
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 118:3: -> ^( LET ^( NAME solvId ) ^( DEF solvdef ) ^( IN program ) )
            {
                // test_grammar/SCHR.g:118:6: ^( LET ^( NAME solvId ) ^( DEF solvdef ) ^( IN program ) )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(stream_LET.nextNode(), root_1).asInstanceOf[CHRTree]

                // test_grammar/SCHR.g:118:12: ^( NAME solvId )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(NAME, "NAME").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_solvId.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // test_grammar/SCHR.g:118:27: ^( DEF solvdef )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(adaptor.create(DEF, "DEF").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_solvdef.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // test_grammar/SCHR.g:118:42: ^( IN program )
                {
                var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_2 = adaptor.becomeRoot(stream_IN.nextNode(), root_2).asInstanceOf[CHRTree]

                adaptor.addChild(root_2, stream_program.nextTree());

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
    // $ANTLR end "letexpr"

      class solvdef_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "solvdef" rule(...)
    // test_grammar/SCHR.g:121:1: solvdef : ( solvinstance | program );
    @throws(classOf[RecognitionException])
     final def solvdef():/*SCHRParser.*/solvdef_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/solvdef_return  = new /*SCHRParser.*/solvdef_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var solvinstance66:/*SCHRParser.*/solvinstance_return = null

        var program67:/*SCHRParser.*/program_return = null



        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:122:2: ( solvinstance | program )
            var alt15=2;
            alt15 = dfa15.predict(input);
            alt15 match{
                case 1 =>
                    // test_grammar/SCHR.g:123:3: solvinstance
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_solvinstance_in_solvdef710);
                    solvinstance66=solvinstance();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, solvinstance66.getTree());

                    }case 2 =>
                    // test_grammar/SCHR.g:123:18: program
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_program_in_solvdef714);
                    program67=program();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, program67.getTree());

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
    // $ANTLR end "solvdef"

      class importdef_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "importdef" rule(...)
    // test_grammar/SCHR.g:126:1: importdef : USE solvname SEMI -> ^( IMPORT solvname ) ;
    @throws(classOf[RecognitionException])
     final def importdef():/*SCHRParser.*/importdef_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/importdef_return  = new /*SCHRParser.*/importdef_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var USE68: Token =null
        var SEMI70: Token =null
        var solvname69:/*SCHRParser.*/solvname_return = null


        var USE68_tree:CHRTree=null
        var SEMI70_tree:CHRTree=null
        var stream_USE:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token USE")
        var stream_SEMI:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token SEMI")
        var stream_solvname:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule solvname");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:127:2: ( USE solvname SEMI -> ^( IMPORT solvname ) )
            // test_grammar/SCHR.g:127:4: USE solvname SEMI
            {
            USE68=smatch(input,USE,FOLLOW_USE_in_importdef729).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_USE.add(USE68);

            pushFollow(FOLLOW_solvname_in_importdef731);
            solvname69=solvname();

            state._fsp -= 1 
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_solvname.add(solvname69.getTree());
            SEMI70=smatch(input,SEMI,FOLLOW_SEMI_in_importdef733).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI70);



            // AST REWRITE
            // elements: solvname
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 127:22: -> ^( IMPORT solvname )
            {
                // test_grammar/SCHR.g:127:25: ^( IMPORT solvname )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(IMPORT, "IMPORT").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                adaptor.addChild(root_1, stream_solvname.nextTree());

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
    // $ANTLR end "importdef"

      class solvparams_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "solvparams" rule(...)
    // test_grammar/SCHR.g:130:1: solvparams : LPAREN ID ( COMMA ID )* RPAREN -> ^( PARAMS ( ID )* ) ;
    @throws(classOf[RecognitionException])
     final def solvparams():/*SCHRParser.*/solvparams_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/solvparams_return  = new /*SCHRParser.*/solvparams_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LPAREN71: Token =null
        var ID72: Token =null
        var COMMA73: Token =null
        var ID74: Token =null
        var RPAREN75: Token =null

        var LPAREN71_tree:CHRTree=null
        var ID72_tree:CHRTree=null
        var COMMA73_tree:CHRTree=null
        var ID74_tree:CHRTree=null
        var RPAREN75_tree:CHRTree=null
        var stream_RPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RPAREN")
        var stream_ID:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ID")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_LPAREN:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LPAREN")

        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:131:2: ( LPAREN ID ( COMMA ID )* RPAREN -> ^( PARAMS ( ID )* ) )
            // test_grammar/SCHR.g:131:4: LPAREN ID ( COMMA ID )* RPAREN
            {
            LPAREN71=smatch(input,LPAREN,FOLLOW_LPAREN_in_solvparams753).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN71);

            ID72=smatch(input,ID,FOLLOW_ID_in_solvparams755).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID72);

            // test_grammar/SCHR.g:131:14: ( COMMA ID )*
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
            		    // test_grammar/SCHR.g:131:15: COMMA ID
            		    {
            		    COMMA73=smatch(input,COMMA,FOLLOW_COMMA_in_solvparams758).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA73);

            		    ID74=smatch(input,ID,FOLLOW_ID_in_solvparams760).asInstanceOf[Token]; if (state.failed) return retval; 
            		    if ( state.backtracking==0 ) stream_ID.add(ID74);


            		    }
            		case _ =>
            		    guard = false //loop16;
                }
            } //while (true);

            RPAREN75=smatch(input,RPAREN,FOLLOW_RPAREN_in_solvparams765).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN75);



            // AST REWRITE
            // elements: ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 132:3: -> ^( PARAMS ( ID )* )
            {
                // test_grammar/SCHR.g:132:6: ^( PARAMS ( ID )* )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(PARAMS, "PARAMS").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                // test_grammar/SCHR.g:132:15: ( ID )*
                while ( stream_ID.hasNext() ) {
                    adaptor.addChild(root_1, stream_ID.nextNode());

                }
                stream_ID.reset();

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
    // $ANTLR end "solvparams"

      class solvname_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "solvname" rule(...)
    // test_grammar/SCHR.g:135:1: solvname : STRING ;
    @throws(classOf[RecognitionException])
     final def solvname():/*SCHRParser.*/solvname_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/solvname_return  = new /*SCHRParser.*/solvname_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var STRING76: Token =null

        var STRING76_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:136:2: ( STRING )
            // test_grammar/SCHR.g:137:3: STRING
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            STRING76=smatch(input,STRING,FOLLOW_STRING_in_solvname790).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRING76_tree = adaptor.create(STRING76).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, STRING76_tree);
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
    // $ANTLR end "solvname"

      class solvinstance_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "solvinstance" rule(...)
    // test_grammar/SCHR.g:140:1: solvinstance : ID ( solvparams )? -> ^( SOLVER ID ( solvparams )? ) ;
    @throws(classOf[RecognitionException])
     final def solvinstance():/*SCHRParser.*/solvinstance_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/solvinstance_return  = new /*SCHRParser.*/solvinstance_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var ID77: Token =null
        var solvparams78:/*SCHRParser.*/solvparams_return = null


        var ID77_tree:CHRTree=null
        var stream_ID:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token ID")
        var stream_solvparams:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule solvparams");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:141:2: ( ID ( solvparams )? -> ^( SOLVER ID ( solvparams )? ) )
            // test_grammar/SCHR.g:141:4: ID ( solvparams )?
            {
            ID77=smatch(input,ID,FOLLOW_ID_in_solvinstance802).asInstanceOf[Token]; if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID77);

            // test_grammar/SCHR.g:141:7: ( solvparams )?
            var alt17=2;
            var LA17_0 = input.LA(1);

            if ( (LA17_0==LPAREN) ) {
                alt17=1;
            }
            alt17 match{
                case 1 =>
                    // test_grammar/SCHR.g:0:0: solvparams
                    {
                    pushFollow(FOLLOW_solvparams_in_solvinstance804);
                    solvparams78=solvparams();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_solvparams.add(solvparams78.getTree());

                    }
                case _ => //Do nothing
            }



            // AST REWRITE
            // elements: ID, solvparams
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

            root_0 = adaptor.nil().asInstanceOf[CHRTree];
            // 141:19: -> ^( SOLVER ID ( solvparams )? )
            {
                // test_grammar/SCHR.g:141:22: ^( SOLVER ID ( solvparams )? )
                {
                var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                root_1 = adaptor.becomeRoot(adaptor.create(SOLVER, "SOLVER").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                adaptor.addChild(root_1, stream_ID.nextNode());
                // test_grammar/SCHR.g:141:34: ( solvparams )?
                if ( stream_solvparams.hasNext() ) {
                    adaptor.addChild(root_1, stream_solvparams.nextTree());

                }
                stream_solvparams.reset();

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
    // $ANTLR end "solvinstance"

      class term_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "term" rule(...)
    // test_grammar/SCHR.g:144:1: term : ( simpleterm | termlist );
    @throws(classOf[RecognitionException])
     final def term():/*SCHRParser.*/term_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/term_return  = new /*SCHRParser.*/term_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var simpleterm79:/*SCHRParser.*/simpleterm_return = null

        var termlist80:/*SCHRParser.*/termlist_return = null



        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:145:2: ( simpleterm | termlist )
            var alt18=2;
            var LA18_0 = input.LA(1);

            if ( (LA18_0==STRING||LA18_0==ID||LA18_0==INT) ) {
                alt18=1;
            }
            else if ( (LA18_0==LBRACK) ) {
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
                    // test_grammar/SCHR.g:145:4: simpleterm
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_simpleterm_in_term827);
                    simpleterm79=simpleterm();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simpleterm79.getTree());

                    }case 2 =>
                    // test_grammar/SCHR.g:146:4: termlist
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_termlist_in_term832);
                    termlist80=termlist();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, termlist80.getTree());

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

      class termlist_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "termlist" rule(...)
    // test_grammar/SCHR.g:149:1: termlist : ( LBRACK RBRACK -> EMPTYLIST | LBRACK simpleterm BAR variable RBRACK -> ^( LIST ^( HEAD simpleterm ) ^( TAIL variable ) ) | LBRACK term ( COMMA term )* RBRACK -> ^( LIST ( term )* ) );
    @throws(classOf[RecognitionException])
     final def termlist():/*SCHRParser.*/termlist_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/termlist_return  = new /*SCHRParser.*/termlist_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var LBRACK81: Token =null
        var RBRACK82: Token =null
        var LBRACK83: Token =null
        var BAR85: Token =null
        var RBRACK87: Token =null
        var LBRACK88: Token =null
        var COMMA90: Token =null
        var RBRACK92: Token =null
        var simpleterm84:/*SCHRParser.*/simpleterm_return = null

        var variable86:/*SCHRParser.*/variable_return = null

        var term89:/*SCHRParser.*/term_return = null

        var term91:/*SCHRParser.*/term_return = null


        var LBRACK81_tree:CHRTree=null
        var RBRACK82_tree:CHRTree=null
        var LBRACK83_tree:CHRTree=null
        var BAR85_tree:CHRTree=null
        var RBRACK87_tree:CHRTree=null
        var LBRACK88_tree:CHRTree=null
        var COMMA90_tree:CHRTree=null
        var RBRACK92_tree:CHRTree=null
        var stream_RBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token RBRACK")
        var stream_LBRACK:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token LBRACK")
        var stream_COMMA:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token COMMA")
        var stream_BAR:RewriteRuleTokenStream =new RewriteRuleTokenStream(adaptor,"token BAR")
        var stream_term:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule term");
        var stream_simpleterm:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule simpleterm");
        var stream_variable:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule variable");
        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:150:2: ( LBRACK RBRACK -> EMPTYLIST | LBRACK simpleterm BAR variable RBRACK -> ^( LIST ^( HEAD simpleterm ) ^( TAIL variable ) ) | LBRACK term ( COMMA term )* RBRACK -> ^( LIST ( term )* ) )
            var alt20=3;
            var LA20_0 = input.LA(1);

            if ( (LA20_0==LBRACK) ) {
                input.LA(2) match{
                	case RBRACK => alt20=1;    
                	case STRING => var LA20_3 = input.LA(3);

                	if ( (LA20_3==COMMA||LA20_3==RBRACK) ) {
                	    alt20=3;
                	}
                	else if ( (LA20_3==BAR) ) {
                	    alt20=2;
                	}
                	else {
                	    if (state.backtracking>0) {state.failed=true; return retval;}
                	    val nvae =
                	        new NoViableAltException("", 20, 3, input);

                	    throw nvae;
                	}
                	case INT => var LA20_3 = input.LA(3);

                	if ( (LA20_3==COMMA||LA20_3==RBRACK) ) {
                	    alt20=3;
                	}
                	else if ( (LA20_3==BAR) ) {
                	    alt20=2;
                	}
                	else {
                	    if (state.backtracking>0) {state.failed=true; return retval;}
                	    val nvae =
                	        new NoViableAltException("", 20, 3, input);

                	    throw nvae;
                	}    
                	case ID => var LA20_4 = input.LA(3);

                	if ( (LA20_4==BAR) ) {
                	    alt20=2;
                	}
                	else if ( (LA20_4==COMMA||LA20_4==RBRACK) ) {
                	    alt20=3;
                	}
                	else {
                	    if (state.backtracking>0) {state.failed=true; return retval;}
                	    val nvae =
                	        new NoViableAltException("", 20, 4, input);

                	    throw nvae;
                	}    
                	case LBRACK => alt20=3;    
                	case _ =>
                		    if (state.backtracking>0) {state.failed=true; return retval;}
                		    val nvae =
                		        new NoViableAltException("", 20, 1, input);

                		    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                val nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            alt20 match{
                case 1 =>
                    // test_grammar/SCHR.g:150:4: LBRACK RBRACK
                    {
                    LBRACK81=smatch(input,LBRACK,FOLLOW_LBRACK_in_termlist844).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK81);

                    RBRACK82=smatch(input,RBRACK,FOLLOW_RBRACK_in_termlist846).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK82);



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
                    // 150:18: -> EMPTYLIST
                    {
                        adaptor.addChild(root_0, adaptor.create(EMPTYLIST, "EMPTYLIST").asInstanceOf[CHRTree]);

                    }

                    retval.tree = root_0;}
                    }case 2 =>
                    // test_grammar/SCHR.g:151:4: LBRACK simpleterm BAR variable RBRACK
                    {
                    LBRACK83=smatch(input,LBRACK,FOLLOW_LBRACK_in_termlist855).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK83);

                    pushFollow(FOLLOW_simpleterm_in_termlist857);
                    simpleterm84=simpleterm();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_simpleterm.add(simpleterm84.getTree());
                    BAR85=smatch(input,BAR,FOLLOW_BAR_in_termlist859).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BAR.add(BAR85);

                    pushFollow(FOLLOW_variable_in_termlist861);
                    variable86=variable();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variable.add(variable86.getTree());
                    RBRACK87=smatch(input,RBRACK,FOLLOW_RBRACK_in_termlist863).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK87);



                    // AST REWRITE
                    // elements: simpleterm, variable
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    var stream_retval:RewriteRuleSubtreeStream =new RewriteRuleSubtreeStream(adaptor,"rule retval",if(retval!=null){retval.tree}else{null})

                    root_0 = adaptor.nil().asInstanceOf[CHRTree];
                    // 151:42: -> ^( LIST ^( HEAD simpleterm ) ^( TAIL variable ) )
                    {
                        // test_grammar/SCHR.g:151:45: ^( LIST ^( HEAD simpleterm ) ^( TAIL variable ) )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(LIST, "LIST").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // test_grammar/SCHR.g:151:52: ^( HEAD simpleterm )
                        {
                        var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_2 = adaptor.becomeRoot(adaptor.create(HEAD, "HEAD").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                        adaptor.addChild(root_2, stream_simpleterm.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }
                        // test_grammar/SCHR.g:151:71: ^( TAIL variable )
                        {
                        var root_2:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_2 = adaptor.becomeRoot(adaptor.create(TAIL, "TAIL").asInstanceOf[CHRTree], root_2).asInstanceOf[CHRTree]

                        adaptor.addChild(root_2, stream_variable.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }case 3 =>
                    // test_grammar/SCHR.g:152:4: LBRACK term ( COMMA term )* RBRACK
                    {
                    LBRACK88=smatch(input,LBRACK,FOLLOW_LBRACK_in_termlist886).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK88);

                    pushFollow(FOLLOW_term_in_termlist888);
                    term89=term();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_term.add(term89.getTree());
                    // test_grammar/SCHR.g:152:16: ( COMMA term )*
                    //loop19:
                    var guard = true
                    while(guard) {
                        var alt19=2;
                        var LA19_0 = input.LA(1);

                        if ( (LA19_0==COMMA) ) {
                            alt19=1;
                        }


                        alt19 match{
                    		case 1 =>
                    		    // test_grammar/SCHR.g:152:17: COMMA term
                    		    {
                    		    COMMA90=smatch(input,COMMA,FOLLOW_COMMA_in_termlist891).asInstanceOf[Token]; if (state.failed) return retval; 
                    		    if ( state.backtracking==0 ) stream_COMMA.add(COMMA90);

                    		    pushFollow(FOLLOW_term_in_termlist893);
                    		    term91=term();

                    		    state._fsp -= 1 
                    		    if (state.failed) return retval;
                    		    if ( state.backtracking==0 ) stream_term.add(term91.getTree());

                    		    }
                    		case _ =>
                    		    guard = false //loop19;
                        }
                    } //while (true);

                    RBRACK92=smatch(input,RBRACK,FOLLOW_RBRACK_in_termlist897).asInstanceOf[Token]; if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK92);



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
                    // 152:37: -> ^( LIST ( term )* )
                    {
                        // test_grammar/SCHR.g:152:40: ^( LIST ( term )* )
                        {
                        var root_1:CHRTree = adaptor.nil().asInstanceOf[CHRTree]
                        root_1 = adaptor.becomeRoot(adaptor.create(LIST, "LIST").asInstanceOf[CHRTree], root_1).asInstanceOf[CHRTree]

                        // test_grammar/SCHR.g:152:47: ( term )*
                        while ( stream_term.hasNext() ) {
                            adaptor.addChild(root_1, stream_term.nextTree());

                        }
                        stream_term.reset();

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
    // $ANTLR end "termlist"

      class simpleterm_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "simpleterm" rule(...)
    // test_grammar/SCHR.g:155:1: simpleterm : ( constant | variable );
    @throws(classOf[RecognitionException])
     final def simpleterm():/*SCHRParser.*/simpleterm_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/simpleterm_return  = new /*SCHRParser.*/simpleterm_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var constant93:/*SCHRParser.*/constant_return = null

        var variable94:/*SCHRParser.*/variable_return = null



        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:156:2: ( constant | variable )
            var alt21=2;
            var LA21_0 = input.LA(1);

            if ( (LA21_0==STRING||LA21_0==INT) ) {
                alt21=1;
            }
            else if ( (LA21_0==ID) ) {
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
                    // test_grammar/SCHR.g:156:4: constant
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_constant_in_simpleterm920);
                    constant93=constant();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constant93.getTree());

                    }case 2 =>
                    // test_grammar/SCHR.g:157:4: variable
                    {
                    root_0 = adaptor.nil().asInstanceOf[CHRTree]

                    pushFollow(FOLLOW_variable_in_simpleterm925);
                    variable94=variable();

                    state._fsp -= 1 
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variable94.getTree());

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
    // $ANTLR end "simpleterm"

      class constant_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "constant" rule(...)
    // test_grammar/SCHR.g:160:1: constant : ( INT | STRING );
    @throws(classOf[RecognitionException])
     final def constant():/*SCHRParser.*/constant_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/constant_return  = new /*SCHRParser.*/constant_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var set95: Token =null

        var set95_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:161:2: ( INT | STRING )
            // test_grammar/SCHR.g:
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            set95=input.LT(1).asInstanceOf[Token]
            if ( input.LA(1)==STRING||input.LA(1)==INT ) {
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
    // $ANTLR end "constant"

      class consId_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "consId" rule(...)
    // test_grammar/SCHR.g:165:1: consId : ID ;
    @throws(classOf[RecognitionException])
     final def consId():/*SCHRParser.*/consId_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/consId_return  = new /*SCHRParser.*/consId_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var ID96: Token =null

        var ID96_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:165:8: ( ID )
            // test_grammar/SCHR.g:165:10: ID
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            ID96=smatch(input,ID,FOLLOW_ID_in_consId951).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID96_tree = adaptor.create(ID96).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, ID96_tree);
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
    // $ANTLR end "consId"

      class ruleId_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "ruleId" rule(...)
    // test_grammar/SCHR.g:168:1: ruleId : ID ;
    @throws(classOf[RecognitionException])
     final def ruleId():/*SCHRParser.*/ruleId_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/ruleId_return  = new /*SCHRParser.*/ruleId_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var ID97: Token =null

        var ID97_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:168:8: ( ID )
            // test_grammar/SCHR.g:168:10: ID
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            ID97=smatch(input,ID,FOLLOW_ID_in_ruleId961).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID97_tree = adaptor.create(ID97).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, ID97_tree);
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
    // $ANTLR end "ruleId"

      class variable_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "variable" rule(...)
    // test_grammar/SCHR.g:171:1: variable : ID ;
    @throws(classOf[RecognitionException])
     final def variable():/*SCHRParser.*/variable_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/variable_return  = new /*SCHRParser.*/variable_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var ID98: Token =null

        var ID98_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:171:10: ( ID )
            // test_grammar/SCHR.g:171:12: ID
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            ID98=smatch(input,ID,FOLLOW_ID_in_variable974).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID98_tree = adaptor.create(ID98).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, ID98_tree);
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

      class solvId_return extends ParserRuleReturnScope {
        var tree: CHRTree = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "solvId" rule(...)
    // test_grammar/SCHR.g:174:1: solvId : ID ;
    @throws(classOf[RecognitionException])
     final def solvId():/*SCHRParser.*/solvId_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*SCHRParser.*/solvId_return  = new /*SCHRParser.*/solvId_return();
        retval.start = input.LT(1);

        var root_0:CHRTree = null

        //ruleLabelDefs
        var ID99: Token =null

        var ID99_tree:CHRTree=null

        //ruleDescriptor.actions.init
        try {
            // test_grammar/SCHR.g:174:8: ( ID )
            // test_grammar/SCHR.g:174:10: ID
            {
            root_0 = adaptor.nil().asInstanceOf[CHRTree]

            ID99=smatch(input,ID,FOLLOW_ID_in_solvId984).asInstanceOf[Token]; if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID99_tree = adaptor.create(ID99).asInstanceOf[CHRTree];
            adaptor.addChild(root_0, ID99_tree);
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
    // $ANTLR end "solvId"

    // $ANTLR start synpred12_SCHR
    @throws(classOf[RecognitionException])
     final def synpred12_SCHR_fragment() /*throws RecognitionException*/ {   
        // test_grammar/SCHR.g:87:4: ( term op term )
        // test_grammar/SCHR.g:87:4: term op term
        {
        pushFollow(FOLLOW_term_in_synpred12_SCHR430);
        term();

        state._fsp -= 1 
        if (state.failed) return ;
        pushFollow(FOLLOW_op_in_synpred12_SCHR432);
        op();

        state._fsp -= 1 
        if (state.failed) return ;
        pushFollow(FOLLOW_term_in_synpred12_SCHR434);
        term();

        state._fsp -= 1 
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred12_SCHR

    // $ANTLR start synpred19_SCHR
    @throws(classOf[RecognitionException])
     final def synpred19_SCHR_fragment() /*throws RecognitionException*/ {   
        // test_grammar/SCHR.g:109:4: ( ruledef )
        // test_grammar/SCHR.g:109:4: ruledef
        {
        pushFollow(FOLLOW_ruledef_in_synpred19_SCHR627);
        ruledef();

        state._fsp -= 1 
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred19_SCHR

    // $ANTLR start synpred20_SCHR
    @throws(classOf[RecognitionException])
     final def synpred20_SCHR_fragment() /*throws RecognitionException*/ {   
        // test_grammar/SCHR.g:110:4: ( axiom )
        // test_grammar/SCHR.g:110:4: axiom
        {
        pushFollow(FOLLOW_axiom_in_synpred20_SCHR632);
        axiom();

        state._fsp -= 1 
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred20_SCHR

    // Delegated rules

     final def synpred12_SCHR():Boolean = {
        state.backtracking = state.backtracking + 1;
        val start = input.mark();
        try {
            synpred12_SCHR_fragment() // can never throw exception
        } catch /*(RecognitionException re)*/ {
            case re:RecognitionException => System.err.println("impossible: "+re)
        }
        val success = !state.failed;
        input.rewind(start);
        state.backtracking = state.backtracking -1 ;
        state.failed=false;
        success;
    }
     final def synpred19_SCHR():Boolean = {
        state.backtracking = state.backtracking + 1;
        val start = input.mark();
        try {
            synpred19_SCHR_fragment() // can never throw exception
        } catch /*(RecognitionException re)*/ {
            case re:RecognitionException => System.err.println("impossible: "+re)
        }
        val success = !state.failed;
        input.rewind(start);
        state.backtracking = state.backtracking -1 ;
        state.failed=false;
        success;
    }
     final def synpred20_SCHR():Boolean = {
        state.backtracking = state.backtracking + 1;
        val start = input.mark();
        try {
            synpred20_SCHR_fragment() // can never throw exception
        } catch /*(RecognitionException re)*/ {
            case re:RecognitionException => System.err.println("impossible: "+re)
        }
        val success = !state.failed;
        input.rewind(start);
        state.backtracking = state.backtracking -1 ;
        state.failed=false;
        success;
    }


    protected val dfa15:DFA15 = new DFA15(this);
    /*
    final val DFA15_eotS =
        "\11\uffff";
    final val DFA15_eofS =
        "\11\uffff";
    final val DFA15_minS =
        "\1\7\1\30\1\uffff\1\27\1\uffff\1\36\1\27\1\30\1\36";
    final val DFA15_maxS =
        "\1\55\1\60\1\uffff\1\55\1\uffff\1\41\1\55\1\60\1\41";
    final val DFA15_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\4\uffff";
    val DFA15_specialS =
        "\11\uffff}>";
    final val DFA15_transitionS = Array[String](
        "\1\2\17\uffff\1\2\2\uffff\1\1\16\uffff\2\2\2\uffff\1\2",
        "\1\2\4\uffff\3\2\1\3\1\uffff\6\2\3\uffff\1\4\4\uffff\1\2",
        "",
        "\1\2\2\uffff\1\5\16\uffff\1\2\3\uffff\1\2",
        "",
        "\1\6\2\uffff\1\7",
        "\1\2\2\uffff\1\10\16\uffff\1\2\3\uffff\1\2",
        "\1\2\5\uffff\1\2\14\uffff\1\4\4\uffff\1\2",
        "\1\6\2\uffff\1\7"
    )

    final val DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    final val DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    final val DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    final val DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    final val DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    final val DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    final val DFA15_transition = new Array[Array[Short]](DFA15_transitionS.length)

    {
        for (i <- 0 to DFA15_transition.length - 1) {
            DFA15_transition(i) = DFA.unpackEncodedString(DFA15_transitionS(i))
        }
    }
    */
    class DFA15(recognizer:BaseRecognizer ) extends DFA {
    		final val DFA15_eotS =
    		    "\11\uffff";
    		final val DFA15_eofS =
    		    "\11\uffff";
    		final val DFA15_minS =
    		    "\1\7\1\30\1\uffff\1\27\1\uffff\1\36\1\27\1\30\1\36";
    		final val DFA15_maxS =
    		    "\1\55\1\60\1\uffff\1\55\1\uffff\1\41\1\55\1\60\1\41";
    		final val DFA15_acceptS =
    		    "\2\uffff\1\2\1\uffff\1\1\4\uffff";
    		val DFA15_specialS =
    		    "\11\uffff}>";
    		final val DFA15_transitionS = Array[String](
    		    "\1\2\17\uffff\1\2\2\uffff\1\1\16\uffff\2\2\2\uffff\1\2",
    		    "\1\2\4\uffff\3\2\1\3\1\uffff\6\2\3\uffff\1\4\4\uffff\1\2",
    		    "",
    		    "\1\2\2\uffff\1\5\16\uffff\1\2\3\uffff\1\2",
    		    "",
    		    "\1\6\2\uffff\1\7",
    		    "\1\2\2\uffff\1\10\16\uffff\1\2\3\uffff\1\2",
    		    "\1\2\5\uffff\1\2\14\uffff\1\4\4\uffff\1\2",
    		    "\1\6\2\uffff\1\7"
    		)

            this.decisionNumber = 15;
            this.eot = DFA.unpackEncodedString(DFA15_eotS) //DFA15_eot;
            this.eof = DFA.unpackEncodedString(DFA15_eofS) //DFA15_eof;
            this.min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS) //DFA15_min;
            this.max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS) //DFA15_max;
            this.accept = DFA.unpackEncodedString(DFA15_acceptS) //DFA15_accept;
            this.special = DFA.unpackEncodedString(DFA15_specialS); //DFA15_special;
            this.transition = new Array[Array[Short]](DFA15_transitionS.length) //DFA15_transition;

    		{
    		    for (i <- 0 to DFA15_transitionS.length - 1) {
    		        this.transition(i) = DFA.unpackEncodedString(DFA15_transitionS(i))
    		    }
    		}

        override def getDescription(): String = "121:1: solvdef : ( solvinstance | program );"
        
    }
 

      final val FOLLOW_packagedef_in_start138:BitSet = new BitSet( Array[Long](0x0000360006800080L))
      //new long[]{0x0000360006800080L});
      final val FOLLOW_importdef_in_start141:BitSet = new BitSet( Array[Long](0x0000360006800080L))
      //new long[]{0x0000360006800080L});
      final val FOLLOW_body_in_start144:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_PACKAGE_in_packagedef172:BitSet = new BitSet( Array[Long](0x0000000000800000L))
      //new long[]{0x0000000000800000L});
      final val FOLLOW_STRING_in_packagedef174:BitSet = new BitSet( Array[Long](0x0000000001000000L))
      //new long[]{0x0000000001000000L});
      final val FOLLOW_SEMI_in_packagedef176:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_gensolver_in_body196:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_program_in_body203:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_SOLVER_in_gensolver219:BitSet = new BitSet( Array[Long](0x0000000004000000L))
      //new long[]{0x0000000004000000L});
      final val FOLLOW_ID_in_gensolver221:BitSet = new BitSet( Array[Long](0x0000000108000000L))
      //new long[]{0x0000000108000000L});
      final val FOLLOW_solvparams_in_gensolver224:BitSet = new BitSet( Array[Long](0x0000000008000000L))
      //new long[]{0x0000000008000000L});
      final val FOLLOW_LCURL_in_gensolver227:BitSet = new BitSet( Array[Long](0x0000360006800080L))
      //new long[]{0x0000360006800080L});
      final val FOLLOW_program_in_gensolver229:BitSet = new BitSet( Array[Long](0x0000000010000000L))
      //new long[]{0x0000000010000000L});
      final val FOLLOW_RCURL_in_gensolver231:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_expression_in_program257:BitSet = new BitSet( Array[Long](0x0000360006800082L))
      //new long[]{0x0000360006800082L});
      final val FOLLOW_expression_in_program259:BitSet = new BitSet( Array[Long](0x0000360006800082L))
      //new long[]{0x0000360006800082L});
      final val FOLLOW_ruleId_in_ruledef282:BitSet = new BitSet( Array[Long](0x0000000020000000L))
      //new long[]{0x0000000020000000L});
      final val FOLLOW_ARROBAS_in_ruledef284:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_constraintList_in_ruledef288:BitSet = new BitSet( Array[Long](0x0001000000000000L))
      //new long[]{0x0001000000000000L});
      final val FOLLOW_CHR_SIMP_in_ruledef290:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_constraintList_in_ruledef292:BitSet = new BitSet( Array[Long](0x0000000001000000L))
      //new long[]{0x0000000001000000L});
      final val FOLLOW_SEMI_in_ruledef294:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_constraint_in_constraintList331:BitSet = new BitSet( Array[Long](0x0000000040000002L))
      //new long[]{0x0000000040000002L});
      final val FOLLOW_COMMA_in_constraintList334:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_constraint_in_constraintList336:BitSet = new BitSet( Array[Long](0x0000000040000002L))
      //new long[]{0x0000000040000002L});
      final val FOLLOW_solvId_in_constraint357:BitSet = new BitSet( Array[Long](0x0000000080000000L))
      //new long[]{0x0000000080000000L});
      final val FOLLOW_POINT_in_constraint359:BitSet = new BitSet( Array[Long](0x0000000004000000L))
      //new long[]{0x0000000004000000L});
      final val FOLLOW_consId_in_constraint364:BitSet = new BitSet( Array[Long](0x0000000100000002L))
      //new long[]{0x0000000100000002L});
      final val FOLLOW_LPAREN_in_constraint368:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_term_in_constraint370:BitSet = new BitSet( Array[Long](0x0000000240000000L))
      //new long[]{0x0000000240000000L});
      final val FOLLOW_COMMA_in_constraint373:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_term_in_constraint375:BitSet = new BitSet( Array[Long](0x0000000240000000L))
      //new long[]{0x0000000240000000L});
      final val FOLLOW_RPAREN_in_constraint379:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_builtinconstraint_in_constraint416:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_term_in_builtinconstraint430:BitSet = new BitSet( Array[Long](0x000000F800000000L))
      //new long[]{0x000000F800000000L});
      final val FOLLOW_op_in_builtinconstraint432:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_term_in_builtinconstraint434:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_term_in_builtinconstraint461:BitSet = new BitSet( Array[Long](0x0000000400000000L))
      //new long[]{0x0000000400000000L});
      final val FOLLOW_IS_in_builtinconstraint463:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_term_in_builtinconstraint465:BitSet = new BitSet( Array[Long](0x000000F800000000L))
      //new long[]{0x000000F800000000L});
      final val FOLLOW_op_in_builtinconstraint467:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_term_in_builtinconstraint469:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_set_in_op0:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_CONSTRAINT_in_constraintsdef532:BitSet = new BitSet( Array[Long](0x0000000004000000L))
      //new long[]{0x0000000004000000L});
      final val FOLLOW_constraintdef_in_constraintsdef534:BitSet = new BitSet( Array[Long](0x0000000041000000L))
      //new long[]{0x0000000041000000L});
      final val FOLLOW_COMMA_in_constraintsdef537:BitSet = new BitSet( Array[Long](0x0000000004000000L))
      //new long[]{0x0000000004000000L});
      final val FOLLOW_constraintdef_in_constraintsdef539:BitSet = new BitSet( Array[Long](0x0000000041000000L))
      //new long[]{0x0000000041000000L});
      final val FOLLOW_SEMI_in_constraintsdef543:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ID_in_constraintdef568:BitSet = new BitSet( Array[Long](0x0000010000000000L))
      //new long[]{0x0000010000000000L});
      final val FOLLOW_SLASH_in_constraintdef570:BitSet = new BitSet( Array[Long](0x0000020000000000L))
      //new long[]{0x0000020000000000L});
      final val FOLLOW_INT_in_constraintdef572:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_constraint_in_axiom602:BitSet = new BitSet( Array[Long](0x0000000001000000L))
      //new long[]{0x0000000001000000L});
      final val FOLLOW_SEMI_in_axiom604:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_constraintsdef_in_expression622:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ruledef_in_expression627:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_axiom_in_expression632:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_letexpr_in_expression637:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LET_in_letexpr652:BitSet = new BitSet( Array[Long](0x0000000004000000L))
      //new long[]{0x0000000004000000L});
      final val FOLLOW_solvId_in_letexpr654:BitSet = new BitSet( Array[Long](0x0000000800000000L))
      //new long[]{0x0000000800000000L});
      final val FOLLOW_EQ_in_letexpr656:BitSet = new BitSet( Array[Long](0x0000360006800080L))
      //new long[]{0x0000360006800080L});
      final val FOLLOW_solvdef_in_letexpr658:BitSet = new BitSet( Array[Long](0x0000080000000000L))
      //new long[]{0x0000080000000000L});
      final val FOLLOW_IN_in_letexpr660:BitSet = new BitSet( Array[Long](0x0000000008000000L))
      //new long[]{0x0000000008000000L});
      final val FOLLOW_LCURL_in_letexpr662:BitSet = new BitSet( Array[Long](0x0000360006800080L))
      //new long[]{0x0000360006800080L});
      final val FOLLOW_program_in_letexpr664:BitSet = new BitSet( Array[Long](0x0000000010000000L))
      //new long[]{0x0000000010000000L});
      final val FOLLOW_RCURL_in_letexpr666:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_solvinstance_in_solvdef710:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_program_in_solvdef714:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_USE_in_importdef729:BitSet = new BitSet( Array[Long](0x0000000000800000L))
      //new long[]{0x0000000000800000L});
      final val FOLLOW_solvname_in_importdef731:BitSet = new BitSet( Array[Long](0x0000000001000000L))
      //new long[]{0x0000000001000000L});
      final val FOLLOW_SEMI_in_importdef733:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LPAREN_in_solvparams753:BitSet = new BitSet( Array[Long](0x0000000004000000L))
      //new long[]{0x0000000004000000L});
      final val FOLLOW_ID_in_solvparams755:BitSet = new BitSet( Array[Long](0x0000000240000000L))
      //new long[]{0x0000000240000000L});
      final val FOLLOW_COMMA_in_solvparams758:BitSet = new BitSet( Array[Long](0x0000000004000000L))
      //new long[]{0x0000000004000000L});
      final val FOLLOW_ID_in_solvparams760:BitSet = new BitSet( Array[Long](0x0000000240000000L))
      //new long[]{0x0000000240000000L});
      final val FOLLOW_RPAREN_in_solvparams765:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_STRING_in_solvname790:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ID_in_solvinstance802:BitSet = new BitSet( Array[Long](0x0000000100000002L))
      //new long[]{0x0000000100000002L});
      final val FOLLOW_solvparams_in_solvinstance804:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_simpleterm_in_term827:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_termlist_in_term832:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LBRACK_in_termlist844:BitSet = new BitSet( Array[Long](0x0000400000000000L))
      //new long[]{0x0000400000000000L});
      final val FOLLOW_RBRACK_in_termlist846:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LBRACK_in_termlist855:BitSet = new BitSet( Array[Long](0x0000020004800000L))
      //new long[]{0x0000020004800000L});
      final val FOLLOW_simpleterm_in_termlist857:BitSet = new BitSet( Array[Long](0x0000800000000000L))
      //new long[]{0x0000800000000000L});
      final val FOLLOW_BAR_in_termlist859:BitSet = new BitSet( Array[Long](0x0000020004800000L))
      //new long[]{0x0000020004800000L});
      final val FOLLOW_variable_in_termlist861:BitSet = new BitSet( Array[Long](0x0000400000000000L))
      //new long[]{0x0000400000000000L});
      final val FOLLOW_RBRACK_in_termlist863:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_LBRACK_in_termlist886:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_term_in_termlist888:BitSet = new BitSet( Array[Long](0x0000400040000000L))
      //new long[]{0x0000400040000000L});
      final val FOLLOW_COMMA_in_termlist891:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_term_in_termlist893:BitSet = new BitSet( Array[Long](0x0000400040000000L))
      //new long[]{0x0000400040000000L});
      final val FOLLOW_RBRACK_in_termlist897:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_constant_in_simpleterm920:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_variable_in_simpleterm925:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_set_in_constant0:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ID_in_consId951:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ID_in_ruleId961:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ID_in_variable974:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ID_in_solvId984:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_term_in_synpred12_SCHR430:BitSet = new BitSet( Array[Long](0x000000F800000000L))
      //new long[]{0x000000F800000000L});
      final val FOLLOW_op_in_synpred12_SCHR432:BitSet = new BitSet( Array[Long](0x0000220004800000L))
      //new long[]{0x0000220004800000L});
      final val FOLLOW_term_in_synpred12_SCHR434:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_ruledef_in_synpred19_SCHR627:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});
      final val FOLLOW_axiom_in_synpred20_SCHR632:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});

}