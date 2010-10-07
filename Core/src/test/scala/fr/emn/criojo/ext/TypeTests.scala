package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 28, 2010
 * Time: 12:14:33 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._

import java.net.URI

class TypeTests{

  @Test
  def testPrint{
    logLevel = INFO
    val machine = new VirtualMachine{
      val x = Var
      val T = Rel("T")
      val r = T(x) ==> Print(x)

      def newRemoteRelation(remoteName:String,url:String):RemoteRelation = null
    }

    machine.introduceAtom(Atom("T", Value("Mayleen")))
  }
}