package fr.emn.criojo.ext.expression.ScalaList

import org.junit.Test

import constructor.WrapScalaList
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaString.constructor.WrapScalaString
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean

class ScalaListTest {
  @Test
  def forAllTest {
    val lInt = WrapScalaList(WrapScalaInt(1) :: WrapScalaInt(2) ::
        WrapScalaInt(3) :: Nil)
    val lString = WrapScalaList(WrapScalaString("foo") ::
        WrapScalaString("bar") :: Nil)
    val lBool = WrapScalaList(WrapScalaBoolean(true) ::
        WrapScalaBoolean(true) :: Nil)

    assertTrue(lInt.forall(i => i > WrapScalaInt(0)))
    assertTrue(lString.forall(s => s.length > WrapScalaInt(0)))
    assertTrue(lBool.forall(b => b))
  }

  def assertTrue(b: ScalaBoolean) {
    assert(b.reduce() == WrapScalaBoolean(true))
  }
}

