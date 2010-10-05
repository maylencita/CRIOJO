package fr.emn.criojo.util

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 20, 2010
 * Time: 3:18:37 PM
 * To change this template use File | Settings | File Templates.
 */

import org.antlr.runtime._

import fr.emn.creole.ext._
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
//    Logger.log(this.getClass,"load", "tokens: " + tokens.getTokens)

    val g = new CREOLE_XParser(tokens);
    g.setTreeAdaptor(adaptor)
    val tree = g.start().getTree().asInstanceOf[^];

//    Logger.log(this.getClass, "load", "tree: " + tree.toStringTree)
    val tokenz = g.getCHRTreeTokens

    //Translate Program
    val translatr = new Translator(tokenz)
    val translatedTree = translatr.translate(tree)

//    Logger.log(this.getClass, "load", "tree': " + translatedTree.toStringTree)

    //Interpret Program
    val interp = new Interpreter(machine, tokenz)
    interp.runScript(translatedTree)
  }
}