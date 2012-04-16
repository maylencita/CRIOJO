package fr.emn.criojo.ext

import expression._
import expression.types.CriojoInteger
import fr.emn.criojo.lang._
import fr.emn.criojo.core._
import factory.DefaultFactory

import expression.converters._
/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 02/11/11
 * Time: 14:38
 */
trait IntegerCham extends EqCHAM with DefaultFactory{
  val IntVal = Rel("Int") //Like Val(x,n)
  val Int_ask = Rel("Int_ask")
  val Int_print = Rel("$Int_print")
  val Add = Rel("Add")
  val Sub = Rel("Subs")
  val Mod = Rel("Mod")
  val Leq_ask = Rel("Leq_ask")
  val Leq = Rel("Leq")
  private val Res = Rel("$Int.Res")
  private val LeqRes = Rel("$Int.LeqRes")
  private val mod = Rel("$Int.mod")
  private val IntAdd2 = Rel("$Int.add2")

  val intEqClasses = new TypedEqClasses[Int](eqClasses,disjClasses)
  private val Declare = NativeRelation("$Declare"){ (a,s) => declare(a) }
  private val IntPrint = NativeRelation("$PrintInt"){
    (a,s) => {
      var t = (a, s)
      println(a(0))
    }
    //    case x => {
    //      var t = x
    //      println(x)
    //    }
  }
  private val IntSub2 = Rel("$IntSubs2")
  private val IntDiv2 = Rel("$IntDivs2")

  protected val IntAdd = NativeRelation("$IntAdd"){ //(a,s) => add(a) }
    case (Atom(_,a::b::c::_), _) =>
      (getValue(a),getValue(b),getValue(c)) match{
        case (Some(v1),Some(v2),_) => introduceAtom(IntVal(c,v1+v2))
        case _ => introduceAtom(IntAdd2(a,b,c))
      }
    case _ =>
  }
  protected val IntSub = NativeRelation("$IntSubs"){
    case (Atom(_,a::b::c::_), _) =>
      (getValue(a),getValue(b)) match{
        case (Some(v1),Some(v2)) => {
          introduceAtom(IntVal(c,SomeTerm(CriojoInteger(v1-v2))))
        }
        case _ => introduceAtom(IntSub2(a,b,c))
      }
    case _ =>
  }

  protected val IntDiv = NativeRelation("$IntDivs"){
    case (Atom(_,a::b::c::_), _) =>
      (getValue(a),getValue(b)) match{
        case (Some(v1),Some(v2)) => introduceAtom(IntVal(c,v1/v2))
        case _ => introduceAtom(IntDiv2(a,b,c))
      }
    case _ =>
  }

  private val CalculeLeq = NativeRelation("$CalculeLeq"){
    case (Atom(_,a::b::_), _) =>
      (getValue(a),getValue(b)) match{
        case (Some(v1),Some(v2)) if(v1 <= v2) => introduceAtom(Leq(a,b))
        case (Some(v1),Some(v2)) if(v1 > v2) => introduceAtom(Leq(b,a))
        case _ => //Err
      }
    case _ =>
  }

  private val CalculeMod = NativeRelation("$CalculeMod"){
    case (Atom(_,a::b::_), _) =>
      (getValue(a),getValue(b)) match{
        case (Some(v1),Some(v2)) if(v1 >= v2) => introduceAtom(mod(a,b,v1 % v2))
        case (Some(v1),Some(v2)) if(v1 < v2) => introduceAtom(mod(b,a,v2 % v1))
        case _ => //No val
      }
    case _ =>
  }

  {
    val K = VarR("K+")
    val Zero = VarR("K0")
    val Err = VarR("Err")
    val z,s,v,v1,v2 = Var
    val x,y,n = Var
    val X1 = Rel("$Int.$X1")
    val ModRes = Rel("$Int.ModRes")

    rules(
      IntVal(x,n) --> Abs(X1(x))?:(Declare(x,n) & IntVal(x,n) & X1(x)),
      (Int_ask(s,x,K,Err) & IntVal(x,n)) --> (K(s,x,n) & IntVal(x,n)),
      Int_ask(s,x,K,Err) --> Abs(IntVal(x,n)) ?: Err(s,x),

      Add(s,x,y,K) --> Nu(z)(IntAdd(x,y,z) & Res(s,x,y,z,K)),
      (Res(s,x,y,z,K) & IntVal(z,v)) --> (K(s,x,y,v) & IntVal(z,v)),
      (IntAdd2(x,y,z)  & IntVal(x,v1) & IntVal(y,v2) )-->
        /*(Ex(IntVal(x,v1)) || Ex(IntVal(y,v2))) ?:*/ (IntAdd(v1,v2,z) & IntVal(x,v1) & IntVal(y,v2) ),

      Sub(s,x,y,K) --> Nu(z)(IntSub(x,y,z) & Res(s,x,y,z,K)),
      (IntSub2(x,y,z) & IntVal(x,v1) & IntVal(y,v2)) -->
        /*(Ex(IntVal(x,v1)) || Ex(IntVal(y,v2))) ?:*/ (IntSub(v1,v2,z)& IntVal(x,v1) & IntVal(y,v2)),

      Mod(s,x,y,K,Zero) --> Nu(z)(CalculeMod(x,y) & ModRes(s,x,y,K,Zero) & ModRes(s,y,x,K,Zero)),
      (ModRes(s,x,y,K,Zero) & mod(x,y,0)) --> Zero(s,y),
      (ModRes(s,x,y,K,Zero) & mod(x,y,z)) --> Abs(mod(x,y,0)) ?: K(s,x,y,z),

      Leq_ask(s,x,y,K) --> (CalculeLeq(x,y) & LeqRes(s,x,y,K) & LeqRes(s,y,x,K)),
      (LeqRes(s,x,y,K) & Leq(x,y)) --> K(s,x,y),

      (Int_print(x) & IntVal(x,n)) --> (IntPrint(n) & IntVal(x,n))
    )
  }

