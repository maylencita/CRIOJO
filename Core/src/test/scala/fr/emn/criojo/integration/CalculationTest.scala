package fr.emn.criojo.integration

import org.junit.Test
import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.lang.{Nu, Cham}
import fr.emn.criojo.core._
import collection.mutable.Buffer
import java.io.FileWriter
import fr.emn.criojo.ext.expressions.{UndefinedExpression, BooleanExpression, Expression, IntExpression}
import physics.PhysicCham
import processing.core._
import processing.opengl._
import javax.media.opengl._
import physics.PhysicsSketch;

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

  @Test
  def FibonnaciNewSyntaxTest() {

    var fw = new FileWriter("etoile.svg");
    fw.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n")

    implicit def LazyGuard(x: => Expression):CriojoGuard = {
      val g = new CriojoGuard(List()){
        def eval(vals: Valuation) = {

          val valuation = x.eval(vals)
          valuation.isInstanceOf[BooleanExpression] && valuation.asInstanceOf[BooleanExpression].getValue()
        }
      }
      g
    }

    implicit def LazyExpression(x: => Expression):Expression = {
      val g = new Expression {

        def name = x.name
        def matches(that: Term): Boolean = false

        def eval(): Expression = x.eval()

        def applyValuation(vals:Valuation): Expression = x.applyValuation(vals) match {
          case e:Expression => e
          case _ => UndefinedExpression
        }

        def getValuation(t:Term):Valuation = x.getValuation(t)
      }
      g
    }

    val cm = new Cham with IntegerCham {

      val fibo = Rel("fibo")
      val Fibo = Rel("Fibo")
      val WaitResult = Rel("WaitResult")
      val Result = Rel("Result")
      val Bingo = Rel("Bingo")

      val n,n1,n2,r,r1,r2,x = Var
      val v,v1,v2 = Var

      val Sierpinski = Rel("Sierpinski")
      val y, z, a, b, c, lp, xp1, xp2, yp, np, l, vx, vy, vl = Var

      val Print = NativeRelation("Print3") {

        case ((Atom(_, (x: IntExpression) :: (y: IntExpression) :: (l: IntExpression) :: _), _)) => {

          fw.write("<polygon points=\"" + x.getValue() + "," + y.getValue() + " " + (x.getValue() - l.getValue()) + "," + (y.getValue() - l.getValue()) + " " + (x.getValue() + l.getValue()) + "," + (y.getValue() - l.getValue()) + "\" style=\"fill:lime;stroke:purple;stroke-width:2\"/>\n")
        }
      }

      rules(
        fibo(n) --> Nu(r)(Fibo(n,r) & WaitResult(r,n)),
        Fibo(n,r) --> (Abs(Result(r,n)) && {n>1}) ?: (Fibo(n-1,r) & Fibo(n-2,r)),
        (Result(n1,v1) & Result(n2,v2)) --> ({n1==(n2+1)}) ?: Result(n1+1,v1+v2),
        Fibo(n,r) --> {n < num2fun(2)} ?: Result(n,1),
        (WaitResult(r,n) & Result(n,v)) --> Bingo(v)
      )

      DEBUG_MODE = true
    }

    import cm.num2fun

    cm.introduceMolecule(cm.fibo(14))
    cm.executeRules()

    cm.getSolution.displaySolution()
    cm.printTrace()
    
    
    fw.write("</svg>\n")
    fw.close()
  }

  @Test /*(timeout=3000)*/
  def fibonacciIterative{
    val fCham = new Cham with IntegerCham{
      val AskFib = Rel("AskFib")
      val Rep = Rel("Rep")
      val Fib = Rel("Fib")
      val Res = Rel("Res")

      val MPrint = NativeRelation("MPrint"){
        case (Atom(_,x::_),_) => println(x)
        case _ =>
      }

      val MPrint_var = VarR("MPrint_var")

      val K = VarR("K+")
      val n,n1,n2,r,r1,r2,r3,s = Var
      val v,v1,v2 = Var

      rules(
        AskFib(n,K) --> Nu(s)(Fib(s,0) & Fib(s,1) & Rep(s, n, K)),
        Fib(s, 0) --> Res(s, 0, 0),
        Fib(s, 1) --> Res(s, 1, 1),
        //(Rep(s,n,K) &: Res(s, n1, r1) &: Res(s, n1+1, r2)) --> ((Gr(n,n1+1) || Eq(n,n1+1)))  ?: (Rep(s,n,K) & Res(s, n1+1, r2) & Res(s,  n1+2, r1+r2)),
        (Rep(s,n,K) &: Res(s, n1, r1) &: Res(s, n2, r2)) --> ((Gr(n,n2) || Eq(n,n2)) && Eq(n2,n1+1))  ?: (Rep(s,n,K) & Res(s, n2, r2) & Res(s,  n1+2, r1+r2)),
        (Rep(s,n,K) &: Res(s,n,r)) --> K(r),
        MPrint_var(r) --> MPrint(r)
      )
    }
    import fCham.{num2fun}

    fCham.MPrint_var.relation = fCham.MPrint
    fCham.introduceMolecule(fCham.AskFib(6, fCham.MPrint_var))
    fCham.executeRules()
    println(fCham.getSolution)
    // println(fCham.printRules)
  }

  @Test /*(timeout=3000)*/
  def gcdTestExp{
    val chemicalMachine = new Cham with IntegerCham{
      val gcd = Rel("gcd")
      val Result = Rel("Resultat")

      val PrintInt = NativeRelation("PrintInt"){
        case (Atom(_,x::_),_) => println(x)
      }

      val x,y = Var


      rules(
        gcd(x,y) --> Less(x,y) ?: gcd(y,x),
        gcd(x,y) --> Gr(x,y) ?: gcd(x-y,y),
        gcd(x,y) --> Eq(x,y) ?: (Result(y) & PrintInt(y))
      )
    }
    import chemicalMachine.{num2fun,gcd}
    chemicalMachine.introduceMolecule(gcd(8,4))
    chemicalMachine.executeRules()

  }

  @Test /*(timeout=3000)*/
  def gcdTestExpCustomGuards{

    implicit def LazyGuard(x: => Expression):CriojoGuard = {
      val g = new CriojoGuard(List()){
        def eval(vals: Valuation) = {

          val valuation = x.eval(vals)
          valuation.isInstanceOf[BooleanExpression] && valuation.asInstanceOf[BooleanExpression].getValue()
        }
      }
      g
    }

    implicit def LazyExpression(x: => Expression):Expression = {
      val g = new Expression {

        def name = x.name
        def matches(that: Term): Boolean = false

        def eval(): Expression = x.eval()

        def applyValuation(vals:Valuation): Expression = x.applyValuation(vals) match {
          case e:Expression => e
          case _ => UndefinedExpression
        }

        def getValuation(t:Term):Valuation = x.getValuation(t)
      }
      g
    }

    val chemicalMachine = new Cham with IntegerCham{
      val gcd = Rel("gcd")
      val Result = Rel("Resultat")

      val PrintInt = NativeRelation("PrintInt"){
        case (Atom(_,x::_),_) => println(x)
      }

      val x,y = Var

      rules(
        gcd(x,y) --> {x < y} ?: gcd(y,x),
        gcd(x,y) --> {
          var i=2;
          println("in the guard...");
          x > y} ?: gcd(LazyExpression({println("in the body...");x-y}),y),
        gcd(x,y) --> {x == y} ?: (Result(y) & PrintInt(y))
      )
    }
    import chemicalMachine.{num2fun,gcd}
    chemicalMachine.introduceMolecule(gcd(13,4))
    chemicalMachine.executeRules()

  }

  @Test
  def PaintSierpinskiTest() {

    var fw = new FileWriter("etoile.svg");
    fw.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n")

    implicit def LazyGuard(x: => Expression):CriojoGuard = {
      val g = new CriojoGuard(List()){
        def eval(vals: Valuation) = {

          val valuation = x.eval(vals)
          valuation.isInstanceOf[BooleanExpression] && valuation.asInstanceOf[BooleanExpression].getValue()
        }
      }
      g
    }

    implicit def LazyExpression(x: => Expression):Expression = {
      val g = new Expression {

        def name = x.name
        def matches(that: Term): Boolean = false

        def eval(): Expression = x.eval()

        def applyValuation(vals:Valuation): Expression = x.applyValuation(vals) match {
          case e:Expression => e
          case _ => UndefinedExpression
        }

        def getValuation(t:Term):Valuation = x.getValuation(t)
      }
      g
    }

    val cm = new Cham with IntegerCham {

      val Sierpinski = Rel("Sierpinski")
      val x, y, z, a, b, c, lp, xp1, xp2, yp, n, np, l, vx, vy, vl = Var

      val Print = NativeRelation("Print3") {

        case ((Atom(_, (x: IntExpression) :: (y: IntExpression) :: (l: IntExpression) :: _), _)) => {

          fw.write("<polygon points=\"" + x.getValue() + "," + y.getValue() + " " + (x.getValue() - l.getValue()) + "," + (y.getValue() - l.getValue()) + " " + (x.getValue() + l.getValue()) + "," + (y.getValue() - l.getValue()) + "\" style=\"fill:lime;stroke:purple;stroke-width:2\"/>\n")
        }
      }

      rules(
        (Sierpinski(x, y, l, n)) --> {n == num2fun(0)} ?: Print(x,y,l),

        Sierpinski(x, y, l, n)
          --> {n>0} ?: (
                         Sierpinski(x, y, l/2, n-1)
                       & Sierpinski(x-l/2, y-l/2, l/2, n-1)
                       & Sierpinski(x+l/2, y-l/2, l/2, n-1)
                      )
      )
      //DEBUG_MODE = true
    }

    import cm.num2fun

    cm.introduceMolecule(cm.Sierpinski(700, 700, 700, 8))
    cm.executeRules()

    fw.write("</svg>\n")
    fw.close()
  }

  @Test
  def PaintWithProcessingSierpinskiTest() {



    implicit def LazyGuard(x: => Expression):CriojoGuard = {
      val g = new CriojoGuard(List()){
        def eval(vals: Valuation) = {

          val valuation = x.eval(vals)
          valuation.isInstanceOf[BooleanExpression] && valuation.asInstanceOf[BooleanExpression].getValue()
        }
      }
      g
    }

    implicit def LazyExpression(x: => Expression):Expression = {
      val g = new Expression {

        def name = x.name
        def matches(that: Term): Boolean = false

        def eval(): Expression = x.eval()

        def applyValuation(vals:Valuation): Expression = x.applyValuation(vals) match {
          case e:Expression => e
          case _ => UndefinedExpression
        }

        def getValuation(t:Term):Valuation = x.getValuation(t)
      }
      g
    }


    //CircleSketch.launch()
    var container:ProcessingAppletContainer[CanDrawTriangle] = ProcessingAppletContainer(new Sketch3D())
    container.launch()

    val cm = new Cham with IntegerCham {

      val Sierpinski = Rel("Sierpinski")
      val x, y, z, a, b, c, lp, xp1, xp2, yp, n, np, l, vx, vy, vl = Var

      val Print = NativeRelation("Print3") {

        case ((Atom(_, (x: IntExpression) :: (y: IntExpression) :: (l: IntExpression) :: _), _)) => {

          container.out.drawTriangle(x.getValue(), y.getValue(), l.getValue())
        }
      }

      rules(
        (Sierpinski(x, y, l, n)) --> {n == num2fun(0)} ?: Print(x,y,l),

        Sierpinski(x, y, l, n)
          --> {n>0} ?: (
                        Sierpinski(x, y, l/2, n-1)
                        &Sierpinski(x-l/2, y-l/2, l/2, n-1)
                        &Sierpinski(x+l/2, y-l/2, l/2, n-1)
                      )
      )
      //DEBUG_MODE = true
    }

    import cm.num2fun

    cm.introduceMolecule(cm.Sierpinski(700, 700, 700, 8))
    cm.executeRules()

    println("Done")
    
    while(!container.out.asInstanceOf[PApplet].finished) {

      Thread.sleep(1000)
    }
  }

  @Test
  def PhysicTest() {

    var sketch = new PhysicsSketch()
    var container:ProcessingAppletContainer[PhysicsSketch] = ProcessingAppletContainer(sketch)
    container.launch()

    println("Done")

    while(!container.out.asInstanceOf[PApplet].finished) {

      Thread.sleep(1000)
    }
  }
}

