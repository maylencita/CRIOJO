package fr.emn.criojo.core

import Creole.Substitution
import fr.emn.criojo.util.Logger

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
  val solution = Solution()
  val TopRel = Rel("true")
  val falseRel = Rel("false")

  def empty = rules.isEmpty

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

  def addRelations (rlst:List[Relation]){
    for (r <- rlst if !relations.contains(r)){
      addRelation(r)  
    }
  }

  def eval(sol:Solution, subs:List[Substitution]):Boolean ={
    Logger.log("------------------------------------------------------")
    Logger.log("[Guard.eval] Begin")
    Logger.log("[Guard.eval] substitutions: " + subs)

    solution.revert
    solution.update(sol.copy) //val solution = sol.clone
    Logger.levelDown
    introduceAtom (rules.find(r=>r.head.exists(a=>a.relation == TopRel)) match{
      case Some(r) => (r.head.find(a => a.relation == TopRel).get) applySubstitutions subs
      case _ => Top()
    })
    Logger.levelUp
      
    Logger.log("[Guard.eval] finished with solution: " + solution)
    Logger.log("------------------------------------------------------")
    solution.exists(a => a.relation == TopRel)
  }
  
  override def toString:String = {
    val nonPrint = List("Eq","Eq_ask","$Int","$AskInt","$Str","$AskStr")
    rules.filterNot(r=>r.head.exists(a => nonPrint.contains(a.relName))).mkString("",";","")
  }

}

object Top{
  def apply():Top = new Top(List())
}
class Top(vlst:List[Variable]) extends Atom ("true", vlst){
  def this() = this(List())
  
  relation = new LocalRelation("Top", false, false)
  override def isTrue:Boolean = true
  override def isFalse:Boolean = false

  override def hashCode = "true".hashCode
  override def clone = this
}

object False extends Atom ("false", List()){
  relation = new LocalRelation("False", false, false)
  override def isTrue:Boolean = false
  override def isFalse:Boolean = true

  override def applySubstitutions(subs:List[Substitution]) = this
  override def hashCode = "false".hashCode
  override def clone = this
}