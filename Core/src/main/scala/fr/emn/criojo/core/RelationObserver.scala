package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 15, 2010
 * Time: 10:01:19 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * The RelationObserver trait
 * @define THIS RelationObserver
 */
trait RelationObserver {

  def receiveUpdate(atom:Atom)
}