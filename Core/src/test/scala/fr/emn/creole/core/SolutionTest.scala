package fr.emn.creole.core

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

  val Solution = new Solution()
  val r = new LocalRelation("R")
  val a = Atom(r, Variable("x1"))
  val a2 = Atom(r, Variable("x1"))

  @Test
  def testAdd2Times{
    Solution.addAtom(a)
    Solution.addAtom(a)

    assertTrue("<R(x1)>" == Solution.toString)
  }

  @Test
  def testMultiRel{
    Solution.addAtom(a)
    Solution.addAtom(a2)

    println("[testMultiRel] Solution= " + Solution)
    assertTrue("<R(x1),R(x1)>" == Solution.toString)
  }

  @Test
  def testCleanup{
    Solution.addAtom(a)
    a.active = false
    Solution.cleanup

    assertTrue("<>" == Solution.toString)
  }

  @Test
  def testRevert{
    Solution.addAtom(a)
    a.active = false
    Solution.revert

    assertTrue("<R(x1)>" == Solution.toString)
  }

  @Test
  def testClone{
    Solution.addAtom(a)
    Solution.addAtom(a2)

    val sol2 = Solution.clone

    assertTrue(Solution.toString == sol2.toString)

    sol2.addAtom(Atom(r, Variable("x2")))
    println("[testClone] Solution, sol2:" + Solution + ", " + sol2)

    assertFalse(Solution.toString == sol2.toString)
  }

  @Test
  def testEquals{
    val b = Atom(r, Variable("y1"))
    val sol1 = new Solution(Set(a,b))
    val sol2 = new Solution(Set(a,b))

    println("[testEquals] sol1, sol2:" + sol1 + ", " + sol2)

    assertTrue(sol1 == sol2)

    sol2.addAtom(Atom(r, Variable("x3")))

    println("[testEquals] sol1, sol2:" + sol1 + ", " + sol2)

    assertFalse(sol1 == sol2)
  }

  @Test
  def testUpdate{
    Solution.addAtom(a); Solution.addAtom(a2)

    val sol2 = Solution.clone

    a.active = false
    sol2.addAtom(Atom(r, Variable("y1")))
    sol2.cleanup

    println("[testUpdate] Solution, sol2: " + Solution + ", " + sol2)
    assertFalse(Solution == sol2)

    Solution.update(sol2)

    assertTrue("<R(x1),R(y1)>" == Solution.toString)
  }

}