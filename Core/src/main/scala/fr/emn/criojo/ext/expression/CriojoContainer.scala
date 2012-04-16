package fr.emn.criojo.ext.expression

import fr.emn.criojo.ext.expression.operations._
import types.{CriojoBoolean, CriojoString, CriojoInteger}
import fr.emn.criojo.core._


sealed trait Container[+A] extends Expression {
  //def unit[A](a: A): Container[A]
  def bind[B](f: A => Container[B]): Container[B]

  def name:String = "Container"

  @throws(classOf[PatternNotMatchingException])
  def getValuation(t:Term):Valuation = t match {
    case p:A => Valuation()
    case _ => {
      throw new PatternNotMatchingException()
    }
  }

  def applyValuation(valuation:Valuation):Term = this
}

case class IncorrectUseException() extends Exception {

}

trait Expression extends Term {

  def eval():Expression = this

  def eval(valuation:Valuation):Expression = {
    this.applyValuation(valuation) match {
      case e:Expression => e.eval()
      case _ => UndefinedExpression
    }
  }

  def +[B](exp:Expression):Expression = BinaryExpression("+", CoupleOfTerms(this, exp))
  def -[B](exp:Expression):Expression = BinaryExpression("-", CoupleOfTerms(this, exp))
  def *[B](exp:Expression):Expression = BinaryExpression("*", CoupleOfTerms(this, exp))
  def /[B](exp:Expression):Expression = BinaryExpression("/", CoupleOfTerms(this, exp))
  def Equal[B](exp:Expression):Expression = BinaryExpression("==", CoupleOfTerms(this, exp))
  def NotEqual[B](exp:Expression):Expression = BinaryExpression("!=", CoupleOfTerms(this, exp))
  def LessThan[B](exp:Expression):Expression = BinaryExpression("<", CoupleOfTerms(this, exp))
  def LessOrEqual[B](exp:Expression):Expression = BinaryExpression("<=", CoupleOfTerms(this, exp))
  def GreaterThan[B](exp:Expression):Expression = BinaryExpression(">", CoupleOfTerms(this, exp))
  def GreaterOrEqual[B](exp:Expression):Expression = BinaryExpression(">=", CoupleOfTerms(this, exp))
}

case class SomeTerm[+A <% CriojoType](value: A) extends Container[A] with Expression {

  def unit[A<% CriojoType](a: A): Container[A] = SomeTerm(a)
  def bind[B](f: A => Container[B]) = f(value)

  def matches(that:Term) = that match {
    case SomeTerm(x:CriojoType) => value match {
      case ct:CriojoType => (x.matches(ct))
      case _ => false
    }
    case _ => false
  }

  override def toString():String = value.toString
}

case class NoneTermClass extends Container[Nothing] with Expression {
  def unit[A](a: A): Container[A] = NoneTerm
  def bind[B](f: Nothing => Container[B]) = NoneTerm

  def matches(that: Term) = false
}

case object NoneTerm extends NoneTermClass {

}

case object UndefinedExpression extends NoneTermClass {

}

case class CoupleOfTerms(t1:Term,t2:Term) {
  def matches(that:CoupleOfTerms) = t1.matches(that.t1) && t2.matches(that.t2)
  def applyValuation(valuation: Valuation) = CoupleOfTerms(t1.applyValuation(valuation),t2.applyValuation(valuation))
  def getValuation(that:CoupleOfTerms):Valuation = t1.getValuation(that.t1).union(t2.getValuation(that.t2))

  def eval():CoupleOfTerms = (t1,t2) match {
    case (exp1:Expression, exp2:Expression) => CoupleOfTerms(exp1.eval(), exp2.eval())
    case _ => CoupleOfTerms(t1,t2)
  }

  def eval(valuation:Valuation):CoupleOfTerms = (t1.applyValuation(valuation),t2.applyValuation(valuation)) match {
    case (exp1:Expression, exp2:Expression) => CoupleOfTerms(exp1.eval(), exp2.eval())
    case _ => CoupleOfTerms(t1,t2)
  }
}

