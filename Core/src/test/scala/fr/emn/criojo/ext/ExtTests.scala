package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 28, 2010
 * Time: 12:14:33 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._

import java.net.URI

object ExtTests{

  val a = Variable("a")
  val b = Variable("b")
  val c = Variable("c")
  val d = Variable("d")
  val e = Variable("d")

  implicit def intToVar(n:Int):Variable = new Variable(n.toString)
  implicit def relToVar(r:Relation):RelVariable = {
    val vr = new RelVariable(r.name)
    vr.relation = r
    vr
  }
  implicit def strToVar(str:String):Variable = new Variable(str)
}
class ExtTests{
  import ExtTests._

  @Test
  def testStr{
//    logLevel = DEBUG
    val machine = new TestCham with StringVM{
      val s,x,y = Var
      val S = Rel("S"); val MyStr = Rel("MyStr")

      rules{
        S(y,s,x) ==> Str_ask(y,s,x,MyStr)
      }
    }

    machine.introduceAtom(Atom("$Str",Variable("Mayleen"),a))
    machine.introduceAtom(Atom("$Str",Variable("Otro"),b))

    machine.strEqClasses.get("Mayleen") match{
      case Some(ec) if (ec.contains(a)) => assertTrue(true)//Ok
      case _ => fail("Missing value \"Mayleen\" ->a. Actual eqClass: " +  machine.strEqClasses)
    }

    machine.introduceAtom(Atom("S", "Mayleen",c, a))
    machine.introduceAtom(Atom("S", "Mayleen",c, b))

    val a7 = Atom("MyStr", a)

//    log("Relations: " + machine.relations)
    log("strEqClasses:" + machine.strEqClasses)
    log("EqClasses:" + machine.eqClasses)

    val s = Variable("s"); val x = Variable("x")
    assertFalse("Missing element: " + a7 + ". Actual solution: " + machine.solution,
      machine.query(List(Atom("MyStr",s,x)),List((x,a))).isEmpty
    )
  }


  @Test
  def testEqStr{
//    logLevel = DEBUG
    var result = false

    val machine = new TestCham with StringVM{
      val s,x,y = Var
      val S = Rel("S");
      val Iguales = NativeRelation("Iguales"){
        case Atom("Iguales", a::b::_) => result = true
        case atom => fail("Expected atom: Iguales(a,b). Actual: " + atom)
      }

      rules(
        S(s,x,y) ==> EQ_ask(s,x,y,Iguales)
      )
    }

    machine.introduceAtom(Atom("$Str",Variable("Mayleen"),a))
    machine.introduceAtom(Atom("$Str",Variable("Mayleen"),b))
    machine.introduceAtom(Atom("S",Variable("1"),a,b))

    val a8 = Atom("Iguales", a,b)

    log("strEqClasses:" + machine.strEqClasses)
    log("EqClasses:" + machine.eqClasses)
    assertTrue("Missing element: Iguales(a,b). Actual solution: " + machine.solution, result)

    machine.introduceAtom(Atom("Eq", c, b))
    machine.strEqClasses.get("Mayleen") match {
      case Some(ec) if(ec.contains(a) && ec.contains(b) && ec.contains(c)) => assertTrue(true)
      case _ => fail("Expected intEqClasses: Map(Mayleen->{a,b,c}). Actual: " + machine.strEqClasses)
    }
  }

  @Test (timeout=1000)
  def mixedVMTest{
    val vm = new TestCham with StringVM with IntVM{
      val s,x,y,id,name,id2,name2 = Var
      val Alumni = Rel("Alumni"); val SameName = Rel("SameName"); val SameId = Rel("SameId")

      rules(
        (Alumni(s, id, name) &: Alumni(s,id2,name2)) ==>
              (EQ_ask(s, name,name2,SameName) &: EQ_ask(s,id,id2,SameId)) ,
        (SameName(s,name,name) &: SameId(s,id,id)) ==> Alumni(s, id, name)
      )
    }

    vm.introduceAtom(Atom("Alumni", Variable("s1"), 12, "Mayleen"))
    vm.introduceAtom(Atom("Alumni", Variable("s1"), 12, "Mayleen"))

    val al = Atom("Alumni", Variable("s1"),12,"Mayleen")
    assertTrue("Wrong solution. Expected: " + al + ". Actual: " + vm.solution,
      vm.query(List(al), List()).size == 1 )

  }

  @Test (timeout=1000)
  def testPrint{
//    logLevel = INFO
    val machine = new LocalVM{
      val x = Var; val y = Variable("$y")
      val X = Rel("X")

      rules(
        X(x) ==> (StringAtom("x=",y) &: Print(y,x))
      )

//      def newRemoteRelation(remoteName:String,url:String):RemoteRelation = null
    }
    info(this.getClass, "testPrint", "rules: " + machine.rules.mkString("","\n",""))

    machine.introduceAtom(Atom("X", Variable("SomeVariable")))
    machine.introduceAtom(Atom("X", Value("Mayleen")))
    machine.introduceAtom(Atom("X", Value(24)))
    
    info(this.getClass, "testPrint", "solution: " + machine.solution)
    info(this.getClass, "testPrint", "intEqClasses: " + machine.intEqClasses)
    info(this.getClass, "testPrint", "strEqClasses:" + machine.strEqClasses)
  }


}