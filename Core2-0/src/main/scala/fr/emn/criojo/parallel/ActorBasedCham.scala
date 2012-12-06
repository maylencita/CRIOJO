package fr.emn.criojo.parallel

import messages._
import fr.emn.criojo.core.engine._
import impure.NativeAtom
import fr.emn.criojo.core.model._
import relation._

import scala.actors.Actor

//import fr.emn.criojo.core.engine.impure.NativeAtom

/**
* Created with IntelliJ IDEA.
* User: mayleen
* Date: 11/23/12
* Time: 6:06 PM
* To change this template use File | Settings | File Templates.
*/
trait ActorBasedCham extends Cham with Actor{

  var rulCount = 1

  def act(){
      loop{
        react{
          case la:List[Atom] => la.foreach(introduceAtom(_))
          case a:Atom => introduceAtom(a); reply(Ok)
          case Ready(r) => executeRule(r)
          case _ =>
        }
      }
  }

  def LocalRelation(name:String):Relation = new LocalRelation(name){}

  def InputChannel(name: String):Channel = new InChannel(name, formatLocation(this.location, name)){}

  def OutputChannel(name: String, remoteLoc: String):OutChannel = new OutChannel(name, formatLocation(remoteLoc, name), location) {}


  def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]) = {
    val rule = new ParRule(h,b,g,scope,this)
    rule.id = rulCount
    rulCount += 1
    rule.start()
    rule
  }

  def executeRules() {
    while(rules.exists(r => executeRule(r))){}
  }

  private def formatLocation(chamLoc:String, channelName:String):ChannelLocation = {
    new ChannelLocation(chamLoc + "." + channelName, channelName)
  }

  private def executeRule(rule:Rule):Boolean = rule match{
    case r:ParRule =>
      r !? Fire match{
        case reac:Reaction => applyReaction(reac)
          true
        case _ => false
      }
    case _ => false
  }

  def introduceAtom(atom: Atom) {
    atom.reduce()
    for (r <- rules){r match{
      case reaction:ParRule => reaction ! Put(atom)
      case _ =>
    }
    }
  }

  def removeAtom(atom: Atom) {
    for (r <- rules){r match{
      case reaction:ParRule => reaction ! Remove(atom)
      case _ =>
    }
    }
  }

}


