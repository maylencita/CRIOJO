package fr.emn.criojo.examples

import fr.emn.criojo.parallel.Agent
import fr.emn.criojo.expression.Converters
import fr.emn.criojo.util.Printer

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/29/12
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
object HCalcul extends App with Converters with Printer{
  val calculAgent = new Agent(){
    val H = LocalRel
    val R = LocalRel

    val x,y,z = Var[Int]

    rules(
      (H(1, x) & H(2, y) & H(3, z) ) --> R(x - y / z ),
      R(x) --> Println("Result = $1",x)
    )
  }
  calculAgent.start()

  calculAgent ! calculAgent.H(1,7)
  calculAgent ! calculAgent.H(2, 2)
  calculAgent ! calculAgent.H(3, 10)

//  Thread.sleep(500)
//  System.exit(0)
}
