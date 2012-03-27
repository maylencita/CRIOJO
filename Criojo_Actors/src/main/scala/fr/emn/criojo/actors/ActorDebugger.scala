package fr.emn.criojo.actors

import fr.emn.criojo.ext.debug.DebugCham

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 27/03/12
 * Time: 09:38
 */
trait ActorDebugger extends DebugCham with MessageHandler{
  override def handleMessage(msg:String){
    println(this.toString + " - Message received: " + msg)
    super.handleMessage(msg)
  }
}