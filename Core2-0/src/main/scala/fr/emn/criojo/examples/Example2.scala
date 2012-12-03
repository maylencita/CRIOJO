package fr.emn.criojo.examples

import fr.emn.criojo.parallel.Agent
import fr.emn.criojo.expression.scala.{VarScalaInt, WrapScalaInt}

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/23/12
 * Time: 6:20 PM
 * To change this template use File | Settings | File Templates.
 */
object Example2{
  def main(args:Array[String]){
    val parCham = new Agent {
      val Print = NativeRel {
        case v1::_ => println("Result: " + v1)
        case _ =>
      }
      val gcd = LocalRelation("gcd")
      val Result = LocalRelation("Resultat")

      val n,n1,n2,r,r1,r2,v,x,y,xNew = VarScalaInt()

      rules(
        gcd(x,y) --> {x < y} ?: gcd(y,x),
        gcd(x,y) --> {x > y} ?: gcd(x -y,y),
        gcd(x,y) --> {x <=> y} ?: Print(y)
      )
    }
    parCham.start()
    parCham ! parCham.gcd(WrapScalaInt(8),WrapScalaInt(2))

//    parCham.introduceAtom(parCham.gcd(WrapScalaInt(8),WrapScalaInt(2)))
//    parCham.executeRules()

    //Stop the cham execution
//    System.exit(0)
  }
}
