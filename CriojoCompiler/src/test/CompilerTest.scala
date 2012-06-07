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

    println(compiler.parseAll(compiler.atomRight,"A(\"A\",\"aaa\")").get)
    println(compiler.parseAll(compiler.atomLeft,"A(\"A\",\"aaa\")").get)

    assert(true)
  }

  @Test
  def variableDeclarationTest() {
    val compiler = new CriojoCompiler()
    println(compiler.parseAll(compiler.declarations,"[x:int,y:string]").get)
    assert(true)
  }

  @Test
  def scalaCodeTest() {
    val compiler = new CriojoCompiler()
    println(compiler.parseAll(compiler.scalaCode,"{var i=0+x}").get)
    println(compiler.parseAll(compiler.nativeCode,"(x){var a =0+1;\nvar b=f(a);;\ndef truc(){return 1;}}(i)").get)
    println(compiler.parseAll(compiler.atomsRight,"A(1),A(2)").get)
    println(compiler.parseAll(compiler.atomsRight,"(x){var a =0+1;\nvar b=f(a);;\ndef truc(){return 1;}}(i),(x){var a =0+1;\nvar b=f(a);;\ndef truc(){return 1;}}(i)").get)
    println(compiler.parseAll(compiler.atomsRight,"A(1 ,1),(x){var a =0+1;\nvar b=f(a);;\ndef truc(){return 1;}}(i),(x){var a =0+1;\nvar b=f(a);;\ndef truc(){return 1;}}(i), B(5)").get)
    println(compiler.parseAll(compiler.atomsRight, "(x){a}(i),(x){a}(i),A(5) ,B(5), (x){a}(i)").get)
    println(compiler.parseAll(compiler.atomsRight," (x){var truc}(i)").get)
    assert(true)
  }

  @Test
  def ruleTest() {
    val compiler = new CriojoCompiler()
    println(compiler.parseAll(compiler.rule, "@Kc()->B( 2) ,A(1) ").get)
    println(compiler.parseAll(compiler.rule, "@Kc()-> (){}() ").get)

    println(compiler.parseAll(compiler.cham, "B{ [d:int,e:string] {var truc=1} @Kc() -> (){}()}").get)
    println(compiler.parseAll(compiler.cham, "B{Kc()-> (){var i=0 ;{hello}}() }").get)
    println(compiler.parseAll(compiler.cham, "B{ @Kc() -> A(1), A(1)\n @Kc() ->(){def a(a){a+1}}() }").get)
    println(compiler.parseAll(compiler.cham, " B { @Kc () -> A(1) , A(1)\n @Kc()->(){()}() } ").get)
    assert(true)
  }

  @Test
  def adressPrefixTest() {
    val compiler = new CriojoCompiler()

    println(compiler.parseAll(compiler.addressPrefix,"a.b.c.d.").get)
    println(compiler.parseAll(compiler.remoteChannelId,"a.b.c.d.A.@K").get)

    assert(true)
  }

  @Test
  def computeTest() {
    new CriojoCompiler().parse(" server1{cham1 {} cham2{}} server2{cham3{}cham4{}}")
    new CriojoCompiler().parse(" server1{ cham1 { } cham2{} } server2 { cham3{}cham4{} } ")

    assert(true)
  }

  @Test
  def firewallTest() {
    println(new CriojoCompiler().parse("server1{firewall(cham1.@A, cham2.@B){ cham1{ @A(x)->cham2.@B(x) } cham2{ @B(x)->D(x) } } }"))

    assert(true)
  }

  @Test
  def computeFileTest() {
    var inputProgram:String = ""
    try {
      inputProgram = scala.io.Source.fromFile("program.criojo").mkString
    }
    catch {
      case e:Exception => inputProgram = scala.io.Source.fromFile("/Users/jonathan/Documents/workspace_stage/CRIOJO_TESTTEST/CriojoCompiler/program.criojo").mkString
    }
    println (new CriojoCompiler().parse(inputProgram) )

    assert(true)
  }

//  @Test
  def mainCompilerTest1Arg() {

    try {
      val args = new Array[String](1)
      args(0) = "program.criojo"
      CriojoCompiler.main(args)
    }
    catch {
      case e:Exception => {
        val args = new Array[String](1)
        args(0) = "/Users/jonathan/Documents/workspace_stage/CRIOJO/CriojoCompiler/program.criojo"
        CriojoCompiler.main(args)
      }
    }
    assert(true)
  }

//  @Test
  def mainCompilerTest2Arg() {

    try {
      val args = new Array[String](2)
      args(0) = "program.criojo"
      args(1) = "out.criojo"
      CriojoCompiler.main(args)
    }
    catch {
      case e:Exception => {
        val args = new Array[String](2)
        args(0) = "/Users/jonathan/Documents/workspace_stage/CRIOJO/CriojoCompiler/program.criojo"
        args(1) = "/Users/jonathan/Documents/workspace_stage/CRIOJO/CriojoCompiler/out.criojo"
        CriojoCompiler.main(args)
      }
    }

    assert(true)
  }
}
