package fr.emn.criojo.integration

import org.junit.Test
import fr.emn.criojo.lang.{Nu, Cham}
import fr.emn.criojo.core._
import java.io.FileWriter
import fr.emn.criojo.ext.IntegerCham

import fr.emn.criojo.core.Converters._
import fr.emn.criojo.ext.debug.DebugCham
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.ext.expression.ScalaInt.ScalaInt
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 15/02/12
* Time: 15:10
*/


class CalculationTest {


//  @Test /*(timeout=3000)*/
//  def fibonacciTest(){
//    val fCham = new Cham with IntegerCham{
//      val fib = LocalRelation("fib")
//      val Fib = LocalRelation("Fib")
//
//      val n,n1,n2,r,r1,r2,v,x,y,z = VarInt
//
//
//      rules(
//        fib(n) --> Nu(r)(Fib(n,r) & Int_print(r)),
//        (Fib(n,r) & IntVal(n,v)) --> Leq(v,1) ?: (IntVal(r,v) /*& IntVal(n,v)*/),
//        Fib(n,r) -->
//          Gr(n, 1) ?: Nu(n1,n2,r1,r2)(
//            IntSub(n,1,n1) & IntSub(n,2,n2) & IntAdd(r1,r2,r) & Fib(n1,r1) & Fib(n2,r2) )
//      )
//    }
////    import fCham.{num2fun,fib}
//    fCham.introduceMolecule(fCham.fib(8))
//    fCham.executeRules()
//    //println(fCham.printRules)
//  }


  @Test /*(timeout=3000)*/
  def gcdTest(){
    val fCham = new Cham with IntegerCham with DebugCham {
      val gcd = LocalRelation("gcd")
      val Result = LocalRelation("Resultat")

      val n,n1,n2,r,r1,r2,v,x,y,xNew = VarInt


      rules(
        gcd(x,y) --> {x LessThan y} ?: gcd(y,x),
        gcd(x,y) --> {x GreaterThan y} ?: gcd(x -y,y),
        gcd(x,y) --> {x Equal y} ?: Result(y)
      )
    }
    fCham.enableStreamingTrace()
    fCham.enableSolutionTrace()
    fCham.introduceMolecule(fCham.gcd(8,2))
    fCham.executeRules()
    fCham.printSolution()
  }

//  @Test /*(timeout=3000)*/
//  def fibonacciWithMemTest(){
//    val fCham = new Cham with IntegerCham with DebugCham {
//      val fib = LocalRelation("fib")
//      val Fib = LocalRelation("Fib")
//      val FEq = LocalRelation("FEq")
//
//      val n,n1,n2,r,r1,r2,x = VarInt
//      val v,v1,v2 = VarInt
//
//      rules(
//        fib(n) --> Nu(r)(Fib(n,r) & Int_print(r)),
//        (Fib(n,r) & IntVal(n,v)) --> Leq(v,1) ?: (IntVal(r,v)),
//        (Fib(n,r1) & Fib(n,r2) & IntVal(r1,v)) --> (IntVal(r2,v) & IntVal(r1,v)),
//        Fib(n,r) -->
//          (Abs(Fib(n,v)) && Gr(n, 1)) ?: Nu(n1,n2,r1,r2)(
//            IntSub(n,1,n1) & IntSub(n,2,n2) & IntAdd(r1,r2,r) & Fib(n1,r1) & Fib(n2,r2) )
//      )
//    }
//    import fCham.{num2fun,fib}
//    fCham.enableSolutionTrace()
//    fCham.enableStreamingTrace()
//
//    fCham.introduceMolecule(fCham.fib(5))
//    fCham.executeRules()
//    // println(fCham.printRules)
//  }

