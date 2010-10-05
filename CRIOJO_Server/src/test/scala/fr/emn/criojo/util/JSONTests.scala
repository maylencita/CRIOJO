package fr.emn.criojo.util

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 27, 2010
 * Time: 5:42:42 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.model._
import fr.emn.creole.core._
import fr.emn.creole.ext._
import fr.emn.creole.util.Logger._

import junit.framework._;
import Assert._;

class JSONTests extends TestCase("json"){

  def testSerialize{
    val atom = Atom("R", Variable("x"), Value("Mayleen"), Value(23), Null)

    val serialized = JSONUtil.serialize(new WebAtom(atom))

    info(this.getClass, "testSerialize", serialized)
    assertEquals("""{"relName":"R","vlst":["""+
    """{"name":"x","relation":null,"typ":null,"value":null},"""+
    """{"name":"Mayleen","relation":null,"typ":"String","value":"Mayleen"},"""+
    """{"name":"23","relation":null,"typ":"Int","value":"23"},"""+
    """{"name":"_","relation":null,"typ":"Null","value":null}]}""", serialized)
  }

  def testDeserialize{
/*
    var jatom= """{"relName":"R","vlst":["""+
    """{"name":"x","relation":null,"typ":null,"value":null},"""+
    """{"name":"Mayleen","relation":null,"typ":"String","value":"Mayleen"},"""+
    """{"name":"23","relation":null,"typ":"Int","value":"23"},"""+
    """{"name":"_","relation":null,"typ":"Null","value":null}]}"""

    val atom = Atom("R", Variable("x"), Value("Mayleen"), Value(23), Null)

    Json2Criojo.parseAtom(jatom) match{
      case Some(a) =>
        assertEquals(atom.relName, a.relName)
        assertEquals(atom.vars.size, a.vars.size)
        assertTrue("Exepcted vars: " + atom.vars +". But got " + a.vars, atom.vars sameElements a.vars)
      case _ => fail
    }
*/    
  }
}