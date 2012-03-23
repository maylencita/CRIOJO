package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 28, 2010
 * Time: 2:18:31 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.lang._
import Criojo._

import collection.mutable.HashSet
import EqClass._

trait EqCHAM extends Cham{

  var eqClasses = new EqClassList
  var disjClasses = new EqClassList

  val genEqClasses = new TypedEqClasses[Any](eqClasses,disjClasses)
  /**********************************************************************
  * CHM definition:
  */
  //--Public:
  //val EQ = NativeRelation("Eq"){ (a,s) => if (a.terms.size == 2) addEquivalence(a.vars(0),a.vars(1),s) }
  //val EQ_ask = NativeRelation("Eq_ask")(askEq)
  //val NotEQ = NativeRelation("$NotEq"){(a,s) => addNotEqual(a.vars(0),a.vars(1),s)}

  //--Private:
  private val s,x,y,z = Var(); private val K = VarR("K")
  /***********************************************************************/

  def Eq(t1:Term, t2:Term):CriojoGuard = {
    val g = new CriojoGuard(List()){

      def eval(vals: Valuation) = {
        existsEqual(t1.applyValuation(vals), t2.applyValuation(vals))
      }

    }
    g
  }
  def NotEq(t1:Term, t2:Term):CriojoGuard = {
    val g = new CriojoGuard(List()){

      def eval(vals: Valuation) = {
        existsNotEqual(t1.applyValuation(vals), t2.applyValuation(vals))
      }

    }
    g
  }

  //TODO applySubstitution() should be in class Term
  //TODO findSubstitution() should be in a class called Substitutions
  //TODO transform type Criojo.Substitutions into a separate class
  def findSubstitution(variable:Variable, vals: Valuation):Term =
    vals.find(s => s._1.name == variable.name) match{
      case Some((v,t)) => t
      case _ =>
        variable match{
          case rv:RelVariable if (rv.relation != null) => rv
          case rv:RelVariable if (rv.relation == null) => Undef
          case _ => Undef
        }
  }

  def applySubstitution(term:Term,vals: Valuation):Term = term match{
    case v:Variable => findSubstitution(v,vals)
    case v:ValueTerm[_] => v
    case f@Function(n, plst) => f(plst.map(p => p.applyValuation(vals)))
    case _ => term
  }

  private val f = new RelVariable("false")
  private val t = new RelVariable("true")

  protected def disjointed(e1:EqClass, e2:EqClass):Boolean ={
    if (e1 == e2)
      false
    else
      disjClasses.contains(e1) && disjClasses.contains(e2)
  }

  /*
  // Native
  private def addEquivalence(v1:Variable, v2:Variable, s:Solution) =
  (eqClasses.find(v1),eqClasses.find(v2)) match{
    case (Some(e1),Some(e2)) if e1 != e2 =>
      //Found two equivalent EqClasses
      if(disjointed(e1,e2))
        throw new InvalidStateError("Illegal operation: " + v1 + " != " + v2)
      else
        eqClasses.merge(e1,e2)
    case (Some(e1),Some(e2)) => //They are already here, do nothing
    case (Some(e), None) =>	e.add(v2)
    case (None, Some(e)) =>	e.add(v1)
    case _ =>
      //Add a new EqClass
      eqClasses add HashSet(v1,v2)
  }
  */

  /*
  private def addNotEqual(v1:Variable, v2:Variable, s:Solution){
    if(v1 == v2)
      throw new InvalidStateError("Illegal operation: " + v1 + " == " + v2)
    (eqClasses.find(v1),eqClasses.find(v2)) match{
      case (Some(e1), Some(e2)) if e1 == e2 =>
        throw new InvalidStateError("Illegal operation: " + v1 + " == " + v2)
      case (Some(e1), Some(e2)) if e1 != e2 =>
        disjClasses.add(e1)
        disjClasses.add(e2)
      case (op1,op2) =>
        val e1 = op1 match {case Some(e) => e; case None => HashSet(v1)}
        val e2 = op2 match {case Some(e) => e; case None => HashSet(v2)}
        eqClasses add e1
        eqClasses add e2
    }
  }
  */

