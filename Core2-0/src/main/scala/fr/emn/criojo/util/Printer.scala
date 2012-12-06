package fr.emn.criojo.util

import fr.emn.criojo.core.engine.impure.{NativeAtom, NativeRelation}
import fr.emn.criojo.core.model._
import fr.emn.criojo.core.model.relation.Relation
import scala.Some

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 12/4/12
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
trait Printer {
  val Println:Relation = new PrinterRel("$criojo.Println",println)

  val Print:Relation = new PrinterRel("$criojo.Print", print)

  private def indexOfPlaceHolder(input:String, start:Int):Option[Int] = {
    println("[indexOfPlaceHolder] input=\""+input+"\" start="+start)
    if(start < input.length){
      input indexOf("$", start) match{
        case index if index >= 0 =>
          Some(index)
        case _ => None
      }
    }else{
      None
    }
  }

  private def getPlaceHolder(input:String, start:Int):String = {
    val lastIndex = input.indexOf(" ",start) match{
      case i if i > 0 => i
      case _ => input.length
    }
    input substring(start+1, lastIndex)
  }

  private def replacePlaceHolders(input:String, values: Map[String,String]): String = {
println("[replacePlaceHolders] values: " + values + "; input: "+input)

    val sb = new StringBuilder(input.length)

    def recurse(start:Int):String = if(start < input.length){
      indexOfPlaceHolder(input, start) match{
        case Some(index) =>
          val placeHolder = getPlaceHolder(input, index)
println("[replacePlaceHolders] placeHolder= " + placeHolder+" length="+placeHolder.length)
          sb.append(input subSequence (start, index))
          sb.append(values.getOrElse(placeHolder, "$"+placeHolder))
          recurse(index + placeHolder.length + 1)
        case _ => sb.toString()
      }
    } else{
println("[replacePlaceHolders] start="+start)
      sb.toString()
    }

    recurse(0)
  }

  private def parseAndPrint(terms:List[Term], fprint:(Any => Unit)=print):List[Atom] =
    terms match{
      case s::tlst =>
        val replacingMap = tlst.foldLeft(Map[String,String]()){
          case (map, t:MappedTerm) => map + Pair(t.variable.name, t.value.toString)
          case (map, t) if !t.isInstanceOf[MappedTerm] => map + Pair(t.toString, t.toString)
        }
        fprint(replacePlaceHolders(s.toString, replacingMap))
        List[Atom]()
      case _ => List[Atom]()
  }

  private class PrinterRel(val name:String, fprint:(Any => Unit)) extends Relation{
    override def newAtom(terms: List[Term]) = new PrinterAtom(this, terms, fprint)
  }

  private class PrinterAtom (val relation:Relation, val terms:List[Term], fprint:(Any => Unit)) extends NativeAtom{

    def apply = parseAndPrint(terms,fprint)

    override def applyValuation(valuation:Valuation):Atom = {
      this.relation.newAtom(
        terms.map({
          case pattern:Variable => new MappedTerm(pattern, pattern.applyValuation(valuation).reduce())
          case t => t.applyValuation(valuation)
        }))
    }
  }

  private class MappedTerm(val variable:Variable, val value:Expression) extends Term{}

}
