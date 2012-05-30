package fr.emn.criojo

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/30/12
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */

class Firewall extends NetworkObject {

  def getName:String = "noname"

  var children:List[NetworkObject] = List()

  def sendFilterRules() {
    children.foreach(o => {
      o.addFilterRules(filterRules)
      o match {
        case f:Firewall => f.sendFilterRules()
        case _ =>
      }
    })
  }
}
