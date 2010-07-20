package fr.emn.creole.util

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 20, 2010
 * Time: 3:18:37 PM
 * To change this template use File | Settings | File Templates.
 */

import org.antlr.runtime._

import fr.emn.creole.core._
import fr.emn.creole.parser._
import fr.emn.creole.parser.tree._
import fr.emn.creole.interpreter._

import java.net.URL

object ScriptLoader{

  def load(url:URL, machine:VirtualMachine){
    val lex = new CREOLE_XLexer(new ANTLRFileStream(url.getFile))
    load(lex, machine)
  }

  def load(script:String, machine:VirtualMachine){
    val lex = new CREOLE_XLexer(new ANTLRStringStream(script))
    load(lex,machine)
  }

  private def load(lex: CREOLE_XLexer, machine:VirtualMachine){
    val adaptor = new CHRTreeAdaptor

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

    //Interpret Program
    val interp = new Interpreter(machine, g.getCHRTreeTokens)
    interp.runScript(tree)
  }
}