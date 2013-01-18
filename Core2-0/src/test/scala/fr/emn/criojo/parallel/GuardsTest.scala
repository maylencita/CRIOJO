package fr.emn.criojo.parallel

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 07/03/12
 * Time: 15:06
 */

import fr.emn.criojo.expression.{CriojoTypesPredef}

import org.junit._
import Assert._
import fr.emn.criojo.expression.scala.{ScalaInt, ScalaTypesPredef}
import fr.emn.criojo.testing.TestCham

class GuardsTest extends ScalaTypesPredef{

  @Test
  def presenceTest(){
    val result = new Array[Int](2)
    var i = 1

    //Sequence example
    val machine = new Agent {
      val R = LocalRel
      val S = LocalRel
      val X = LocalRel
      val x,y,z = Var[ScalaInt]

      val First = NativeRel {case _ => result(0) = i; i+=1 }
      val Second = NativeRel { case _ => result(1) = i }

      rules(
        R(x,y) --> Prs(X(x,y)) ?: Second(),
        (R(x,y) & S(y,z)) --> (First() & X(x,y) & R(x,y))
      )
    }
    machine.start()

    machine ! machine.R(1,2)
    machine ! machine.S(2,3)

    Thread.sleep(500)

    assertTrue("\nExpected : Array(1,2)\n" +
               "Actual   : " + result.mkString("Array(",",",")"),
                Array(1,2).sameElements(result))
  }

  @Test
  def presenceSansParamsTest(){
    var finalword = ""

    val cham = new Agent{
      val R = LocalRel
      val S = LocalRel
      val X = LocalRel

      val Concat = NativeRel {
        case v::Nil => finalword += v
        case _ =>
      }

      rules(
        R() --> Prs(X()) ?: Concat("two"),
        (R() & S()) --> (Concat("one") & X() & R())
      )
    }
    cham.start()

    cham ! cham.R()
    cham ! cham.S()

    Thread.sleep(500)

    assertEquals("onetwo",finalword)
  }

  @Test
  def absenceTest(){

    //Clonning example
    val cham = new Agent with TestCham{
      var rCount = 1
      val name = "sequenceTest"
      val One = LocalRel("One")
      val Two = LocalRel("Two")
      val Three = LocalRel("Three")
      val R = LocalRel("R")
      val S = LocalRel("S")
      val count = LocalRel

      val x,n = Var[ScalaInt]

      rules(
        (One() & R(x)) --> (One() & S(x) & S(x)),
        One() --> Abs(R(x)) ?: (Two() & count(0)),
        (Two() & S(x) & count(n)) --> (Two() & R(x) & count(n+1)),
        (Two() & count(n)) --> Abs(S(x)) ?: (AssertTrue(n<=>(rCount * 2)))
      )
    }
    cham.start()

    cham ! cham.One()
    for(i <- 0 to cham.rCount-1){
      cham ! cham.R(1)
    }

    Thread.sleep(500)
    cham.testAssert()
  }

  @Test
  def absenceTest2(){
    var finalword = ""

    //Clonning example
    val cham = new Agent{
      val One = LocalRel
      val Two = LocalRel
      val Three = LocalRel
      val R = LocalRel
      val S = LocalRel
      val Test1 = LocalRel
      val Test2 = LocalRel
      val x,y,z = Var[ScalaInt]

      val Concat = NativeRel {
        case v::Nil => finalword += v
        case _ =>
      }

      rules(
        (One() & R()) --> (One() & S() & S() & Concat("one")),
        (One() & Test2(x,y)) --> Abs(R(),Test1(x,y)) ?: (Two() & Concat("two")),
        (Two() & S()) --> (Two() & R() & Concat("two")),
        Two() --> Abs(S()) ?: (Three() & Concat("three"))
      )
    }
    cham.start()

    cham ! List(cham.One(),cham.R(),cham.Test2(1,2))

    Thread.sleep(500)
    assertEquals("onetwotwotwothree",finalword)
  }

//  @Test
//  def existsTest(){
//    var passed = 0
//
//    val cham = new Agent{
//      val R = LocalRel
//      val S = LocalRel
//      val Passed = NativeRel { l => passed += 1 }
//
//      val x,y,z = Var[ScalaInt]
//
//      rules(
//        R(x) --> Ex(y,Prs(S(x,y))) ?: Passed()
//      )
//    }
//    cham.start()
//
//    cham ! cham.S(1,2)
//    cham ! cham.R(1)
//
//    Thread.sleep(500)
//    assertEquals(1, passed)
//
//    cham ! cham.R(2)
//
//    Thread.sleep(500)
//    assertEquals(1, passed)
//  }

//  @Test
//  def notExistsTest(){
//    var passed = 0
//
//    val cham = new Agent{
//      val R = LocalRel
//      val S = LocalRel
//      val Session = LocalRel
//      val x,y,z,s = Var[ScalaInt]
//
//      val Passed = NativeRel { case _ => passed += 1 }
//
//      rules(
//        (R(x) & Session(s)) --> Not(Ex(y,Prs(S(x,y)))) ?: (R(x) & S(x,s) & Passed() & Session(s + 1))
//      )
//    }
//    cham.start()
//
//    cham ! cham.Session(0)
//    cham ! cham.S(1,2)
//    cham ! cham.R(1)
//
//    Thread.sleep(500)
//    assertEquals(0, passed)
//
//    cham ! cham.R(2)
//
//    Thread.sleep(500)
//    assertEquals(1, passed)
//  }

  @Test
  def andTest(){
    var finalword = ""
    val cham = new Agent{
      val R = LocalRel
      val X1 = LocalRel
      val X2 = LocalRel

      val Concat = NativeRel {
        case v::Nil => finalword += v
        case _ =>
      }

      rules(
        R() --> (Prs(X1()) && Abs(X2())) ?: (R() & X2() & Concat("123")),
        (R() & X2()) --> Concat("456")
      )
    }
    cham.start

    cham ! cham.R()

    Thread.sleep(500)
    assertEquals("",finalword)

    cham ! cham.X1()

    Thread.sleep(500)
    assertEquals("123456",finalword)
  }
}