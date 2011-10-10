package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 19/01/11
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._

trait BoolCHAM extends EqCHAM{
  val boolEqClasses = new TypedEqClasses[Boolean](eqClasses,disjClasses)

  /**********************************************************************
  * VM definition:
  */
  //--Public:
  /***********************************************************************/

}