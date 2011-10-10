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

  /**********************************************************************
  * CHM definition:
  */
  //--Public:
  val EQ = NativeRelation("Eq"){ (a,s) => if (a.vars.size == 2) addEquivalence(a(0),a(1),s) }
  val EQ_ask = NativeRelation("Eq_ask")(askEq)
  val NotEQ = NativeRelation("$NotEq"){(a,s) => addNotEqual(a(0),a(1),s)}
  //--Private:
  private val s,x,y,z = Var; private val K = VarR("K")
  /***********************************************************************/

  private val f = new RelVariable("false")
  private val t = new RelVariable("true")

  protected def disjointed(e1:EqClass, e2:EqClass):Boolean ={
    if (e1 == e2)
      false
    else
      disjClasses.contains(e1) && disjClasses.contains(e2)
  }

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

  private def askEq(a:Atom, sol:Solution){
    a match{
      case Atom(_, i::v1::v2::kpls::kmin::_) =>
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

  def askEquivalence(x:Variable, y:Variable, s:Solution):Boolean = {
    x == y ||
    eqClasses.exists(ec => ec.contains(x) && ec.contains(y))
  }

  def getSubstitutions(hatom:Atom, solAtoms:List[Atom]):List[Substitution] = {
    def getSubsRec(ratoms:List[Atom], satoms:List[Atom], acum:List[Substitution]): List[Substitution] = ratoms match{
      case List() => acum
      case ra :: rest =>
        satoms match{
          case List() => acum
          case sa :: rest2 => getSubsRec(rest, rest2, acum.union(ra.vars.zip(sa.vars)))
        }
    }
    getSubsRec(List(hatom), solAtoms, List())
  }

  def Eq(v1:Variable, v2:Variable):Guard = {
    guard(T(v1,v2), T(x,y) --> Abs(EQ_ask()) ?: Nu(s)(EQ_ask(s,x,y,t,f)))
  }

  def NotEq(v1:Variable, v2:Variable):Guard = {
    guard(T(v1,v2), T(x,y) --> Abs(EQ_ask()) ?: Nu(s)(EQ_ask(s,x,y,f,t)))
  }

}

