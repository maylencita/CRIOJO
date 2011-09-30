package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 29/09/11
 * Time: 15:11
 */

case class StandAloneSolution(var elems:List[Atom]) extends Solution{
  def this()= this(List[Atom]())
  def remove(a:Atom) { elems.filterNot(_==a)}
  def clear() {   elems = List[Atom]()  }
  def copy(newOwner:CHAM):Solution = new StandAloneSolution(elems.map(a => a.clone))
  def addAtom(atom:Atom){
    elems :+= atom
  }
  def addMolecule(molecule:List[Atom]){
    elems ++= molecule
  }
  def cleanup(){
    elems = elems.filter(_.isActive)
  }
  def revert(){
    elems.foreach(_.setActive(true))
  }
  def update(newsol: Solution){
    if (newsol.contains(False) || newsol.isEmpty){
      clear()
    }else{
      this.elems = newsol.elems
    }
  }
  def inactivate(a:Atom){
    a.setActive(false)
  }
  def activate(a:Atom){
    a.setActive(true)
  }
  override def clone:Solution = new StandAloneSolution(List[Atom]() ++ this.elems)

  def notifyCHAM(newAtom:Atom){}
}
