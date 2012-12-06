package scala.fr.emn.criojo.network

//import fr.emn.criojo.core.{Engine, Atom, LocalReactionStrategy}
//import fr.emn.criojo.ext.expression.Relation.constructor.{OutChannel, Channel, LocalRelation}
//import fr.emn.criojo.network.ActorCham


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 6/6/12
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */

//class NetworkReactionStrategy extends LocalReactionStrategy {
//
//  def chamUrlFromChannelUrl(url:String):String = {
//    url.substring(0, url.lastIndexOf("."))
//  }
//
//  override def applyReaction(engine:Engine, atom:Atom) {
//    atom.relation match {
//      case l:LocalRelation => super.applyReaction(engine, atom)
//      case k:Channel => super.applyReaction(engine, atom)
//      case k:OutChannel => engine match {
//        case a:ActorCham => {
//          a.send(chamUrlFromChannelUrl(k.location.url), atom)
//        }
//        case _ =>
//      }
//    }
//  }
//}
