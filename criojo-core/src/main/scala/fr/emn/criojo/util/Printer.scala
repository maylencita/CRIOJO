package fr.emn.criojo.util

import fr.emn.criojo.core.engine.impure.{NativeAtom, NativeRelation}
import fr.emn.criojo.core.model._
import fr.emn.criojo.core.model.relation.Relation

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 12/4/12
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
trait Printer {
  val Println:Relation = new NativeRelation("$criojo.Println",(tlst:List[Term])=>parseAndPrint(tlst,println))

  val Print:Relation = new NativeRelation("$criojo.Print", (tlst:List[Term])=>parseAndPrint(tlst,print))

    def indexOfPlaceHolder(input:String, start:Int):Option[Int] = {
        if(start < input.length){
            input indexOf("$", start) match{
                case index if index >= 0 => 
                    if(index + 1 < input.length && input(index+1).isDigit)
                        Some(index)
                    else
                        indexOfPlaceHolder(input, index+1)
                case _ => None
            }
        }else{
            None
        }
   }

   def indexOfValueAsStr(input:String, start:Int):String = {
      val sb = new StringBuilder(input.length)
      def recurse(pos:Int):String = {
        if(pos < input.length && input(pos).isDigit){
           sb.append(input(pos))
           recurse(pos+1)
        }else
           sb.toString
      }
      recurse(start)
   }

   def format(input:String, values: IndexedSeq[Term]): String = {
      val sb = new StringBuilder(input.length)
      
      def recurse(start:Int):String = if(start < input.length){
         indexOfPlaceHolder(input, start) match{
            case Some(index) => 
               val valueIndex = indexOfValueAsStr(input, index+1)
               sb.append(input subSequence (start, index))
               sb.append(values(valueIndex.toInt-1).toString)
               recurse(index + valueIndex.length + 1)
            case _ => sb.toString
         }
      } else{
         sb.toString
      }

      if (values.isEmpty)
        input
      else
        recurse(0)
   }      

  private def parseAndPrint(terms:List[Term], fprint:(Any => Unit)=print):List[Atom] =
    terms match{
      case s::tlst =>
        fprint(format(s.toString,tlst.toIndexedSeq))
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
