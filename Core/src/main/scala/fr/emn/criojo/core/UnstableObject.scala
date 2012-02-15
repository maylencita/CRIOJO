package fr.emn.criojo.core

import fr.emn.criojo.lang.{CrjAtom, Molecule}
import collection.mutable.{Buffer, ListBuffer}

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/9/12
 * Time: 9:38 AM
 * To change this template use File | Settings | File Templates.
 */

trait UnstableObject {

}

class UnstableRelation(name:String, f:(Tuple2[Product,Product],Buffer[(Variable,Term)]) => Unit) extends LocalRelation(name, true) with UnstableObject {

  def apply(t:Tuple2[Product,Product]):Molecule = {

    var param1:List[Variable] = List()
    var param2:List[Variable] = List()

    // we identify two cases:
    // case x: Variable means => (x) -> [...]
    // case x: Product  means => (x,y,z,t) -> [...]
    t._1 match {
      case x:Variable => {param1 = x::param1}
      case x:Product => {param1 = x.productIterator.toList.asInstanceOf[List[Variable]]}
      case _ => throw new Exception("Bad arguments: when creating\""+name+"\"(a -> b), a should be a Term*")
    }

    // we identify two cases:
    // case x: Variable means => [...] -> (x)
    // case x: Product  means => [...] -> (x,y,z,t).
    t._2 match {
      case x:Variable => {param2 = x::param2}
      case x:Product => {param2 = x.productIterator.toList.asInstanceOf[List[Variable]]}
      case _ => throw new Exception("Bad arguments: when creating\""+name+"\"(a -> b), b should be a Term*")
    }

    val unstableAtom:UnstableAtom = new UnstableAtom(name, f, t, param1:::param2)
    unstableAtom.setParams(param1,param2)
    unstableAtom
  }


}

class UnstableAtom(relName:String, val f:(Tuple2[Product,Product],Buffer[(Variable,Term)]) => Unit, exp:Tuple2[Product,Product], terms: List[Term]) extends CrjAtom(relName, terms) with UnstableObject {

  //override def toString():String = "UnstableAtom"

  var inputParams:List[Term] = List()
  var outputParams:List[Variable] = List()
  var missingVariable:Int = -1;

  def setParams(param1:List[Variable], param2:List[Variable]) = {
    inputParams = param1
    outputParams = param2
  }

  def eval(subs:Buffer[(Variable,Term)]):Int = {

    val missingVariableBeforeThisIteration = missingVariable // save the previous value of missing value, so that we will be able to detect any changes

    missingVariable = 0

    var inputArgsExp:List[(Variable, ValueTerm[Int])] = Nil

    // take the (Variable | Term)* input expression and construct a ( (Variable | Term), ValueTerm )* expression
    try {
      inputParams.foreach(p => {
        p match  {
          case x:Variable => {
            val res = subs.find(s => s._1.name == x.name)
            res match {
              case Some((y,v:ValueTerm[Int])) => {
                inputArgsExp = inputArgsExp ::: ((x, new ValueTerm[Int](v.value))::Nil)
                true
              }
              case _ => {missingVariable = missingVariable+1; false}
            }
          }
          case t:ValueTerm[Int] => {
            inputArgsExp = inputArgsExp ::: List[(Variable, ValueTerm[Int])]((new Variable("_"), t))
          }
          case _ => {}
        }
      })
    } catch {
      case ex:Exception => {
        // TODO: check if execeptions are useful for this part?
        ex.printStackTrace()
      }
    }

    var outputArgsExp:List[(Variable, ValueTerm[Int])] = Nil

    // take the (Variable | Term)* output expression and construct a ( (Variable | Term), ValueTerm )* expression
    outputParams.foreach( p => {
      p match {
        case x:Variable => {
          outputArgsExp = outputArgsExp ::: ((x, new ValueTerm[Int](0))::Nil)
        }
        case t => {
        }
      }
    })

    var exp2:Tuple2[Product, Product] = (inputArgsExp,outputArgsExp)

    // finalValue is the value returned by the function:
    //    0 : no more variable available since the last "eval" call
    //    1 : some variable are now available but "eval" need some other values
    //    2 : anything is available

    var finalValue:Int = -1
    // everything is OK => 2 (all variables are available)
    if (missingVariable == 0) {
      // evaluating the function
      f(exp2,subs)

      // insertion of the new values for the intermediary variable
      exp2._2.asInstanceOf[List[(Variable, ValueTerm[Int])]].foreach( s => {
        subs.append(s)
      })

      finalValue = 2
    }
    else
    // no progress => 0 (no more values)
    if (missingVariable == missingVariableBeforeThisIteration)
      finalValue = 0
    else
    // a progress => 1 (some previously unavailable values are available)
    if (missingVariable < missingVariableBeforeThisIteration || missingVariableBeforeThisIteration == -1)
      finalValue = 1

    finalValue
  }
}

