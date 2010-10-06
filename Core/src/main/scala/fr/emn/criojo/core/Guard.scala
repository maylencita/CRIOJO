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

class Guard (grules:List[Rule]) extends CHAM{//[Solution]{
  def this(){
    this(List[Rule]())
  }
  val trueRel = Rel("true")
  val falseRel = Rel("false")

//  val solution = new Solution()
  grules.foreach{ rule =>
//    addRule(rule)
    newRule(rule.head,rule.body,rule.guard)
  }

//  val relations:HashSet[Relation] = HashSet()

//  rules.foreach{
//    r => r.head.foreach{
//      a => if (a != True){
//        val rel:Relation = findRelation(a.relName)//getRelation(a)
//        a.relation = rel
//        rel.addObserver(r)
//      }
//      r.body.foreach(a => a.relation = getRelation(a))
//    }
//  }

  override def relation(relName:String):Option[Relation] = {
    relations.find(_.name == relName) match{
      case sr:Some[_] => sr
      case _ =>
        val rel = new LocalRelation(relName,false)
        addRelation(rel)
        Some(rel)
    }
  }

//  private def getRelation(atom:Atom):Relation = {
//    relations.find(_.name == atom.relName) match{
//      case Some(rel) => rel
//      case _ =>
//        val rel = new LocalRelation(atom.relName,false)
//        relations.add(rel)
//        rel
//    }
//  }

  def empty = rules.isEmpty

//  def findMatches(conj:List[Atom], subs:List[Substitution]):List[Atom] = {
//    if (solution == null)
//      List()
//    else
//      solution.findMatches(conj, subs)
//  }

  def addRelations (rlst:List[Relation]){
    for (r <- rlst if !relations.contains(r)){
      addRelation(r)  
    }
  }

  def eval(sol:Solution, substitutions:List[Substitution]):Boolean ={
    Logger.log("------------------------------------------------------")
    Logger.log("[Guard.eval] Begin")
//    Logger.log("[Guard.eval] substitutions: " + substitutions)
    solution.update(sol.copy) //val solution = sol.clone
    solution.revert
    if (!solution.contains(True))
      solution.addAtom(True)

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
