package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/11/11
 * Time: 10:05
 */
import fr.emn.criojo.core.Criojo._
import collection.immutable.HashSet
import statemachine.{StateMachine, PartialExecution}
import collection.mutable.ListBuffer

/**
 * The StatefulEngine trait
 * @define THIS A
 * @define PARENT no other class
 * @define RESULT 3
 */
trait StatefulEngine extends Engine{

  var DEBUG_MODE:Boolean = false
  var DEBUG_DIRECT_MODE:Boolean = false
  var DEBUG_TRACE:ListBuffer[String] = ListBuffer()

  def printTrace() {
    DEBUG_TRACE.foreach(t => println(t))
  }

  def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]) = new StatefulRule(h,b,g,scope)

  def initSolution = new HashSolution()

  def executeRules(){
    while (rules.exists(r => r.execute())){}
  }

  def introduceAtom(atom: Atom){

    if(DEBUG_MODE)
      solution.addAtom(atom)

    notifyRelationObservers(atom)
  }

  def removeAtom(atom: Atom){
    if(DEBUG_MODE)
      solution.remove(atom)
    atom.setActive(false)
    notifyRelationObservers(atom)
  }

  def getSolution:Solution = this.solution //EmptySolution

  class StatefulRule(val head:List[Atom], val body:List[Atom], val guard:Guard, scope:Set[Variable])
    extends Rule with StateMachine{

    init(head.toArray)

    def receiveUpdate(atom: Atom){
      if (atom.isActive)
        addExecution(atom)
      else
        removeExecution(atom)
    }

    def execute(vals: Valuation) = {
      var executed = false
      val finalState = states(size - 1)
      if(finalState.hasExecutions){
        finalState.removeExecution(pe => guard.eval(pe.valuation)) match{
          case Some(pe:PartialExecution) => {
            applyReaction(pe)
            
            if(DEBUG_MODE) {
              var valuatedHead = this.head.map({a => a.applyValuation(pe.valuation)})
              var valuatedBody = this.body.map({a => a.applyValuation(pe.valuation)})

              var valuatedHeadString = valuatedHead.toString()
              var valuatedBodyString = valuatedBody.toString()
              valuatedHeadString = valuatedHeadString.substring(5,valuatedHeadString.length()-1)
              valuatedBodyString = valuatedBodyString.substring(5,valuatedBodyString.length()-1)

              DEBUG_TRACE += valuatedHeadString+" --> "+valuatedBodyString
            }


            if(DEBUG_DIRECT_MODE) {
              var valuatedHead = this.head.map({a => a.applyValuation(pe.valuation)})
              var valuatedBody = this.body.map({a => a.applyValuation(pe.valuation)})

              var valuatedHeadString = valuatedHead.toString()
              var valuatedBodyString = valuatedBody.toString()
              valuatedHeadString = valuatedHeadString.substring(5,valuatedHeadString.length()-1)
              valuatedBodyString = valuatedBodyString.substring(5,valuatedBodyString.length()-1)

              println(valuatedHeadString+" --> "+valuatedBodyString)
            }

            executed = true
          }
          case _ => //Skip
        }
      }
      executed
    }

    def applyReaction(finalExecution:PartialExecution) {
      val finalValuation = scope.foldLeft(finalExecution.valuation){(vals,sv) =>
        val i = Indexator.getIndex
        vals union Valuation(sv -> new IdTerm(sv.name+"@"+i))
      }

      if(!finalValuation.isEmpty) {

        val newAtoms = this.body.map(_.applyValuation(finalValuation))

        val removeAtoms = for (i <- 0 until head.size; if !head(i).persistent) yield{
          finalExecution.atom(i)
        }

        removeAtoms.foreach{a => removeAtom(a)}
        newAtoms.foreach(a => introduceAtom(a))
      }
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

  def displaySolution() {
    var firstPrint:Boolean = true
    print("<")
    
    var cpt:Int = 0
    
    elements.foreach( a => {
      if(a.relName.charAt(0)!='$') {
        if(!firstPrint)
          print(",")
        cpt = (cpt+1)%10
        if(cpt!=9)
          print(a)
        else
          println(a)
        firstPrint = false
      }
    })
    println(">")
  }

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

