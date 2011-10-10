package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/01/11
 * Time: 11:53
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._
import ExtTests._
import fr.emn.criojo.lang.Cham

class IntTests{

  @Test (timeout=1000)
  def testDeclare{
//    logLevel = DEBUG
    val machine = new Cham with IntCHAM{ //DefaultCham with IntCHAM{
      val s,x,y,n = Var
      val S = Rel("S"); val Siete = Rel("Siete")

      rules(
        S(n,s,x) ==> Int_ask(n,s,x,Siete)
      )
    }

    machine.introduceAtom(Atom("$Int",Variable("7"),a))
    machine.introduceAtom(Atom("$Int",Variable("6"),b))

    machine.intEqClasses.get(7) match{
      case Some(ec) if (ec.contains(a)) => assertTrue(true)//Ok
      case _ => fail("Missing value 7->a. Actual eqClass: " +  machine.intEqClasses)
    }

    machine.intEqClasses.get(6) match{
      case Some(ec) if (ec.contains(b)) => assertTrue(true)//Ok
      case _ => fail("Missing value 6->b. Actual eqClass: " +  machine.intEqClasses)
    }

    machine.introduceAtom(Atom("S", 7, c, a))
    machine.introduceAtom(Atom("S", 7, c, b))

    val a7 = Atom("Siete", a)
    val a6 = Atom("Seis", b)

//    log("Relations: " + machine.relations)
    log("intEqClasses:" + machine.intEqClasses)
    log("EqClasses:" + machine.eqClasses)
    info(this.getClass, "testInt", "Solution:" + machine.solution)

    val s = Variable("s"); val x = Variable("x")
    assertFalse("Missing element: " + a7 + ". Actual solution: " + machine.solution,
      machine.querySansEffect(List(Atom("Siete",c,a))).isEmpty
    )
    assertTrue("Wrong solution with: " + a6 + ". Actual solution: " + machine.solution,
      machine.querySansEffect(List(a6)).isEmpty
    )
  }

  @Test (timeout=1000)
  def testEqInt{
//    logLevel = DEBUG
    var iguales = false
    var noIguales = false

    val machine = new Cham with IntCHAM{
      val s,x,y = Var
      val Test = Rel("Test");
      val Iguales = NativeRelation("Iguales"){
        case (Atom(_, a::b::_),s) => iguales = true
        case atom => fail("Expected atom: Iguales(a,b). Actual: " + atom)
      }
      val NoIguales = NativeRelation("NoIguales"){
        case (Atom(_, a::b::_),s) => noIguales = true
        case atom => fail("Expected atom: NoIguales(a,b). Actual: " + atom)
      }

      rules(
        Test(s,x,y) ==> EQ_ask(s,x,y,Iguales,NoIguales)
      )
    }

    machine.introduceAtom(Atom("$Int",Variable("8"),a))
    machine.introduceAtom(Atom("$Int",Variable("8"),b))
    machine.introduceAtom(Atom("$Int",Variable("9"),c))
    machine.introduceAtom(Atom("Test",Variable("1"),a,b))
    machine.introduceAtom(Atom("Test",Variable("2"),c,b))

//    println("EqClasses: " + machine.eqClasses)
//    println("DisjClasses: " + machine.disjClasses)
//    println("IntEqClasses: " + machine.intEqClasses)

    assertTrue("Missing element: Iguales(a,b). Actual solution: " + machine.solution + "\n" +
      "intEqClasses:" + machine.intEqClasses + "\n" +
      "EqClasses:" + machine.eqClasses, iguales)

    assertTrue("Missing element: NoIguales(b,c). Actual solution: " + machine.solution + "\n" +
      "intEqClasses:" + machine.intEqClasses + "\n" +
      "DisjClasses:" + machine.disjClasses, noIguales)

    machine.introduceAtom(Atom("Eq", c, d))

    machine.intEqClasses.get(9) match {
      case Some(ec) if(ec.contains(c) && ec.contains(d)) => assertTrue(true)
      case _ => fail("Expected intEqClasses: Map(9->{c,d}). Actual: " + machine.intEqClasses)
    }
  }

  @Test (timeout=1000,expected=classOf[InvalidStateError])
  def testEqError{
    val machine = new Cham with IntCHAM //DefaultCham with IntCHAM
    machine.introduceAtom(Atom("$Int",Variable("8"),a))
    machine.introduceAtom(Atom("$Int",Variable("9"),b))
    machine.introduceAtom(Atom("Eq",a,b))
  }

  @Test (timeout=1000)
  def testSuc{
//    logLevel = DEBUG
    val n = Variable("n")
    val x = Variable("x")

    val machine = new Cham with IntCHAM //DefaultCham with IntCHAM
//    println("Rules: " + machine.rules.mkString("","\n",""))

    machine.introduceAtom(Atom("$Suc",a,b))
    machine.introduceAtom(Atom("$Int",Value[Int](7),a))

    assertTrue("Element $Int(8,b) not found in solution " + machine.solution,
      machine.query(List(machine.IntRel(n,x)),List((n,8),(x,b))).size > 0 )
    println("Final Solution: " + machine.solution)
  }

  @Test (timeout=1000)
  def testSum{
//    logLevel = DEBUG

    val n = Variable("n")
    val x = Variable("x")

    val machine = new Cham with IntCHAM //DefaultCham with IntCHAM
    println("Rules: " + machine.printRules)

    machine.introduceAtom(Atom("$Sum",a,b,c))
    machine.introduceAtom(Atom("$Int",Value[Int](7),a))
    machine.introduceAtom(Atom("$Int",Value[Int](5),b))

    println("Query result: " + machine.querySansEffect(List(machine.IntRel(12,c))))
    assertTrue("Element $Int(12,c) not found in solution " + machine.solution,
      machine.querySansEffect(List(machine.IntRel(12,c))).size > 0 )

    println("La solution hasta aca: " + machine.solution)

    machine.introduceAtom(Atom("$Sum",c,b,d))
    assertTrue("Element $Int(17,d) not found in solution " + machine.solution,
      machine.querySansEffect(List(machine.IntRel(17,d))).size > 0 )

    println("Final Solution: " + machine.solution)
  }
}