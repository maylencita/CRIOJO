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

object Eq{
  type EqClass = HashSet[Variable]
}
import Eq._

class TypedEqClasses[T](eqClasses:EqClassList) extends HashMap[T, EqClass]{

  def add(k:T, v:Variable){
    get(k) match{
      case Some(iec) =>
        eqClasses.find(v) match{
          case Some(ec) if(iec != ec)=> update (k, eqClasses.merge(iec,ec))
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

  def merge(ec1:EqClass, ec2:EqClass):EqClass = {
    val merged = ec1.union(ec2)
    remove(ec1)
    remove(ec2)
    add(merged)
    merged
  }
}

trait Eq extends CHAM{

  var eqClasses = new EqClassList

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
  (eqClasses.find(x),eqClasses.find(y)) match{
    case (Some(e1),Some(e2)) if e1 != e2 =>
      //Found two equivalent EqClasses
      eqClasses.merge(e1,e2)
    case (Some(e1),Some(e2)) => //They are already here, do nothing
    case (Some(e), None) =>	e.add(y)
    case (None, Some(e)) =>	e.add(x)
    case _ =>
      //Add a new EqClass
      eqClasses add HashSet(x,y)
  }

  private def askEq(a:Atom) = a match{
    case Atom("$EqAsk", i::v1::v2::k::_) =>
      if(v1 == v2 || eqClasses.exists(ec => ec.contains(v1) && ec.contains(v2)))
        introduceAtom(Atom(k.toString, i,v1,v2))
    case _ => //Nothing
  }

  def askEquivalence(x:Variable, y:Variable):Boolean = {
    x == y ||
    eqClasses.exists(ec => ec.contains(x) && ec.contains(y)) // || TODO Ask for value
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

}