case class BinaryExpression(op:String, value:CoupleOfTerms) extends Container[CoupleOfTerms] with Expression {

  def bind[D](f: CoupleOfTerms => Container[D]) = f(value)
  
  def matches(that: Term) = that match {
    case that:BinaryExpression => op.equals(that.op) && value.matches(that.value)
    case _ => false
  }

  override def applyValuation(valuation: Valuation):Term = BinaryExpression(op, value.applyValuation(valuation))

  @throws(classOf[PatternNotMatchingException])
  override def getValuation(that:Term):Valuation = that match {
    case thatBinaryExpression:BinaryExpression if(op equals thatBinaryExpression.op) => value.getValuation(thatBinaryExpression.value)
    case _ => throw new PatternNotMatchingException()
  }

  override def eval():Expression = {
    val evaluatedCouple:CoupleOfTerms = value.eval()
    evaluatedCouple match {
      case CoupleOfTerms(cc1:SomeTerm[CriojoType], cc2:SomeTerm[CriojoType]) => BinaryExpressionEvaluator.eval(op, cc1, cc2)
      case _ => NoneTerm
    }

  }
  override def eval(valuation:Valuation):Expression =  {
    val evaluatedCouple:CoupleOfTerms = value.eval(valuation)
    evaluatedCouple match {
      case CoupleOfTerms(cc1:SomeTerm[CriojoType], cc2:SomeTerm[CriojoType]) => BinaryExpressionEvaluator.eval(op, cc1, cc2)
      case _ => NoneTerm
    }
  }


}

object BinaryExpressionEvaluator {
  
  def eval(op:String, c1:SomeTerm[CriojoType], c2:SomeTerm[CriojoType]):Expression = c1.bind( x => c2.bind(y => {
    (op, x, y) match {
      case ("+", o1:CanAddType, o2:CriojoType) => SomeTerm(o1.add(o2))
      case ("-", o1:CanSubType, o2:CriojoType) => SomeTerm(o1.sub(o2))
      case ("*", o1:CanMultiplyType, o2:CriojoType) => SomeTerm(o1.multiply(o2))
      case ("/", o1:CanDivideType, o2:CriojoType) => SomeTerm(o1.divide(o2))
      case ("==", o1:CanBeCompared, o2:CanBeCompared) => SomeTerm(o1.isEqual(o2))
      case ("!=", o1:CanBeCompared, o2:CanBeCompared) => SomeTerm(o1.isDifferent(o2))
      case ("<", o1:CanBeCompared, o2:CanBeCompared) => SomeTerm(o1.lessThan(o2))
      case ("<=", o1:CanBeCompared, o2:CanBeCompared) => SomeTerm(o1.lessOrEquals(o2))
      case (">", o1:CanBeCompared, o2:CanBeCompared) => SomeTerm(o1.greaterThan(o2))
      case (">=", o1:CanBeCompared, o2:CanBeCompared) => SomeTerm(o1.greaterOrEquals(o2))
      case _ => throw IncorrectUseException()
    }
  }))
}



case class ListOfTerms(terms:List[Term]) {
  def matches(that: ListOfTerms) = (terms zip that.terms).forall {case (term1:Term, term2:Term) => term1.matches(term2)}
  def applyValuation(valuation: Valuation) = ListOfTerms(terms map {term:Term => term.applyValuation(valuation)})
  def getValuation(that:ListOfTerms):Valuation = (Valuation() /: ((terms zip that.terms) map { case (term1:Term, term2:Term) => term1.getValuation(term2)}))(_ union  _)

  def eval():ListOfTerms = ListOfTerms(terms.map({t:Term => t match {
    case exp:Expression => exp.eval()
    case _ => t
  }}))

  def eval(valuation:Valuation):ListOfTerms = ListOfTerms(terms.map({t:Term => t match {
    case exp:Expression => exp.eval(valuation)
    case _ => t
  }}))
}

case class NAryExpression(op:String, value:ListOfTerms) extends Container[ListOfTerms] with Expression {

  def bind[D](f: ListOfTerms => Container[D]) = f(value)

  def matches(that: Term) = that match {
    case NAryExpression(thatOperator, thatValue:ListOfTerms) if op.equals(thatOperator) && value.matches(thatValue) => true
    case _ => false
  }

  override def applyValuation(valuation: Valuation):Term = NAryExpression(op, value.applyValuation(valuation))

  @throws(classOf[PatternNotMatchingException])
  override def getValuation(t:Term):Valuation = t match {
    case thatNAryExpression:NAryExpression if(op equals thatNAryExpression.op) => value.getValuation(thatNAryExpression.value)
    case _ => throw new PatternNotMatchingException()
  }

  override def eval():Expression =  NAryExpressionEvaluator.eval(op, value.eval())
  override def eval(valuation:Valuation):Expression =  NAryExpressionEvaluator.eval(op, value.eval(valuation))
}

