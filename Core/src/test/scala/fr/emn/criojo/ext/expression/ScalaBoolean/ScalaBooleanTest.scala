package fr.emn.criojo.ext.expression.ScalaBoolean

import org.junit.{Before, Test}
import org.junit.Assert._

import constructor._
import fr.emn.criojo.TestCham
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.core.datatype.Term
import fr.emn.criojo.ext.expression.ScalaList.VarScalaList
import fr.emn.criojo.ext.expression.ScalaList.constructor.WrapScalaNil

class ScalaBooleanTest {
  var boolTrue, boolFalse: ScalaBoolean = _
  var boolVar: VarScalaBoolean = _

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

  @Test (expected = classOf[NoValueDefined]) def testAccesValueOnOperation {
    val result = boolTrue && boolTrue
    result.value
  }

  // Variable Tests
  @Test (expected = classOf[NoValueDefined]) def testAccesValueVar() {
    val b = VarScalaBoolean()
    b.value
  }

  @Test def testEqualsVar() {
    criojoAssertEquals(boolVar, boolVar)
  }

  @Test def testNotVar() {
    val cham = new Cham with TestCham {
      override def name = "testNotCham"
      val BoolTrue = LocalRelation("BoolTrue")
      val BoolFalse = LocalRelation("BoolFalse")
      val bTrue, bFalse = VarScalaBoolean()

      rules (
        (BoolTrue(bTrue) & BoolFalse(bFalse)) -->
          ( AssertTrue(!bFalse)
            & AssertTrue(!(!bTrue)))
      )
    }

    cham.introduceMolecule(cham.BoolTrue(WrapScalaBoolean(true)))
    cham.introduceMolecule(cham.BoolFalse(WrapScalaBoolean(false)))
    cham.executeRules()
  }

  @Test def testOrVar() {
    val cham = new Cham with TestCham {
      override def name = "testOrCham"
      val BoolTrue = LocalRelation("BoolTrue")
      val BoolFalse = LocalRelation("BoolFalse")
      val bTrue, bFalse = VarScalaBoolean()

      rules (
        (BoolTrue(bTrue) & BoolFalse(bFalse)) -->
          ( AssertTrue(bTrue || bTrue)
            & AssertTrue(bTrue || bFalse)
            & AssertTrue(!(bFalse || bFalse)))
      )
    }

    cham.introduceMolecule(cham.BoolTrue(WrapScalaBoolean(true)))
    cham.introduceMolecule(cham.BoolFalse(WrapScalaBoolean(false)))
    cham.executeRules()
  }

  @Test def testAndVar() {
    val cham = new Cham with TestCham {
      override def name = "testAndCham"
      val BoolTrue = LocalRelation("BoolTrue")
      val BoolFalse = LocalRelation("BoolFalse")
      val bTrue, bFalse = VarScalaBoolean()

      rules (
        (BoolTrue(bTrue) & BoolFalse(bFalse)) -->
          ( AssertTrue(bTrue && bTrue)
            & AssertTrue(!(bTrue && bFalse))
            & AssertTrue(!(bFalse && bFalse)))
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
