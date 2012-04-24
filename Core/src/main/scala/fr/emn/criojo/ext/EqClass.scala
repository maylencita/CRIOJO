package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 28, 2010
 * Time: 2:18:31 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._

import collection.mutable.{HashSet,HashMap}
import datatype.Variable

object EqClass{
  type EqClass = HashSet[Variable]
  def apply(vlst:Variable*):EqClass = {
    val varSet = new HashSet[Variable]
    varSet ++ vlst
  }
}

import EqClass._

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
            val newEC = EqClass(v)
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

