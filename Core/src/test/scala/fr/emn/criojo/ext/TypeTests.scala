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

class TypeTests{

  val a = Variable("a")
  val b = Variable("b")
  val c = Variable("c")

  implicit def intToVar(n:Int):Variable = new Variable(n.toString)
  implicit def relToVar(r:Relation):RelVariable = {
    val vr = new RelVariable(r.name)
    vr.relation = r
    vr
  }
  implicit def strToVar(str:String):Variable = new Variable(str)

  logLevel = INFO

  @Test
  def testInt{
//    logLevel = DEBUG
    val machine = new CHAM with IntVM{
      val s,x,y = Var
      val S = Rel("S"); val Seis = Rel("Seis"); val Siete = Rel("Siete")

      rules(
        S(s,x) ==> Int_ask(7,s,x,Siete)
      )
    }

    machine.introduceAtom(Atom("$Int",Variable("7"),a))
    machine.introduceAtom(Atom("$Int",Variable("6"),b))

    machine.intEqClasses.get(7) match{
      case Some(ec) if (ec.contains(a)) => assertTrue(true)//Ok
      case _ => fail("Missing value 7->a. Actual eqClass: " +  machine.intEqClasses)
    }

    machine.introduceAtom(Atom("S", c, a))
    machine.introduceAtom(Atom("S", c, b))

    val a7 = Atom("Siete", a)
    val a6 = Atom("Seis", b)

//    log("Relations: " + machine.relations)
    log("intEqClasses:" + machine.intEqClasses)
    log("EqClasses:" + machine.eqClasses)

    assertFalse("Missing element: " + a7 + ". Actual solution: " + machine.solution,
      machine.query(List(a7),List()).isEmpty
    )
    assertTrue("Wrong solution with: " + a6 + ". Actual solution: " + machine.solution,
      machine.query(List(a6),List()).isEmpty
    )
  }

  @Test
  def testStr{
//    logLevel = DEBUG
    val machine = new CHAM with StringVM{
      val s,x,y = Var
      val S = Rel("S"); val MyStr = Rel("MyStr")

      rules{
        S(s,x) ==> Str_ask("Mayleen",s,x,MyStr)
      }
    }

    machine.introduceAtom(Atom("$Str",Variable("Mayleen"),a))
    machine.introduceAtom(Atom("$Str",Variable("Otro"),b))

    machine.strEqClasses.get("Mayleen") match{
      case Some(ec) if (ec.contains(a)) => assertTrue(true)//Ok
      case _ => fail("Missing value \"Mayleen\" ->a. Actual eqClass: " +  machine.strEqClasses)
    }

    machine.introduceAtom(Atom("S", c, a))
    machine.introduceAtom(Atom("S", c, b))

    val a7 = Atom("MyStr", a)

//    log("Relations: " + machine.relations)
    log("strEqClasses:" + machine.strEqClasses)
    log("EqClasses:" + machine.eqClasses)

    assertFalse("Missing element: " + a7 + ". Actual solution: " + machine.solution,
      machine.query(List(a7),List()).isEmpty
    )
  }

  @Test
  def testEqInt{
//    logLevel = DEBUG
    var result = false

    val machine = new CHAM with IntVM{
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

    machine.introduceAtom(Atom("$Int",Variable("8"),a))
    machine.introduceAtom(Atom("$Int",Variable("8"),b))
    machine.introduceAtom(Atom("S",Variable("1"),a,b))

    log("intEqClasses:" + machine.intEqClasses)
    log("EqClasses:" + machine.eqClasses)
    assertTrue("Missing element: Iguales(a,b). Actual solution: " + machine.solution, result)

    machine.introduceAtom(Atom("Eq", c, b))
    machine.intEqClasses.get(8) match {
      case Some(ec) if(ec.contains(a) && ec.contains(b) && ec.contains(c)) => assertTrue(true)
      case _ => fail("Expected intEqClasses: Map(7->{a,b,c}). Actual: " + machine.intEqClasses)
    }
  }

  @Test
  def testEqStr{
//    logLevel = DEBUG
    var result = false

    val machine = new CHAM with StringVM{
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
    val vm = new CHAM with StringVM with IntVM{
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

  @Test
  def testPrint{
//    logLevel = INFO
    val machine = new VirtualMachine{
      val x = Var; val y = Variable("$y")
      val X = Rel("X")

      rules(
        X(x) ==> (StringAtom("x=",y) &: Print(y,x))
      )

      def newRemoteRelation(remoteName:String,url:String):RemoteRelation = null
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