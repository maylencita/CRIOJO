package fr.emn.criojo.core.engine

import collection.mutable.ListBuffer

/**
 * Created with IntelliJ IDEA.
 * User: jonathan
 * Date: 7/13/12
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Singleton of Combinatory.
 *
 */
object Combinatory {


  /**
   * Create a chain of Combinatory from a list of list of A.
   *
   * @param list a list of list of A.
   *
   * @return a chain of Combinatory.
   */
  def from[A](list:ListBuffer[ListBuffer[A]]):Combinatory[A] = {

    def from_list(list:ListBuffer[A]):Combinatory[A] = {
      new Combinatory[A](list, null)
    }

    var firstPointer:Combinatory[A] = null
    var currentPointer:Combinatory[A] = null

    list.foreach(l => {
      val newCombinatory:Combinatory[A] = from_list(l)

      if(firstPointer == null)
        firstPointer = newCombinatory

      if(currentPointer != null)
        currentPointer.comb = newCombinatory

      currentPointer = newCombinatory
    })

    firstPointer
  }
}

/**
 * Combinatory is an inductive way to make lazy combination of elements. The comb parameter is another Combinatory: it
 * enables chain of combinatory. Using the next() function gives the next combination of elements.
 *
 * For example: Combinatory( l1, Combinatory( l2, Combinatory( l3, null) ) )
 *
 * @param list
	 * The list of elements
 * @param comb
	 * The next combinatory (this enables chain of combinatory)
 */
class Combinatory[A](var list:ListBuffer[A] = ListBuffer(), var comb:Combinatory[A] = null) {
  var index:Int = 0

  def reset() {
    index = 0
  }

  /**
   * True when the index of this Combinatory is out of bounds (reset function should be called).
   *
   * @return True when the index of this Combinatory is not out of bounds.
   */
  def isOverflowed:Boolean = index >= list.size


  /**
   * Put the index at the next combination.
   */
  def next() {
    if(comb != null) {
      if(!(isOverflowed)) {
        comb.next()

        if (comb.isOverflowed) {
          comb.reset()
          index += 1
        }
      }
    }
    else {
      if (!isOverflowed) {
        index += 1
      }
    }
  }

  /**
   * Return the actual combination.
   *
   * @return The actual combination.
   */
  def get():List[A] = {
    if(!(isOverflowed)) {
      if(comb != null) {
        if(!comb.isOverflowed) {
          list(index) :: comb.get()
        }
        else {
          Nil
        }
      }
      else {
        List(list(index))
      }
    }
    else {
      Nil
    }
  }

  /**
   * True when the index of this Combinatory and the index next Combinatories are not out of bounds (reset function should be called).
   *
   * @return True when the iindex of this Combinatory and the index next Combinatories are not out of bounds.
   */
  def hasNext:Boolean = {
    if(comb != null) {
      !isOverflowed || comb.hasNext
    }
    else {
      !isOverflowed
    }
  }


}