object ExpressionTools {
  def min(t1:Term, t2:Term):Term = (t1,t2) match {
    case (exp1:Expression, exp2:Expression) => (exp1.eval() LessOrEqual exp2.eval()).eval() match {
      case SomeTerm(CriojoBoolean(b)) => if(b) exp1.eval() else exp2.eval()
      case _ => NoneTerm
    }
    case _ => NoneTerm
  }
  def max(t1:Term, t2:Term):Term = (t1,t2) match {
    case (exp1:Expression, exp2:Expression) => (exp1.eval() GreaterOrEqual exp2.eval()).eval() match {
      case SomeTerm(CriojoBoolean(b)) => if(b) exp1.eval() else exp2.eval()
      case _ => NoneTerm
    }
    case _ => NoneTerm
  }
}

object NAryExpressionEvaluator {

  def filterExpression[A](o:A):Expression = o match {
    case exp:Expression => exp
    case _ => NoneTerm
  }

  def eval(op:String, l:ListOfTerms):Expression = op match {
    case "min" => filterExpression(l.terms.reduceRight(ExpressionTools.min(_, _)))
    case "max" => filterExpression(l.terms.reduceRight(ExpressionTools.max(_, _)))
    case _ => NoneTerm
  }
}



case class Min(lTerms:Term*) extends NAryExpression("min", ListOfTerms(lTerms.toList))
case class Max(lTerms:Term*) extends NAryExpression("max", ListOfTerms(lTerms.toList))

case class CriojoContainer(criojoValue:CriojoType) extends SomeTerm(criojoValue) {
  override def toString():String = criojoValue.toString
}

case class IntExpression(anInt:Int) extends SomeTerm(CriojoInteger(anInt)) {
  def getValue:Int = anInt

  override def toString():String = anInt.toString
}

case class StrExpression(aString:String) extends SomeTerm(CriojoString(aString)) {
  def getValue:String = aString
}

case class BooleanExpression(aBoolean:Boolean) extends SomeTerm(CriojoBoolean(aBoolean)) {
  def getValue:Boolean = aBoolean
}

case class VarExpression(aName:String) extends Variable(aName) with Expression {

  override def matches(that:Term) = true
}

object converters {
//  implicit def StringToCriojoType(s:String):CriojoType = CriojoString(s)
//  implicit def IntegerToCriojoType(i:Int):CriojoType = CriojoInteger(i)
//  implicit def BooleanToCriojoType(b:Boolean):CriojoType = CriojoBoolean(b)
//  implicit def CriojoTypeToCriojoContainer[T <% CriojoType](ct:T):CriojoContainer[T] = CriojoContainer(ct)
//  implicit def CriojoTypeToExpression[T <% CriojoType](ct:T):Expression = CriojoContainer(ct)

  implicit def intToExpression(n:Int):Expression = SomeTerm(CriojoInteger(n))
  implicit def stringToExpression(s:String):Expression = SomeTerm(CriojoString(s))
  implicit def booleanToExpression(b:Boolean):Expression = SomeTerm(CriojoBoolean(b))


  implicit def LazyGuard(x: => Expression):CriojoGuard = {
    val g = new CriojoGuard{
      override def eval(vals: Valuation) = {
        val truc = x.eval(vals)
        x.eval(vals).matches(SomeTerm(CriojoBoolean(true)))
      }

      val valuations = new ValuationList()
      val observed = Set[String]()
      def receiveUpdate(atom: Atom){}
    }
    g
  }

  implicit def LazyExpression(x: => Expression):Expression = {
    val g = new Expression {

      def name = x.name
      def matches(that: Term): Boolean = false

      override def eval(): Expression = x.eval()

      def applyValuation(vals:Valuation): Expression = x.applyValuation(vals) match {
        case e:Expression => e
        case _ => NoneTerm
      }

      def getValuation(t:Term):Valuation = x.getValuation(t)
    }
    g
  }
}