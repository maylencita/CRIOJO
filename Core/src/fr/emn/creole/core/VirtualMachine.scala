package fr.emn.creole.core

import Creole.Substitution
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
      if (eval(r.head) && r.active){
        applyReaction(r)
        r.inactivate
      }
    }

    println("finished with solution: " + solution.atoms.mkString("<",",",">"))
  }

  def addRule(rule:Rule){
    rules :+= rule
  }

  def eval(molecule:List[Atom]):Boolean = {
    molecule.head.isTrue || !solution.findMatches(molecule).isEmpty
  }

  def applyReaction(r:Rule){
    var newSolution = solution.clone
    var matches = solution.findMatches(r.head)
    var subs = getSubstitutions(r.head, matches)

    newSolution.addMolecule(r.body.map{
      a => a.applySubstitutions(subs)
    })

    newSolution.cleanup

    if (newSolution != solution)      //TODO an actual evaluation of equivalence between solutions
      solution = newSolution
    
  }

  def getSubstitutions(ruleAtoms:List[Atom], solAtoms:List[Atom]):List[Substitution] = {
    def getSubsRec(ratoms:List[Atom], satoms:List[Atom], acum:List[Substitution]): List[Substitution] = ratoms match{
      case List() => acum
      case ra :: rest =>
        satoms match{
          case List() => List()
          case sa :: rest2 => getSubsRec(rest, rest2, acum.union(ra.vars.zip(sa.vars)))
        }
    }

    getSubsRec(ruleAtoms, solAtoms, List())
  }
}