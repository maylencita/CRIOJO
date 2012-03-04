package fr.emn.criojo.ext

import fr.emn.criojo.core.Term
import fr.emn.criojo.core.CriojoGuard
import fr.emn.criojo.core.Guard
import fr.emn.criojo.core.Variable
import fr.emn.criojo.core.ValueTerm
import fr.emn.criojo.core.Solution
import fr.emn.criojo.core.Criojo
import fr.emn.criojo.core.Criojo.Valuation

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/15/12
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */

class EqualsGuard(term1:Term, term2:Term) extends CriojoGuard(List()){

  def eval(sol: Solution, vals: Valuation) = {

    var value1 : Term = null
    var value2 : Term = null

    term1 match {
      case x:ValueTerm[_] => {
        value1 = term1
      }
      case _ =>
    }

    term2 match {
      case x:ValueTerm[_] => {
        value2 = term2
      }
      case _ =>
    }

    vals.forall( s => {
      s match{
        case (x:Variable,v:Term) => {

          if(value1 == null && term1 == x) {
            value1 = v
          }

          if(value2 == null && term2 == x) {
            value2 = v
          }
        }
      }

      !(value1!=null && value2!=null)
    })

    value1!=null && value2!=null & value1.equals(value2)
  }
  override def toString = atoms.mkString("Equals(", ",", ")")
}

class NotEqualsGuard(term1:Term, term2:Term) extends CriojoGuard(List()){
  def eval(sol: Solution, vals: Valuation) = {

    var value1 : Term = null
    var value2 : Term = null

    term1 match {
      case x:ValueTerm[_] => {
        value1 = term1
      }
      case _ =>
    }

    term2 match {
      case x:ValueTerm[_] => {
        value2 = term2
      }
      case _ =>
    }

    vals.forall( s => {
      s match{
        case (x:Variable,v:Term) => {

          if(value1 == null && term1 == x) {
            value1 = v
          }

          if(value2 == null && term2 == x) {
            value2 = v
          }
        }
      }

      !(value1!=null && value2!=null)
    })

    !(value1!=null && value2!=null & value1.equals(value2))
  }
  override def toString = atoms.mkString("Equals(", ",", ")")
}

class AllAreTrueGuard(guards:List[Guard]) extends CriojoGuard(List()){
  def eval(sol: Solution, vals: Valuation) = {

    val allGuards = guards
    allGuards.forall(g => {

      g.eval(sol, vals)
    })
  }
  override def toString = atoms.mkString("ForEachTrue(", ",", ")")
}

class CustomGuards {

}
