package fr.emn.criojo.core

import org.junit.Test
import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.lang.{Nu, Cham}

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 15/02/12
* Time: 15:10
*/
class CalculationTest {
  @Test (timeout=3000)
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
        (Fib(n,r) & IntVal(n,v)) --> Leq(v,1) ?: (IntVal(r,v) & IntVal(n,v)),
        Fib(n,r) -->
          Gr(n, 1) ?: Nu(n1,n2,r1,r2)(
            IntSub(n,1,n1) & IntSub(n,2,n2) & IntAdd(r1,r2,r) & Fib(n1,r1) & Fib(n2,r2) )
      )
    }
    import fCham.{num2fun,fib}
    fCham.introduceMolecule(fib(4))
    fCham.executeRules()
//    println(fCham.printRules)
  }

}