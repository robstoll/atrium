package ch.tutteli.atrium.api.fluent.en_GB.scala
import java.lang.{Iterable => JIterable}

import ch.tutteli.atrium.api.fluent.en_GB.scala.IsIterableHelpers._
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.IterableAssertionsBuilder

class IterableLikeElementComparableAssertions[Repr, E <: Comparable[E]](expect: Expect[Repr])(
    implicit isIterable: IsIterableFixA[Repr, E]
) extends BaseAssertions {
  private val expectJ: Expect[JIterable[E]] = expect.asExpectJIterable

  @inline private def iterableAssertions: IterableAssertionsBuilder = ExpectImpl.getIterable

  def min(): Expect[E] = iterableAssertions.min[E, JIterable[E]](expectJ).expectOfFeature
  def max(): Expect[E] = iterableAssertions.max[E, JIterable[E]](expectJ).expectOfFeature

  def min(assertionCreator: Expect[E] => Unit): Expect[Repr] =
    iterableAssertions.min[E, JIterable[E]](expectJ).addToInitial(assertionCreator).backToScalaRepr(expect)

  def max(assertionCreator: Expect[E] => Unit): Expect[Repr] =
    iterableAssertions.max[E, JIterable[E]](expectJ).addToInitial(assertionCreator).backToScalaRepr(expect)
}
