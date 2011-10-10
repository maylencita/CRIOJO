package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 7, 2010
 * Time: 2:34:39 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._
import fr.emn.criojo.lang._

trait StrCHAM extends EqCHAM{

  val strEqClasses = new TypedEqClasses[String](eqClasses,disjClasses)

  /**********************************************************************
  * VM definition:
  */
  val Str_print = Rel("$Str_print")
  val StrRel = new NativeRelation("$Str", this.solution, (a,s) => strEqClasses add (a(0).name,a(1))){
    addRelation(this)
    override def apply(vars:Variable*) = new StringAtom(vars(0).toString, vars(1))
  }
  val Str_ask = NativeRelation("$Str_ask"){(a,s) => ask(a) }

  //--Private:
  private val NewStr = NativeRelation("$NewStr"){ (a,s) => add(a) }
  private val AskStr = NativeRelation("$AskStr"){ (a,s) => ask(a) }
  private val PrintStr = NativeRelation("$PrintStr"){(a,s) => println(a(0) + "=" + a(1))}
  private val str,s,x,y = Var; private val K = VarR("K")

  rules(
    (StrRel(s,x) &: Str_print(x)) ==> PrintStr(x,s)
  )


  /***********************************************************************/

  def getStrValue(x:Variable):Option[String]= strEqClasses getValue x
  
  //Native
  private def add(a:Atom){
    a match{
      case Atom("$NewStr", i::v::_) => strEqClasses add (i.name,v)
      case _ => //Nothing, wrong variables
    }
  }

  private def ask(a:Atom){
    a match{
      case Atom(_, sval::session::vr::k::_) =>
        strEqClasses.get(sval.name) match{
          case Some(vlst) if(vlst contains vr) => introduceAtom(Atom(k.toString, session, vr))
          case _ => //Nothing or negative answer
        }
      case _=>
    }
  }


}