  /*
  private def askEq(a:Atom, sol:Solution){
    a match{
      case Atom(_, i::(v1:Variable)::(v2:Variable)::kpls::kmin::_) =>
        if(v1 == v2 || eqClasses.exists(ec => ec.contains(v1) && ec.contains(v2))){
          //They are equal
          sol.addAtom(Atom(kpls.toString, i,v1,v2))
        }
        else
          (disjClasses.find(v1), disjClasses.find(v2)) match{
            case (Some(e1),Some(e2)) if e1 != e2 =>
              //They are different
              sol.addAtom(Atom(kmin.toString, i,v1,v2))
            case _ => //No enough information to answer
          }
      case _ => //Nothing
    }
  }
  */

  //TODO maybe move this somewhere else, like a utility class or something
  private def equalsOp(op:Option[Any],value:Any):Boolean = op match{
    case Some(s) => s == value
    case _ => false
  }

  protected def existsEqual(t1:Term, t2:Term):Boolean = (t1,t2) match{
        case (ValueTerm(v1),ValueTerm(v2)) => v1 == v2
        case (v1:Variable,v2:Variable) => (v1.equals(v2) || eqClasses.exists(ec => ec.contains(v1) && ec.contains(v2)))
        case (x:Variable,ValueTerm(v)) => equalsOp(genEqClasses.getValue(x),v)
        case (ValueTerm(v),x:Variable) => equalsOp(genEqClasses.getValue(x),v)
        case (IdTerm(v1),IdTerm(v2)) => v1==v2
        case (v1,v2) => v1==v2
  }

  protected def existsNotEqual(t1:Term, t2:Term):Boolean = (t1,t2) match{
        case (ValueTerm(v1),ValueTerm(v2)) => v1 != v2
        case (v1:Variable,v2:Variable) =>
          (disjClasses.find(v1), disjClasses.find(v2)) match{
          case (Some(e1),Some(e2)) if e1 != e2 =>
            //They are different
            true
          case _ => false //No enough information to answer
          }
        case (x:Variable,ValueTerm(v)) => !equalsOp(genEqClasses.getValue(x),v)
        case (ValueTerm(v),x:Variable) => !equalsOp(genEqClasses.getValue(x),v)
        case (IdTerm(v1),IdTerm(v2)) => v1!=v2
        case (v1,v2) => v1!=v2
  }

  def askEquivalence(x:Variable, y:Variable, s:Solution):Boolean = {
    x.equals(y) ||
    eqClasses.exists(ec => ec.contains(x) && ec.contains(y))
  }

  def getSubstitutions(hatom:Atom, solAtoms:List[Atom]):List[Substitution] = {
    def getSubsRec(ratoms:List[Atom], satoms:List[Atom], acum:List[Substitution]): List[Substitution] = ratoms match{
      case List() => acum
      case ra :: rest =>
        satoms match{
          case List() => acum
          case sa :: rest2 => getSubsRec(rest, rest2, acum.union(ra.vars.zip(sa.terms).filterNot(_._1 == Undef).toSeq)) // todo: quick'n dirty fix ".toSeq"
        }
    }
    getSubsRec(List(hatom), solAtoms, List())
  }

//  def Eq(v1:Variable, v2:Variable):Guard = {
//    guard(T(v1,v2), T(x,y) --> Abs(EQ_ask()) ?: Nu(s)(EQ_ask(s,x,y,t,f)))
//  }
//
//  def NotEq(v1:Variable, v2:Variable):Guard = {
//    guard(T(v1,v2), T(x,y) --> Abs(EQ_ask()) ?: Nu(s)(EQ_ask(s,x,y,f,t)))
//  }

}

