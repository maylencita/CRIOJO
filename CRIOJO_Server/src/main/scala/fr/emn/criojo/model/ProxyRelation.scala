package fr.emn.criojo.model

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 14, 2010
 * Time: 11:18:19 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.ext._
import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger
import fr.emn.criojo.virtualmachine._
import fr.emn.criojo.util._

import scala.actors.Actor

import java.net.URI

class ProxyRelation(val name:String, sender:Actor) extends RemoteRelation{

  def url:URI = {
    throw new IllegalAccessException("ProxyRelation is for local use only.")
  }

  override def notifyObservers(a: Atom){
    val subs = a.vars.map{
      v => VirtualMachineService.getValue(v) match{
        case value:Value[_] => (v, value)
        case NoValue => (v,v)
      }      
//      v => val value = VirtualMachineService.machine.getValue(v)
//        if (value == NoValue)
//          (v, v)
//        else
//          (v, value)
    }

//    Logger.log(this.getClass,"notifyObservers"," subs= " + subs)

    val newAtom = a.applySubstitutions(subs)
    sender ! newAtom
    Logger.log(this.getClass,"notifyObservers"," Atom exported =" + newAtom)
  }
}
