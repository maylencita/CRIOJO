package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 4:53:46 PM
 * To change this template use File | Settings | File Templates.
 */

object Criojo{
  var id = 0
  type Substitution = Pair[Variable, Term]//Variable]

  def newId = {
    id += 1
    id
  }

  def getSubstitutions(l1:List[Term], l2:List[Term]):List[Substitution] = {
    l1.zip(l2).flatMap(p=>getSubstitution(p._1,p._2))
  }

  def getSubstitution(term1:Term,term2:Term):List[Substitution] = term1 match{
    case v:Variable => List((v,term2))
    case f1@Function(n,params) => term2 match{
      case f2:Function if(f2.params.size == f1.params.size) =>
        getSubstitutions(f1.params,f2.params)
//        f1.params.zip(f2.params).flatMap(p => getSubstitution(p._1,p._2))
      case _ => (Undef,term2) :: Nil
    }
    case _ => (Undef,term2) :: Nil
  }

}

object Indexator{
  var index=0
  def getIndex = {
     index +=1
     index
  }
}
