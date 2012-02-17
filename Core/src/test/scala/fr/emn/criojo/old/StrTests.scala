package fr.emn.criojo.old

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 22/02/11
 * Time: 12:29
 */
;

import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.ext.StrCHAM

class StrTests {

  import ExtTests._

  @Test(timeout = 1000)
  def testStr {
    logLevel = DEBUG
    val machine = new Cham with StrCHAM {
      //DefaultCham with StrCHAM{
      val s, x, y = Var
      val S = Rel("S");
      val MyStr = Rel("MyStr")

      when(S(y, s, x)) {
        Str_ask(y, s, x, MyStr)
      }
      //      when(MyStr(y,x)){
      //        S(x)
      //      }
    }

    machine.introduceAtom(Atom("$Str", Variable("Mayleen"), a))
    machine.introduceAtom(Atom("$Str", Variable("Otro"), b))

    machine.strEqClasses.get("Mayleen") match {
      case Some(ec) if (ec.contains(a)) => assertTrue(true) //Ok
      case _ => fail("Missing value \"Mayleen\" ->a. Actual eqClass: " + machine.strEqClasses)
    }

    machine.introduceAtom(Atom("S", "Mayleen", c, a))
    machine.introduceAtom(Atom("S", "Mayleen", c, b))

    val a7 = Atom("MyStr", a)

    //    log("Relations: " + machine.relations)
    log("strEqClasses:" + machine.strEqClasses)
    log("EqClasses:" + machine.eqClasses)

    val s = Variable("s");
    val x = Variable("x")
    //TODO rewrite test
    //    assertFalse("Missing element: " + a7 + ". Actual solution: " + machine.solution,
    //      machine.query(List(Atom("MyStr",s,x)),List((x,a))).isEmpty
    //    )
  }

  /*
  @Test(timeout = 1000)
  def testEqStr {
    logLevel = DEBUG
    var result = false

    val machine = new Cham with StrCHAM {
      //DefaultCham with StrCHAM{
      val s, x, y = Var
      val S = Rel("S");
      val Iguales = NativeRelation("Iguales") {
        case (Atom("Iguales", a :: b :: _), _) => result = true
        case (atom, _) => fail("Expected atom: Iguales(a,b). Actual: " + atom)
      }
      val NoIguales = VarR("NoIguales")

      rules(
        S(s, x, y) --> EQ_ask(s, x, y, Iguales, NoIguales)
      )
    }

    machine.introduceAtom(Atom("$Str", Variable("Mayleen"), a))
    machine.introduceAtom(Atom("$Str", Variable("Mayleen"), b))
    machine.introduceAtom(Atom("S", Variable("1"), a, b))

    val a8 = Atom("Iguales", a, b)

    //    log("strEqClasses:" + machine.strEqClasses)
    //    log("EqClasses:" + machine.eqClasses)

    //TODO rewrite test
    //    assertTrue("Missing element: Iguales(a,b). Actual solution: " + machine.solution + "\n" +
    //      "strEqClasses:" + machine.strEqClasses +"\n" +
    //      "EqClasses:" + machine.eqClasses,
    //      result)

    machine.introduceAtom(Atom("Eq", c, b))
    machine.strEqClasses.get("Mayleen") match {
      case Some(ec) if (ec.contains(a) && ec.contains(b) && ec.contains(c)) => assertTrue(true)
      case _ => fail("Expected intEqClasses: Map(Mayleen->{a,b,c}). Actual: " + machine.strEqClasses)
    }
  }
  */
}