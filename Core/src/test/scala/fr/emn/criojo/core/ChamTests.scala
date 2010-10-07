package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 28, 2010
 * Time: 7:45:45 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._

class ChamTests{
  import Creole._

  val machine:CHAM = new CHAM{
//    val solution = new Solution
//    Conjunction.configMachine(this)
    val x,y,z = Var //("x","y","z")
    val R = Rel("R")
    val S = Rel("S")

    val r1:Rule = (R(x,y) &: R(y,z)) ==> R(x,z)
    val r2 = S(x,y) ==> R(x,y)
  }

  @Test
  def testRelations{
    val relLst = new LocalRelation("R") :: new LocalRelation("S") :: Nil
    log("relLst: " + relLst)
    for(r <- relLst){
      if (!machine.relations.contains(r))
        fail("Expected: " + relLst + " Actual: " + machine.relations)
    }
  }

  @Test
  def testRuleCreation{
    val x = Variable("x")
    val y = Variable("y")
    val z = Variable("z")
    val r1:Rule = new Rule{
      val head = List(Atom("R",x,y),Atom("R",y,z))
      val body = List(Atom("R",x,z))
      val guard = new Guard(List())
      def execute (subs:List[Substitution]):Boolean = true
    }

    assertTrue("Rule "+ r1 +" not found! Existing rules: " + machine.rules, machine.rules.exists(_ == r1))
  }

  @Test(timeout=1000)
  def testAtomInsertion{
    log("relations: " + machine.relations)
    log("rules: " + machine.rules)

    val a1 = Atom("R", Variable("a"),Variable("b"))
    val a2 = Atom("S", Variable("b"),Variable("c"))
    val a3 = Atom("R", Variable("a"),Variable("c"))

    machine.introduceAtom(a1)
    assertEquals(Solution(a1), machine.solution)

    machine.introduceAtom(a2)
    log(this.getClass, "testAtomInsertion", "solution: " + machine.solution)

    assertEquals(1, machine.solution.size)
    assertTrue(machine.solution.exists(a => a.relName == a3.relName && a.vars == a3.vars))
  }

  @Test
  def testGuard{
    val m2 = new CHAM{
      val x,y1,y2,z = Var //("x","y1","y2","z")
      val R = Rel("R")
      val S = Rel("S")

      val r1 = (R(x,y1) &: R(y2,z)) ==> (?((True &: R(x,z)) ==> False), R(x,z)) 
    }
    log ("m2: " + m2.rules)
  }
  object ? {
    def apply(rules:Rule*):Guard = new Guard(rules.toList)
  }
}