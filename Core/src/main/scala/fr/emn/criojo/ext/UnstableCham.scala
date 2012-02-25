//package fr.emn.criojo.ext
//
//import fr.emn.criojo.core._
//import fr.emn.criojo.ext._
//import collection.mutable.Buffer
//import fr.emn.criojo.lang.Cham
//
///*
//* Created by IntelliJ IDEA.
//* User: mayleen
//* Date: 30/09/11
//* Time: 14:59
//*/
//@deprecated
//class UnstableCham extends Cham with
//UnstableEngine {
////StatefulEngine
////SimpleEngine {
//
//
//  def ComputableRelation(name: String)(f: (Tuple2[Product, Product], Buffer[(Variable, Term)]) => Unit) = {
//    val unstableRel = new UnstableRelation(name, f)
//    addRelation(unstableRel)
//    unstableRel
//  }
//
//
//}