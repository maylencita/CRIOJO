package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 7, 2010
 * Time: 11:45:30 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._

class EqTests{
  val x = Variable("x")
  val y = Variable("y")
  val z = Variable("z")
  val w = Variable("w")
  val a = Variable("a")
  val b = Variable("b")
  val c = Variable("c")
  val d = Variable("d")

  val testMachine = new LocalCHAM
  val EQQ = testMachine.EQ

  @Test (timeout=1000)
  def testEq{
//    logLevel = DEBUG
    val machine = new LocalCHAM

    log("relations: " + machine.relations)
    log("rules: " + machine.rules)

    machine.introduceAtom(Atom(EQQ, a, b))
    machine.introduceAtom(Atom(EQQ, c, d))

    log("solution: " + machine.solution)

//    info(this.getClass, "testEq", "eqClasses= " + eqClasses)
    assertTrue("Missing elements from eqClasses: " + machine.eqClasses + ". Expected: <{a,b},{c,d}>",
      machine.eqClasses.exists(ec => ec.contains(a) && ec.contains(b)) &&
      machine.eqClasses.exists(ec => ec.contains(c) && ec.contains(d))
    )

    machine.introduceAtom(Atom(EQQ, b, c))
//    info(this.getClass, "testEq", "eqClasses= " + eqClasses)
    assertTrue("Wrong eqClasses: " + machine.eqClasses + ". Expected: <{a,b,c,d}>",
      machine.eqClasses.exists(ec => ec.contains(a) && ec.contains(b) &&
        ec.contains(c) && ec.contains(d))
    )
  }

  @Test (timeout=1000, expected=classOf[InvalidStateError])
  def testErrorNotEq{
    logLevel = INFO
    val machine = new LocalCHAM {
      val R = Rel("R") ; val S=Rel("S")
      val x,y,z,w = Var

      rules((R(x,y) &: R(x,w)) ==> EQ(y,w))
    }
    log("Relations: " + machine.relations)
    log("Solution: " + machine.solution)
    log("Rules: " + machine.rules)

    val R = "R"

    machine.introduceAtom(Atom(R, a, b))
    machine.introduceAtom(Atom(R, a, c))
    machine.introduceAtom(Atom("$NotEq", b, c))
  }

  @Test (timeout=1000,expected=classOf[InvalidStateError])
  def testErrorEq{
    logLevel = DEBUG
    val machine = new LocalCHAM {
      val R = Rel("R") ; val S=Rel("S")
      val x,y,z,w = Var

      rules(
        (S(x) &: R(x,w)) ==> NotEQ(x,w)
      )
    }
    log("Relations: " + machine.relations)
    log("Solution: " + machine.solution)
    log("Rules: " + machine.rules)

    machine.introduceAtom(Atom("S", a))
    machine.introduceAtom(Atom("R", a, a))
  }

}