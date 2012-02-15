package fr.emn.criojo.core

import org.junit.Test
import fr.emn.criojo.lang._
import fr.emn.criojo.ext._
import collection.mutable.Buffer
import java.io.FileWriter

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/11/11
 * Time: 14:41
 */
class SierpinskiTests {

  @Test
  def SierpinskiTest() {

    val cm = new Cham with IntegerCham {

      //implicit def int2fun(integer:Int):Term = new ValueTerm[Int](integer)

      val DIVIDE = Rel("DIVIDE")
      val RESULT = Rel("RESULT")
      val Sierpinski = Rel("Sierpinski")
      val x, y, z, a, b, c, lp, xp1, xp2, yp, n, np, l = Var
      var one, two, zero: Term = _
      one = 1
      two = 2
      zero = 0

      val Print = NativeRelation("Print") {
        case ((Atom(_, (x: Value[Int]) :: (y: Value[Int]) :: (l: Value[Int]) :: _), _)) => {


        }
        case _ => println("cannot match correct values")
      }

      val Div: UnstableRelation = ComputableRelation("Div")((exp: Tuple2[Product, Product], subs: Buffer[(Variable, Term)]) => {
        exp match {

          case (List((o, v1: Value[Int]), (p, v2: Value[Int])), List((q, v3: MutableValueTerm[Int]))) => {
            v3.setValue((v1.getValue() / v2.getValue())) // "q = o/p"
          }
          case _ => throw new Exception("Bad arguments: need (Term*) -> (Term*) when evaluating \"Div\"")
        }
      })

      val Minus: UnstableRelation = ComputableRelation("Minus")((exp: Tuple2[Product, Product], subs: Buffer[(Variable, Term)]) => {
        exp match {

          case (List((o, v1: Value[Int]), (p, v2: Value[Int])), List((q, v3: MutableValueTerm[Int]))) => {
            v3.setValue((v1.getValue() - v2.getValue())) // "q = o-p"
          }
          case _ => throw new Exception("Bad arguments: need (Term*) -> (Term*) when evaluating \"Minus\"")
        }
      })

      val Plus: UnstableRelation = ComputableRelation("Plus")((exp: Tuple2[Product, Product], subs: Buffer[(Variable, Term)]) => {
        exp match {

          case (List((o, v1: Value[Int]), (p, v2: Value[Int])), List((q, v3: MutableValueTerm[Int]))) => {
            v3.setValue((v1.getValue() + v2.getValue())) // "q = o+p"
          }
          case _ => throw new Exception("Bad arguments: need (Term*) -> (Term*) when evaluating \"Plus\"")
        }
      })

      rules(
        Sierpinski(x, y, l, n) --> Equal(n, zero) ?: Print(x, y, l),

        (Sierpinski(x, y, l, n)
          & Div((l, two) -> (lp))
          & Minus((x, lp) -> (xp1))
          & Minus((y, lp) -> (yp))
          & Plus((x, lp) -> (xp2))
          & Minus((n, one) -> (np))
          )
          --> NotEqual(n, zero) ?:
          (Sierpinski(x, y, lp, np)
            & Sierpinski(xp1, yp, lp, np)
            & Sierpinski(xp2, yp, lp, np)
            )

        //,(DIVIDE(x,y) & Div((x,y) -> (z)) & Div((x,one) ->(lp)) ) --> RESULT(x,y,z,lp) // simple case
        //,(DIVIDE(x,y) & Div((x,y) -> (z)) & Div((x,z) -> (c)) ) --> RESULT(x,y,z,c) // more complex case
        //,(DIVIDE(x,y) & Div((x,y) -> (z)) & Div((x,z) -> (c)) & Div((k,k) -> (k)) ) --> RESULT(x,y,z,c) // there is a cycle

      )

      implicit def str2fun(str: String): Term = new ValueTerm[String](str)

    }

    import cm.num2fun

    //cm.introduceMolecule(cm.DIVIDE(6,3))
    cm.introduceMolecule(cm.Sierpinski(700, 700, 700, 7))
    cm.executeRules()

    //println(cm.getSolution)
    //println(cm.getSolution.size)
    assert(cm.getSolution.size==2187)
  }

