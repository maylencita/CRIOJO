package fr.emn.criojo.core

import org.junit.Test
import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.lang.Cham


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/16/12
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */

class RuleTest {

  @Test
  def ApplyReactionTest {

    val cm = new Cham with IntegerCham {

    }

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom("Carbon",listOfTerms)

    val x1 = new Variable("x")
    val y1 = new Variable("y")
    val vx1 = new ValueTerm[Int](1)
    val vy1 = new ValueTerm[Int](2)

    val listOfSubstitutions:List[(Variable, Term)] = List((x1,vx1),(y1,vy1))

    val rule = cm.createRule(List(new Atom("A", listOfTerms)), List(new Atom("B", listOfTerms)), EmptyGuard, List())

    rule.addExecution(new Atom("A", listOfTerms))

    assert(rule.execute(listOfSubstitutions))
  }

  @Test
  def EqualTest {

    val cm = new Cham with IntegerCham {

    }

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom("Carbon",listOfTerms)

    val x1 = new Variable("x")
    val y1 = new Variable("y")
    val vx1 = new ValueTerm[Int](1)
    val vy1 = new ValueTerm[Int](2)

    val listOfSubstitutions:List[(Variable, Term)] = List((x1,vx1),(y1,vy1))

    val rule = cm.createRule(List(new Atom("A", listOfTerms)), List(new Atom("B", listOfTerms)), EmptyGuard, List())
    val rule2 = cm.createRule(List(new Atom("A", listOfTerms)), List(new Atom("B", listOfTerms)), EmptyGuard, List())
    val rule3 = cm.createRule(List(new Atom("B", listOfTerms)), List(new Atom("C", listOfTerms)), EmptyGuard, List())

    assert(rule.equals(rule2))
    assert(!rule.equals(rule3))
    assert(!rule.equals(true))
  }

  @Test
  def GetHeadSubsTest {

    val cm = new Cham with IntegerCham {

    }

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom("Carbon",listOfTerms)

    val x1 = new Variable("x")
    val y1 = new Variable("y")
    val vx1 = new ValueTerm[Int](1)
    val vy1 = new ValueTerm[Int](2)

    val listOfSubstitutions:List[(Variable, Term)] = List((x1,vx1),(y1,vy1))

    val rule = cm.createRule(List(new Atom("A", listOfTerms)), List(new Atom("B", listOfTerms)), EmptyGuard, List())
    val rule2 = cm.createRule(List(new Atom("A", listOfTerms)), List(new Atom("B", listOfTerms)), EmptyGuard, List())
    val rule3 = cm.createRule(List(new Atom("B", listOfTerms)), List(new Atom("C", listOfTerms)), EmptyGuard, List())

    var result = rule.getHeadSubstitutions(List(new Atom("A", listOfTerms),new Atom("B", listOfTerms)))

    assert(result.size == 2)
  }
}
