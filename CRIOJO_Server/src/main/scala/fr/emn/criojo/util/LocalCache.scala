package fr.emn.criojo.util

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 29, 2010
 * Time: 10:29:54 AM
 * To change this template use File | Settings | File Templates.
 */

import java.io._
import collection.mutable.Map

object LocalCache{
  val data = Map[Any,Array[Byte]]()

  def put(key:Any, value:Any){
    try{
      val baos = new ByteArrayOutputStream()
      val out = new ObjectOutputStream(baos)
      out.writeObject(value)
      data.put(key,baos.toByteArray)
    }catch{
      case e => e.printStackTrace()
    }
  }

  def get(key:Any):Any = {
    var in:ObjectInputStream = null
    try{
      data.get(key) match{
        case Some(value:Array[Byte]) =>
          val bais = new ByteArrayInputStream(value)
          in = new ObjectInputStream(bais)
          in.readObject
        case _ => null
      }
    } catch{
      case e => e.printStackTrace()
    } finally{
      if (in != null)
        in.close()
    }
  }
}