package fr.emn.criojo.expression

import org.junit.Assert._
import fr.emn.criojo.core.model.{Expression, WrappedValue}

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 1/9/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
object CriojoAssert {
  def criojoAssertEquals(expected: Expression, actual: Expression) {
    assertEquals(expected.reduce(), actual.reduce())
  }

  def criojoAssertTrue(b: Expression) {
    b.reduce() match {
      case _b:WrappedValue[Boolean] => assertTrue("Expected true, but found " + _b, _b.self)
      case _ => fail("Wrong value")
    }
  }

  def criojoAssertFalse(b: CriojoBoolean) {
    b.reduce() match {
      case _b:WrappedValue[Boolean] => assertTrue("Expected false, but found " + _b, !_b.self)
      case _ => fail("Wrong value")
    }
  }
}
