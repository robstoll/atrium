package ch.tutteli.atrium.api.fluent.en_GB.scala

import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KClass
import TestUtils._
import ch.tutteli.atrium.specs.integration.AnyAssertionsSpec.{SubType, SuperType}

//noinspection TypeAnnotation
object AnyAssertionsSpec {
  def toBe[T]: Fun1[T, T] = fun1("toBe", _.toBe(_))
  def notToBe[T]: Fun1[T, T] = fun1("notToBe", _.notToBe(_))
  def isSameAs[T]: Fun1[T, T] = fun1("isSameAs", _.isSameAs(_))
  def isNotSameAs[T]: Fun1[T, T] = fun1("isNotSameAs", _.isNotSameAs(_))
  def toBeNullIfNullGivenElse[T](
      implicit kClass: KClass[T]
  ): Fun1[T, KFun1[_ >: Expect[T], KUnit]] =
    fun1("toBeNullIfNullGivenElse", (e, c) => e.toBeNullIfNullGivenElse(c.asInstanceOf[KAssertionCreator[T]]))

  def notToBeNull[T](
      implicit kClass: KClass[T]
  ): Fun1[T, KFun1[_ >: Expect[T], KUnit]] =
    fun1("notToBeNull", (e, c) => e.notToBeNull(c.asInstanceOf[KAssertionCreator[T]]))
}

import AnyAssertionsSpec._

class AnyAssertionsSpec
    extends ch.tutteli.atrium.specs.integration.AnyAssertionsSpec(
      toBe,
      toBe,
      toBe,
      toBe,
      notToBe,
      notToBe,
      notToBe,
      notToBe,
      isSameAs,
      isSameAs,
      isSameAs,
      isSameAs,
      isNotSameAs,
      isNotSameAs,
      isNotSameAs,
      isNotSameAs,
      fun0("toBe", _.toBe(null)),
      toBeNullIfNullGivenElse[Integer],
      new kotlin.Pair("isA (feature)", (e: Expect[_]) => e.isA()),
      new kotlin.Pair("isA", (e: Expect[_], c) => e.isA(c.asInstanceOf[KAssertionCreator[Integer]])),
      new kotlin.Pair("isA (feature)", (e: Expect[_]) => e.isA()),
      new kotlin.Pair("isA", (e: Expect[_], c) => e.isA(c.asInstanceOf[KAssertionCreator[SuperType]])),
      new kotlin.Pair("isA (feature)", (e: Expect[_]) => e.isA()),
      new kotlin.Pair("isA", (e: Expect[_], c) => e.isA(c.asInstanceOf[KAssertionCreator[SubType]])),
      new kotlin.Pair("notToBeNull (feature)", _.notToBeNull()),
      notToBeNull[Integer],
      fun0("and", _.and),
      fun1("and with creator", (e, c) => e.and(c.asInstanceOf[KAssertionCreator[Integer]])),
      "[Atrium] "
    )
