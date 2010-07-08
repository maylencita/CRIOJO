package fr.emn.creole.core

import Creole.Substitution
import fr.emn.creole.util.Logger

import scala.collection.mutable.HashSet

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 16, 2010
 * Time: 11:32:59 AM
 * To change this template use File | Settings | File Templates.
 */

class Guard (rules:List[Rule]){
  def this(){
    this(List[Rule]())
  }
  
  val relations:HashSet[Relation] = HashSet()

  rules.foreach{
    r => r.head.foreach{
      a => if (a != True){
        val rel:Relation = getRelation(a)
        a.relation = rel
        rel.addObserver(r)
      }
      r.body.foreach(a => a.relation = getRelation(a))
    }
  }

  private def getRelation(atom:Atom):Relation = {
    relations.find(_.name == atom.relName) match{
      case Some(rel) => rel
      case _ => val rel = new Relation(atom.relName,false)
        relations.add(rel)
        rel
    }
  }

  def empty = rules.isEmpty

  def eval(sol:Solution, substitutions:List[Substitution]):Boolean ={
    Logger.log("------------------------------------------------------")
    Logger.log("[Guard.eval] Begin")
//    Logger.log("[Guard.eval] substitutions: " + substitutions)
    val solution = sol.clone
    solution.revert
    if (!solution.contains(True))
      solution.add(True)

    rules.foreach(r => r.solution = solution)
    Logger.levelDown
    rules.foreach(r => r.execute(substitutions))
    Logger.levelUp
      
    Logger.log("[Guard.eval] finished with solution: " + solution)
    Logger.log("------------------------------------------------------")
    solution.isTrue
  }
  
  override def toString:String = rules.mkString("",";","")
}
