package fr.emn.criojo.core.statemachine

import fr.emn.criojo.core.engine.ExecutionEngine
import fr.emn.criojo.core.model.{Atom,BottomValuation}

import collection.mutable.{HashMap, MultiMap, Set}

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 29/11/11
* Time: 14:17
*/
class StateMachine(pattern: Array[Atom]) extends ExecutionEngine{

  val size = math.pow(2,pattern.length).intValue
  val states:Array[State] = initStates
  protected var transitions:HashMap[Int,Array[Transition]] = initTransitions

  def addAtom(atom:Atom){
    /*
    A temporal map to hold new executions, to differentiate
    the previous general-state from the current general-state
     */
    val newExecutions:MultiMap[State,PartialExecution] =
      new HashMap[State,Set[PartialExecution]] with MultiMap[State,PartialExecution]

    if(pattern != null) {
      for(i <- 0 until pattern.length){
        val a = pattern(i)
        if (a.relation.name == atom.relation.name && a.arity == atom.arity && a.correspondsTo(atom)){
          val vals = a.getValuation(atom) // fixme: le premier atome contient une liste de patterns et le second une list d'expression
          transitions(a.hashCode).foreach{
            transition=> {
              if(transition.ini.stateZero) {
                val pExec = new PartialExecution(i,atom,vals)
                pExec.valuation match {
                  case BottomValuation =>
                  case _ => newExecutions.addBinding(transition.fin,pExec)
                }
              }
              else {
                transition.ini.executions.foreach {
                  pe => {
                    val pExec = pe.newExecution(i,atom,vals)

                    pExec.valuation match {
                      case BottomValuation =>
                      case _ => newExecutions.addBinding(transition.fin,pExec)
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

  def removeAtom(atom:Atom){
    if(pattern != null){
      for(a <- pattern){
        if (a.relation.name == atom.relation.name && a.arity == atom.arity){
          transitions(a.hashCode).foreach{t=>
            t.fin.removeExecutions(atom)
          }
        }
      }
    }
  }

  private def initStates = {
//    if (size == 0)
//      size = math.pow(2,pattern.length).intValue
    val slst = new Array[State](size)
    for(i <- 0 to size-1){
      slst.update(i, new State(i))
    }
    slst
  }

  private def initTransitions = {
    val tmap = HashMap[Int,Array[Transition]]()

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
      tmap.put(hd.hashCode, tarr)
      pos += 1
    }
    tmap
  }

  class Transition(val ini:State,val fin:State){
    override def toString=ini +"-->"+ fin
  }
}


