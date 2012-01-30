package fr.emn.criojo.ext

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 14/10/11
 * Time: 16:28
 */
import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._

import org.junit._
import Assert._
import fr.emn.criojo.lang._

class ListsTests {

  logLevel = INFO
//  logLevel = DEBUG

  val lstCham = new ListCham() with NumCham{
    val NewLst = Rel("NewLst")
    val Next = Rel("Rest")
    val PrintFun = Rel("PrintFun")
    val PrintLst = Rel("PrintLst")
    val Dummy = Rel("Dummy")
    val Uno = Rel("Uno")
    val Test = Rel("Test")
    val X = Tok
    private val a,b,c,l,l0,l1,l2,l3,x,y,s = Var

    val Prnt = NativeRelation("Print"){(a,s) => print(a.vars(0).name.span(_ != '@')._1)}
    private val PrintItem = NativeRelation("PrintItem"){(a,s) =>
      print(a.vars(0) + "::")
    }
    private val PrintVar = NativeRelation("PrintVar"){(a,s) =>
      print(a.vars(0))
    }
    private val PrintNil = NativeRelation("PrintNil"){(a,s) => print("[]")}

    when(NewLst(l,a,b,c)){
      Nu(l0,l1,l2)(Nil(l0) & Cons(c,l0,l1) & Cons(b,l1,l2) & Cons(a,l2,l))
    }
//    when(Next(s,l1) & NewLst(l,a,b,c)){
//      Cons(s,b,l1,Next) & NewLst(l,a,b,c)
//    }
//    when(Next(s,l1) & NewLst(l,a,b,c)){
//      Cons(s,a,l1,Next)
//    }
    when(Dummy(a,b)){
      Zero(b)
    }
    when(Uno(a,b)){
      Suc(a,b)
    }
    when(PrintFun(a,b)){PrintNum(a) & Print("::")}

    val h,tl = Var
    val Hd = Rel("Hd")
    val Tl = Rel("Tl")
    val Pr = Rel("Pr")
    val Nl = Rel("Nill+")
    val NotNl = Rel("Nill-")
    val IErr= Rel("IErr")
    when(PrintLst(l)){
      Head(l,Hd,IErr) & Tail(l,Tl,IErr) & IsNil(l,Nl,NotNl) & Pr(l)
    }
    when(Pr(l) & Hd(l,h) & Tl(l,tl)){
      PrintItem(h) & PrintLst(tl) & Hd(l,h) & Tl(l,tl)
    }
    when(Pr(l) & Nl(l)){
      PrintNil()
    }
    when(IErr(l)){
      Print("\nIllegal access. Invalid list ") & PrintVar(l)
    }

    def Print(str:String):Molecule = {
      val strVar = new Variable(str)
      Molecule(List(strVar), List(Prnt(strVar)))
    }
    def introduceAtoms(atoms:Atom*){
      (atoms.toList).foreach { case atm => {
              introduceAtom(atm)
            } }
    }
  }

  val l = Variable("l")
  val l2 = Variable("l2")
  val l3 = Variable("l3")
  val a = Variable("a")
  val b = Variable("b")
  val c = Variable("c")
  val F = new RelVariable("Dummy")
  val F2 = new RelVariable("PrintFun")
  val F3 = new RelVariable("Uno")

  @Test (timeout=1000)
  def listCreation{

    import lstCham.{NewLst,PrintLst}
    lstCham.introduceAtom(NewLst(l,a,b,c))
    lstCham.introduceAtom(PrintLst(l))
//    println("\nSolution: " + lstCham.solution)
    print("\n")

//    lstCham.introduceAtom(Map(l,F,l2))
//    lstCham.introduceAtom(Map(l2,F3,l3))
//    lstCham.introduceAtom(Foreach(l3,F2))
  }

  @Test
  def filterTest{
    import lstCham.{Num,PrintNum,NewLst}

    lstCham.introduceAtoms(Num(2,a),Num(1,b),Num(3,c))
    lstCham.introduceAtom(NewLst(a,b,c))

    lstCham.introduceAtom(PrintNum(a))
//    println("\nSolution: " + lstCham.solution)
  }

  implicit def mol2atom(mol:Molecule):Atom = mol.head
  implicit def int2var(n:Int):Variable = new Variable(n.toString)
}

trait NumCham extends Cham{
  val Num = Rel("Num")
  val Suc = Rel("Suc")
  val Zero = Rel("Zero")
  val PrintNum = Rel("PrintNum")
  private val x,y,x0,x1,x2,x3,t = Var

  var counter = 0
//    private val PrintVal = Rel("PrintVal")
  private val PrintVal = NativeRelation("PV"){(a,s) =>
    if(a.vars.isEmpty){
      print(counter)
      counter = 0
    }
    else counter += 1
  }
  private val CreateNum = NativeRelation("CN"){(a,s) =>
    var num = a.vars(0).toInt
    var z0 = Var
    var z1 = Var
    introduceAtom(Suc(z1,a.vars(1)))
    while(num > 0){
      introduceAtom(Suc(z0,z1))
      z1 = z0; z0 = Var
      num -= 1
    }
    introduceAtom(Zero(z1))
  }
  when (Num(x,y)){CreateNum(x,y)}
  when(PrintNum(x) & Zero(x)){PrintVal() & Zero(x)}
  when(PrintNum(x) & Suc(y,x)){Nu(t)(PrintVal(t) & PrintNum(y) & Suc(y,x))}

}
