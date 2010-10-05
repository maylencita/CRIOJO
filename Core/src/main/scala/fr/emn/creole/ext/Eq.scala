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
import Creole._

import collection.mutable.HashSet

trait Eq extends CHAM{
  type EqClass = HashSet[Variable]

  val EQ = Rel("Eq")
  val EqAsk = Rel("Eq?")
  var eqClasses = List[EqClass]()

  val x_eq = "x"; val y_eq = "y"; val z_eq = "z"
  
  //Some rules
  val r1 = EQ(x_eq,y_eq) ==> Eclass(y_eq,x_eq)

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

//  override def query(conj:List[Atom], subs:List[Substitution]):List[Atom] =

  object Eclass extends Rel("EqClass"){
    override def notifyObservers(a:Atom)= a match{
      case Atom("EqClass", x::y::_) =>
        log("[Relation("+name+").notifyObservers] notified by " + a)
        addEquivalence(x,y)
      case _ => super.notifyObservers(a)
    }
  }
}

