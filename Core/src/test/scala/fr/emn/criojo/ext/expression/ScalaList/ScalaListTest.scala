package fr.emn.criojo.ext.expression.ScalaList

import org.junit.{Test, Before}
import org.junit.Assert._

import constructor._
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaString.constructor.WrapScalaString
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.ext.expression.ScalaInt.{VarScalaInt, ScalaInt}
import fr.emn.criojo.ext.expression.ScalaString.{ScalaString, VarScalaString}
import fr.emn.criojo.TestCham
import fr.emn.criojo.core.datatype.{Expression, Pattern}

class ScalaListTest {
  val _1: ScalaInt = WrapScalaInt(1)
  val _2: ScalaInt = WrapScalaInt(2)
  val _3: ScalaInt = WrapScalaInt(3)

  val _foo: ScalaString = WrapScalaString("foo")
  val _bar: ScalaString = WrapScalaString("bar")
  val _baz: ScalaString = WrapScalaString("baz")

  val _true: ScalaBoolean = WrapScalaBoolean(true)
  val _false: ScalaBoolean = WrapScalaBoolean(false)

  var lInt: ScalaList[ScalaInt] = _
  var lString: ScalaList[ScalaString] = _
  var lBool: ScalaList[ScalaBoolean] = _
  var lColonColon: ScalaList[ScalaInt] = _

  @Before def setUp() {
    lInt = WrapScalaList(_1 :: _2 :: _3 :: Nil)
    lString = WrapScalaList(_foo :: _bar :: _baz :: Nil)
    lBool = WrapScalaList(_true :: _true :: Nil)
    lColonColon = _1 :: _2 :: _3 :: WrapScalaNil
  }

  @Test def testForAll() {
    criojoAssertTrue(lInt.forall(i => i > WrapScalaInt(0)))
    criojoAssertTrue(lString.forall(s => s.length > WrapScalaInt(0)))
    criojoAssertTrue(lBool.forall(b => b))
  }

  @Test def testEquals() {
    assertEquals(lInt, lInt)
    assertEquals(lInt, WrapScalaList(_1 :: _2 :: _3 :: Nil))
  }

  @Test def testColonColon() {
    val lInt = _1 :: _1 :: WrapScalaNil
    val lString = _foo :: _foo :: WrapScalaNil
    val lBool = _true :: _true :: WrapScalaNil

    criojoAssertTrue(lInt.forall(_ <=> _1))
    criojoAssertTrue(lInt.length <=> _2)
    criojoAssertEquals(lInt, WrapScalaList(_1 :: _1 :: Nil))
    criojoAssertTrue(lString.forall(_ <=> _foo))
    criojoAssertTrue(lString.length <=> _2)
    criojoAssertEquals(lString, WrapScalaList(_foo :: _foo :: Nil))
    criojoAssertTrue(lBool.forall(b => b))
    criojoAssertTrue(lBool.length <=> _2)
    criojoAssertEquals(lBool, WrapScalaList(_true :: _true :: Nil))
  }

  @Test def testContains() {
    val _4 = WrapScalaInt(4)
    val _foobar = WrapScalaString("foobar")

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
    val _4 = WrapScalaInt(4)

    val llInt = _1 :: lInt
    val llColonColon = _1 :: lColonColon

    criojoAssertTrue(lInt.length <=> _3)
    criojoAssertTrue(llInt.length <=> _4)
    criojoAssertTrue(lColonColon.length <=> _3)
    criojoAssertTrue(llColonColon.length <=> _4)
  }

  @Test def testSize() {
    val _4 = WrapScalaInt(4)

    val llInt = _1 :: lInt
    val llColonColon = _1 :: lColonColon

    criojoAssertTrue(lInt.size <=> _3)
    criojoAssertTrue(llInt.size <=> _4)
    criojoAssertTrue(lColonColon.size <=> _3)
    criojoAssertTrue(llColonColon.size <=> _4)
  }

  @Test def testChamLength() {
    val cham = new Cham with TestCham {
      override def name = "testChamLength"
      val List = LocalRelation("List")
      val l = VarScalaList[ScalaInt]()

      rules(
        List(l) --> AssertTrue(l.length <=> _3)
      )
    }

    cham.introduceMolecule(cham.List(lInt))
    cham.executeRules()
  }

  @Test def testSimplePatternMatching() {
    val cham = new Cham with TestCham {
      override def name = "testSimplePatternMatching"
      val List = LocalRelation("List")
      val tl = VarScalaList[ScalaInt]()
      val hd = VarScalaInt()

      rules(
        List(hd :: tl) --> (AssertTrue(hd <=> _1)
          & AssertTrue(tl.length <=> _2))
      )
    }

    cham.introduceMolecule(cham.List(lInt))
    cham.executeRules()
  }

  @Test def testHardPatternMatching() {
    val _sentinel = WrapScalaString("sentinel")

    val cham = new Cham with TestCham {
      override def name = "testHardPatternMatching"
      val List = LocalRelation("List")
      val tl = VarScalaList[ScalaString]()
      val hd1, hd2 = VarScalaString()

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

    cham.introduceMolecule(cham.List(_foo :: _sentinel :: _bar :: _bar :: WrapScalaNil))
    cham.introduceMolecule(cham.List(_foo :: _bar :: _baz :: _baz :: WrapScalaNil))

    cham.executeRules()
  }

  // ****************************************************************** Utils **
  def criojoAssertEquals[A <: Pattern with Expression](l1: ScalaList[A], l2: ScalaList[A]) {
    assertEquals(l1.reduce(), l2.reduce())
  }

  def criojoAssertTrue(b: ScalaBoolean) {
    assertTrue(b.reduce() == WrapScalaBoolean(true))
  }

  def criojoAssertFalse(b: ScalaBoolean) {
    criojoAssertTrue(!b)
  }
}

