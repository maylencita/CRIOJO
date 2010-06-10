package fr.emn.creole.core

import Creole.Substitution

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 4:27:10 PM
 * To change this template use File | Settings | File Templates.
 */

class Solution {
  var atoms:List[Atom] = List()

  def findMatches(conjunction:List[Atom]):List[Atom] = {
    def findMatchesRec(c:List[Atom], subs:List[Substitution], acum:List[Atom]):List[Atom] = c match{
      case List() => acum
      case h :: rest =>
        val matches = findMatches(h, subs)
        if (matches.isEmpty)
          List()
        else{
          var results = List[Atom]()
          var i = matches.iterator
          while (i.hasNext && results.isEmpty){
            val m = i.next
            m.active = false
            results = findMatchesRec(rest, subs.union(h.vars.zip(m.vars)), acum :+ m)
            m.active = results.isEmpty
          }
          results
        }
    }


    findMatchesRec(conjunction, List(), List())
  }

  def findMatches(relation:Atom, subs:List[Substitution]): List[Atom] = {
    if (subs.isEmpty){
      atoms.filter(_.relName == relation.relName)
    }else{
      val test = relation.applySubstitutions(subs)
      atoms.filter(a => a.active && a.matches(test))
    }
  }
}