  @Test
  def FibonnaciNewSyntaxTest() {

    var fw = new FileWriter("etoile.svg");
    fw.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n")

    val cm = new Cham with IntegerCham with DebugCham {

      val fibo = LocalRelation("fibo")
      val Fibo = LocalRelation("Fibo")
      val WaitResult = LocalRelation("WaitResult")
      val Result = LocalRelation("Result")
      val Bingo = LocalRelation("Bingo")
      val Session = LocalRelation("Session")

      val n,n1,n2,r,r1,r2,x,s = VarInt
      val v,v1,v2 = VarInt

      val y, z, a, b, c, lp, xp1, xp2, yp, np, l, vx, vy, vl = VarInt

      rules(
        (fibo(n) & Session(s)) --> (Fibo(n,s) & WaitResult(s,n) & Session(s+1)),
        Fibo(n,r) --> (Abs(Result(r,n)) && {n GreaterThan 1}) ?: (Fibo(n-1,r) & Fibo(n-2,r)),
        (Result(n1,v1) & Result(n2,v2)) --> ({n1 Equal (n2+1)}) ?: Result(n1+1,v1+v2),
        Fibo(n,r) --> {n LessThan  2} ?: Result(n,1),
        (WaitResult(r,n) & Result(n,v)) --> Bingo(v)
      )
    }

//    import cm.num2fun
    cm.enableSolutionTrace()
    cm.enableStreamingTrace()

    cm.introduceMolecule(cm.Session(0))
    cm.introduceMolecule(cm.fibo(14))
    cm.executeRules()

    cm.printSolution()

    
    fw.write("</svg>\n")
    fw.close()
  }

//  @Test /*(timeout=3000)*/
//  def fibonacciIterative(){
//    val fCham = new Cham with IntegerCham{
//      val AskFib = LocalRelation("AskFib")
//      val Rep = LocalRelation("Rep")
//      val Fib = LocalRelation("Fib")
//      val Res = LocalRelation("Res")
//
//      val MPrint = NativeRelation("MPrint"){
//        case (Atom(_,a::_),_) => println(a)
//        case _ =>
//      }
//
//      val MPrint_var = VarR("MPrint_var")
//
//      val K = VarR("K+")
//      val n,n1,n2,r,r1,r2,r3,s = VarInt
//      val v,v1,v2 = VarInt
//
//      rules(
//        AskFib(n,K) --> Nu(s)(Fib(s,0) & Fib(s,1) & Rep(s, n, K)),
//        Fib(s, 0) --> Res(s, 0, 0),
//        Fib(s, 1) --> Res(s, 1, 1),
//        //(Rep(s,n,K) &: Res(s, n1, r1) &: Res(s, n1+1, r2)) --> ((Gr(n,n1+1) || Eq(n,n1+1)))  ?: (Rep(s,n,K) & Res(s, n1+1, r2) & Res(s,  n1+2, r1+r2)),
//        (Rep(s,n,K) &: Res(s, n1, r1) &: Res(s, n2, r2)) --> ((Gr(n,n2) || Eq(n,n2)) && Eq(n2,n1+1))  ?: (Rep(s,n,K) & Res(s, n2, r2) & Res(s,  n1+2, r1+r2)),
//        (Rep(s,n,K) &: Res(s,n,r)) --> K(r),
//        MPrint_var(r) --> MPrint(r)
//      )
//    }
////    import fCham.{num2fun}
//
//    fCham.MPrint_var.relation = fCham.MPrint
//    fCham.introduceMolecule(fCham.AskFib(6, fCham.MPrint_var))
//    fCham.executeRules()
//    println(fCham.getSolution)
//    // println(fCham.printRules)
//  }

  @Test /*(timeout=3000)*/
  def gcdTestExp(){
    val chemicalMachine = new Cham with IntegerCham{
      val gcd = LocalRelation("gcd")
      val Result = LocalRelation("Resultat")

      val PrintInt = NativeRelation("PrintInt"){
        case (Atom(_,a::_),_) => println(a)
        case _ =>
      }

      val x,y = VarInt


      rules(
        gcd(x,y) --> {x LessThan y} ?: gcd(y,x),
        gcd(x,y) --> {x GreaterThan y} ?: gcd(x-y,y),
        gcd(x,y) --> {x Equal y} ?: (Result(y) & PrintInt(y))
      )
    }
//    import chemicalMachine.{num2fun,gcd}
    chemicalMachine.introduceMolecule(chemicalMachine.gcd(8,4))
    chemicalMachine.executeRules()

  }

  @Test /*(timeout=3000)*/
  def gcdTestExpCustomGuards(){



    val chemicalMachine = new Cham with IntegerCham{
      val gcd = LocalRelation("gcd")
      val Result = LocalRelation("Resultat")

      val PrintInt = NativeRelation("PrintInt"){
        case (Atom(_,a::_),_) => println(a)
        case _ =>
      }

      val x,y = VarInt

      rules(
        gcd(x,y) --> {x LessThan y} ?: gcd(y,x),
        gcd(x,y) --> {
          var i=2;
          println("in the guard...");
          x GreaterThan y} ?: gcd(LazyExpression({println("in the body...");x-y}),y),
        gcd(x,y) --> {x Equal y} ?: (Result(y) & PrintInt(y))
      )
    }
//    import chemicalMachine.{num2fun,gcd}
    chemicalMachine.introduceMolecule(chemicalMachine.gcd(13,4))
    chemicalMachine.executeRules()

  }

