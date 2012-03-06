package fr.emn.criojo.net

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 1, 2010
 * Time: 2:48:32 PM
 * To change this template use File | Settings | File Templates.
 */

// TODO Needs update
import fr.emn.criojo.core._
import fr.emn.criojo.util.Logger._
import fr.emn.criojo.util.json._
import Criojo._

import java.net.URI
import javax.ws.rs.core.{UriBuilder,MediaType}
import java.util.logging.Logger
import java.util.logging.Level

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.config.DefaultClientConfig
import fr.emn.criojo.ext.{ExtendedCHAM}

trait PublicRelation extends Relation{
  def url:URI
}

class ConnectedCHAM(val vmUrl:URI) extends ExtendedCHAM{
 assert(vmUrl != null)

  val owner = this
  val logger = Logger.getLogger(this.getClass.getName)

  def addAtom(a:Atom){
//    var nuAtoms = List[Atom]()
//    var nuVars = List[Term]()
//    a.terms foreach{
//      case ValueTerm(v) =>
//        val nuVar = Variable("y@"+newId)
//        nuVars :+= nuVar
//        v match{
//          case n:Int => nuAtoms :+= IntAtom(n, nuVar) //Atom(IntRel.name, Variable(n.toString), nuVar)
//          case s:String => nuAtoms :+= Atom(StrVal.name, Variable(s), nuVar)
//          case null => nuAtoms :+= Atom(NullRel.name, nuVar )
//        }
//      case v => nuVars :+= v
//    }
//    val a2=new Atom(a.relName, nuVars); a2.relation = a.relation
//
//    nuAtoms.foreach(introduceAtom(_))
//    introduceAtom(a2)
  }

  override def newLocalRelation(name:String,public:Boolean):LocalRelation = {
    new LocalRelation(name) with PublicRelation{val url = vmUrl}
  }

  override def newRemoteRelation(remoteName:String,remoteUrl:String):RemoteRelation = {
    val uri = UriBuilder.fromUri(remoteUrl).build()
    val r = new RemoteRelationImpl(remoteName,uri)
    r
  }

  def Provided(relName:String) = {
    new LocalRelation(relName) with PublicRelation{val url = vmUrl}
  }

//  implicit def intToVariable(num:Int):Variable = ValueTerm(num)
//  implicit def strToVariable(str:String):Variable = ValueTerm(str)

  @serializable
  class RemoteRelationImpl(val name:String,val url:URI) extends RemoteRelation{
    val myclient:Client = Client.create(new DefaultClientConfig)
    var observers:List[RelationObserver] = List()

    def addObserver(observer:RelationObserver){
      observers :+= observer
    }

    override def notifyObservers(a: Atom){
      val vals:Valuation = a.vars.map{
        v => getValue(v) match{
          case value:ValueTerm[_] => (v, value)
          case NoValue => (v,v)
        }
      }.filterNot(_._1 == Undef).toSet

      log(this.getClass,"notifyObservers"," subs= " + vals)

      val newAtom = a.applySubstitutions(vals)

      if (observers.isEmpty){
        exportAtom(newAtom)
        log(this.getClass,"notifyObservers"," Solution after export: " + solution)
      }else{
        observers.foreach(o => o.receiveUpdate(newAtom))
      }
    }

    def exportAtom(atom:Atom){
//      val myservice = myclient.resource(url).path(atom.relName)
//      val content = JSONUtil.serialize(new AtomList("", List(atom)))
//
//      try{
//        logger.info("Sending: " + content)
//        val resp:String = myservice.
//                header("X-Client-URL", vmUrl.toString).
//                entity(content.getBytes, MediaType.APPLICATION_JSON_TYPE).
//                post(classOf[String])
//        logger.info("Response: " + resp)
//        if (resp.startsWith("{\"atoms\"")){
//          Json2Criojo(owner).parseAtomList(new String(resp)) match{
//            case lst:List[Atom] =>
//              lst.foreach(addAtom(_))
//            case _ => log(WARNING, this.getClass, "exportAtom", "Received empty list.")
//          }
//        }
//      }catch{
//        case e =>
//          logger.log(Level.SEVERE, "Error exportin atom: " + e)
//      }
    }

    def copy(sol:Solution) = new RemoteRelationImpl(name, url)

    override def toString = name + "@" + url
  }

  case class Required(n:String,u:String) extends RemoteRelationImpl(n,UriBuilder.fromUri(u).build()){
    addRelation(this)
    def apply(vlst:Variable*):Atom = new Atom(name, vlst.toList)
  }

}