package fr.emn.criojo.test

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 27, 2010
 * Time: 11:11:32 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core.{Atom,Variable}
import fr.emn.criojo.ext._
import fr.emn.criojo.loader.ScriptLoader
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._

class PivotTests{//} extends TestCase("types"){
  logLevel = INFO

  import ScriptLoader._

  val a = Variable("a"); val b = Variable("b"); val c = Variable("c")
  val x = Variable("x"); val y = Variable("y"); val z = Variable("z"); val w = Variable("w")

  val machine = new LocalVM

  @Test(timeout=1000)
  def testRun{
//    logLevel = DEBUG
    val script =
    "(local:R)" +
         "!T => new(x) R(x)"
    load(machine,script)
    info(this.getClass, "testRun","relations: " + machine.relations)
    info(this.getClass, "testRun", "rules: " + machine)
    machine.execute

    info(this.getClass, "testRun","Final solution: " + machine.solution)
    assertEquals(2, machine.solution.size)
  }

  @Test(timeout=1000)
  def testInt{
    logLevel = DEBUG       
    load(machine,
      """(local:R)
            !T => R(123) |
            !T => R(123) 
      """
    )
    machine.execute

//    info(this.getClass, "testInt", "rules: " + machine)
//    info(this.getClass, "testInt", "solution: " + machine.solution /*.prettyPrint*/)
//    info(this.getClass, "testInt", "intEqClasses: " + machine.intEqClasses)

    machine.intEqClasses.get(123) match{
      case Some(ec) if (ec.size == 2) => assertTrue(true)
      case _ => fail("Value 123 not found in intEqClasses: " + machine.intEqClasses)
    }
  }

  @Test(timeout=1000)
  def testString{
    load(machine,
      "(local:S)" +
              "!T => S(\"Hola mundo\")"
    )
    machine.execute

    info(this.getClass, "testString", "solution: " + machine.solution /*prettyPrint*/)
    info(this.getClass, "testString", "rules: " + machine.rules.mkString("","\n","") )

    machine.strEqClasses.get("Hola mundo") match{
      case Some(ec) if (ec.size == 1) => assertTrue(true)
      case _ => fail("Value \"Hola mundo\" not found in strEqClasses: " + machine.strEqClasses)
    }
  }

  @Test(timeout=1000)
  def testNull{
//    logLevel = DEBUG
    load(machine,
      """
      (local:S,Eqq)
        !S(x,y),S(y2,z) => new(s)Eq_ask(s,y,y2,Eqq),S(x,y),S(y2,z)
        | S(x,y),S(y2,z),Eqq(s,y,y2) => S(x,z)
      """
    )
    info(this.getClass, "testNull", "relations: " + machine)

    machine.introduceAtom(Atom("Null", w))
    machine.introduceAtom(Atom("Null", y))
    machine.introduceAtom(Atom("S", y, c))
    machine.introduceAtom(Atom("S", a, w))

    info(this.getClass, "testNull", "solution: " + machine.prettyPrint)
    info(this.getClass, "testNull", "nullVars: " + machine.nullVars)
    info(this.getClass, "testNull", "eqClasses: " + machine.eqClasses)

    assertEquals("<S(a,c)>",machine.prettyPrint)
  }

  @Test(timeout=1000)
  def testPrint{
//    logLevel = DEBUG

    val vm = new LocalVM 
    log("machine: " + vm.relations)
    
    load(vm,
      """
      (provided:R; local:S)
         !T => new(x) S(x), S("Hola") |
         S(x) => Print(x)
      """
    )
    vm.execute
    info(this.getClass, "testPrint", "solution: " + vm.solution)
    info(this.getClass, "testPrint", "rules: " + vm.rules.mkString("","\n","") )
    info(this.getClass, "testPrint", "intEqClasses: " + vm.intEqClasses)
    info(this.getClass, "testPrint", "strEqClasses:" + vm.strEqClasses)

//    assertTrue("Relation 'Print' not found.", vm.relations.contains(LocalRelation("Print")))
  }

  @Test(expected=classOf[java.lang.IllegalAccessException])
  def testRemoteRelation{
    load(machine,
    """
      (local: R;
       required: S@"http://localhost:8080/VM")
        !T => new(x) S(x)
    """)
    
  }

  @Test (timeout=1000)
  def testGuards{
    load(machine,
      """
      (local:S,X1)
      T => [true,X1=>false]? new(x) S(x),X1
      """)

    machine.execute

    info(this.getClass, "testEqGuard", "machine: " + machine)
    info(this.getClass, "testEqGuard", "solution: " + machine.solution)

    assertTrue("Expected solution: S(x). Found: " + machine.prettyPrint,
      machine.query(List(Atom("S",Variable("x"))),List()).size == 1)
  }

  @Test
  def testEqGuard{
    logLevel = DEBUG

    load(machine,
      """
      (local:S)
//        S(x,y), S(y2,z) => [true(y,y2)=>new(s)Eq_ask(s,y,y2,Eqq) Eqq(s,y,y2) => true]? S(x,z)
        S(x,y), S(y2,z) => [y = y2]? S(x,z)          
      """)

    machine.introduceAtom(Atom("S", a,b))
    machine.introduceAtom(Atom("S", b,c))

    info(this.getClass, "testEqGuard", "machine: " + machine)
    info(this.getClass, "testEqGuard", "solution: " + machine.solution)

    assertTrue("Expected solution: <S(a,c)>. Found: " + machine.prettyPrint,
      machine.query(List(Atom("S",a,c)),List((a,a),(c,c))).size == 1)

    machine.introduceAtom(Atom("S", z,w))
    info(this.getClass, "testEqGuard", "solution: " + machine.solution)

    assertTrue("Expected solution: <S(a,c),S(z,w)>. Found: " + machine.prettyPrint,
      machine.query(List(Atom("S", a,c),Atom("S",z,w)),List()).size == 2)
  }

  @Test (timeout=1000)
  def testAbsGuard{
    load(machine,
      """
      (local:R,X1)
        T => Abs[X1]? new(x) R(x),X1
      """)
    machine.execute

    info(this.getClass, "testRun","Final solution: " + machine.solution)
    assertEquals(2, machine.solution.size)
  }
}