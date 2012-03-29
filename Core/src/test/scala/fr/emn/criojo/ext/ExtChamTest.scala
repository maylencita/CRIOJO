package fr.emn.criojo.ext

import debug.DebugCham
import org.junit.Test
import fr.emn.criojo.lang.Cham
import org.junit.Assert._
import fr.emn.criojo.core._


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/16/12
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */

class ExtChamTest {

  @Test
  def EqChamTest {
    //TODO Ameliorate this test
    val sm = new EqCHAM with IntegerCham with DebugCham {
      val A = Rel("A")
      val B = Rel("B")
      val C = Rel("C")
      val D = Rel("D")

      var x,y,v,z = Var
      var vx:Term = 1
      var vy:Term = 2

      rules(
        (A() & B() & C()) --> D(),
        (D(x) & C(y)) --> A()
      )

      val solution2 = new StandAloneSolution()
      val r2 = new LocalRelation("R")
      val a2 = Atom(r2, x)
      val a22 = Atom(r2, y)
      solution2.addAtom(a2)
      solution2.addAtom(a22)

      assertTrue(askEquivalence(x,x,solution2))
      assertFalse(askEquivalence(x,y,solution2))

//      assertTrue(getSubstitutions(a2,List(a2,a22)).size>0)
//      assertTrue(getSubstitutions(a2,List(a2,a22)).size>0)
//      assertTrue(getSubstitutions(a2,List()).size==0)
    }

    import sm.{num2fun}
    sm.enableSolutionTrace()

    sm.introduceMolecule(sm.A())
    sm.introduceMolecule(sm.C())
    sm.introduceMolecule(sm.B())
    sm.introduceMolecule(sm.C(1))
    sm.introduceMolecule(sm.C(2))
    sm.executeRules()

    val solution = new StandAloneSolution()
    val r = new LocalRelation("R")
    val a = Atom(r, sm.x)
    val a2 = Atom(r, sm.y)
    solution.addAtom(a)
    solution.addAtom(a2)

    assertTrue(sm.getSolution.size==1)

    // value x value
    assertTrue(sm.Eq(sm.x,sm.x).eval(Valuation(sm.x -> 3,sm.y -> 2)))
    assertTrue(!sm.Eq(sm.x,sm.y).eval(Valuation(sm.x-> 3,sm.y-> 2)))

    assertTrue(!sm.NotEq(sm.x,sm.x).eval(Valuation(sm.x-> 3,sm.y-> 2)))
    assertTrue(sm.NotEq(sm.x,sm.y).eval(Valuation(sm.x-> 3,sm.y-> 2)))

    // variable x variable
    assertTrue(sm.Eq(sm.v,sm.v).eval(Valuation(sm.x-> 3,sm.y-> 2)))
    assertTrue(!sm.NotEq(sm.v,sm.v).eval(Valuation(sm.x-> 3,sm.y-> 2)))

    // variable x value
    assertTrue(!sm.Eq(sm.x,sm.v).eval(Valuation(sm.x-> 3,sm.y-> 2)))
    assertTrue(sm.NotEq(sm.x,sm.v).eval(Valuation(sm.x-> 3,sm.y-> 2)))

    // value x variable
    assertTrue(!sm.Eq(sm.v,sm.y).eval(Valuation(sm.x-> 3,sm.y-> 2)))
    assertTrue(sm.NotEq(sm.v,sm.y).eval(Valuation(sm.x-> 3,sm.y-> 2)))
  }

  @Test
  def ExtendedChamTest {
    //TODO Review ExtendedCham
    val sm = new ExtendedCHAM with IntegerCham with DebugCham {
      val A = Rel("A")
      val B = Rel("B")
      val C = Rel("C")
      val D = Rel("D")

      var x,y,v,z = Var
      var vx:Term = 1
      var vy:Term = 2

      rules(
        (A() & B() & C()) --> D(),
        (D(x) & C(y)) --> A()
      )

      val solution2 = new StandAloneSolution()
      val r2 = new LocalRelation("R")
      val a2 = Atom(r2, x)
      val a22 = Atom(r2, y)
      solution2.addAtom(a2)
      solution2.addAtom(a22)

      assertTrue(askEquivalence(x,x,solution2))
      assertFalse(askEquivalence(x,y,solution2))

//      assertTrue(getSubstitutions(a2,List(a2,a22)).size>0)
//      assertTrue(getSubstitutions(a2,List(a2,a22)).size>0)
//      assertTrue(getSubstitutions(a2,List()).size==0)
    }

    import sm.{num2fun}
    sm.enableSolutionTrace()

    sm.introduceMolecule(sm.A())
    sm.introduceMolecule(sm.C())
    sm.introduceMolecule(sm.B())
    sm.introduceMolecule(sm.C(1))
    sm.introduceMolecule(sm.C(2))
    sm.executeRules()

    val solution = new StandAloneSolution()
    val r = new LocalRelation("R")
    val a = Atom(r, sm.x)
    val a2 = Atom(r, sm.y)
    solution.addAtom(a)
    solution.addAtom(a2)

    println(sm.getSolution)
    assertTrue(sm.containsMolecule(sm.D()))
    
    assertTrue(sm.getValue(sm.x)!=null)
    assertTrue(sm.getValue(sm.v).isInstanceOf[Value[Nothing]])
  }
}
