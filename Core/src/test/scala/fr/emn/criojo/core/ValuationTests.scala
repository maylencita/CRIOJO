package fr.emn.criojo.core

import org.junit._
import Assert._

import fr.emn.criojo.ext.expressions.IntExpression
import fr.emn.criojo.core.Criojo._

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 07/03/12
 * Time: 00:34
 */

class ValuationTests {
  implicit def num2fun(n:Int):Term = new IntExpression(n)

  //Common objects
  val x = Variable("x")
  val y = Variable("y")
  val z = Variable("z")

  @Test
  def unionTest{

    val v1 = Valuation(x->1, z->3)
    val v2 = Valuation(y->2)
    val v3 = Valuation(y->2,z->4)
    val union1 = v1 union v2
    val union2 = v2 union v3

    val expected1 = Valuation(x->1,z->3,y->2)
    val expected2 = Valuation(y->2,z->4)
    assertTrue("Expected : " + expected1 + "\n" +
               "Actual   : " + union1, expected1.sameElements(union1))
    assertTrue("Expected : " + expected2 + "\n" +
               "Actual   : " + union2, expected2.sameElements(union2))
  }

  @Test
  def emptyUnionTest{

    val v1 = Valuation(x->1, z->3)
    val v2 = Valuation(y->2, x->2)
    val union = v1 union v2

    assertTrue("Expected : Empy \n" +
               "Actual   : " + union,union.isEmpty)
  }

  @Test
  def getValuationTest{

    val termLst1 = List(x,y,z)
    val termLst2 = List[Term](1,2,3)
    val valuation = getValuation(termLst1,termLst2)

    val expected = Valuation(x->1,y->2,z->3)
    assertTrue("Expected : " + expected + "\n" +
               "Actual   : " + valuation, expected.sameElements(valuation))
  }

  @Test
  def applyValuationTest{
    val atom = Atom("A",x,y,z)
    val valuation = Valuation(x->1,y->2,z->3)
    val newAtom = atom.applyValuation(valuation)
    val expected = Atom("A",1,2,3)

    assertTrue("Expected : " + expected + "\n" +
               "Actual   : " + newAtom, expected.matches(newAtom))
  }
}