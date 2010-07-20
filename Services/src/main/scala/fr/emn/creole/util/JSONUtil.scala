package fr.emn.creole.util

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 15, 2010
 * Time: 11:47:52 AM
 * To change this template use File | Settings | File Templates.
 */

import scala.reflect.Manifest
import sjson.json._
import sjson.json.Serializer.SJSON

object JSONUtil{
  /**
   * Serializes objects as JSON string
   * @val obj object to be serialized
   */
  def serialize(obj: AnyRef): String = new String(Serializer.SJSON.out(obj))

  /**
   * Deserializes objects from JSON byte array
   * @val json JSON byte array
   * @type T type of the deserialized object
   */
  def deserialize[T](json: Array[Byte])(implicit m: Manifest[T]): AnyRef =
    Serializer.SJSON.in[T](json)(m)
  
}