  @Test
  def HydrolyseTestRelations() {


    val machine = new Cham with IntegerCham with DebugCham {
      //TestCham with DefaultCham{
      val n1, n2, n3, n4, n5 = VarInt
      val R1_COO_R2 = LocalRelation("R1_COO_R2")
      val R2_OH = LocalRelation("R2_OH")
      val R1_COOH = LocalRelation("R1_COOH")
      val H2O = LocalRelation("H2O")
      val COO = LocalRelation("COO")
      val R1 = LocalRelation("R1")
      val R2 = LocalRelation("R2")
      val C = LocalRelation("C")
      val O = LocalRelation("O")
      val H = LocalRelation("H")

      //      implicit def int2term(n: Int): Expression = IntExpression(n)

      val EXPLODE_R1_COO_R2 = LocalRelation("EXPLODE_R1_COO_R2")
      val EXPLODE_H2O = LocalRelation("EXPLODE_H2O")

      rules(

        // INITIATION
        (R1_COO_R2(n1) &: H2O(n2)) --> {ScalaInt.Min(n1, n2) GreaterThan 0} ?: (R1_COO_R2(n1 - ScalaInt.Min(n1, n2)) & H2O(n2 - ScalaInt.Min(n1, n2)) & EXPLODE_R1_COO_R2(ScalaInt.Min(n1, n2)) & EXPLODE_H2O(ScalaInt.Min(n1, n2))),

        // DIVISION
        (EXPLODE_R1_COO_R2(n1) &: R1(n2) &: R2(n3) &: COO(n4)) --> (R1(n2 + n1) & R2(n3 + n1) & COO(n4 + n1)),
        (EXPLODE_H2O(n1) &: H(n2) &: O(n3)) --> (H(n2 + n1*2) & O(n3 + n1)),

        // SUB DIVISION
        (COO(n1) &: C(n2) &: O(n3)) --> {n1 GreaterThan 0} ?: (COO(0) & C(n2 + n1) & O(n3 + n1*2)),

        // RECOMPOSITION
        (R2(n1) &: O(n2) &: H(n3) &: R2_OH(n4)) --> {ScalaInt.Min(n1, n2, n3) GreaterThan 0} ?: (R2(n1 - ScalaInt.Min(n1, n2, n3)) & O(n2 - ScalaInt.Min(n1, n2, n3)) & H(n3 - ScalaInt.Min(n1, n2, n3)) & R2_OH(n4 + ScalaInt.Min(n1, n2, n3))),
        (R1(n1) &: C(n2) &: O(n3) &: H(n4) &: R1_COOH(n5)) --> {ScalaInt.Min(n1, n2, n3 / 2, n4) GreaterThan 0} ?: (R1_COOH(n5 + ScalaInt.Min(n1, n2, n3 / 2, n4)) & R1(n1 - ScalaInt.Min(n1, n2, n3 / 2, n4)) & C(n2 - ScalaInt.Min(n1, n2, n3 / 2, n4)) & O(n3 -  ScalaInt.Min(n1, n2, n3 / 2, n4)) & H(n4 -ScalaInt.Min(n1, n2, n3 / 2, n4)))
      )
    }

    //    import machine.int2term
    machine.enableSolutionTrace()
    machine.enableStreamingTrace()

    machine.introduceMolecule(machine.R1_COO_R2(1))
    machine.introduceMolecule(machine.H2O(2))
    machine.introduceMolecule(machine.R2_OH(0))
    machine.introduceMolecule(machine.R1_COOH(0))
    machine.introduceMolecule(machine.COO(0))
    machine.introduceMolecule(machine.R1(0))
    machine.introduceMolecule(machine.R2(0))
    machine.introduceMolecule(machine.C(0))
    machine.introduceMolecule(machine.O(0))
    machine.introduceMolecule(machine.H(0))
    machine.executeRules()

    assert(machine.containsMolecule(machine.R1_COO_R2(0)))
    assert(machine.containsMolecule(machine.H2O(1)))
    assert(machine.containsMolecule(machine.R1_COOH(1)))
  }

  @Test
  def MapReduceTest() {

    val machine = new Cham with IntegerCham with DebugCham {
      //TestCham with DefaultCham{
      val w = VarString
      val z, n, i, j, kp, km = VarInt
      val InsertWord = LocalRelation("InsertWord")
      val Count = LocalRelation("Count")

      val PrintInt = NativeRelation("PrintInt") {
        case (Atom(_, a :: _), _) => println(a)
        case _ =>
      }

      rules(
        InsertWord(w) --> Count(w, 1), // MAP
        (Count(w, i) & Count(w, j)) --> Count(w, i + j) // REDUCE
      )
    }

    //import machine.num2fun
    //    implicit def stringToValue(s: String) = StrExpression(s)

    machine.enableSolutionTrace()

    machine.introduceMolecule(machine.InsertWord("aa"))
    machine.introduceMolecule(machine.InsertWord("aa"))
    machine.introduceMolecule(machine.InsertWord("bb"))
    machine.introduceMolecule(machine.InsertWord("bb"))
    machine.introduceMolecule(machine.InsertWord("cc"))
    machine.introduceMolecule(machine.InsertWord("aa"))
    machine.introduceMolecule(machine.InsertWord("bb"))
    machine.introduceMolecule(machine.InsertWord("dd"))
    machine.executeRules()

    assert(machine.getSolution.containsMolecule(machine.Count("aa", 3)))
    assert(machine.getSolution.containsMolecule(machine.Count("cc", 1)))
  }

