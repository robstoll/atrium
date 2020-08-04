package ch.tutteli.atrium.scala2.api.fluent

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import _root_.scala.annotation.unchecked.uncheckedVariance

package object en_GB {
  import IsIterableHelpers._

  implicit def expectToAnyAssertions[T](expect: Expect[T]): AnyAssertions[T] =
    new AnyAssertions(expect)

  implicit def expectToIterableLikeElementComparableAssertions[Repr, E <: Comparable[E]](expect: Expect[Repr])(
      implicit it: IsIterableFixA[Repr, E]
  ): IterableLikeElementComparableAssertions[Repr, E] = new IterableLikeElementComparableAssertions(expect)
}
