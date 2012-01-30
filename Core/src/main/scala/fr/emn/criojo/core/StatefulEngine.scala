package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 18/11/11
 * Time: 10:07
 */
import Criojo._
import fr.emn.criojo.util.Logger._

trait StatefulEngine extends Engine
{
  def initSolution = null

  def executeRules(){
    while (rules.exists(r => r.execute)){}
  }

  def introduceAtom(atom: Atom){
    notifyRelationObservers(atom)
    this.executeRules()
  }

  def getSolution:Solution = {
    val sol = new SolutionImpl(null,List[Atom]())
    rules.foreach{
      case r:StatefulRule =>
        r.head.foreach{
          case a:r.StatefulHeadAtom =>
            r.states.foreach{s =>
              if(s(a.pos))
                sol.addAtom(a.applySubstitutions(s.substitutions))
            }
        }
      case _ =>
    }
    sol
  }

  def createRule(h: Head, b: Body, g: Guard, scope: List[Variable]) = new StatefulRule(h,b,g,scope)

  class StatefulRule(h:List[Atom], val body:List[Atom], val guard:Guard, scp:List[Variable]) extends Rule{
    var states = List[State]()
    val headSize = h.size

    val head = {
      var i = 0
      h.map{ a =>
        val newAtom = new StatefulHeadAtom(a,i)
        i += 1
        newAtom
      }
    }

    def receiveUpdate(atom:Atom){
      //Position? //TODO What about the case (A & B & A)?
      val pos = head.find(_.relName == atom.relName) match{
        case Some(a) => a.pos
        case _ => -1
      }

      //Find a matching state or create a new one
      if(atom.isActive){
        states.find(s => !s(pos) && head(pos).applySubstitutions(s.substitutions).matches(atom)) match{
          case Some(s) =>
              s.setOn(pos,atom)
          case _ => val newState = new State(head)
            newState.setOn(pos,atom)
            states :+= newState
        }
      }else{
        states.find(s => s(pos)) match{
          case Some(s) =>
            s.setOff(pos,atom)
          case _ => //Do nothing
        }
      }
      println("receiveUpdate() with " + atom + " --> " + this.toString + " " + states.mkString("["," ","]"))
    }

    override def execute:Boolean = {
      val theState = states.find(s => s.complete) match{
        case Some(s) => s
        case _ => null //new State(0,List[Substitution]())
      }
      val subs = if (theState != null)
        theState.substitutions
      else
        List[Substitution]()

//      if ((this.isAxiom || !subs.isEmpty) &&
      if((this.isAxiom || theState != null) &&
        guard.eval(getSolution,subs)){
        val subs2 = subs.union(getHeadSubstitutions(List[Atom]()))
        applyReaction(subs2)
        true
      }else
        false
    }

    def execute (subs:List[Substitution]):Boolean = false

    //Generate new atoms
    def applyReaction(subs:List[Substitution]):Boolean = {
      var newAtoms = this.body.map(_.applySubstitutions(subs))
      newAtoms = if(newAtoms.contains(False)) List() else newAtoms

      val removeAtoms = this.head.map(_.applySubstitutions(subs))
      println("applyReaction() removeAtoms: " + removeAtoms)
      removeAtoms.foreach{a => a.setActive(false); notifyCham(a)}

      println("applyReaction() newAtoms: " + newAtoms)
      newAtoms.foreach(a => notifyCham(a))
      true
    }

    def notifyCham(atom:Atom){
      notifyRelationObservers(atom)
    }

    class StatefulHeadAtom(relName:String, terms: List[Term], val pos:Int) extends Atom(relName, terms){
      def this(a:Atom, pos:Int) = this(a.relName, a.terms, pos)
    }

  }

  class State(premises:List[Atom]){
  val size = premises.size

//  var flags:Array[Boolean] = new Array[Boolean](size)
  var atoms:Array[Atom] = new Array[Atom](size)

  def apply(pos:Int):Boolean = atoms(pos) != null //flags(pos)

  def substitutions:List[Substitution] = {
    premises.zip(atoms).flatMap{p=>
      if(p._2 == null)
        List[Substitution]()
      else
        getSubstitutions(p._1.terms,p._2.terms)
    }
  }

  def complete:Boolean = {
    var isComplete = true
    var i = 0
    while(i < size && isComplete){
      isComplete = this.apply(i)
      i += 1
    }
    isComplete
  }

  def setOn(position:Int, atom:Atom){
//    flags.update(position,true)
    atoms.update(position,atom)
  }

  def setOff(position:Int, atom:Atom){
//    flags.update(position,false)
    atoms.update(position,null)
  }

  private def getSubstitutions(l1:List[Term], l2:List[Term]):List[Substitution] = {
    l1.zip(l2).flatMap(p=>getSubstitution(p._1,p._2))
  }
  private def getSubstitution(term1:Term,term2:Term):List[Substitution] = term1 match{
    case v:Variable => List((v,term2))
    case f1@Function(n,params) => term2 match{
      case f2:Function if(f2.params.size == f1.params.size) =>
        getSubstitutions(f1.params,f2.params)
      case _ => (Undef,term2) :: Nil
    }
    case _ => (Undef,term2) :: Nil
  }

  override def toString:String =
      new String(
        for(p <- atoms) yield{
        if(p != null)
          '1'
        else
          '0'
     })
}
}


