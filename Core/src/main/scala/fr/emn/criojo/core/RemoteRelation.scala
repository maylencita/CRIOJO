package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 1, 2010
 * Time: 2:05:31 PM
 * To change this template use File | Settings | File Templates.
 */

import java.net.URI;

@serializable
abstract class RemoteRelation extends Relation{
  def name:String
  def url:URI

  val public = false

  val isMultiRel = true

}