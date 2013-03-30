package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model._
import fr.emn.criojo.expression.list.{CriojoList,CriojoNil}

case class WrapScalaList[A <: Expression](l: List[A])
  extends CriojoList[A] with Expression with WrappedValue[List[A]]{

  /**
   * Caution, this method is applicable only when WrapScalaList is an expression
   * and not a Pattern.
   */
  def head: A = l.head

  /**
   * Caution, this method is applicable only when WrapScalaList is an expression
   * and not a Pattern.
   */
  def tail =
    new WrapScalaList[A](l.tail)

  def isEmpty = l.isEmpty

  /*override*/ def value = l
  val self = l

  /**
   * With type ScalaList, only WrapScalaList or WrapScalaNil could be in
   * solution, because WrapScalaColonColon is reduce as WrapScalaList.
   * So matches no needs to test match with WrapScalaColonColon.
   */
  override def matches(expr: Expression): Boolean = expr match {
    case CriojoNil => l == Nil
    case wsl: WrapScalaList[A] => l == wsl.value
    case _ => false
  }

  override def getValuation(exp: Expression): Valuation = Valuation()

  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this

  override def toString: String = l.toString()
}

/*
final case class WrapScalaColonColon[A <: Pattern with Expression](hd: A,
                                                                   tl: ScalaList[A]) extends ScalaList[A] {


  /**
   * Matches is done on element in solution. These elements are Expression, so
   * only WrapScalaList, WrapScalaNil & WrapScalaColonColon are possible with
   * no variables.
   *
   * But the reduce of WrapScalaColonColon produces a WrapScalaList,
   * so WrapScalaColonColon could not be in solution. This
   * will largely simplify the match algorithm.
   *
   */
  override def matches(expr: Expression): Boolean = expr match {
    case wsl: WrapScalaList[A] => matchesOfWrapScalaList(wsl)
    case _ => false
  }


  /**
   * GetValuation is done on element in solution (already matches). These
   * elements are Expression, so only WrapScalaList, WrapScalaColonColon &
   * WrapScalaNil are possible with no variables.
   *
   * But the reduce of WrapScalaColonColon produces a WrapScalaList. So Expression
   * is obviously one of WrapScalaList or WrapScalaNil but not
   * WrapScalaColonColon.
   *
   * Moreover, a WrapScalaNil is an empty list, so it's could never be matched.
   */
  override def getValuation(expr: Expression): Valuation = expr match {
    case wsl: WrapScalaList[A] => getValuationOfWrapScalaList(wsl)
    case _ => Valuation()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val hdExpr = hd.applyValuation(valuation).asInstanceOf[A]
    val tlExpr = tl.applyValuation(valuation).asInstanceOf[ScalaList[A]]

    new WrapScalaColonColon[A](hdExpr, tlExpr)
  }

  /** Reduces the current constructor to a WrapScalaList constructor */
  override def reduce(): Expression = WrapScalaList(value)

  /** Returns wrapped list */
  override def value: List[A] = {
    val head: A = hd match {
      case v: Variable => throw new NoValuationForVariable(v)
      case _ => hd
    }

    val tail: List[A] = tl match {
      case WrapScalaNil => Nil
      case wsl: WrapScalaList[A] => wsl.getValue
      case wscc: WrapScalaColonColon[A] => wscc.getValue
      case v: Variable => throw new NoValuationForVariable(v)
      case _ => Nil
    }

    head :: tail
  }

  override def toString: String = value.toString()

  // ************************************************** Recursive Algorithmes **

  /**
   * WrapScalaList is in solution, it's an Expression
   *
   * WrapScalaList is for sure an Expression, so the value l of WrapScalaList
   * is for sure an Scala List. The result of matches depend on head and tail
   * of WrapScalaColonColon.
   */
  def matchesOfWrapScalaList(wsl: WrapScalaList[A]): Boolean = {
    val headCheck: Boolean = hd match {
      case hdVar: TypedVar[A] => hdVar.matches(wsl.head)
      case _ => hd == wsl.head
    }

    val tailCheck: Boolean = tl match {
      case tlWsl: WrapScalaList[A] => tlWsl.matches(wsl.tail)
      case WrapScalaNil => WrapScalaNil.matches(wsl.tail)
      case tlVar: VarScalaList[A] => tlVar.matches(wsl.tail)
      case tlWscc: WrapScalaColonColon[A] =>
        tlWscc.matchesOfWrapScalaList(wsl.tail)
      case _ => false
    }

    headCheck && tailCheck
  }

  def getValuationOfWrapScalaList(wsl: WrapScalaList[A]): Valuation = {
    val headVals: Valuation = hd match {
      case hdVar: TypedVar[A] => Valuation(hdVar -> wsl.head)
      case _ => Valuation()
    }

    val tailVals: Valuation = tl match {
      case WrapScalaNil => WrapScalaNil.getValuation(wsl.tail)
      case tlWsl: WrapScalaList[A] => tlWsl.getValuation(wsl.tail)
      case tlWscc: WrapScalaColonColon[A] =>
        tlWscc.getValuationOfWrapScalaList(wsl.tail)
      case tlVar: VarScalaList[A] => Valuation(tlVar -> wsl.tail)
      case _ => Valuation()
    }

    headVals.union(tailVals)
  }
}
*/

