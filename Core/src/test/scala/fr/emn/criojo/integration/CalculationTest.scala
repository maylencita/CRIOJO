package fr.emn.criojo.integration

import org.junit.Test
import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.lang.{Nu, Cham}
import fr.emn.criojo.core._
import collection.mutable.Buffer

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 15/02/12
* Time: 15:10
*/


class CalculationTest {


  @Test /*(timeout=3000)*/
  def fibonacciTest{
    val fCham = new Cham with IntegerCham{
      val fib = Rel("fib")
      val Fib = Rel("Fib")

      val MPrint = NativeRelation("2"){
        case (Atom(_,x::y::z::_),_) => println(x + "," + y + "," + z)
        case _ =>
      }

      val n,n1,n2,r,r1,r2,v,x = Var


      rules(
        fib(n) --> Nu(r)(Fib(n,r) & Int_print(r)),
        (Fib(n,r) & IntVal(n,v)) --> Leq(v,1) ?: (IntVal(r,v) /*& IntVal(n,v)*/),
        Fib(n,r) -->
          Gr(n, 1) ?: Nu(n1,n2,r1,r2)(
            IntSub(n,1,n1) & IntSub(n,2,n2) & IntAdd(r1,r2,r) & Fib(n1,r1) & Fib(n2,r2) )
      )
    }
    import fCham.{num2fun,fib}
    fCham.introduceMolecule(fib(8))
    fCham.executeRules()
    //println(fCham.printRules)
  }


  @Test /*(timeout=3000)*/
  def gcdTest{
    val fCham = new Cham with IntegerCham{
      val gcd = Rel("gcd")
      val Result = Rel("Resultat")

      val MPrint = NativeRelation("2"){
        case (Atom(_,x::y::z::_),_) => println(x + "," + y + "," + z)
        case _ =>
      }

      val n,n1,n2,r,r1,r2,v,x,y,xNew = Var


      rules(
        gcd(x,y) --> Less(x,y) ?: gcd(y,x),
        gcd(x,y) --> Gr(x,y) ?: Nu(xNew)(IntSub(x,y,xNew) & gcd(xNew,y)),
        gcd(x,y) --> Eq(x,y) ?: Result(y)
      )
    }
    import fCham.{num2fun,gcd}
    fCham.introduceMolecule(gcd(8,2))
    fCham.executeRules()
    // println(fCham.printRules)
  }

  @Test /*(timeout=3000)*/
  def fibonacciWithMemTest{
    val fCham = new Cham with IntegerCham{
      val fib = Rel("fib")
      val Fib = Rel("Fib")
      val FEq = Rel("FEq")

      val MPrint = NativeRelation("2"){
        case (Atom(_,x::y::z::_),_) => println(x + "," + y + "," + z)
        case _ =>
      }

      val n,n1,n2,r,r1,r2,x = Var
      val v,v1,v2 = Var

      rules(
        fib(n) --> Nu(r)(Fib(n,r) & Int_print(r)),
        (Fib(n,r) & IntVal(n,v)) --> Leq(v,1) ?: (IntVal(r,v)),
        (Fib(n,r1) & Fib(n,r2) & IntVal(r1,v)) --> (IntVal(r2,v) & IntVal(r1,v)),
        Fib(n,r) -->
          (Abs(Fib(n,v)) && Gr(n, 1)) ?: Nu(n1,n2,r1,r2)(
            IntSub(n,1,n1) & IntSub(n,2,n2) & IntAdd(r1,r2,r) & Fib(n1,r1) & Fib(n2,r2) )
      )
    }
    import fCham.{num2fun,fib}
    fCham.introduceMolecule(fib(5))
    fCham.executeRules()
    // println(fCham.printRules)
  }

  @Test /*(timeout=3000)*/
  def gcdTest{
    val fCham = new Cham with IntegerCham{
      val gcd = Rel("gcd")
      val Result = Rel("Resultat")

      val MPrint = NativeRelation("2"){
        case (Atom(_,x::y::z::_),_) => println(x + "," + y + "," + z)
        case _ =>
      }

      val n,n1,n2,r,r1,r2,v,x,y,xNew = Var


      rules(
        gcd(x,y) --> Less(x,y) ?: gcd(y,x),
        gcd(x,y) --> Gr(x,y) ?: Nu(xNew)(IntSub(x,y,xNew) & gcd(xNew,y)),
        gcd(x,y) --> Eq(x,y) ?: Result(y)
      )
    }
    import fCham.{num2fun,gcd}
    fCham.introduceMolecule(gcd(8,2))
    fCham.executeRules()
    // println(fCham.printRules)

  }
}
