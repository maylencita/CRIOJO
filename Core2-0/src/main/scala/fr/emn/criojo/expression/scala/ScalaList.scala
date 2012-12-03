package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{Valuation, Expression, Pattern}
import fr.emn.criojo.expression.NoValueDefined

trait ScalaList[+A <: Pattern with Expression] extends Pattern with Expression {
  // Constructors -- Destructors
  final def ::[B >: A <: Pattern with Expression](elem: B): ScalaList[B] =
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

case class ContainsScalaList[A <: Pattern with Expression](elem: Pattern with Expression,
                                                           l: ScalaList[A]) extends ScalaBoolean {
  override def matches(expr: Expression): Boolean = expr match {
    case ContainsScalaList(e, ll) => e == e && l.matches(ll)
    case _ => false
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val lExpr = l.applyValuation(valuation).asInstanceOf[ScalaList[A]]
    val elemExpr = elem.applyValuation(valuation).asInstanceOf[Pattern with Expression]

    lExpr.contains(elemExpr)
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(l.getValue.contains(elem))
  }
}

case class ForAllScalaList[A <: Pattern with Expression](f: (A) => ScalaBoolean,
                                                         l: ScalaList[A]) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr = l.applyValuation(valuation).asInstanceOf[ScalaList[A]]

    expr.forall(f)
  }

  override def reduce(): Expression = {
    // Scala list required Boolean and not Scala Boolean so :
    //   * f: A => ScalaBoolean
    //   * g: ScalaBoolean => Boolean
    //   * g o f
    def g(b: ScalaBoolean): Boolean = b.getValue

    WrapScalaBoolean(l.getValue.forall(g _ compose f))
  }
}

case class LengthScalaList[A <: Pattern with Expression](l: ScalaList[A])
  extends ScalaInt {
  override def matches(expr: Expression): Boolean = expr match {
    case LengthScalaList(ll) => l.matches(ll)
    case _ => false
  }

  override def applyValuation(valuation: Valuation): Expression = {
    l.applyValuation(valuation).asInstanceOf[ScalaList[A]].length
  }

  override def reduce(): Expression = {
    WrapScalaInt(l.getValue.length)
  }
}
