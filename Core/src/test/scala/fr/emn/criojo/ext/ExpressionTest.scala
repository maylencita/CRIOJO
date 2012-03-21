package fr.emn.criojo.core

import org.junit.Test
import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.ext.expressions._
import fr.emn.criojo.lang.{Nu, Cham}


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/16/12
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */

class ExpressionTest {

  @Test
  def ExpressionAddTest {

    implicit def int2term(n:Int):Expression = IntExpression(n)

    val x:Expression = 1
    val y:Expression = 2

    val result = x+y
    assert(result.isInstanceOf[BinaryExpression])
    println(result)
  }

  @Test
  def ExpressionValueAddEvalTest {

    implicit def int2term(n:Int):Expression = IntExpression(n)

    val x:Expression = 1
    val y:Expression = 2

    val result = x+y+2+3+4
    val result2 = result.eval()
    assert(result.isInstanceOf[BinaryExpression])
  }

  @Test
  def ExpressionWithUndefinedvalTest {

    implicit def int2term(n:Int):Expression = IntExpression(n)

    val x:Expression = 1
    val y:Expression = 2

    val result11 = x+y+UndefinedExpression+3+4
    val result12 = result11.eval()
    assert(result12.equals(UndefinedExpression))

    val result21 = UndefinedExpression+x+y+3+4
    val result22 = result21.eval()
    assert(result22.equals(UndefinedExpression))

    val result31 = x+y+3+4+UndefinedExpression
    val result32 = result21.eval()
    assert(result32.equals(UndefinedExpression))
  }

  @Test
  def StringExpressionTest {

    implicit def string2Exp(n:String):Expression = StrExpression(n)
    implicit def int2term(n:Int):Expression = IntExpression(n)

    val x:Expression = 1
    val y:Expression = "2"

    val result = x+y+2+3+4
    val result2 = result.eval()
    assert(result.isInstanceOf[BinaryExpression])
  }

  @Test
  def VariableTest {

    implicit def string2Exp(n:String):Expression = StrExpression(n)
    implicit def int2term(n:Int):Expression = IntExpression(n)

    val x:Expression = 1
    val y:Expression = 2

    var x1 = VarExpression("x1")
    var x2 = VarExpression("x2")
    var x3 = VarExpression("x3")

    var listOfSubs = Valuation(x1 -> x, x2 -> y)

    val result = x1+x2
    val result2 = result.eval()
    val result3 = result.eval(listOfSubs)
    assert(result3.isInstanceOf[IntExpression] && result3.asInstanceOf[IntExpression].getValue()==3)

    val resultb = x1+x2+x3
    val result2b = resultb.eval()
    val result3b = resultb.eval(listOfSubs)
    assert(result3b.equals(UndefinedExpression))
  }


  @Test
  def BonbonstestRelations {

    val machine = new Cham with IntegerCham { //TestCham with DefaultCham{
      implicit def string2Exp(n:String):Expression = StrExpression(n)
      implicit def int2term(n:Int):Expression = IntExpression(n)

      val x,y,z = Var
      val OneBonbon = Rel("OneBonbon")
      val TwoBonbons = Rel("TwoBonbons")

      rules(
        (OneBonbon(x) &: OneBonbon(y)) --> TwoBonbons(x+y+1)
      )

      DEBUG_MODE = true
    }

    implicit def string2Exp(n:String):Expression = StrExpression(n)
    implicit def int2term(n:Int):Expression = IntExpression(n)

    machine.introduceMolecule(machine.OneBonbon(1))
    assert(machine.getSolution.containsAtom(machine.OneBonbon, 1))
    machine.executeRules()

    machine.introduceMolecule(machine.OneBonbon(2))
    machine.executeRules()
    assert(machine.getSolution.containsAtom(machine.TwoBonbons, 1))
    assert(machine.getSolution.containsAtom(machine.OneBonbon, 0))
    assert(machine.getSolution.size==1)
  }

