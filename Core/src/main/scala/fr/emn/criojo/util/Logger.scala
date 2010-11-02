package fr.emn.criojo.util

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 7:10:10 PM
 * To change this template use File | Settings | File Templates.
 */

//object LogLevel extends Enumeration{
//  val DEBUG, ERROR, INFO, WARNING = Value
//}

object Logger {

//  import LogLevel._
  val ERROR = 3
  val WARNING = 2
  val INFO = 1
  val DEBUG = 0

  var on = true
  var indent = 0
  var logLevel = INFO

  private def log(level:Int, message:String){
    if (level >= logLevel){
      var prefix = ""
      for(i<- 0 until indent){
        prefix += "   "
      }
      println(prefix + message)
    }
  }

  def log(level:Int, klass:Class[_],method:String,message:String){
    log(level, "["+klass+"."+method+"] "+message)
  }

  def log(text:String){
    log(DEBUG, text)
  }

  def log(klass:Class[_],method:String,message:String){
    log(DEBUG, klass, method, message)
  }

  def info(klass:Class[_],method:String,message:String){
    log(INFO, klass, method, message)
  }

  def error(klass:Class[_],method:String,message:String){
    log(ERROR, klass, method, message)
  }

  def debug(klass:Class[_],method:String,message:String){
    log(DEBUG, klass, method, message)
  }


  def levelDown{
    indent+=1
  }

  def levelUp{
    indent-=1
  }

  def disableLog{
    on = false
  }

  def enableLog{
    on = true
  }

}