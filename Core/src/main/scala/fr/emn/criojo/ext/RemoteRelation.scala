package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 1, 2010
 * Time: 2:05:31 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._

import java.net.URI;

abstract class RemoteRelation extends Relation{
  def name:String
  def url:URI

  val public = false

  val isMultiRel = true

  def addObserver(observer:RelationObserver){
    //Does nothing
  }
  
}