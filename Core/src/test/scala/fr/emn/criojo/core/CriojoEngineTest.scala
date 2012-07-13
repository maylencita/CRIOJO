package fr.emn.criojo.core

import datatype.Term
import engine.{CriojoCham, Combinatory}
import org.junit.Test
import fr.emn.criojo.core.Converters._
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.ext.expression.ScalaInt.VarScalaInt
import collection.mutable.ListBuffer
import fr.emn.criojo.ext.debug.DebugCham

/**
 * Created with IntelliJ IDEA.
 * User: jonathan
 * Date: 6/28/12
 * Time: 12:24 PM
 * To change this template use File | Settings | File Templates.
 */

class CriojoEngineTest {

  @Test
  def combinationTest() {
    var combination = new Combinatory[Int](ListBuffer(1,2,3,4), new Combinatory[Int](ListBuffer(5,6,7), new Combinatory[Int](ListBuffer(8,9,10), new Combinatory[Int](ListBuffer(11,12,13), null))))

    while(combination.get() != Nil) {
      println(combination.get())
      combination.next()
    }
  }

  @Test
  def multiplicityTest() {

    val cham = new CriojoCham with DebugCham {
      var A:LocalRelation = new LocalRelation("A")
      var B:LocalRelation = new LocalRelation("B")
      val x:VarScalaInt = VarScalaInt("x")
      val y:VarScalaInt = VarScalaInt("y")

      rules(
        (A(x)&A(y)) --> B(x+y),
        (A(x)&B(y)) --> B(x+y),
        (B(x)&B(y)) --> B(x+y)
      )
    }

    println(cham.solution.listOfAtoms.mkString(","))

    cham.enableStreamingTrace()

    cham.introduceAtom(cham.A(1).head)
    cham.introduceAtom(cham.A(1).head)
    cham.introduceAtom(cham.A(1).head)
    cham.introduceAtom(cham.A(1).head)
    cham.introduceAtom(cham.A(1).head)
    cham.introduceAtom(cham.A(1).head)

    cham.executeRules()

    println(cham.solution.listOfAtoms.mkString(","))
  }
}
