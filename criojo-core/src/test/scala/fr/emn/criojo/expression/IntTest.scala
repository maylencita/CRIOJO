package fr.emn.criojo.expression

import org.junit.{Before, Test}
import org.junit.Assert._
import scala.{IntVar, ScalaTypesPredef}
import fr.emn.criojo.core.ProhibitedOperation
import CriojoAssert._

class IntTest extends ScalaTypesPredef{
  var int2, int5, intVar: CriojoInt = _

  @Before def setUp() {
    int2 = 2
    int5 = 5
    intVar = IntVar()
  }

  @Test def testEqual() {
    criojoAssertEquals(int2, int2)
    criojoAssertEquals(int2, 2)
    criojoAssertTrue(int2 <=> int2)
    criojoAssertTrue(!(int2 <=> int5))
  }

  @Test def testNotEqual() {
    criojoAssertTrue(int2 !<=> int5)
    criojoAssertTrue(!(int2 !<=> int2))
  }

  @Test def testGreater() {
    criojoAssertTrue(int5 > int2)
    criojoAssertTrue(!(int5 > int5))
    criojoAssertTrue(!(int2 > int5))
  }

  @Test def testGreaterEqual() {
    criojoAssertTrue(int5 >= int2)
    criojoAssertTrue(int5 >= int5)
    criojoAssertTrue(!(int2 >= int5))
  }

  @Test def testLess() {
    criojoAssertTrue(int2 < int5)
    criojoAssertTrue(!(int2 < int2))
    criojoAssertTrue(!(int5 < int2))
  }

  @Test def testLessEqual() {
    criojoAssertTrue(int2 <= int5)
    criojoAssertTrue(int2 <= int2)
    criojoAssertTrue(!(int5 <= int2))
  }

  @Test def testAdd() {
    val expected = 7
    val result = int2 + int5
    criojoAssertEquals(expected, result)
  }

  @Test def testMinus() {
    val expected = -3
    val result = int2 - int5
    criojoAssertEquals(expected, result)
  }

  @Test def testCross() {
    val expected = 10
    val result = int2 * int5
    criojoAssertEquals(expected, result)
  }

  @Test def testDivide() {
    val expected = 0
    val result = int2 / int5
    criojoAssertEquals(expected,result)
  }

//  @Test (expected = classOf[NoValueDefined]) def testValueOnOperation() {
//    val result = int2 + int5
//    result.value
//  }
//
//  // Variable Tests
//  @Test (expected = classOf[NoValueDefined]) def testValueVar() {
//    intVar.value
//  }
//
//  @Test (expected = classOf[ProhibitedOperation]) def testGetValueVar() {
//    intVar.getValue()
//  }

  @Test (expected = classOf[ProhibitedOperation]) def testEqualsVar() {
    criojoAssertEquals(intVar, intVar)
    criojoAssertTrue(intVar <=> intVar)
  }

  // ****************************************************************** Utils **
//  def criojoAssertEquals(int1: CriojoInt, int2: CriojoInt) {
//    assertEquals(int1.reduce(), int2.reduce())
//  }
//
//  def criojoAssertTrue(b: CriojoBoolean) {
//    b.reduce() match {
//      case _b:WrappedValue[Boolean] => assertTrue("Expected true, but found " + _b, _b.self)
//      case _ => fail("Wrong value")
//    }
//  }
//
//  def criojoAssertFalse(b: CriojoBoolean) {
//    criojoAssertTrue(!b)
//  }
}
