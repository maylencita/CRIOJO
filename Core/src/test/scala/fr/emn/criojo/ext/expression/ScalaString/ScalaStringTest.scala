package fr.emn.criojo.ext.expression.ScalaString

import constructor.WrapScalaString
import org.junit.Test
import fr.emn.criojo.ext.expression.ScalaBoolean.{VarScalaBoolean, ScalaBoolean}
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.core.factory.DefaultFactory

class ScalaStringTest {
  // ****************************************************************** Tests **
  @Test
  def equalTest {
    val sFoo1 = WrapScalaString("foo")
    val sFoo2 = WrapScalaString("foo")
    val sBar = WrapScalaString("bar")

    assertTrue(sFoo1 <=> sFoo1)
    assertTrue(sFoo1 <=> sFoo2)
    assertTrue(!(sFoo1 <=> sBar))
  }

  @Test
  def notEqualTest {
    val sFoo1 = WrapScalaString("foo")
    val sFoo2 = WrapScalaString("foo")
    val sBar = WrapScalaString("bar")

    assertTrue(sFoo1 !<=> sBar)
    assertTrue(!(sFoo1 !<=> sFoo1))
    assertTrue(!(sFoo1 !<=> sFoo2))
  }

  @Test
  def lengthTest {
    val sFoo = WrapScalaString("foo")
    val sEmpty = WrapScalaString("")

    assertTrue(sFoo.length > WrapScalaInt(0))
    assertTrue(sFoo.length >= WrapScalaInt(0))
    assertTrue(sFoo.length !<=> WrapScalaInt(0))
    assertTrue(sEmpty.length <= WrapScalaInt(0))
    assertTrue(sEmpty.length <=> WrapScalaInt(0))
  }

  @Test
  def emptyTest {
    val sEmpty = WrapScalaString("")
    val sFoo = WrapScalaString("foo")

    assertTrue(sEmpty.isEmpty)
    assertTrue(!sFoo.isEmpty)
  }

  @Test
  def concatTest {
    val sFoo = WrapScalaString("foo")
    val sBar = WrapScalaString("bar")
    val sFooBar = WrapScalaString("foobar")

    assertTrue((sFoo + sBar) <=> sFooBar)
    assertTrue((sFoo + sFoo) !<=> sFooBar)
  }

  @Test
  def chamEqualTest {
    val cham = new Cham with StringTestCham {
      val A = LocalRelation("A")
      val B = LocalRelation("B")
      val C = LocalRelation("C")
      val D = LocalRelation("D")

      val x, y = VarScalaString()

      rules(
        (A(x) & B(y)) --> AssertTrue(x <=> y)
        , (C(x) & D(y)) --> AssertTrue(x !<=> y)
      )
    }

    cham.introduceMolecule(cham.A(WrapScalaString("foo")))
    cham.introduceMolecule(cham.B(WrapScalaString("foo")))
    cham.introduceMolecule(cham.C(WrapScalaString("foo")))
    cham.introduceMolecule(cham.D(WrapScalaString("bar")))
    cham.executeRules()
  }

  @Test
  def chamLenghtTest {
    val cham = new Cham with StringTestCham {
      val A = LocalRelation("A")
      val B = LocalRelation("B")

      val x = VarScalaString()

      rules (
        A(x) --> AssertTrue(x.length <=> WrapScalaInt(0))
        , B(x) --> AssertTrue(x.length <=> WrapScalaInt(3))
      )
    }

    cham.introduceMolecule(cham.A(WrapScalaString("")))
    cham.introduceMolecule(cham.B(WrapScalaString("foo")))
    cham.executeRules()
  }

  @Test
  def chamEmptyTest {
    val cham = new Cham with StringTestCham {
      val Empty = LocalRelation("Empty")
      val NotEmpty = LocalRelation("NotEmpty")

      val x = VarScalaString()

      rules (
        Empty(x) --> AssertTrue(x.isEmpty)
        , NotEmpty(x) --> AssertTrue(!x.isEmpty)
      )
    }

    cham.introduceMolecule(cham.Empty(WrapScalaString("")))
    cham.introduceMolecule(cham.NotEmpty(WrapScalaString("foo")))
    cham.executeRules()
  }

  @Test
  def chamConcatTest {
    val cham = new Cham with StringTestCham {
      val Foo = LocalRelation("A")
      val Bar = LocalRelation("B")

      val x, y = VarScalaString()
      val fooBar = WrapScalaString("foobar")

      rules (
        (Foo(x) & Bar(y)) --> AssertTrue((x + y) <=> fooBar)
        , (Foo(x) & Foo(y)) --> AssertTrue((x + y) !<=> fooBar)
      )
    }

    cham.introduceMolecule(cham.Foo(WrapScalaString("foo")))
    cham.introduceMolecule(cham.Foo(WrapScalaString("foo")))
    cham.introduceMolecule(cham.Foo(WrapScalaString("foo")))
    cham.introduceMolecule(cham.Bar(WrapScalaString("bar")))
    cham.executeRules()
  }

  // ****************************************************************** Utils **
  trait StringTestCham extends Cham with DefaultFactory {
    val AssertTrue = NativeRel {
      case (wb: WrapScalaBoolean)::Nil => assertTrue(wb)
      case _ => assert(false)
    }
  }

  def assertTrue(b: ScalaBoolean) {
    assert(b.reduce() == WrapScalaBoolean(true))
  }
}
