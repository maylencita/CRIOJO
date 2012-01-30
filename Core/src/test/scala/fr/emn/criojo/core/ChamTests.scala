package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 28, 2010
 * Time: 7:45:45 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._
import fr.emn.criojo.lang._

/*
trait DefaultCham extends CHAM{
  def createGuard(ruleDefs:List[RuleFactory => Rule]):Guard = {
    if (ruleDefs isEmpty)
      new EmptyGuard
    else
      new Guard with DefaultCham{
        initRules(ruleDefs.toList)
      }
  }
}

class TestCham extends CHAM with DefaultCham{}
*/

class ChamTests{
  import Criojo._

  logLevel = INFO
  
  val machine = new Cham { //TestCham with DefaultCham{
    val x,y,z = Var
    val R = Rel("R")
    val S = Rel("S")

    rules(
      (R(x,y) &: R(y,z)) --> R(x,z),
      S(x,y) --> R(x,y)
    )
  }

  @Test
  def testSyntax{
    val mv = new Cham{
      val x,y,z = Var
      val R = Rel("R")
      val S = Rel("S")
      val X1,X2 = Tok()

//      rules(
//        (R(x,y) &: R(y,z)) ==> R(x,z),
//        S(x,y) ==> R(x,y),
//        R(x,y) ==> guard(T(x,y), (T(x,y) &: X1(x,y)) ==> F) ?: (R(x,y) &: R(x,y) &: X1(x,y))
//      )

      when(R(x,y) &: R(y,z)){
        S(x,y)
      }
      when(R(x,y) &: R(y,z)){
        R(x,z) &: S(x,y)
      }
      when(R(x,y)){
        If(Abs(X1(x,y))){
          (R(x,y) &: R(x,y) &: X1(x,y))
        }
      }
      when(S(x,y)){
        If(Abs(X2(x,y))){
          (S(x,y) &: S(x,y) &: X2(x,y))
        }
      }
    }
    println("mv: " + mv.printRules)
  }

//  @Test
//  def testRelations{
//    val relLst = new LocalRelation("R") :: new LocalRelation("S") :: Nil
//    log("relLst: " + relLst)
//    for(r <- relLst){
//      if (!machine.relations.contains(r))
//        fail("Expected: " + relLst + " Actual: " + machine.relations)
//    }
//  }

//  @Test
//  def testRuleCreation{
//    val x = Variable("x")
//    val y = Variable("y")
//    val z = Variable("z")
//    val r1:Rule = new Rule{
//      val head = List(Atom("R",x,y),Atom("R",y,z))
//      val body = List(Atom("R",x,z))
//      val guard = new Guard(List())
//      def executeRules (subs:List[Substitution]):Boolean = true
//      def notifyCham(a:Atom){}
//    }
//    val r2:Rule = new Rule{
//      val head = Atom("S",x,y)::Nil
//      val body = Atom("R",x,y)::Nil
//      val guard = new Guard
//      def executeRules (subs:List[Substitution]):Boolean = true
//      def notifyCham(a:Atom){}
//    }
//    assertTrue("Rule "+ r1 +" not found! Existing rules: " + machine.rules, machine.rules.exists(_ == r1))
//    assertTrue("Rule "+ r2 +" not found! Existing rules: " + machine.rules, machine.rules.exists(_ == r1))
//  }

  @Test(timeout=1000)
  def testAtomInsertion{
    logLevel = DEBUG

//    log("relations: " + machine.relations)
    log("rules: " + machine.printRules)

    val a1 = Atom("R", Variable("a"),Variable("b"))
    val a2 = Atom("S", Variable("b"),Variable("c"))
    val a3 = Atom("R", Variable("a"),Variable("c"))

    machine.introduceAtom(a1)
    //TODO rewrite test
//    assertEquals(StandAloneSolution(List(a1)), machine.solution)

    machine.introduceAtom(a2)
//    log(this.getClass, "testAtomInsertion", "solution: " + machine.solution)

    //TODO rewrite tests
//    assertEquals(1, machine.solution.size)
//    assertTrue(machine.solution.exists(a => a.relName == a3.relName && a.terms == a3.terms))
  }

  @Test(timeout=500)
  def testGuard{
    logLevel = DEBUG
    val a = Variable("a")
    val b = Variable("b")
    val c = Variable("c")
    val d = Variable("d")

    val m2 = new Cham{
      val x,y,z = Var
      val R = Rel("R")
      val X1 = Rel("X1")

//      rules(
//        R(x,y) ==> guard(T(x,y), (T(x,y) &: X1(x,y)) ==> F) ?: (R(x,y) &: R(x,y) &: X1(x,y))
////        R(x,y) ==> {(T &: X1()) ==> F} ?: (R(x,y) &: R(x,y) &: X1())
//      )
      when(R(x,y)){
        If(Abs(X1(x,y))){
          R(x,y) &: R(x,y) &: X1(x,y)
        }
      }
    }
    info (this.getClass, "testGuard", "m2: " + m2.printRules)

    val atom = Atom("R", a, b) 
    m2.introduceAtom(atom)

//    assertEquals(2, m2.query(List(atom,atom), List()).size)
      //TODO rewrite test
//    assertTrue("Expected: <R(a,b),R(a,b)>. Actual: " + m2.solution,
//      m2.query(List(atom,atom), List((a,a),(b,b))).size == 2)
  }

