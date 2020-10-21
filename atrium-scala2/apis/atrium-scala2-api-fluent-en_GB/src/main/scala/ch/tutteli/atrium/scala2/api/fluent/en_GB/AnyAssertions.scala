package ch.tutteli.atrium.scala2.api.fluent.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.AnyAssertionsBuilder
import ch.tutteli.atrium.logic.impl.DefaultAnyAssertions
import ch.tutteli.atrium.creating.AssertionContainer

class AnyAssertions[T](expect: Expect[T]) {
  @inline private def anyAssertions: AnyAssertionsBuilder = ExpectImpl.getAny

  def toBe(expected: T): Expect[T] = addAssertion(_.toBe(expect, expected))

  def notToBe(expected: T): Expect[T] =
    addAssertion(_.notToBe(expect, expected))

  def isSameAs(expected: T): Expect[T] =
    addAssertion(_.isSame(expect, expected))

  def isNotSameAs(expected: T): Expect[T] =
    addAssertion(_.isNotSame(expect, expected))

  def toBeNullIfNullGivenElse(assertionCreatorOrNull: Expect[T] => Unit)(implicit kClass: KClassTag[T]): Expect[T] =
    addAssertion(_.toBeNullIfNullGivenElse[T](expect, kClass.kClass, assertionCreatorOrNull))

  def notToBeNull()(implicit kClass: KClassTag[T]): Expect[T] = isA[T]()

  def notToBeNull(assertionCreator: Expect[T] => Unit)(implicit kClass: KClassTag[T]): Expect[T] =
    isA[T](assertionCreator)

  def isA[TSub]()(implicit kClass: KClassTag[TSub]): Expect[TSub] =
    anyAssertions.isA(expect, kClass.kClass).getExpectOfFeature

  def isA[TSub](assertionCreator: Expect[TSub] => Unit)(implicit kClass: KClassTag[TSub]): Expect[TSub] =
    anyAssertions.isA(expect, kClass.kClass).addToFeature(assertionCreator)

  val and: Expect[T] = expect
  def and(assertionCreator: Expect[T] => Unit): Expect[T] = expect.addAssertionsCreatedBy(assertionCreator)

  def isNoneOf(expected: T, otherValues: T*): Expect[T] = {
    isNotIn(expected +: otherValues)
  }

  def isNotIn(iterable: Iterable[T]): Expect[T] = {
    import scala.jdk.CollectionConverters._
    expect.addAssertion(new DefaultAnyAssertions().isNotIn(expect.asInstanceOf[AssertionContainer[T]], iterable.asJava))
  }
    
    

  @inline private def addAssertion(f: AnyAssertionsBuilder => Assertion): Expect[T] =
    expect.addAssertion(f(anyAssertions))
}