  @Test
  def DjikstraTest() {

    val machine = new Cham with IntegerCham with DebugCham {
      //TestCham with DefaultCham{
      val x, y, z, w  = VarString
      val n,s,i, j = VarInt

      val AreConnected = LocalRelation("LeadsTo")
      val AreInRelation = LocalRelation("AreInRelation")
      val IsItEquivalent = LocalRelation("IsItEquivalent")
      val Session = LocalRelation("Session")

      val PUSH = LocalRelation("PUSH")
      val POP = LocalRelation("POP")

      val PrintInt = NativeRelation("PrintInt") {
        case (Atom(_, a :: _), _) => /*println(x)*/
        case _ =>
      }

      rules(
        (IsItEquivalent(x, y) & Session(s)) --> (AreInRelation(x, y, s) & Session(s+1)),
        (AreInRelation(x, w, n) & AreConnected(x, y) & Session(s)) --> {x NotEqual y} ?: (AreInRelation(x, w, n) & PUSH(s, n, x) & AreInRelation(y, w, s) & Session(s+1)),
        AreInRelation(x, y, n) --> {x Equal y} ?: (PrintInt(x) & POP(n)),
        (POP(j) &: PUSH(i, n, x)) --> {i Equal j} ?: (PrintInt(x) & POP(n))
      )
    }

    //    import machine.num2fun

    //    implicit def str2fun(s: String): Term = new StrExpression(s)
    machine.enableSolutionTrace()

    machine.introduceMolecule(machine.Session(0))
    machine.introduceMolecule(machine.AreConnected("A", "B"))
    machine.introduceMolecule(machine.AreConnected("B", "C"))
    machine.introduceMolecule(machine.AreConnected("C", "D"))
    machine.introduceMolecule(machine.AreConnected("D", "E"))
    machine.introduceMolecule(machine.AreConnected("B", "D"))
    machine.introduceMolecule(machine.AreConnected("D", "F"))

    machine.introduceMolecule(machine.IsItEquivalent("A", "E"))
    machine.executeRules()

    assert(machine.containsMolecule(machine.PrintInt("A")))
    assert(machine.containsMolecule(machine.PrintInt("B")))
    assert(machine.containsMolecule(machine.PrintInt("D")))
    assert(machine.containsMolecule(machine.PrintInt("E")))

    //    assert(machine.containsMolecule(machine.PrintInt("C"), 0)) // C is not in the shortest, but it can appear in the solution
  }

  @Test
  def PaintSierpinskiTest() {

    var fw = new FileWriter("etoile.svg");
    fw.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n")



    val cm = new Cham with IntegerCham {

      val Sierpinski = LocalRelation("Sierpinski")
      val x, y, z, a, b, c, lp, xp1, xp2, yp, n, np, l, vx, vy, vl = VarInt

      val Print = NativeRelation("Print3") {

        case ((Atom(_, WrapScalaInt(xvalue) :: WrapScalaInt(yvalue) :: WrapScalaInt(lvalue) :: _), _)) => {
          fw.write("<polygon points=\"" + xvalue + "," + yvalue + " " + (xvalue - lvalue) + "," + (yvalue - lvalue) + " " + (xvalue + lvalue) + "," + (yvalue - lvalue) + "\" style=\"fill:lime;stroke:purple;stroke-width:2\"/>\n")
        }
        case _ =>
      }

      rules(
        (Sierpinski(x, y, l, n)) --> {n Equal 0} ?: Print(x,y,l),

        Sierpinski(x, y, l, n)
          --> {n GreaterThan 0} ?: (Sierpinski(x, y, l/2, n-1) & Sierpinski(x-l/2, y-l/2, l/2, n-1) & Sierpinski(x+l/2, y-l/2, l/2, n-1))
      )
      //DEBUG_MODE = true
    }

//    import cm.num2fun

    cm.introduceMolecule(cm.Sierpinski(700, 700, 700, 7))
    cm.executeRules()

    fw.write("</svg>\n")
    fw.close()
  }
}
