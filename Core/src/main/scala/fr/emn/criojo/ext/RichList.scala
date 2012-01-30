package fr.emn.criojo.ext

import fr.emn.criojo.lang._

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 25/10/11
 * Time: 16:08
 */
trait RichList extends ListCham{

  val Append = Rel("Append")
  private val Appd = Rel("$Appd")
  private val Hd = Rel("$$Head")
  private val Tl = Rel("$$Tail")
  private val LErr = Rel("$$LErr")
  private val LNil = Rel("$$LNil")
  private val Fn = VarR("$F")
  private val K = VarR("K")
  private val Kmin = VarR("K-")
  private val Err = VarR("Err")
  private val a,l,l0,l1,l2,tl,r,x,s,h = Var
  private val X,Y = Tok()

  val Copy = Rel("Copy")
  when(Copy(l1,l)){
    Head(l1,Hd,LErr) & Tail(l1,Tl,LErr) & Copy(l1,l)
  }
  when(Copy(l1,l) & Hd(l1,h) & Tl(l1,tl)){
    Cons()
  }

  /*****
   * Append : (List,List) => List
   */
  when(Append(l1,l2,l)){
    IsNil(l1,LNil,LErr) & Head(l1,Hd,LErr) & Tail(l1,Tl,LErr) & Appd(l1,l2,l)
//    Appd(l1,l2,l)
  }
  when(Appd(l0,l1,l) & Hd(l0,a) & Tl(l0,tl)){
    Nu(l2)(Cons(a, l2, l) & Appd(tl,l1,l2) & Head(tl,Hd,LErr) & Tail(tl,Tl,LErr) & IsNil(tl,LNil,LErr))
  }
  when(Appd(l0,l1,l) & LNil(l0)){
    Copy(l1,l)
  }

  val Foreach = Rel("Foreach")
  when(Foreach(l,Fn) & Head(l,a) & Tail(l,tl)){
    Fn(a) & Foreach(tl,Fn) & Head(l,a) & Tail(l,tl)
  }
  when(Foreach(l,Fn) & Nil(l)){
    Nil(l)
  }

  /*****
   * Map: List => List
   */
  val Map = Rel("Map")
  when(Map(l,Fn,l0) & Head(l,a) & Tail(l,tl)){
    Nu(l1,r)(Map(tl,Fn,l1) & Fn(a,r) & Appd(r,l1,l0) & Head(l,a) & Tail(l,tl))
  }
  when(Map(l,Fn,l2) & Nil(l)){
    Nil(l2) & Nil(l)
  }

  /*****
   * Filter: (List, => Boolean) => List
   */
  val Filter = Rel("Filter")
  private val Cont = Rel("$Cont")
  val Kp = Rel("K+")
  val Km = Rel("K-")
  when(Filter(l0,Fn,l1) & Head(l0,a)){
    Nu(x,l2)(Fn(x,a,Kp,Km) & Cont(x,l0,Fn,l1))
  }
  when(Kp(x,a) & Cont(x,l0,Fn,l2) & Tail(l0,tl)){
    Nu(l1)(Filter(tl,Fn,l1) & Appd(a,l1,l2) & Head(l0,a) & Tail(l0,tl))
  }
  when(Km(x,a) & Cont(x,l0,Fn,l2) & Tail(l0,tl)){
    Filter(tl,Fn,l2) & Head(l0,a) & Tail(l0,tl)
  }

}