package ch.tutteli.atrium.scala2.api.fluent.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.{AssertionContainer, Expect}
import ch.tutteli.atrium.logic.LogicKt

trait BaseExpectations[T] {
  protected val expect: Expect[T]

  @inline protected def addAssertion(f: AssertionContainer[T] => Assertion): Expect[T] =
    LogicKt._logicAppend(expect, f)
}
