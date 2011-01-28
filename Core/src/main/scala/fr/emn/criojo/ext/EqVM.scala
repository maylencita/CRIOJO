package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 28, 2010
 * Time: 2:18:31 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import Creole._

import collection.mutable.{HashSet,HashMap}

object EqVM{
  type EqClass = HashSet[Variable]
}
import EqVM._

class TypedEqClasses[T](eqClasses:EqClassList,noEqClasses:EqClassList) extends HashMap[T, EqClass]{

  def add(k:T, v:Variable){
    get(k) match{
      case Some(iec) =>
        eqClasses.find(v) match{
          case Some(ec) if(iec != ec) =>
            if(noEqClasses.contains(ec) && noEqClasses.contains(iec))
              throw new InvalidStateError("Illegal operation: " + iec + " != " + ec)
            else
              update (k, eqClasses.merge(iec,ec))
          case _ => iec.add(v)
        }
      case _ =>
        eqClasses.find(v) match{
          case Some(ec) => put(k, ec)
          case _ =>
            val newEC = HashSet(v)
            eqClasses.add(newEC)
            put(k, newEC)
        }
    }
    //Update noEqClasses
    for (ec <- this.values){
      if(!noEqClasses.contains(ec))
        noEqClasses.add(ec)
    }
  }

  def getValue(x:Variable):Option[T] = {
    find(_._2 contains (x)) match{
      case Some((k, ec)) => Some (k)
      case _ => None
    }
  }
}

class EqClassList{
  var eqClasses = List[EqClass]()

  def add(ec:EqClass){ eqClasses :+= ec}
  def find(x:Variable):Option[EqClass] = eqClasses.find(_.contains(x))
  def exists(f:EqClass => Boolean) = eqClasses.exists(f)
  def remove(ec:EqClass) { eqClasses = eqClasses.filterNot(e => e == ec) }
  def contains(ec:EqClass) = eqClasses.contains(ec)

  def merge(ec1:EqClass, ec2:EqClass):EqClass = {
    val merged = ec1.union(ec2)
    remove(ec1)
    remove(ec2)
    add(merged)
    merged
  }

  override def toString = eqClasses.map(_.mkString("{",",","}")).mkString("[",",","]") 
}

trait EqVM extends CHAM{

  var eqClasses = new EqClassList
  var disjClasses = new EqClassList

  /**********************************************************************
  * VM definition:
  */
  //--Public:
  val EQ = NativeRelation("Eq"){ a => if (a.vars.size == 2) addEquivalence(a(0),a(1)) }
  val EQ_ask = NativeRelation("Eq_ask"){ a => askEq(a) }
  val NotEQ = NativeRelation("$NotEq"){a => addNotEqual(a(0),a(1))}
  //--Private:
  private val s,x,y,z = Var; private val K = RelVariable("K")
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
  private def addEquivalence(v1:Variable, v2:Variable) =
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

  private def addNotEqual(v1:Variable, v2:Variable){
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

  private def askEq(a:Atom) = a match{
    case Atom(_, i::v1::v2::kpls::kmin::_) =>
      if(v1 == v2 || eqClasses.exists(ec => ec.contains(v1) && ec.contains(v2))){
        //They are equal
        introduceAtom(Atom(kpls.toString, i,v1,v2))
      }
      else
        (disjClasses.find(v1), disjClasses.find(v2)) match{
          case (Some(e1),Some(e2)) if e1 != e2 =>
            //They are different
            introduceAtom(Atom(kmin.toString, i,v1,v2))
          case _ => //No enough information to answer
        }
    case _ => //Nothing
  }


  def askEquivalence(x:Variable, y:Variable):Boolean = {
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
    val g = new EqGuard(this,T(v1,v2), T(x,y) ==> Abs(EQ_ask()) ? Nu(s)(EQ_ask(s,x,y,t,f)))
    g
  }

  def NotEq(v1:Variable, v2:Variable):Guard = {
    val g = new EqGuard(this,T(v1,v2), T(x,y) ==> Abs(EQ_ask()) ? Nu(s)(EQ_ask(s,x,y,f,t)))
    g
  }
}

class EqGuard(owner:EqVM, sttr:Atom, ruleDefs:(RuleFactory => Rule)*) extends Guard(sttr) with EqVM{
    this.eqClasses = owner.eqClasses
    this.disjClasses = owner.disjClasses
    private val s,x,y,z = Var

    initRules(ruleDefs.toList)
  }


