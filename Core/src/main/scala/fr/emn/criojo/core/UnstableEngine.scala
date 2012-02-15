package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/9/12
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core.Criojo._
import collection.mutable.Buffer
import fr.emn.criojo.lang.ChamGuard

class UnstableEngine extends StatefulEngine2 {

  def Equal(term1:Term, term2:Term):Guard =  new EqualsGuard(term1, term2) with ChamGuard
  def NotEqual(term1:Term, term2:Term):Guard =  new NotEqualsGuard(term1, term2) with ChamGuard
  //def OneIsTrue(guards:List[Guard]):Guard = new OneIsTrueGuard(guards.toList) with ChamGuard

  // this method has been override to include a specific case for UnstableAtom
  override def createRule(h: Head, b: Body, g: Guard, scope: List[Variable]) = {

    var headList:List[Atom] = List()
    var unstableList:List[UnstableAtom] = List()

    h.foreach(a => {
      a match {
        case x:UnstableAtom => unstableList = x :: unstableList
        case _ => headList = a :: headList
      }
    })

    new UnstableRule(headList, b, g,scope, unstableList)
  }

  class UnstableRule(head:List[Atom], body:List[Atom], guard:Guard, scope:List[Variable],
                     val unstableRelations:List[UnstableAtom]) extends StatefulRule(head, body, guard, scope) {

    // since the introduction of UnstableRule, some parts of the algorithm needs specific code
    override def applyReaction(finalExecution:PartialExecution) {
      try {
        val scopeSubs = scope.map{v => val i=Indexator.getIndex; (v,v+"@"+i)}

        // il faut ajouter les nouvelles variables ici!
        var subs:Buffer[(Variable,Term)] = finalExecution.subs.union(scopeSubs).toBuffer


        // for each unstableRelation, Criojo engin will compute values for the missing variables
        if(unstableRelations.size != 0) {
          var sum:Int = 0

          // while the evaluation of the UnstableRelation is not stable
          var n:Int = 1;
          while(n!=0 && sum/n != 2) {
            sum = 0; // initialization of the states
            n=0;
            var size = unstableRelations.size
            
            for(i <- 0 to (size-1)) {
              var r = unstableRelations(i)

                // 0 : same number of missing variables
                // 1 : number of missing variables is decreasing
                // 2 : all variables are available
                var result = r.eval(subs)
                sum += result
                n += 1
            }

            // execution has detected a cycle!
            if(sum == 0 && n != 0) {
              throw new Exception("Problem when calculating this rule : \""+this+"\", Criojo engine assumes that there is a cycle!")
            }
          }
        }

        val newAtoms = this.body.map(_.applySubstitutions(subs.toList))

        val removeAtoms = finalExecution.atoms

        removeAtoms.foreach{a => removeAtom(a)}
        newAtoms.foreach(a => introduceAtom(a))

        // TODO : check if it is the cause of the reccursive bug!
        //executeRules()

        true

      } catch {

        case ex:Exception => println("execution aborted: "+ex.printStackTrace())
      }
    }
  }
}
