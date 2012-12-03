package fr.emn.criojo.core.statemachine

import fr.emn.criojo.core.model.Atom
import scala.collection.mutable.Queue
import scala._

/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 29/11/11
* Time: 14:17
*/
class State(val id:Int){
  private val qExecutions:Queue[PartialExecution] = Queue[PartialExecution]()

  def hasExecutions:Boolean = !qExecutions.isEmpty

  def executions = qExecutions

  def hasExecution(p:(PartialExecution)=>Boolean):Boolean = {
    qExecutions.exists(p)
  }
  def addExecutions(exLst:Iterable[PartialExecution]){
    for(ex <- exLst){
      qExecutions.enqueue(ex)
    }
  }

  def addExecution(pe:PartialExecution){
    qExecutions.enqueue(pe)
  }

  //Removes and returns the first execution
  def removeExecution():PartialExecution = qExecutions.dequeue()

  //Removes and returns the first execution which satisfy the predicate `p
  def removeExecution(p:(PartialExecution)=>Boolean):Option[PartialExecution] =
    qExecutions.dequeueFirst(p)

  //Removes all executions associated to atom `atom
  def removeExecutions(atom:Atom){
    // todo: check if dequeueFirst fix the C() problem, instead of dequeueAll
    qExecutions.dequeueAll(ex => ex.containsAtom(atom))
  }

  def stateZero = (id == 0)

  override def equals(that:Any) = that match{
    case num:Int => num == id
    case s:State => s.id == this.id
    case _ => false
  }
  override def toString = "("+id.toBinaryString+")"
}





