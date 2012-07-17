package fr.emn.criojo.ext.expression.ScalaInt

import org.junit.{Before, Test}
import org.junit.Assert._

import constructor._
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.core.datatype.ProhibitedOperation

class ScalaIntTest {
  var int2, int5, intVar: ScalaInt = _

  @Before def setUp() {
    int2 = WrapScalaInt(2)
    int5 = WrapScalaInt(5)
    intVar = VarScalaInt()
  }

  @Test def testEqual() {
    criojoAssertEquals(int2, int2)
    criojoAssertEquals(int2, WrapScalaInt(2))
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
    val expected = WrapScalaInt(7)
    val result = int2 + int5
    criojoAssertTrue(result <=> expected)
  }

  @Test def testMinus() {
    val expected = WrapScalaInt(-3)
    val result = int2 - int5
    criojoAssertTrue(result <=> expected)
  }

  @Test def testCross() {
    val expected = WrapScalaInt(10)
    val result = int2 * int5
    criojoAssertTrue(result <=> expected)
  }

  @Test def testDivide() {
    val expected = WrapScalaInt(0)
    val result = int2 / int5
    criojoAssertTrue(result <=> expected)
  }

  @Test (expected = classOf[NoValueDefined]) def testValueOnOperation {
    val result = int2 + int5
    result.value
  }

  // Variable Tests
  @Test (expected = classOf[NoValueDefined]) def testValueVar() {
    intVar.value
  }

  @Test (expected = classOf[ProhibitedOperation]) def testGetValueVar() {
    intVar.getValue()
  }

  @Test (expected = classOf[ProhibitedOperation]) def testEqualsVar() {
    criojoAssertEquals(intVar, intVar)
    criojoAssertTrue(intVar <=> intVar)
  }

  // ****************************************************************** Utils **
  def criojoAssertEquals(int1: ScalaInt, int2: ScalaInt) {
    assertEquals(int1, int2)
  }

  def criojoAssertTrue(b: ScalaBoolean) {
    assertTrue(b.reduce() == WrapScalaBoolean(true))
  }

  def criojoAssertFalse(b: ScalaBoolean) {
    criojoAssertTrue(!b)
  }
}
