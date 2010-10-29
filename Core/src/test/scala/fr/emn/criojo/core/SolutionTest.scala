package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 25, 2010
 * Time: 11:08:23 PM
 * To change this template use File | Settings | File Templates.
 */

import org.junit._
import Assert._

//object SolutionTest{
//  def suite: Test = {
//      val suite = new TestSuite(classOf[SolutionTest]);
//      suite
//  }
//
//  def main(args : Array[String]) {
//      junit.textui.TestRunner.run(suite);
//  }
//}

class SolutionTest {

  val solution = /*new*/ Solution()
  val r = new LocalRelation("R")
  val a = Atom(r, Variable("x1"))
  val a2 = Atom(r, Variable("x1"))

//  @Test
//  def testAdd2Times{
//    solution.addAtom(a)
//    solution.addAtom(a)
//
//    assertTrue("<R(x1)>" == solution.toString)
//  }

  @Test
  def testMultiRel{
    solution.addAtom(a)
    solution.addAtom(a2)

    println("[testMultiRel] Solution= " + solution)
    assertTrue("<R(x1),R(x1)>" == solution.toString)
  }

  @Test
  def testCleanup{
    solution.addAtom(a)
    a.setActive(false)
    solution.cleanup

    assertTrue("<>" == solution.toString)
  }

  @Test
  def testRevert{
    solution.addAtom(a)
    a.setActive(false)
    solution.revert

    assertTrue("<R(x1)>" == solution.toString)
  }

  @Test
  def testClone{
    solution.addAtom(a)
    solution.addAtom(a2)

    val sol2 = solution.clone

    assertTrue(solution.toString == sol2.toString)

    sol2.addAtom(Atom(r, Variable("x2")))
    println("[testClone] Solution, sol2:" + solution + ", " + sol2)

    assertFalse(solution.toString == sol2.toString)
  }

  @Test
  def testEquals{
    val b = Atom(r, Variable("y1"))
    val sol1 = /*new*/ Solution.createDefault(List(a,b))
    val sol2 = /*new*/ Solution.createDefault(List(a,b))

    println("[testEquals] sol1, sol2:" + sol1 + ", " + sol2)

    assertTrue(sol1 == sol2)

    sol2.addAtom(Atom(r, Variable("x3")))

    println("[testEquals] sol1, sol2:" + sol1 + ", " + sol2)

    assertFalse(sol1 == sol2)
  }

  @Test
  def testUpdate{
    solution.addAtom(a); solution.addAtom(a2)

    val sol2 = solution.clone

    a.setActive(false)
    sol2.addAtom(Atom(r, Variable("y1")))
    sol2.cleanup

    println("[testUpdate] Solution, sol2: " + solution + ", " + sol2)
    assertFalse(solution == sol2)

    solution.update(sol2)

    assertTrue("<R(x1),R(y1)>" == solution.toString)
  }

}