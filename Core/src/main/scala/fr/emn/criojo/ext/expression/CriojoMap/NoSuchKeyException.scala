package fr.emn.criojo.ext.expression.CriojoMap

/** Exception thrown on un-existing key in CriojoMap */
class NoSuchKeyException(keyName: String)
  extends NoSuchElementException(keyName + " is not a key in CriojoMap") {
}
