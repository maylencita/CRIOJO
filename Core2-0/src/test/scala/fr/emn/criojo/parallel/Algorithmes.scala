package fr.emn.criojo.parallel

import org.junit.Test
import org.junit.Assert._
import fr.emn.criojo.expression.scala.{ScalaTypesPredef, WrapScalaInt}
import fr.emn.criojo.examples.LocalGateway
import fr.emn.criojo.core.model.relation.Channel
import fr.emn.criojo.core.model.{WrappedValue, Atom}
import fr.emn.criojo.dsl.ChamBody
import fr.emn.criojo.core.engine.Cham


/*
* Created by IntelliJ IDEA.
* User: hgrall
* Date: 4 mai 2012
*/


class Algorithmes extends ScalaTypesPredef{

  @Test
  def hectorTest() {
    var result = false

    val fCham = new Agent {
      val H = LocalRel
      val R = NativeRel{
        case WrapScalaInt(n)::_ => if (n == 7-2/10){result = true}
        case _ => println("Wrong result!")
      }

      val x, y, z = Var[Int]//VarScalaInt()

      rules(
        (H(1, x) & H(2, y) & H(3, z) )  --> R(x - y / z )
      )
    }
    fCham.start()
    fCham ! fCham.H(1,7)
    fCham ! fCham.H(2, 2)
    fCham ! fCham.H(3, 10)

    Thread.sleep(500)
    assertTrue(result)
  }

  @Test
  def distributedCalcul(){
    var result = false

    val divAgent = new Agent("divAgent", LocalGateway){
      val Div = InputChannel("Div")
      val k = Var[Channel]
      val x,y = Var[Int]

      rules(
        Div(k,x,y) --> k(x / y)
      )
    }
    LocalGateway.addAgent(divAgent.location,divAgent)
    divAgent.start()

    val calcAgent = new Agent("calcAgent", LocalGateway){
      val Div = OutputChannel("Div","divAgent")
      val Answer = InputChannel("Answer")
      val H = LocalRel
      val R = NativeRel {
        case WrapScalaInt(n)::_ => if (n == 7-2/10){result = true}
        case _ => print("Wrong answer!")
      }
      val x,y,z = Var[Int]

      rules(
        (H(2, y) & H(3, z) ) --> Div(Answer,y,z),
        (H(1, x) & Answer(z)) --> R(x - z)
      )
    }
    LocalGateway.addAgent(calcAgent.location,calcAgent)
    calcAgent.start()

    calcAgent ! List(calcAgent.H(1,7),calcAgent.H(2,2),calcAgent.H(3,10))

    Thread.sleep(1000)
    assertTrue(result)
  }

  @Test
  def modularCalcul(){
    var result = false

    trait DivAgent extends ChamBody with ScalaTypesPredef{
      this: Cham =>

      val Div, DivAnswer = LocalRel
      val k = Var[Channel]
      private val x,y = Var[Int]

      rules(
        Div(x,y) --> DivAnswer(x / y)
      )
    }

    val calcAgent = new Agent with DivAgent{
      val H = LocalRel
      val R = NativeRel {
        case WrapScalaInt(n)::_ => if (n == 7-2/10){result = true}
        case _ => print("Wrong answer!")
      }
      val x,y,z = Var[Int]

      rules(
        (H(2, y) & H(3, z) ) --> Div(y,z),
        (H(1, x) & DivAnswer(z)) --> R(x - z)
      )
    }

    calcAgent.start()

    calcAgent ! List(calcAgent.H(1,7),calcAgent.H(2,2),calcAgent.H(3,10))

    Thread.sleep(1000)
    assertTrue(result)
  }

  @Test
  def maxTest() {
    var result:Int = 0

    val fCham = new Agent{
      val V = LocalRel
      val Max = NativeRel {
        case WrapScalaInt(n)::_ => result = n
        case _ =>
      }

      val x, y = Var[Int]


      rules(
        (V(x) & V(y))  --> {x <= y} ?: (V(y) & Max(y))
      )
    }
    fCham.start()

    fCham ! List(fCham.V(2),fCham.V(2),fCham.V(3),fCham.V(4),fCham.V(4))

    Thread.sleep(1000)
    assertEquals(4, result)
  }

  @Test
  def bubbleTest() {
    var atomList = List[Pair[Any,Any]]()

    val fCham = new Agent{
      val L = LocalRel
      val Export = LocalRel

      val ExportList = NativeRel {
        case v1::v2::_ => atomList :+= (v1,v2)
        case _ =>
      }

      val x, y, u, v = Var[Int]


      rules(
        (L(x, u) & L(y, v))  --> {(y <=>  (x + 1)) && (v < u)} ?: (L(x, v) & L(y, u)),
        (Export() & L(x,y)) --> ExportList(x,y)
      )
    }
    fCham.start()

    val j = 5
    for (i <- 0 to j)
      fCham ! fCham.L(i, j - i)

    Thread.sleep(500)
    assertTrue(atomList.forall {p => p._1 == p._2})
  }

  @Test
  def listFill(){
    var atomList = List[Pair[Any,Any]]()

    val agent = new Agent(){
      val Init = LocalRel
      val L = LocalRel
      val Export = LocalRel

      private val _genList = NativeFun{
        case (max:WrappedValue[Int])::_ =>
          (0 to max).map(i => L(i,max-i)).toList
        case _ => List[Atom]()
      }

      val _exportList = NativeRel {
        case v1::v2::_ => atomList :+= (v1,v2)
        case _ =>
      }

      val x, y, u, v = Var[Int]

      rules(
        Init(x) --> _genList(x),
        (L(x, u) & L(y, v))  --> {(y <=>  (x + 1)) && (v < u)} ?: (L(x, v) & L(y, u)),
        (Export() & L(x,y)) --> _exportList(x,y)
      )
    }

    agent.start()
    agent ! agent.Init(5)

    Thread.sleep(500)
    assertTrue(atomList.forall {p => p._1 == p._2})

  }
}

