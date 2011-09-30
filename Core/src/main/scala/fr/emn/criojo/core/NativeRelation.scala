/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 22/02/11
 * Time: 12:13
 */
package fr.emn.criojo.core

import fr.emn.criojo.util.Logger._

case class NativeRelation(rn:String, sol:Solution, f:(Atom,Solution) => Unit) extends LocalRelation(rn, false, true){
  def apply(vars:Variable*):Atom = new Atom(name, vars.toList)

  override def notifyObservers(a:Atom)= a match{
    case Atom(this.name, _) =>
      log("[Relation("+name+").notifyObservers] notified by " + a)
      f(a, sol)
      sol.inactivate(a)
      sol.cleanup
    case _ => super.notifyObservers(a)
  }
}
