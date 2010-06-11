package fr.emn.creole.core

import Creole.Substitution
import fr.emn.creole.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 8, 2010
 * Time: 3:03:16 PM
 * To change this template use File | Settings | File Templates.
 */

class VirtualMachine {
  var rules:List[Rule] = List()
  var solution:Solution = new Solution(List())
  var relations:List[String] = List()

  def start(){
    for (r <- rules){
      var matches = List[Atom]()
      if (r.active && !{matches = eval(r.head); matches}.isEmpty){
        if (applyReaction(r,matches))
          r.inactivate
      }
    }

    println("finished with solution: " + solution)
  }

  def addRule(rule:Rule){
    rules :+= rule
  }

  def eval(molecule:List[Atom]):List[Atom] = {
    if (molecule.head.isTrue)
      List(new Atom("True",List()))
    else
      solution.findMatches(molecule)
  }

  def applyReaction(r:Rule,matches:List[Atom]):Boolean = {
    Logger.log("============================================================================")
    Logger.log("[VirtualMachine.applyReaction] Found Matches: " + matches + " for rule " + r)
    var newSolution = solution.clone
    var subs = getSubstitutions(r.head, r.scope, matches)
    Logger.log("[VirtualMachine.applyReaction] Substitutions: " + subs)

    newSolution.addMolecule(r.body.map{
      a => a.applySubstitutions(subs)
    })

    newSolution.cleanup

    if (newSolution != solution){      //TODO an actual evaluation of equivalence between solutions
      solution = newSolution
      Logger.log("[VirtualMachine.applyReaction] solution=" + solution)
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
}