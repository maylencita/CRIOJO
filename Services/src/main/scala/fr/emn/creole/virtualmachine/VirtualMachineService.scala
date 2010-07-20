package fr.emn.creole.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 11, 2010
 * Time: 4:35:12 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._
import fr.emn.creole.parser._
import fr.emn.creole.parser.tree._
import fr.emn.creole.interpreter._
import fr.emn.creole.util._

import java.io._

import org.antlr.runtime._
import org.antlr.runtime.tree.DOTTreeGenerator

object VirtualMachineService{
  val machine:VirtualMachine = new VirtualMachine()

  loadScript()

  def addAtom(atom:String){
    machine.introduceAtom(new Atom(atom, List()))
  }

  def addAtom(atom:String, terms:List[String]){
    machine.introduceAtom(new Atom(atom, terms.map(t => new Variable(t))))
  }

  def addAtom(atom:Atom){
    machine.introduceAtom(atom)
  }

  def runScript(script:String){
    ScriptLoader.load(script, this.machine)
  }

  def loadScript(){
    val url = this.getClass.getClassLoader.getResource("fr/emn/creole/script/vm.crl")//"fr/emn/creole/test/naive_execution_test.crl")
    println("url: " + url)
    if (url != null){
      ScriptLoader.load(url, this.machine)
    }
  }

  def getSolution:String = this.machine.solution.toString
}