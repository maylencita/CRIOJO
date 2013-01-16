package fr.emn.criojo.expression

import list.{ListVar, CriojoNil, CriojoList}
import org.junit.{Test, Before}
import org.junit.Assert._

import CriojoAssert._
import scala._
import fr.emn.criojo.parallel.Agent
import fr.emn.criojo.testing.TestCham
import scala.WrapScalaList

class ScalaListTest extends ScalaTypesPredef{
  val _1: CriojoInt = 1
  val _2: CriojoInt = 2
  val _3: CriojoInt = 3

  val _foo: CriojoString = "foo"
  val _bar: CriojoString = "bar"
  val _baz: CriojoString = "baz"

  val _true: CriojoBoolean = true
  val _false: CriojoBoolean = false

  var lInt: CriojoList[CriojoInt] = _
  var lString: CriojoList[CriojoString] = _
  var lBool: CriojoList[CriojoBoolean] = _
  var lColonColon: CriojoList[CriojoInt] = _

  @Before def setUp() {
    lInt = _1 :: _2 :: _3 :: CriojoNil
    lString = _foo :: _bar :: _baz :: CriojoNil
    lBool = _true :: _true :: CriojoNil
    lColonColon = _1 :: _2 :: _3 :: CriojoNil
  }

  @Test def testForAll() {
    criojoAssertTrue(lInt.forall(i => i > 0))
    criojoAssertTrue(lString.forall(s => s.length > 0))
    criojoAssertTrue(lBool.forall(b => b))
  }

  @Test def testEquals() {
    assertEquals(lInt, lInt)
    assertEquals(lInt, _1 :: _2 :: _3 :: CriojoNil)
  }

  @Test def testMatches() {
    val hd = IntVar()
    val tl = ListVar()

    assertTrue("Error: (hd::tl) should match " + lInt, (hd::tl).matches(lInt))
  }

  @Test def testColonColon() {
    val lInt = _1 :: _1 :: Nil
    val lString = _foo :: _foo :: Nil
    val lBool = _true :: _true :: Nil

    criojoAssertTrue(lInt.forall(_ <=> _1))
    criojoAssertTrue(lInt.length <=> _2)
    criojoAssertEquals(lInt, WrapScalaList(_1 :: _1 :: Nil))
    criojoAssertTrue(lString.forall(_ <=> _foo))
    criojoAssertTrue(lString.length <=> _2)
    criojoAssertEquals(lString, WrapScalaList(_foo :: _foo :: Nil))
//    criojoAssertTrue(lBool.forall(b => b))
    criojoAssertTrue(lBool.length <=> _2)
    criojoAssertEquals(lBool, WrapScalaList(_true :: _true :: Nil))
  }

  @Test def testContains() {
    val _4:CriojoInt = 4
    val _foobar = "foobar"

    val lPrepend = lInt :: lInt

    criojoAssertTrue(lInt.contains(_3))
    criojoAssertTrue(!lInt.contains(_4))
    criojoAssertTrue(lString.contains(_foo))
    criojoAssertTrue(!lString.contains(_foobar))
    criojoAssertTrue(lBool.contains(_true))
    criojoAssertTrue(!lBool.contains(_false))
    criojoAssertTrue(lPrepend.contains(lInt))
    criojoAssertTrue(!lPrepend.contains(lString))
  }

  @Test def testLength() {
    val _4:CriojoInt = 4

    val llInt = _1 :: lInt
    val llColonColon = _1 :: lColonColon

    criojoAssertTrue(lInt.length <=> _3)
    criojoAssertTrue(llInt.length <=> _4)
    criojoAssertTrue(lColonColon.length <=> _3)
    criojoAssertTrue(llColonColon.length <=> _4)
  }

  @Test def testAgentLength() {
    val cham = new Agent with TestCham {
      override def name = "testAgentLength"
      val List = LocalRelation("List")
      val l = Var[CriojoList[ScalaInt]]

      rules(
        List(l) --> AssertTrue(l.length <=> _3)
      )
    }
    cham.start()

    cham ! cham.List(lInt)

    Thread.sleep(200)
    cham.testAssert()
  }

  @Test def testSimplePatternMatching() {
    val cham = new Agent with TestCham {
      override def name = "testSimplePatternMatching"
      val List = LocalRelation("List")
      val tl = Var[CriojoList[ScalaInt]]
      val hd = Var[ScalaInt]

      rules(
        List(hd :: tl) --> (AssertTrue(hd <=> 1)
          & AssertTrue(tl.length <=> _2))
      )
    }
    cham.start()

    cham ! cham.List(lInt)

    Thread.sleep(200)
    cham.testAssert()
  }

  @Test def testHardPatternMatching() {
    val _sentinel:CriojoString = "sentinel"

    val cham = new Agent with TestCham {
      override def name = "testHardPatternMatching"
      val List = LocalRelation("List")
      val tl = ListVar[CriojoString]()
      val hd1, hd2 = StringVar()

      rules (
        List(hd1 :: _sentinel :: tl) --> (AssertTrue(hd1 <=> _foo)
            & AssertTrue(tl.forall(_ <=> _bar))
            & AssertTrue(tl.length <=> _2))
        , List(hd1 :: hd2 :: tl) --> (AssertTrue(hd1 <=> _foo)
            & AssertTrue(hd2 <=> _bar)
            & AssertTrue(tl.forall(_ <=> _baz))
            & AssertTrue(tl.length <=> _2))
      )
    }
    cham.start()

    cham ! cham.List(_foo :: _sentinel :: _bar :: _bar :: CriojoNil)
    cham ! cham.List(_foo :: _bar :: _baz :: _baz :: CriojoNil)

    Thread.sleep(200)
    cham.testAssert()
  }

}

