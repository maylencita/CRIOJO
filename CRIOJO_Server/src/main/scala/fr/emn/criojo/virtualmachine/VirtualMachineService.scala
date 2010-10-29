package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 11, 2010
 * Time: 4:35:12 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.ext._
import fr.emn.criojo.loader.ScriptLoader

import fr.emn.criojo.util.Logger._

import java.net.URI

object VirtualMachineConfigurator{
  var url:URI = _
}

object VirtualMachineService extends ConnectedVM(VirtualMachineConfigurator.url){
  var started = false

//  override val solution = new CachedSolution

  def start{
    execute
    started = true
  }

  def runScript(script:String){
    ScriptLoader.load(this, script)
  }

  def runScript(script:java.net.URL){
    ScriptLoader.load(this, script)
  }

  /**
   * Initializes the virtual machine
   */
  def loadScript(){
    val scriptUrl = this.getClass.getClassLoader.getResource("fr/emn/criojo/virtualmachine/vm.crj")//"fr/emn/criojo/test/naive_execution_test.crl")
    info(this.getClass, "loadScript", "url: " + scriptUrl)
    if (scriptUrl != null){
//      ScriptLoader.load(machine, scriptUrl)
      ScriptLoader.load(this, scriptUrl)
      start //TODO Must be called explicitly
    }
  }

  def getSolution:String = {
    prettyPrint
  }

}

