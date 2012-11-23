package fr.emn.criojo.parallel

import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.core.datatype.Term
import fr.emn.criojo.lang.Molecule

/**
* Created with IntelliJ IDEA.
* User: mayleen
* Date: 11/23/12
* Time: 6:06 PM
* To change this template use File | Settings | File Templates.
*/
//TODO Must replace native relation with something that executes the code at creation time.
class NativeRelationSansObserver(name:String, nativeFun: (List[Term]) => Unit) extends LocalRelation(name){
  override def apply(vars:Term*):Molecule = {
    null
  }
}
