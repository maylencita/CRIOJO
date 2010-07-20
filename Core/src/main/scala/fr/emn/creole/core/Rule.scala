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

object Indexator{
  var index=0
  def getIndex = {
     index +=1
     index
  }
}

class Rule(val head:List[Atom], val body:List[Atom], val guard:Guard) extends RelationObserver{

  def this(head:List[Atom], body:List[Atom]){
    this(head, body, new Guard)
  }

  var linear = false;
  var active = true;
  var scope:List[Variable] = List()

  var solution:Solution = _
//  var relations:List[Relation] = List() 

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
  
  def execute:Boolean =  execute(List())

  def execute (subs:List[Substitution]):Boolean = {
//    Logger.log("============================================================================")
    Logger.log("[Rule.execute] " + this)
    Logger.log("[Rule.execute] Substitutions: " + subs)
    Logger.log("[Rule.execute] solution= " + solution)
    var matches = List[Atom]()
    var result = false

    if (this.isAxiom || !{matches= solution.findMatches(head,subs); matches}.isEmpty){
      Logger.log("[Rule.execute] Found Matches: " + matches + " for rule " + this)
      Logger.levelDown
      val subs2 = subs.union(getSubstitutions(matches))
      if (guard.empty || guard.eval(solution, subs2)){
        Logger.levelUp
        applyReaction(subs2)
        result=true
      }else
        Logger.levelUp
    }

    //Activate atoms that were innactivated but not eliminated
    solution.revert
    result
  }

  private def evalHead(subs:List[Substitution]):List[Atom] =
    head match{
    case True ::_ => solution.foreach(a => if (a == True) a.inactivate ); this.head
    case _ => solution.findMatches(this.head,subs)
  }

  private def applyReaction(subs:List[Substitution]):Boolean = {
    var newSolution = solution.clone
//    Logger.log("============================================================================")
    Logger.log("[Rule.applyReaction] Substitutions: " + subs)

    var newAtoms = this.body.map{
      a => val newA = a.applySubstitutions(subs)
      newA
    }
    newAtoms = if(newAtoms.contains(False)) List() else newAtoms
    
    Logger.log("[Rule.applyReaction] newAtoms=" + newAtoms)

    newSolution.cleanup
    newSolution.addMolecule(newAtoms)

    Logger.log("[Rule.applyReaction] solution=" + solution)
//    Logger.log("[Rule.applyReaction] newSolution=" + newSolution)

    if (newSolution != solution){
      solution.update(newSolution)
      Logger.log("[Rule.applyReaction] applied! "+ this)
      Logger.log("[Rule.applyReaction] newSolution=" + solution)
      newAtoms.foreach(a => a.relation.notifyObservers(a))
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

    getSubsRec(head, solAtoms, scope.map{v => val i=Indexator.getIndex; (v,v+"@"+i)})
  }

  override def receiveUpdate(atom:Atom){
    Logger.log("============================================================================")
    Logger.log("[Rule.receiveUpdate] " + this)
    Logger.levelDown
    execute(getSubstitutions(List(atom)))
    Logger.levelUp
    Logger.log("============================================================================")
  }

  override def toString = head.mkString("", "," ,"") + "=>" +
          (if(!scope.isEmpty) "v"+scope.mkString("(",",",")")+"." else "") +
          (if(!guard.empty) "[" + guard + "]?" else "") +
          body.mkString("", "," ,"")

}