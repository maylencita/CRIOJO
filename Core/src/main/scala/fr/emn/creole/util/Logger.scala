package fr.emn.creole.util

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 10, 2010
 * Time: 7:10:10 PM
 * To change this template use File | Settings | File Templates.
 */

object Logger {
  val on = true
  var level = 0

//  def log(text:String){
//    println(text)
//  }

  def log(text:String){
    if (on){
      var prefix = ""
      for(i<- 0 until level){
        prefix += "   "
      }
      println(prefix + text)
    }
  }

  def levelDown{
    level+=1
  }

  def levelUp{
    level-=1
  }

}