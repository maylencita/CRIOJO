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

  @Test
  def extensionTest{
    val val1 = Valuation(x->2,y->2)
    val val2 = Valuation(y->2,z->3,x->2)
    val val3 = Valuation(x->3,y->2,z->1)

    assertTrue(val1 + " !ext " + val2, val1.hasExtension(val2))
    assertTrue(val2 + " ext " + val3, !val1.hasExtension(val3))
  }

  @Test
  def normalFormTest(){
    val vg = new NormalForm(Valuation(x->2,y->2),(!Valuation(z->3))::(!Valuation(z->4))::Nil)
    val vg2 = new NormalForm(!Valuation(x->3,y->3),Valuation(z->4)::(!Valuation(z->5))::Nil)
    assertTrue("Not a normal form: " + vg, vg.isNormalForm)
    assertTrue("Not a normal form: " + vg2, !vg2.isNormalForm)
  }

  @Test
  def notTest{
    val lst = new ValuationList(List(
      new NormalForm(Valuation(x->2,y->2),(!Valuation(z->3))::(!Valuation(z->4))::Nil),
      new NormalForm(Valuation(x->3,y->4),(!Valuation(z->5))::(!Valuation(z->6))::Nil),
      new NormalForm(Valuation(x->6,y->6),(!Valuation(z->4))::(!Valuation(z->3))::Nil)
    ))

    val expected = "{(z=6) ^ (x!=2,y!=2)^(x!=6,y!=6)}," +
      "{(z=5) ^ (x!=2,y!=2)^(x!=6,y!=6)}," +
      "{T ^ (x!=2,y!=2)^(x!=3,y!=4)^(x!=6,y!=6)}," +
      "{(z=4) ^ (x!=2,y!=2)^(x!=3,y!=4)}," +
      "{(z=3) ^ (x!=2,y!=2)^(x!=3,y!=4)}," +
      "{(z=3) ^ (x!=3,y!=4)^(x!=6,y!=6)}," +
      "{(z=3) ^ (x!=3,y!=4)}," +
      "{(z=4) ^ (x!=3,y!=4)^(x!=6,y!=6)}," +
      "{(z=4) ^ (x!=3,y!=4)}"
    assertEquals(expected, lst.not.mkString("",",",""))
  }
}