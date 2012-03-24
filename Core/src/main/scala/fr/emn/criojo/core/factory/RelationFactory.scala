package fr.emn.criojo.core.factory

import fr.emn.criojo.core.{LocalRelation, Relation, Term}
import fr.emn.criojo.core.impur.NativeRelation


/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 23/03/12
 * Time: 20:03
 * To change this template use File | Settings | File Templates.
 */

trait RelationFactory {
  def createLocalRelation(name:String):Relation
  def createRemoteRelation(name:String):Relation
  def createNativeRelation(name:String, f:(List[Term]) => Unit):NativeRelation
}

trait DefaultFactory extends RelationFactory{
  def createLocalRelation(name:String):Relation = new LocalRelation(name)

  def createNativeRelation(name:String, f:(List[Term]) => Unit):NativeRelation = {
    new NativeRelation(name,f)
  }

  def createRemoteRelation(name:String):Relation = {
    throw new IllegalAccessException("Unimplemented method createRemoteRelation()")
  }
}
