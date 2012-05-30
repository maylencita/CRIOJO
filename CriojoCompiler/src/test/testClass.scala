/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/29/12
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test

case class Post(s1:String, s2:String) {

}

class testClass {

  @Test
  def main() {
    val truc:List[Post] = List(Post("1","jonathan"), Post("2","admin"), Post("3","jonathan"), Post("4","admin"))

    val r = for {
      o <- truc
      if (o match {
        case Post(texte,user) => user=="jonathan"
      })
    } yield o
    println(r)
  }
}
