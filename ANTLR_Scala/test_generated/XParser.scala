// $ANTLR 3.2 Sep 23, 2009 12:02:23 test_grammar/X.g 2010-06-08 16:55:58

package grammar_test;


import org.antlr.runtime._;
import BaseRecognizer.HIDDEN
import java.util.Stack;
//import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree._

import org.antlr.runtime.Token.{DOWN, UP}
 class XParser(input: TokenStream, state:SRecognizerSharedState) 
 extends SParser(input,state) {
 
 	def this(input: TokenStream){
 		this(input, new SRecognizerSharedState())
 	}
 
    val tokenNames:Array[String] = Array[String] (
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ZERO"
    )
      val EOF:Int = -1;
      val ZERO:Int = 4;

    // delegates
    // delegators


        
    protected var adaptor:TreeAdaptor = new CommonTreeAdaptor()

    def setTreeAdaptor(adaptor:TreeAdaptor) {
        this.adaptor = adaptor
    }
    def  getTreeAdaptor():TreeAdaptor = adaptor

    override def getTokenNames: Array[String] = /*XParser.*/tokenNames 
    override def getGrammarFileName(): String = "test_grammar/X.g"


      class start_return extends ParserRuleReturnScope {
        var tree: Object = _;
        override def  getTree():Object = tree
    };

    // $ANTLR start "start" rule(...)
    // test_grammar/X.g:13:1: start : ZERO ;
    @throws(classOf[RecognitionException])
     final def start():/*XParser.*/start_return = /*throws RecognitionException*/ {
        //ruleScopeSetUp
        //ruleDeclarations
        var retval:/*XParser.*/start_return  = new /*XParser.*/start_return();
        retval.start = input.LT(1);

        var root_0:Object = null

        //ruleLabelDefs
        var ZERO1: Token =null

        var ZERO1_tree:Object=null

        //ruleDescriptor.actions.init
        try {
            // test_grammar/X.g:13:7: ( ZERO )
            // test_grammar/X.g:13:9: ZERO
            {
            root_0 = adaptor.nil().asInstanceOf[Object]

            ZERO1=smatch(input,ZERO,FOLLOW_ZERO_in_start40).asInstanceOf[Token]; 
            ZERO1_tree = adaptor.create(ZERO1).asInstanceOf[Object];
            adaptor.addChild(root_0, ZERO1_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = adaptor.rulePostProcessing(root_0).asInstanceOf[Object]
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch /*(RecognitionException re)*/ {
        	case re:RecognitionException =>
            	reportError(re);
            	recover(input,re);
    	retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re).asInstanceOf[Object]

        }
        finally {
        }
        retval
    }
    // $ANTLR end "start"

    // Delegated rules


 

      final val FOLLOW_ZERO_in_start40:BitSet = new BitSet( Array[Long](0x0000000000000002L))
      //new long[]{0x0000000000000002L});

}