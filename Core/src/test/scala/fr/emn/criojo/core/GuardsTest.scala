package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 07/03/12
 * Time: 15:06
 */

import datatype.Term
import org.junit._
import Assert._
import fr.emn.criojo.lang._
import fr.emn.criojo.TestCham
import fr.emn.criojo.ext.debug.DebugCham
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.ext.expression.ScalaString.constructor.WrapScalaString
import fr.emn.criojo.ext.expression.ScalaString.VarScalaString
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.ext.expression.ScalaInt.VarScalaInt
import fr.emn.criojo.core.Converters._

class GuardsTest {

  @Test
  def presenceTest(){
    val result = new Array[Int](2)
    var i = 1

    //Sequence example
    val machine = new Cham with TestCham with DebugCham {
      val R = LocalRelation("R")
      val S = LocalRelation("S")
      val X = LocalRelation("X")
      val x,y,z = VarScalaString()

      val First = NativeRel {case _ => result(0) = i; i+=1 }
      val Second = NativeRel { case _ => result(1) = i }

      rules(
        R(x,y) --> Prs(X(x,y)) ?: Second(),
        (R(x,y) & S(y,z)) --> (First() & X(x,y) & R(x,y))
      )
    }

    machine.enableStreamingTrace()

    import machine.{R,S}
    machine.introduceMolecule(R(1,2))
    machine.introduceMolecule(S(2,3))
    machine.executeRules()

    assertTrue("\nExpected : Array(1,2)\n" +
               "Actual   : " + result.mkString("Array(",",",")"),
                Array(1,2).sameElements(result))
  }

  @Test
  def presenceSansParamsTest(){
    var finalword = ""

    val cham = new Cham with TestCham{
      val R = LocalRelation("R")
      val S = LocalRelation("S")
      val X = LocalRelation("X")

      val Concat = NativeRel {
        case WrapScalaString(v)::Nil => finalword += v
        case _ =>
      }

      rules(
        R() --> Prs(X()) ?: Concat("two"),
        (R() & S()) --> (Concat("one") & X() & R())
      )
    }

    import cham.{R,S}
    cham.introduceMolecule(R())
    cham.introduceMolecule(S())
    cham.executeRules()

    assertEquals("onetwo",finalword)
  }

  @Test
  def absenceTest(){
    var finalword = ""

    //Clonning example
    val cham = new Cham with TestCham {
      val One = LocalRelation("One")
      val Two = LocalRelation("Two")
      val Three = LocalRelation("Three")
      val R = LocalRelation("R")
      val S = LocalRelation("S")

      val Concat = NativeRel {
        case WrapScalaString(v)::Nil => finalword += v
        case _ =>
      }

      rules(
        (One() & R()) --> (One() & S() & S() & Concat("one")),
        One() --> Abs(R()) ?: (Two() & Concat("two")),
        (Two() & S()) --> (Two() & R() & Concat("two")),
        Two() --> Abs(S()) ?: (Three() & Concat("three"))
      )
    }

    import cham.{One,R}
    cham.introduceMolecule(One() & R())
    cham.executeRules()

    assertEquals("onetwotwotwothree",finalword)
  }

  @Test
  def absenceTest2(){
    var finalword = ""

    //Clonning example
    val cham = new Cham with TestCham{
      val One = LocalRelation("One")
      val Two = LocalRelation("Two")
      val Three = LocalRelation("Three")
      val R = LocalRelation("R")
      val S = LocalRelation("S")
      val Test1 = LocalRelation("Test1")
      val Test2 = LocalRelation("Test2")
      val x,y,z = VarScalaInt()

      val Concat = NativeRel {
        case WrapScalaString(v)::Nil => finalword += v
        case _ =>
      }

      rules(
        (One() & R()) --> (One() & S() & S() & Concat("one")),
        (One() & Test2(x,y)) --> Abs(R(),Test1(x,y)) ?: (Two() & Concat("two")),
        (Two() & S()) --> (Two() & R() & Concat("two")),
        Two() --> Abs(S()) ?: (Three() & Concat("three"))
      )
    }

    import cham.{One,R,Test2}
    cham.introduceMolecule(One() & R() & Test2(1,2))
    cham.executeRules()

    assertEquals("onetwotwotwothree",finalword)
  }

  @Test
  def existsTest(){
    var passed = 0

    val cham = new Cham with TestCham {
      val R = LocalRelation("R")
      val S = LocalRelation("S")
      val Passed = NativeRel { case _ => passed += 1 }

      val x,y,z = VarScalaString()

      rules(
        R(x) --> Ex(y,Prs(S(x,y))) ?: Passed()
      )
    }

    import cham.{R,S}
    cham.introduceMolecule(S(1,2))
    cham.introduceMolecule(R(1))
    cham.executeRules()

    assertEquals(1, passed)

    cham.introduceMolecule(R(2))
    cham.executeRules()
    assertEquals(1, passed)
  }

  @Test
  def notExistsTest(){
    var passed = 0

    val cham = new Cham with TestCham {
      val R = LocalRelation("R")
      val S = LocalRelation("S")
      val Session = LocalRelation("Session")
      val x,y,z,s = VarScalaInt()

      val Passed = NativeRel { case _ => passed += 1 }

      rules(
        (R(x) & Session(s)) --> Not(Ex(y,Prs(S(x,y)))) ?: (R(x) & S(x,s) & Passed() & Session(s+1))
      )
    }

    import cham.{R,S,Session}
    cham.introduceMolecule(Session(0))
    cham.introduceMolecule(S(1,2))
    cham.introduceMolecule(R(1))
    cham.executeRules()

    assertEquals(0, passed)

    cham.introduceMolecule(R(2))
    cham.executeRules()

    assertEquals(1, passed)
  }

  @Test
  def andTest(){
    var finalword = ""
    val cham = new Cham with TestCham{
      val R = LocalRelation("R")
      val X1 = LocalRelation("X1")
      val X2 = LocalRelation("X2")

      val Concat = NativeRel {
        case WrapScalaString(v)::Nil => finalword += v
        case _ =>
      }

      rules(
        R() --> (Prs(X1()) && Abs(X2())) ?: (R() & X2() & Concat("123")),
        (R() & X2()) --> Concat("456")
      )
    }

    import cham.{R,X1}
    cham.introduceMolecule(R())
    cham.executeRules()

    assertEquals("",finalword)

    cham.introduceMolecule(X1())
    cham.executeRules()

    assertEquals("123456",finalword)
  }
}