package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 17/11/11
 * Time: 17:18
 */

import fr.emn.criojo.util.Logger._
import fr.emn.criojo.ext.expression.Relation.constructor.{LocalRelation}
import fr.emn.criojo.ext.expression.Relation.{Relation}

trait Engine extends RuleFactory{
  protected var rules:List[Rule] = List()
  protected var relations:List[Relation] = List()

  def reactionStrategy:ReactionStrategy

  def executeRules()
  def introduceAtom(atom:Atom)

  def addRelation(relation:Relation) { relations :+= relation }
  def addRule(rule:Rule) {

    rule.body.foreach(a => addRelation(a.relation))
    rule.head.foreach(a => addRelation(a.relation))

    rules :+= rule
  }

  /**
   * Searches for a relation. If found, Some[Relation] is returned.
   * Otherwise, it will return Nothing
   */
  def findRelation(relName:String):Option[Relation] = relations.find(_.name == relName)

  def getRelation(relName:String):Relation = findRelation(relName) match{
      case Some(r) => r
      case _ =>
        if(relName startsWith ("$"))
          new LocalRelation(relName)
        else{
          log(WARNING, this.getClass, "findRelation","Undefined relation " + relName)
          new LocalRelation("Undefined")
        }
  }

  def initRules(ruleDefs:List[RuleFactory => Rule]){
    ruleDefs.foreach{ f => initRule(f)  }
  }

  def initRule(rf: RuleFactory => Rule){
    val rule = rf(this)
    processRuleHead(rule)
    processRuleBody(rule)
    addRule(rule)
  }

  def processRuleHead(rule:Rule) {
  }

  def processRuleBody(rule:Rule)  {
  }

  def printRules: String = rules.mkString("","\n","")
}

