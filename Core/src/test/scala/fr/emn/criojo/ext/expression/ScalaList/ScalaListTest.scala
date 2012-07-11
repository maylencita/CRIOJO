package fr.emn.criojo.ext.expression.ScalaList

import org.junit.Test
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

class ScalaListTest {
  @Test
  def forAllTest() {
    val lInt = WrapScalaList(WrapScalaInt(1) :: WrapScalaInt(2) ::
      WrapScalaInt(3) :: Nil)
    val lString = WrapScalaList(WrapScalaString("foo") ::
      WrapScalaString("bar") :: Nil)
    val lBool = WrapScalaList(WrapScalaBoolean(true) ::
      WrapScalaBoolean(true) :: Nil)

    criojoAssertTrue(lInt.forall(i => i > WrapScalaInt(0)))
    criojoAssertTrue(lString.forall(s => s.length > WrapScalaInt(0)))
    criojoAssertTrue(lBool.forall(b => b))
  }

  @Test
  def colonColonTest() {
    val lInt = WrapScalaInt(42) :: WrapScalaInt(42) :: WrapScalaNil
    val lString = WrapScalaString("foo") :: WrapScalaString("foo") :: WrapScalaNil
    val lBool = WrapScalaBoolean(true) :: WrapScalaBoolean(true) :: WrapScalaNil

    criojoAssertTrue(lInt.forall(i => i <=> WrapScalaInt(42)))
    criojoAssertTrue(lInt.length <=> WrapScalaInt(2))
    criojoAssertTrue(lString.forall(s => s <=> WrapScalaString("foo")))
    criojoAssertTrue(lString.length <=> WrapScalaInt(2))
    criojoAssertTrue(lBool.forall(b => b))
    criojoAssertTrue(lBool.length <=> WrapScalaInt(2))
  }

  @Test
  def containsTest() {
    val lInt = WrapScalaInt(1) :: WrapScalaInt(2) ::
      WrapScalaInt(3) :: WrapScalaNil
    val lString = WrapScalaString("foo") ::
      WrapScalaString("bar") :: WrapScalaNil
    val lBool = WrapScalaBoolean(true) ::
      WrapScalaBoolean(true) :: WrapScalaNil
    val lPrepend = lInt :: lInt

    criojoAssertTrue(lInt.contains(WrapScalaInt(3)))
    criojoAssertTrue(!lInt.contains(WrapScalaInt(4)))
    criojoAssertTrue(lString.contains(WrapScalaString("foo")))
    criojoAssertTrue(!lString.contains(WrapScalaString("foobar")))
    criojoAssertTrue(lBool.contains(WrapScalaBoolean(true)))
    criojoAssertTrue(!lBool.contains(WrapScalaBoolean(false)))
    criojoAssertTrue(lPrepend.contains(lInt))
  }

  @Test
  def lengthTest() {
    val l1 = WrapScalaList(WrapScalaInt(1) :: Nil)
    val l2 = WrapScalaList(WrapScalaInt(1) :: WrapScalaInt(2) :: Nil)
    val l3 = WrapScalaInt(1) :: l2

    criojoAssertTrue(l1.length <=> WrapScalaInt(1))
    criojoAssertTrue(l2.length <=> WrapScalaInt(2))
    criojoAssertTrue(l3.length <=> WrapScalaInt(3))
  }

  @Test
  def sizeTest() {
    val l1 = WrapScalaList(WrapScalaInt(1) :: Nil)
    val l2 = WrapScalaList(WrapScalaInt(1) :: WrapScalaInt(2) :: Nil)
    val l3 = WrapScalaInt(1) :: l2

    criojoAssertTrue(l1.size <=> WrapScalaInt(1))
    criojoAssertTrue(l2.size <=> WrapScalaInt(2))
    criojoAssertTrue(l3.size <=> WrapScalaInt(3))
  }

  @Test
  def chamLengthTest() {
    val cham = new Cham with TestCham {
      override def name = "chamLengthTest"
      val ListInt = LocalRelation("ListInt")
      val x, y = VarScalaList[ScalaInt]()

      rules(
        (ListInt(x) & ListInt(y)) -->
            AssertTrue((x.length + y.length) <=> (x :: y).length)
      )
    }

    cham.introduceMolecule(cham.ListInt(WrapScalaInt(1) :: WrapScalaNil))
    cham.introduceMolecule(cham.ListInt(WrapScalaInt(2) :: WrapScalaNil))
    cham.executeRules()
  }

  @Test
  def chamHeadTest() {
    val cham = new Cham with TestCham {
      override def name = "chamHeadTest"
      val List = LocalRelation("ListInt")
      val tl = VarScalaList[ScalaInt]()
      val hd = VarScalaInt()

      rules(
        List(hd :: tl) --> (AssertTrue(hd <=> WrapScalaInt(1))
          & AssertTrue(tl.length >= WrapScalaInt(0)))
      )
    }

    cham.introduceMolecule(cham.List(WrapScalaInt(1) :: WrapScalaInt(2) ::
        WrapScalaNil))
    cham.introduceMolecule(cham.List(WrapScalaInt(1) :: WrapScalaNil))
    cham.executeRules()
  }

  @Test
  def chamHeadHeadTest() {
    val cham = new Cham with TestCham {
      override def name = "chamHeadHeadTest"
      val List = LocalRelation("List")
      val tl = VarScalaList[ScalaString]()
      val hd1, hd2 = VarScalaString()

      rules (
        List(hd1 :: WrapScalaString("sentinel") :: tl) -->
            (AssertTrue(hd1 <=> WrapScalaString("foo"))
            & AssertTrue(tl.forall(s => s <=> WrapScalaString("bar")))
            & AssertTrue(tl.length <=> WrapScalaInt(2)))
        , List(hd1 :: hd2 :: tl) -->
            (AssertTrue(hd1 <=> WrapScalaString("foo"))
            & AssertTrue(hd2 <=> WrapScalaString("bar"))
            & AssertTrue(tl.forall(s => s <=> WrapScalaString("bar")))
            & AssertTrue(tl.length <=> WrapScalaInt(1)))
      )
    }

    cham.introduceMolecule(cham.List(WrapScalaString("foo") ::
        WrapScalaString("sentinel") :: WrapScalaString("bar") ::
        WrapScalaString("bar") :: WrapScalaNil))
    cham.introduceMolecule(cham.List(WrapScalaString("foo") ::
        WrapScalaString("bar") :: WrapScalaString("bar") ::
        WrapScalaNil))

    cham.executeRules()
  }

  // ****************************************************************** Utils **
  def criojoAssertTrue(b: ScalaBoolean) {
    assertTrue(b.reduce() == WrapScalaBoolean(true))
  }

  def criojoAssertFalse(b: ScalaBoolean) {
    criojoAssertTrue(!b)
  }
}

