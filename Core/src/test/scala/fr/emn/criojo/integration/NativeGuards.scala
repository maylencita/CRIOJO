package fr.emn.criojo.integration

import org.specs2.mutable._
import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.ext.expressions.{EqualExpr, GreaterThanExpr, LessThanExpr, Expression}
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.core.{ValueTerm, Atom, CriojoGuard}

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 06/04/12
 * Time: 11:03
 */

trait IntChamWithNative extends IntegerCham{
  implicit def nativeGuard(exp: => Expression):CriojoGuard = exp match{
    case lt:LessThanExpr => Less(lt.t1,lt.t2)
    case gr:GreaterThanExpr => Gr(gr.t1,gr.t2)
    case eqv:EqualExpr => Eq(eqv.t1,eqv.t2)
    case _ => new CriojoGuard{
      def receiveUpdate(atom: Atom){}
      def observed = Set[String]()
    }
  }
}

object GCD{
  def apply(a:Int,b:Int):Int = {
    if(b == 0)
      a
    else
      GCD(b, a % b)
  }
}

class NativeGuardGCDSpec extends Specification{
  val a = 7
  val b = 3
  val expected = GCD(a,b)
  "Result" should{
    "be " + expected in{
      var result=0
      val gcdCham = new Cham with IntChamWithNative{
        val gcd = Rel
        val Result = NativeRel{
          case ValueTerm(n:Int)::_ => result = n
          case _ =>
        }
        val x,y = Var

        rules(
          gcd(x,y) --> {x < y} ?: gcd(y,x),
          gcd(x,y) --> {x > y} ?: gcd(x-y,y),
          gcd(x,y) --> {x == y} ?: (Result(y))
        )
      }
      import gcdCham.{gcd,num2fun}
      gcdCham.introduceMolecule(gcd(a,b))
      gcdCham.executeRules()

      result === expected
    }
  }
}