  @Test
  def PaintSierpinskiTest() {

    var fw = new FileWriter("/Users/jonathan/Desktop/test.svg");
    fw.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n")

    val cm = new Cham with IntegerCham {

      //implicit def int2fun(integer:Int):Term = new ValueTerm[Int](integer)

      val DIVIDE = Rel("DIVIDE")
      val RESULT = Rel("RESULT")
      val Sierpinski = Rel("Sierpinski")
      val x, y, z, a, b, c, lp, xp1, xp2, yp, n, np, l = Var
      var one, two, zero: Term = _
      one = 1
      two = 2
      zero = 0

      val Print = NativeRelation("Print") {
        case ((Atom(_, (x: Value[Int]) :: (y: Value[Int]) :: (l: Value[Int]) :: _), _)) => {

          fw.write("<polygon points=\"" + x.getValue() + "," + y.getValue() + " " + (x.getValue() - l.getValue()) + "," + (y.getValue() - l.getValue()) + " " + (x.getValue() + l.getValue()) + "," + (y.getValue() - l.getValue()) + "\" style=\"fill:lime;stroke:purple;stroke-width:2\"/>\n")
        }
        case _ => println("cannot match correct values")
      }

      val Div: UnstableRelation = ComputableRelation("Div")((exp: Tuple2[Product, Product], subs: Buffer[(Variable, Term)]) => {
        exp match {

          case (List((o, v1: Value[Int]), (p, v2: Value[Int])), List((q, v3: MutableValueTerm[Int]))) => {
            v3.setValue((v1.getValue() / v2.getValue())) // "q = o/p"
          }
          case _ => throw new Exception("Bad arguments: need (Term*) -> (Term*) when evaluating \"Div\"")
        }
      })

      val Minus: UnstableRelation = ComputableRelation("Minus")((exp: Tuple2[Product, Product], subs: Buffer[(Variable, Term)]) => {
        exp match {

          case (List((o, v1: Value[Int]), (p, v2: Value[Int])), List((q, v3: MutableValueTerm[Int]))) => {
            v3.setValue((v1.getValue() - v2.getValue())) // "q = o-p"
          }
          case _ => throw new Exception("Bad arguments: need (Term*) -> (Term*) when evaluating \"Minus\"")
        }
      })

      val Plus: UnstableRelation = ComputableRelation("Plus")((exp: Tuple2[Product, Product], subs: Buffer[(Variable, Term)]) => {
        exp match {

          case (List((o, v1: Value[Int]), (p, v2: Value[Int])), List((q, v3: MutableValueTerm[Int]))) => {
            v3.setValue((v1.getValue() + v2.getValue())) // "q = o+p"
          }
          case _ => throw new Exception("Bad arguments: need (Term*) -> (Term*) when evaluating \"Plus\"")
        }
      })

      rules(
        Sierpinski(x, y, l, n) --> Equal(n, zero) ?: Print(x, y, l),

        (Sierpinski(x, y, l, n)
          & Div((l, two) -> (lp))
          & Minus((x, lp) -> (xp1))
          & Minus((y, lp) -> (yp))
          & Plus((x, lp) -> (xp2))
          & Minus((n, one) -> (np))
          )
          --> NotEqual(n, zero) ?:
          (Sierpinski(x, y, lp, np)
            & Sierpinski(xp1, yp, lp, np)
            & Sierpinski(xp2, yp, lp, np)
            )

        //,(DIVIDE(x,y) & Div((x,y) -> (z)) & Div((x,one) ->(lp)) ) --> RESULT(x,y,z,lp) // simple case
        //,(DIVIDE(x,y) & Div((x,y) -> (z)) & Div((x,z) -> (c)) ) --> RESULT(x,y,z,c) // more complex case
        //,(DIVIDE(x,y) & Div((x,y) -> (z)) & Div((x,z) -> (c)) & Div((k,k) -> (k)) ) --> RESULT(x,y,z,c) // there is a cycle

      )

      implicit def str2fun(str: String): Term = new ValueTerm[String](str)

    }

    import cm.num2fun

    //cm.introduceMolecule(cm.DIVIDE(6,3))
    cm.introduceMolecule(cm.Sierpinski(700, 700, 700, 7))
    cm.executeRules()

    println(cm.getSolution)
    println(cm.getSolution.size)

    fw.write("</svg>\n")
    fw.close()
  }
}