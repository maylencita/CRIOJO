package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 1, 2010
 * Time: 3:08:58 PM
 * To change this template use File | Settings | File Templates.
 */

import java.net.URI

class LocalVM extends VirtualMachine{
  def newRemoteRelation(remoteName:String,url:String):RemoteRelation = {
    throw new IllegalAccessException("Local machine virtual.")
  }
}