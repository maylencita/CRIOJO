package fr.emn.criojo.ext.expressions

import fr.emn.criojo.core.{PatternNotMatchingException, Term, Valuation}


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

  override def toString:String = eval().toString
}

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

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = t match {
    case p:SumExpr => t1.getValuation(p.t1).union(t2.getValuation(p.t2))
    case _ => {
      throw new PatternNotMatchingException()
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

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = t match {
    case p:SubExpr => t1.getValuation(p.t1).union(t2.getValuation(p.t2))
    case _ => {
      throw new PatternNotMatchingException()
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

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = t match {
    case p:MultExpr => t1.getValuation(p.t1).union(t2.getValuation(p.t2))
    case _ => {
      throw new PatternNotMatchingException()
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

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = t match {
    case p:DivExpr => t1.getValuation(p.t1).union(t2.getValuation(p.t2))
    case _ => {
      throw new PatternNotMatchingException()
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

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = t match {
    case p:EqualExpr => t1.getValuation(p.t1).union(t2.getValuation(p.t2))
    case _ => {
      throw new PatternNotMatchingException()
    }
  }
}

class NotEqualExpr(val t1: Term, val t2: Term) extends BinaryExpression {
  def name = "!="

  //If exp1 or exp2 are not well typed this should not work,
  //and that is Ok
  def eval():Expression = (t1, t2) match {
    case (exp1:Expression, exp2:Expression) => {
      (exp1.eval(), exp2.eval()) match {
        case (v1: ValueExpression[_], v2: ValueExpression[_]) => v1.isNotEqual(v2)
        case _ => UndefinedExpression
      }
    }
    case _ => UndefinedExpression
  }

  def applyValuation(valuation: Valuation): Term = {
    (t1.applyValuation(valuation), t2.applyValuation(valuation)) match {
      case (e1: Expression, e2: Expression) => new NotEqualExpr(e1, e2).eval()
      case _ => UndefinedExpression
    }
  }

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = t match {
    case p:EqualExpr => t1.getValuation(p.t1).union(t2.getValuation(p.t2))
    case _ => {
      throw new PatternNotMatchingException()
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

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = t match {
    case p:GreaterThanExpr => t1.getValuation(p.t1).union(t2.getValuation(p.t2))
    case _ => {
      throw new PatternNotMatchingException()
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

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = t match {
    case p:LessThanExpr => t1.getValuation(p.t1).union(t2.getValuation(p.t2))
    case _ => {
      throw new PatternNotMatchingException()
    }
  }
}