package fr.emn.criojo.core.statemachine

import fr.emn.criojo.core.Criojo._
import fr.emn.criojo.core.Atom
import collection.mutable.{Queue, HashMap, MultiMap, Set}

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 29/11/11
* Time: 14:17
*/
trait StateMachine {

  protected var size = 0
  protected var states:Array[State] = null
  protected var transitions:HashMap[Atom,Array[Transition]] = null

  var pattern:Array[Atom] = null

  def init(pattern:Array[Atom]){
    this.pattern = pattern
    states = initStates
    transitions = initTransitions
  }

  def addExecution(atom:Atom){
    /*
    A temporal map to hold new executions, to differentiate
    the previous general-state from the current general-state
     */
    val newExecutions:MultiMap[State,PartialExecution] =
      new HashMap[State,Set[PartialExecution]] with MultiMap[State,PartialExecution]

    if(pattern != null){
      for(i <- 0 until pattern.length){
        val a = pattern(i)
        if (a.relName == atom.relName && a.arity == atom.arity && a.matches(atom)){
          val vals = getValuation(a.terms,atom.terms)
          transitions(a).foreach{
            transition=> {
              if(transition.ini.stateZero) {
                val pExec = new PartialExecution(i,atom,vals)
                newExecutions.addBinding(transition.fin,pExec)
              }
              else {
                transition.ini.executions.foreach {
                  pe => {
                    if(a.applyValuation(pe.valuation).matches(atom)){
                      val pExec = pe.newExecution(i,atom,vals)
                      newExecutions.addBinding(transition.fin,pExec)
                    }
                  }
                }
              }
            }
          }
        }
      }
      updateStates(newExecutions)
    }
  }

  private def updateStates(newExecutions:MultiMap[State,PartialExecution]){
    newExecutions.foreach{
      case (state,exSet) => states(state.id).addExecutions(exSet)
    }
  }

  def removeExecution(atom:Atom){
    if(pattern != null){
      for(a <- pattern){
        if (a.relName == atom.relName && a.arity == atom.arity){
          transitions(a).foreach{t=>
            t.fin.removeExecutions(atom)
          }
        }
      }
    }
  }

  private def initStates = {
    if (size == 0)
      size = math.pow(2,pattern.length).intValue
    val slst = new Array[State](size)
    for(i <- 0 until size){
      slst.update(i, new State(i))
    }
    slst
  }

  private def initTransitions = {
    val tmap = HashMap[Atom,Array[Transition]]()

    var pos = 0
    for(hd <- pattern){
      val step = math.pow(2,pattern.length - (pos+1)).intValue
      val tarr = new Array[Transition]((size / 2).intValue)
      var i = 0; var p = 0
      while(i < size){
        if ((i & step) == 0 ){
          val iniState = i
          val finState = iniState + step
          if(finState < size){
            tarr.update(p, new Transition(states(iniState),states(finState)))
            p += 1
          }else
            i = size
        }
        i += 1
      }
      tmap.put(hd, tarr)
      pos += 1
    }

    tmap
  }

}

class Transition(val ini:State,val fin:State){
  override def toString=ini +"-->"+ fin
}

