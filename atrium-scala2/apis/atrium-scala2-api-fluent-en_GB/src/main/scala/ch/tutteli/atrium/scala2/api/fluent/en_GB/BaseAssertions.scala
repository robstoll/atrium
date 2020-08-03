package ch.tutteli.atrium.scala2.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.PostFinalStep
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.LogicKt
import ch.tutteli.atrium.assertions.Assertion

trait BaseAssertions[T] {

  val expect: Expect[T]

  protected implicit class RxPostFinalStep[T, R, E <: Expect[R]](postStep: PostFinalStep[T, R, E]) {
    @inline def expectOfFeature: E = postStep.getExpectOfFeature
  }

  protected def _logicAppend(factory: AssertionContainer[T] => Assertion): Expect[T] =
      expect.addAssertion(factory(_logic))

  protected val _logic: AssertionContainer[T] = LogicKt.get_logic(expect)
}
