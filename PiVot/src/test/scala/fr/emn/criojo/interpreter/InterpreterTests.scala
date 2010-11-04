package fr.emn.criojo.interpreter

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 4:30:42 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.parser._
import fr.emn.criojo.parser.tree._

import java.io._
import org.antlr.runtime._
import org.antlr.runtime.tree.DOTTreeGenerator

import org.junit._
import Assert._

class  InterpreterTests{

  val args:Array[String] = Array("fr/emn/criojo/interpreter/naive_execution_test.crj")

  @Test(timeout=1000)
  def testParser{
    //My own TreeAdaptor
    val adaptor = new CHRTreeAdaptor

    val url = this.getClass.getClassLoader.getResource("fr/emn/criojo/interpreter/grammar_test.crj")
    println("url: " + url)

    val lex = new CREOLELexer(new ANTLRFileStream(url.getFile))
    val tokens = new CommonTokenStream(lex)
      println("tokens: " + tokens.getTokens)

    val g = new CREOLEParser(tokens);
    g.setTreeAdaptor(adaptor)
    val program = g.start()
    val tree = program.getTree().asInstanceOf[CHRTree];

    println("tree: " + tree.toStringTree)

    // GENERATE DOT AST
//    val gen = new DOTTreeGenerator()
//    val st = gen.toDOT(tree);
//    val bout = new BufferedWriter(new FileWriter("QueryTest.dot"));
//    bout.write(st.toString());
//    bout.close();
//
//    val rt = Runtime.getRuntime();
//    val pr = rt
//        .exec("/Applications/Graphviz.app/Contents/MacOS/Graphviz QueryTest.dot");

    assertTrue(true)
  }

  @Test (timeout=1000)
  def testInterpreter{
    println(args(0))
    //My own TreeAdaptor
    val adaptor = new CHRTreeAdaptor

    val url = this.getClass.getClassLoader.getResource(args(0))
    println("url: " + url)

    val lex = new CREOLELexer(new ANTLRFileStream(url.getFile))
    val tokens = new CommonTokenStream(lex)
      println("tokens: " + tokens.getTokens)

    val g = new CREOLEParser(tokens);
    g.setTreeAdaptor(adaptor)
    val tree = g.start().getTree().asInstanceOf[^];

    println("tree: " + tree.toStringTree)

    //Interpret Program
    val interp = new Interpreter(g.getCHRTreeTokens)
    interp.runScript(tree)
  //		println(interp.runScript(programTree))

    assertTrue(true)
  }

  @Test(timeout=1000)
  def testTranslate{
    println(args(0))
    //My own TreeAdaptor
    val adaptor = new CHRTreeAdaptor

    val url = this.getClass.getClassLoader.getResource(args(0))
    println("url: " + url)

    val lex = new CREOLE_XLexer(new ANTLRFileStream(url.getFile))
    val tokens = new CommonTokenStream(lex)
      println("tokens: " + tokens.getTokens)

    val g = new CREOLE_XParser(tokens);
    g.setTreeAdaptor(adaptor)
    val tree = g.start().getTree().asInstanceOf[^];

    println("tree: " + tree.toStringTree)
    val tokenz = g.getCHRTreeTokens

    //Translate Program
    val translatr = new Translator(tokenz)
    val translatedTree = translatr.translate(tree)

    println("tree': " + translatedTree.toStringTree)

    // GENERATE AND DRAW DOT AST
//    val gen = new DOTTreeGenerator()
//    val st = gen.toDOT(translatedTree);
//    val bout = new BufferedWriter(new FileWriter("Test.dot"));
//    bout.write(st.toString());
//    bout.close();
//    val rt = Runtime.getRuntime();
//    val pr = rt
//        .exec("/Applications/Graphviz.app/Contents/MacOS/Graphviz Test.dot");

    //Execute script
    val interp = new Interpreter(tokenz)
    interp.runScript(translatedTree)
//  		println(interp.runScript(programTree))

    assertTrue(true)
  }

}