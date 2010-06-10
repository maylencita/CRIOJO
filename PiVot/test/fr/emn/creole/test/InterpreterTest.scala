package fr.emn.creole.test

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 4:30:42 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.parser._
import fr.emn.creole.parser.tree._
import fr.emn.creole.interpreter.Interpreter

import org.antlr.runtime._

object InterpreterTest extends Application{
//  println(args[0])
  //My own TreeAdaptor
  val adaptor = new CHRTreeAdaptor

  val url = this.getClass.getClassLoader.getResource("fr/emn/creole/test/grammar_test.crl")
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
}