trait ProcessingApplet {

//  var instance:ProcessingApplet = null
//
//  def createInstance():ProcessingApplet
//
//  def getInstance():ProcessingApplet = {
//    if(instance==null)
//      instance = createInstance()
//    instance
//  }

  def launch() {

//    this match {
//      case p:PApplet => PApplet.runSketch(Array("Test"), p)
//      case _ => throw new Exception("type incorrect: I need a PApplet")
//    }

    this match {
      case p:PApplet => PApplet.runSketch(Array("Test"), new Sketch3D())
      case _ => throw new Exception("type incorrect: I need a PApplet")
    }
  }
}


trait CanDrawTriangle {

  def drawTriangle(x:Int, y:Int, l:Int)
}

case class CircleSketch() extends PApplet with ProcessingApplet with CanDrawTriangle {

  override def setup() {
    size(640, 480);
    smooth();
    noStroke();
  }

  def drawTriangle(x:Int, y:Int, l:Int) {

    triangle(x, y, x+l, y-l, x-l, y-l);
  }
  
  override def draw() {
  }
}

case class ProcessingAppletContainer[T](val instance:T) {

  def launch() {

    instance match {
      case p:PApplet => PApplet.runSketch(Array("Test"), p)
      case _ => throw new Exception("type incorrect: I need a PApplet")
    }
  }

  def out:T = instance
}