package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 7, 2010
 * Time: 2:33:04 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._
import Creole._

import collection.mutable.{HashMap, HashSet}

trait IntVM extends CHAM
        with Eq{

  val intEqClasses = HashMap[Int, EqClass]()

  /**********************************************************************
  * VM definition:
  */
  //--Public:
  val IntRel = Rel("$Int")
  val Int_ask = new Rel("$Int_ask"){
    override def apply(vars:Variable*):Atom= {
      assert(vars.size == 4)
      new IntAtom_ask(vars.toList)
    }
  }
  //--Private:
  private val NewInt = NativeRelation("$NewInt"){ a => add(a) }
  private val AskInt = NativeRelation("$AskInt"){ a => askInt(a) }
  private val s,n,x,y = Var; private val K = RelVariable("K")

  IntRel(n,x) ==> NewInt(n,x)
  Int_ask(n,s,x,K) ==> AskInt(n,s,x,K)
  /***********************************************************************/

  //Native functions
  private def add(a:Atom)= a match{
    case Atom("$NewInt", i::v::_) =>
      val k = i.name.toInt
      intEqClasses.get(k) match{
        case Some(iec) =>
          eqClasses.find(_.contains(v)) match{
            case Some(ec) if(iec != ec)=> intEqClasses update (k, merge(iec,ec)) 
            case _ => iec.add(v)
          }
        case _ =>
          eqClasses.find(_.contains(v)) match{
            case Some(ec) => intEqClasses.put(k, ec)
            case _ =>
              val newEC = HashSet(v)
              eqClasses :+= newEC
              intEqClasses.put(k, newEC)
          }
      }
    case _ => //Nothing, wrong format
  }

  private def askInt(a:Atom)= a match{
    case IntAtom_ask(num,session,vr,k) =>
      intEqClasses.get(num.toInt) match{
        case Some(vlst) if(vlst contains vr) => introduceAtom(Atom(k.toString, session, vr))
        case _ => //Nothing or negative answer
      }
    case _ =>
  }

  //Special atom type to handle filtering
  case class IntAtom_ask(num:Variable, session:Variable, variable:Variable, k:Variable)
          extends Atom(AskInt.name, List(Variable(num.toString),session,variable,k)){
    def this(vlst:List[Variable])={
      this(vlst(0),vlst(1),vlst(2),vlst(3)) 
    }

    this.relation = AskInt
    override def applySubstitutions(subs:List[Substitution])={
      new IntAtom_ask(super.applySubstitutions(subs :+ (num,num)).vars)
    }
  }
}