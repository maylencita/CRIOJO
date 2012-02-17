package fr.emn.criojo.old

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
import fr.emn.criojo.lang._
import fr.emn.criojo.ext.LocalCHAM

class VirtualMachineTests {
  val x = Variable("x")
  val y = Variable("y")
  val z = Variable("z")
  val w = Variable("w")
  val a = Variable("a")
  val b = Variable("b")
  val c = Variable("c")
  val d = Variable("d")

  @Test(timeout = 1000)
  def testEqGuard {
    logLevel = DEBUG

    var result = false

    val m2 = new LocalCHAM {
      val s, x, y, y2, z = Var
      val R = Rel("R");
      val S = Rel("S")
      val X1 = Rel("X1");
      val X2 = Rel("X2")
      //      val TT = RelVariable("true")
      val Cont = VarR("Cont")
      val Resp = NativeRelation("Resp") {
        case (Atom("Resp", id :: dd :: _), _) if (dd == d) => result = true
        case resp => fail("Expected atom: Resp(1,d). Actual: " + resp)
      }
      rules(
        R(s, x, y, Cont) --> Abs(X1(s)) ?: (R(s, x, y, Cont) &: X1(s)),
        (R(s, x, y, Cont) &: S(y2, z)) --> /*Eq(y,y2) ?:*/ Cont(s, z) //TODO Rewrite test
      )
    }
    info(this.getClass, "testEqGuard", "m2: " + m2.printRules)

    val atom1 = Atom("R", Variable("1"), a, b, RelVariable(m2.Resp))
    val atom2 = Atom("S", b, d)
    m2.introduceAtom(atom1)
    m2.introduceAtom(atom2)

    //TODO Rewrite test
    //    assertTrue("Expected: <Resp(1,d)>. Actual: " + m2.solution, result)

  }

  @Test(timeout = 1000)
  def testEqClassesGuard {
    logLevel = DEBUG
    var result = false

    val m2 = new LocalCHAM {
      val s, x, y, y2, z = Var
      val R = Rel("R");
      val S = Rel("S")
      val X1 = Rel("X1");
      val X2 = Rel("X2")
      //      val TT = RelVariable("true")
      val Cont = VarR("Cont")
      val Resp = NativeRelation("Resp") {
        case (Atom("Resp", id :: d :: _), s) => result = true
        case resp => fail("Expected atom: Resp(1,d). Actual: " + resp)
      }
      rules(
        R(s, x, y, Cont) ==> Abs(X1(s)) ?: (R(s, x, y, Cont) &: X1(s)),
        (R(s, x, y, Cont) &: S(y2, z)) ==> /*Eq(y,y2) ?:*/ Cont(s, z) //TODO rewrite test
      )
    }
    info(this.getClass, "testEqClassesGuard", "m2: " + m2) //.rules.mkString("","\n",""))

    val atom1 = Atom("R", Variable("1"), a, b, RelVariable(m2.Resp))
    val atom2 = Atom("S", c, d)
    m2.introduceAtom(Atom("Eq", b, c))
    m2.introduceAtom(atom1)
    m2.introduceAtom(atom2)

    info(this.getClass, "testEqClassesGuard", "eqClasses: " + m2.eqClasses)
    //TODO rewrite test
    //    assertTrue("Expected: <Resp(1,d)>. Actual: " + m2.solution, result)
  }

  @Test(timeout = 2000)
  def testMapReduce {
    logLevel = DEBUG
    var total: Int = 0
    val m2 = new LocalCHAM {
      val Word = Rel("Word")
      val Map = Rel("Map")
      val Reduce = Rel("Reduce")
      val Init = Rel("Init")
      val Sum = Rel("Sum")
      val Sum2 = Rel("Sum2")
      val X1 = Tok()
      val X2 = Rel("X2")
      val n, m, nm, s, w1, w2, v = Var
      val Total = NativeRelation("Total") {
        case (a: Atom, ss) =>
          getIntValue(a.vars(0)) match {
            case Some(num) => total = num
            case _ =>
          }
        case _ =>
      }

      // _ => Reduce("foo",0) & X2()
      when(Empty) {
        If(Abs(X2())) {
          //          Nu(s,n)(Reduce(s,n) &: StrRel("foo",s) &: IntRel(0,n) &: X2())
          Reduce("foo", 0) & X2()
        }
      }

      //Init() & Word(w) => Map(w,1) & Init()
      when(Init() &: Word(w)) {
        //        Nu(n)(Map(w,n) &: IntRel(1,n) &: Init())
        Map(w, 1) & Init()
      }
      //Map(w1,n) & Reduce(w2,m) => [w1 == w2] ? Nu(nm) Reduce(w1,nm) & Sum(n,m,nm)
      when(Map(w1, n) &: Reduce(w2, m)) {
        //        If(Eq(w1,w2)){
        Nu(nm)(Reduce(w1, nm) &: Sum(n, m, nm)) //TODO rewrite test
        //        }
      }
      //Reduce(w,n) & Init() => Abs[Word(w)] ? Print(n) & Total(n)
      when(Reduce(w, n) &: Init()) {
        If(Abs(Word())) {
          Print(n) &: Total(n)
        }
      }

      when(Sum(x, y, z)) {
        Nu(s)(Add(s, x, y, Sum2) & X1(s, z))
      }

      when(Sum2(s, x, y, v) & X1(s, z)) {
        IntVal(z, v)
      }
    }

    val s1 = Variable("s1");
    val s2 = Variable("s2");
    val s3 = Variable("s3");
    val s4 = Variable("s4");
    val s5 = Variable("s5")
    m2.executeRules
    m2.introduceAtom(Atom("$Str", ValueTerm("foo"), s1));
    m2.introduceAtom(Atom("Word", s1))
    m2.introduceAtom(Atom("$Str", ValueTerm("boo"), s2));
    m2.introduceAtom(Atom("Word", s2))
    m2.introduceAtom(Atom("$Str", ValueTerm("foo"), s3));
    m2.introduceAtom(Atom("Word", s3))
    m2.introduceAtom(Atom("$Str", ValueTerm("cat"), s4));
    m2.introduceAtom(Atom("Word", s4))
    m2.introduceAtom(Atom("$Str", ValueTerm("lol"), s5));
    m2.introduceAtom(Atom("Word", s5))
    m2.introduceAtom(Atom("Init"))

    //assertEquals("Final solution: " + m2.prettyPrint, 2, total)
  }

  @Test(timeout = 2000)
  def testMapReduce2() {
    //    logLevel = DEBUG
    var total: Int = 0

    val m2 = new LocalCHAM {
      val Word = Rel("Word")
      val Map = Rel("Map")
      val Reduce = Rel("Reduce")
      val Init = Rel("Init")
      val Sum = Rel("Sum")
      val Sum2 = Rel("Sum2")
      val X1 = Tok()
      val X2 = Rel("X2")
      val n, m, nm, s, w1, w2, v = Var
      val Total = NativeRelation("Total") {
        case (a: Atom, ss) =>
          getIntValue(a.vars(0)) match {
            case Some(num) => total = num
            case _ =>
          }
        case _ =>
      }

      facts(
        Word("foo"), Word("boo"), Word("cat"), Word("lol")
      )
      //      seq(
      //        Reduce("foo",0),
      //        Do(
      //          when(Word(w)){Map(w,1)},
      //          when(Map(w,n) & Map(w,m)){Nu(nm)(Reduce(w,nm) & Sum(n,m,nm))}
      //        ),
      //        when(Reduce(w,n)){Print(n)}
      //      )
    }

    m2.executeRules()
    println(m2.printRules)
    //    println(m2.relations)
  }

}