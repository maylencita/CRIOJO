package fr.emn.criojo.integration

import org.junit.Test
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.ext.debug.DebugCham
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.ext.expression.ScalaInt.VarScalaInt
import fr.emn.criojo.core.Converters._

/**
 * Created with IntelliJ IDEA.
 * User: jonathan
 * Date: 7/6/12
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */

class IPIPIPTest {

  @Test
  def bubbleTest() {
    val fCham = new Cham with IntegerCham with DebugCham {
      val L = LocalRelation("L")

      val x, y, u, v = VarScalaInt()


      rules(
        (L(x, u) & L(y, v))  --> {(y Equal (x + 1)) && (v GreaterThan u)} ?: (L(x, v) & L(y, u))
      )
    }
    fCham.enableStreamingTrace()
    fCham.enableSolutionTrace()
    val j = 43
    for (i <- 0 to j)
      fCham.introduceMolecule(fCham.L(i, j - i))
    fCham.executeRules()
    fCham.printSolution()
  }
}
