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

  val t1:Term
  val t2:Term

  def matches(that: Term) = that match {
    case bExp: BinaryExpression if (this.t1.matches(bExp.t1) && (this.t2.matches(bExp.t2))) => true
    case _ => false
  }

  override def toString():String = eval().toString()
}

//TODO Create SubExpr, DivExpr...
class SumExpr(val t1: Term, val t2: Term) extends BinaryExpression {
  def name = "+"

  //If exp1 or exp2 are not well typed this should not work,
  //and that is Ok
  def eval():Expression = (t1, t2) match {
    case (exp1:Expression, exp2:Expression) => {
      (exp1.eval(), exp2.eval()) match {
        case (v1: ValueExpression[_], v2: ValueExpression[_]) => v1.add(v2)
        case _ => UndefinedExpression
      }
    }
    case _ => UndefinedExpression
  }

  def applyValuation(valuation: Valuation):Term = {
    (t1.applyValuation(valuation), t2.applyValuation(valuation)) match {
      case (e1: Expression, e2: Expression) => new SumExpr(e1, e2).eval()
      case _ => UndefinedExpression
    }
  }
}

class SubExpr(val t1: Term, val t2: Term) extends BinaryExpression {
  def name = "-"

  //If exp1 or exp2 are not well typed this should not work,
  //and that is Ok
  def eval():Expression = (t1, t2) match {
    case (exp1:Expression, exp2:Expression) => {
      (exp1.eval(), exp2.eval()) match {
        case (v1: ValueExpression[_], v2: ValueExpression[_]) => v1.sub(v2)
        case _ => UndefinedExpression
      }
    }
    case _ => UndefinedExpression
  }

  def applyValuation(valuation: Valuation): Term = {
    (t1.applyValuation(valuation), t2.applyValuation(valuation)) match {
      case (e1: Expression, e2: Expression) => new SubExpr(e1, e2).eval()
      case _ => UndefinedExpression
    }
  }
}

class MultExpr(val t1: Term, val t2: Term) extends BinaryExpression {
  def name = "*"

  //If exp1 or exp2 are not well typed this should not work,
  //and that is Ok
  def eval():Expression = (t1, t2) match {
    case (exp1:Expression, exp2:Expression) => {
      (exp1.eval(), exp2.eval()) match {
        case (v1: ValueExpression[_], v2: ValueExpression[_]) => v1.mult(v2)
        case _ => UndefinedExpression
      }
    }
    case _ => UndefinedExpression
  }

  def applyValuation(valuation: Valuation): Term = {
    (t1.applyValuation(valuation), t2.applyValuation(valuation)) match {
      case (e1: Expression, e2: Expression) => new MultExpr(e1, e2).eval()
      case _ => UndefinedExpression
    }
  }
}

class DivExpr(val t1: Term, val t2: Term) extends BinaryExpression {
  def name = "/"

  //If exp1 or exp2 are not well typed this should not work,
  //and that is Ok
  def eval():Expression = (t1, t2) match {
    case (exp1:Expression, exp2:Expression) => {
      (exp1.eval(), exp2.eval()) match {
        case (v1: ValueExpression[_], v2: ValueExpression[_]) => v1.div(v2)
        case _ => UndefinedExpression
      }
    }
    case _ => UndefinedExpression
  }

  def applyValuation(valuation: Valuation): Term = {
    (t1.applyValuation(valuation), t2.applyValuation(valuation)) match {
      case (e1: Expression, e2: Expression) => new DivExpr(e1, e2).eval()
      case _ => UndefinedExpression
    }
  }
}

class EqualExpr(val t1: Term, val t2: Term) extends BinaryExpression {
  def name = "=="

  //If exp1 or exp2 are not well typed this should not work,
  //and that is Ok
  def eval():Expression = (t1, t2) match {
    case (exp1:Expression, exp2:Expression) => {
      (exp1.eval(), exp2.eval()) match {
        case (v1: ValueExpression[_], v2: ValueExpression[_]) => v1.isEqual(v2)
        case _ => UndefinedExpression
      }
    }
    case _ => UndefinedExpression
  }

  def applyValuation(valuation: Valuation): Term = {
    (t1.applyValuation(valuation), t2.applyValuation(valuation)) match {
      case (e1: Expression, e2: Expression) => new EqualExpr(e1, e2).eval()
      case _ => UndefinedExpression
    }
  }
}

class GreaterThanExpr(val t1: Term, val t2: Term) extends BinaryExpression {
  def name = ">"

  //If exp1 or exp2 are not well typed this should not work,
  //and that is Ok
  def eval():Expression = (t1, t2) match {
    case (exp1:Expression, exp2:Expression) => {
      (exp1.eval(), exp2.eval()) match {
        case (v1: ValueExpression[_], v2: ValueExpression[_]) => v1.greaterThan(v2)
        case _ => UndefinedExpression
      }
    }
    case _ => UndefinedExpression
  }

  def applyValuation(valuation: Valuation): Term = {
    (t1.applyValuation(valuation), t2.applyValuation(valuation)) match {
      case (e1: Expression, e2: Expression) => new GreaterThanExpr(e1, e2).eval()
      case _ => UndefinedExpression
    }
  }
}

class LessThanExpr(val t1: Term, val t2: Term) extends BinaryExpression {
  def name = "<"

  //If exp1 or exp2 are not well typed this should not work,
  //and that is Ok
  def eval():Expression = (t1, t2) match {
    case (exp1:Expression, exp2:Expression) => {
      (exp1.eval(), exp2.eval()) match {
        case (v1: ValueExpression[_], v2: ValueExpression[_]) => v1.lessThan(v2)
        case _ => UndefinedExpression
      }
    }
    case _ => UndefinedExpression
  }

  def applyValuation(valuation: Valuation): Term = {
    (t1.applyValuation(valuation), t2.applyValuation(valuation)) match {
      case (e1: Expression, e2: Expression) => new LessThanExpr(e1, e2).eval()
      case _ => UndefinedExpression
    }
  }
}