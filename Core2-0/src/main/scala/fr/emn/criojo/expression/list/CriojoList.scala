package fr.emn.criojo.expression.list

import fr.emn.criojo.core.model._
import fr.emn.criojo.expression.{CriojoInt, NoValueDefined, CriojoBoolean}
import fr.emn.criojo.expression.scala._

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 1/9/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */

trait CriojoList[+A <: Term] extends Expression {
  // Constructors
  final def ::[B >: A <: Term](elem: B):CriojoList[B] =  CriojoColonColon(elem, this)

  def head:A

  def tail:CriojoList[A]

  def isEmpty:Boolean

//  // Operations
  final def length: CriojoInt = new LengthCriojoList[A](this)

//  final def size = this.length

  final def forall(f: A => CriojoBoolean): CriojoBoolean =  new ForAllCriojoList[A](f, this)

  final def contains(elem: Expression): CriojoBoolean = new ContainsCriojoList[A](elem, this)

//  def value: List[Expression] = reduce() match {
//    case l: WrappedValue[List[A]] => l.self
//    case _ => throw new NoValueDefined()
//  }
}

case object CriojoNil extends CriojoList[Nothing]{
//  override def value = Nil

  val isEmpty = true

  def head: Nothing =
    throw new NoSuchElementException("head of empty list")

  def tail: CriojoList[Nothing] =
    throw new UnsupportedOperationException("tail of empty list")

  override def matches(expr: Expression): Boolean = expr match {
    case CriojoNil => true
    case l:WrappedValue[List[_]] => l == Nil
    case _ => false
  }

  override def getValuation(exp: Expression): Valuation = Valuation()

  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this

  override def toString: String = "CNil" //Nil.toString()
}

case class CriojoColonColon[A <: Term](val head: A, val tail: CriojoList[A]) extends CriojoList[A] with Pattern{

  val isEmpty = false

  override def matches(expr: Expression): Boolean = expr match {
    case wsl: CriojoList[Expression] => //matchesOfWrapCriojoList(wsl)
      head.matches(wsl.head) && tail.matches(wsl.tail)
    case _ => false
  }

  override def getValuation(expr: Expression): Valuation = expr match {
    case wsl: CriojoList[Expression] => //getValuationOfWrapCriojoList(wsl)
      head.getValuation(wsl.head).union(tail.getValuation(wsl.tail))
    case _ => Valuation()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val hdExpr = head.applyValuation(valuation).asInstanceOf[A]
    val tlExpr = tail.applyValuation(valuation).asInstanceOf[CriojoList[A]]

    new CriojoColonColon[A](hdExpr, tlExpr)
  }

  /** Reduces the current list to the minimal expression.
    * The result should be a List of WrappedValue[_]
    * */
  override def reduce(): Expression = {
    val hd:Expression = head.reduce() match{
      case expr:Expression => expr
      case _ => throw new NoValueDefined()
    }
    val tl = tail.reduce() match{
      case lst:CriojoList[Expression] => lst
      case _ => throw new NoValueDefined()
    }

    CriojoColonColon(hd,tl)
  }
}

case class ContainsCriojoList[A <: Term](elem: Expression,
                                         l: CriojoList[A]) extends ScalaBoolean {

  override def applyValuation(valuation: Valuation): Expression = {
    val lExpr = l.applyValuation(valuation).asInstanceOf[CriojoList[A]]
    val elemExpr = elem.applyValuation(valuation)

    lExpr.contains(elemExpr)
  }

  override def reduce(): Expression = {
    var these = l
    while (!these.isEmpty) {
      if (these.head.reduce() == elem) return WrapScalaBoolean(true)
      these = these.tail
    }
    WrapScalaBoolean(false)
  }
}

case class ForAllCriojoList[A <: Term](f: (A) => CriojoBoolean,
                                       l: CriojoList[A]) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr = l.applyValuation(valuation).asInstanceOf[CriojoList[A]]

    expr.forall(f)
  }

  override def reduce(): Expression = {
    // Scala List requires Boolean and not CriojoBoolean so a composed function is required:
    //   * f: A => ScalaBoolean
    //   * g: ScalaBoolean => Boolean
    //   * g o f
    def g(b: CriojoBoolean): Boolean = b.reduce() match {
      case _b:WrappedValue[Boolean] => _b.self
      case _ => throw new NoValueDefined()
    }

    def reduceTerm(term:A):A = term.reduce() match {
      case a:A => a
      case _ => throw new NoValueDefined()
    }

    var these:CriojoList[A] = l

    while (!these.isEmpty) {
      if (!g(f(reduceTerm(these.head)))) return WrapScalaBoolean(false)
      these = these.tail
    }
    WrapScalaBoolean(true)

  }
}

case class LengthCriojoList[A <: Term](l: CriojoList[A])
  extends ScalaInt {

  override def applyValuation(valuation: Valuation): Expression = {
    l.applyValuation(valuation).asInstanceOf[CriojoList[A]].length
  }

  override def reduce(): Expression = {
    var these = l
    var len = 0
    while (!these.isEmpty) {
      len += 1
      these = these.tail
    }
    WrapScalaInt(len)
  }

}
