package fr.emn.criojo.network

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/30/12
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */

trait NetworkObject {

  def getName:String

  var filterRules:List[String] = List()

  def addFilterRules(list:List[String]) {
    filterRules = filterRules ::: list
  }
}
