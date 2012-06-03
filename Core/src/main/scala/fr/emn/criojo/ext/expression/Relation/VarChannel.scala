package fr.emn.criojo.ext.expression.Relation

import fr.emn.criojo.core.datatype.Var

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 6/1/12
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */

/** Channel Variable */
case class VarChannel(n: String) extends Var[ChannelLocation](n)
with Relation {
}

object VarChannel {
  private var instanceNum = 0

  def apply() = new VarChannel("VarChannel@" + getInstanceNum)

  private def getInstanceNum() = {
    instanceNum += 1
    instanceNum
  }
}