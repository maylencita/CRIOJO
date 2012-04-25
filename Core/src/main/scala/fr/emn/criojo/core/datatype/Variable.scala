package fr.emn.criojo.core.datatype

trait Variable extends Pattern {
  /** Variable name (only for debug) */
  val name: String
}

/** Parametric Variable Definition.
  *
  * == Overview ==
  * To use a variable, it's necessary to redefined
  *
  * @constructor  Create a new variable of specific type with a given name.
  * @param  n The variable's name.
  */
abstract class Var[T <: Pattern](private val n: String) extends Variable {
  override val name: String = n

  /** Test if variable matches with an expression.
    *
    * For a variable, test works on type.
    *
    * @param exp The expression to test matches with variable.
    * @return <code>true</code> if variable matches with expression.
    *         <code>false</code> otherwise.
    */
  override def matches(exp: Expression): Boolean = exp match {
    case t: T => true
    case _ => false
  }

  /** Get Valuation from given expression to variable.
    *
    * It's possible the given expression not match with variable. In this
    * case, an empty valuation is returned.
    *
    * @param expr Expression to try to match.
    * @return If expression match, a valuation from given expression for current
    *         variable. An empty valuation otherwise.
    */
  override def getValuation(expr: Expression): Valuation = expr match {
    case t: T => Valuation(this -> expr)
    case _ => Valuation()
  }

  /** Transforms variable in expression using valuation.
    *
    * Applying [[fr.emn.criojo.core.datatype.Valuation]] to current variable and
    * transform in [[fr.emn.criojo.core.datatype.Expression]]. This mechanism is
    * use to pass from Right Rule Pattern to Expression in solution.
    *
    * @param valuation  Map of VariableName, value, match between Left Rule
    *        variable and solution value.
    * @return Expression from variable.
    */
  @throws(classOf[NoValuationForVariable])
  override def applyValuation(valuation: Valuation): Expression = {
    var expr: Expression = null

    valuation.forall(v => {
      if (v._1.equals(this)) {
        expr = v._2.asInstanceOf[Expression]
        false
      } else {
        true
      }
    })

    if (expr == null) {
      throw new NoValuationForVariable(this)
    }

    expr
  }
}

@deprecated ("Find a way to delete Undef")
object Undef extends Variable with Expression {
  override val name = "Undef"
  def matches(that: Term): Boolean = true
}
