package fr.emn.criojo.core


trait Variable extends Pattern {
}

trait Var[ T<: Pattern] extends Variable {

  /** Transforms variable in expression using valuation.
   *
   * Applying [[fr.emn.criojo.core.Valuation]] to current variable and transform in
   * [[fr.emn.criojo.core.Expression]]. This mechanism is use to pass from
   * Right Rule Pattern to Expression in solution.
   *
   * @param valuation  Map of VariableName, value, match between Left Rule
   *                   variable and solution value.
   * @return  Expression from variable.
   * @throws  NoValuationForVariable No valuation applicable for this variable.
   */
  override def applyValuation(valuation: Valuation): Expression = {
    var expr: Expression = null

    valuation.forall(v => {
      if (v._1.equals(this))  {
        expr = v._2
        false
      }  else {
        true
      }
    })

    if (expr == null) {
      throw new NoValuationForVariable()
    }

    expr
  }

  /** Matches given expression with variable.
   *
   * It's possible the given expression not match with variable. In this
   * case, an empty valuation is returned.
   *
   * @param expr Expression to try to match.
   * @return If expression match, a valuation from given expression for current
   *         variable. An empty valuation otherwise.
   */
  override def getValuation(expr: Expression): Valuation = expr match {
    case expr: T => Valuation(this -> expr)
    case _ => Valuation()
  }


  override def matches(that:Expression):Boolean = that match {
    case o:T => true
    case _ => false
  }
}

object Undef extends Variable with Expression {
  def matches(that:Term):Boolean = true
}
