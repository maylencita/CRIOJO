package fr.emn.creole.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 8, 2010
 * Time: 3:03:16 PM
 * To change this template use File | Settings | File Templates.
 */

class VirtualMachine {
  var rules:List[Rule] = List()
  var solution:Solution = new Solution()
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
      //molecule.forall(this.solution.contains(_))
  }

  def applyReaction(r:Rule){
    var newSolution:List[Atom] = solution.filter(x=>true)
    r.body.foreach{
       x => newSolution :+= x
    }

    newSolution = newSolution -- r.head

    if (newSolution != solution)
      solution = newSolution
  }
}