//package fr.emn.criojo.ext
//
//import fr.emn.criojo.core._
//
///**
// * Created by IntelliJ IDEA.
// * User: jonathan
// * Date: 2/9/12
// * Time: 10:34 AM
// * To change this template use File | Settings | File Templates.
// */
//
//
//import fr.emn.criojo.lang.ChamGuard
//import fr.emn.criojo.lang.{CrjAtom, Molecule}
//import collection.mutable.{Buffer}
//import fr.emn.criojo.core._
//import fr.emn.criojo.core.statemachine.PartialExecution
//
//
///**
// * The UnstableEngine class, an engine that can handle rules with temporary variables in the left body
// * @define THIS UnstableEngine
// * @define PARENT StatefulEngine
// */
//@deprecated
//trait UnstableEngine extends StatefulEngine {
//
//  def Equal(term1: Term, term2: Term): Guard = new EqualsGuard(term1, term2) with ChamGuard
//  def NotEqual(term1: Term, term2: Term): Guard = new NotEqualsGuard(term1, term2) with ChamGuard
//  //def OneIsTrue(guards:List[Guard]):Guard = new OneIsTrueGuard(guards.toList) with ChamGuard
//
//
//  /** Create a rule that can handle rules with temporary variables in the left body
//   *
//   * @param h head of the rule
//   * @param b body of the rule
//   * @param g guards of the rule
//   * @param scope list of variable
//   * @return the new rule
//   */
//
//  override def createRule(h: Head, b: Body, g: Guard, scope: List[Variable]) = {
//  // this method has been override to include a specific case for UnstableAtom
//
//    var headList: List[Atom] = List()
//    var unstableList: List[UnstableAtom] = List()
//
//    h.foreach(a => {
//      a match {
//        case x: UnstableAtom => unstableList = x :: unstableList
//        case _ => headList = a :: headList
//      }
//    })
//
//    new UnstableRule(headList, b, g, scope, unstableList)
//  }
//
//  /**
//   * The UnstableRule class, a rule that can have temporary variables in her left body
//   * @define THIS UnstableRule
//   * @define PARENT StatefulRule
//   */
//  class UnstableRule(head: List[Atom], body: List[Atom], guard: Guard, scope: List[Variable],
//                     val unstableRelations: List[UnstableAtom]) extends StatefulRule(head, body, guard, scope) {
//
//    /**
//     * Check if every variable is available. If so, apply the rule
//     * @param finalExecution the execution context
//     */
//    override def applyReaction(finalExecution: PartialExecution)  {
//    // since the introduction of UnstableRule, some parts of the algorithm needs specific code
//
//      try {
//        val scopeSubs = scope.map {
//          v => val i = Indexator.getIndex; (v, v + ("@" + i))
//        }
//
//        var subs: Buffer[(Variable, Term)] = finalExecution.subs.union(scopeSubs).toBuffer
//
//
//        // for each unstableRelation, Criojo engine will compute values for the missing variables
//        if (unstableRelations.size != 0) {
//          var sum: Int = 0
//
//          // while the evaluation of the UnstableRelation is not stable
//          var n: Int = 1;
//          while (n != 0 && sum / n != 2) {
//            sum = 0; // initialization of the states
//            n = 0;
//            var size = unstableRelations.size
//
//            for (i <- 0 to (size - 1)) {
//              var r = unstableRelations(i)
//
//              // 0 : same number of missing variables
//              // 1 : number of missing variables is decreasing
//              // 2 : all variables are available
//              var result = r.eval(subs)
//              sum += result
//              n += 1
//            }
//
//            // execution has detected a cycle!
//            if (sum == 0 && n != 0) {
//              throw new Exception("Problem when calculating this rule : \"" + this + "\", Criojo engine assumes that there is a cycle!")
//            }
//          }
//        }
//
//        val newAtoms = this.body.map(_.applySubstitutions(subs.toList))
//
//        val removeAtoms = finalExecution.atoms
//
//        removeAtoms.foreach {
//          a => removeAtom(a)
//        }
//        newAtoms.foreach(a => introduceAtom(a))
//
//        // should comment next line if no call to executeRules when during atoms insertion
//        //executeRules()
//
//      } catch {
//
//        case ex: Exception => println("execution aborted: " + ex.printStackTrace())
//      }
//    }
//  }
//
//
//  /**
//   * The UnstableRelation class
//   * @define THIS UnstableRelation
//   * @define PARENT LocalRelation
//   */
//  class UnstableRelation(name: String, f: (Tuple2[Product, Product]
//                        ,Buffer[(Variable, Term)]) => Unit) extends LocalRelation(name, true) {
//
//
//    /** Apply the parameters to the rules. Here the parameters values are not interpreted, the engine will only store
//     *  the variables references.
//     *
//     * @param t a couple that will contains the transformation expression (eg: (x,y) --> (z))
//     * @return an UnstableAtom molecule
//     */
//    def apply(t: Tuple2[Product, Product]): Molecule = {
//
//      var param1: List[Variable] = List()
//      var param2: List[Variable] = List()
//
//      // we identify two cases:
//      // case x: Variable means => (x) -> [...]
//      // case x: Product  means => (x,y,z,t) -> [...]
//      t._1 match {
//        case x: Variable => {
//          param1 = x :: param1
//        }
//        case x: Product => {
//          param1 = x.productIterator.toList.asInstanceOf[List[Variable]]
//        }
//        case _ => throw new Exception("Bad arguments: when creating\"" + name + "\"(a -> b), a should be a Term*")
//      }
//
//      // we identify two cases:
//      // case x: Variable means => [...] -> (x)
//      // case x: Product  means => [...] -> (x,y,z,t).
//      t._2 match {
//        case x: Variable => {
//          param2 = x :: param2
//        }
//        case x: Product => {
//          param2 = x.productIterator.toList.asInstanceOf[List[Variable]]
//        }
//        case _ => throw new Exception("Bad arguments: when creating\"" + name + "\"(a -> b), b should be a Term*")
//      }
//
//      val unstableAtom: UnstableAtom = new UnstableAtom(name, f, t, param1 ::: param2)
//      unstableAtom.setParams(param1, param2)
//      unstableAtom
//    }
//
//
//  }
//
//  /**
//   * The UnstableAtom class
//   * @define THIS UnstableAtom
//   * @define PARENT CrjAtom
//   */
//  class UnstableAtom(relName: String, val f: (Tuple2[Product, Product], Buffer[(Variable, Term)]) => Unit, exp: Tuple2[Product, Product], terms: List[Term]) extends CrjAtom(relName, terms) {
//
//    //override def toString():String = "UnstableAtom"
//
//    var inputParams: List[Term] = List()
//    var outputParams: List[Variable] = List()
//    var missingVariable: Int = -1;
//
//    /** Store the given parameters as input and output parameters
//     *  the variables references.
//     *
//     * @param param1 a list of input variable
//     * @param param2 a list of output variable
//     */
//    def setParams(param1: List[Variable], param2: List[Variable]) = {
//      inputParams = param1
//      outputParams = param2
//    }
//
//
//    /** Evaluate the rule with the given substitutions
//     *  the variables references.
//     *
//     * @param subs a list of substitutions
//     * @return an integer: 0 => no more variable available since the last "eval" call
//     *    1 => some variable are now available but "eval" need some other values
//     *    2 => anything is available and the rule has been correctly computed
//     */
//    def eval(subs: Buffer[(Variable, Term)]): Int = {
//
//      val missingVariableBeforeThisIteration = missingVariable // save the previous value of missing value, so that we will be able to detect any changes
//
//      missingVariable = 0
//
//      var inputArgsExp: List[(Variable, Value[Int])] = Nil
//
//      // take the (Variable | Term)* input expression and construct a ( (Variable | Term), ValueTerm )* expression
//      try {
//        inputParams.foreach(p => {
//          p match {
//            case x: Variable => {
//              val res = subs.find(s => s._1.name == x.name)
//              res match {
//                case Some((y, v: Value[Int])) => {
//                  inputArgsExp = inputArgsExp ::: ((x, new ValueTerm[Int](v.getValue)) :: Nil)
//                  true
//                }
//                case _ => {
//                  missingVariable = missingVariable + 1; false
//                }
//              }
//            }
//            case t: Value[Int] => {
//              inputArgsExp = inputArgsExp ::: List[(Variable, Value[Int])]((new Variable("_"), t))
//            }
//            case _ => {}
//          }
//        })
//      } catch {
//        case ex: Exception => {
//          // TODO: check if execeptions are useful for this part?
//          ex.printStackTrace()
//        }
//      }
//
//      var outputArgsExp: List[(Variable, Value[Int])] = Nil
//
//      // take the (Variable | Term)* output expression and construct a ( (Variable | Term), ValueTerm )* expression
//      outputParams.foreach(p => {
//        p match {
//          case x: Variable => {
//            outputArgsExp = outputArgsExp ::: ((x, new MutableValueTerm[Int](0)) :: Nil)
//          }
//          case t => {
//          }
//        }
//      })
//
//      var exp2: Tuple2[Product, Product] = (inputArgsExp, outputArgsExp)
//
//      // finalValue is the value returned by the function:
//      //    0 : no more variable available since the last "eval" call
//      //    1 : some variable are now available but "eval" need some other values
//      //    2 : anything is available
//
//      var finalValue: Int = -1
//      // everything is OK => 2 (all variables are available)
//      if (missingVariable == 0) {
//        // evaluating the function
//        f(exp2, subs)
//
//        // insertion of the new values for the intermediary variable
//        exp2._2.asInstanceOf[List[(Variable, ValueTerm[Int])]].foreach(s => {
//          subs.append(s)
//        })
//
//        finalValue = 2
//      }
//      else
//      // no progress => 0 (no more values)
//      if (missingVariable == missingVariableBeforeThisIteration)
//        finalValue = 0
//      else
//      // a progress => 1 (some previously unavailable values are available)
//      if (missingVariable < missingVariableBeforeThisIteration || missingVariableBeforeThisIteration == -1)
//        finalValue = 1
//
//      finalValue
//    }
//  }
//}
