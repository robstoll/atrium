package ch.tutteli.atrium.scala2.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.PostFinalStep
trait BaseAssertions {
  implicit class RxPostFinalStep[T, R, E <: Expect[R]](postStep: PostFinalStep[T, R, E]) {
    @inline def expectOfFeature: E = postStep.getExpectOfFeature
  }
}
