package fr.emn.criojo.ext.expression.types

import fr.emn.criojo.ext.expression.operations._
import fr.emn.criojo.ext.expression.{IncorrectUseException, CriojoType}

case class CriojoInteger(v:Int) extends CriojoType with CanAddType with CanSubType with CanMultiplyType with CanDivideType with CanBeCompared {

  def add(o:CriojoType):CriojoType = o match {
    case CriojoInteger(x:Int) => CriojoInteger(v+x)
    case CriojoString(s:String) => CriojoString(v.toString()+s)
  }
  
  def sub(o:CriojoType):CriojoType = o match {
    case CriojoInteger(x:Int) => CriojoInteger(v-x)
  }
  
  def multiply(o:CriojoType):CriojoType = o match {
    case CriojoInteger(x:Int) => CriojoInteger(v*x)
  }
  
  def divide(o:CriojoType):CriojoType = o match {
    case CriojoInteger(x:Int) => CriojoInteger(v/x)
  }

  def isEqual(o:CriojoType):CriojoType = o match {
    case CriojoInteger(x:Int) => CriojoBoolean(v==x)
  }

  def isDifferent(o:CriojoType):CriojoType = o match {
    case CriojoInteger(x:Int) => CriojoBoolean(v!=x)
  }

  def lessThan(o:CriojoType):CriojoType = o match {
    case CriojoInteger(x:Int) => CriojoBoolean(v<x)
  }

  def lessOrEquals(o:CriojoType):CriojoType = o match {
    case CriojoInteger(x:Int) => CriojoBoolean(v<=x)
  }

  def greaterThan(o:CriojoType):CriojoType = o match {
    case CriojoInteger(x:Int) => CriojoBoolean(v>x)
  }

  def greaterOrEquals(o:CriojoType):CriojoType = o match {
    case CriojoInteger(x:Int) => CriojoBoolean(v>=x)
  }

  def matches(that:CriojoType):Boolean = that match {
    case CriojoInteger(w:Int) => w == v
    case _ => false
  }

  override def toString():String = v.toString()
}