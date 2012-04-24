package fr.emn.criojo.lang

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 22/02/11
 * Time: 12:13
 */
import fr.emn.criojo.util.Logger._
import fr.emn.criojo.core._
import datatype.Term
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation

case class NativeRelation(rn:String, solution:Solution, f:(Atom,Solution) => Unit) extends LocalRelation(rn){

  override def apply(vars:Term*):Molecule = new CrjAtom(this, vars.toList)

  override def notifyObservers(a:Atom) {
    a match {
      case Atom(LocalRelation(this.name), _) =>
        log("[Relation(" + name + ").notifyObservers] notified by " + a)
        f(a, solution)
        solution.inactivate(a)

        // todo: remove a?
        //solution.remove(a)

        solution.cleanup()
      case _ =>
    }
  }
}
