package fr.emn.criojo.expression.map

/** Exception thrown on un-existing key in map */
class NoSuchKeyException(keyName: String)
  extends NoSuchElementException(keyName + " is not a key in map") {
}
