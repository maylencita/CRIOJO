package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 07/03/12
 * Time: 15:06
 */
import org.junit._
import Assert._
import fr.emn.criojo.lang._
import fr.emn.criojo.TestCham

class GuardsTests {

  implicit def num2term(n:Int):Term = new ValueTerm[Int](n)
  implicit def str2term(str:String):Term = new ValueTerm[String](str)

  @Test
  def presenceTest{
    val result = new Array[Int](2)
    var i = 1

    //Sequence example
    val cham = new Cham with TestCham{
      val R = Rel("R")
      val S = Rel("S")
      val X = Rel("X")
      val x,y,z = Var

      val First = NativeRelation("First"){(s,a) => result(0) = i; i+=1}
      val Second = NativeRelation("Second"){(s,a) => result(1) = i}

      rules(
        R(x,y) --> Prs(X(x,y)) ?: Second(),
        (!R(x,y) & S(y,z)) --> (First() & X(x,y))
      )
    }

    import cham.{R,S}
    cham.introduceMolecule(R(1,2) & S(2,3))
    cham.executeRules()

    assertTrue("\nExpected : Array(1,2)\n" +
               "Actual   : " + result.mkString("Array(",",",")"),
                Array(1,2).sameElements(result))
  }

  @Test
  def absenceTest{
    var finalworld = ""

    //Clonning example
    val cham = new Cham with TestCham{
      val One = Rel("One")
      val Two = Rel("Two")
      val Three = Rel("Three")
      val R = Rel("R")
      val S = Rel("S")

      val Concat = NativeRelation("Concat"){
        case ((Atom(_,ValueTerm(v) :: _), _)) => finalworld += v
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

    println(finalworld)
  }
}