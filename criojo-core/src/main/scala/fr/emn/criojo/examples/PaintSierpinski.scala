package fr.emn.criojo.examples

import java.io.FileWriter
import fr.emn.criojo.parallel.Agent

import fr.emn.criojo.expression.scala._
import fr.emn.criojo.core.model._
import fr.emn.criojo.expression.ConversionImpossible

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/25/12
 * Time: 9:45 PM
 * To change this template use File | Settings | File Templates.
 */
object PaintSierpinski extends App with ScalaTypesPredef{
  case class WrappedFile(self:FileWriter) extends Expression with WrappedValue[FileWriter]{
    override def reduce = this
  }

  trait SVGPainter{
    self:Agent =>

    val openFile, paintTriangle, closeFile = LocalRel

    private val printing, file = LocalRel

    private val _openFile = NativeFun {
      case (f:WrappedValue[String])::_ =>
        val fw = new FileWriter(f.self)
        fw.write("""<svg xmlns="http://www.w3.org/2000/svg" version="1.1">\n""")
        List(file(WrappedFile(fw)))
      case _ => List[Atom]()
    }
    private val _closeFile = NativeRel {
      case (fw:WrappedValue[FileWriter])::_ =>
        fw.write("""</svg>""")
        fw.close()
        println("[painter] Closing...")
        System.exit(0)
      case _ =>
    }
    private val _printPolygon = NativeRel {
      case (fw:WrappedValue[FileWriter])::tlst =>
        val sb =  "<polygon points=\"" + tlst.map(term2StringPoint(_)).mkString(" ") +
          "\" style=\"fill:lime;stroke:purple;stroke-width:2\"/>\n"
        fw.write(sb)
      case _ =>
    }
    private def term2StringPoint(term:Term):String = term match {
      case t:ScalaTuple2[_,_] => t._1 + "," + t._2
      case _ => throw new ConversionImpossible("Impossible to convert " + term + " to Tuple2")
    }

    private val f, x, y, z = Var[ScalaInt]

    rules(
      openFile(f) --> Abs(printing()) ?: _openFile(f),
      (paintTriangle(x,y,z) & !file(f)) --> _printPolygon(f, x, y, z),
      (closeFile() & file(f)) --> Abs(paintTriangle(x,y,z)) ?: _closeFile(f)
    )
  }

  val sierpinskiAgent = new Agent with SVGPainter{

    val paintSierpinski = LocalRel
    private val sierpinski,count = LocalRel
    val x, y, n, l, m = Var[ScalaInt]

    rules(
      paintSierpinski(l, n) --> (openFile("etoile.svg") & sierpinski(l, 0, l, n) & count(1)),

      (sierpinski(x, y, l, n) & count(m)) --> {n <=> 0} ?: (count(m-1) & paintTriangle(x->y, (x-l) -> (y+l), (x+l) -> (y+l))),

      (sierpinski(x, y, l, n) & count(m)) -->
        {n > 0} ?: (count(m+2) & sierpinski(x, y, l / 2, n - 1) &
        sierpinski(x - l / 2, y + l / 2, l / 2, n - 1) &
        sierpinski(x + l / 2, y + l / 2, l / 2, n - 1)),

      count(0) --> closeFile()
    )

  }
  sierpinskiAgent.start()

  sierpinskiAgent ! sierpinskiAgent.paintSierpinski(600,7)
}