package fr.emn.criojo

import collection.mutable.ArrayBuffer

import ext.expression.Relation.constructor.LocalRelation
import org.junit.Assert._

import fr.emn.criojo.lang.Cham
import fr.emn.criojo.core.factory.DefaultFactory
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.core.datatype.Term
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean

/** TestCham is cham especially for junit testing.
  *
  * TestCham defines a new Relation type named AssertRel. All rules containing
  * a AssertRel at right must be called. At the of executeRules method, if one
  * of rule containing AssertRel not called, test fail with
  * java.lang.AssertionError.
  *
  * TestCham defines two specific AssertRel which are:
  *   1. AssertTrue, wrap junit assertTrue and test a ScalaBoolean is true.
  *   2. AssertFasle, wrap junit assertFalse and test a ScalaBoolean is false.
  */
trait TestCham extends Cham with DefaultFactory {
  /** Chame name using in AssertRel when one failed */
  def name: String = "test"
  var asserts: ArrayBuffer[Boolean] = ArrayBuffer()
  var assertsId = 0

  /** A well -- Do nothing */
  val Well = LocalRelation("Well")

  /** Print value of variable */
  val Println = NativeRel {
    case a :: Nil => println(a)
    case _ => println("_")
  }

  /** Test variable ScalaBoolean is <code>true</code> */
  def AssertTrue = AssertRel {
    case (b: ScalaBoolean) :: Nil =>
      assertTrue(b.reduce() == WrapScalaBoolean(b = true))
    case x => fail("Unknow type in AssertTrue of " + name + " " + x)
  }

  /** Test variable ScalaBoolean is <code>false</code> */
  def AssertFalse = AssertRel {
    case (b: ScalaBoolean) :: Nil =>
      assertTrue(b.reduce() == WrapScalaBoolean(b = false))
    case x => fail("Unknow type in AssertFalse of " + name + " " + x)
  }

  def AssertRel(f:(List[Term]) => Unit) = {
    asserts += false
    val currAssertId = assertsId
    assertsId += 1

    // set the asserts to true
    def g(u: Unit) {
      asserts.update(currAssertId, true)
    }

    NativeRel(g _ compose f)
  }

  /** Override executeRules to test all AssertRel are executed. */
  override def executeRules() {
    super.executeRules()

    var notExecutedAssertsId: String = ""

    for ((executed, assertId) <- asserts.view.zipWithIndex)
      if (!executed) notExecutedAssertsId += (assertId + 1) + ", "

    if (!notExecutedAssertsId.isEmpty)
        fail("Assertion(s) number(s) " +
          notExecutedAssertsId + "not executed in " + name)
  }
}

