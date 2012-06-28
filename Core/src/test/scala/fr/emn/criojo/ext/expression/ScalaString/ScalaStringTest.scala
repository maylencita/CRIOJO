package fr.emn.criojo.ext.expression.ScalaString

import constructor.WrapScalaString
import org.junit.Test
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt

class ScalaStringTest {
  @Test
  def equalTest {
    val sFoo1 = WrapScalaString("foo")
    val sFoo2 = WrapScalaString("foo")
    val sBar = WrapScalaString("bar")

    assertTrue(sFoo1 <=> sFoo1)
    assertTrue(sFoo1 <=> sFoo2)
    assertTrue(sFoo1 !<=> sBar)
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
  def concatTest {
    val sFoo = WrapScalaString("foo")
    val sBar = WrapScalaString("bar")
    val sFooBar = WrapScalaString("foobar")

    assertTrue((sFoo + sBar) <=> sFooBar)
    assertTrue((sFoo + sFoo) !<=> sFooBar)
  }

  def assertTrue(b: ScalaBoolean) {
    assert(b.reduce() == WrapScalaBoolean(true))
  }
}
