package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 19/01/11
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._
import fr.emn.criojo.lang.Cham

trait BoolCHAM extends EqCHAM{
  val boolEqClasses = new TypedEqClasses[Boolean](eqClasses,disjClasses)

  /**********************************************************************
  * VM definition:
  */
  //--Public:
  /***********************************************************************/

}

trait BoolCHAM2 extends Cham{

  val And = createAndAddRelation("And")
  val Or = createAndAddRelation("Or")
  val TrueRel = createAndAddRelation("TrueRel")
  val FalseRel = createAndAddRelation("FalseRel")
  val trueFun = Fun("true")
  val falseFun = Fun("false")
  private val K = VarR("K")
  private val Val = createAndAddRelation("$Val")
  private val s,x,y,v,v1,v2 = createVariable()

  rules(
    And(s,true,true,K) --> K(s,true),
    And(s,x,false,K) --> K(s,false),
    And(s,false,x,K) --> K(s,false),
    (And(s,x,y,K) & Val(x,v1) & Val(y,v2)) --> And(s,v1,v2,K),

    Or(s,true,x,K) --> K(s,true),
    Or(s,x,true,K) --> K(s,true),
    Or(s,false,false,K) --> K(s,false),
    (Or(s,x,y,K) & Val(x,v1) & Val(y,v2)) --> Or(s,v1,v2,K),

    TrueRel(x) --> Val(x,true),
    FalseRel(x) --> Val(x,false)
  )

  implicit def bool2fun(bool:Boolean):Term =
    if(bool) trueFun() else falseFun()
}