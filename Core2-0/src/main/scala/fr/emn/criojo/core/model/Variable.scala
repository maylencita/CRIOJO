package fr.emn.criojo.core.model

trait Variable extends Pattern {
  /** Variable name (only for debug) */
  val name: String
}

/** Parametric Variable Definition.
  *
  * == Overview ==
  * To use a variable, it's necessary to redefined it and extends en concrete
  * pattern. As you could have static typing benefits (on operation). For
  * example to construct variable on CriojoInt do as :
  * {{{
  *                                            +-+
  *  +---------+                        +------|T|-+
  *  | Pattern |                        | TypedVar  +-+ |
  *  +---------+                        +----------+
  *       ^                                   ^
  *       |                                   |
  *       |                                   |
  *       |                              +--------+
  *  +----------+         +--------------|CriojoInt|-+
  *  | CriojoInt | <|----- | IntVar  +--------+ |
  *  +----------+         +-------------------------+
  * }}}
  *
  * @constructor  Create a new variable of specific type with a given name.
  * @param  n The variable's name.
  */
abstract class TypedVar[+T <: Expression](private val n: String) extends Variable {
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

  /** Transforms variable in expression using a valuation.
    *
    * Applying [[fr.emn.criojo.core.model.Valuation]] to current variable and
    * transform in [[fr.emn.criojo.core.model.Expression]]. This mechanism is
    * use to pass from Right Rule Pattern to Expression in solution.
    *
    * @param valuation  Map of VariableName, value, match between Left Rule
    *        variable and solution value.
    * @return Expression from variable.
    */
  @throws(classOf[NoValuationForVariable])
  override def applyValuation(valuation: Valuation): Expression = {
    valuation.apply(this) match {
      case Some(expr) => expr
      case _ => throw new NoValuationForVariable(this)
    }
  }
}

