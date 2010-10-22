package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 15, 2010
 * Time: 1:56:29 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._

class VirtualMachineTests{
  val x = Variable("x")
  val y = Variable("y")
  val z = Variable("z")
  val w = Variable("w")
  val a = Variable("a")
  val b = Variable("b")
  val c = Variable("c")
  val d = Variable("d")

  @Test (timeout=1000)
  def testEqGuard{
//    logLevel = DEBUG
    val a = Variable("a")
    val b = Variable("b")
    val c = Variable("c")
    val d = Variable("d")

    var result = false

    val m2 = new LocalVM{
      val s,x,y,y2,z = Var
      val R = Rel("R"); val S = Rel("S")
      val X1 = Rel("X1"); val X2 = Rel("X2")
      val TT = RelVariable("true")
      val Cont = RelVariable("Cont")
      val Resp = NativeRelation("Resp"){
        case Atom("Resp", id::d::_) => result = true
        case resp => fail("Expected atom: Resp(1,d). Actual: " + resp)
      }
      rules(
        R(s,x,y,Cont) ==> {(T &: X1(s)) ==> F} ?: (R(s,x,y,Cont) &: X1(s)) ,
        (R(s,x,y,Cont) &: S(y2,z)) ==> { T(s,y,y2) ==> {(T &: X2()) ==> F} ?: (EQ_ask(s,y,y2,TT) &: X2()) } ?: Cont(s,z)
      )
    }
    info (this.getClass, "testGuard", "m2: " + m2) //.rules.mkString("","\n",""))

    val atom1 = Atom("R", Variable("1"), a, b, RelVariable(m2.Resp))
    val atom2 = Atom("S", b, d)
    m2.introduceAtom(atom1)
    m2.introduceAtom(atom2)

    assertTrue("Expected: <Resp(1,d)>. Actual: " + m2.solution, result)

  }

  @Test (timeout=1000)
  def testEqClassesGuard{
//    logLevel = DEBUG
    val a = Variable("a")
    val b = Variable("b")
    val c = Variable("c")
    val d = Variable("d")

    var result = false

    val m2 = new LocalVM{
      val s,x,y,y2,z = Var
      val R = Rel("R"); val S = Rel("S")
      val X1 = Rel("X1"); val X2 = Rel("X2")
      val TT = RelVariable("true")
      val Cont = RelVariable("Cont")
      val Resp = NativeRelation("Resp"){
        case Atom("Resp", id::d::_) => result = true
        case resp => fail("Expected atom: Resp(1,d). Actual: " + resp)
      }
      rules(
        R(s,x,y,Cont) ==> {(T &: X1(s)) ==> F} ?: (R(s,x,y,Cont) &: X1(s)) ,
        (R(s,x,y,Cont) &: S(y2,z)) ==> { T(s,y,y2) ==> {(T &: X2()) ==> F} ?: (EQ_ask(s,y,y2,TT) &: X2()) } ?: Cont(s,z)
      )
    }
    info (this.getClass, "testEqClassesGuard", "m2: " + m2) //.rules.mkString("","\n",""))

    val atom1 = Atom("R", Variable("1"), a, b, RelVariable(m2.Resp))
    val atom2 = Atom("S", c, d)
    m2.introduceAtom(Atom("Eq",b,c))
    m2.introduceAtom(atom1)
    m2.introduceAtom(atom2)

    info(this.getClass, "testEqClassesGuard", "eqClasses: " + m2.eqClasses)
    assertTrue("Expected: <Resp(1,d)>. Actual: " + m2.solution, result)
  }
}