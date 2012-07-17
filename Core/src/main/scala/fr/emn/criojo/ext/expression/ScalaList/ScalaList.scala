package fr.emn.criojo.ext.expression.ScalaList

import constructor.WrapScalaColonColon
import fr.emn.criojo.core.datatype.{Expression, Pattern}
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.ScalaInt.ScalaInt
import operation._

trait ScalaList[+A <: Pattern with Expression] extends Pattern with Expression {
  // Constructors -- Destructors
  final def ::[B >: A <: Pattern with Expression] (elem: B): ScalaList[B] =
    new WrapScalaColonColon[B](elem, this)

  // Operations
  final def length: ScalaInt = new LengthScalaList[A](this)

  final def size: ScalaInt = this.length

  final def forall(f: A => ScalaBoolean): ScalaBoolean =
    new ForAllScalaList[A](f, this)

  final def contains(elem: Pattern with Expression): ScalaBoolean =
    new ContainsScalaList[A](elem, this)

  // Wrap
  def value: List[A] = throw new NoValueDefined()

  final def getValue: List[A] = reduce() match {
    case l: ScalaList[A] => l.value
    case _ => throw new NoValueDefined()
  }
}
