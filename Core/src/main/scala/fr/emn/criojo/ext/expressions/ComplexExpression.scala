package fr.emn.criojo.ext.expressions

import fr.emn.criojo.core.Term
import fr.emn.criojo.core.Valuation

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 3/2/12
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */

abstract class ComplexExpression(val terms:Term*) extends Expression {

  def this(lTermsRepeated:Array[Term]) = this(lTermsRepeated.toSeq:_*)

  def matches(that: Term) = that match {
    case cplxExp:ComplexExpression => {
      terms.corresponds(cplxExp.terms) {_.matches(_)}
    }
    case _ => false
  }
}

case class Min(lTerms:Term*) extends ComplexExpression {
  //implicit def boolean2Term(b:Boolean) = new BooleanValueTerm(b)
  def name = "min"

  //If exp1 or exp2 are not well typed this should not work,
  //and that is Ok

  def eval():Expression = {
    var minExp:Expression = null
    var initialized:Boolean = false

    lTerms.foreach(t => {

      if(!initialized) {
        t match {
          case exp:Expression => {
            minExp = exp
            initialized = true
          }
          case _ =>
        }
      }
      else {
        t match {
          case exp:Expression => {
            (minExp.eval()>exp.eval()).eval() match {
              case BooleanExpression(b) => {
                if(b) {
                  minExp = exp
                }
              }
              case _ =>
            }
          }
          case _ =>
        }
      }
    })

    minExp
  }

  def applyValuation(valuation: Valuation):Term = {

    val valuatedList:Seq[Term] = lTerms.map(t => t.applyValuation(valuation))
    new Min(valuatedList:_*).eval()
  }

  def getValuation(t:Term):Valuation = {
    var valuation:Valuation = Valuation()

    t match {
      case m:Min => lTerms.zip(m.lTerms).foreach(pair => valuation = valuation union pair._1.getValuation(pair._2))
      case _ =>
    }

    valuation
  }
}

case class Max(lTerms:Term*) extends ComplexExpression {
  //implicit def boolean2Term(b:Boolean) = new BooleanValueTerm(b)
  def name = "max"

  def eval():Expression = {
    var maxExp:Expression = null
    var initialized:Boolean = false

    lTerms.foreach(t => {

      if(!initialized) {
        t match {
          case exp:Expression => {
            maxExp = exp
            initialized = true
          }
          case _ =>
        }
      }
      else {
        t match {
          case exp:Expression => {

            (maxExp.eval()<exp.eval()).eval() match {
              case BooleanExpression(b) => {
                if(b) {
                  maxExp = exp
                }
              }
              case _ =>
            }
          }
          case _ =>
        }
      }
    })

    maxExp
  }

  def applyValuation(valuation: Valuation):Term = {

    val valuatedList:Seq[Term] = lTerms.map(t => t.applyValuation(valuation))
    new Max(valuatedList:_*).eval()
  }

  def getValuation(t:Term):Valuation = {
    var valuation:Valuation = Valuation()

    t match {
      case m:Max => lTerms.zip(m.lTerms).foreach(pair => valuation = valuation union pair._1.getValuation(pair._2))
      case _ =>
    }

    valuation
  }
}