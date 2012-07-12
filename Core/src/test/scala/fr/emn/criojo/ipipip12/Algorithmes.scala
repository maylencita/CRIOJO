package fr.emn.criojo.ipipip12

import org.junit.Test
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.core.Converters._
import fr.emn.criojo.ext.debug.DebugCham
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.ext.expression.ScalaInt.VarScalaInt
import fr.emn.criojo.TestCham

/*
* Created by IntelliJ IDEA.
* User: hgrall
* Date: 4 mai 2012
*/


class Algorithmes {

  @Test
  def hectorTest() {
    val fCham = new Cham with TestCham with DebugCham {
      val H = LocalRelation("H")
      val R = LocalRelation("R")

      val x, y, z = VarScalaInt()

      rules(
        (H(1, x) & H(2, y) & H(3, z) )  --> R(x - y / z )
      )
    }
    fCham.enableStreamingTrace()
    fCham.enableSolutionTrace()
    fCham.introduceMolecule(fCham.H(1, 7))
    fCham.introduceMolecule(fCham.H(2, 2))
    fCham.introduceMolecule(fCham.H(3, 10))
    fCham.executeRules()
    fCham.printSolution()
  }


  @Test
  def maxTest() {
    val fCham = new Cham with TestCham with DebugCham {
      val V = LocalRelation("V")

      val x, y = VarScalaInt()


      rules(
        (V(x) & V(y))  --> {x <= y} ?: V(y)
         )
    }
    fCham.enableStreamingTrace()
    fCham.enableSolutionTrace()
    fCham.introduceMolecule(fCham.V(2))
    fCham.introduceMolecule(fCham.V(2))
    fCham.introduceMolecule(fCham.V(3))
    fCham.introduceMolecule(fCham.V(4))
    fCham.introduceMolecule(fCham.V(4))

    fCham.executeRules()
    fCham.printSolution()
  }

  @Test
  def bubbleTest() {
    val fCham = new Cham with TestCham with DebugCham {
      val L = LocalRelation("L")

      val x, y, u, v = VarScalaInt()


      rules(
        (L(x, u) & L(y, v))  --> {(y <=>  (x + 1)) && (v < u)} ?: (L(x, v) & L(y, u))
      )
    }
    fCham.enableStreamingTrace()
    fCham.enableSolutionTrace()
    val j = 5
    for (i <- 0 to j)
      fCham.introduceMolecule(fCham.L(i, j - i))
    fCham.executeRules()
    fCham.printSolution()
  }

}


/*fCham.introduceMolecule(fCham.L(1, 64))
    fCham.introduceMolecule(fCham.L(2, 2))
    fCham.introduceMolecule(fCham.L(3, 1))
    fCham.introduceMolecule(fCham.L(4, 4 ))
    fCham.introduceMolecule(fCham.L(5, 32))
    fCham.introduceMolecule(fCham.L(6, 16))
    */