package fr.emn.criojo.ext

import fr.emn.criojo.lang._

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 21/10/11
 * Time: 11:11
 */
trait ListCham extends Cham{
  val Cons = createAndAddRelation("Cons")
  val IsNil = createAndAddRelation("IsNil")
  private val Lst = createAndAddRelation("lst")
  val Head = createAndAddRelation("Head")
  val Tail = createAndAddRelation("Tail")
  val Nil = createAndAddRelation("Nil")
  private val Hd = createAndAddRelation("$Hd")
  private val Tl = createAndAddRelation("$Tl")
  private val K = VarR("K")
  private val Kmin = VarR("K-")
  private val Err = VarR("Err")
  private val a,l,l0,l1,l2,tl,r,x,s = createVariable()
  private val X,Y = Tok()


  /**
   * List construction
   */
  when(Cons(a,l1,l) & Lst(l1)){
    Lst(l) & Hd(l,a) & Tl(l, l1) & Lst(l1)
//    Nu(l)(Cns(a,l1,l) & K(s,l))
//    Cns(a,l1,l)
  }
//  when(Cns(a,l1,l) & Lst(l1)){
//    Lst(l) & Hd(l,a) & Tl(l, l1) & Lst(l1)
//  }
  when(Nil(l)){
    If(Abs(Lst(l))){
      Lst(l) & Nil(l)
    }
  }

  /**
   * Query Services
   */
  when(Head(l,K,Err) & Hd(l,a)){K(l,a) & Hd(l,a)}
  when(Head(l,K,Err)){
    If(Abs(Lst(l))){
      Err(l)
    }
  }

  when(Tail(l,K,Err) & Tl(l,tl)){K(l,tl) & Tl(l,tl)}
  when(Tail(l,K,Err)){
    If(Abs(Lst(l))){
      Err(l)
    }
  }

  when(IsNil(l,K,Kmin) & Nil(l)){K(l) & Nil(l)}
  when(IsNil(l,K,Kmin)){
    If(Abs(Nil(l))){
      Kmin(l)
    }
  }


}
