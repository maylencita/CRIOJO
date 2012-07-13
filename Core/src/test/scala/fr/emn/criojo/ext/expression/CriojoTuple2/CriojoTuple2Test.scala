package fr.emn.criojo.ext.expression.CriojoTuple2

import org.junit.{Before, Test}
import org.junit.Assert._

import constructor._
import fr.emn.criojo.ext.expression.ScalaString.constructor.WrapScalaString
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.ext.expression.ScalaString.{VarScalaString, ScalaString}
import fr.emn.criojo.ext.expression.ScalaInt.{VarScalaInt, ScalaInt}
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.TestCham
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.ext.expression.CriojoPredef._

class CriojoTuple2Test {
  var _A, _B: ScalaString = _
  var _1, _2: ScalaInt = _
  var tupleA1, tupleB2: CriojoTuple2[ScalaString, ScalaInt] = _

  @Before def setUp() {
    _A = WrapScalaString("A")
    _B = WrapScalaString("B")
    _1 = WrapScalaInt(1)
    _2 = WrapScalaInt(2)
    tupleA1 = WrapScalaTuple2(Tuple2(_A, _1))
    tupleB2 = WrapScalaTuple2(Tuple2(_B, _2))
  }

  @Test def testWrap {
    assertEquals(tupleA1.value, Tuple2(_A, _1))
    assertEquals(tupleB2.value, Tuple2(_B, _2))
    assertEquals(tupleA1.getValue, Tuple2(_A, _1))
    assertEquals(tupleB2.getValue, Tuple2(_B, _2))
  }

  @Test def testEquals() {
    criojoAssertEquals(tupleA1, tupleA1)
    criojoAssertEquals(tupleA1, WrapScalaTuple2(Tuple2(_A, _1)))
    criojoAssertEquals(tupleA1, _A -> _1)
  }

  @Test (expected = classOf[NoValueDefined]) def testValueOperation() {
    val tuple = _A -> _1
    tuple.value
  }

  @Test def testSimplePatternMatching() {
    val cham = new Cham with TestCham {
      override def name = "testSimplePatternMatching"
      val Tuple2 = LocalRelation("Tuple2")
      val strVar1, strVar2 = VarScalaString()
      val intVar1, intVar2 = VarScalaInt()

      rules (
        (Tuple2(strVar1 -> intVar1) & Tuple2(strVar2 -> intVar2)) -->
            ( AssertTrue((strVar1 <=> _A && intVar1 <=> _1)
              || (strVar1 <=> _B && intVar1 <=> _2))
            & AssertTrue((strVar2 <=> _A && intVar2 <=> _1)
              || (strVar2 <=> _B && intVar2 <=> _2)))
      )
    }

    cham.introduceMolecule(cham.Tuple2(tupleA1))
    cham.introduceMolecule(cham.Tuple2(tupleB2))
    cham.executeRules()
  }

  @Test def testIntPatternMatching() {
    val _3 = WrapScalaInt(3)
    val _4 = WrapScalaInt(4)

    val cham = new Cham with TestCham {
      override def name = "testIntPatternMatching"
      val Tuple2 = LocalRelation("Tuple2")
      val intVar1, intVar2 = VarScalaInt()
      rules (
        Tuple2(intVar1 -> _2) --> AssertTrue(intVar1 <=> _1)
        , Tuple2(intVar1 -> intVar2) --> AssertTrue(intVar1 <=> _3)
      )
    }

    cham.introduceMolecule(cham.Tuple2(_3 -> _4))
    cham.introduceMolecule(cham.Tuple2(_1 -> _2))
    cham.executeRules()
  }

  // TODO: Unable Arrow Arrow in ArrowAssocCriojoTuple
  /*
  @Test def testArrowArrowPatternMatching() {
    val _3 = WrapScalaInt(3)
    val _4 = WrapScalaInt(4)

    val cham = new Cham with TestCham {
      override def name = "testArrowArrowPatternMatching"
      val Tuple2 = LocalRelation("Tuple2")
      val intVar1, intVar2, intVar3, intVar4 = VarScalaInt()
      rules (
        Tuple2(intVar1 -> intVar2 -> intVar3 -> intVar4) -->
          ( AssertTrue(intVar1 <=> _1)
          & AssertTrue(intVar2 <=> _2)
          & AssertTrue(intVar3 <=> _3)
          & AssertTrue(intVar4 <=> _4))
      )
    }

    cham.introduceMolecule(cham.Tuple2(_1 -> _2 -> _3 -> _4))
    cham.executeRules()
  }

  @Test def testArrowIntPatternMatching() {
    val _3 = WrapScalaInt(3)
    val _4 = WrapScalaInt(4)

    val cham = new Cham with TestCham {
      override def name = "testArrowIntPatternMatching"
      val Tuple2 = LocalRelation("Tuple2")
      val intVar1, intVar3 = VarScalaInt()
      rules (
        Tuple2(intVar1 -> _2 -> intVar3 -> _4) -->
          ( AssertTrue(intVar1 <=> _1)
          & AssertTrue(intVar3 <=> _3))
      )
    }

    cham.introduceMolecule(cham.Tuple2(_1 -> _2 -> _3 -> _4))
    cham.executeRules()
  }
  */

  // ****************************************************************** Utils **
  def criojoAssertEquals(t1: CriojoTuple2[ScalaString, ScalaInt],
    t2: CriojoTuple2[ScalaString, ScalaInt]) {
    assertEquals(t1.reduce(), t2.reduce())
  }
  def criojoAssertTrue(b: ScalaBoolean) {
    assertTrue(b.reduce() == WrapScalaBoolean(true))
  }

  def criojoAssertFalse(b: ScalaBoolean) {
    criojoAssertTrue(!b)
  }
}
