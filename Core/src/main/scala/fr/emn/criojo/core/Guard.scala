package fr.emn.criojo.core

import Creole.Substitution
import fr.emn.criojo.util.Logger

import scala.collection.mutable.HashSet

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 16, 2010
 * Time: 11:32:59 AM
 * To change this template use File | Settings | File Templates.
 */

class Guard (grules:List[Rule]) extends CHAM{
  def this(){
    this(List[Rule]())
  }
//  val trueRel = Rel("true")
//  val falseRel = Rel("false")
  val T = Rel("true")
  val F = Rel("false")

  grules.foreach{ rule =>
    newRule(rule.head,rule.body,rule.guard)
  }

  override def relation(relName:String):Option[Relation] = {
    relations.find(_.name == relName) match{
      case sr:Some[_] => sr
      case _ =>
        val rel = new LocalRelation(relName,false)
        addRelation(rel)
        Some(rel)
    }
  }

  def empty = rules.isEmpty

  def addRelations (rlst:List[Relation]){
    for (r <- rlst if !relations.contains(r)){
      addRelation(r)  
    }
  }

  def eval(sol:Solution, substitutions:List[Substitution]):Boolean ={
    Logger.log("------------------------------------------------------")
    Logger.log("[Guard.eval] Begin")
//    Logger.log("[Guard.eval] substitutions: " + substitutions)
    solution.revert
    solution.update(sol.copy) //val solution = sol.clone
    if (!solution.exists(_.relName == T.toString))
      solution.addAtom(True())

//    rules.foreach(r => r.solution = solution)
    Logger.levelDown
    rules.foreach(r => r.execute(substitutions))
    Logger.levelUp
      
    Logger.log("[Guard.eval] finished with solution: " + solution)
    Logger.log("------------------------------------------------------")
    solution.isTrue
  }
  
  override def toString:String = rules.mkString("",";","")

}
