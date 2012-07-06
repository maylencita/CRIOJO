package fr.emn.criojo.core

import datatype.{Term, Valuation}
import org.junit.Test
import fr.emn.criojo.ext.expression.ScalaString.VarScalaString
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.ext.expression.ScalaInt.VarScalaInt
import fr.emn.criojo.ext.expression.Relation.constructor.LocalRelation


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 2/16/12
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */

class AtomTest {

  var carbonRelation = LocalRelation("Carbon")
  var carbonRelation2 = LocalRelation("Carbon2")

  @Test
  def TermListTest() {

    val x = VarScalaString()
    val y = VarScalaString()

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom(carbonRelation ,listOfTerms)

    assert(atom.patterns(0)==x) // selection the 1st term of atom
    assert(atom.patterns(1)==y) // selection the 2nd term of atom
  }

  @Test
  def MatchesTest() {

    val x = VarScalaString()
    val y = VarScalaString()

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom(carbonRelation, listOfTerms)

    val x1 = VarScalaString()
    val y1 = VarScalaString()

    val listOfTerms1:List[Term] = List(x1, y1)
    var atom1:Atom = new Atom(carbonRelation, listOfTerms1)

    assert(atom.correspondsTo(atom1)) // Carbon(x,y) should match Carbon(x,y)

    val x2 = VarScalaString()
    val y2 = VarScalaString()

    val listOfTerms2:List[Term] = List(x2, y2)
    var atom2:Atom = new Atom(carbonRelation, listOfTerms2)

    assert(atom.correspondsTo(atom2)) // Carbon(x,y) should match Carbon(x1,y)

    val x3 = VarScalaString()
    val y3 = VarScalaString()

    val listOfTerms3:List[Term] = List(x3, y3)
    var atom3:Atom = new Atom(carbonRelation2, listOfTerms3)

    assert(!atom.correspondsTo(atom3)) // Carbon(x,y) should not match Carbon2(x1,y)
  }

  @Test
  def ApplyValuationTest() {

    val x = VarScalaInt()
    val y = VarScalaInt()
    val vx = WrapScalaInt(1)
    val vy = WrapScalaInt(2)

    val atomPattern = new Atom(carbonRelation, x :: y :: Nil)
    val atomExpression = atomPattern.applyValuation(Valuation(x->vx, y->vy))

    assert(atomExpression.patterns(0) == vx)
    assert(atomExpression.patterns(1) == vy)
  }

  @Test
  def cloneTest() {

    val x = VarScalaInt()
    val y = VarScalaInt()

    val listOfTerms:List[Term] = List(x, y)
    var atom:Atom = new Atom(carbonRelation,listOfTerms)

    var atom2:Atom = atom.clone
    assert(atom.correspondsTo(atom2)) // an atom should match his clone
  }
}
