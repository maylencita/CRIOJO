package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 24, 2010
 * Time: 11:37:47 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._

object ScriptTest extends Application{
  val scriptUrl = this.getClass.getClassLoader.getResource("fr/emn/criojo/virtualmachine/script.crj")
  VirtualMachineService.runScript(scriptUrl)
  println("Solution: " + VirtualMachineService.getSolution)

  VirtualMachineService.addAtom(Atom("PAlbum", Variable("123")::Variable("1")::Variable("Italy")::Nil))

  VirtualMachineService.addAtom(Atom("PAlbum", Variable("123")::Variable("1")::Variable("Italy")::Nil))

  println("Relations: " + VirtualMachineService/*.machine*/.relations)
//  VirtualMachineService.addAtom(Atom("Session", List(Variable("x"))))
}