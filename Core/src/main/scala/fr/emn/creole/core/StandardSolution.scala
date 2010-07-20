package fr.emn.creole.core

import Creole.Substitution
import fr.emn.creole.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 8, 2010
 * Time: 11:30:18 AM
 * To change this template use File | Settings | File Templates.
 */

class StandardSolution extends Solution{
  private val SUC = "Suc"

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

  override def addAtom(atom:Atom):Boolean =
    atom match{
    case Atom(SUC, x::y::Nil) =>
      val newAtom:Atom = find{
//        a => a.hasVariable(x)
        case IntAtom(n, v) => x == v
        case _ => false
      } match {
        case Some(c:IntAtom) => remove(c); new IntAtom(c.number+1 ,y)
        case _ => atom
      }
      super.add(newAtom) 
    case _ => super.add(atom)
  }

  private def getValue(x:Variable):Variable={
      find{
        case IntAtom(n,v) => x == v
        case _ => false
      } match{
        case Some(c:IntAtom) => new Variable(c.number.toString)
        case _ => x
      }
  }

  override def clone:Solution = {
    val sol = new StandardSolution()
    foreach(a => sol.add(a.clone))
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

      filterNot(_.relName.startsWith("$")).map(a => new Atom(a.relName, a.vars.map(getValue(_)))).mkString("<",",",">")
    }

  }

}