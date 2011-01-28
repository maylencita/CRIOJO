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

  val strEqClasses = new TypedEqClasses[String](eqClasses,disjClasses)

  /**********************************************************************
  * VM definition:
  */
  val Str_print = Rel("$Str_print")
  val StrRel = new NativeRelation("$Str", a => strEqClasses add (a(0).name,a(1))){
    override def apply(vars:Variable*):Atom = new StringAtom(vars(0).toString, vars(1))
  }
  val Str_ask = NativeRelation("$Str_ask"){a => ask(a) }

  //--Private:
  private val NewStr = NativeRelation("$NewStr"){ a => add(a) }
  private val AskStr = NativeRelation("$AskStr"){ a => ask(a) }
  private val PrintStr = NativeRelation("$PrintStr"){a => println(a(0) + "=" + a(1))}
  private val str,s,x,y = Var; private val K = RelVariable("K")

  rules(
    (StrRel(s,x) &: Str_print(x)) ==> PrintStr(x,s)
  )

  /***********************************************************************/

  def getStrValue(x:Variable):Option[String]= strEqClasses getValue x
  
  //Native
  private def add(a:Atom) = a match{
    case Atom("$NewStr", i::v::_) => strEqClasses add (i.name,v)
    case _ => //Nothing, wrong variables
  }

  private def ask(a:Atom) = a match{
    case Atom(_, sval::session::vr::k::_) =>
      strEqClasses.get(sval.name) match{
        case Some(vlst) if(vlst contains vr) => introduceAtom(Atom(k.toString, session, vr))
        case _ => //Nothing or negative answer
      }
    case _=>
  }


}