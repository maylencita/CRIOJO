package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 04/03/12
 * Time: 19:04
 */

/**
 * Companion object
 */
object Valuation{
  def apply():Valuation =  TopValuation
  def apply(elems: (Variable,Term)*):Valuation = new MapValuation(elems.map(p=>new Assignment(p._1,p._2)).toSet)

  implicit def pair2Assg(p:(Variable,Term)):Assignment = new Assignment(p._1,p._2)
}

class Assignment (val variable:Variable,val value:Term,s:Boolean) extends Pair(variable,value){
  def this(x:Variable,v:Term)={
    this(x,v,true)
  }
  def apply(v:Variable):Boolean = v == variable
  def sign:Boolean = s
  def unary_! : Assignment  = new Assignment(variable,value,!sign)

  override def equals(that:Any):Boolean = that match{
    case v2:Assignment => this.variable == v2.variable &&
      this.sign == v2.sign &&
      this.value == v2.value
  }

  override def toString =
    variable+ (if(!s) "!=" else "=") +value.toString
}
object NullAssignment extends Assignment(Undef,Undef){}

/**
 * valuation := Top
 *            | Bottom
 *            | (x -> v) valuation
 */
trait Valuation{
  def sign:Boolean
  def unary_! : Valuation
  def isEmpty:Boolean
  def size:Int
  def domain:Set[Variable]
  def keyValues:Set[Assignment]
  def apply(key:Variable):Term
  def get(key:Variable):Assignment
  def union(that:Valuation):Valuation
  def intersect(that:Valuation):Valuation
  def sameElements(that:Valuation):Boolean

  /**
   * Evaluates if that is an extension of this Valuation
   * @param that
   * @return
   */
  def hasExtension(that:Valuation):Boolean
  /*
Methods to avoid compilation errors while Valuation is adopted in all the code
  */
  def find(p:((Variable,Term)) => Boolean) = keyValues.find(p)
  def forall(p:((Variable,Term)) => Boolean) = keyValues.forall(p)
  def toSet = Set()
}

abstract
class EmptyVal extends Valuation
{
  def head = throw new NoSuchElementException("head of empty valuation")
  def tail = throw new NoSuchElementException("head of empty valuation")
  def size = 0

  override def apply(key:Variable) = Undef
  def get(key:Variable):Assignment = NullAssignment

  def domain = Set()

  def keyValues = Set()
}

object TopValuation extends EmptyVal{
  override val sign = true
  override def union(that:Valuation) = that
  def intersect(that:Valuation) = that
  def isEmpty = false

  def unary_! = BottomValuation
  def sameElements(that:Valuation):Boolean = false
  def hasExtension(that:Valuation):Boolean = true

  override def toString = "T"
}

object BottomValuation extends EmptyVal{
  override val sign = false
  override def union(that:Valuation) = this
  def intersect(that:Valuation) = this
  def isEmpty = true

  def unary_! = TopValuation
  def sameElements(that:Valuation):Boolean = false
  def hasExtension(that:Valuation):Boolean = false
}

class MapValuation(kv:Set[Assignment],val sign:Boolean=true) extends Valuation{

  def keyValues = kv

  def size = keyValues.size

  def unary_! : Valuation = new MapValuation(kv.map(!_),!sign)

  def get(x:Variable):Assignment =
    kv.find(asg => asg(x)) match{
      case Some(a) => a
      case _ => NullAssignment
    }

  /**
   * Retrieves the value of the associated variable.
   * If no value exists for the given variable, it returns the Undef variable
   * @param key
   * @return
   */
  def apply(key:Variable):Term = keyValues.find(a => a(key)) match{
    case Some(assg) => assg.value
    case _ => Undef
  }

  /*
  Partial defined union:
  if this(x)=v1 and that(x)=v2,
  if v1 != v2 then the resulting valuation is empty
   */
  def union(that:Valuation):Valuation = {
    val domIntersec = this.domain & that.domain
    if (domIntersec.isEmpty || domIntersec.forall(x => this.get(x) == that.get(x))){
      new MapValuation(this.keyValues ++ that.keyValues)
    }else
      Valuation()
  }

   /**
   * Returns the domain of this valuation
   */
  def domain = keyValues.map(asg => asg.variable)

  def intersect(that:Valuation):Valuation = that match{
    case TopValuation => this
    case _ =>
      if (this.sign == that.sign)
        new MapValuation(
          for (x <- this.domain if this(x) == that(x)) yield{
            get(x)
          }
        )
      else
        new MapValuation(Set())
  }

  def sameElements(that:Valuation):Boolean = keyValues.sameElements(that.keyValues)

  def hasExtension(that:Valuation):Boolean =
    this.domain.forall{ x =>
      this.sign == (that.get(x) == this.get(x))
    }

  def isEmpty = keyValues.isEmpty

  override def toString = keyValues.mkString("(",",",")")
}

/**
 * The an element of the set of valuations in the normal form:
 * the conjunction of one positive valuation and several negative valuations
 * @param alpha the positive valuation
 * @param beta the list of negative valuations
 */
class ValGenerator(val alpha:Valuation, val beta:List[Valuation]){
  def this(a:Valuation) = {
    this(a,List())
  }
  /**
   * Evaluates if the valuation valu, is consistent with this set of valuations:
   * valu is an extension of alpha and is not an extension of any of the betas
   * @param valu
   * @return
   */
  def consistentWith(valu:Valuation):Boolean = {
    alpha.hasExtension(valu) && beta.forall(b => b.hasExtension(valu))
  }

  def intersect(that:ValGenerator):ValGenerator = {
    //TODO apply validations
    new ValGenerator(alpha.intersect(that.alpha), this.beta ++ that.beta)
  }

  def isNormalForm:Boolean = alpha.sign && beta.forall(!_.sign)

  override def toString = "{"+alpha+" ^ "+beta.mkString("^")+"}"
}

/**
 * A list of valuations that corresponds to the union of
 * multiple valuations
 * @param vlist
 */
class ValuationList(protected val vlist:List[ValGenerator]){
  def this() = {
    this(List())
  }

  def isEmpty = vlist.isEmpty

  /**
   * Tests wheter the condition p holds for at least one of its elements
   * @param p
   * @return
   */
  def exists(p:(ValGenerator) => Boolean):Boolean = {
    vlist.exists(p)
  }

  def not:ValuationList = {
    if (this.isEmpty)
      new ValuationList()
    else{
      new ValuationList(recursiveNot(this.vlist))
    }
  }

  def or(that:ValuationList):ValuationList =
    new ValuationList(this.vlist ++ that.vlist)

  def mkString(i:String,m:String,f:String)=vlist.mkString(i,m,f)

  private def recursiveNot(lst:List[ValGenerator]):List[ValGenerator] = lst match{
    case head::tail =>
      val hd = new ValGenerator(TopValuation, List(! head.alpha))
      val newBetas = for(b <- head.beta) yield{
        new ValGenerator(!b,List())
      }
      val rec = recursiveNot(tail)
      intersect(hd,rec) ++ newBetas.flatMap(b => intersect(b,rec))
    case _ => List()
  }

  private def intersect(vg:ValGenerator, lst:List[ValGenerator]):List[ValGenerator] = lst match{
    case List() => List(vg)
    case _ =>
      lst.foldLeft(List[ValGenerator]()){(l,g) =>
        val alpha = vg.alpha.intersect(g.alpha)
        if(alpha.isEmpty)
          l
        else
          new ValGenerator(alpha, vg.beta ++ g.beta) :: l
      }
  }
}
