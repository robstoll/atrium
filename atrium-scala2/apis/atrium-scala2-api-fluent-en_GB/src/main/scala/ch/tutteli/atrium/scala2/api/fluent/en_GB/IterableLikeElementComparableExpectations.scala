package ch.tutteli.atrium.scala2.api.fluent.en_GB

import java.lang.{Iterable => JIterable}
import IsIterableHelpers._
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.{IterableLikeKt, LogicKt}

class IterableLikeElementComparableExpectations[Repr, E <: Comparable[E]](override val expect: Expect[Repr])(
    implicit isIterable: IsIterableFixA[Repr, E]
) extends BaseExpectations[Repr] {
  import scala.jdk.CollectionConverters._

  private def convertToJIterable(repr: Repr): JIterable[E] = {
    val subject = isIterable.apply(repr)
    subject match {
      case l: List[E]     => l.asJava
      case i: Iterable[E] => i.asJava
      case subject        => subject.toList.asJava
    }
  }

  def min(): Expect[E] =
    IterableLikeKt.min(LogicKt.get_logic(expect), convertToJIterable).transform()

  def max(): Expect[E] =
    IterableLikeKt.max(LogicKt.get_logic(expect), convertToJIterable).transform()

  def min(assertionCreator: Expect[E] => Unit): Expect[Repr] =
    IterableLikeKt.min(LogicKt.get_logic(expect), convertToJIterable).collectAndAppend(assertionCreator)

  def max(assertionCreator: Expect[E] => Unit): Expect[Repr] =
    IterableLikeKt.max(LogicKt.get_logic(expect), convertToJIterable).collectAndAppend(assertionCreator)
}
