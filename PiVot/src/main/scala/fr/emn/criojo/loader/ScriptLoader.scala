package fr.emn.criojo.loader

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 27, 2010
 * Time: 11:22:19 AM
 * To change this template use File | Settings | File Templates.
 */
import org.antlr.runtime._

import fr.emn.criojo.ext._
import fr.emn.criojo.parser._
import fr.emn.criojo.parser.tree._
import fr.emn.criojo.interpreter._

import java.net.URL

object ScriptLoader{

  def load(machine:ExtendedCHAM, url:URL){
    val lex = new CREOLE_XLexer(new ANTLRFileStream(url.getFile))
    load(machine,lex)
  }

  def load(machine:ExtendedCHAM, script:String){
    val lex = new CREOLE_XLexer(new ANTLRStringStream(script))
    load(machine,lex)
  }

  private def load(machine:ExtendedCHAM, lex: CREOLE_XLexer){
    val adaptor = new CHRTreeAdaptor

    val tokens = new CommonTokenStream(lex)

    val g = new CREOLE_XParser(tokens);
    g.setTreeAdaptor(adaptor)
    val tree = g.start().getTree().asInstanceOf[^];

    val tokenz = g.getCHRTreeTokens

    //Translate Program
    val translatr = new Translator(tokenz)
    val translatedTree = translatr.translate(tree)

    //Interpret Program
    val interp = new Interpreter(machine, tokenz)
    interp.runScript(translatedTree)
  }
}