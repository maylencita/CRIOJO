package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/11/11
 * Time: 10:05
 */
import collection.mutable.HashMap
import fr.emn.criojo.core.Criojo._
import collection.immutable.HashSet

/**
 * The StatefulEngine trait
 * @define THIS A
 * @define PARENT no other class
 * @define RESULT 3
 */
trait StatefulEngine extends Engine{

  def createRule(h: Head, b: Body, g: Guard, scope: List[Variable]) = new StatefulRule(h,b,g,scope)

  def initSolution = new HashSolution()

  def executeRules(){
    while (rules.exists(r => r.execute())){}
  }

  def introduceAtom(atom: Atom){
    solution.addAtom(atom)
    notifyRelationObservers(atom)
  }

  def removeAtom(atom: Atom){
    solution.remove(atom)
    atom.setActive(false)
    notifyRelationObservers(atom)
  }

  def getSolution = this.solution //EmptySolution

  class StatefulRule(val head:List[Atom], val body:List[Atom], val guard:Guard, scope:List[Variable])
    extends Rule with StateMachine{

    var ready:Boolean = false

    init(head.toArray)

    def onFinalState(){
//println("\t\tReady!")
      this.ready = true
//      this.execute()
    }

    def receiveUpdate(atom: Atom){
//println("Updated: " +this+ " with " +atom )
      if (atom.isActive)
        addExecution(atom)
      else
        removeExecution(atom)
//println("\tNew solution: " +solution)
    }

    def execute(subs: List[Substitution]) = {
      var executed = false
      if(ready){
        val finalState = states(size - 1)
        // TODO: check if this is a good patch (2/2) a)
        var consumedAtoms:List[Atom] = List()

        finalState.executions.foreach{pe =>
//        finalState.executions.forall{pe => {
          //scala.util.Random.shuffle(finalState.executions).forall{pe =>
            if(guard.eval(getSolution,pe.subs) && pe.atoms.intersect(consumedAtoms).isEmpty){
  //println("Executed: "+ this)
              ready = false
              applyReaction(pe)
              /*
              applyReaction(pe).foreach(a => {
                consumedAtoms = a :: consumedAtoms
              })
              */
              pe.atoms.foreach(a => {
                consumedAtoms = a :: consumedAtoms
              })
              executed = true
            }
//            !executed }
          // TODO: check if this is a good patch (2/2) b)
          //!executed
        }
      }
      executed
    }

    def applyReaction(finalExecution:PartialExecution) {
      val scopeSubs = scope.map{v => val i=Indexator.getIndex; (v,v+("@"+i))}
      val newAtoms = this.body.map(_.applySubstitutions(finalExecution.subs.union(scopeSubs)))
//println("\tNew atoms: " + newAtoms.mkString(","))
      val removeAtoms:Array[Atom] = finalExecution.atoms

      removeAtoms.foreach{a => removeAtom(a)}
      newAtoms.foreach(a => introduceAtom(a))

      //executeRules()
    }

    def notifyCham(atom: Atom){}

    override def toString = {
      var str = super.toString + ": \n" +
        "\tPartial Executions: \n"
      for(s <- states){
        if (!s.executions.isEmpty)
          str += "\t"+ s +":"+ s.executions.mkString(";") + "\n"
      }
      str
    }
  }
}

class HashSolution extends Solution{
  var elements = new HashSet[Atom]()

  def createBackUp() = null

  def reverse() = null

  def elems = elements.toList

  def addAtom(atom: Atom){
    elements += atom
  }

  def addMolecule(molecule: List[Atom]){
    elements ++= molecule
  }

  def remove(atom: Atom){
    elements = elements.filterNot(a=> atom == a)
  }

  override def contains(atom:Atom) = elements.exists{a =>
    (a.relName == atom.relName) &&
      a.arity == atom.arity &&
      a.terms.zip(atom.terms).forall{t =>
        t._1 == t._2
      }
  }

  def clear(){}

  def cleanup(){}

  def update(newsol: Solution){}

  def inactivate(a: Atom){}

  def activate(a: Atom){}

  def notifyCHAM(newAtom: Atom){}
}

