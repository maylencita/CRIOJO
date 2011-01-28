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

  val intEqClasses = new TypedEqClasses[Int](eqClasses,disjClasses)

  /**********************************************************************
  * VM definition:
  */
  //--Public:
  val IntRel = new Rel("$Int"){override def apply(vars:Variable*)=new NumAtom(this.name, vars.toList)} //NativeRelation("$Int"){ a => add(a) }
  val Int_ask = NativeRelation("$Int_ask"){ a => askInt(a) }
  val Int_print = Rel("$Int_print")
  val Suc = Rel("$Suc") //NativeRelation("Suc"){a => addSuc(a)}
  val Sum = Rel("$Sum")
  //--Private:
  private val AskInt = NativeRelation("$AskInt"){ a => askInt(a) }
  private val AddInt = NativeRelation("$AddInt"){ a => add(a) }
  private val PrintInt = NativeRelation("$PrintInt"){a => println(a(0) + "=" + a(1))}
  private val Error = NativeRelation("$ErrorInt"){a => throw new Exception("Invalid equal state:" + a(0) + "=" + a(1))}
//  private val AddSum = NativeRelation("$AddSum"){ a => addSum(a)}

  private val s,n,m,x,y,z = Var;
  private val K = RelVariable("K")
  private val X1,X2 = Tok()

//  private val AddInt = new NativeRelation("$AddInt", a => add(a)){
//    override def apply(vars:Variable*):Atom = new IntAtom(name, vars.toList)
//  }
  private val AddSum = new NativeRelation("$AddSum", a => addSum(a)){
    override def apply(vars:Variable*):Atom = new NumAtom(this.name,vars.toList)
  }
  rules(
    IntRel(n,x) ==> Abs(X1(n,x)) ? (X1(n,x) &: AddInt(n,x) &: IntRel(n,x)),
    (IntRel(n,x) &: Suc(x,y)) ==> (AddSum(n,1,y) &: IntRel(n,x)),
    (IntRel(n,x) &: IntRel(m,y) &: Sum(x,y,z)) ==> (AddSum(n,m,z) &: IntRel(n,x) &: IntRel(m,y)),
    (Int_print(x) &: IntRel(n,x)) ==> PrintInt(x,n)
  )

  /***********************************************************************/

  def getIntValue(x:Variable):Option[Int]= intEqClasses getValue x

  //Native functions
  implicit def int2Var(num:Int):Value[Int] = new Value[Int](num)

  private def add(a:Atom)= a match{
    case Atom(_, i::v::_) => intEqClasses add (i.toInt,v)
    case _ => //Nothing, wrong format
  }

  private def addSum(a:Atom) = a.vars match{
    case t1::t2::t3::Nil => introduceAtom(IntRel(Variable((t1.toInt+t2.toInt).toString),t3))
    case _ => //Skip
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
    case Atom(_, v1::v2::_) =>
      intEqClasses getValue v1 match{
        case Some(k) => intEqClasses add (k+1, v2)
        case _ => log(WARNING, this.getClass, "addSuc", "Variable " + v1 + " not found.") 
      }
    case _ =>
  }

  object sum {
    def apply(x:Variable, y:Variable):Variable = new SumVar[Variable,Variable](x,y)
    def apply(x:Variable, num:Int):Variable = new SumVar[Variable,Int](x,num)
  }

  case class SumVar[T,S](t1:T,t2:S) extends Variable(t1.toString+"+"+t2.toString)

  class NumAtom(name:String, vars:List[Variable]) extends Atom(name, vars){
    override def applySubstitutions(subs:List[Substitution]):Atom = {
      def replaceVar(variable:Variable):Variable = variable match{
        case vl:Value[Int] => vl
        case _ => find(variable.name)
      }
      def find(name:String):Variable = subs.find(s => s._1.name == name) match{
          case Some(v) => v._2
          case _ => Undef
      }
      val atom = new Atom(relName, vars.map {v => replaceVar(v)})
      atom.relation = this.relation
      atom
    }
  }
}
