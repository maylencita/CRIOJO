package fr.emn.criojo.ext

import fr.emn.criojo.lang._
import fr.emn.criojo.core._

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 02/11/11
 * Time: 14:38
 */
trait IntegerCham extends EqCHAM{
  val IntVal = Rel("Int") //Like Val(x,n)
  val Int_ask = Rel("Int_ask")
  val Int_print = Rel("$Int_print")
  val Add = Rel("Add")
  val Subs = Rel("Subs")
  val Mod = Rel("Mod")
  val Leq_ask = Rel("Leq_ask")
  val Leq = Rel("Leq")
  private val Res = Rel("$Int.Res")
  private val LeqRes = Rel("$Int.LeqRes")
  private val mod = Rel("$Int.mod")

  val intEqClasses = new TypedEqClasses[Int](eqClasses,disjClasses)
  private val Declare = NativeRelation("$Declare"){ (a,s) => declare(a) }
  private val IntPrint = NativeRelation("$PrintInt"){(a,s) => println(a(0))}

  private val IntAdd = NativeRelation("$IntAdd"){ //(a,s) => add(a) }
    case (Atom(_,x::y::z::_), _) =>
      (getValue(x),getValue(y),getValue(z)) match{
        case (Some(v1),Some(v2),_) => introduceAtom(IntVal(z,v1+v2))
        case (None,Some(v1),Some(v2)) => introduceAtom(IntVal(x,v2-v1))
        case (Some(v1),None,Some(v2)) => introduceAtom(IntVal(x,v2-v1))
        case _ =>
      }
    case _ =>
  }

  private val CalculeLeq = NativeRelation("$CalculeLeq"){
    case (Atom(_,x::y::_), _) =>
      (getValue(x),getValue(y)) match{
        case (Some(v1),Some(v2)) if(v1 <= v2) => introduceAtom(Leq(x,y))
        case (Some(v1),Some(v2)) if(v1 > v2) => introduceAtom(Leq(y,x))
        case _ => //Err
      }
    case _ =>
  }

  private val CalculeMod = NativeRelation("$CalculeMod"){
    case (Atom(_,x::y::_), _) =>
      (getValue(x),getValue(y)) match{
        case (Some(v1),Some(v2)) if(v1 >= v2) => introduceAtom(mod(x,y,v1 % v2))
        case (Some(v1),Some(v2)) if(v1 < v2) => introduceAtom(mod(y,x,v2 % v1))
        case _ => //No val
      }
    case _ =>
  }

  {
    val K = VarR("K+")
    val Zero = VarR("K0")
    val Err = VarR("Err")
    val z,s,v = Var
    val x,y,n = Var
    val X1 = Rel("$Int.$X1")
    val ModRes = Rel("$Int.ModRes")

    rules(
      IntVal(x,n) --> Abs(X1(x))?:(Declare(x,n) & IntVal(x,n) & X1(x)),
      (Int_ask(s,x,K,Err) & IntVal(x,n)) --> (K(s,x,n) & IntVal(x,n)),
      Int_ask(s,x,K,Err) --> Abs(IntVal(x,n)) ?: Err(s,x),

      Add(s,x,y,K) --> Nu(z)(IntAdd(x,y,z) & Res(s,x,y,z,K)),
      (Res(s,x,y,z,K) & IntVal(z,v)) --> (K(s,x,y,v) & IntVal(z,v)),

      Subs(s,x,y,K) --> Nu(z)(IntAdd(z,y,x) & Res(s,x,y,z,K)),

      Mod(s,x,y,K,Zero) --> Nu(z)(CalculeMod(x,y) & ModRes(s,x,y,K,Zero) & ModRes(s,y,x,K,Zero)),
      (ModRes(s,x,y,K,Zero) & mod(x,y,0)) --> Zero(s,y),
      (ModRes(s,x,y,K,Zero) & mod(x,y,z)) --> Abs(mod(x,y,0)) ?: K(s,x,y,z),

      Leq_ask(s,x,y,K) --> (CalculeLeq(x,y) & LeqRes(s,x,y,K) & LeqRes(s,y,x,K)),
      (LeqRes(s,x,y,K) & Leq(x,y)) --> K(s,x,y),

      (Int_print(x) & IntVal(x,n)) --> IntPrint(n)
    )
  }

  def getIntValue(x:Variable):Option[Int]= intEqClasses getValue x

  implicit def num2fun(n:Int):Term = new ValueTerm[Int](n) //new IntTerm(n)

  private def getValue(t:Term):Option[Int] = t match{
    case ValueTerm(v:Int) => Some(v)
    case vr:Variable => getIntValue(vr) match{
      case Some(v) => Some(v)
      case _ => None
    }
    case _ => None
  }

  private def declare(a:Atom){ a match{
      case Atom(_, (v:Variable)::(Function(n,_))::_) => intEqClasses add (n.toInt,v) //TODO ValueTerm instead of Function?
      case _ => //Nothing, wrong format
    }
  }

//  private def add(a:Atom){ a match{
//    case Atom(_, IntTerm(v1)::IntTerm(v2)::v3::_) =>
//      introduceAtom(IntVal(v3,v1 + v2))
//    case Atom(_, (v1:Variable)::(v2:Variable)::v3::_) =>
//      introduceAtom(Add2(v1,v2,v3))
//    case Atom(_, v1::v2::v3::_) =>
//      introduceAtom(Add3(v1,v2,v3))
//    case _ =>
//    }
//  }

  case class IntTerm(n:Int) extends Function(n.toString,List[Term]()){
    override def apply(params: List[Term]) = new IntTerm(n)
  }

}