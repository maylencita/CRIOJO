package fr.emn.criojo.examples

import fr.emn.criojo.parallel.Agent
import fr.emn.criojo.expression.scala.{ScalaInt, ScalaTypesPredef, WrapScalaInt}

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/30/12
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
object Max extends App with ScalaTypesPredef{
  var result:Int = 0

  val fCham = new Agent{
    val V = LocalRel
    val Max = NativeRel {
      case WrapScalaInt(n)::_ => result = n
        println("Result: " + n)
        System.exit(0)
      case _ =>
    }

    val x, y = Var[ScalaInt]


    rules(
      (V(x) & V(y))  --> {x <= y} ?: (V(y) & Max(y))
    )
  }
  fCham.start()

  fCham ! List(fCham.V(2),fCham.V(2),fCham.V(3),fCham.V(4),fCham.V(4))

}
