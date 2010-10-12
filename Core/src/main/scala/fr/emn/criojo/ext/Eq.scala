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

import collection.mutable.HashSet

trait Eq extends CHAM{

  type EqClass = HashSet[Variable]
  var eqClasses = List[EqClass]()

  /**********************************************************************
  * VM definition:
  */
  //--Public:
  val EQ = Rel("Eq")
  val EQ_ask = Rel("Eq_ask")
  //--Private:
  private val Eclass = NativeRelation("EqClass"){ a => if (a.vars.size == 2) addEquivalence(a(0),a(1)) }
  private val EqAsk = NativeRelation("$EqAsk"){ a => askEq(a) }
  private val s,x,y,z = Var; private val K = RelVariable("K")
  
  EQ(x,y) ==> Eclass(y,x)
  EQ_ask(s,x,y,K) ==> EqAsk(s,x,y,K)
  /***********************************************************************/

  // Native
  private def addEquivalence(x:Variable, y:Variable) =
    (eqClasses.find(_.contains(x)),eqClasses.find(_.contains(y))) match{
    case (Some(e1),Some(e2)) if e1 != e2 =>
      //Found two equivalent EqClasses
      merge(e1,e2)
//      eqClasses = eqClasses.filterNot(e => e == e1 || e == e2) :+ e1.union(e2)
    case (Some(e1),Some(e2)) => //They are already here, do nothing
    case (Some(e), None) =>	e.add(y)
    case (None, Some(e)) =>	e.add(x)
    case _ =>
      //Add a new EqClass
      eqClasses :+= HashSet(x,y)
  }

  protected def merge(ec1:EqClass, ec2:EqClass):EqClass = {
    val merged = ec1.union(ec2)
    eqClasses = eqClasses.filterNot(e => e == ec1 || e == ec2) :+ merged
    merged
  }

  private def askEq(a:Atom) = a match{
    case Atom("$EqAsk", i::v1::v2::k::_) =>
      if(v1 == v2 || eqClasses.exists(ec => ec.contains(v1) && ec.contains(v2)))
        introduceAtom(Atom(k.toString, i,v1,v2))
    case _ => //Nothing
  }

  def askEquivalence(x:Variable, y:Variable):Boolean = {
    x == y ||
//    eqClasses.exists(p => p._2.contains(x) && p._2.contains(y))
    eqClasses.exists(ec => ec.contains(x) && ec.contains(y)) // || TODO Ask for value

  }

  

/*
  override def query(conj:List[Atom], subs:List[Substitution]):List[Atom] = {
    if (conj.exists(_.relName == "Eq")){
      val conj2 = conj.filterNot(_.relName == "Eq")
      val eqAtoms = conj.filter(_.relName == "Eq")

      super.query(conj2,subs) match{
        case List() => List()
        case lst =>
          val s = subs union conj2.flatMap(a=>getSubstitutions(a,lst))
          if (eqAtoms.forall{a =>
            val a2=a.applySubstitutions(s);
            askEquivalence(a2(0),a2(1))})
              lst
          else
            List()
      }
    }else{
      super.query(conj,subs)
    }
  }
*/
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

}



