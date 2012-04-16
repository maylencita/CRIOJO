package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 28, 2010
 * Time: 7:45:45 PM
 * To change this template use File | Settings | File Templates.
 */

import factory.DefaultFactory
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._
import fr.emn.criojo.lang._
import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.ext.debug.DebugCham

import fr.emn.criojo.ext.expression.converters._

class ChamTest {
  import Criojo._

  logLevel = INFO



  @Test
  def testRelations() {

    val machine = new IntegerCham with DebugCham {
      val x,y,z = Var
      val R = Rel("R")
      val S = Rel("S")
      val Z = Rel("S")

      rules(
        (R(x,y) &: R(y,z)) --> R(x,z),
        S(x,y) --> R(x,y)
      )
    }

//    import machine.num2fun

    machine.enableSolutionTrace()

    machine.introduceMolecule(machine.R(1,2))
    machine.introduceMolecule(machine.S(2,1))

    machine.executeRules()

    assert(machine.containsRelation(machine.R,1)) // S(x,y) -> R(x,y) and R(x,y),R(y,z) -> R(x,y)
    assert(machine.containsRelation(machine.Z,0)) // no Z atom
  }


  @Test
  def BonbonsTestRelations() {

    val machine = new Cham with IntegerCham with DebugCham {
      val x,y,z = Var
      val OneBonbon = Rel("OneBonbon")
      val TwoBonbons = Rel("TwoBonbons")

      rules(
        (OneBonbon() &: OneBonbon()) --> TwoBonbons()
      )
    }

//    import machine.num2fun
    machine.enableSolutionTrace()

    machine.introduceMolecule(machine.OneBonbon())
    assert(machine.containsRelation(machine.OneBonbon,1))
    assert(machine.containsRelation(machine.TwoBonbons,0))

    machine.executeRules()
    machine.introduceMolecule(machine.OneBonbon())
    assert(machine.containsRelation(machine.OneBonbon,2))
    assert(machine.containsRelation(machine.TwoBonbons,0))

    machine.executeRules()
    assert(machine.containsRelation(machine.OneBonbon,0))
    assert(machine.containsRelation(machine.TwoBonbons,1))
    assert(machine.getSolution.size==1)
  }

  @Test
  def H4ORelations() {

    val machine = new Cham with IntegerCham with DebugCham { //TestCham with DefaultCham{
      val a,b,x,y,z = Var
      val H = Rel("H")
      val O = Rel("O")
      val H4O = Rel("H4O")

      rules(
        (H() & H() & H() & H() & O()) --> H4O()
      )
    }

//    import machine.num2fun

    implicit def str2fun(n:String):Term = new ValueTerm[String](n) //new IntTerm(n)
    machine.enableSolutionTrace()

    machine.introduceMolecule(machine.H())
    machine.introduceMolecule(machine.H())
    machine.introduceMolecule(machine.H())
    machine.introduceMolecule(machine.H())
    machine.introduceMolecule(machine.O())
    machine.executeRules()

    assert(machine.containsRelation(machine.H4O,1))
    assert(machine.containsRelation(machine.H,0))
    assert(machine.containsRelation(machine.O,0))
    assert(machine.getSolution.size==1)
  }

  @Test(timeout=1000)
  def testAtomInsertion(){

    val machine = new Cham with DebugCham with DefaultFactory {
      val x,y,z = Var
      val R = Rel("R")
      val S = Rel("S")

      rules(
        (R(x,y) & R(z,y)) --> R(x,z),
        (R(x,y) & R(y,z)) --> R(x,z),
        S(x,y) --> R(x,y)
      )
    }

    val a1 = Atom("R", Variable("a"),Variable("b"))
    val a2 = Atom("S", Variable("b"),Variable("c"))
    val a3 = Atom("R", Variable("a"),Variable("c"))

    machine.enableSolutionTrace()

    machine.introduceAtom(a1)
    assert(machine.containsRelation(machine.R,  1))
    assert(machine.containsRelation(machine.S,  0))

    machine.introduceAtom(a2)
    machine.introduceAtom(a3)
    assert(machine.containsRelation(machine.R,  2))
    assert(machine.containsRelation(machine.S,  1))
    assertEquals(3, machine.getSolution.size)

    machine.executeRules()
    assert(machine.containsRelation(machine.R,  1))
    assert(machine.containsRelation(machine.S,  0))
  }

