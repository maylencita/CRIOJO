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
  val StrVal = Rel("Str")
//  val StrRel = new NativeRelation("$Str", this.solution, (a,s) => strEqClasses add (a.vars(0).name,a.vars(1))){
//    addRelation(this)
//    override def apply(vars:Term*) = new StringAtom(vars(0).toString, vars(1))
//  }
  val Str_ask = Rel("Str_ask")//NativeRelation("$Str_ask"){(a,s) => ask(a) }

  //--Private:
  private val Declare = NativeRelation("$StrCham.Declare"){ (a,s) => declare(a) }
  private val AskStr = NativeRelation("$AskStr"){ (a,s) => ask(a) }
  private val PrintStr = NativeRelation("$PrintStr"){(a,s) => println(a(0) + "=" + a(1))}
  private val str,s,x,y = Var();
  private val K = VarR("K")
  private val Err = VarR("Err")
  private val X1 = Tok()

  rules(
    StrVal(x,str) --> Abs(X1(x)) ?: (Declare(x,str) & StrVal(x,str) & X1(x)),
    (StrVal(s,x) &: Str_print(x)) --> PrintStr(x,s),

    (Str_ask(s,x,K,Err) & StrVal(x,str)) --> (K(s,x,str) & StrVal(x,str)),
    Str_ask(s,x,K,Err) --> Abs(StrVal(x,str)) ?: Err(s,x)
  )


  /***********************************************************************/

  def getStrValue(x:Variable):Option[String]= strEqClasses getValue x

  //Native
  private def declare(a:Atom){
    a match{
      case Atom(_, (v:Variable)::(s:ValueTerm[String])::_) => strEqClasses add (s.value,v)
      case _ => //Nothing, wrong variables
    }
  }

  private def ask(a:Atom){
    a match{
      case Atom(_, sval::session::(vr:Variable)::k::_) =>
        strEqClasses.get(sval.name) match{
          case Some(vlst) if(vlst contains vr) => introduceAtom(Atom(k.toString, session, vr))
          case _ => //Nothing or negative answer
        }
      case _=>
    }
  }

  implicit def str2fun(str:String):Term = new ValueTerm[String](str)
}