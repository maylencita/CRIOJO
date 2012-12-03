package fr.emn.criojo.core.model

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/27/12
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
package object relation {

  import java.util.UUID

  trait Relation {
    def name: String

    def newAtom(terms: List[Term]): Atom

    def apply(terms: Term*): Atom = {
      newAtom(terms.toList)
    }

    override def toString = name
  }

  trait Channel extends Relation {
    def location: ChannelLocation

//    def this(name: String) = this(name, new ChannelLocation(name))

    def newAtom(tlst: List[Term]): Atom = new RemoteMessage(this) {
      val terms = tlst
      val destination = location
    }
  }

  abstract class LocalRelation(val name: String) extends Relation {
    def newAtom(tlst: List[Term]) = new LocalMessage(this) {
      val terms = tlst
    }
  }

  abstract class OutChannel(val name: String, val location: ChannelLocation) extends Channel {}

  abstract class InChannel(val name:String, val location:ChannelLocation) extends Channel{}

  /** Channel Variable */
  case class VarChannel(n: String) extends TypedVar[ChannelLocation](n)
  with Relation {

    def newAtom(tlst:List[Term]):Atom = new RemoteMessgVar(this, tlst)

    private class RemoteMessgVar(val relation:Relation, val terms:List[Term]) extends Atom{
      /**
       * Returns a new RemoteMessage
       * @param valuation
       * @return
       */
      override def applyValuation(valuation:Valuation):Atom = {
        valuation.apply(relation.asInstanceOf[Variable]) match{
          case Some(loc:ChannelLocation) =>
            new Channel {
              val name = loc.relName
              val location = loc
            }.newAtom(
              terms.map((pattern => pattern.applyValuation(valuation).reduce()))
            )
          case _ => throw new NoValuationForVariable(relation.asInstanceOf[Variable])
        }
      }
    }
  }

  class ChannelLocation(val url: String,val relName:String) extends Pattern with Expression {

    override def matches(exp: Expression): Boolean = exp match {
      case cl: ChannelLocation => cl.url.equals(url)
      case _ => false
    }

    override def getValuation(expr: Expression): Valuation = Valuation()

    override def applyValuation(valuation: Valuation): Expression = this

    override def reduce(): Expression = this

    override def toString: String = url
  }

}