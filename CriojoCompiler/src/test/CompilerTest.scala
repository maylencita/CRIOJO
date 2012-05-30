import org.junit.Test


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/10/12
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */

class CompilerTest {

  @Test
  def expressionTest() {
    val compiler = new CriojoCompiler()

    println(compiler.parseAll(compiler.exp,"\"abcdDedd\"").get)
    println(compiler.parseAll(compiler.exp,"12").get)

    assert(true)
  }

  @Test
  def expressionSTest() {
    val compiler = new CriojoCompiler()

    println(compiler.parseAll(compiler.exps,"\"abcdDedd\"").get)
    println(compiler.parseAll(compiler.exps,"12").get)
    println(compiler.parseAll(compiler.exps,"\"abcdDedd\",\"abcdDedd\"").get)
    println(compiler.parseAll(compiler.exps,"12,\"abcdDedd\"").get)

    assert(true)
  }

  @Test
  def atomTest() {
    val compiler = new CriojoCompiler()

    println(compiler.parseAll(compiler.atom,"A(\"A\",\"aaa\")").get)

    assert(true)
  }

  @Test
  def computeTest() {
    new CriojoCompiler().parse("server1{cham1{}cham2{}}server2{cham3{}cham4{}}")

    assert(true)
  }

  @Test
  def firewallTest() {
    println(new CriojoCompiler().parse("server1{firewall(cham1.A){cham1{}}}"))

    assert(true)
  }

  @Test
  def computeFileTest() {
    val inputProgram = scala.io.Source.fromFile("program.criojo").mkString
    new CriojoCompiler().parse(inputProgram)

    assert(true)
  }

  @Test
  def mainCompilerTest1Arg() {

    val args = new Array[String](1)
    args(0) = "program.criojo"
    CriojoCompiler.main(args)

    assert(true)
  }

  @Test
  def mainCompilerTest2Arg() {

    val args = new Array[String](2)
    args(0) = "program.criojo"
    args(1) = "out.criojo"
    CriojoCompiler.main(args)

    assert(true)
  }
}
