package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 8, 2010
 * Time: 3:03:16 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._

abstract class VirtualMachine extends ExtendedCHAM 
{
  def execute{
      while (rules.exists(r => r.isAxiom && r.execute)){}
  }

  def newRemoteRelation(remoteName:String,url:String):RemoteRelation

  override def toString:String = {
    val nonPrint = List("Eq","Eq_ask","$Int","$AskInt","$Str","$AskStr")
    rules.filterNot(r=>r.head.exists(a => nonPrint.contains(a.relName))).mkString("","\n","")
  }

}

