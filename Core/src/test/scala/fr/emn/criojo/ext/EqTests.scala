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
    logLevel = INFO
    val machine = new CHAM with Eq with ValueVM  {
      val R = Rel("R") ; val S=Rel("S")
      val x,y,z,w = Var

      val r2 = (R(x,y) &: R(z,w) &: EQ(y,z)) ==> R(x,w)
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
  def testEqValue{
    logLevel = DEBUG
    val v1 = Variable("1")
    val machine = new LocalVM

    machine.introduceAtom(Atom("$Int", v1,a))
    machine.introduceAtom(Atom("$Int", v1,b))

    assertTrue("Missing elements from eqClasses: " + machine.eqClasses,
      machine.eqClasses.exists(ec => ec.contains(a) && ec.contains(b)) 
    )

  }

  @Test
  def testEqAskValue{
    logLevel = DEBUG
    val v1 = Variable("1")

    val machine = new CHAM with Eq with ValueVM{
      val R = Rel("R")
      val S = Rel("S")

      (S(x) &: S(y) &: EQ(x,y)) ==> R(x,y)
    }

    machine.introduceAtom(Atom("$Int", v1,a))
    machine.introduceAtom(Atom("$Int", v1,b))
    machine.introduceAtom(Atom("S", a))
    machine.introduceAtom(Atom("S", b))

    val ra = Atom("R", a,b)
    assertFalse("Expected value in solution: " + ra + ". Actual solution: " + machine.solution,
      machine.query(List(ra), List()).isEmpty
    )

  }
}