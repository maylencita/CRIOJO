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

class ChamTests{
  import Creole._

  logLevel = INFO
  
  val machine:CHAM = new CHAM{
    val x = Variable("x")
    val y = Variable("y")
    val z = Variable("z")
    val R = Rel("R")
    val S = Rel("S")

    rules(
      (R(x,y) &: R(y,z)) ==> R(x,z),
      S(x,y) ==> R(x,y)
    )
  }



  @Test
  def testRelations{
    val relLst = new LocalRelation("R") :: new LocalRelation("S") :: Nil
    log("relLst: " + relLst)
    for(r <- relLst){
      if (!machine.relations.contains(r))
        fail("Expected: " + relLst + " Actual: " + machine.relations)
    }
  }

  @Test
  def testRuleCreation{
    val x = Variable("x")
    val y = Variable("y")
    val z = Variable("z")
    val r1:Rule = new Rule{
      val head = List(Atom("R",x,y),Atom("R",y,z))
      val body = List(Atom("R",x,z))
      val guard = new Guard(List())
      def execute (subs:List[Substitution]):Boolean = true
    }
    val r2:Rule = new Rule{
      val head = Atom("S",x,y)::Nil
      val body = Atom("R",x,y)::Nil
      val guard = new Guard
      def execute (subs:List[Substitution]):Boolean = true
    }
    assertTrue("Rule "+ r1 +" not found! Existing rules: " + machine.rules, machine.rules.exists(_ == r1))
    assertTrue("Rule "+ r2 +" not found! Existing rules: " + machine.rules, machine.rules.exists(_ == r1))
  }

  @Test(timeout=1000)
  def testAtomInsertion{
    log("relations: " + machine.relations)
    log("rules: " + machine.rules)

    val a1 = Atom("R", Variable("a"),Variable("b"))
    val a2 = Atom("S", Variable("b"),Variable("c"))
    val a3 = Atom("R", Variable("a"),Variable("c"))

    machine.introduceAtom(a1)
    assertEquals(Solution(a1), machine.solution)

    machine.introduceAtom(a2)
    log(this.getClass, "testAtomInsertion", "solution: " + machine.solution)

    assertEquals(1, machine.solution.size)
    assertTrue(machine.solution.exists(a => a.relName == a3.relName && a.vars == a3.vars))
  }

  @Test(timeout=1000)
  def testGuard{
//    logLevel = DEBUG
    val a = Variable("a")
    val b = Variable("b")
    val c = Variable("c")
    val d = Variable("d")

    val m2 = new CHAM{
      val x,y,z = Var
      val R = Rel("R")
      val X1 = Rel("X1")

      rules(
        R(x,y) ==> {(T &: X1()) ==> F} ?: (R(x,y) &: R(x,y) &: X1())
      )
    }
    info (this.getClass, "testGuard", "m2: " + m2.rules.mkString("","\n",""))

    val atom = Atom("R", a, b) 
    m2.introduceAtom(atom)

    assertEquals(2, m2.query(List(atom,atom), List()).size)
    assertTrue("Expected: <R(a,b),R(a,b)>. Actual: " + m2.solution,
      m2.query(List(atom,atom), List()).size == 2)
  }

  @Test (timeout=1000)
  def testGuardSubs{
    logLevel = DEBUG
    val a = Variable("a")
    val b = Variable("b")
    val c = Variable("c")
    val d = Variable("d")

    val m2 = new CHAM{
      val s,x,y,z = Var
      val R = Rel("R")
      val X1 = Rel("X1")

      rules(
        R(s,x,y) ==> {(T(s) &: X1(s)) ==> F} ?: (R(s,x,y) &: R(s,x,y) &: X1(s))
      )
    }
    info (this.getClass, "testGuard", "m2: " + m2.rules.mkString("","\n",""))

    val atom = Atom("R", Variable("1"), a, b)
    val atom2 = Atom("R", Variable("2"), a, b)
    m2.introduceAtom(atom)
    m2.introduceAtom(atom2)
    
    assertTrue("Expected: <R(1,a,b),R(1,a,b),R(2,a,b),R(2,a,b)>. Actual: " + m2.solution,
      m2.query(List(atom,atom,atom2,atom2), List()).size == 4)
  }

  @Test (timeout=1000)
  def testNu{
    logLevel = DEBUG

    var result = false
    val a = Variable("a")
    val b = Variable("b")

    val vm = new CHAM{
      val x,y,z,w = Var
      val S = Rel("S")
      val R = NativeRelation("R"){
        case Atom("R", a::v2::v3::_) if (v2 != Undef && v3 != Undef) => result = true
        case at => fail("Expected: R(x1,x2,x3). Actual: " + at)
      }

      rules{
        S(x) ==> Nu(y,z)(R(x,y,z))
      }
    }
    info (this.getClass, "testNu", "vm: " + vm.rules.mkString("","\n",""))

//    val atom = Atom("R", a, b)
    vm.introduceAtom(Atom("S", a))
//    vm.introduceAtom(atom)

    assertTrue("Expected: <R(x1,x2,x3)>. Actual: " + vm.solution, result)

  }

  @Test
  def testHORelation{
    logLevel = DEBUG
    
    val a = Variable("a")
    val b = Variable("b")
    var result = false

    val vm = new CHAM{
      val x,y,z,w = Var
      val Cont = RelVariable("Cont")

      val R = Rel("R"); val S = Rel("S")
      val Resp = NativeRelation("Resp"){
        case Atom("Resp", a::b::_) => result = true
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
    assertTrue("Expected: <Resp(a,b)>. Actual: " + vm.solution, result)

  }

}