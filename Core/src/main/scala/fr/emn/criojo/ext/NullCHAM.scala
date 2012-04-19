package fr.emn.criojo.ext

/**
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 21/01/11
* Time: 11:36
* To change this template use File | Settings | File Templates.
*/
import fr.emn.criojo.core._
import EqClass._

import collection.mutable.HashSet
import factory.DefaultFactory
import fr.emn.criojo.lang.{ChamGuard, Molecule}

trait NullCHAM extends EqCHAM with DefaultFactory{
//  val nullVars:EqClass = HashSet[Variable]()
//  private val x,y = Var
//
//  /**********************************************************************
//  * VM definition:
//  */
//  private val f = new ChannelVariable("false")
//  private val t = new ChannelVariable("true")
//
//  val NullRel = NativeRelation("Null")( addNull )
//  val Null_ask = NativeRelation("Null_ask")(askNull)
//
//  /***********************************************************************/
//
//  private def addNull(a:Atom, s:Solution){
//    a match{
//      case Atom("Null", (v:Variable)::_) =>
//        if (nullVars.isEmpty){
//          eqClasses add nullVars
//        }
//        nullVars add v
//        s.inactivate(a)
//        s.cleanup()
//        true
//      case _ => false //Nothing
//    }
//  }
//
//  private def askNull(a:Atom, s:Solution){
//    a match{
//      case Atom(_, (v:Variable)::kplus::kminus::_) =>
//        if (nullVars contains (v))
//          s.addAtom(Atom(kplus.name, v))
//        else
//          s.addAtom(Atom(kminus.name, v))
//      case _ =>
//    }
//  }
//
//  case class NotNul(override val variable:Variable) extends Nul(variable){
//    override def eval(vals: Valuation) = {
//      ! super.eval(vals)
//    }
//  }
//
//  case class Nul(variable:Variable) extends PresenceGuard(Atom("Null",variable)::Nil) with ChamGuard
//
}

