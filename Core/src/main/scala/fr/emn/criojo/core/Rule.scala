package fr.emn.criojo.core

import Creole.Substitution
import fr.emn.criojo.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:58 PM
 * To change this template use File | Settings | File Templates.
 */


abstract class Rule{ //(val head:List[Atom], val body:List[Atom], val guard:Guard) extends RelationObserver{

  var linear = false;
  var active = true;
  var scope:List[Variable] = List()

//  var solution:Solution = _
//  var relations:List[Relation] = List() 
  def head:List[Atom]
  def body:List[Atom]
  def guard:Guard

  def inactivate{
    active = false;
  }

  def setLinear{
    linear = true
  }

  def setScope(newScope: List[Variable]){
    this.scope = newScope//.map(x => x+Indexator.getIndex)
  }

  def isAxiom:Boolean = head.isEmpty

  def toList:List[Rule] = List(this)
  
  def execute:Boolean =  execute(List())

  def execute (subs:List[Substitution]):Boolean

  def notifyRelationObservers(atom:Atom)

  protected def applyReaction(solution:Solution, subs:List[Substitution]):Boolean = {
    var newSolution = solution.clone
    Logger.log("[Rule.applyReaction] Substitutions: " + subs)

    var newAtoms = this.body.map{
      a => val newA = a.applySubstitutions(subs)
      newA
    }
    newAtoms = if(newAtoms.contains(False)) List() else newAtoms

    Logger.log("[Rule.applyReaction] newAtoms=" + newAtoms)

    newSolution.cleanup
    newSolution.addMolecule(newAtoms.filter(a=>a.relation.isInstanceOf[LocalRelation]))
    Logger.log("[Rule.applyReaction] solution = " + solution)
    Logger.log("[Rule.applyReaction] newSolution (after cleanup) = " + newSolution)

    if (newSolution != solution){
      solution.update(newSolution)

      Logger.debug(this.getClass, "applyReaction", this.toString + " applied! solution=" + solution)
      head.foreach(h => h.setActive(true))
//      println(this.toString + " applied! solution=" + solution)
      newAtoms.foreach(a => notifyRelationObservers(a))
      true
    }else{
      solution.revert
      false
    }
  }

  def getSubstitutions(solAtoms:List[Atom]):List[Substitution] = {
    def getSubsRec(ratoms:List[Atom], satoms:List[Atom], acum:List[Substitution]): List[Substitution] = ratoms match{
      case List() => acum
      case ra :: rest =>
        satoms match{
          case List() => acum
          case sa :: rest2 => getSubsRec(rest, rest2, acum.union(ra.vars.zip(sa.vars)))
        }
    }

    getSubsRec(head.filter(_.isActive), solAtoms, scope.map{v => val i=Indexator.getIndex; (v,v+"@"+i)})
  }

  //TODO Rewrite
  def getSubstitutions(hatom:HeadAtom, solAtoms:List[Atom]):List[Substitution] = {
    def getSubsRec(ratoms:List[Atom], satoms:List[Atom], acum:List[Substitution]): List[Substitution] = ratoms match{
      case List() => acum
      case ra :: rest =>
        satoms match{
          case List() => acum
          case sa :: rest2 => getSubsRec(rest, rest2, acum.union(ra.vars.zip(sa.vars)))
        }
    }

    getSubsRec(List(hatom), solAtoms, scope.map{v => val i=Indexator.getIndex; (v,v+"@"+i)})
  }

  class HeadAtom(relName:String, vars: List[Variable]) extends Atom(relName, vars) with RelationObserver{
    def this(a:Atom) = this(a.relName, a.vars)

    override def receiveUpdate(atom:Atom){
      this.active = false
      atom.setActive(false)
      
      Logger.log("============================================================================")
      Logger.log(this.getClass,"receiveUpdate","this: " + this)
      Logger.levelDown
      if(!execute(getSubstitutions(this, List(atom)))){
        atom.setActive(true)
        this.active = true
      }
      Logger.levelUp
//    Logger.log(this.getClass, "receiveUpdate", "Final solution: " + solution)
      Logger.log("============================================================================")
    }

  }

  override def equals(that:Any) = {
    def eq2(a1:Atom, a2:Atom):Boolean = a1.relName == a2.relName && a1.vars == a2.vars
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