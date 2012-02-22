package fr.emn.criojo.core

import org.junit.Test
import fr.emn.criojo.lang._
import fr.emn.criojo.ext._
import collection.mutable.Buffer
import java.io.FileWriter
import fr.emn.criojo.core.Variable._
import fr.emn.criojo.core.{ValueTerm, Term, Atom, Variable}

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/11/11
 * Time: 14:41
 */
class StatesTest {

  // Some useful objects
  //-------------------------------------------------------
  trait PrintableCham extends Cham{
    val Print = NativeRelation("Print") {
      case ((Atom(_,terms), _)) => print(terms.mkString(","))
      case _ =>
    }
  }

  implicit def num2fun(n:Int):Term = new ValueTerm[Int](n) //new IntTerm(n)

  @Test(timeout = 1000)
  def simpleTest1() {
    val sm = new Cham {
      val A = Rel("A")
      val B = Rel("B")
      val C = Rel("C")
      val D = Rel("D")
      val K = Rel("K")
      val x, y, z = Var


      rules(
        (A(x, y) & B(y, z)) --> Abs(D(y)) ?: (D(y) & A(x, y) & B(y, z))
      )
    }

    val a = Variable("a");
    val b = Variable("b");
    val c = Variable("c")
    sm.introduceMolecule(sm.A(a, b))
    sm.introduceMolecule(sm.A(b, c))
    sm.introduceMolecule(sm.B(c, a))
    sm.executeRules()

    println("[simpleTest1] Solution: " + sm.getSolution)
    assert(sm.getSolution.size == 4)
  }

  @Test (timeout=1000)
  def oneHeadTest{
    val cham = new Cham with PrintableCham{
      val R = Rel("R")
      val x,y = Var

      rules(
        R(x,y) --> Print(x,y)
      )
    }
    import cham.R
    cham.introduceMolecule(R(1,2))
    cham.executeRules()
  }

  @Test (timeout=1000)
  def repeatedHeadTest{
    val sm = new Cham{
      val H = Rel("H")
      val H3 = Rel("H3")
      val O = Rel("O")
      val x,y,z = Var

      val Print = NativeRelation("Print2") {
        case ((Atom(_, x :: y :: z :: _), _)) => print("H3(" + x + "," + y + "," + z + ")")
        case _ =>
      }

      rules(
        (H(x) & H(y) & H(z) & O()) --> H3(x,y,z),
        H3(x,y,z) --> Print(x,y,z)
      )
    }

    import sm.H
    sm.introduceMolecule(H(1))
    sm.introduceMolecule(H(2))
    sm.introduceMolecule(H(3))
    sm.executeRules()
//    println(sm.printRules)

  }

  //def RelVariable(rel: this.type#ApplicableRel): Term

  @Test //(timeout=1000)
  def intTest() {
    val cm = new Cham with IntegerCham {
      val Result = Rel("Result")
      val Zero = Rel("Zero")
      val Print2 = NativeRelation("Print2") {
        case ((Atom(_, x :: _), _)) => {
          var t = x;
          //print(" "+x)
        }
        case _ =>
      }

      val s, x, y, z = Var

      rules(
        Result(s, x, y, z) --> Print2(z)
      )
    }
    import cm.num2fun
    val s = Variable("s")
    cm.introduceMolecule(cm.Mod(s, 4, 3, RelVariable(cm.Result), RelVariable(cm.Zero)))
    cm.executeRules()

    println("[intTest] Solution: " + cm.getSolution)
    assert(cm.getSolution.size == 3)
  }

  @Test(timeout = 2000)
  def gcd() {
    val cm = new Cham with IntegerCham {
      val Gcd = Rel("Gcd")
      val mod = Rel("mod")
      val zero = Rel("zero")
      val n, m, l, x, y, z, s = Var
      val sp = Fun("sp")

      val Print = Rel("Print")
      val Print2 = NativeRelation("Print2") {
        case ((Atom(_, x :: _), _)) => /*print(" "+x)*/
        case _ =>
      }

      rules(
        Gcd(x, y) --> Nu(s)(Mod(s, x, y, mod, zero)),
        mod(s, x, y, z) --> (Gcd(y, z) & Print2(y) & Print2(z) & Print2("")),
        zero(s, x) --> Print2(x)
      )

      implicit def str2fun(str: String): Term = new ValueTerm[String](str)
    }

    import cm.num2fun
    cm.introduceMolecule(cm.Gcd(2, 3))
    cm.executeRules()

    println("[gcd] Solution: " + cm.getSolution)
    assert(cm.getSolution.size == 8)
  }
}