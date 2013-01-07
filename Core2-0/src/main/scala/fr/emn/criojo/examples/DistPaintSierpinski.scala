package fr.emn.criojo.examples

import java.io.FileWriter
import fr.emn.criojo.parallel.Agent

import fr.emn.criojo.core.model.relation.{Channel, VarChannel}
import fr.emn.criojo.expression.scala.WrapScalaInt

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/25/12
 * Time: 9:45 PM
 * To change this template use File | Settings | File Templates.
 */
object DistPaintSierpinski extends App{
  val cm = new Agent("cm", LocalGateway) {

    val Sierpinski = InputChannel("Sierpinski")
    val session = LocalRel("Session")
    val sierpinski = LocalRel
    val x, y, z, a, b, c, lp, xp1, xp2, yp, n, np, l, vx, vy, vl, m = Var[Int]
    val k, k1,k2 = Var[Channel]

    val count = LocalRel

    rules(
      Sierpinski(k1,k2,x, y, l, n) --> (sierpinski(k1, x, y, l, n) & session(k2) & count(1)),

      (sierpinski(k, x, y, l, n) & count(m)) --> {n <=> 0} ?: (k(x, y, l) & count(m-1)),

      (sierpinski(k, x, y, l, n) & count(m)) --> {n > 0} ?: (sierpinski(k, x, y, l / 2, n - 1) & sierpinski(k, x - l / 2, y - l / 2, l / 2, n - 1) &
        sierpinski(k, x + l / 2, y - l / 2, l / 2, n - 1) & count(m+2)),

      (session(k) & count(0)) --> Abs(sierpinski(a,x, y, l, n)) ?: k()
    )
    //DEBUG_MODE = true
  }
  LocalGateway.addAgent(cm.location,cm)
  cm.start()

  //    //    import cm.num2fun
  val painter = new Agent("painter", LocalGateway) {
    val fw = new FileWriter("etoile.svg")

    val Start = LocalRel
    val Cm = OutputChannel("Sierpinski", "cm")
    val Paint = InputChannel("Paint")
    val End = InputChannel("End")

    val OpenFile = NativeRel {
      l =>
        fw.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n")
    }
    val CloseFile = NativeRel {
      l =>
        fw.write("</svg>\n")
        fw.close()
        println("[painter] Closing...")
        System.exit(0)
    }
    val Print = NativeRel {
      case WrapScalaInt(xvalue) :: WrapScalaInt(yvalue) :: WrapScalaInt(lvalue) :: Nil => {
        fw.write("<polygon points=\"" + xvalue + "," + yvalue + " " + (xvalue - lvalue) + "," + (yvalue - lvalue) + " " + (xvalue + lvalue) + "," + (yvalue - lvalue) + "\" style=\"fill:lime;stroke:purple;stroke-width:2\"/>\n")
      }
      case _ =>
    }

    val x, y, z = Var[Int]

    rules(
      Start() --> (OpenFile() & Cm(Paint,End, 700, 700, 700, 7)),
      Paint(x, y, z) --> Print(x, y, z),
      End() --> CloseFile()
    )
  }
  LocalGateway.addAgent(painter.location, painter)
  painter.start()

  painter ! painter.Start()
}