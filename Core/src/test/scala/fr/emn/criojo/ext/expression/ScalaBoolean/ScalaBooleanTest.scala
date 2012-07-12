package fr.emn.criojo.ext.expression.ScalaBoolean

import org.junit.{Before, Test}
import org.junit.Assert._

import constructor._
import fr.emn.criojo.TestCham
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.core.datatype.{ProhibitedOperation, Term}
import fr.emn.criojo.ext.expression.ScalaList.VarScalaList
import fr.emn.criojo.ext.expression.ScalaList.constructor.WrapScalaNil

class ScalaBooleanTest {
  var boolTrue, boolFalse, boolVar: ScalaBoolean = _

  @Before def setUp() {
    boolTrue = WrapScalaBoolean(true)
    boolFalse = WrapScalaBoolean(false)
    boolVar = VarScalaBoolean()
  }

  @Test def testWrap {
    assertEquals(boolTrue.value, true)
    assertEquals(boolFalse.value, false)
    assertEquals(boolTrue.getValue(), true)
    assertEquals(boolFalse.getValue(), false)
  }

  @Test def testEquals() {
    criojoAssertEquals(boolTrue, boolTrue)
    criojoAssertEquals(boolTrue, WrapScalaBoolean(true))
  }

  @Test def testNot {
    criojoAssertTrue(!boolFalse)
    criojoAssertTrue(!(!boolTrue))
  }

  @Test def testOr() {
    criojoAssertTrue(boolTrue || boolTrue)
    criojoAssertTrue(boolTrue || boolFalse)
    criojoAssertTrue(!(boolFalse || boolFalse))
  }

  @Test def testAnd() {
    criojoAssertTrue(boolTrue && boolTrue)
    criojoAssertTrue(!(boolTrue && boolFalse))
    criojoAssertTrue(!(boolFalse && boolFalse))
  }

  @Test (expected = classOf[NoValueDefined]) def testValueOnOperation {
    val result = boolTrue && boolTrue
    result.value
  }

  // Variable Tests
  @Test (expected = classOf[NoValueDefined]) def testValueVar() {
    boolVar.value
  }

  @Test (expected = classOf[ProhibitedOperation]) def testGetValueVar() {
    boolVar.getValue()
  }

  @Test def testEqualsVar() {
    criojoAssertEquals(boolVar, boolVar)
  }

  @Test (expected = classOf[ProhibitedOperation]) def testNotVar() {
    val cham = new Cham with TestCham {
      override def name = "testNotCham"
      val Sequence = LocalRelation("Sequence")
      val BoolTrue = LocalRelation("BoolTrue")
      val BoolFalse = LocalRelation("BoolFalse")
      val bTrue, bFalse = VarScalaBoolean()

      rules (
        (BoolTrue(bTrue) & BoolFalse(bFalse)) -->
            ( AssertTrue(!bFalse)
            & AssertTrue(!(!bTrue))
            & BoolTrue(bTrue) & Sequence()),
        (BoolTrue(!bTrue) & Sequence()) --> Well()
      )
    }

    cham.introduceMolecule(cham.BoolTrue(WrapScalaBoolean(true)))
    cham.introduceMolecule(cham.BoolFalse(WrapScalaBoolean(false)))
    cham.executeRules()
  }

  @Test (expected = classOf[ProhibitedOperation]) def testOrVar() {
    val cham = new Cham with TestCham {
      override def name = "testOrCham"
      val Sequence = LocalRelation("Sequence")
      val BoolTrue = LocalRelation("BoolTrue")
      val BoolFalse = LocalRelation("BoolFalse")
      val bTrue, bFalse = VarScalaBoolean()

      rules (
        (BoolTrue(bTrue) & BoolFalse(bFalse)) -->
            ( AssertTrue(bTrue || bTrue)
            & AssertTrue(bTrue || bFalse)
            & AssertTrue(!(bFalse || bFalse))
            & BoolTrue(bTrue) & Sequence()),
        (BoolTrue(bTrue|| bFalse) & Sequence()) --> Well()
      )
    }

    cham.introduceMolecule(cham.BoolTrue(WrapScalaBoolean(true)))
    cham.introduceMolecule(cham.BoolFalse(WrapScalaBoolean(false)))
    cham.executeRules()
  }

  @Test (expected = classOf[ProhibitedOperation]) def testAndVar() {
    val cham = new Cham with TestCham {
      override def name = "testAndCham"
      val Sequence = LocalRelation("Sequence")
      val BoolTrue = LocalRelation("BoolTrue")
      val BoolFalse = LocalRelation("BoolFalse")
      val bTrue, bFalse = VarScalaBoolean()

      rules (
        (BoolTrue(bTrue) & BoolFalse(bFalse)) -->
            ( AssertTrue(bTrue && bTrue)
            & AssertTrue(!(bTrue && bFalse))
            & AssertTrue(!(bFalse && bFalse))
            & BoolFalse(bFalse) & Sequence()),
        (BoolFalse(bTrue && bFalse) & Sequence()) --> Well()
      )
    }

    cham.introduceMolecule(cham.BoolTrue(WrapScalaBoolean(true)))
    cham.introduceMolecule(cham.BoolFalse(WrapScalaBoolean(false)))
    cham.executeRules()
  }

  // ****************************************************************** Utils **
  def criojoAssertEquals(bool1: ScalaBoolean, bool2: ScalaBoolean) {
    assertEquals(bool1, bool2)
  }

  def criojoAssertTrue(b: ScalaBoolean) {
    assertTrue(b.reduce() == WrapScalaBoolean(true))
  }

  def criojoAssertFalse(b: ScalaBoolean) {
    criojoAssertTrue(!b)
  }
}
