package fr.emn.criojo.test

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 27, 2010
 * Time: 11:11:32 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.ext._
import fr.emn.criojo.loader.ScriptLoader
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._

//object PivotTests{
//  def suite: Test = {
//      val suite = new TestSuite(classOf[TypeSystemTest]);
//      suite
//  }
//
//  def main(args : Array[String]) {
//      junit.textui.TestRunner.run(suite);
//  }
//}

class PivotTests{//} extends TestCase("types"){
  logLevel = INFO

  import ScriptLoader._

  val machine = new LocalVM

  @Test(timeout=1000)
  def testLoad{
    val script =
    "(public:_; private:R)" +
            "!T => new(x) R(x)"
    load(machine,script)
    info(this.getClass, "testLoad", "rules: " + machine)

    assertTrue(machine.toString.indexOf("=>v(x).[true,$X1=>false]?$X1,R(x)") > 0)
  }

  @Test(timeout=1000)
  def testRun{
//    logLevel = DEBUG
    val script =
    "(public:_; private:R)" +
            "!T => new(x) R(x)"
    load(machine,script)
    info(this.getClass, "testRun", "rules: " + machine)
    machine.execute

    log("Final solution: " + machine.solution)
    assertEquals(2, machine.solution.size)
  }

  @Test(timeout=1000)
  def testInt{
    logLevel = DEBUG       
    load(machine,
      """(public:_; private:R)
            !T => R(123) |
            !T => R(123) 
      """
    )
    machine.execute

//    info(this.getClass, "testInt", "rules: " + machine)
//    info(this.getClass, "testInt", "solution: " + machine.solution /*.prettyPrint*/)
//    info(this.getClass, "testInt", "intEqClasses: " + machine.intEqClasses)

//    assertFalse(machine.solution.findMatches(List(IntAtom(123, Undef)),List()).isEmpty)
    machine.intEqClasses.get(123) match{
      case Some(ec) if (ec.size == 2) => assertTrue(true)
      case _ => fail("Value 123 not found in intEqClasses: " + machine.intEqClasses)
    }
  }

  @Test(timeout=1000)
  def testString{
    load(machine,
      "(public:_; private:S)" +
              "!T => S(\"Hola mundo\")"
    )
    machine.execute

    info(this.getClass, "testString", "solution: " + machine.solution /*prettyPrint*/)

    assertFalse(machine.solution.findMatches(List(StringAtom("Hola mundo", Undef)), List()).isEmpty)
  }

  @Test
  def testNull{
//    logLevel = DEBUG
    load(machine,
      "(public:_; private:S)" +
              "!T => S(null)"
    )
    machine.execute
    
    info(this.getClass, "testNull", "solution: " + machine.prettyPrint)

    assertTrue("<S(null)>" == machine.prettyPrint)
  }

  @Test(timeout=1000)
  def testPrint{
//    logLevel = DEBUG

    val vm = new LocalVM 
//                    VirtualMachine{
//      def newRemoteRelation(remoteName:String,url:String):RemoteRelation = null
//    }
    log("machine: " + vm.relations)
    
    load(vm,
      "(public:R; private:S)" +
              "!T => new(x) S(x) |" +
              "S(x) => Print(x)"
    )
    vm.execute
    assertTrue("Relation 'Print' not found.", vm.relations.contains(LocalRelation("Print")))
  }

  @Test(expected=classOf[java.lang.IllegalAccessException])
  def testRemoteRelation{
    load(machine,
    """
      (public:_;
      private:
        R,
        S@"http://localhost:8080/VM")
      !T => new(x) S(x)
    """)
    
  }
}