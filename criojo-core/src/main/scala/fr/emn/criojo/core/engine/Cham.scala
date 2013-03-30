package fr.emn.criojo.core.engine

import fr.emn.criojo.core.model._
import impure.NativeAtom
import relation._
import java.util.UUID

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/26/12
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
trait Cham extends RuleFactory{

  protected var rules:List[Rule] = List()
  protected var relations:List[Relation] = List()      //TODO Do we need a list of relations?

  /**
   * A Cham has a location that is protocol independent.
   * @return
   */
  def location:String

  /**
   * Atoms are exported outside the Cham via an AtomGateway
   * Implementing classes implement this abstract method as a class value
   * @return
   */
  def gateway:AtomGateway

  /**
   * Tries to execute every rules until there are no more reactions left
   */
  def executeRules()

  /**
   * Introduces a new atom into de solution
   * @param atom A new Atom
   */
  def introduceAtom(atom: Atom)

  /**
   * Removes an Atom from the solution
   * @param atom
   */
  def removeAtom(atom: Atom)

  /**
   * Removes all the atoms of the solution
   */
  def removeAll()

  def LocalRel:Relation = this.LocalRelation("LocalRelation"+UUID.randomUUID().toString)
  def LocalRel(name:String):Relation = this.LocalRelation(name)

  /**
   * Allows the definition of a LocalRelation.
   * Implementing classes define their own LocalRelation type
   * @param name
   * @return
   */
  def LocalRelation(name:String):Relation
  def InputChannel(name: String):Channel
  def OutputChannel(name: String, location: String):OutChannel

  /**
   * Applies the result of a reaction
   */
  def applyReaction(reaction:Reaction){
    reaction.toRemove.foreach(removeAtom(_))
    reaction.toInsert.foreach{
      case a:LocalMessage => introduceAtom(a)
      case na:NativeAtom => na.apply.foreach{a => introduceAtom(a)}
      case rm:RemoteMessage => gateway.exportAtom(rm)
      case uhm => println("[CHAM] Warning! Unknown message type: " + uhm)
    }
  }

  def addRule(rule:Rule) {
    rules :+= rule
  }

  def addRelation(relation:Relation){
    relations :+= relation
  }

  def initRules(ruleDefs:List[RuleFactory => Rule]){
    ruleDefs.foreach{ f => addRule(f(this))  }
  }

  override def toString = this.location
}
