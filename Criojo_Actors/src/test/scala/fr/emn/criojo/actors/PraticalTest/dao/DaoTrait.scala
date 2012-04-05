package fr.emn.criojo.actors.PraticalTest.dao

import model.Picture

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 4/5/12
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */

trait DaoTrait {

  def connect(login:String, password:String)
  def getPictures():List[Picture]
}
