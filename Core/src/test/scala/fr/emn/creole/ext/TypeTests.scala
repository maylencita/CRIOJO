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

import collection.mutable.HashSet

//class TypeTests extends TestCase("types"){
class TypeTests{
  val machine = new VirtualMachine
  val x = Variable("x")
  val y = Variable("y")
  val z = Variable("z")
  val a = Variable("a")
  val b = Variable("b")
  val c = Variable("c")

  import machine._

  @Test
  def testEq{

    introduceAtom(Atom(EQ, a, b))
    introduceAtom(Atom(EQ, x, y))

//    info(this.getClass, "testEq", "eqClasses= " + eqClasses)
    assertTrue{"Missing elements from eqClasses: " + eqClasses
      eqClasses.exists(ec => ec.contains(a) && ec.contains(b)) &&
      eqClasses.exists(ec => ec.contains(x) && ec.contains(y))
    }

    introduceAtom(Atom(EQ, b, y))
//    info(this.getClass, "testEq", "eqClasses= " + eqClasses)
    assertTrue{"Wrong eqClasses: " + eqClasses
      eqClasses.exists(ec => ec.contains(a) && ec.contains(b) &&
        ec.contains(x) && ec.contains(y))
    }
  }
}