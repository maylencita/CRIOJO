package fr.emn.criojo.core.datatype

class ProhibitedOperation(s: String) extends
    SecurityException(if (s.isEmpty) "Prohibited Operation"
      else "Prohibited Operation: " + s) {
  def this() = this("")
}
