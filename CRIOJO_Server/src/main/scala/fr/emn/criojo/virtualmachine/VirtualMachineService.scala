package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 11, 2010
 * Time: 4:35:12 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._
import fr.emn.creole.ext._
import fr.emn.creole.loader.ScriptLoader

import fr.emn.creole.util.Logger._

import java.net.URI

object VirtualMachineConfigurator{
  var url:URI = _
}

object VirtualMachineService extends ConnectedVM(VirtualMachineConfigurator.url){
//  var url:URI = _
  var started = false

//  val machine:VirtualMachine = new ConnectedVM(url)

//  def reset{
//    this machine = new ConnectedVM()
//  }

  def addAtom(atom:Atom){
    /*machine.*/introduceAtom(atom)
  }

//  def getRelation(relName:String):List[Atom]={
//
//  }

  def start{
    /*machine.*/execute
    started = true
  }

  def runScript(script:String){
//    ScriptLoader.load(machine, script)
    ScriptLoader.load(this, script)
  }

  def runScript(script:java.net.URL){
//    ScriptLoader.load(machine, script)
    ScriptLoader.load(this, script)
  }

  /**
   * Initializes the virtual machine
   */
  def loadScript(){
    val scriptUrl = this.getClass.getClassLoader.getResource("fr/emn/criojo/virtualmachine/vm.crj")//"fr/emn/creole/test/naive_execution_test.crl")
    info(this.getClass, "loadScript", "url: " + scriptUrl)
    if (scriptUrl != null){
//      ScriptLoader.load(machine, scriptUrl)
      ScriptLoader.load(this, scriptUrl)
      start //TODO Must be called explicitly
    }
  }

  def getSolution:String = /*this.machine.*/prettyPrint

//  def getValue(x:Variable) = machine.getValue(x)
}

