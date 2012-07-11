package fr.emn.criojo.ext.expression.ScalaList.constructor

import fr.emn.criojo.core.datatype._
import fr.emn.criojo.ext.expression.ScalaList.{VarScalaList, ScalaList}

final case class WrapScalaColonColon[A <: Pattern with Expression](val hd: A,
    val tl: ScalaList[A]) extends ScalaList[A] {


  /**
   * Matches is done on element in solution. These elements are Expression, so
   * only WrapScalaList, WrapScalaNil & WrapScalaColonColon are possible with
   * no variables.
   *
   * But the reduce of WrapScalaColonColon is done to reduce WrapScalaColonColon
   * to WrapScalaList, so WrapScalaColonColon could not be in solution. This
   * will largely simplified the match algorithme.
   *
   * By the way, a WrapScalaNil is an empty list, so it's could never match with
   * a WrapScalaColonColon which contains, at least, one element.
   */
  override def matches(expr: Expression):Boolean = expr match {
    case wsl: WrapScalaList[A] => matchesOfWrapScalaList(wsl)
    case _ => false
  }


  /**
   * GetValuation is done on element in solution (already matches). These
   * elements are Expression, so only WrapScalaList, WrapScalaColonColon &
   * WrapScalaNil are possible with no variables.
   *
   * But the reduce of WrapScalaColonColon produce a WrapScalaList. So Expression
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

  /** Reduce the current constructor to a WrapScalaList constructor */
  override def reduce(): Expression = WrapScalaList(value)

  /** Return wrapped list */
  override def value: List[A] = {
    val head: A = hd

    val tail: List[A] = tl match {
      case WrapScalaNil => Nil
      case wsl: WrapScalaList[A] => wsl.value
      case wscc: WrapScalaColonColon[A] => wscc.value
      case _ => Nil
    }

    head :: tail
  }

  override def toString :String = value.toString

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
      case hdVar: Variable => hdVar.matches(wsl.head)
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
      case hdVar: Variable => Valuation(hdVar -> wsl.head)
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
