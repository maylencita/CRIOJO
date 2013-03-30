package fr.emn.criojo.expression

import org.junit.{Before, Test}
import org.junit.Assert._

import CriojoAssert._
import scala.{ScalaBoolean, BooleanVar, ScalaTypesPredef}
import fr.emn.criojo.core.ProhibitedOperation
import fr.emn.criojo.testing.TestCham
import fr.emn.criojo.parallel.Agent

class BooleanTest extends ScalaTypesPredef{
  var boolTrue, boolFalse, boolVar: CriojoBoolean = _

  @Before def setUp() {
    boolTrue = true
    boolFalse = false
    boolVar = BooleanVar()
  }

  @Test def testWrap {
    criojoAssertEquals(boolTrue, true)
    criojoAssertEquals(boolFalse, false)
    criojoAssertEquals(boolTrue, true)
    criojoAssertEquals(boolFalse, false)
  }

  @Test def testEquals() {
    criojoAssertEquals(boolTrue, boolTrue)
    criojoAssertEquals(boolTrue, true)
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
    boolTrue && boolTrue match {
      case b:ScalaBoolean => b.value
      case _ => //Wrong
    }
  }

  // Variable Tests
  @Test (expected = classOf[NoValueDefined]) def testValueVar() {
    boolVar.value
  }

  @Test (expected = classOf[ProhibitedOperation]) def testEqualsVar() {
    assertEquals(boolVar, boolVar)
    criojoAssertEquals(boolTrue, boolVar)
  }

  //TODO Cannot intercept this exception as it happens in another thread!
  @Test /*(expected = classOf[ProhibitedOperation])*/ def testNotVar() {
    val cham = new Agent with TestCham {
      override def name = "testNotCham"
      val Sequence = LocalRelation("Sequence")
      val BoolTrue = LocalRelation("BoolTrue")
      val BoolFalse = LocalRelation("BoolFalse")
      val bTrue, bFalse = BooleanVar()

      rules (
        (BoolTrue(bTrue) & BoolFalse(bFalse)) -->
            ( AssertTrue(!bFalse)
            & AssertTrue(!(!bTrue))
            & BoolTrue(bTrue) & Sequence())//,
//        (BoolTrue(!bTrue) & Sequence()) --> Well()
      )
    }
    cham.start()

    cham ! cham.BoolTrue(true)
    cham ! cham.BoolFalse(false)

    Thread.sleep(500)
    cham.testAssert()
  }

  @Test /*(expected = classOf[ProhibitedOperation])*/ def testOrVar() {
    val cham = new Agent with TestCham {
      override def name = "testOrCham"
      val Sequence = LocalRelation("Sequence")
      val BoolTrue = LocalRelation("BoolTrue")
      val BoolFalse = LocalRelation("BoolFalse")
      val bTrue, bFalse = BooleanVar()

      rules (
        (BoolTrue(bTrue) & BoolFalse(bFalse)) -->
            ( AssertTrue(bTrue || bTrue)
            & AssertTrue(bTrue || bFalse)
            & AssertTrue(!(bFalse || bFalse))
            & BoolTrue(bTrue) & Sequence())//,
//        (BoolTrue(bTrue || bFalse) & Sequence()) --> Well()
      )
    }
    cham.start()

    cham ! cham.BoolTrue(true)
    cham ! cham.BoolFalse(false)

    Thread.sleep(500)
    cham.testAssert()
  }

  @Test /*(expected = classOf[ProhibitedOperation])*/ def testAndVar() {
    val cham = new Agent with TestCham {
      override def name = "testAndCham"
      val Sequence = LocalRelation("Sequence")
      val BoolTrue = LocalRelation("BoolTrue")
      val BoolFalse = LocalRelation("BoolFalse")
      val bTrue, bFalse = BooleanVar()

      rules (
        (BoolTrue(bTrue) & BoolFalse(bFalse)) -->
            ( AssertTrue(bTrue && bTrue)
            & AssertTrue(!(bTrue && bFalse))
            & AssertTrue(!(bFalse && bFalse))
            & BoolFalse(bFalse) & Sequence())//,
//        (BoolFalse(bTrue && bFalse) & Sequence()) --> Well()
      )
    }
    cham.start()

    cham ! cham.BoolTrue(true)
    cham ! cham.BoolFalse(false)

    Thread.sleep(500)
    cham.testAssert()
  }

}
