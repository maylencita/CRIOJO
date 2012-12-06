package fr.emn.criojo.expression

import map.constructor.WrapScalaMap
import map.{VarCriojoMap, NoSuchKeyException, CriojoMap}
import org.junit.{Before, Test}
import scala._
import fr.emn.criojo.parallel.Agent
import scala.WrapScalaInt
import scala.WrapScalaString
import fr.emn.criojo.testing.TestCham

class CriojoMapTest {
  var map: CriojoMap[ScalaString, ScalaInt] = _
  var a, b, c: ScalaString = _
  var _1, _2, _3: ScalaInt = _


  @Before def setUp() {
    a = WrapScalaString("a")
    b = WrapScalaString("b")
    c = WrapScalaString("c")

    _1 = WrapScalaInt(1)
    _2 = WrapScalaInt(2)
    _3 = WrapScalaInt(3)

    map = WrapScalaMap(Map(a -> _1, b -> _2, c -> _3))
  }

  @Test (expected = classOf[NoSuchKeyException]) def testRequest() {
    val cham = new Agent with TestCham{
      val name = "testRequest"
      val Map = LocalRelation("Map")
      val m = VarCriojoMap[ScalaString, ScalaInt]()
      val d = WrapScalaString("d")
      val _4 = WrapScalaInt(4)

      rules(
        Map(m) --> (AssertTrue(m.request(a, _ <=> _1))
          & AssertTrue(m.request(b, _ <=> _2))
          & AssertTrue(m.request(c, _ <=> _3))
          & AssertTrue(m.request(d, _ <=> _4)))
      )
    }

    cham.introduceAtom(cham.Map(map))
    cham.executeRules()

    cham.testAssert()
  }

  @Test def testRequestInsert() {
    val cham = new Agent with TestCham{
      val name = "testRequest"
      val Map = LocalRelation("Map")
      val Int = LocalRelation("Int")
      val m = VarCriojoMap[ScalaString, ScalaInt]()
      var i = VarScalaInt()

      rules(
        Map(m) --> Int(m.request(a, i => i))
        , Int(i) --> AssertTrue(i <=> _1)
      )
    }

    cham.introduceAtom(cham.Map(map))
    cham.executeRules()

    cham.testAssert()
  }

  @Test def testUpdate() {
    val cham = new Agent with TestCham{
      val name = "testUpdate"
      val Map = LocalRelation("Map")
      val UpdatedMap = LocalRelation("UpdatedMap")
      val m = VarCriojoMap[ScalaString, ScalaInt]()

      rules(
        Map(m) --> UpdatedMap(m.update(a, _ + _1))
        , UpdatedMap(m) --> AssertTrue(m.request(a, _ <=> _2))
      )
    }

    cham.introduceAtom(cham.Map(map))
    cham.executeRules()

    cham.testAssert()
  }

  @Test (expected = classOf[NoSuchKeyException]) def testNoSuchKeyUpdate() {
    val cham = new Agent with TestCham{
      val name = "testNoSuchKeyUpdate"
      val Map = LocalRelation("Map")
      val UpdatedMap = LocalRelation("UpdatedMap")
      val m = VarCriojoMap[ScalaString, ScalaInt]()
      val d = WrapScalaString("d")

      rules(
        Map(m) --> UpdatedMap(m.update(d, _ + _1))
      )
    }

    cham.introduceAtom(cham.Map(map))
    cham.executeRules()

    cham.testAssert()
  }

  @Test def testForAll() {
    val cham = new Agent with TestCham{
      val name = "testForAll"
      val Map = LocalRelation("Map")
      val m = VarCriojoMap[ScalaString, ScalaInt]()
      val d = WrapScalaString("d")
      val _4 = WrapScalaInt(4)

      rules(
        Map(m) --> AssertTrue(m.forall(kv => kv._1 !<=> d && kv._2 < _4))
      )
    }

    cham.introduceAtom(cham.Map(map))
    cham.executeRules()

    cham.testAssert()
  }

  @Test def testFoldLeft() {
    val cham = new Agent with TestCham{
      val name = "testFoldLeft"
      val Map = LocalRelation("Map")
      val StartValue = LocalRelation("StartValue")
      val Sum = LocalRelation("Sum")

      val m = VarCriojoMap[ScalaString, ScalaInt]()
      val i = VarScalaInt()
      val expectedResult = WrapScalaInt(9)

      rules(
        (Map(m) & StartValue(i)) --> Sum(m.foldLeft[ScalaInt](i)((sum, kv) => sum + kv._2))
        , Sum(i) --> AssertTrue(i <=> expectedResult)
      )
    }

    cham.introduceAtom(cham.Map(map))
    cham.introduceAtom(cham.StartValue(_3))
    cham.executeRules()

    cham.testAssert()
  }
}

