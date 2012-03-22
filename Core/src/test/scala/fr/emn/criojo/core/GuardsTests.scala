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
    cham.introduceMolecule(R(1,2))
    cham.introduceMolecule(S(2,3))
    cham.executeRules()

    assertTrue("\nExpected : Array(1,2)\n" +
               "Actual   : " + result.mkString("Array(",",",")"),
                Array(1,2).sameElements(result))
  }

  @Test
  def presenceSansParamsTest{
    var finalword = ""

    val cham = new Cham with TestCham{
      val R = Rel("R")
      val S = Rel("S")
      val X = Rel("X")

      val Concat = NativeRelation("Concat"){
        case ((Atom(_,ValueTerm(v) :: _), _)) => finalword += v
        case _ =>
      }

      rules(
        R() --> Prs(X()) ?: Concat("two"),
        (!R() & S()) --> (Concat("one") & X())
      )
    }

    import cham.{R,S}
    cham.introduceMolecule(R())
    cham.introduceMolecule(S())
    cham.executeRules()

    assertEquals("onetwo",finalword)
  }

  @Test
  def absenceTest{
    var finalword = ""

    //Clonning example
    val cham = new Cham with TestCham{
      val One = Rel("One")
      val Two = Rel("Two")
      val Three = Rel("Three")
      val R = Rel("R")
      val S = Rel("S")

      val Concat = NativeRelation("Concat"){
        case ((Atom(_,ValueTerm(v) :: _), _)) => finalword += v
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
  def absenceTest2{
    var finalword = ""

    //Clonning example
    val cham = new Cham with TestCham{
      val One = Rel("One")
      val Two = Rel("Two")
      val Three = Rel("Three")
      val R = Rel("R")
      val S = Rel("S")
      val Test1 = Rel("Test1")
      val Test2 = Rel("Test2")
      val x,y,z = Var

      val Concat = NativeRelation("Concat"){
        case ((Atom(_,ValueTerm(v) :: _), _)) => finalword += v
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
  def notExistsTest{

  }
}