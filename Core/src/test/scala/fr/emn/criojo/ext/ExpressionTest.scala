//package fr.emn.criojo.ext
//
//
//import debug.DebugCham
//import expression._
//import org.junit.Test
//import fr.emn.criojo.lang.{Nu, Cham}
//import fr.emn.criojo.core.{Atom, Term, Valuation}
//import fr.emn.criojo.ext.expression.converters._
//
///**
// * Created by IntelliJ IDEA.
// * User: jonathan
// * Date: 2/16/12
// * Time: 10:32 AM
// * To change this template use File | Settings | File Templates.
// */
//
//class ExpressionTest {
//
//  @Test
//  def ExpressionAddTest() {
//
//    //implicit def int2term(n: Int): Expression = IntExpression(n)
//
//    val x: Expression = 1
//    val y: Expression = 2
//
//    val result:Expression = x + y
//    assert(result.isInstanceOf[BinaryExpression])
//    println(result)
//  }
//
//  @Test
//  def ExpressionValueAddEvalTest() {
//
//    //implicit def int2term(n: Int): Expression = IntExpression(n)
//
//    val x: Expression = 1
//    val y: Expression = 2
//
//    val result = x + y + 2 + 3 + 4
//    val result2 = result.reduce()
//    assert(result.isInstanceOf[BinaryExpression])
//  }
//
//  @Test
//  def ExpressionWithUndefinedvalTest() {
//
////    implicit def int2term(n: Int): Expression = IntExpression(n)
//
//    val x: Expression = 1
//    val y: Expression = 2
//
//    val result11 = x + y + NoneTerm + 3 + 4
//    val result12 = result11.reduce()
//    assert(result12.equals(NoneTerm))
//
//    val result21 = NoneTerm + x + y + 3 + 4
//    val result22 = result21.reduce()
//    assert(result22.equals(NoneTerm))
//
//    val result31 = x + y + 3 + 4 + NoneTerm
//    val result32 = result21.reduce()
//    assert(result32.equals(NoneTerm))
//  }
//
//  @Test
//  def StringExpressionTest() {
//
////    implicit def string2Exp(n: String): Expression = StrExpression(n)
////    implicit def int2term(n: Int): Expression = IntExpression(n)
//
//    val x: Expression = 1
//    val y: Expression = "2"
//
//    val result = x + y + 2 + 3 + 4
//    val result2 = result.reduce()
//    assert(result.isInstanceOf[BinaryExpression])
//  }
//
//  @Test
//  def VariableTest() {
//
////    implicit def string2Exp(n: String): Expression = StrExpression(n)
////    implicit def int2term(n: Int): Expression = IntExpression(n)
//
//    val x: Expression = 1
//    val y: Expression = 2
//
//    var x1 = VarExpression("x1")
//    var x2 = VarExpression("x2")
//    var x3 = VarExpression("x3")
//
//    var listOfSubs = Valuation(x1 -> x, x2 -> y)
//
//    val result = x1 + x2
//    val result2 = result.reduce()
//    val result3 = result.reduce(listOfSubs)
//
//    assert(result3.correspondsTo(3))
//
//    val resultb = x1 + x2 + x3
//    val result2b = resultb.reduce()
//    val result3b = resultb.reduce(listOfSubs)
//    assert(result3b.equals(NoneTerm))
//  }
//
//
//  @Test
//  def BonbonstestRelations() {
//
//    val machine = new Cham with IntegerCham with DebugCham {
//      //TestCham with DefaultCham{
////      implicit def string2Exp(n: String): Expression = StrExpression(n)
//
////      implicit def int2term(n: Int): Expression = IntExpression(n)
//
//      val x, y, z = Var
//      val OneBonbon = LocalRelation("OneBonbon")
//      val TwoBonbons = LocalRelation("TwoBonbons")
//
//      rules(
//        (OneBonbon(x) &: OneBonbon(y)) --> TwoBonbons(x + y + 1)
//      )
//    }
//
////    implicit def string2Exp(n: String): Expression = StrExpression(n)
////    implicit def int2term(n: Int): Expression = IntExpression(n)
//
//    machine.enableSolutionTrace()
//
//    machine.introduceMolecule(machine.OneBonbon(1))
//
//    assert(machine.containsRelation(machine.OneBonbon, 1))
//    machine.executeRules()
//
//    machine.introduceMolecule(machine.OneBonbon(2))
//    machine.executeRules()
//    assert(machine.containsRelation(machine.TwoBonbons, 1))
//    assert(machine.containsRelation(machine.OneBonbon, 0))
//    assert(machine.getSolution.size == 1)
//  }
//
////  @Test
////  def HydrolyseTestRelations() {
////
////
////    val machine = new Cham with IntegerCham with DebugCham {
////      //TestCham with DefaultCham{
////      val n1, n2, n3, n4, n5 = Var
////      val R1_COO_R2 = LocalRelation("R1_COO_R2")
////      val R2_OH = LocalRelation("R2_OH")
////      val R1_COOH = LocalRelation("R1_COOH")
////      val H2O = LocalRelation("H2O")
////      val COO = LocalRelation("COO")
////      val R1 = LocalRelation("R1")
////      val R2 = LocalRelation("R2")
////      val C = LocalRelation("C")
////      val O = LocalRelation("O")
////      val H = LocalRelation("H")
////
//////      implicit def int2term(n: Int): Expression = IntExpression(n)
////
////      val EXPLODE_R1_COO_R2 = LocalRelation("EXPLODE_R1_COO_R2")
////      val EXPLODE_H2O = LocalRelation("EXPLODE_H2O")
////
////      rules(
////
////        // INITIATION
////        (R1_COO_R2(n1) &: H2O(n2)) --> {Min(n1, n2) > 0} ?: (R1_COO_R2(n1 - Min(n1, n2)) & H2O(n2 - Min(n1, n2)) & EXPLODE_R1_COO_R2(Min(n1, n2)) & EXPLODE_H2O(Min(n1, n2))),
////
////        // DIVISION
////        (EXPLODE_R1_COO_R2(n1) &: R1(n2) &: R2(n3) &: COO(n4)) --> (R1(n2 + n1) & R2(n3 + n1) & COO(n4 + n1)),
////        (EXPLODE_H2O(n1) &: H(n2) &: O(n3)) --> (H(n2 + n1*2) & O(n3 + n1)),
////
////        // SUB DIVISION
////        (COO(n1) &: C(n2) &: O(n3)) --> {n1 > 0} ?: (COO(0) & C(n2 + n1) & O(n3 + n1*2)),
////
////        // RECOMPOSITION
////        (R2(n1) &: O(n2) &: H(n3) &: R2_OH(n4)) --> {Min(n1, n2, n3) > 0} ?: (R2(n1 - Min(n1, n2, n3)) & O(n2 - Min(n1, n2, n3)) & H(n3 - Min(n1, n2, n3)) & R2_OH(n4 + Min(n1, n2, n3))),
////        (R1(n1) &: C(n2) &: O(n3) &: H(n4) &: R1_COOH(n5)) --> {Min(n1, n2, n3 / 2, n4) > 0} ?: (R1_COOH(n5 + Min(n1, n2, n3 / 2, n4)) & R1(n1 - Min(n1, n2, n3 / 2, n4)) & C(n2 - Min(n1, n2, n3 / 2, n4)) & O(n3 -  Min(n1, n2, n3 / 2, n4)) & H(n4 -Min(n1, n2, n3 / 2, n4)))
////      )
////    }
////
//////    import machine.int2term
////    machine.enableSolutionTrace()
////    machine.enableStreamingTrace()
////
////    machine.introduceMolecule(machine.R1_COO_R2(1))
////    machine.introduceMolecule(machine.H2O(2))
////    machine.introduceMolecule(machine.R2_OH(0))
////    machine.introduceMolecule(machine.R1_COOH(0))
////    machine.introduceMolecule(machine.COO(0))
////    machine.introduceMolecule(machine.R1(0))
////    machine.introduceMolecule(machine.R2(0))
////    machine.introduceMolecule(machine.C(0))
////    machine.introduceMolecule(machine.O(0))
////    machine.introduceMolecule(machine.H(0))
////    machine.executeRules()
////
////    assert(machine.containsMolecule(machine.R1_COO_R2(0)))
////    assert(machine.containsMolecule(machine.H2O(1)))
////    assert(machine.containsMolecule(machine.R1_COOH(1)))
////  }
////
////  @Test
////  def MapReduceTest() {
////
////    val machine = new Cham with IntegerCham with DebugCham {
////      //TestCham with DefaultCham{
////      val z, w, n, i, j, kp, km = Var
////      val InsertWord = LocalRelation("InsertWord")
////      val Count = LocalRelation("Count")
////
////      val PrintInt = NativeRelation("PrintInt") {
////        case (Atom(_, a :: _), _) => println(a)
////        case _ =>
////      }
////
////      rules(
////        InsertWord(w) --> Count(w, 1), // MAP
////        (Count(w, i) & Count(w, j)) --> Count(w, i + j) // REDUCE
////      )
////    }
////
////    //import machine.num2fun
//////    implicit def stringToValue(s: String) = StrExpression(s)
////
////    machine.enableSolutionTrace()
////
////    machine.introduceMolecule(machine.InsertWord("aa"))
////    machine.introduceMolecule(machine.InsertWord("aa"))
////    machine.introduceMolecule(machine.InsertWord("bb"))
////    machine.introduceMolecule(machine.InsertWord("bb"))
////    machine.introduceMolecule(machine.InsertWord("cc"))
////    machine.introduceMolecule(machine.InsertWord("aa"))
////    machine.introduceMolecule(machine.InsertWord("bb"))
////    machine.introduceMolecule(machine.InsertWord("dd"))
////    machine.executeRules()
////
////    assert(machine.getSolution.containsMolecule(machine.Count("aa", 3)))
////    assert(machine.getSolution.containsMolecule(machine.Count("cc", 1)))
////  }
////
////  @Test
////  def DjikstraTest() {
////
////    val machine = new Cham with IntegerCham with DebugCham {
////      //TestCham with DefaultCham{
////      val x, y, z, w, n, i, j = Var
////
////      val AreConnected = LocalRelation("LeadsTo")
////      val AreInRelation = LocalRelation("AreInRelation")
////      val IsItEquivalent = LocalRelation("IsItEquivalent")
////
////      val PUSH = LocalRelation("PUSH")
////      val POP = LocalRelation("POP")
////
////      val PrintInt = NativeRelation("PrintInt") {
////        case (Atom(_, a :: _), _) => /*println(x)*/
////        case _ =>
////      }
////
////      rules(
////        IsItEquivalent(x, y) --> Nu(i)(AreInRelation(x, y, i)),
////        (AreInRelation(x, w, n) &: AreConnected(x, y)) --> NotEq(x, w) ?: Nu(i)((AreInRelation(x, w, n) & PUSH(i, n, x) & AreInRelation(y, w, i))),
////        AreInRelation(x, y, n) --> Eq(x, y) ?: (PrintInt(x) & POP(n)),
////        (POP(j) &: PUSH(i, n, x)) --> Eq(i, j) ?: (PrintInt(x) & POP(n))
////      )
////    }
////
//////    import machine.num2fun
////
//////    implicit def str2fun(s: String): Term = new StrExpression(s)
////    machine.enableSolutionTrace()
////
////    machine.introduceMolecule(machine.AreConnected("A", "B"))
////    machine.introduceMolecule(machine.AreConnected("B", "C"))
////    machine.introduceMolecule(machine.AreConnected("C", "D"))
////    machine.introduceMolecule(machine.AreConnected("D", "E"))
////    machine.introduceMolecule(machine.AreConnected("B", "D"))
////    machine.introduceMolecule(machine.AreConnected("D", "F"))
////
////    machine.introduceMolecule(machine.IsItEquivalent("A", "E"))
////    machine.executeRules()
////
////    assert(machine.containsMolecule(machine.PrintInt("A")))
////    assert(machine.containsMolecule(machine.PrintInt("B")))
////    assert(machine.containsMolecule(machine.PrintInt("D")))
////    assert(machine.containsMolecule(machine.PrintInt("E")))
////
//////    assert(machine.containsMolecule(machine.PrintInt("C"), 0)) // C is not in the shortest, but it can appear in the solution
////  }
//}
