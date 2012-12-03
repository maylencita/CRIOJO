package fr.emn.criojo.examples

import fr.emn.criojo.parallel.Agent
import fr.emn.criojo.expression.Converters

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/29/12
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
object HCalcul extends App with Converters{
  val calculAgent = new Agent(){
    val H = LocalRel
    val R = LocalRel

    val N = NativeRel {l => println("Result: "+l)}
    val x,y,z = Var[Int]

    rules(
      (H(1, x) & H(2, y) & H(3, z) ) --> R(x - y / z ),
      R(x) --> N(x)
    )
  }
  calculAgent.start()

  calculAgent ! calculAgent.H(1,7)
  calculAgent ! calculAgent.H(2, 2)
  calculAgent ! calculAgent.H(3, 10)
}
