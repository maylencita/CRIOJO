package fr.emn.criojo.core

import datatype.Term
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

class NormalEngineTest {

  @Test
  def combinationTest() {
    var combination = new Combinatory[Int](ListBuffer(1,2,3,4), new Combinatory[Int](ListBuffer(5,6,7), new Combinatory[Int](ListBuffer(8,9,10), new Combinatory[Int](ListBuffer(11,12,13), null))))
//    var combination = new Combinatory[Int](ListBuffer(1,2), null)
    while(combination.get() != Nil) {
      println(combination.get())
      combination.next()
    }
  }

//  @Test
//  def solutionTest() {
//
//    val cham = new NormalCham with DebugCham {
//      var A:LocalRelation = new LocalRelation("A")
//      val B:LocalRelation = new LocalRelation("B")
//      val C:LocalRelation = new LocalRelation("C")
//      val D:LocalRelation = new LocalRelation("D")
//      val x:VarScalaInt = VarScalaInt("x")
//      val y:VarScalaInt = VarScalaInt("y")
//
//      rules(
//        (B(x)&B(x)&B(x)) --> (A(x)),
//          (A(x)) --> (B(x)&B(x)&B(x))
//      )
//    }
//
//    println(cham.solution.listOfAtoms.mkString(","))
//
//    cham.enableStreamingTrace()
//
//    cham.introduceAtom(cham.B(1).head)
//    cham.introduceAtom(cham.C(1).head)
//    cham.introduceAtom(cham.B(1).head)
//    cham.introduceAtom(cham.B(1).head)
//
//    cham.executeRules()
//
//    cham.printSolution()
//  }


//  @Test
//  def solutionTest2() {
//
//    val cham = new NormalCham with DebugCham {
//      var A:LocalRelation = new LocalRelation("A")
//      val B:LocalRelation = new LocalRelation("B")
//      val C:LocalRelation = new LocalRelation("C")
//      val D:LocalRelation = new LocalRelation("D")
//      val x:VarScalaInt = VarScalaInt("x")
//      val y:VarScalaInt = VarScalaInt("y")
//
//      rules(
//        (A(x)&A(y)&B(y)) --> (C(y)&B(x)),
//        (B(x)&C(y)) --> (A(x)&A(x)&B(x))
//      )
//    }
//
//    println(cham.solution.listOfAtoms.mkString(","))
//
//    cham.enableStreamingTrace()
//
//    cham.introduceAtom(cham.A(1).head)
//    cham.introduceAtom(cham.A(2).head)
//    cham.introduceAtom(cham.B(2).head)
//    cham.introduceAtom(cham.B(1).head)
//
//    cham.executeRules()
//
//    cham.printSolution()
//  }


  @Test
  def multiplicityTest() {

    val cham = new NormalCham with DebugCham {
      var A:LocalRelation = new LocalRelation("A")
      var B:LocalRelation = new LocalRelation("B")
      val x:VarScalaInt = VarScalaInt("x")
      val y:VarScalaInt = VarScalaInt("y")

      rules(
        (A(x)&A(y)) --> B(x+y),
        (A(x)&B(y)) --> B(x+y)
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

    cham.printSolution()
  }
}
