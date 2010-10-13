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

trait IntVM extends CHAM with Eq{

  val intEqClasses = new TypedEqClasses[Int](eqClasses)

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

  def getIntValue(x:Variable):Option[Int]= intEqClasses getValue x

  //Native functions
  private def add(a:Atom)= a match{
    case Atom("$NewInt", i::v::_) => intEqClasses add (i.name.toInt,v)
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