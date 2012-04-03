package fr.emn.criojo.core

import org.junit.Test

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/16/12
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */

class FunctionTest {

  @Test
  def MatchesTest() {

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)
    var fun:Function = new Function("Carbon",listOfTerms)

    val x1 = new Variable("x")
    val y1 = new Variable("y")

    val listOfTerms1:List[Term] = List(x1, y1)
    var fun1:Function = new Function("Carbon",listOfTerms1)

    assert(fun.matches(fun1))

    val x2 = new Variable("x1")
    val y2 = new Variable("y")

    val listOfTerms2:List[Term] = List(x2, y2)
    var fun2:Function = new Function("Carbon",listOfTerms2)

    assert(fun.matches(fun2))

    val x3 = new Variable("x1")
    val y3 = new Variable("y")

    val listOfTerms3:List[Term] = List(x3, y3)
    var fun3:Function = new Function("Carbon2",listOfTerms2)

    assert(!fun.matches(fun3))
    assert(!fun.matches(null))
  }

  @Test
  def ApplyTest() {

    val x = new Variable("x")
    val y = new Variable("y")
    val z = new Variable("z")

    val listOfTerms:List[Term] = List(x, y)
    var fun:Function = new Function("Carbon",listOfTerms)
    assert(fun.params.size==2)

    val listOfTerms1:List[Term] = List(x, z, y)
    var fun1 = fun.apply(listOfTerms1)

    assert(fun1.params.size==3)
    assert(fun1.toString != "Carbon")

    var fun2:Function = new Function("Carbon",List())
    assert(fun2.toString == "Carbon")
    fun2 = fun2.apply(listOfTerms1)
    assert(fun2.toString != "Carbon")
  }
}
