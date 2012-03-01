package fr.emn.criojo.ext.expressions

import fr.emn.criojo.core.Criojo._
import fr.emn.criojo.core.{Variable, ValueTerm, Term}

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 29/02/12
 * Time: 21:33
 */
trait Expression extends Term {

  def eval(): Expression

  //TODO This is syntactic sugar: move to package lang

  //  def + (that:Expression):Expression = new BinaryExpression("+", this, that)
  //  def - (that:Expression):Expression = new BinaryExpression("-", this, that)
  //  def * (that:Expression):Expression = new BinaryExpression("*", this, that)
  //  def / (that:Expression):Expression = new BinaryExpression("/", this, that)
  //  //def == (that:Expression):Expression = new BinaryExpression("==", this, that)
  //  def > (that:Expression):Expression = new BinaryExpression(">", this, that)
  //  def < (that:Expression):Expression = new BinaryExpression("<", this, that)

}

trait TerminalExpr extends Expression {
  def eval(): Expression = this
}

class VarExpression(name: String) extends Variable(name) with TerminalExpr

object UndefinedExpression extends TerminalExpr {
  val name = "_"
  //TODO Should throw an exception??
  def applyValuation(valuation: Valuation): Term = this
  def matches(that:Term):Boolean = false
}