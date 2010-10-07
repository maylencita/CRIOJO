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
//  this:ValueVM =>
  
  type EqClass = HashSet[Variable]
  var eqClasses = List[EqClass]()

  val EQ = Rel("Eq")
  val EqAsk = Rel("Eq?")
  val Eclass = NativeRelation("EqClass"){
    a => if (a.vars.size == 2){
      addEquivalence(a(0),a(1))
      a.inactivate
    }
  }

  private val x,y,z = Var
  
  //Eq rules
  val r1 = EQ(x,y) ==> Eclass(y,x)

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

  def askEquivalence(x:Variable, y:Variable):Boolean = {
    x == y || eqClasses.exists(ec => ec.contains(x) && ec.contains(y)) // || TODO Ask for value

  }

  override def query(conj:List[Atom], subs:List[Substitution]):List[Atom] = {
    println("Overrided query in Eq with " + conj + " and " + subs)
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

//  object Eclass extends Rel("EqClass"){
//    override def notifyObservers(a:Atom)= a match{
//      case Atom("EqClass", x::y::_) =>
//        log("[Relation("+name+").notifyObservers] notified by " + a)
//        addEquivalence(x,y)
//      case _ => super.notifyObservers(a)
//    }
//  }
}



