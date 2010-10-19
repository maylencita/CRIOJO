package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 7, 2010
 * Time: 2:34:39 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._
import Creole._

trait StringVM extends CHAM with EqVM{

  val strEqClasses = new TypedEqClasses[String](eqClasses)

  /**********************************************************************
  * VM definition:
  */
  val StrRel = Rel("$Str")
  val Str_ask = new Rel("$Str_ask"){
    override def apply(vars:Variable*):Atom= {
      assert(vars.size == 4)
      new StrAtom_ask(vars.toList)
    }
  }
  //--Private:
  private val NewStr = NativeRelation("$NewStr"){ a => add(a) }
  private val AskStr = NativeRelation("$AskStr"){ a => ask(a) }
  private val str,s,x,y = Var; private val K = RelVariable("K")

  rules(
    StrRel(str,x) ==> NewStr(str,x),
    Str_ask(str,s,x,K) ==> AskStr(str,s,x,K)
  )
  /***********************************************************************/

  def getStrValue(x:Variable):Option[String]= strEqClasses getValue x
  
  //Native
  private def add(a:Atom) = a match{
    case Atom("$NewStr", i::v::_) => strEqClasses add (i.name,v)
    case _ => //Nothing, wrong variables
  }

  private def ask(a:Atom) = a match{
    case Atom("$AskStr", sval::session::vr::k::_) =>
      strEqClasses.get(sval.name) match{
        case Some(vlst) if(vlst contains vr) => introduceAtom(Atom(k.toString, session, vr))
        case _ => //Nothing or negative answer
      }
    case _=>
  }

  //Special atom type to handle filtering
  case class StrAtom_ask(str:Variable, session:Variable, variable:Variable, k:Variable)
          extends Atom(AskStr.name, List(str,session,variable,k)){
    def this(vlst:List[Variable])={
      this(vlst(0),vlst(1),vlst(2),vlst(3))
    }

    this.relation = AskStr
    override def applySubstitutions(subs:List[Substitution])={
      new StrAtom_ask(super.applySubstitutions(subs :+ (str,str)).vars)
    }
  }

}