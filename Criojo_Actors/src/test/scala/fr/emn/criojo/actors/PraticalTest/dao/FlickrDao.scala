package fr.emn.criojo.actors.PraticalTest.dao

import com.aetrion.flickr.{REST, Flickr}
import java.util.{ArrayList, Properties}
import model.Picture

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 4/5/12
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */

class FlickrDao extends DaoTrait {

  var properties:Properties = null

  def connect(login:String, password:String) {

  }

  def getPictures():List[Picture] = {
    List[Picture]()
  }
}
