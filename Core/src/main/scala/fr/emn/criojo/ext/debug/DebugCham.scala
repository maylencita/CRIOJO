package fr.emn.criojo.ext.debug

import collection.mutable.ListBuffer
import fr.emn.criojo.core.datatype.{Valuation, Variable}
import fr.emn.criojo.core._
import engine.{PartialStateExecution, PartialStateRule, CriojoEngine}
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.lang.Molecule


trait DebugCham extends CriojoEngine {

  var DEBUG_TRACE:ListBuffer[String] = ListBuffer()
  var DEBUG_DIRECT_MODE = false
  var DEBUG_SOLUTION_MODE = false

  def enableSolutionTrace() {
    DEBUG_SOLUTION_MODE = true
  }

  def disableSolutionTrace() {
    DEBUG_SOLUTION_MODE = false
  }

  def enableStreamingTrace() {
    DEBUG_DIRECT_MODE = true
  }

  def disableStreamingTrace() {
    DEBUG_DIRECT_MODE = false
  }

  def printTrace() {
    DEBUG_TRACE.foreach(t => println(t))
  }

  def containsAtom(a:Atom, n:Int):Boolean = {
    (solution.listOfAtoms.count(atom => atom.correspondsTo(a)) == n)
  }

  def containsRelation(a:LocalRelation, n:Int):Boolean = {
    (solution.listOfAtoms.count(atom => a.name==atom.relation.name) == n)
  }

  def containsMolecule(m:Molecule):Boolean = {
    containsMolecule(m, 1)
  }

  def containsMolecule(m:Molecule, n:Int):Boolean = {
    containsAtom(m.head, n)
  }

  def printSolution() {
    var firstPrint:Boolean = true
    print("<")

    var cpt:Int = 0

    solution.listOfAtoms.foreach( a => {
      if(a.relation.name.charAt(0)!='%') {
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

  override def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]) = {
    new PartialStateRule(h,b,g,scope, this) with DebugStatefulRule
  }

  trait DebugStatefulRule
    extends PartialStateRule {

    override def applyReaction(pes:List[PartialStateExecution], valuation:Valuation, listAtoms:ListBuffer[Atom] = null, oneTime:Boolean = false):Boolean =  {

      var valuatedHead = this.head.map({a => a.applyValuation(valuation)})
      var valuatedBody = this.body.map({a => a.applyValuation(valuation)})

      var valuatedHeadString = valuatedHead.toString()
      var valuatedBodyString = valuatedBody.toString()
      valuatedHeadString = valuatedHeadString.substring(5,valuatedHeadString.length()-1)
      valuatedBodyString = valuatedBodyString.substring(5,valuatedBodyString.length()-1)

      var result:String = valuatedHeadString+" --> "+valuatedBodyString

      DEBUG_TRACE += result


      if(DEBUG_DIRECT_MODE){
        println(result)
        //printSolution()
      }

      super.applyReaction(pes, valuation, listAtoms, oneTime)
    }

  }
}