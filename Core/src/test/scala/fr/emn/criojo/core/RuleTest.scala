package fr.emn.criojo.core

import org.junit.Test
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.ext.IntegerCham


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/16/12
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */

class RuleTest {

  @Test
  def ApplyReactionTest() {

    val cm = new Cham with IntegerCham {

    }

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)

    val rule = cm.createRule(List(new Atom("A", listOfTerms)), List(new Atom("B", listOfTerms)), EmptyGuard, Set())

    rule.addExecution(new Atom("A", listOfTerms))

    assert(rule.execute())
  }

  @Test
  def EqualTest() {

    val cm = new Cham with IntegerCham {

    }

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)

    val rule = cm.createRule(List(new Atom("A", listOfTerms)), List(new Atom("B", listOfTerms)), EmptyGuard, Set())
    val rule2 = cm.createRule(List(new Atom("A", listOfTerms)), List(new Atom("B", listOfTerms)), EmptyGuard, Set())
    val rule3 = cm.createRule(List(new Atom("B", listOfTerms)), List(new Atom("C", listOfTerms)), EmptyGuard, Set())

    assert(rule.equals(rule2))
    assert(!rule.equals(rule3))
    assert(!rule.equals(true))
  }

}
