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

import fr.emn.criojo.core.Converters._
import fr.emn.criojo.ext.expression.ScalaString.constructor.WrapScalaString
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.ext.expression.ScalaInt.VarScalaInt
import fr.emn.criojo.ext.expression.ScalaString.VarScalaString

class ChamTest {

  logLevel = INFO



  @Test
  def testRelations() {

    val machine = new IntegerCham with DebugCham {
      val x,y,z = VarScalaInt()
      val R = LocalRelation("R")
      val S = LocalRelation("S")
      val Z = LocalRelation("S")

      rules(
        (R(x,y) &: R(y,z)) --> R(x,z),
        S(x,y) --> R(x,y)
      )
    }

//    import machine.num2fun

    machine.enableSolutionTrace()
    machine.enableStreamingTrace()

    machine.introduceMolecule(machine.R(1,2))
    machine.introduceMolecule(machine.S(2,1))
    machine.introduceMolecule(machine.S(2,1))

    machine.executeRules()
    machine.printSolution()

    assert(machine.containsRelation(machine.R,1)) // S(x,y) -> R(x,y) and R(x,y),R(y,z) -> R(x,y)
    assert(machine.containsRelation(machine.Z,0)) // no Z atom
  }


  @Test
  def BonbonsTestRelations() {

    val machine = new Cham with IntegerCham with DebugCham {
      val x,y,z = VarScalaInt()
      val OneBonbon = LocalRelation("OneBonbon")
      val TwoBonbons = LocalRelation("TwoBonbons")

      rules(
        (OneBonbon() &: OneBonbon()) --> TwoBonbons()
      )
    }

//    import machine.num2fun
    machine.enableSolutionTrace()

    machine.introduceMolecule(machine.OneBonbon())
    machine.introduceMolecule(machine.OneBonbon())
    machine.executeRules()

    assert(machine.containsRelation(machine.OneBonbon,0))
    assert(machine.containsRelation(machine.TwoBonbons,1))

    assert(machine.solution.listOfAtoms.size==1)
  }

  @Test
  def H4ORelations() {

    val machine = new Cham with IntegerCham with DebugCham { //TestCham with DefaultCham{
      val a,b,x,y,z = VarScalaInt()
      val H = LocalRelation("H")
      val O = LocalRelation("O")
      val H4O = LocalRelation("H4O")

      rules(
        (H() & H() & H() & H() & O()) --> H4O()
      )
    }

    machine.enableSolutionTrace()
    machine.enableStreamingTrace()

    machine.introduceMolecule(machine.H())
    machine.introduceMolecule(machine.H())
    machine.introduceMolecule(machine.H())
    machine.introduceMolecule(machine.H())
    machine.introduceMolecule(machine.O())
    machine.executeRules()

    assert(machine.containsRelation(machine.H4O,1))
    assert(machine.containsRelation(machine.H,0))
    assert(machine.containsRelation(machine.O,0))
    assert(machine.solution.listOfAtoms.size==1)
  }

