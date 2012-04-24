package fr.emn.criojo.core.factory

import fr.emn.criojo.core.impur.NativeRelation
import fr.emn.criojo.ext.expression.Relation.constructor.{Channel, LocalRelation}
import fr.emn.criojo.ext.expression.Relation.{ChannelLocation, Relation}
import fr.emn.criojo.core.datatype.Term


/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 23/03/12
 * Time: 20:03
 * To change this template use File | Settings | File Templates.
 */

trait RelationFactory {
  def createLocalRelation(name:String):Relation
  def createChannel(name:String,location:ChannelLocation):Channel
  def createNativeRelation(name:String, f:(List[Term]) => Unit):NativeRelation
}

trait DefaultFactory extends RelationFactory{
  def createLocalRelation(name:String):Relation = new LocalRelation(name)

  def createNativeRelation(name:String, f:(List[Term]) => Unit):NativeRelation = new NativeRelation(name,f)

  def createChannel(name:String,location:ChannelLocation):Channel = new Channel(name, location)
}
