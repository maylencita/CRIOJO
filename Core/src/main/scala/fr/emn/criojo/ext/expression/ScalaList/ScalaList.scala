package fr.emn.criojo.ext.expression.ScalaList

import fr.emn.criojo.core.datatype.{Term, Expression, Pattern}
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import operation.ForAllScalaList

trait ScalaList[+A <: Pattern with Expression] extends Pattern with Expression {
  def value: List[A] = {
    throw new NoValueDefined()
  }

  final def forall(f: A => ScalaBoolean): ScalaBoolean =
      new ForAllScalaList[A](f, this)

  final def getValue(): List[A] = reduce() match {
    case l: ScalaList[A] => l.value
    case _ => throw new NoValueDefined()
  }
}
