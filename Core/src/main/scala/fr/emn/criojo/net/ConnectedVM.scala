package fr.emn.criojo.net

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 1, 2010
 * Time: 2:48:32 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._
import fr.emn.criojo.ext.{VirtualMachine,RemoteRelation}
import fr.emn.criojo.util.Logger._
//import fr.emn.criojo.util.ConfigProperties._
import fr.emn.criojo.util.json._
import Creole._

//import fr.emn.criojo.model.RemoteRelationImpl

import java.net.URI
import javax.ws.rs.core.{UriBuilder,MediaType}
import java.util.logging.Logger
import java.util.logging.Level //._

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.config.DefaultClientConfig

//case class PublicRelation(override val name:String, url:URI) extends LocalRelation(name, true)
trait PublicRelation extends Relation{
  def url:URI
}

class ConnectedVM(val vmUrl:URI) extends VirtualMachine{
 assert(vmUrl != null)

//  type JWARNING = Level.WARNING
//  type JSEVERE = Level.SEVERE

  val owner = this
  val logger = Logger.getLogger(this.getClass.getName())

  val solution = Solution()

//  def loadSolution{
//    solution match{
//      case s:CachedSolution => s.load
//      case _ => //Skip
//    }
//  }

  def addAtom(a:Atom){
    var nuAtoms = List[Atom]()
    var nuVars = List[Variable]()
    a.vars foreach{
      case Value(v) =>
        val nuVar = Variable("y@"+newId)
        nuVars :+= nuVar
        v match{
          case n:Int => nuAtoms :+= Atom(IntRel.name, Variable(n.toString), nuVar)
          case s:String => nuAtoms :+= Atom(StrRel.name, Variable(s), nuVar)
          case null => nuAtoms :+= Atom(NullRel.name, nuVar )
        }
      case v => nuVars :+= v
    }
    val a2=new Atom(a.relName, nuVars); a2.relation = a.relation
//    nuAtoms :+= a2

    nuAtoms.foreach(introduceAtom(_))
    introduceAtom(a2)
  }

  def newLocalRelation(name:String,public:Boolean):LocalRelation = {
    Provided(name)
  }

  def newRemoteRelation(remoteName:String,remoteUrl:String):RemoteRelation = {
    val uri = UriBuilder.fromUri(remoteUrl).build()
    val r = new RemoteRelationImpl(remoteName,uri)
    //addRelation(r)
    r
  }

  def Provided(relName:String):Rel = new Rel(relName) with PublicRelation{val url = vmUrl}

  implicit def intToVariable(num:Int):Variable = Value(num)
  implicit def strToVariable(str:String):Variable = Value(str)

  @serializable
  class RemoteRelationImpl(val name:String,val url:URI) extends RemoteRelation{
    val myclient:Client = Client.create(new DefaultClientConfig)
    var observers:List[RelationObserver] = List()

    def addObserver(observer:RelationObserver){
      observers :+= observer
    }

    override def notifyObservers(a: Atom){
      val subs = a.vars.map{
        v => getValue(v) match{
          case value:Value[_] => (v, value)
          case NoValue => (v,v)
        }
      }

      log(this.getClass,"notifyObservers"," subs= " + subs)

      val newAtom = a.applySubstitutions(subs)

//      observers.foreach(o => o.receiveUpdate(newAtom))
      if (observers.isEmpty){
        exportAtom(newAtom)//, url)
//        log(this.getClass,"notifyObservers"," Atom " + newAtom + " exported to " + url)
        log(this.getClass,"notifyObservers"," Solution after export: " + solution)
      }else{
        observers.foreach(o => o.receiveUpdate(newAtom))
      }
    }

    def exportAtom(atom:Atom){//, url:URI){
      val myservice = myclient.resource(url).path(atom.relName)
//      val content = JSONUtil.serialize(new WebAtom(atom))
      val content = JSONUtil.serialize(new AtomList("", List(atom)))

      try{
//        info(this.getClass, "exportAtom","Sending: " + content)
        logger.info("Sending: " + content)
        val resp:String = myservice.
                header("X-Client-URL", vmUrl.toString).
                entity(content.getBytes, MediaType.APPLICATION_JSON_TYPE).
                post(classOf[String])
//        info(this.getClass, "exportAtom","Response: " + resp)
        logger.info("Response: " + resp)
        if (resp.startsWith("{\"atoms\"")){
          Json2Criojo(owner).parseAtomList(new String(resp)) match{
            case lst:List[Atom] =>
              lst.foreach(addAtom(_))
//              logger.info("Atoms " + lst + " successfully added.")
//              logger.info("New solution: " + prettyPrint)
            case _ => log(WARNING, this.getClass, "exportAtom", "Received empty list.")
          }
        }
      }catch{
        case e =>
          logger.log(Level.SEVERE, "Error exportin atom: " + e)
//          error(this.getClass, "exportAtom","Error: " + e)
//          e.printStackTrace()
      }
    }

    override def toString = name + "@" + url
  }

  case class Required(n:String,u:String) extends RemoteRelationImpl(n,UriBuilder.fromUri(u).build()){
    addRelation(this)
    def apply(vlst:Variable*):Atom = new Atom(name, vlst.toList)
  }

}