  @Test(timeout=1000)
  def testAtomInsertion(){

    val machine = new Cham with DebugCham with DefaultFactory {
      val x,y,z = VarScalaString()
      val R = LocalRelation("R")
      val S = LocalRelation("S")

      rules(
        (R(x,y) & R(z,y)) --> R(x,z),
        (R(x,y) & R(y,z)) --> R(x,z),
        S(x,y) --> R(x,y)
      )
    }

    val a1 = Atom(machine.R, WrapScalaString("a"),WrapScalaString("b"))
    val a2 = Atom(machine.S, WrapScalaString("b"),WrapScalaString("c"))
    val a3 = Atom(machine.R, WrapScalaString("a"),WrapScalaString("c"))

    machine.enableSolutionTrace()
    machine.enableStreamingTrace()

    machine.introduceAtom(a1)

    machine.introduceAtom(a2)
    machine.introduceAtom(a3)
    machine.executeRules()

    assert(machine.containsRelation(machine.R,  1))
    assert(machine.containsRelation(machine.S,  0))
  }

//  @Test (timeout=1000)
//  def testGuardSubs(){
//
//    logLevel = DEBUG
//    val a = Variable("a")
//    val b = Variable("b")
//    val c = Variable("c")
//    val d = Variable("d")
//
//    val machine = new Cham with DefaultFactory with DebugCham {
//      val s,x,y,z = Var
//      val R = LocalRelation("R")
//      val X1 = LocalRelation("X1")
//
//      rules(
//        R(s,x,y) --> Abs(X1(s)) ?: (R(s,x,y) &: R(s,x,y) &: X1(s)) // (1)
//      )
//    }
//
//    machine.enableSolutionTrace()
//
//    val atom = Atom("R", Variable("1"), a, b)
//    val atom2 = Atom("R", Variable("2"), a, b)
//    machine.introduceAtom(atom)
//    machine.introduceAtom(atom2)
//
//    assert(machine.containsRelation(machine.R, 2))
//    assert(machine.containsRelation(machine.X1, 0))
//
//    machine.executeRules() // the rule (1) should execute two times
//
//    assert(machine.containsRelation(machine.R, 4))
//    assert(machine.containsRelation(machine.X1, 2))
//  }

//  @Test (timeout=1000)
//  def testNu(){
//
//    logLevel = DEBUG
//
//    var result = false
//    val a = Variable("a")
//    val b = Variable("b")
//
//    val machine = new Cham with DefaultFactory {
//      val x,y,z,w = Var
//      val S = LocalRelation("S")
//      val R = NativeRelation("R"){
//        case (Atom("R", o::v2::v3::_),s) if (v2 != Undef && v3 != Undef) => result = true
//        case at => fail("Expected: R(x1,x2,x3). Actual: " + at)
//      }
//
//      rules{
//        S(x) --> Nu(y,z)(R(x,y,z))
//      }
//    }
//
//    machine.introduceAtom(Atom("S", a))
//    machine.executeRules()
//
//    assertTrue(result) // triggered in the "R" nativeRelation
//  }

//  @Test (timeout=1000)
//  def testHORelation(){
//
//    logLevel = DEBUG
//
//    val a = WrapScalaString("a")
//    val b = WrapScalaString("b")
//    var result = false
//
//    val machine = new Cham with DefaultFactory with DebugCham {
//      val x,y,z,w = VarScalaString()
//      val Cont = LocalRelation("Cont")
//
//      val R = LocalRelation("R"); val S = LocalRelation("S")
//      val Resp = NativeRelation("Resp"){
//        case (Atom(LocalRelation("Resp"), o::p::_),s) => result = true
//        case resp => fail("Expected atom: Resp(a,b). Actual: " + resp)
//      }
//      val RespVar = ChannelVariable(Resp)
//
//      rules(
//        S(x,y) --> R(x,y,RespVar),
//        R(x,y,Cont) --> Cont(x,y)
//      )
//    }
//
//    val atom1 = Atom("S", a, b)
//    machine.introduceAtom(atom1)
//    machine.executeRules()
//
//    //TODO rewrite test
//    assertTrue(result) // triggered in the "Resp" nativeRelation
//
//  }


  @Test //(timeout=1000)
  def simpleTest2(){
    val sm = new IntegerCham with DebugCham {
      val A = LocalRelation("A")
      val B = LocalRelation("B")
      val C = LocalRelation("C")
      val D = LocalRelation("D")
      val x = VarScalaInt()
      rules(
        (A() & B() & C()) --> D(),
        (D() & C()) --> A()
      )
    }

//    import sm.{num2fun}
    sm.enableSolutionTrace()
    sm.enableStreamingTrace()

    sm.introduceMolecule(sm.A())
    sm.introduceMolecule(sm.C())
    sm.introduceMolecule(sm.B())
    sm.introduceMolecule(sm.C())
    sm.introduceMolecule(sm.C())

    sm.printSolution()
    sm.executeRules()
    sm.printSolution()

    assertTrue(sm.containsRelation(sm.A,  1))
    assertTrue(sm.containsRelation(sm.C,  1))
    assertTrue(sm.containsRelation(sm.B,  0))
    assertTrue(sm.containsRelation(sm.D,  0))

  }
}