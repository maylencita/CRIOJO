package fr.emn.criojo.ext

/**
* Created by IntelliJ IDEA.
* User: mayleen
* Date: Sep 28, 2010
* Time: 2:18:31 PM
* To change this template use File | Settings | File Templates.
*/

import fr.emn.criojo.core._
import factory.DefaultFactory
import fr.emn.criojo.lang._

import collection.mutable.HashSet
import EqClass._

trait EqCHAM extends Cham with DefaultFactory{
//
//  var eqClasses = new EqClassList
//  var disjClasses = new EqClassList
//
//  val genEqClasses = new TypedEqClasses[Any](eqClasses,disjClasses)
//  /**********************************************************************
//  * CHM definition:
//  */
//  //--Public:
//  //val EQ = NativeRelation("Eq"){ (a,s) => if (a.patterns.size == 2) addEquivalence(a.vars(0),a.vars(1),s) }
//  //val EQ_ask = NativeRelation("Eq_ask")(askEq)
//  //val NotEQ = NativeRelation("$NotEq"){(a,s) => addNotEqual(a.vars(0),a.vars(1),s)}
//
//  //--Private:
//  private val s,x,y,z = BooleanVariable();
//  private val K = VarR("K")
//  /***********************************************************************/
//
//  def Eq(t1:Term, t2:Term):CriojoGuard = {
//    val g = new CriojoGuard{
//      override def eval(vals: Valuation) = {
//        existsEqual(t1.applyValuation(vals), t2.applyValuation(vals))
//      }
//      override def valuations(valuation:Valuation):ValuationList = {
//        if(existsEqual(t1.applyValuation(valuation), t2.applyValuation(valuation))) {
//          valuation
//        }
//        else {
//          new ValuationList()
//        }
//      }
//      val observed = Set[String]()
//      def receiveUpdate(atom: Atom){}
//    }
//    g
//  }
//  def NotEq(t1:Term, t2:Term):CriojoGuard = {
//    val g = new CriojoGuard{
//      override def eval(vals: Valuation) = {
//        existsNotEqual(t1.applyValuation(vals), t2.applyValuation(vals))
//      }
//      override def valuations(valuation:Valuation):ValuationList = {
//        if(existsNotEqual(t1.applyValuation(valuation), t2.applyValuation(valuation))) {
//          valuation
//        }
//        else {
//          new ValuationList()
//        }
//      }
//      val observed = Set[String]()
//      def receiveUpdate(atom: Atom){}
//    }
//    g
//  }
//
//  protected def disjointed(e1:EqClass, e2:EqClass):Boolean ={
//    if (e1 == e2)
//      false
//    else
//      disjClasses.contains(e1) && disjClasses.contains(e2)
//  }
//
//  private def equalsOp(op:Option[Any],value:Any):Boolean = op match{
//    case Some(v) => v == value
//    case _ => false
//  }
//
//  protected def existsEqual(t1:Term, t2:Term):Boolean = (t1,t2) match{
//        case (ValueTerm(v1),ValueTerm(v2)) => v1 == v2
//        case (v1:Variable,v2:Variable) => (v1.equals(v2) || eqClasses.exists(ec => ec.contains(v1) && ec.contains(v2)))
//        case (x:Variable,ValueTerm(v)) => equalsOp(genEqClasses.getValue(x),v)
//        case (x:IdTerm,CriojoIntegerValue(v)) => equalsOp(genEqClasses.getValue(IntegerVariable.createVariable(x.name)),v)
//        case (ValueTerm(v),x:Variable) => equalsOp(genEqClasses.getValue(x),v)
//        case (IdTerm(v1),IdTerm(v2)) => v1==v2
//        case (v1,v2) => v1==v2
//  }
//
//  protected def existsNotEqual(t1:Term, t2:Term):Boolean = (t1,t2) match{
//        case (ValueTerm(v1),ValueTerm(v2)) => v1 != v2
//        case (v1:Variable,v2:Variable) =>
//          (disjClasses.find(v1), disjClasses.find(v2)) match{
//          case (Some(e1),Some(e2)) if e1 != e2 =>
//            //They are different
//            true
//          case _ => false //No enough information to answer
//          }
//        case (x:Variable,ValueTerm(v)) => !equalsOp(genEqClasses.getValue(x),v)
//        case (ValueTerm(v),x:Variable) => !equalsOp(genEqClasses.getValue(x),v)
//        case (IdTerm(v1),IdTerm(v2)) => v1!=v2
//        case (v1,v2) => v1!=v2
//  }
//
//  def askEquivalence(x:Variable, y:Variable, s:Solution):Boolean = {
//    x.equals(y) ||
//    eqClasses.exists(ec => ec.contains(x) && ec.contains(y))
//  }
//
}

