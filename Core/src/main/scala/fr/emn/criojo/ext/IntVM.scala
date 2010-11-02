package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 7, 2010
 * Time: 2:33:04 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._
import Creole._

trait IntVM extends CHAM with EqVM{

  val intEqClasses = new TypedEqClasses[Int](eqClasses)

  /**********************************************************************
  * VM definition:
  */
  //--Public:
  val IntRel = NativeRelation("$Int"){ a => add(a) }
  val Int_ask = NativeRelation("$Int_ask"){ a => askInt(a) }
  val Suc = NativeRelation("$Suc"){a => addSuc(a)}
  //--Private:
  private val AskInt = NativeRelation("$AskInt"){ a => askInt(a) }
  private val s,n,x,y = Var; private val K = RelVariable("K")

  /***********************************************************************/

  def getIntValue(x:Variable):Option[Int]= intEqClasses getValue x

  //Native functions
  private def add(a:Atom)= a match{
    case Atom(_, i::v::_) => intEqClasses add (i.toInt,v)
    case _ => //Nothing, wrong format
  }

  private def askInt(a:Atom)= a match{
    case Atom(_, num::session::vr::k::_) =>
      intEqClasses.get(num.toInt) match{
        case Some(vlst) if(vlst contains vr) => introduceAtom(Atom(k.toString, session, vr))
        case _ => //Nothing or negative answer
      }
    case _ =>
  }

  private def addSuc(a:Atom) = a match{
    case Atom("$Suc", v1::v2::_) =>
      intEqClasses getValue v1 match{
        case Some(k) => intEqClasses add (k+1, v2)
        case _ => log(WARNING, this.getClass, "addSuc", "Variable " + v1 + " not found.") 
      }
    case _ =>
  }

}