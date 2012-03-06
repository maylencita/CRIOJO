package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 4:53:46 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * The Criojo singleton
 * @define THIS Atom
 */
object Criojo{
  var id = 0
  type Valuation = Set[Pair[Variable,Term]]

  @deprecated("Use: Valuation")
  type Substitution = Pair[Variable, Term]//Variable]

  /** Increments and returns an Id
   *
   * @return a incremented [[scala.Int]] Id
   */
  def newId = {
    id += 1
    id
  }

  /** Builds a list of Substitution (Pair[ [[fr.emn.criojo.core.Variable]],[[fr.emn.criojo.core.ValueTerm]] ]) with two List[ [[fr.emn.criojo.core.Term]] ]
   *
   * @param l1 a List[ [[fr.emn.criojo.core.Term]] ], usually variables ([[fr.emn.criojo.core.Variable]])
   * @param l2 a List[ [[fr.emn.criojo.core.Term]] ], usually values ([[fr.emn.criojo.core.ValueTerm]])
   * @return a list of Substitution (Pair[ [[fr.emn.criojo.core.Variable]],[[fr.emn.criojo.core.ValueTerm]] ])
   */
  def getSubstitutions(l1:List[Term], l2:List[Term]):List[Substitution] = {
    l1.zip(l2).flatMap(p=>getSubstitution(p._1,p._2))
  }

  /** Builds a Substitution (Pair[ [[fr.emn.criojo.core.Variable]],[[fr.emn.criojo.core.ValueTerm]] ])
   *
   * @param term1 a [[fr.emn.criojo.core.Term]]
   * @param term2 a [[fr.emn.criojo.core.Term]]
   * @return a Substitution (Pair[ [[fr.emn.criojo.core.Variable]],[[fr.emn.criojo.core.ValueTerm]] ])
   */
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

/**
 * The Indexator singleton
 * @define THIS Atom
 */
object Indexator{
  var index=0

  /** Increments and returns an index
   *
   * @return a incremented [[scala.Int]] index
   */
  def getIndex = {
     index +=1
     index
  }
}
