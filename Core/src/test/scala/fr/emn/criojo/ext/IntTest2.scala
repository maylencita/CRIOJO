package fr.emn.criojo.ext

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 03/11/11
 * Time: 14:39
 */
import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._
import fr.emn.criojo.lang.{Nu, Empty, Cham}

class IntTest2 {
  val a = Variable("a")
  val b = Variable("b")
  val c = Variable("c")
  val s = Variable("s")

  @Test (timeout=1000)
  def testDeclare(){
//    logLevel = DEBUG

    var declared = 0
    val mchn = new Cham with IntegerCham{
      val Test = Rel("Test")
      val Err = Rel("$$ERR")
      val Init = Rel("Init")
      val x,y,s = Var
      val IsInt = NativeRelation("IsInt"){(a,s) => declared += 1}

      rules(
        Init(x,y) --> (IntVal(x,7) & IntVal(y,6) & Test(x,y)),
        Test(x,y) --> Nu(s)(Int_ask(s,x,IsInt,Err) & Int_ask(s,y,IsInt,Err))
      )
    }

    import mchn.{Init}
    mchn.introduceMolecule(Init(a,b))

    mchn.intEqClasses.get(7) match{
      case Some(ec) if (ec.contains(a)) => assertTrue(true)//Ok
      case _ => fail("Missing value 7->a. Actual eqClass: " +  mchn.intEqClasses)
    }

    mchn.intEqClasses.get(6) match{
      case Some(ec) if (ec.contains(b)) => assertTrue(true)//Ok
      case _ => fail("Missing value 6->b. Actual eqClass: " +  mchn.intEqClasses)
    }

    assertEquals(2,declared)
  }

  @Test (timeout=1000)
  def testEqual(){
    var iguales = false
    var noIguales = false

    val machine = new Cham with IntegerCham{
      val s,x,y,z = Var
      val Init = Rel
      val Test = Rel
      val Iguales = NativeRelation("Iguales"){
        case (Atom(_, a::b::_),s) => iguales = true
        case atom => fail("Expected atom: Iguales(a,b). Actual: " + atom)
      }
      val NoIguales = NativeRelation("NoIguales"){
        case (Atom(_, a::b::_),s) => noIguales = true
        case atom => fail("Expected atom: NoIguales(a,b). Actual: " + atom)
      }

      rules(
        Init(x,y,z) --> (IntVal(x,8) & IntVal(y,8) & IntVal(z,9)),
        Test(s,x,y) --> EQ_ask(s,x,y,Iguales,NoIguales)
      )
    }

    import machine.{Init,Test}

    machine.introduceMolecule(Init(a,b,c))
    machine.introduceMolecule(Test(s,a,b))
    machine.introduceMolecule(Test(s,c,b))

    //TODO rewrite tests
//    assertTrue("Missing element: Iguales(a,b). Actual solution: " + machine.solution + "\n" +
//      "intEqClasses:" + machine.intEqClasses + "\n" +
//      "EqClasses:" + machine.eqClasses, iguales)
//
//    assertTrue("Missing element: NoIguales(b,c). Actual solution: " + machine.solution + "\n" +
//      "intEqClasses:" + machine.intEqClasses + "\n" +
//      "DisjClasses:" + machine.disjClasses, noIguales)

  }

  @Test (timeout=1000)
  def testAdd(){
    logLevel = DEBUG

    val machine = new Cham with IntegerCham{
      val Result = NativeRelation("Result"){//(a,s) => println("Result: "+a)}
        case (Atom(_,s::v1::v2::v3::_),_)  =>
          assertEquals("4",v3.toString)
        case _ => fail("Wrong format")
      }
      val x,y,s = Var

      axiom(
        Nu(s)(Add(s,1,3,Result))
      )
      axiom(
        Nu(x,y)(IntVal(x,1) & IntVal(y,3) & Add(s,x,y,Result))
      )
      axiom(
        Nu(x)(IntVal(x,1) & Add(s,x,3,Result))
      )
      axiom(
        Nu(x)(IntVal(x,1) & Add(s,3,x,Result))
      )
    }

    machine.executeRules()
  }
}