package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 8, 2010
 * Time: 11:17:48 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._
import Creole._

trait ValueVM extends CHAM{ //[Solution]{
  this:Eq =>
  
  val Nul = Rel("Null")
  val Suc = Rel("Suc")
  val IntRel = Rel("$Int")
  private val n,x,y = Var

  (IntRel(n,x) &: IntRel(n,y)) ==> EQ(x,y)

  def getValue(x:Variable):ValueVariable[_]={
      solution.find{
        case ta:TypedAtom[_] => ta.variable == x
        case _ => false
      } match{
        case Some(c:IntAtom) => new Value[Int](c.number)
        case Some(c:StringAtom) => new Value[String](c.str.replace("\"",""))
        case Some(c:NullAtom) => Null
        case _ => NoValue
      }
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

  def getPrintVariable(v:Variable):Variable = getValue(v) match{
    case value:Value[_] => value
    case NoValue => v
  }

  def prettyPrint = {
      solution.elems.filterNot(_.relName.startsWith("$")).map(a => new Atom(a.relName, a.vars.map(getPrintVariable(_)))).mkString("<",",",">")
  }

}