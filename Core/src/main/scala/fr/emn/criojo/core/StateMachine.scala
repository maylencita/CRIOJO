package fr.emn.criojo.core

import fr.emn.criojo.core.Criojo._
import collection.mutable.HashMap

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 29/11/11
 * Time: 14:17
 */
trait StateMachine {

  protected var size = 0 //math.pow(2,pattern.length).intValue
  protected var states:Array[State] = null //initStates
  protected var transitions:HashMap[Atom,Array[Transition]] = null //initTransitions

  var pattern:Array[Atom] = null

  def onFinalState()

  def init(pattern:Array[Atom]){
    this.pattern = pattern
    states = initStates
    transitions = initTransitions
  }

  def addExecution(atom:Atom){
    if(pattern != null){
      for(a <- pattern){
        if (a.relName == atom.relName && a.arity == atom.arity && a.matches(atom)){
          val subs = getSubstitutions(a.terms,atom.terms)
          transitions(a).foreach{transition=>
            if(transition.ini.stateZero){
              val pExec = new PartialExecution(atom,Array[Atom](),subs)
              transition.fin.addPExecution(pExec)
//println("\tT."+a+ "{" +transition.ini +"-->"+ transition.fin + "} with " +atom+ " : ")
//println("\t\tNew p.exec: " +pExec + " Subs: " + pExec.subs)

              if(transition.fin == size-1 ){
                onFinalState()
              }
            }else
              transition.ini.executions.foreach{pe =>{
                var truc = pe
                var bidule = pe.atoms
                // TODO: check if this is a good patch (1/2)
                //if(a.applySubstitutions(pe.subs).matches(atom)){
                if(!(pe.atoms.contains(atom)) && a.applySubstitutions(pe.subs).matches(atom)){
                  val pExec = new PartialExecution(atom,pe.atoms,pe.subs.union(subs))
                  transition.fin.addPExecution(pExec)
//println("\tT."+a+ "{" +transition.ini +"-->"+ transition.fin + "} with " +atom+ " : ")
//println("\t\tNew p.exec: " +pExec + " Subs: " + pExec.subs)
                  if(transition.fin == size-1 ){
                    onFinalState()
                  }
                }
              }
              }
          }
        }
      }
    }
  }


  def removeExecution(atom:Atom){
    if(pattern != null){
      for(a <- pattern){
        if (a.relName == atom.relName && a.arity == atom.arity){
          transitions(a).foreach{t=>
            t.fin.removePExecution(atom)
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

class State(val id:Int){
  var executions:List[PartialExecution] = List()

  def addPExecution(pe:PartialExecution){
    executions :+= pe
  }
  def removePExecution(atom:Atom){
    executions = executions.filterNot(ex => ex.atoms.contains(atom))
  }
  def stateZero = (id == 0)

  override def equals(that:Any) = that match{
    case num:Int => num == id
    case s:State => s.id == this.id
    case _ => false
  }
  override def toString = "("+id.toBinaryString+")"
}

class PartialExecution(newAtom:Atom, prevAtoms:Array[Atom], val subs:List[Substitution]){
  def atoms:Array[Atom] = newAtom +: prevAtoms
  override def toString = atoms.mkString("{",",","}")
}
