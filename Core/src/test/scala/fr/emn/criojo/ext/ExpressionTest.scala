import fr.emn.criojo.ext.debug.DebugCham
import fr.emn.criojo.ext.expression.Relation.constructor.{Channel, OutChannel}
import fr.emn.criojo.ext.expression.Relation.{Relation, VarChannel}
import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.core.Converters._
import fr.emn.criojo.lang.Cham
import org.junit.Test

class ExpressionTest {

  @Test
  def channelMobility() {



    val a = new Cham with IntegerCham with DebugCham {
      var cpt:Int = 0

      var aTotoken:OutChannel = new OutChannel("a.token")
      var token:Channel = new Channel("token")
      var ack:Channel = new Channel("ack")
      var ackV:VarChannel = VarChannel("ackV")
      rules(
        (token(ackV)) --> (ackV()&aTotoken(ack))
        ,(ack()) --> (NativeRel { case (Nil) => {cpt=cpt+1;println(cpt)}; case _ => }())
      )

//      var truc:Relation = ackV.getValuation(ack).get(ackV)._2
//      println(truc)
//      truc = ackV.getValuation(aTotoken).get(ackV)._2
//      println(truc)
    }
    a.enableSolutionTrace()
    a.enableStreamingTrace()

    a.introduceMolecule(a.token(a.ack))
    a.executeRules()
  }
}