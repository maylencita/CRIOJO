package fr.emn.creole.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 8, 2010
 * Time: 11:17:48 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._
import fr.emn.creole.util.Logger._

class StandardCHAM extends CHAM[StandardSolution]{
  solution = new StandardSolution()
  
  addRelation(new LocalRelation("Null", true))
  addRelation(new LocalRelation("Suc", true))

  def getValue(x:Variable): ValueVariable[_] = {
    solution.asInstanceOf[StandardSolution].getValue(x)
  }

  def processValues(atom:Atom):List[Atom]={
    var valAtoms = List[Atom]()
    var newVarList = List[Variable]()

    atom.vars.foreach{
      case Value(value) =>
        val newVar = new Variable("$y"+Indexator.getIndex)
        newVarList :+= newVar
        valAtoms ::= (value match{
          case n:Int => new IntAtom(n, newVar)
          case s:String => new StringAtom(s, newVar)
          case _ => throw new Exception("Unsuported value type: " + value.asInstanceOf[AnyRef].getClass)
        })
      case v:Variable =>
        newVarList :+= v
    }

    valAtoms :+ (if (newVarList.isEmpty) atom else new Atom(atom.relName, newVarList))
  }

}