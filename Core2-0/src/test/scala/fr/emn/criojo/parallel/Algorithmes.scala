package fr.emn.criojo.parallel

import org.junit.Test
import org.junit.Assert._
import fr.emn.criojo.expression.Converters
import fr.emn.criojo.expression.scala.WrapScalaInt


/*
* Created by IntelliJ IDEA.
* User: hgrall
* Date: 4 mai 2012
*/


class Algorithmes extends Converters{

  @Test
  def hectorTest() {
    var result = false

    val fCham = new Agent {
      val H = LocalRel
      val R = NativeRel{
        case WrapScalaInt(n)::_ => if (n == 7-2/10){result = true}
        case _ => println("Wrong result!")
      }

      val x, y, z = Var[Int]//VarScalaInt()

      rules(
        (H(1, x) & H(2, y) & H(3, z) )  --> R(x - y / z )
      )
    }
    fCham.start()
    fCham ! fCham.H(1,7)
    fCham ! fCham.H(2, 2)
    fCham ! fCham.H(3, 10)

    Thread.sleep(500)
    assertTrue(result)
  }


  @Test
  def maxTest() {
    var result:Int = 0

    val fCham = new Agent{
      val V = LocalRel
      val Max = NativeRel {
        case WrapScalaInt(n)::_ => result = n
        case _ =>
      }

      val x, y = Var[Int]


      rules(
        (V(x) & V(y))  --> {x <= y} ?: (V(y) & Max(y))
      )
    }
    fCham.start()

    fCham ! List(fCham.V(2),fCham.V(2),fCham.V(3),fCham.V(4),fCham.V(4))

    Thread.sleep(1000)
    assertEquals(4, result)
  }

  @Test
  def bubbleTest() {
    var atomList = List[Pair[Any,Any]]()

    val fCham = new Agent{
      val L = LocalRel
      val Export = LocalRel

      val ExportList = NativeRel {
        case v1::v2::_ => atomList :+= (v1,v2)
        case _ =>
      }

      val x, y, u, v = Var[Int]


      rules(
        (L(x, u) & L(y, v))  --> {(y <=>  (x + 1)) && (v < u)} ?: (L(x, v) & L(y, u)),
        (Export() & L(x,y)) --> ExportList(x,y)
      )
    }
    fCham.start()

    val j = 5
    for (i <- 0 to j)
      fCham ! fCham.L(i, j - i)

    Thread.sleep(500)
    assertTrue(atomList.forall {p => p._1 == p._2})
  }

}

