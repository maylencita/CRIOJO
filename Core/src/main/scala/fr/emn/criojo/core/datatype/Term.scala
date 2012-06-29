package fr.emn.criojo.core.datatype

/** Term is part of an [[fr.emn.criojo.core.Atom]].
  *
  * Term defined the atom variable interface. Two concrete implementation are
  * done in Criojo :
  * $ - [[fr.emn.criojo.core.datatype.Pattern]] for terms in Atom in Rule.
  * $ - [[fr.emn.criojo.core.datatype.Expression]] for terms in Atom in Solution.
  */
trait Term {

  /** Test if current matches with an expression.
    *
    * The matches test is call during rules execution. At each new atoms add in
    * the solution. The Chemical Machine will test if
    * [[fr.emn.criojo.core.datatype.Pattern]] match with
    * [[fr.emn.criojo.core.datatype.Expression]]. The test is only implemented
    * in [[fr.emn.criojo.core.datatype.Pattern]] terms.
    *
    * This method could only be called from a pattern. Never from a expression.
    *
    * @param exp The expression to test matches with pattern.
    * @return <code>true</code> if pattern matches with expression.
    *         <code>false</code> otherwise.
    */
  def matches(exp: Expression): Boolean =
    throw new ProhibitedOperation

  /** Get valuation from an expression.
    *
    * If pattern is a [[fr.emn.criojo.core.datatype.Variable]], the variable may
    * have valuation from [[fr.emn.criojo.core.datatype.Expression]] in
    * Solution.
    *
    * This method could only be called from a pattern. Never from a expression.
    *
    * @param  exp The expression to get valuation.
    * @return Valuation for current pattern if the expression matches with. An
    *         empty valuation otherwise.
    */
  def getValuation(exp: Expression): Valuation =
    throw new ProhibitedOperation

  /** Transform the current pattern in expression.
    *
    * Transform the pattern in expression. If pattern is a
    * [[fr.emn.criojo.core.datatype.Variable]], valuation is used to set a
    * value to the variable.
    *
    * This method could only be called from a pattern. Never from a expression.
    *
    * @param valuation  Valuation to set variable value (if any).
    * @return An expression from current pattern.
    */
  def applyValuation(valuation: Valuation): Expression =
    throw new ProhibitedOperation

  /** Reduce an expression.
    *
    * Reduce the expression to only have constructor in. If expression are
    * operation, the reduce do the operation.
    *
    * This method could only be called from a expression. Never from a pattern.
    *
    * @return The expression with only constructor (no operation).
    */
  def reduce(): Expression =
    throw new ProhibitedOperation
}
