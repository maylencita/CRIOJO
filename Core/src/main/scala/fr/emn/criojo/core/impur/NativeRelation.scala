package fr.emn.criojo.core.impur

import fr.emn.criojo.core.{Atom, Term, LocalRelation}


/*
* Created by IntelliJ IDEA.
* User: mayleen
* Date: 23/03/12
* Time: 22:37
*/

class NativeRelation(name:String, nativeFun: (List[Term]) => Unit) extends LocalRelation(name,false){
  override def notifyObservers(a:Atom){
    nativeFun(a.terms)
  }
}