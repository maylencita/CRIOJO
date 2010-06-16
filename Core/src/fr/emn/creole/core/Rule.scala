package fr.emn.creole.core

import Creole.Substitution
import fr.emn.creole.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:58 PM
 * To change this template use File | Settings | File Templates.
 */

class Rule(val head:List[Atom], val body:List[Atom], val guards:List[Guard]) extends RelationObserver with Guard{

  def this(head:List[Atom], body:List[Atom]){
    this(head, body, List())
  }

  var linear = false;
  var active = true;
  var scope:List[Variable] = List()

  var solution:Solution = _
  var relations:List[Relation] = List() 

  def inactivate{
    active = false;
  }

  def setLinear{
    linear = true
  }

  def setScope(newScope: List[Variable]){
    this.scope = newScope
  }

  def execute{
    Logger.log("============================================================================")
    Logger.log("[Rule.activate] " + this)
    var matches = List[Atom]()
    if (!{matches = evalHead; matches}.isEmpty) &&
      (guards.isEmpty || guards.forall(_.eval(this.solution))) 

      applyReaction(matches)
  }

  def evalHead:List[Atom] = head match{
    case True ::_ => this.head
    case _ => solution.findMatches(this.head)
  }

  def applyReaction(matches:List[Atom]):Boolean = {
    Logger.log("============================================================================")
    Logger.log("[Rule.applyReaction] Found Matches: " + matches + " for rule " + this)
    var newSolution = solution.clone
    var subs = getSubstitutions(this.head, this.scope, matches)
    Logger.log("[Rule.applyReaction] Substitutions: " + subs)

    val newAtoms = this.body.map{
      a => val newA = a.applySubstitutions(subs)
      if (newA != True && newA != False)
        newA.relation = relations.find(r => r.name == newA.relName) match{
          case Some(r) => r
          case _ => null //TODO manage invalid relation error
        }
      newA
    }

    newSolution.cleanup
    newSolution.addMolecule(newAtoms)

    Logger.log("[Rule.applyReaction] solution=" + solution)
    Logger.log("[Rule.applyReaction] newSolution=" + newSolution)

    if (newSolution != solution){
      solution.update(newSolution)
      Logger.log("[Rule.applyReaction] applied!")
      newAtoms.foreach(_.relation.notifyObservers)
      true
    }else{
      solution.revert
      false
    }
  }

  def getSubstitutions(ruleAtoms:List[Atom], scope:List[Variable], solAtoms:List[Atom]):List[Substitution] = {
    def getSubsRec(ratoms:List[Atom], satoms:List[Atom], acum:List[Substitution]): List[Substitution] = ratoms match{
      case List() => acum
      case ra :: rest =>
        satoms match{
          case List() => acum
          case sa :: rest2 => getSubsRec(rest, rest2, acum.union(ra.vars.zip(sa.vars)))
        }
    }

    getSubsRec(ruleAtoms, solAtoms, scope.map(v => (v,v)))
  }

  override def eval(solution:Solution):Boolean = {
    this.solution = solution.clone
    execute
    this.solution.isTrue
  }

  override def receiveUpdate{
    if (active)
      execute
  }

  override def toString = head.mkString("", "&" ,"") + "=>" + (if(!scope.isEmpty) "v"+scope.mkString("(",",",")")+"." else "") + body.mkString("", "&" ,"")

}