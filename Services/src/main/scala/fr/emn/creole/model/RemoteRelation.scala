package fr.emn.creole.model

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 18, 2010
 * Time: 2:21:37 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._
import fr.emn.creole.client._
import fr.emn.creole.virtualmachine.VirtualMachineService
import fr.emn.creole.util.Logger

import java.net.URI;

class RemoteRelation(remoteName:String,url:URI) extends Relation{

  def name = this.remoteName

  def public = false

  def addObserver(observer:RelationObserver){
    //Does nothing
  }

  def isMultiRel = true

  override def notifyObservers(a: Atom){
    val subs = a.vars.map{
      v => val value = VirtualMachineService.machine.getValue(v)
        if (value == NoValue)
          (v, v)
        else
          (v, value)
    }

    Logger.log(this.getClass,"notifyObservers"," subs= " + subs)    

    val newAtom = a.applySubstitutions(subs)   //TODO 
    Logger.log(this.getClass,"notifyObservers"," newAtom=" + newAtom)
    
    CreoleClient.exportAtom(newAtom, url)
  }
}