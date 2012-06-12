package fr.emn.criojo.ext.expression.Relation

import fr.emn.criojo.core.datatype.{NoValuationForVariable, Valuation, Expression, Pattern}


class ChannelLocation(val url:String) extends Pattern with Expression {

  override def matches(exp: Expression): Boolean = exp match {
    case cl:ChannelLocation => cl.url.equals(url)
    case _ => false
  }

  override def getValuation(expr: Expression): Valuation = Valuation()

  @throws(classOf[NoValuationForVariable])
  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this
  
  override def toString:String = url
}
