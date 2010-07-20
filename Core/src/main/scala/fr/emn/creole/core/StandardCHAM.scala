package fr.emn.creole.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 8, 2010
 * Time: 11:17:48 AM
 * To change this template use File | Settings | File Templates.
 */

trait StandardCHAM extends CHAM{
  this.solution = new StandardSolution()
  addRelation(new Relation("Null", true))
  addRelation(new Relation("Suc", true))

  

}