  def Gr(t1:Term, t2:Term):ChamGuard = {
    val g = new CriojoGuard with ChamGuard {
      override def eval(vals: Valuation) = {
        //greaterThan(applySubstitution(t1,subs),applySubstitution(t2,subs))
        greaterThan(t1.applyValuation(vals), t2.applyValuation(vals))
      }

      def valuation(valuation:Valuation) = {
        if(greaterThan(t1.applyValuation(valuation), t2.applyValuation(valuation))) {
          valuation
        }
        else {
          new ValuationList()
        }
      }

      val valuations = new ValuationList()
      val observed = Set[String]()
      def receiveUpdate(atom: Atom){}
    }
    g
  }

  def Less(t1:Term, t2:Term):ChamGuard = {
    val g = new CriojoGuard with ChamGuard {
      override def eval(vals: Valuation) = {
        lessThan(t1.applyValuation(vals), t2.applyValuation(vals))
      }

      def valuation(valuation:Valuation) = {
        if(lessThan(t1.applyValuation(valuation), t2.applyValuation(valuation))) {
          valuation
        }
        else {
          new ValuationList()
        }
      }

      val valuations = new ValuationList()
      val observed = Set[String]()
      def receiveUpdate(atom: Atom){}
    }
    g
  }

  def Leq(t1:Term, t2:Term):ChamGuard = {
    val g = new CriojoGuard with ChamGuard {
      override def eval(vals: Valuation) = {
        lessThanOrEqual(t1.applyValuation(vals), t2.applyValuation(vals))
      }

      def valuation(valuation:Valuation) = {
        if(lessThanOrEqual(t1.applyValuation(valuation), t2.applyValuation(valuation))) {
          valuation
        }
        else {
          new ValuationList()
        }
      }

      val valuations = new ValuationList()
      val observed = Set[String]()
      def receiveUpdate(atom: Atom){}
    }
    g
  }

  def greaterThan(t1:Term, t2:Term):Boolean = {
    (getValue(t1),getValue(t2)) match{
      case (Some(v1),Some(v2)) => v1 > v2
      case _ => false //no information
    }
  }

  def lessThan(t1:Term, t2:Term):Boolean = {
    (getValue(t1),getValue(t2)) match{
      case (Some(v1),Some(v2)) => v1 < v2
      case _ => false //no information
    }
  }

  def lessThanOrEqual(t1:Term, t2:Term):Boolean = {
    (getValue(t1),getValue(t2)) match{
      case (Some(v1),Some(v2)) => v1 <= v2
      case _ => false //No val
    }
  }

  def getIntValue(x:Variable):Option[Int]= {
    val truc = genEqClasses
    genEqClasses.getValue(x) match{
    case Some(v) => v match {
      case n:Int => Some(n)
      case _ => None
    }
    case _ => None
  }
}
  //implicit def num2fun(n:Int):Term = new IntExpression(n) //new IntTerm(n)

  private def getValue(t:Term):Option[Int] = t match{
    case SomeTerm(CriojoInteger(n)) => Some(n)
    case vr:Variable => getIntValue(vr) match{
      case Some(v) => Some(v)
      case _ => None
    }
    case id:IdTerm => getIntValue(Variable(id.name))  match{
      case Some(v) => Some(v)
      case _ => None
    }
    case _ => None
  }

  private def declare(a:Atom){ a match{
    case Atom(_, (v:Variable)::(ValueTerm(n:Int))::_) => genEqClasses.add(n,v) //intEqClasses add (n.toInt,v)
    case Atom(_, (id:IdTerm)::(SomeTerm(CriojoInteger(n)))::_) => genEqClasses.add(n,Variable(id.name)) //intEqClasses add (n.toInt,v)
    case _ => //Nothing, wrong format
  }
  }

}