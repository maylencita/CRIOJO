package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 29, 2010
 * Time: 10:27:28 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._

import collection.immutable.ListSet

object CacheTest extends Application{
  val a1 = Atom("a1")  ;  val a4 = Atom("a4")
  val a2 = Atom("a2")  ;  val a5 = Atom("a5")
  val a3 = Atom("a3")  ;  val a6 = Atom("a6")

  val atomList = List(a1,a2,a3)
  println("atomList: " + atomList)
  LocalCache.put("List1", atomList)

  println("a1 - a2: " + a1 == a2 )
  println("a4 - a5: " + a4 == a5 )
  
  val atomSet = new ListSet() ++ (a4 :: a5 :: a6 :: a4 :: a5 :: a6 :: Nil)
  println("atomSet: " + atomSet)
  LocalCache.put("AtomSet", atomSet)

  val atomList2 = LocalCache.get("List1")
  println("atomList2: " + atomList2)

  val atomSet2 = LocalCache.get("AtomSet")
  println("atomSet2: " + atomSet2)
}