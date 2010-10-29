package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 22, 2010
 * Time: 2:59:57 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._ , Creole._
import fr.emn.criojo.util.Logger._

import java.util.Collections
//import net.sf.jsr107cache._
//import javax.cache._
import com.google.appengine.api.memcache._

object CachedSolution{
  def apply(vmName:String,alst:Atom*):Solution = new CachedSolution(vmName,alst.toList)
}

class UnavailableChacheException extends Exception("Unable to reeach cache service.")

class CachedSolution(vmName:String, var elems:List[Atom]) extends Solution{
  def this(vmName:String) = this(vmName, List[Atom]())

  val cache = //LocalCache
    try{
      MemcacheServiceFactory.getMemcacheService
    }catch{
      case e =>
        log(WARNING, this.getClass, "init()", "Unable to get cache: " + e)
        throw new UnavailableChacheException
    }
//  cache.put(vmName, elems)

  private def getCachedElems:List[Atom] = {
    if (cache == null)
      List[Atom]()
    else
      cache.get(vmName) match{
            case elemsSet:List[Atom] => elemsSet
            case _ => log(WARNING, this.getClass, "getCachedElems", "Solution not found in cache. Creating a new one...")
            List[Atom]()
          }
  }

  /**
   * Retrieves existing solution from cache
   */
  def load{
    log(this.getClass, "load", "Current cache: " + cache.get(vmName))
    elems = getCachedElems
  }

  /**
   * Apply changes
   */
  def update(newsol: Solution){
    if (newsol.contains(False) || newsol.isEmpty){
      clear
    }else{
      this.elems = newsol.elems
    }
    cache.put(vmName,elems)
    log(DEBUG, this.getClass, "update", "New cache: " + cache.get(vmName))
  }

  def addAtom(atom:Atom){ elems :+= atom }
  def remove(a:Atom) { elems -= a}
  def clear {   elems = List[Atom]()  }
  def copy:Solution = new SolutionImpl(elems.map(a => a.clone))
  def cleanup{
    var elems2 = List[Atom]()
    for(a <- elems){
        elems2 = elems2 :+ a
    }

    elems = elems.filter(a => a.isActive)
  }
  def revert{
    elems.foreach(_.setActive(true))
  }
  def inactivate(a:Atom){
    a.setActive(false)
  }
  def activate(a:Atom){
    a.setActive(true)
  }
  override def clone:Solution = new SolutionImpl(List[Atom]() ++ this.elems)
/*
  def addAtom(atom:Atom){
    cache.put("elems", elems + atom)
  }

  def remove(a:Atom) { cache.put("elems", elems - a)}

  def clear {   cache.put("elems", Set[Atom]())  }

  def copy:Solution = new CachedSolution(elems.map(a => a.clone))

  def cleanup{cache.put("elems", elems.filter(_.isActive))  }

  def update(newsol: Solution){
    if (newsol.contains(False) || newsol.isEmpty){
      clear
    }else{
      cache.put("elems", newsol.elems)
    }
  }

  def revert{
    val cElems = elems
    cElems foreach(_.setActive(true))
    cache.put("elems", cElems )
  }
  def inactivate(atom:Atom){
    val cElems = elems
    cElems foreach {
      case a @ Atom(atom.relName, atom.vars) => a.setActive(false)
      case _ =>
    }
    cache.put("elems", cElems )
  }
  def activate(atom:Atom){
    val cElems = elems
    cElems foreach {
      case a @ Atom(atom.relName, atom.vars) => a.setActive(true)
      case _ =>
    }
    cache.put("elems", cElems )
  }

  override def clone:Solution = new CachedSolution(Set[Atom]() ++ elems)
*/
}

