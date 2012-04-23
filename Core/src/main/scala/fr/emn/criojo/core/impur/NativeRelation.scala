package fr.emn.criojo.core.impur

import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation
import fr.emn.criojo.core.{Atom, Term}


/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 23/03/12
* Time: 22:37
*/

class NativeRelation(name:String, nativeFun: (List[Term]) => Unit) extends LocalRelation(name){
  override def notifyObservers(a:Atom){
    nativeFun(a.patterns)
  }
}