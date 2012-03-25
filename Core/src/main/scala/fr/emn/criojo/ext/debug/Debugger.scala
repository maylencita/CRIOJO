package fr.emn.criojo.ext.debug

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 25/03/12
 * Time: 10:26
 */

import collection.mutable.ListBuffer
import fr.emn.criojo.core.statemachine.PartialExecution
import fr.emn.criojo.core._
import fr.emn.criojo.lang.Molecule
import fr.emn.criojo.core.StatefulEngine


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 3/22/12
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */


trait DebugCham extends StatefulEngine {

  var DEBUG_TRACE:ListBuffer[String] = ListBuffer()
  var DEBUG_DIRECT_MODE = false
  var DEBUG_SOLUTION_MODE = false

  override def introduceAtom(atom: Atom){

    if(DEBUG_SOLUTION_MODE)
      solution.addAtom(atom)
    super.introduceAtom(atom)
  }

  override def removeAtom(atom: Atom){

    if(DEBUG_SOLUTION_MODE)
      solution.remove(atom)
    super.removeAtom(atom)
  }

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
    return (solution.elems.count(atom => atom.matches(a)) == n)
  }

  def containsAtom(a:LocalRelation, n:Int):Boolean = {
    return (solution.elems.count(atom => a.name==atom.relName) == n)
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

    solution.foreach( a => {
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

  override def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]) = {
    new StatefulRule(h,b,g,scope) with DebugStatefulRule
  }

  trait DebugStatefulRule
    extends Rule {

    override def applyReaction(pe:PartialExecution) {

      var valuatedHead = this.head.map({a => a.applyValuation(pe.valuation)})
      var valuatedBody = this.body.map({a => a.applyValuation(pe.valuation)})

      var valuatedHeadString = valuatedHead.toString()
      var valuatedBodyString = valuatedBody.toString()
      valuatedHeadString = valuatedHeadString.substring(5,valuatedHeadString.length()-1)
      valuatedBodyString = valuatedBodyString.substring(5,valuatedBodyString.length()-1)

      var result:String = valuatedHeadString+" --> "+valuatedBodyString

      DEBUG_TRACE += result

      if(DEBUG_DIRECT_MODE)
        println(result)

      super.applyReaction(pe)
    }

  }
}
