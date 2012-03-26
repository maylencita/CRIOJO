package fr.emn.criojo

import core.Atom
import core.factory.DefaultFactory
import lang.Cham

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 07/03/12
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */

trait TestCham extends Cham with DefaultFactory{
  var passed = 0
  val Print = NativeRelation("Print") {
    case ((Atom(_,terms), _)) => print(terms.mkString(","))
    case _ =>
  }
  val Passed = NativeRelation("Passed"){(s,a) =>
    passed += 1
  }
}
