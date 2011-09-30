package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 18, 2010
 * Time: 2:08:14 PM
 * To change this template use File | Settings | File Templates.
 */

abstract class Conjunction{
    def empty:Boolean
    def head:Atom
    def tail:Conjunction

    var scope = List[Variable]()

    def &: (a:Atom) = new &: (a, this)

    def toList:List[Atom] = head :: tail.toList

    def ==> (c2:Conjunction): RuleFactory => Rule ={
      val f = (rf:RuleFactory) => rf.createRule(this.toList,c2.toList,new EmptyGuard,c2.scope)
      f
    }

    def ==> (gc: Tuple2[_,_]):RuleFactory => Rule = gc match {
      case (g:Guard, conj:Conjunction) => getRuleBuilder(g,conj)
      case _ => null
    }

    def getRuleBuilder (g:Guard,conj:Conjunction): RuleFactory => Rule = {
      val f = (rf:RuleFactory) => rf.createRule(this.toList,conj.toList,g,conj.scope)
      f
    }

    override def toString = head + (if (tail.empty) "" else  " & " + tail)
  }

  case class Nu(varLst:Variable*){
    val scope = varLst.toList

    def apply(conj:Conjunction):Conjunction = {
      conj.scope = this.scope
      conj
    }
  }

  case object Empty extends Conjunction{
    def empty = true
    def head = throw new NoSuchElementException("head of empty conjunction")
    def tail = throw new NoSuchElementException("tail of empty conjunction")
    scope = List()

    override def toList = List()
    override def toString = ""
  }

  final case class &: (hd:Atom, tl:Conjunction) extends Conjunction{
    def head = hd
    def tail = tl
    def empty = false
    scope = List()
  }

