package fr.emn.criojo.examples

import fr.emn.criojo.parallel.Agent
import fr.emn.criojo.expression.scala.{WrapScalaInt, VarScalaInt}

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/26/12
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */
object BubbleAlgorithm {

  def main(args:Array[String]) {
    val fCham = new Agent() {
      val L = LocalRelation("L")
      val Print = LocalRelation("Print")
      val nPrint = NativeRel{
        case x::y::_ => println("L("+ x + "," + y + ")")
        case _ =>
      }

      val x, y, u, v = VarScalaInt()

      rules(
        (L(x, u) & L(y, v))  --> {(y <=>  (x + 1)) && (v < u)} ?: (L(x, v) & L(y, u)),
        (Print() & L(x,y)) --> (nPrint(x,y) & Print())
      )
    }
    val j = 5
    for (i <- 0 to j)
      fCham.introduceAtom(fCham.L(WrapScalaInt(i), WrapScalaInt(j - i)))
    fCham.executeRules()

    fCham.introduceAtom(fCham.Print())
    fCham.executeRules()

    System.exit(0)
  }

}
