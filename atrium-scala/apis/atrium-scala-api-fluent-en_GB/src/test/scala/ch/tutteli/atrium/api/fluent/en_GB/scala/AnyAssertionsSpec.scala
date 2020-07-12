package ch.tutteli.atrium.api.fluent.en_GB.scala

import TestUtils._
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
      fun1("toBeNullIfNullGivenElse", _.toBeNullIfNullGivenElse(_)),
      new kotlin.Pair("isA (feature)", _.isA()),
      new kotlin.Pair("isA", _.isA(_)),
      new kotlin.Pair("isA (feature)", _.isA()),
      new kotlin.Pair("isA", _.isA(_)),
      new kotlin.Pair("isA (feature)", _.isA()),
      new kotlin.Pair("isA", _.isA(_)),
      new kotlin.Pair("notToBeNull (feature)", _.notToBeNull()),
      fun1("notToBeNull", _.notToBeNull(_)),
      fun0("and (feature)", _.and),
      fun1("and", _.and(_)),
      "[Atrium] "
    )

//noinspection TypeAnnotation
object AnyAssertionsSpec {
  def toBe[T]: Fun1[T, T] = fun1("toBe", _.toBe(_))
  def notToBe[T]: Fun1[T, T] = fun1("notToBe", _.notToBe(_))
  def isSameAs[T]: Fun1[T, T] = fun1("isSameAs", _.isSameAs(_))
  def isNotSameAs[T]: Fun1[T, T] = fun1("isNotSameAs", _.isNotSameAs(_))
}
