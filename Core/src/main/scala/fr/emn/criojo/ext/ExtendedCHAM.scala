package fr.emn.criojo.ext

import fr.emn.criojo.core._
import fr.emn.criojo.lang.{CrjAtom, Molecule, Cham}

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 15, 2010
 * Time: 11:27:15 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Extended CHAM with support for constant values, pretty-print, etc.
 */
//TODO Add other CHAM traits.. for example: with NumberCHAM, DateCHAM...
abstract class ExtendedCHAM extends Cham with IntegerCham with StrCHAM with NullCHAM{
//
//  private val x,y = Var

  /**********************************************************************
  * VM definition:
  */
//  val Print = LocalRelation("Print")
//
//  private val Null_print = NativeRelation("Null_print"){(a,s) => println("Null")}
//
//  rules(
//    Print(x) --> NotNul(x) ?: (Int_print(x) &: Str_print(x)),
//    Print(x) --> Nul(x) ?: Null_print(x)
//  )
  /***********************************************************************/


}

