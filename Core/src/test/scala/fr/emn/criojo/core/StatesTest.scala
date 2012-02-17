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