  @Test
  def HydrolyseTestRelations {



    val machine = new Cham with IntegerCham { //TestCham with DefaultCham{
      val n1,n2,n3,n4,n5 = Var
      val R1_COO_R2 = Rel("R1_COO_R2")
      val R2_OH = Rel("R2_OH")
      val R1_COOH = Rel("R1_COOH")
      val H2O = Rel("H2O")
      val COO = Rel("COO")
      val R1 = Rel("R1")
      val R2 = Rel("R2")
      val C = Rel("C")
      val O = Rel("O")
      val H = Rel("H")

      implicit def int2term(n:Int):Expression = IntExpression(n)

      val EXPLODE_R1_COO_R2 = Rel("EXPLODE_R1_COO_R2")
      val EXPLODE_H2O = Rel("EXPLODE_H2O")

      rules(

        // INITIATION
        (R1_COO_R2(n1) &: H2O(n2)) --> Gr(Min(n1,n2),0) ?: (R1_COO_R2(n1-Min(n1,n2)) & H2O(n2-Min(n1,n2))  & EXPLODE_R1_COO_R2(Min(n1,n2)) & EXPLODE_H2O(Min(n1,n2))),

        // DIVISION
        (EXPLODE_R1_COO_R2(n1) &: R1(n2) &: R2(n3) &: COO(n4)) --> (R1(n2+n1) & R2(n3+n1) & COO(n4+n1)),
        (EXPLODE_H2O(n1) &: H(n2) &: O(n3)) --> (H(n2+2*n1) & O(n3+n1)),

        // SUB DIVISION
        (COO(n1) &: C(n2) &: O(n3)) --> Gr(n1,0) ?: (COO(0) & C(n2+n1) & O(n3+2*n1)),

        // RECOMPOSITION
        (R2(n1) &: O(n2) &: H(n3) &: R2_OH(n4)) --> Gr(Min(n1,n2,n3), 0) ?: ( R2(n1-Min(n1,n2,n3)) & O(n2-Min(n1,n2,n3)) & H(n3-Min(n1,n2,n3)) & R2_OH(n4+Min(n1,n2,n3))),
        (R1(n1) &: C(n2) &: O(n3) &: H(n4) &: R1_COOH(n5)) --> Gr(Min(n1,n2,n3/2,n4), 0) ?: (R1_COOH(n5+Min(n1,n2,n3/2,n4)) & R1(n1-Min(n1,n2,n3/2,n4)) & C(n2-Min(n1,n2,n3/2,n4)) & O(n3-2*Min(n1,n2,n3/2,n4)) & H(n4-Min(n1,n2,n3/2,n4)))
      )

      DEBUG_MODE = true
    }

    import machine.int2term

    machine.introduceMolecule(machine.R1_COO_R2(1))
    machine.introduceMolecule(machine.H2O(2))
    machine.introduceMolecule(machine.R2_OH(0))
    machine.introduceMolecule(machine.R1_COOH(0))
    machine.introduceMolecule(machine.COO(0))
    machine.introduceMolecule(machine.R1(0))
    machine.introduceMolecule(machine.R2(0))
    machine.introduceMolecule(machine.C(0))
    machine.introduceMolecule(machine.O(0))
    machine.introduceMolecule(machine.H(0))
    machine.executeRules()

    assert(machine.getSolution.containsMolecule(machine.R1_COO_R2(0)))
    assert(machine.getSolution.containsMolecule(machine.H2O(1)))
    assert(machine.getSolution.containsMolecule(machine.R1_COOH(1)))

    println(machine.getSolution)
  }

  @Test
  def WordTest {

    val machine = new Cham with IntegerCham { //TestCham with DefaultCham{
      val w,kp,km = Var
      val InsertWord = Rel("InsertWord")
      val AskContain = Rel("AskContain")
      val Kp = VarR("Kp")
      val Km = VarR("Km")
      val OK = VarR("OK")
      val NOTOK = VarR("NOTOK")
      val Clean = Rel("Clean")

      val PrintInt = NativeRelation("PrintInt"){
        case (Atom(_,x::_),_) => println(x)
      }

      rules(
        (AskContain(w,Kp,Km) &: InsertWord(w)) --> (Kp(w) & Clean()),
        (AskContain(w,Kp,Km)) --> Abs(InsertWord(w)) ?: (Km(w) & Clean()),
        (Clean() &: InsertWord(w) ) --> Clean()
      )

      DEBUG_MODE = true
    }

    import machine.num2fun
    implicit def stringToValue(s:String) = new StrExpression(s)

    machine.introduceMolecule(machine.InsertWord("aa"))
    machine.introduceMolecule(machine.InsertWord("bb"))
    machine.introduceMolecule(machine.InsertWord("cc"))
    machine.introduceMolecule(machine.InsertWord("dd"))
    machine.introduceMolecule(machine.InsertWord("dd"))
    machine.introduceMolecule(machine.InsertWord("ee"))
    machine.introduceMolecule(machine.InsertWord("ff"))
    machine.introduceMolecule(machine.InsertWord("gg"))

    machine.introduceMolecule(machine.AskContain("gg", machine.OK, machine.NOTOK))
    machine.introduceMolecule(machine.AskContain("gh", machine.OK, machine.NOTOK))
    machine.executeRules()

    machine.printTrace()
    println(machine.getSolution)
    
    assert(machine.getSolution.containsMolecule(machine.OK("gg")))
    assert(machine.getSolution.containsMolecule(machine.NOTOK("gh")))

    //println(machine.printRules)
  }

