package fr.emn.criojo.ext.expressions

import fr.emn.criojo.lang.{ComposableWithBinaryExpression}
import fr.emn.criojo.lang.CompositionOperators
import fr.emn.criojo.core.{PatternNotMatchingException, Variable, Term, Valuation}
import fr.emn.criojo.ext.expressions.{Expression, UndefinedExpression}


/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 29/02/12
 * Time: 21:33
 */
trait Expression extends Term with ComposableWithBinaryExpression with CompositionOperators {

  def eval():Expression

  def eval(valuation:Valuation):Expression = {
    this.applyValuation(valuation) match {
      case e:Expression => e.eval()
      case _ => UndefinedExpression
    }
  }
}

trait TerminalExpr extends Expression {
  override def eval():Expression = this
}

object UndefinedExpression extends TerminalExpr {
  val name = "_"
  //TODO Should throw an exception??
  def applyValuation(valuation: Valuation): Term = this

  //Undef matches everything
  def matches(that:Term):Boolean = true

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = Valuation()
}