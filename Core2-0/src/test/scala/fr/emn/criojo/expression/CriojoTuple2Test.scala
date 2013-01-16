package fr.emn.criojo.expression

import org.junit.{Before, Test}
import org.junit.Assert._

import CriojoAssert._
import scala._
import fr.emn.criojo.testing.TestCham
import fr.emn.criojo.parallel.Agent

class CriojoTuple2Test extends ScalaTypesPredef{
  var _A, _B: CriojoString = _
  var _1, _2: CriojoInt = _
  var tupleA1, tupleB2:ScalaTuple2[CriojoString, CriojoInt] = _

  @Before def setUp() {
    _A = "A"
    _B = "B"
    _1 = 1
    _2 = 2
    tupleA1 = _A -> _1
    tupleB2 = _B -> _2
  }

//  @Test def testWrap() {
//    assertEquals(tupleA1, WrapScalaTuple2(_A, _1))
//    assertEquals(tupleB2, WrapScalaTuple2(_B, _2))
//  }

  @Test def testEquals() {
    criojoAssertEquals(tupleA1, tupleA1)
//    criojoAssertEquals(tupleA1, WrapScalaTuple2(_A, _1))
    criojoAssertEquals(tupleA1, _A -> _1)
  }

//  @Test (expected = classOf[NoValueDefined]) def testValueOperation() {
//    val tuple = _A -> _1
//    tuple.value
//  }

  @Test def testSimplePatternMatching() {
    val cham = new Agent with TestCham {
      override def name = "testSimplePatternMatching"
      val cTuple2 = LocalRelation("Tuple2")
      val strVar1, strVar2 = StringVar()
      val intVar1, intVar2 = IntVar()

      rules (
        (cTuple2(strVar1 -> intVar1) & cTuple2(strVar2 -> intVar2)) -->
            ( AssertTrue((strVar1 <=> _A && intVar1 <=> _1)
              || (strVar1 <=> _B && intVar1 <=> _2))
            & AssertTrue((strVar2 <=> _A && intVar2 <=> _1)
              || (strVar2 <=> _B && intVar2 <=> _2)))
      )
    }
    cham.start()

    cham ! cham.cTuple2(tupleA1)
    cham ! cham.cTuple2(tupleB2)

    Thread.sleep(200)
    cham.testAssert()
  }

  @Test def testIntPatternMatching() {
    val _3:CriojoInt = 3
    val _4:CriojoInt = 4

    val cham = new Agent with TestCham {
      override def name = "testIntPatternMatching"
      val Tuple2 = LocalRelation("Tuple2")
      val intVar1, intVar2 = IntVar()
      rules (
        Tuple2(intVar1 -> _2) --> AssertTrue(intVar1 <=> _1)
        , Tuple2(intVar1 -> intVar2) --> AssertTrue(intVar1 <=> _3)
      )
    }
    cham.start()

    cham ! cham.Tuple2(_3 -> _4)
    cham ! cham.Tuple2(_1 -> _2)

    Thread.sleep(200)
    cham.testAssert()
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

}
