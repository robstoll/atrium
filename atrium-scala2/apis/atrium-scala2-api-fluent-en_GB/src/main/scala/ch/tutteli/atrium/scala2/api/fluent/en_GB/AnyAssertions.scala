package ch.tutteli.atrium.scala2.api.fluent.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder
import ch.tutteli.atrium.scala2.logic._

class AnyAssertions[T](override val expect: Expect[T]) extends BaseAssertions[T] {
  def toBe(expected: T): Expect[T] = _logicAppend(_.toBe(expected))

  def notToBe(expected: T): Expect[T] = _logicAppend(_.notToBe(expected))

  def isSameAs(expected: T): Expect[T] = _logicAppend(_.isSameAs(expected))

  def isNotSameAs(expected: T): Expect[T] = _logicAppend(_.isNotSameAs(expected))

  def toBeNullIfNullGivenElse(assertionCreatorOrNull: Expect[T] => Unit)(implicit kClass: KClassTag[T]): Expect[T] =
    _logicAppend(_.toBeNullIfNullGivenElse[T](kClass.kClass, assertionCreatorOrNull))

  def notToBeNull()(implicit kClass: KClassTag[T]): Expect[T] = _logic.notToBeNull().getExpectOfFeature

  def notToBeNull(assertionCreator: Expect[T] => Unit)(implicit kClass: KClassTag[T]): Expect[T] =
    _logic.notToBeNull(assertionCreator)

  def isA[TSub]()(implicit kClass: KClassTag[TSub]): Expect[TSub] =
    _logic.isA(kClass.kClass).getExpectOfFeature

  def isA[TSub](assertionCreator: Expect[TSub] => Unit)(implicit kClass: KClassTag[TSub]): Expect[TSub] =
    _logic.isA(expect, kClass.kClass).addToFeature(assertionCreator)

  val and: Expect[T] = expect
  def and(assertionCreator: Expect[T] => Unit): Expect[T] = expect.addAssertionsCreatedBy(assertionCreator)
}
