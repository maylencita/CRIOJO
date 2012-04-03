package fr.emn.criojo.lang

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 22/02/11
 * Time: 12:13
 */
import fr.emn.criojo.util.Logger._
import fr.emn.criojo.core._

case class NativeRelation(rn:String, solution:Solution, f:(Atom,Solution) => Unit) extends LocalRelation(rn, false, true){

  def apply(vars:Term*):Molecule = new CrjAtom(name, vars.toList)

  override def copy(sol:Solution) = new NativeRelation(rn, sol, f)

  override def notifyObservers(a:Atom) {
    a match {
      case Atom(this.name, _) =>
        log("[Relation(" + name + ").notifyObservers] notified by " + a)
        f(a, solution)
        solution.inactivate(a)

        // todo: remove a?
        //solution.remove(a)

        solution.cleanup()
      case _ => super.notifyObservers(a)
    }
  }
}
