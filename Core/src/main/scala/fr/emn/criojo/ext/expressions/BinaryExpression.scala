package fr.emn.criojo.ext.expressions

import fr.emn.criojo.core.Term
import fr.emn.criojo.core.Criojo.Valuation

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 01/03/12
 * Time: 00:22
 */

// A generic binary expression allows to create custom binary expressions that extend BinaryExpression.
// See SumExpr below
abstract class BinaryExpression extends Expression {
  def exp1: Expression
  def exp2: Expression

  def matches(that: Term) = that match {
    case bExp: BinaryExpression if (this.exp1.matches(bExp.exp1) && (this.exp2.matches(bExp.exp2))) => true
    case _ => false
  }
}

//TODO Create SubExpr, DivExpr...
class SumExpr(val exp1: Expression, val exp2: Expression) extends BinaryExpression {
  def name = "+"

  //If exp1 or exp2 are not well typed this should not work,
  //and that is Ok
  def eval(): Expression = (exp1.eval(), exp2.eval()) match {
    case (v1: ValueExpression[_], v2: ValueExpression[_]) => v1 + v2
    case _ => UndefinedExpression
  }

  def applyValuation(valuation: Valuation): Term = {
    (exp1.applyValuation(valuation), exp2.applyValuation(valuation)) match {
      case (e1: Expression, e2: Expression) => new SumExpr(e1, e2)
      case _ => UndefinedExpression
    }
  }
}
