package fr.emn.criojo.parallel

import messages._
import fr.emn.criojo.core._
import datatype.{Term, Variable}
import impur.NativeRelation

import scala.actors.Actor

/**
* Created with IntelliJ IDEA.
* User: mayleen
* Date: 11/23/12
* Time: 6:06 PM
* To change this template use File | Settings | File Templates.
*/
class Dispatcher() extends Engine with Actor{
  //var executionPile = new mutable.Queue[]()

  def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]) = new ParReaction(h,b,g,scope,this)

  def reactionStrategy = new LocalReactionStrategy()

  def executeRules() { //TODO make it Boolean = {
    var active = false
     rules.foreach{
        case r:ParReaction =>
          r !? Ready() match{
            case Ok() =>  active = true
            case _ => active = false
          }
      }
    //active
  }

  def introduceAtom(atom: Atom) {
    atom.reduce()
    for (r <- rules){r match{
      case reaction:ParReaction => reaction ! Put(atom)
      case _ =>
    }
    }
  }

  def removeAtom(atom: Atom) {
    for (r <- rules){r match{
      case reaction:ParReaction => reaction ! Remove(atom)
      case _ =>
    }
    }
  }

  override def addRule(rule:Rule) {
    super.addRule(rule)
    rule match{
      case pr:ParReaction => pr.start()
    }
  }

  override def initRule(rf: RuleFactory => Rule){
    val rule = rf(this)
    addRule(rule)
  }

  def act = {
    loop{
      executeRules()

      react{
        case Put(atom) => atom.relation match{ //TODO See next todo
          case nr:NativeRelation => nr.notifyObservers(atom)
          case _ => introduceAtom(atom)
        }
        case Remove(atom) =>
          removeAtom(atom)
      }
    }
  }

}


