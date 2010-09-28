package fr.emn.creole.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 28, 2010
 * Time: 2:18:31 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._
import fr.emn.creole.util.Logger._

import collection.mutable.HashSet

trait Eq extends CHAM[StandardSolution]{
  type EqClass = HashSet[Variable]

  val EQ = "Eq"
  var eqClasses = List[EqClass]()

  addRelation(new LocalRelation(EQ, true))

  def addEquivalence(x:Variable, y:Variable) =
    (eqClasses.find(_.contains(x)),eqClasses.find(_.contains(y))) match{
    case (Some(e1),Some(e2)) if e1 != e2 =>
      //Found two equivalent EqClasses			
      eqClasses = eqClasses.filterNot(e => e == e1 || e == e2) :+ e1.union(e2)
    case (Some(e1),Some(e2)) => //They are already here, do nothing
    case (Some(e), None) =>	e.add(y)
    case (None, Some(e)) =>	e.add(x)
    case _ =>
      //Add a new EqClass
      eqClasses :+= HashSet(x,y)
  }
}

