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
import fr.emn.criojo.lang._
import fr.emn.criojo.ext.IntegerCham

class ChamTest {
  import Criojo._

  logLevel = INFO
  


  @Test
  def testRelations {

    val machine = new Cham { //TestCham with DefaultCham{
      val x,y,z = Var
      val R = Rel("R")
      val S = Rel("S")

      rules(
        (R(x,y) &: R(y,z)) --> R(x,z),
        S(x,y) --> R(x,y)
      )
    }

    assert(machine.getRelation("R") != null)
    assert(machine.getRelation("Z").name == "Undefined")
  }

  @Test
  def BonbonstestRelations {

    val machine = new Cham with IntegerCham { //TestCham with DefaultCham{
      val x,y,z = Var
      val OneBonbon = Rel("OneBonbon")
      val TwoBonbons = Rel("TwoBonbons")

      rules(
        (OneBonbon(x) &: OneBonbon(y)) --> TwoBonbons(x,y)
      )

      DEBUG_MODE = true
    }

    import machine.num2fun

    machine.introduceMolecule(machine.OneBonbon(1))
    assert(machine.getSolution.size==1)
    machine.executeRules()

    machine.introduceMolecule(machine.OneBonbon(2))
    machine.executeRules()
    assert(machine.getSolution.size==1)
    //println(machine.getSolution)
  }

  @Test
  def H4ORelations {

    val machine = new Cham with IntegerCham { //TestCham with DefaultCham{
      val a,b,x,y,z = Var
      val H = Rel("H")
      val O = Rel("O")
      val H4O = Rel("H4O")

      rules(
        (H(x) &: H(y) &: H(a) &: H(b) &: O(z)) --> H4O(x,y,a,b,z)
      )

      DEBUG_MODE = true
    }

    import machine.num2fun

    implicit def str2fun(n:String):Term = new ValueTerm[String](n) //new IntTerm(n)

    machine.introduceMolecule(machine.H("1"))
    machine.introduceMolecule(machine.H("2"))
    machine.introduceMolecule(machine.H("3"))
    machine.introduceMolecule(machine.H("4"))
    machine.introduceMolecule(machine.O("1"))
    machine.executeRules()

    assert(machine.getSolution.size==1)
    //println(machine.getSolution.size)
    //println(machine.getSolution)
  }

  @Test(timeout=1000)
  def testAtomInsertion{

    val machine = new Cham { //TestCham with DefaultCham{
      val x,y,z = Var
      val R = Rel("R")
      val S = Rel("S")

      rules(
        (R(x,y) &: R(y,z)) --> R(x,z),
        S(x,y) --> R(x,y)
      )

      DEBUG_MODE = true
    }

    val a1 = Atom("R", Variable("a"),Variable("b"))
    val a2 = Atom("S", Variable("b"),Variable("c"))
    val a3 = Atom("R", Variable("a"),Variable("c"))

    machine.introduceAtom(a1)
    assertEquals(StandAloneSolution(List(a1)), machine.getSolution)

    machine.introduceAtom(a2)
    machine.introduceAtom(a3)

    assertEquals(3, machine.getSolution.size)
    assertTrue(machine.getSolution.exists(a => a.relName == a3.relName && a.terms == a3.terms))
  }

  @Test (timeout=1000)
  def testGuardSubs{

    logLevel = DEBUG
    val a = Variable("a")
    val b = Variable("b")
    val c = Variable("c")
    val d = Variable("d")

    val m2 = new Cham{
      val s,x,y,z = Var
      val R = Rel("R")
      val X1 = Rel("X1")

      rules(
        R(s,x,y) --> Abs(X1(s)) ?: (R(s,x,y) &: R(s,x,y) &: X1(s))
      )

      DEBUG_MODE = true
    }
    info (this.getClass, "testGuard", "m2: " + m2.printRules)

    val atom = Atom("R", Variable("1"), a, b)
    val atom2 = Atom("R", Variable("2"), a, b)
    m2.introduceAtom(atom)
    m2.introduceAtom(atom2)

    //TODO rewrite test
//    assertTrue("Expected: <R(1,a,b),R(1,a,b),R(2,a,b),R(2,a,b)>. Actual: " + m2.solution,
//      m2.query(List(atom,atom,atom2,atom2), List()).size == 4)
  }

  @Test (timeout=1000)
  def testNu{

    logLevel = DEBUG

    var result = false
    val a = Variable("a")
    val b = Variable("b")

    val vm = new Cham{
      val x,y,z,w = Var
      val S = Rel("S")
      val R = NativeRelation("R"){
        case (Atom("R", a::v2::v3::_),s) if (v2 != Undef && v3 != Undef) => result = true
        case at => fail("Expected: R(x1,x2,x3). Actual: " + at)
      }

      rules{
        S(x) ==> Nu(y,z)(R(x,y,z))
      }

      DEBUG_MODE = true
    }
    info (this.getClass, "testNu", "vm: " + vm.printRules)

//    val atom = Atom("R", a, b)
    vm.introduceAtom(Atom("S", a))
    vm.executeRules()
//    vm.introduceAtom(atom)

    assertTrue("Expected: <R(x1,x2,x3)>. Actual: " + vm.getSolution, result)
  }

  @Test (timeout=1000)
  def testHORelation{

    logLevel = DEBUG

    val a = Variable("a")
    val b = Variable("b")
    var result = false

    val vm = new Cham {
      val x,y,z,w = Var
      val Cont = Rel("Cont")

      val R = Rel("R"); val S = Rel("S")
      val Resp = NativeRelation("Resp"){
        case (Atom("Resp", a::b::_),s) => result = true
        case resp => fail("Expected atom: Resp(a,b). Actual: " + resp)
      }
      val RespVar = RelVariable(Resp)

      rules(
        S(x,y) ==> R(x,y,RespVar),
        R(x,y,Cont) ==> Cont(x,y)
      )

      DEBUG_MODE = true
    }

    val atom1 = Atom("S", a, b)
    vm.introduceAtom(atom1)
    vm.executeRules()

    //TODO rewrite test
    //assertTrue("Expected: <Resp(a,b)>. Actual: " + vm.getSolution, result)

  }


  @Test //(timeout=1000)
  def simpleTest2(){
    val sm = new Cham with IntegerCham {
      val A = Rel("A")
      val B = Rel("B")
      val C = Rel("C")
      val D = Rel("D")

      rules(
        (A() & B() & C()) --> D(),
        (D() & C()) --> A()
      )

      DEBUG_MODE = true
    }

    import sm.{num2fun}

    sm.introduceMolecule(sm.A())
    sm.introduceMolecule(sm.C())
    sm.introduceMolecule(sm.B())
    sm.introduceMolecule(sm.C(1))
    sm.introduceMolecule(sm.C(2))
    sm.executeRules()

    println(sm.getSolution)
    
    assertTrue(sm.getSolution.size==1)
  }
}