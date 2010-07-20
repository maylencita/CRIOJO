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
        a => a.relation = findRelation(a.relName) match {
          case r:Relation => r.addObserver(rule); r
          case null => null
        }
      }

    rule.body.foreach{a =>  a.relation = findRelation(a.relName);
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

  private def findRelation(relName:String):Relation = {
    relations.find(_.name == relName) match {
      case Some(r) => r
      case _ => if (relName.startsWith("$")){
                  new Relation(relName,false)
                }else{
                  println("Undefined relation " + relName)
                  null
                }
    }
  }

  def addRelation(relation:Relation){
    relations :+= relation
  }

  def introduceAtom(atom:Atom){
    solution.addAtom(atom)
    findRelation(atom.relName) match{
      case r:Relation => atom.relation = r; r.notifyObservers(atom)
      case _ => //skip
    }
  }
}