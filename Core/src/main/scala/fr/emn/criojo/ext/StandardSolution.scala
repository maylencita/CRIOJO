package fr.emn.criojo.ext

import fr.emn.criojo.core._
import Criojo.Substitution
import fr.emn.criojo.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 8, 2010
 * Time: 11:30:18 AM
 * To change this template use File | Settings | File Templates.
 */

@Deprecated
class StandardSolution /*extends Solution{
  private val SUC = "Suc"
  private val NULL = "null"

  override def findMatches(conjunction:List[Atom], substitutions:List[Substitution]):List[Atom] = {
    super.findMatches(conjunction,substitutions)
  }

//  override def findMatches(relation:Atom, subs:List[Substitution]): List[Atom] = relation match{
//    case Atom(SUC, vars) =>
//    case _ =>
//      if (subs.isEmpty){
//        filter(_.relName == relation.relName).toList
//      }else{
//        val test = relation.applySubstitutions(subs)
//        filter(a => a.active && a.matches(test)).toList
//      }
//  }

  override def addAtom(atom:Atom) =
    atom match{
    case Atom(SUC, x::y::Nil) =>
      val newAtom:Atom = find{
        case IntAtom(n, v) => x == v
        case _ => false
      } match {
        case Some(c:IntAtom) => remove(c); new IntAtom(c.number+1 ,y)
        case _ => atom
      }
      super.addAtom(newAtom)
    case _ => super.addAtom(atom)
  }

  def getValue(x:Variable):ValueVariable[_]={
      find{
        case ta:TypedAtom[_] => ta.variable == x
        case _ => false
      } match{
        case Some(c:IntAtom) => new Value[Int](c.number)
        case Some(c:StringAtom) => new Value[String](c.str.replace("\"",""))
        case Some(c:NullAtom) => Null
        case _ => NoValue
      }
  }

  def getPrintVariable(v:Variable):Variable = getValue(v) match{
    case value:Value[_] => value
    case NoValue => v
  }

  def prettyPrint = {
      elems.filterNot(_.relName.startsWith("$")).map(a => new Atom(a.relName, a.vars.map(getPrintVariable(_)))).mkString("<",",",">")
  }
  
  override def clone:Solution = {
    val sol = new StandardSolution()
    foreach(a => sol.addAtom(a.clone))
    sol
  }

  //Pretty Print
  override def toString = {
    if (Logger.on){
       super.toString
    }else{
//      def getValue(v:Variable):Variable={
//
////        if(v.name.startsWith("$")){
//          find(a => a.relName.startsWith("$") && a.hasVariable(v)) match{
//            case Some(c) => new Variable(c.relName.substring(5))
//            case _ => v
//          }
////        }else
////          v
//      }

      elems.filterNot(_.relName.startsWith("$")).map(a => new Atom(a.relName, a.vars.map(getPrintVariable(_)))).mkString("<",",",">")
    }

  }


}
*/