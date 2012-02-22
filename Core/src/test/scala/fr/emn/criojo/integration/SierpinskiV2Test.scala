package fr.emn.criojo.integration

import org.junit.Test
import fr.emn.criojo.ext._
import collection.mutable.Buffer
import java.io.FileWriter
import fr.emn.criojo.core._
import fr.emn.criojo.ext.AllAreTrueGuard
import fr.emn.criojo.lang.{CrjAtom, &:, Cham, Nu}

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/11/11
 * Time: 14:41
 */
class SierpinskiV2Test {


  @Test
  def SierpinskiTest() {

    val cm = new UnstableCham with IntegerCham {

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
        Sierpinski(x, y, l, n) --> Equal(n, 0) ?: Print(x, y, l),

        (Sierpinski(x, y, l, n)
          & Div((l, two) -> (lp))
          & Minus((x, lp) -> (xp1))
          & Minus((y, lp) -> (yp))
          & Plus((x, lp) -> (xp2))
          & Minus((n, one) -> (np))
          )
          --> NotEqual(n, 0) ?:
          (Sierpinski(x, y, lp, np)
            & Sierpinski(xp1, yp, lp, np)
            & Sierpinski(xp2, yp, lp, np)
            )

      )

      implicit def str2fun(str: String): Term = new ValueTerm[String](str)

    }

    import cm.num2fun

    cm.introduceMolecule(cm.Sierpinski(700, 700, 700, 7))
    cm.executeRules()

    assert(cm.getSolution.size == 2187)
  }

  @Test
  def PaintSierpinskiTest() {

    var fw = new FileWriter("/Users/jonathan/Desktop/test.svg");
    fw.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n")

    val cm = new Cham with IntegerCham {

      val Sierpinski = Rel("Sierpinski")
      val x, y, z, a, b, c, lp, xp1, xp2, yp, n, np, l, vx, vy, vl = Var

      rules(
        (Sierpinski(x, y, l, n) & IntVal(x,vx)) --> Leq(n, 0) ?: (Sierpinski(vx, y, l, n) & IntVal(x,vx)),
        (Sierpinski(x, y, l, n) & IntVal(y,vy)) --> Leq(n, 0) ?: (Sierpinski(x, vy, l, n) & IntVal(y,vy)),
        (Sierpinski(x, y, l, n) & IntVal(l,vl)) --> Leq(n, 0) ?: (Sierpinski(x, y, vl, n) & IntVal(l,vl)),
        (Sierpinski(x, y, l, n) & IntVal(n,np)) --> Leq(n, 0) ?: (Sierpinski(x, y, l, np) & IntVal(n,np)),

        Sierpinski(x, y, l, n)
          --> Gr(n, 0) ?:

            Nu(xp1,xp2,yp,lp,np)(
              IntDiv(l,2,lp) &
              IntAdd(x,l,xp2) &
              IntSub(x,l,xp1) &
              IntSub(y,l,yp) &
              IntSub(n,1,np) &

              Sierpinski(x, y, lp, np) &
              Sierpinski(xp1, yp, lp, np) &
              Sierpinski(xp2, yp, lp, np)
            )
      )

    }

    val cmDraw = new Cham with IntegerCham {

      //implicit def int2fun(integer:Int):Term = new ValueTerm[Int](integer)
      val x, y, l, n = Var
      val Sierpinski = Rel("Sierpinski")

      val Print3 = NativeRelation("Print3") {
        case ((Atom(_, (x: Value[Int]) :: (y: Value[Int]) :: (l: Value[Int]) :: _), _)) => {

          fw.write("<polygon points=\"" + x.getValue() + "," + y.getValue() + " " + (x.getValue() - l.getValue()) + "," + (y.getValue() - l.getValue()) + " " + (x.getValue() + l.getValue()) + "," + (y.getValue() - l.getValue()) + "\" style=\"fill:lime;stroke:purple;stroke-width:2\"/>\n")
        }
        case l => {
          val truc = l
          println("cannot match correct values")
        }
      }

      rules(
        Sierpinski(x, y, l, n) --> Print3(x, y, l)
      )

    }

    import cm.num2fun

    cm.introduceMolecule(cm.Sierpinski(700, 700, 700, 3))
    cm.executeRules()

    //println(cm.getSolution.size)
    //println(cm.getSolution)

    //cmDraw.introduceMolecule(cmDraw.Sierpinski(300, 300, 100, 3))
    //val tr = cmDraw.getSolution


    cm.getSolution.foreach(a => {
      
      if(a.relation.name==cm.Sierpinski.name) {
        cmDraw.introduceMolecule(new CrjAtom(cmDraw.Sierpinski.name, a.terms))
      }
    })

    cmDraw.executeRules()

    //println(cmDraw.getSolution.size)
    //println(cmDraw.getSolution)

    fw.write("</svg>\n")
    fw.close()
  }
}
