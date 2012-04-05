package fr.emn.criojo.actors.PraticalTest.dao

import model.Picture

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 4/5/12
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */

class MockFlickrDao extends DaoTrait {

  def connect(login:String, password:String) {

  }

  def getPictures():List[Picture] = {
    var result = List[Picture]()

    result = new Picture("http://helloworld1.jpg", "picture1") :: result
    result = new Picture("http://helloworld2.jpg", "picture2") :: result
    result = new Picture("http://helloworld3.jpg", "picture3") :: result
    result = new Picture("http://helloworld4.jpg", "picture4") :: result
    result = new Picture("http://helloworld5.jpg", "picture5") :: result
    result = new Picture("http://helloworld6.jpg", "picture6") :: result
    result = new Picture("http://helloworld7.jpg", "picture7") :: result
    result = new Picture("http://helloworld8.jpg", "picture8") :: result
    result = new Picture("http://helloworld9.jpg", "picture9") :: result

    result
  }
}
