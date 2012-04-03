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

class SolutionTest {

  val solution = new StandAloneSolution()
  val r = new LocalRelation("R")
  val a = Atom(r, Variable("x1"))
  val a2 = Atom(r, Variable("x1"))

  @Test (timeout=1000)
  def testMultiRel(){
    solution.addAtom(a)
    solution.addAtom(a2)

    assertTrue("<R(x1),R(x1)>" == solution.toString)
  }

  @Test (timeout=1000)
  def testCleanup(){
    solution.addAtom(a)
    a.setActive(false)
    solution.cleanup()

    assertTrue("<>" == solution.toString)
  }

  @Test (timeout=1000)
  def testRevert(){
    solution.addAtom(a)
    a.setActive(false)
    solution.revert()

    assertTrue("<R(x1)>" == solution.toString)
  }

  @Test  (timeout=1000)
  def testClone(){
    solution.addAtom(a)
    solution.addAtom(a2)

    val sol2 = solution.clone

    assertTrue(solution.toString == sol2.toString)

    sol2.addAtom(Atom(r, Variable("x2")))

    assertFalse(solution.toString == sol2.toString)
  }

  @Test (timeout=1000)
  def testEquals(){
    val b = Atom(r, Variable("y1"))
    val sol1 = StandAloneSolution(List(a,b))
    val sol2 = StandAloneSolution(List(a,b))

    assertTrue(sol1 == sol2)

    sol2.addAtom(Atom(r, Variable("x3")))

    assertFalse(sol1 == sol2)
  }

  @Test (timeout=1000)
  def testUpdate(){
    solution.addAtom(a); solution.addAtom(a2)

    val sol2 = solution.clone

    a.setActive(false)
    sol2.addAtom(Atom(r, Variable("y1")))
    sol2.cleanup()

    assertFalse(solution == sol2)

    solution.update(sol2)

    assertTrue("<R(x1),R(y1)>" == solution.toString)
  }

  @Test (timeout=1000)
  def EmptySolutionTest(){

    var emptySol = EmptySolution
    assertTrue(emptySol.size==0)
    emptySol.reverse()
    assertTrue(emptySol.size==0)
  }

  @Test (timeout=1000)
  def SolutionImplTest(){

    var solImpl = new SolutionImpl()
    assertTrue(solImpl.size==0)

    val solution = new StandAloneSolution()
    val r = new LocalRelation("R")
    val a = Atom(r, Variable("x1"))
    val a2 = Atom(r, Variable("x1"))

    assertTrue(solImpl.isEmpty)
    solution.addAtom(a)
    solution.addAtom(a2)

    assertTrue(solution.contains(a))

    solImpl.reverse()
    solImpl.update(solution)
    assertTrue(solImpl.size==2)

    solImpl.remove(a)
    assertTrue(solImpl.size==1)
    assertTrue(!solImpl.contains(a))

    solImpl.addAtom(a)
    assertTrue(solImpl.size==2)
  }
}