package fr.emn.criojo.integration.physics

import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.ext.expressions.{UndefinedExpression, BooleanExpression, Expression}
import util.Random
import fr.emn.criojo.core.statemachine.PartialExecution
import fr.emn.criojo.core._
import fr.emn.criojo.lang.{CrjAtom, ChamGuard, Cham}

import scala.collection.JavaConversions._

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 3/16/12
 * Time: 10:21 AM
 * To change this template use File | Settings | File Templates.
 */

class PhysicCham(val view:PhysicsSketch) extends Cham with IntegerCham {

  //DEBUG_DIRECT_MODE = true

  class PhysicRule(head:List[Atom], body:List[Atom],  guard:Guard, scope:Set[Variable])
    extends StatefulRule(head, body, guard, scope) {

    override def applyReaction(finalExecution:PartialExecution) {
      val finalValuation = scope.foldLeft(finalExecution.vals){(vals,sv) =>
        val i = Indexator.getIndex
        vals union Valuation(sv -> new IdTerm(sv.name+"@"+i))
      }

      if(!finalValuation.failed) {

        val removeAtoms = for (i <- 0 until head.size; if !head(i).persistent) yield{
          finalExecution.atom(i)
        }

        var x2 = 0.0f
        var y2 = 0.0f

        removeAtoms.foreach(a => {

          removeAtom(a)

          var parentViewObject:PhysicAtom = null
          a match {
            case crj:PhysicCriojoAtom => parentViewObject = view.getPhysicAtom(crj)
            case _ =>
          }


          if(parentViewObject == null) {
            a match {
              case crj:PhysicCriojoAtom => parentViewObject=crj.parentViewObject
              case _ =>
            }
          }
            
          if(parentViewObject != null) {
            x2 += parentViewObject.x/removeAtoms.size
            y2 += parentViewObject.y/removeAtoms.size

            view.removeAtom(parentViewObject)
          }
        })

        val newAtoms:List[PhysicCriojoAtom] = this.body.map(_.applyValuation(finalValuation)).map(a => {
          var physAtom:PhysicCriojoAtom = new PhysicCriojoAtom(a.relName, a.terms)

          physAtom.Id = PhysicCriojoAtom.nextId
          PhysicCriojoAtom.nextId = PhysicCriojoAtom.nextId+1

          var newViewAtom:PhysicAtom = new PhysicAtom()
          newViewAtom.setAtom(physAtom)
          newViewAtom.setX(x2)
          newViewAtom.setY(y2)
          newViewAtom.setView(view)
          newViewAtom.setRadius(80f)

          newViewAtom.setXSpeed(view.speed * scala.util.Random.nextFloat()*1f - 0.5f);
          newViewAtom.setYSpeed(view.speed * scala.util.Random.nextFloat()*1f - 0.5f);

          if(view != null) {
            view.addAtom(newViewAtom)
            view.mem.put(physAtom.asInstanceOf[PhysicCriojoAtom].Id, newViewAtom)
          }

          physAtom.parentViewObject = newViewAtom
          physAtom
            //introduceAtom(physAtom)
        }).asInstanceOf[List[PhysicCriojoAtom]]
      }
    }
  }

  override def createRule(h: Head, b: Body, g: Guard, scope: Set[Variable]) = new PhysicRule(h,b,g,scope)

  override def Rel(n:String): ApplicableRel = {
    val r = new ApplicableRel(n,
      (terms:List[Term])=>new PhysicCriojoAtom(n, terms.toList))
    addRelation(r)
    r
  }

  def giveNewAtom(rel:ApplicableRel):PhysicCriojoAtom = {
    var t:PhysicCriojoAtom = rel().asInstanceOf[PhysicCriojoAtom]
    t.Id = PhysicCriojoAtom.nextId
    PhysicCriojoAtom.nextId = PhysicCriojoAtom.nextId+1
    t
  }

  def Proba(p:Float):ChamGuard = {
    val g = new CriojoGuard(List()) with ChamGuard {
      def eval(vals: Valuation) = {
        var f:Float = Random.nextFloat()
        p > f
      }
    }
    g
  }

  implicit def LazyGuard(x: => Expression):CriojoGuard = {
    val g = new CriojoGuard(List()){
      def eval(vals: Valuation) = {

        val valuation = x.eval(vals)
        valuation.isInstanceOf[BooleanExpression] && valuation.asInstanceOf[BooleanExpression].getValue()
      }
    }
    g
  }

  implicit def LazyExpression(x: => Expression):Expression = {
    val g = new Expression {

      def name = x.name
      def matches(that: Term): Boolean = false

      def eval(): Expression = x.eval()

      def applyValuation(vals:Valuation): Expression = x.applyValuation(vals) match {
        case e:Expression => e
        case _ => UndefinedExpression
      }

      def getValuation(t:Term):Valuation = x.getValuation(t)
    }
    g
  }

  val H = Rel("H")
  val H2 = Rel("H2")
  val H2O = Rel("H2O")
  val O = Rel("O")
  val C = Rel("C")

  val PrintInt = NativeRelation("PrintInt"){
    case (Atom(_,x::_),_) => println(x)
  }

  val x,y = Var

  rules(
    (H() & H()) --> Proba(0.5f) ?: H2(),
    H2() --> Proba(0.01f) ?: (H() & H()),
    (H2() & O()) --> Proba(0.5f) ?: (H2O()),
    (H2O() & C()) --> Proba(0.5f) ?: (C() & H() & H() & O()),
    H2O() --> Proba (0.01f) ?: (H() & H() & O())
  )
}