  @Test
  def MapReduceTest {

    val machine = new Cham with IntegerCham { //TestCham with DefaultCham{
      val z,w,n,i,j,kp,km = Var
      val InsertWord = Rel("InsertWord")
      val Count = Rel("Count")

      val PrintInt = NativeRelation("PrintInt"){
        case (Atom(_,x::_),_) => println(x)
      }

      rules(
        InsertWord(n, w) --> Count(n, w, 1), // MAP
        (Count(n, w, i) & Count(z, w, j)) --> Count(n, w, i+j) // REDUCE
      )

      DEBUG_MODE = true
    }

    import machine.num2fun
    implicit def stringToValue(s:String) = StrExpression(s)

    machine.introduceMolecule(machine.InsertWord(1,"aa"))
    machine.introduceMolecule(machine.InsertWord(2,"aa"))
    machine.introduceMolecule(machine.InsertWord(3,"bb"))
    machine.introduceMolecule(machine.InsertWord(4,"bb"))
    machine.introduceMolecule(machine.InsertWord(5,"cc"))
    machine.introduceMolecule(machine.InsertWord(6,"aa"))
    machine.introduceMolecule(machine.InsertWord(7,"bb"))
    machine.introduceMolecule(machine.InsertWord(8,"dd"))
    machine.executeRules()

    println(machine.getSolution)
    
    //assert(machine.getSolution.containsMolecule(machine.Count(7,"bb",3)))
    //assert(machine.getSolution.containsMolecule(machine.Count(8,"dd",1)))
  }

  @Test
  def DjikstraTest {

    val machine = new Cham with IntegerCham { //TestCham with DefaultCham{
      val x,y,z,w,n,i,j = Var

      val AreConnected = Rel("LeadsTo")
      val AreInRelation = Rel("AreInRelation")
      val IsItEquivalent = Rel("IsItEquivalent")

      val PUSH = Rel("PUSH")
      val POP = Rel("POP")

      val PrintInt = NativeRelation("PrintInt"){
        case (Atom(_,x::_),_) => println(x)
      }

      rules(
        IsItEquivalent(x,y) --> Nu(i)( AreInRelation(x,y,i) ),
        (AreInRelation(x,w,n) &: AreConnected(x,y)) --> NotEq (x,w) ?: Nu(i)( (AreInRelation(x,w,n) & PUSH(i,n,x) & AreInRelation(y,w,i)) ),
        AreInRelation(x,y,n) --> Eq(x,y) ?: (PrintInt(x) & POP(n)),
        (POP(j) &: PUSH(i,n,x)) --> Eq(i,j) ?: ( PrintInt(x) & POP(n))
      )

      DEBUG_MODE = true
    }

    import machine.num2fun
    
    implicit def str2fun(s:String):Term = new StrExpression(s)
    
    machine.introduceMolecule(machine.AreConnected("A","B"))
    machine.introduceMolecule(machine.AreConnected("B","C"))
    machine.introduceMolecule(machine.AreConnected("C","D"))
    machine.introduceMolecule(machine.AreConnected("D","E"))
    machine.introduceMolecule(machine.AreConnected("B","D"))
    machine.introduceMolecule(machine.AreConnected("D", "F"))

    machine.introduceMolecule(machine.IsItEquivalent("A","E"))
    machine.executeRules()
    //assert(machine.getSolution.size==1)
    println(machine.getSolution)
  }
}
