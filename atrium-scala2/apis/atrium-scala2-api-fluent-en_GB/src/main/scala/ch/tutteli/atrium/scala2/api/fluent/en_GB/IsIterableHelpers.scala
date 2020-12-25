package ch.tutteli.atrium.scala2.api.fluent.en_GB

import scala.collection.generic.IsIterable
import ch.tutteli.atrium.creating.Expect

import java.lang.{Iterable => JIterable}
import scala.jdk.CollectionConverters._
import ch.tutteli.atrium.logic.{LogicKt, UtilsKt}

object IsIterableHelpers {
  type IsIterableFixA[Repr, A0] = IsIterable[Repr] { type A = A0 }
}
