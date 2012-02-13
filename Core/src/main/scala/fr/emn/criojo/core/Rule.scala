package fr.emn.criojo.core

import Criojo._
import fr.emn.criojo.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:58 PM
 * To change this template use File | Settings | File Templates.
 */


abstract class Rule extends RelationObserver{
  var linear = false;
  var active = true;
  var scope:List[Variable] = List()

  def head:List[Atom]
  def body:List[Atom]
  def guard:Guard

  def inactivate(){
    active = false;
  }

  def setLinear(){
    linear = true
  }

  def setScope(newScope: List[Variable]){
    this.scope = newScope
  }

  def isAxiom:Boolean = head.isEmpty

  def toList:List[Rule] = List(this)
  
  def execute():Boolean =  execute(List())

  def execute (subs:List[Substitution]):Boolean

  def notifyCham(atom:Atom)

  protected def applyReaction(solution:Solution, subs:List[Substitution]):Boolean = {
    val newSolution = solution.clone
    Logger.log("[Rule.applyReaction] Substitutions: " + subs)

    var newAtoms = this.body.map{
      a => val newA = a.applySubstitutions(subs)
      newA
    }
    newAtoms = if(newAtoms.contains(False)) List() else newAtoms

//    Logger.log("[Rule.applyReaction] newAtoms=" + newAtoms)

    newSolution.cleanup()
    newSolution.addMolecule(newAtoms.filter(a=>a.relation.isInstanceOf[LocalRelation]))
//    Logger.log("[Rule.applyReaction] solution = " + solution)
//    Logger.log("[Rule.applyReaction] newSolution (after cleanup) = " + newSolution)

    if (newSolution != solution){
      solution.update(newSolution)

      Logger.debug(this.getClass, "applyReaction", this.toString + " applied!")
      Logger.debug(this.getClass, "applyReaction", "New solution=" + solution)
      head.foreach(h => h.setActive(true))
      newAtoms.foreach(a => notifyCham(a))
      true
    }else{
      solution.revert()
      false
    }
  }

  def getHeadSubstitutions(solAtoms:List[Atom]):List[Substitution] = {
    def getSubsRec(ratoms:List[Atom], satoms:List[Atom], acum:List[Substitution]): List[Substitution] = ratoms match{
      case List() => acum
      case ra :: rest =>
        satoms match{
          case List() => acum
          case sa :: rest2 => getSubsRec(rest, rest2, acum.union(ra.vars.zip(sa.terms)))
        }
    }

    getSubsRec(head.filter(_.isActive), solAtoms, scope.map{v => val i=Indexator.getIndex; (v,v+("@"+i))})
  }

//  def getHeadSubstitutions(hatom:Atom, solatom:Atom):List[Substitution] = {
//    def getSubsRec(ratoms:List[Atom], satoms:List[Atom], acum:List[Substitution]): List[Substitution] = ratoms match{
//      case List() => acum
//      case ra :: rest =>
//        satoms match{
//          case List() => acum
//          case sa :: rest2 =>
//            getSubsRec(rest, rest2, acum.union(ra.vars.zip(sa.terms).flatMap(p=>getSubstitution(p._1,p._2))))
//        }
//    }
//
//    getSubsRec(List(hatom), solAtoms, scope.map{v => (v,v+"@"+Indexator.getIndex)})

//    scope.map{v => (v,v+"@"+Indexator.getIndex)}.union(getHeadSubstitutions(hatom.terms,solatom.terms))
//  }

  override def equals(that:Any) = {
    def eq2(a1:Atom, a2:Atom):Boolean = a1.relName == a2.relName && a1.terms == a2.terms
    def eq(lst1:List[Atom], lst2:List[Atom]) =  lst1.forall(a1=>lst2.exists(a2 => eq2(a1,a2)))

    that match{
      case r:Rule => eq(this.head,r.head) && eq(this.body,r.body) 
      case _ => false
    }
  }
  override def toString = head.mkString("", "," ,"") + "=>" +
          (if(!scope.isEmpty) "v"+scope.mkString("(",",",")")+"." else "") +
          (if(!guard.empty) "[" + guard + "]?" else "") +
          body.mkString("", "," ,"")

}