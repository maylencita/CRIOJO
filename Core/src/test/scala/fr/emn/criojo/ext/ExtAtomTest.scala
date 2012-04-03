package fr.emn.criojo.ext

import org.junit.Test
import fr.emn.criojo.core.{Atom, Term, Variable}


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/16/12
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */

class ExtAtomTest {

  @Test
  def cloneTest() {

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom("Carbon",listOfTerms)

    var atom2:Atom = atom.clone
    assert(atom.matches(atom2))
  }
}
