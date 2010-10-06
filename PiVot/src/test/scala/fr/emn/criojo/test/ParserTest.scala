package fr.emn.criojo.test


/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 8, 2010
 * Time: 9:58:34 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.parser._
import fr.emn.criojo.parser.tree._

import java.io._

import org.antlr.runtime._
import org.antlr.runtime.tree.DOTTreeGenerator
                 
object ParserTest extends Application{
  //My own TreeAdaptor
  val adaptor = new CHRTreeAdaptor

  val url = this.getClass.getClassLoader.getResource("fr/emn/criojo/test/grammar_test.crl")
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
	val gen = new DOTTreeGenerator()
	val st = gen.toDOT(tree);
	val bout = new BufferedWriter(new FileWriter("QueryTest.dot"));
	bout.write(st.toString());
	bout.close();

	val rt = Runtime.getRuntime();
	val pr = rt
			.exec("/Applications/Graphviz.app/Contents/MacOS/Graphviz QueryTest.dot");


}