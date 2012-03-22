package fr.emn.criojo.core

import org.junit._
import Assert._
import fr.emn.criojo.lang._

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/11/11
 * Time: 14:41
 */
class StatesTest {

  // Some useful objects
  //-------------------------------------------------------
  trait TestCham extends Cham{
    var passed = 0
    val Print = NativeRelation("Print") {
      case ((Atom(_,terms), _)) => print(terms.mkString(","))
      case _ =>
    }
    val Passed = NativeRelation("Passed"){(s,a) =>
      passed += 1
    }
  }

  implicit def num2term(n:Int):Term = new ValueTerm[Int](n)
  implicit def str2term(str:String):Term = new ValueTerm[String](str)

  @Test//(timeout = 1000)
  def terminationTest() {
    val sm = new Cham with TestCham{
      val A = createAndAddRelation("A")
      val B = createAndAddRelation("B")
      val C = createAndAddRelation("C")
      val D = createAndAddRelation("D")
      val K = createAndAddRelation("K")
      val x, y, z = createVariable()

      rules(
        (A(x, y) & B(y, z)) --> Abs(D(y)) ?: (D(y) & A(x, y) & B(y, z) & Print(x,y,z) & Passed())
      )
    }

    import sm.{A,B}
    sm.introduceMolecule(A(1, 2))
    sm.introduceMolecule(A(2, 3))
    sm.introduceMolecule(B(3, 4))
    sm.executeRules()

    println(sm.printRules)

    assertEquals(1,sm.passed)
  }

  @Test (timeout=1000)
  def persistenceTest{
    val cham = new Cham with TestCham{
      val A = createAndAddRelation("A")
      val B = createAndAddRelation("B")
      val C = createAndAddRelation("C")
      val D = createAndAddRelation("D")

      val x,y,z = createVariable()
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
  def oneHeadTest{
    val cham = new Cham with TestCham{
      val R = createAndAddRelation("R")
      val x,y = createVariable()

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
  def repeatedHeadTest{
    val sm = new Cham with TestCham{
      val H = createAndAddRelation("H")
      val H3 = createAndAddRelation("H3")
      val x,y,z = createVariable()

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

}