  @Test (timeout=1000)
  def testGuardSubs{
    logLevel = DEBUG
    val a = Variable("a")
    val b = Variable("b")
    val c = Variable("c")
    val d = Variable("d")

    val m2 = new Cham{
      val s,x,y,z = Var
      val R = Rel("R")
      val X1 = Rel("X1")

      rules(
        R(s,x,y) --> Abs(X1(s)) ?: (R(s,x,y) &: R(s,x,y) &: X1(s))
      )
    }
    info (this.getClass, "testGuard", "m2: " + m2.printRules)

    val atom = Atom("R", Variable("1"), a, b)
    val atom2 = Atom("R", Variable("2"), a, b)
    m2.introduceAtom(atom)
    m2.introduceAtom(atom2)

    //TODO rewrite test
//    assertTrue("Expected: <R(1,a,b),R(1,a,b),R(2,a,b),R(2,a,b)>. Actual: " + m2.solution,
//      m2.query(List(atom,atom,atom2,atom2), List()).size == 4)
  }

  @Test (timeout=1000)
  def testNu{
    logLevel = DEBUG

    var result = false
    val a = Variable("a")
    val b = Variable("b")

    val vm = new Cham{
      val x,y,z,w = Var
      val S = Rel("S")
      val R = NativeRelation("R"){
        case (Atom("R", a::v2::v3::_),s) if (v2 != Undef && v3 != Undef) => result = true
        case at => fail("Expected: R(x1,x2,x3). Actual: " + at)
      }

      rules{
        S(x) ==> Nu(y,z)(R(x,y,z))
      }
    }
    info (this.getClass, "testNu", "vm: " + vm.printRules)

//    val atom = Atom("R", a, b)
    vm.introduceAtom(Atom("S", a))
//    vm.introduceAtom(atom)

    //TODO rewrite test
//    assertTrue("Expected: <R(x1,x2,x3)>. Actual: " + vm.solution, result)
  }

  @Test (timeout=1000)
  def testAbs{
    val vm = new Cham{
      val s,x,y,z,w = Var
      val R = Rel("R")
      val X1 = Rel("X1")

      rules(
        R(s,x,y) ==> Abs(X1(s)) ?: (R(s,x,y) &: R(s,x,y) &: X1(s))
      )
    }

      val a = Variable("a"); val b= Variable("b")
      val atom = Atom("R", Variable("1"), a, b)
      val atom2 = Atom("R", Variable("2"), a, b)
      vm.introduceAtom(atom)
      vm.introduceAtom(atom2)

    //TODO rewrite test
//      assertTrue("Expected: <R(1,a,b),R(1,a,b),R(2,a,b),R(2,a,b)>. Actual: " + vm.solution,
//        vm.query(List(atom,atom,atom2,atom2), List()).size == 4)
  }

  @Test (timeout=1000)
  def testHORelation{
    logLevel = DEBUG
    
    val a = Variable("a")
    val b = Variable("b")
    var result = false

    val vm = new Cham{
      val x,y,z,w = Var
      val Cont = Rel("Cont")

      val R = Rel("R"); val S = Rel("S")
      val Resp = NativeRelation("Resp"){
        case (Atom("Resp", a::b::_),s) => result = true
        case resp => fail("Expected atom: Resp(a,b). Actual: " + resp)
      }
      val RespVar = RelVariable(Resp)

      rules(
        S(x,y) ==> R(x,y,RespVar),
        R(x,y,Cont) ==> Cont(x,y)
      )
    }

    val atom1 = Atom("S", a, b)
    vm.introduceAtom(atom1)
    //TODO rewrite test
//    assertTrue("Expected: <Resp(a,b)>. Actual: " + vm.solution, result)

  }

  @Test (timeout=1000)
  def testTerms{
//    logLevel = DEBUG

    var result = false

    //Test with nums: n ::= 0 | S(n)
    val m2 = new Cham{
      val Add = Rel("Add")
      val S = Fun("S")
      private val Sum = Rel("Sum")
      private val Add2 = Rel("Add2")
      private val Res = Rel("Res")
      private val Val = Rel("Val")
      private val Suc = Rel("Suc")
      private val K = VarR("K")
      private val s,i,x,y,z,z2,v = Var
      private val X = Tok()
      private val Print = NativeRelation("Print"){(a,s) =>
        result = true
        a.terms.foreach(print(_) + " ")
      }

      rules(
        Add(s,x,y,K) --> Nu(i,z)(Res(s,i,x,y,z,K) &: Add2(i,x,y,z)),
        Add2(i,0,y,z) --> Val(i,z,y),
        Add2(i,S(x),y,z) --> Nu(z2)(Suc(z2,z) &: Add2(i,x,y,z2)),
        (Val(i,z,v) &: Suc(z,z2)) --> Val(i,z2,S(v)),
        (Res(s,i,x,y,z,K) &: Val(i,z,v)) --> K(s,x,y,v),

        Empty --> Abs(X()) ?: Nu(s)(Add(s,S(S(0)),S(0),Sum) &: X()),
        Sum(s,x,y,v) --> Print(v)
      )

      implicit def num2fun(n:Int):Term =
        if(n == 0) new Function("0", List[Term]())
        else{
            new Function("S", List(num2fun(n-1)))
        }
    }

    m2.executeRules()

    //TODO rewrite test
//    assertTrue("Expected: <Sum(s,S(S(0)),S(0),S(S(S(0)))>. Actual: " + m2.solution, result)
  }


  @Test (timeout=1000)
  def simpleTest2(){
    val sm = new Cham2{
      val A = Rel("A")
      val B = Rel("B")
      val C = Rel("C")
      val D = Rel("D")

      rules(
        (A() & B() & C()) --> D(),
        (D() & C()) --> A()
      )
    }

    sm.introduceMolecule(sm.A())
    sm.introduceMolecule(sm.B())
    sm.introduceMolecule(sm.C())
    sm.introduceMolecule(sm.C())
    println(sm.getSolution)
  }
}