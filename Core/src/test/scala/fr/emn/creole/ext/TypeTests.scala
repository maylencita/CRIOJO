package fr.emn.creole.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 28, 2010
 * Time: 12:14:33 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._
import fr.emn.creole.util.Logger._

import org.junit._
import Assert._

import java.net.URI

class TypeTests{

  val x = Variable("x")
  val y = Variable("y")
  val z = Variable("z")
  val w = Variable("w")
  val a = Variable("a")
  val b = Variable("b")
  val c = Variable("c")
  val d = Variable("d")

  val EQQ = "Eq"

  @Test
  def testEq{
    logLevel = DEBUG
    val machine = new LocalVM

    log("relations: " + machine.relations)
    log("rules: " + machine.rules)

    machine.introduceAtom(Atom(EQQ, a, b))
    machine.introduceAtom(Atom(EQQ, c, d))

    log("solution: " + machine.solution)

//    info(this.getClass, "testEq", "eqClasses= " + eqClasses)
    assertTrue("Missing elements from eqClasses: " + machine.eqClasses,
      machine.eqClasses.exists(ec => ec.contains(a) && ec.contains(b)) &&
      machine.eqClasses.exists(ec => ec.contains(c) && ec.contains(d))
    )

    machine.introduceAtom(Atom(EQQ, b, c))
//    info(this.getClass, "testEq", "eqClasses= " + eqClasses)
    assertTrue("Wrong eqClasses: " + machine.eqClasses,
      machine.eqClasses.exists(ec => ec.contains(a) && ec.contains(b) &&
        ec.contains(c) && ec.contains(d))
    )
  }

  @Test
  def testAskEq{
    val machine = new CHAM with Eq{
      val R = Rel("R")

      val r2 = (R("x","y") &: R("z","w") &: EQ("y","z")) ==> R("x","w")
    }
    log("Relations: " + machine.relations)
    log("Solution: " + machine.solution)
    log("Rules: " + machine.rules)

    val R = "R"

    machine.introduceAtom(Atom(R, a, b))
    machine.introduceAtom(Atom(R, b, c))

    val atom = Atom(R, a,c)
    assertTrue("Expected value in solution: " + atom + ". Actual solution: " + machine.solution,
      machine.solution.exists(at => at.relName == atom.relName && at.vars == atom.vars)  
    )
  }

  @Test
  def testPrint{
    logLevel = INFO
    val machine = new VirtualMachine{
      val T = Rel("T")
      val r = T("x") ==> Print("x")

      def newRemoteRelation(remoteName:String,url:String):RemoteRelation = null
    }

    machine.introduceAtom(Atom("T", Value("Mayleen")))
  }
}