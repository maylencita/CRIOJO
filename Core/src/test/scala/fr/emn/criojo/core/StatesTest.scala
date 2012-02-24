package fr.emn.criojo.core

import org.junit._
import Assert._
import fr.emn.criojo.lang._
import fr.emn.criojo.ext._
import collection.mutable.Buffer
import java.io.FileWriter
import fr.emn.criojo.core.Variable._
import fr.emn.criojo.core.{Term, Atom, Variable}

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/11/11
 * Time: 14:41
 */
class StatesTest {

  // Some useful objects
  //-------------------------------------------------------
  trait TestCham extends Cham{
    var passed = 0
    val Print = NativeRelation("Print") {
      case ((Atom(_,terms), _)) => print(terms.mkString(","))
      case _ =>
    }
    val Passed = NativeRelation("Passed"){(s,a) =>
      passed += 1
    }
  }

  implicit def num2term(n:Int):Term = new ValueTerm[Int](n)
  implicit def str2term(str:String):Term = new ValueTerm[String](str)

  @Test(timeout = 1000)
  def terminationTest() {
    val sm = new Cham with TestCham{
      val A = Rel("A")
      val B = Rel("B")
      val C = Rel("C")
      val D = Rel("D")
      val K = Rel("K")
      val x, y, z = Var

      rules(
        (A(x, y) & B(y, z)) --> Abs(D(y)) ?: (D(y) & A(x, y) & B(y, z) & Print(x,y,z) & Passed())
      )
    }

    val a = Variable("a");
    val b = Variable("b");
    val c = Variable("c")
    sm.introduceMolecule(sm.A(a, b))
    sm.introduceMolecule(sm.A(b, c))
    sm.introduceMolecule(sm.B(c, a))
    sm.executeRules()

    assertEquals(1,sm.passed)
  }

  @Test (timeout=1000)
  def persistenceTest{
    val cham = new Cham with TestCham{
      val A = Rel("A")
      val B = Rel("B")
      val C = Rel("C")
      val D = Rel("D")

      val x,y,z = Var
      rules(
        (!A(x,y) & B(y,z)) --> (Print(x,z,"\n") & Passed()),
        (A(x,y) & D(y,z)) --> (Print(x,z,"\n") & Passed())
      )
    }

    import cham.{A,B,D}

    cham.introduceMolecule(A(1,2) & D(2,3) & B(2,4))
    cham.executeRules()

    assertEquals(2,cham.passed)
  }

  @Test (timeout=1000)
  def oneHeadTest{
    val cham = new Cham with TestCham{
      val R = Rel("R")
      val x,y = Var

      rules(
        R(x,y) --> (Print(x,y) & Passed())
      )
    }
    import cham.R
    cham.introduceMolecule(R(1,2))
    cham.executeRules()

    assertEquals(1,cham.passed)
  }

  @Test (timeout=1000)
  def repeatedHeadTest{
    val sm = new Cham with TestCham{
      val H = Rel("H")
      val H3 = Rel("H3")
      val x,y,z = Var

      rules(
        (H(x) & H(y) & H(z)) --> H3(x,y,z),
        H3(x,y,z) --> (Print(x,y,z) & Passed())
      )
    }

    import sm.H
    sm.introduceMolecule(H(1))
    sm.introduceMolecule(H(2))
    sm.introduceMolecule(H(3))
    sm.executeRules()
//    println(sm.printRules)

    assertEquals(1,sm.passed)
  }

  //def RelVariable(rel: this.type#ApplicableRel): Term

  //TODO These are more complex tests. Should go into integration package
//  @Test //(timeout=1000)
//  def intTest() {
//    val cm = new Cham with IntegerCham {
//      val Result = Rel("Result")
//      val Zero = Rel("Zero")
//      val Print2 = NativeRelation("Print2") {
//        case ((Atom(_, x :: _), _)) => {
//          var t = x;
//          //print(" "+x)
//        }
//        case _ =>
//      }
//
//      val s, x, y, z = Var
//
//      rules(
//        Result(s, x, y, z) --> Print2(z)
//      )
//    }
//    import cm.num2fun
//    val s = Variable("s")
//    cm.introduceMolecule(cm.Mod(s, 4, 3, RelVariable(cm.Result), RelVariable(cm.Zero)))
//    cm.executeRules()
//
//    println("[intTest] Solution: " + cm.getSolution)
//    assert(cm.getSolution.size == 3)
//  }

//  @Test(timeout = 2000)
//  def gcd() {
//    val cm = new Cham with IntegerCham {
//      val Gcd = Rel("Gcd")
//      val mod = Rel("mod")
//      val zero = Rel("zero")
//      val n, m, l, x, y, z, s = Var
//      val sp = Fun("sp")
//
//      val Print = Rel("Print")
//      val Print2 = NativeRelation("Print2") {
//        case ((Atom(_, x :: _), _)) => /*print(" "+x)*/
//        case _ =>
//      }
//
//      rules(
//        Gcd(x, y) --> Nu(s)(Mod(s, x, y, mod, zero)),
//        mod(s, x, y, z) --> (Gcd(y, z) & Print2(y) & Print2(z) & Print2("")),
//        zero(s, x) --> Print2(x)
//      )
//
//      implicit def str2fun(str: String): Term = new ValueTerm[String](str)
//    }
//
//    import cm.num2fun
//    cm.introduceMolecule(cm.Gcd(2, 3))
//    cm.executeRules()
//
//    println("[gcd] Solution: " + cm.getSolution)
//    assert(cm.getSolution.size == 8)
//  }
}