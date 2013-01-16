package fr.emn.criojo.expression

import org.junit.Test

import scala._
import CriojoAssert._
import fr.emn.criojo.testing.TestCham
import fr.emn.criojo.parallel.Agent

class StringTest extends ScalaTypesPredef{
  @Test
  def equalTest() {
    val sFoo1:CriojoString = "foo"
    val sFoo2:CriojoString = "foo"
    val sBar:CriojoString = "bar"

    criojoAssertTrue(sFoo1 <=> sFoo1)
    criojoAssertTrue(sFoo1 <=> sFoo2)
    criojoAssertTrue(!(sFoo1 <=> sBar))
  }

  @Test
  def notEqualTest() {
    val sFoo1:CriojoString = "foo"
    val sFoo2:CriojoString = "foo"
    val sBar:CriojoString = "bar"

    criojoAssertTrue(sFoo1 !<=> sBar)
    criojoAssertTrue(!(sFoo1 !<=> sFoo1))
    criojoAssertTrue(!(sFoo1 !<=> sFoo2))
  }

  @Test
  def lengthTest() {
    val sFoo:CriojoString = "foo"
    val sEmpty:CriojoString = ""

    criojoAssertTrue(sFoo.length > 0)
    criojoAssertTrue(sFoo.length >= 0)
    criojoAssertTrue(sFoo.length !<=> 0)
    criojoAssertTrue(sEmpty.length <= 0)
    criojoAssertTrue(sEmpty.length <=> 0)
  }

  @Test
  def emptyTest() {
    val sEmpty:CriojoString = ""
    val sFoo:CriojoString = "foo"

    criojoAssertTrue(sEmpty.isEmpty)
    criojoAssertTrue(!sFoo.isEmpty)
  }

  @Test
  def concatTest() {
    val sFoo:CriojoString = "foo"
    val sBar:CriojoString = "bar"
    val sFooBar:CriojoString = "foobar"

    criojoAssertTrue((sFoo + sBar) <=> sFooBar)
    criojoAssertTrue((sFoo + sFoo) !<=> sFooBar)
  }

  @Test
  def chamEqualTest() {
    val cham = new Agent with TestCham {
      override def name = "chamEqualTest"
      val Foo = LocalRelation("Foo")
      val Bar = LocalRelation("Bar")
      val x, y = StringVar()

      rules(
        (Foo(x) & Foo(y)) --> AssertTrue(x <=> y)
        , (Foo(x) & Bar(y)) --> AssertTrue(x !<=> y)
      )
    }
    cham.start()

    cham ! cham.Foo("foo")
    cham ! cham.Foo("foo")
    cham ! cham.Foo("foo")
    cham ! cham.Bar("bar")

    Thread.sleep(500)
    cham.testAssert()

  }

  @Test
  def chamLenghtTest() {
    val cham = new Agent with TestCham {
      override def name = "chamLenghtTest"
      val Empty = LocalRelation("Empty")
      val NotEmpty = LocalRelation("NotEmpty")

      val x = StringVar()

      rules (
        Empty(x) --> AssertTrue(x.length <=> 0)
        , NotEmpty(x) --> AssertTrue(x.length <=> 3)
      )
    }
    cham.start()

    cham ! cham.Empty("")
    cham ! cham.NotEmpty("foo")

    Thread.sleep(500)
    cham.testAssert()
  }

  @Test
  def chamEmptyTest() {
    val cham = new Agent with TestCham {
      override def name = "chamEmptyTest"
      val Empty = LocalRelation("Empty")
      val NotEmpty = LocalRelation("NotEmpty")

      val x = StringVar()

      rules (
        Empty(x) --> AssertTrue(x.isEmpty)
        , NotEmpty(x) --> AssertTrue(!x.isEmpty)
      )
    }
    cham.start()

    cham ! cham.Empty("")
    cham ! cham.NotEmpty("foo")

    Thread.sleep(500)
    cham.testAssert()
  }

  @Test
  def chamConcatTest() {
    val cham = new Agent with TestCham {
      override def name = "chamConcatTest"
      val Foo = LocalRelation("A")
      val Bar = LocalRelation("B")

      val x, y = StringVar()
      val fooBar:CriojoString = "foobar"

      rules (
        (Foo(x) & Bar(y)) --> AssertTrue((x + y) <=> fooBar)
        , (Foo(x) & Foo(y)) --> AssertTrue((x + y) !<=> fooBar)
      )
    }
    cham.start()

    cham ! cham.Foo("foo")
    cham ! cham.Foo("foo")
    cham ! cham.Foo("foo")
    cham ! cham.Bar("bar")

    Thread.sleep(500)
    cham.testAssert()
  }

}
