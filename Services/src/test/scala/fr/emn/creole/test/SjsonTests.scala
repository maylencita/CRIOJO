package fr.emn.creole.test

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 16, 2010
 * Time: 2:32:38 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.creole.model._
import fr.emn.creole.util._

import junit.framework._;
import Assert._;
//import dispatch.json.jsObject

import sjson.json.Serializer
import sjson.json.Serializer.SJSON

object SjsonTests {
  def suite: Test = {
      val suite = new TestSuite(classOf[SjsonTests]);
      suite
  }

  def main(args : Array[String]) {
      junit.textui.TestRunner.run(suite);
  }  
}

class SjsonTests extends TestCase("sjson"){// extends Application{

    def testVar{
      val v = new WebVariable("X", null)
      println("Variable: " + v)

      val after = JSONUtil.serialize(v)
      println("after serialization: " + after)

      val back = JSONUtil.deserialize[WebVariable](after.getBytes)
      println("after deserialized: " + back)

      assertTrue(back equals v)
    }

    def testVarList = {
      val atom1 = VarList(List(WebVariable("X", WebRelation("X","http://localhost:9999")), new WebVariable("a", null)))

      val a = JSONUtil.serialize(atom1)

      println("before: " + atom1)
      println("after: " + a)

      val post = JSONUtil.deserialize[VarList](a.getBytes)
  //      match{
  //        case lst:List[_] => lst.map(item => Serializer.SJSON.in[WebVariable](item.toString))
  //        case _ => List()
  //      }
      println("post: " + post)

      assertTrue(atom1 equals(post))
    }

}