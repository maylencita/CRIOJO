package fr.emn.criojo.core

import org.junit._
import Assert._
import fr.emn.criojo.lang._
import fr.emn.criojo.TestCham

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/11/11
 * Time: 14:41
 */
class StatesTest {

  implicit def num2term(n:Int):Term = new ValueTerm[Int](n)
  implicit def str2term(str:String):Term = new ValueTerm[String](str)

  @Test(timeout = 1000)
  def terminationTest() {
    val sm = new Cham with TestCham {
      val A = Rel("A")
      val B = Rel("B")
      val C = Rel("C")
      val D = Rel("D")
      val K = Rel("K")
      val x, y, z = Var

      rules(
        (A(x, y) & B(y, z)) --> Abs(D(y)) ?: (D(y) & A(x, y) & B(y, z) & Passed())
      )
    }

    import sm.{A,B}
    sm.introduceMolecule(A(1, 2))
    sm.introduceMolecule(A(2, 3))
    sm.introduceMolecule(B(3, 4))
    sm.executeRules()

    assertEquals(1,sm.passed)
  }

  @Test (timeout=1000)
  def persistenceTest(){
    val cham = new Cham with TestCham{
      val A = Rel("A")
      val B = Rel("B")
      val C = Rel("C")
      val D = Rel("D")

      val x,y,z = Var
      rules(
        (!A(x,y) & B(y,z)) --> (Print(x,z,"\n") & Passed()),
        (A(x,y) & D(y,z)) --> (Print(x,z,"\n") & Passed())
      )
    }

    import cham.{A,B,D}

    cham.introduceMolecule(A(1,2) & D(2,3) & B(2,4))
    cham.executeRules()

    assertEquals(2,cham.passed)
  }

  @Test (timeout=1000)
  def oneHeadTest(){
    val cham = new Cham with TestCham{
      val R = Rel("R")
      val x,y = Var

      rules(
        R(x,y) --> (Print(x,y) & Passed())
      )
    }
    import cham.R
    cham.introduceMolecule(R(1,2))
    cham.executeRules()

    assertEquals(1,cham.passed)
  }

  @Test (timeout=1000)
  def repeatedHeadTest(){
    val sm = new Cham with TestCham{
      val H = Rel("H")
      val H3 = Rel("H3")
      val x,y,z = Var

      rules(
        (H(x) & H(y) & H(z)) --> H3(x,y,z),
        H3(x,y,z) --> (Print(x,y,z) & Passed())
      )
    }

    import sm.H
    sm.introduceMolecule(H(1))
    sm.introduceMolecule(H(2))
    sm.introduceMolecule(H(3))
    sm.executeRules()
//    println(sm.printRules)

    assertEquals(1,sm.passed)
  }

  @Test
  def messagePassingTest(){
    var word = ""
    val cham = new Cham with TestCham{
      val A = Rel
      val Start = Rel
      val k = VarR("k")
      val x = Var

      val Concat = NativeRel{
        case ValueTerm(first)::_ => word += first
        case _ =>
      }

      rules(
        A(x,k) --> k(x),
        Start() --> A("passed!",Concat)
      )
    }

    import cham.{Start}
    cham.introduceMolecule(Start())
    cham.executeRules()

    assertEquals("passed!",word)
  }
}