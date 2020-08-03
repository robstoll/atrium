package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.logic._

class AnyLogic[T](container: AssertionContainer[T]) {
  def toBe(expected: T): Assertion = AnyKt.toBe(container, expected)
  def notToBe(expected: T): Assertion = AnyKt.notToBe(container, expected)
  def isSameAs(expected: T): Assertion = AnyKt.isSameAs(container, expected)
  def isNotSameAs(expected: T): Assertion = AnyKt.isNotSameAs(container, expected)
}
