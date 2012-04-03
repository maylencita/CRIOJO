package fr.emn.criojo.core

import org.junit.Test


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/16/12
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */

class AtomTest {

  @Test
  def TermListTest {

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom("Carbon",listOfTerms)

    assert(atom.apply(0)==x) // selection the 1st term of atom
    assert(atom.apply(1)==y) // selection the 2nd term of atom
  }

  @Test
  def MatchesTest {

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom("Carbon",listOfTerms)

    val x1 = new Variable("x")
    val y1 = new Variable("y")

    val listOfTerms1:List[Term] = List(x1, y1)
    var atom1:Atom = new Atom("Carbon",listOfTerms1)

    assert(atom.matches(atom1)) // Carbon(x,y) should match Carbon(x,y)

    val x2 = new Variable("x1")
    val y2 = new Variable("y")

    val listOfTerms2:List[Term] = List(x2, y2)
    var atom2:Atom = new Atom("Carbon",listOfTerms2)

    assert(atom.matches(atom2)) // Carbon(x,y) should match Carbon(x1,y)

    val x3 = new Variable("x1")
    val y3 = new Variable("y")

    val listOfTerms3:List[Term] = List(x3, y3)
    var atom3:Atom = new Atom("Carbon2",listOfTerms3)

    assert(!atom.matches(atom3)) // Carbon(x,y) should not match Carbon2(x1,y)
  }

  @Test
  def ApplyValuationTest {

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom("Carbon",listOfTerms)

    val x1 = new Variable("x")
    val y1 = new Variable("y")
    val vx1 = new ValueTerm[Int](1)
    val vy1 = new ValueTerm[Int](2)

    val valuations = Valuation(x1->vx1,y1->vy1)

    val atom2:Atom = atom.applyValuation(valuations)

    assert(atom2.apply(0)==vx1) // selection of the 1st term of the valuated atom
    assert(atom2.apply(1)==vy1) // selection of the 2nd term of the valuated atom
  }

  @Test
  def cloneTest {

    val x = new Variable("x")
    val y = new Variable("y")

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom("Carbon",listOfTerms)

    var atom2:Atom = atom.clone
    assert(atom.matches(atom2)) // an atom should match his clone
  }
}
