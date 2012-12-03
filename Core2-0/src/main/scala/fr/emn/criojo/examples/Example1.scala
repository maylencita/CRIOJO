package fr.emn.criojo.examples

import fr.emn.criojo.parallel._
import fr.emn.criojo.expression.scala.WrapScalaInt


/**
* Created with IntelliJ IDEA.
* User: mayleen
* Date: 11/23/12
* Time: 6:19 PM
* To change this template use File | Settings | File Templates.
*/
object Example1{
    def main(args:Array[String]){
      val parCham = new Agent{
        val gcd = LocalRelation("gcd")
        val Result = LocalRelation("Resultat")

        val n,n1,n2,r,r1,r2,v,x,y,xNew = Var[Int]


        rules(
          gcd(x,y) --> {x < y} ?: gcd(y,x),
          gcd(x,y) --> {x > y} ?: gcd(x -y,y),
          gcd(x,y) --> {x <=> y} ?: Result(y)
        )

      }
      parCham.introduceAtom(parCham.gcd(WrapScalaInt(8),WrapScalaInt(2)))
      parCham.executeRules()
    }
  }
