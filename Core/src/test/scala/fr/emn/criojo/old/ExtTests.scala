package fr.emn.criojo.old

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
import fr.emn.criojo.lang.{Cham, Nu}
import fr.emn.criojo.ext.{StringAtom, LocalCHAM, IntegerCham, StrCHAM}

object ExtTests {

  val a = Variable("a")
  val b = Variable("b")
  val c = Variable("c")
  val d = Variable("d")
  val e = Variable("d")

  implicit def intToVar(n: Int): Variable = new Variable(n.toString)

  //  implicit def relToVar(r:Relation):RelVariable = {
  //    val vr = new RelVariable(r.name)
  //    vr.relation = r
  //    vr
  //  }
  implicit def strToVar(str: String): Variable = new Variable(str)
}

class MiscTexts {

  import ExtTests._

  @Test(timeout = 1000)
  def nativeRelTest {
    val vm = new Cham {
      val NatRel = NativeRelation("NatRel")(dummyMethod)

      def dummyMethod(a: Atom, s: Solution) {
        println("Called Dummy Method with " + a)
      }
    }

    vm.introduceAtom(Atom("NatRel", a, b))
  }

  /*
  @Test(timeout = 1000)
  def mixedVMTest {
    logLevel = DEBUG

    val vm = new Cham with StrCHAM with IntegerCham {
      //DefaultCham with StrCHAM with IntCHAM{
      val s, x, y, id, name, id2, name2 = Var
      val Alumni = Rel("Alumni");
      val SameName = Rel("SameName");
      val SameId = Rel("SameId");
      val NotSame = Rel("NotSame")

      rules(
        (Alumni(s, id, name) &: Alumni(s, id2, name2)) -->
          (EQ_ask(s, name, name2, SameName, NotSame) &: EQ_ask(s, id, id2, SameId, NotSame)),
        (SameName(s, name, name) &: SameId(s, id, id)) --> Alumni(s, id, name)
      )
    }

    vm.introduceAtom(Atom("Alumni", Variable("s1"), 12, "Mayleen"))
    vm.introduceAtom(Atom("Alumni", Variable("s1"), 12, "Mayleen"))

    val al = Atom("Alumni", Variable("s1"), 12, "Mayleen")
    //TODO Rewrite test
    //    assertTrue("Wrong solution. Expected: " + al + ". Actual: " + vm.solution,
    //      vm.query(List(al), List()).size == 1 )

  }
  */

  @Test(timeout = 1000)
  def testPrint {
    logLevel = DEBUG
    val machine = new LocalCHAM {
      val x = Var;
      val y = Variable("$y")
      val X = Rel("X")

      rules(
        X(x) ==> Nu(y)(StringAtom("x=", y) &: Print(y, x))
      )

      //      def newRemoteRelation(remoteName:String,url:String):RemoteRelation = null
    }
    info(this.getClass, "testPrint", "rules: " + machine.printRules)

    machine.introduceAtom(Atom("X", Variable("SomeVariable")))
    machine.introduceAtom(Atom("X", ValueTerm("Mayleen")))
    machine.introduceAtom(Atom("X", ValueTerm(24)))

    //    info(this.getClass, "testPrint", "solution: " + machine.solution)
    info(this.getClass, "testPrint", "intEqClasses: " + machine.intEqClasses)
    info(this.getClass, "testPrint", "strEqClasses:" + machine.strEqClasses)
  }


}