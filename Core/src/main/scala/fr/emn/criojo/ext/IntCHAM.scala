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
import Criojo._
import fr.emn.criojo.lang.{NativeRelation, CrjAtom}

trait IntCHAM extends EqCHAM{

  val intEqClasses = new TypedEqClasses[Int](eqClasses,disjClasses)

  /**********************************************************************
  * VM definition:
  */
  //--Public:
  val IntRel = Rel("$Int", (vars:List[Variable]) => new NumAtom("$Int", vars))
  val Int_ask = NativeRelation("$Int_ask"){ (a,s) => askInt(a) }
  val Int_print = Rel("$Int_print")
  val Suc = Rel("$Suc")
  val Sum = Rel("$Sum")
  //--Private:
  private val AskInt = NativeRelation("$AskInt"){ (a,s) => askInt(a) }
  private val AddInt = NativeRelation("$AddInt"){ (a,s) => add(a) }
  private val PrintInt = NativeRelation("$PrintInt"){(a,s) => println(a(0) + "=" + a(1))}
  private val Error = NativeRelation("$ErrorInt"){(a,s) => throw new Exception("Invalid equal state:" + a(0) + "=" + a(1))}

  private val AddSum = new NativeRelation("$AddSum", this.solution, (a,s) => addSum(a)){
    addRelation(this)
    override def apply(vars:Variable*) = new NumAtom(this.name,vars.toList)
  }

  private val s,n,m,x,y,z = Var;
  private val K = VarR("K")
  private val X1,X2 = Tok()

  rules(
    IntRel(n,x) --> Abs(X1(n,x)) ?: (X1(n,x) &: AddInt(n,x) &: IntRel(n,x)),
    (IntRel(n,x) &: Suc(x,y)) --> (AddSum(n,1,y) &: IntRel(n,x)),
    (IntRel(n,x) &: IntRel(m,y) &: Sum(x,y,z)) --> (AddSum(n,m,z) &: IntRel(n,x) &: IntRel(m,y)),
    (Int_print(x) &: IntRel(n,x)) --> PrintInt(x,n)
  )

  /***********************************************************************/

  def getIntValue(x:Variable):Option[Int]= intEqClasses getValue x

  //Native functions
  implicit def int2Var(num:Int):Value[Int] = new Value[Int](num)

  private def add(a:Atom):Boolean = a match{
    case Atom(_, i::v::_) => intEqClasses add (i.toInt,v); true
    case _ => false //Nothing, wrong format
  }

  private def addSum(a:Atom):Boolean = a.vars match{
    case t1::t2::t3::_ => introduceAtom(IntRel(Variable((t1.toInt+t2.toInt).toString),t3)); true
    case _ => false //Skip
  }

  private def askInt(a:Atom):Boolean = a match{
    case Atom(_, num::session::vr::k::_) =>
      intEqClasses.get(num.toInt) match{
        case Some(vlst) if(vlst contains vr) => introduceAtom(Atom(k.toString, session, vr)); true
        case _ => false //Nothing or negative answer
      }
    case _ => false
  }

  private def addSuc(a:Atom):Boolean = a match{
    case Atom(_, v1::v2::_) =>
      intEqClasses getValue v1 match{
        case Some(k) => intEqClasses add (k+1, v2); true
        case _ => log(WARNING, this.getClass, "addSuc", "Variable " + v1 + " not found."); true
      }
    case _ => false
  }

  object sum {
    def apply(x:Variable, y:Variable):Variable = new SumVar[Variable,Variable](x,y)
    def apply(x:Variable, num:Int):Variable = new SumVar[Variable,Int](x,num)
  }

  case class SumVar[T,S](t1:T,t2:S) extends Variable(t1.toString+"+"+t2.toString)

  class NumAtom(name:String, vars:List[Variable]) extends CrjAtom(name, vars){//Atom(name, vars){
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
