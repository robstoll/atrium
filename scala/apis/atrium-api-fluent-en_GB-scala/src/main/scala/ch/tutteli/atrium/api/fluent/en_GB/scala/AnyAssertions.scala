package ch.tutteli.atrium.api.fluent.en_GB.scala

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder
import kotlin.reflect.KClass

class AnyAssertions[T](expect: Expect[T]) {

  def toBe(expected: T): Expect[T] = addAssertion(_.toBe(expect, expected))

  def notToBe(expected: T): Expect[T] =
    addAssertion(_.notToBe(expect, expected))

  def isSameAs(expected: T): Expect[T] =
    addAssertion(_.isSame(expect, expected))

  def isNotSameAs(expected: T): Expect[T] =
    addAssertion(_.isNotSame(expect, expected))

  def toBeNullIfNullGivenElse(assertionCreatorOrNull: Expect[T] => Unit)(implicit kClass: KClass[T]) =
    addAssertion(_.toBeNullIfNullGivenElse[T](expect, kClass, assertionCreatorOrNull))

  def notToBeNull()(implicit kClass: KClass[T]): Expect[T] = isA[T]()

  def notToBeNull(assertionCreator: Expect[T] => Unit)(implicit kClass: KClass[T]): Expect[T] =
    isA[T](assertionCreator)

  def isA[TSub]()(implicit kClass: KClass[TSub]): Expect[TSub] =
    ExpectImpl.INSTANCE.getAny.isA(expect, kClass).getExpectOfFeature

  def isA[TSub](
      assertionCreator: Expect[TSub] => Unit
  )(implicit kClass: KClass[TSub]): Expect[TSub] =
    ExpectImpl.INSTANCE.getAny.isA(expect, kClass).addToFeature(assertionCreator)

  val and: Expect[T] = expect
  def and(assertionCreator: Expect[T] => Unit): Expect[T] = expect.addAssertionsCreatedBy(assertionCreator)

  private def addAssertion(f: AnyAssertionsBuilder => Assertion): Expect[T] =
    expect.addAssertion(f(ExpectImpl.INSTANCE.getAny))
}
