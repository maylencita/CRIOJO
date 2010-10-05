package fr.emn.creole.test

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 23, 2010
 * Time: 3:53:56 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.creole.parser._
import fr.emn.creole.parser.tree._
import fr.emn.creole.interpreter._

import java.io._

import org.antlr.runtime._
import org.antlr.runtime.tree.DOTTreeGenerator

object TranslateTest extends Application{
  override def main(args:Array[String]){
    println(args(0))
    //My own TreeAdaptor
    val adaptor = new CHRTreeAdaptor

    val url = this.getClass.getClassLoader.getResource(args(0))//"fr/emn/creole/test/naive_execution_test.crl")
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
    val gen = new DOTTreeGenerator()
    val st = gen.toDOT(translatedTree);
//    val st = gen.toDOT(tree);
    val bout = new BufferedWriter(new FileWriter("Test.dot"));
    bout.write(st.toString());
    bout.close();
    val rt = Runtime.getRuntime();
    val pr = rt
        .exec("/Applications/Graphviz.app/Contents/MacOS/Graphviz Test.dot");

    //Execute Program
//    val interp = new Interpreter(tokenz)
//    interp.runScript(translatedTree)
  //		println(interp.runScript(programTree))
  }

}

