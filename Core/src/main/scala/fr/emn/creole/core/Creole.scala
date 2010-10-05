package fr.emn.creole.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 4:53:46 PM
 * To change this template use File | Settings | File Templates.
 */

object Creole{
  var id = 0
  type Substitution = Pair[Variable, Variable]

  def newId = {
    id += 1
    id
  }
}

object Indexator{
  var index=0
  def getIndex = {
     index +=1
     index
  }
}