  @Test (timeout=1000)
  def testGuardSubs(){

    logLevel = DEBUG
    val a = Variable("a")
    val b = Variable("b")
    val c = Variable("c")
    val d = Variable("d")

    val machine = new Cham with DefaultFactory with DebugCham {
      val s,x,y,z = Var
      val R = Rel("R")
      val X1 = Rel("X1")

      rules(
        R(s,x,y) --> Abs(X1(s)) ?: (R(s,x,y) &: R(s,x,y) &: X1(s)) // (1)
      )
    }

    machine.enableSolutionTrace()

    val atom = Atom("R", Variable("1"), a, b)
    val atom2 = Atom("R", Variable("2"), a, b)
    machine.introduceAtom(atom)
    machine.introduceAtom(atom2)

    assert(machine.containsRelation(machine.R, 2))
    assert(machine.containsRelation(machine.X1, 0))

    machine.executeRules() // the rule (1) should execute two times

    assert(machine.containsRelation(machine.R, 4))
    assert(machine.containsRelation(machine.X1, 2))
  }

  @Test (timeout=1000)
  def testNu(){

    logLevel = DEBUG

    var result = false
    val a = Variable("a")
    val b = Variable("b")

    val machine = new Cham with DefaultFactory {
      val x,y,z,w = Var
      val S = Rel("S")
      val R = NativeRelation("R"){
        case (Atom("R", o::v2::v3::_),s) if (v2 != Undef && v3 != Undef) => result = true
        case at => fail("Expected: R(x1,x2,x3). Actual: " + at)
      }

      rules{
        S(x) --> Nu(y,z)(R(x,y,z))
      }
    }

    machine.introduceAtom(Atom("S", a))
    machine.executeRules()

    assertTrue(result) // triggered in the "R" nativeRelation
  }

  @Test (timeout=1000)
  def testHORelation(){

    logLevel = DEBUG

    val a = Variable("a")
    val b = Variable("b")
    var result = false

    val machine = new Cham with DefaultFactory with DebugCham {
      val x,y,z,w = Var
      val Cont = Rel("Cont")

      val R = Rel("R"); val S = Rel("S")
      val Resp = NativeRelation("Resp"){
        case (Atom("Resp", o::p::_),s) => result = true
        case resp => fail("Expected atom: Resp(a,b). Actual: " + resp)
      }
      val RespVar = RelVariable(Resp)

      rules(
        S(x,y) --> R(x,y,RespVar),
        R(x,y,Cont) --> Cont(x,y)
      )
    }

    val atom1 = Atom("S", a, b)
    machine.introduceAtom(atom1)
    machine.executeRules()

    //TODO rewrite test
    assertTrue(result) // triggered in the "Resp" nativeRelation

  }


  @Test //(timeout=1000)
  def simpleTest2(){
    val sm = new Cham with IntegerCham with DebugCham {
      val A = Rel("A")
      val B = Rel("B")
      val C = Rel("C")
      val D = Rel("D")

      rules(
        (A() & B() & C()) --> D(),
        (D() & C()) --> A()
      )
    }

//    import sm.{num2fun}
    sm.enableSolutionTrace()

    sm.introduceMolecule(sm.A())
    sm.introduceMolecule(sm.C())
    sm.introduceMolecule(sm.B())
    sm.introduceMolecule(sm.C())
    sm.introduceMolecule(sm.C())
    sm.executeRules()

    assertTrue(sm.containsRelation(sm.A,  1))
    assertTrue(sm.containsRelation(sm.C,  1))
    assertTrue(sm.containsRelation(sm.B,  0))
    assertTrue(sm.containsRelation(sm.D,  0))

  }
}