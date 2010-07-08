package fr.emn.creole.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 8, 2010
 * Time: 11:15:43 AM
 * To change this template use File | Settings | File Templates.
 */

trait CHAM {
  var rules:List[Rule] = List()
  var solution:Solution = new Solution() //Set())
  var relations:List[Relation] = List()

  def addRule(rule:Rule){
    rule.solution = this.solution
//    rule.relations = this.relations

    if (!rule.isAxiom)
      rule.head.foreach{
        a => a.relation = relations.find(_.name == a.relName) match {
                  case Some(r) => r.addObserver(rule); r
                  case _ => if (!a.relName.startsWith("$")){
                    println("Undefined relation " + a.relName)
                    null
                  }else{
                    new Relation(a.relName,false)
                  }
             }
      }

    rule.body.foreach{a =>  a.relation = relations.find(_.name == a.relName) match {
        case Some(r) => r
        case _ => if (!a.relName.startsWith("$")){
                    println("Undefined relation " + a.relName)
                    null
                  }else{
                    new Relation(a.relName,false)
                  }
      } ;
      a.vars.foreach{
        case rv: RelVariable => rv.relation = relations.find(_.name == rv.name) match{
          case Some(r) => r
          case _ => println("Undefined relation variable " + rv.name); null //TODO Handle this
        }
        case _ => //skip
      }
    }

    rules :+= rule
//    rule.execute    
  }

  def addRelation(relation:Relation){
    relations :+= relation
  }

}