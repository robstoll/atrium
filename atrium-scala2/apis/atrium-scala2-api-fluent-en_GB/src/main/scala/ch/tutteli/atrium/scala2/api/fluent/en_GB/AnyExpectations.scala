package ch.tutteli.atrium.scala2.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.{AnyKt, LogicKt}

class AnyExpectations[T](override val expect: Expect[T]) extends BaseExpectations[T] {

  def toBe(expected: T): Expect[T] =
    addAssertion(c => AnyKt.toBe(c, expected))

  def notToBe(expected: T): Expect[T] =
    addAssertion(c => AnyKt.notToBe(c, expected))

  def isSameAs(expected: T): Expect[T] =
    addAssertion(c => AnyKt.isSameAs(c, expected))

  def isNotSameAs(expected: T): Expect[T] =
    addAssertion(c => AnyKt.isNotSameAs(c, expected))

  def toBeNullIfNullGivenElse(assertionCreatorOrNull: Expect[T] => Unit): Expect[T] =
    addAssertion(c => AnyKt.toBeNullIfNullGivenElse(c, assertionCreatorOrNull))

  def notToBeNull()(implicit kClass: KClassTag[T]): Expect[T] = isA[T]()

  def notToBeNull(assertionCreator: Expect[T] => Unit)(implicit kClass: KClassTag[T]): Expect[T] =
    isA[T](assertionCreator)

  def isA[TSub]()(implicit kClass: KClassTag[TSub]): Expect[TSub] =
    AnyKt.isA(LogicKt.get_logic(expect), kClass.kClass).transform()

  def isA[TSub](assertionCreator: Expect[TSub] => Unit)(implicit kClass: KClassTag[TSub]): Expect[TSub] =
    AnyKt.isA(LogicKt.get_logic(expect), kClass.kClass).transformAndAppend(assertionCreator)

  val and: Expect[T] = expect
  def and(assertionCreator: Expect[T] => Unit): Expect[T] = expect.addAssertionsCreatedBy(assertionCreator)

  def isNoneOf(expected: T, otherValues: T*): Expect[T] = {
    isNotIn(expected +: otherValues)
  }

  def isNotIn(iterable: Iterable[T]): Expect[T] = {
    import scala.jdk.CollectionConverters._
    addAssertion(c => AnyKt.isNotIn(c, iterable.asJava))
  }

  def because(reason: String, assertionCreator: Expect[T] => Unit): Expect[T] =
    addAssertion(c => AnyKt.because(c, reason, assertionCreator))
}
