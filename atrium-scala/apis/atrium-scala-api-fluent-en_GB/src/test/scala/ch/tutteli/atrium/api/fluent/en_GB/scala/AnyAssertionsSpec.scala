package ch.tutteli.atrium.api.fluent.en_GB.scala

import TestUtils._
import AnyAssertionsSpec._
import ch.tutteli.atrium.creating.Expect

class AnyAssertionsSpec
    extends ch.tutteli.atrium.specs.integration.AnyAssertionsSpec(
      toBe,
      toBe,
      toBe.withNullableSuffix(),
      toBe.withNullableSuffix(),
      notToBe,
      notToBe,
      notToBe.withNullableSuffix(),
      notToBe.withNullableSuffix(),
      isSameAs,
      isSameAs,
      isSameAs.withNullableSuffix(),
      isSameAs.withNullableSuffix(),
      isNotSameAs,
      isNotSameAs,
      isNotSameAs.withNullableSuffix(),
      isNotSameAs.withNullableSuffix(),
      isNoneOf,
      isNoneOf,
      isNoneOf.withNullableSuffix(),
      isNoneOf.withNullableSuffix(),
      isNotIn,
      isNotIn,
      isNotIn.withNullableSuffix(),
      isNotIn.withNullableSuffix(),

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
        "⚬ ",
      "[Atrium] "
    )

//noinspection TypeAnnotation
object AnyAssertionsSpec {
  import scala.jdk.CollectionConverters._

  def toBe[T]: Fun1[T, T] = fun1("toBe", _.toBe(_))
  def notToBe[T]: Fun1[T, T] = fun1("notToBe", _.notToBe(_))
  def isSameAs[T]: Fun1[T, T] = fun1("isSameAs", _.isSameAs(_))
  def isNotSameAs[T]: Fun1[T, T] = fun1("isNotSameAs", _.isNotSameAs(_))
  def isNoneOf[T]: Fun2[T, T, Array[T]] = fun2("isNoneOf", (e: Expect[T], t: T, arr: Array[T]) => e.isNoneOf(t, arr.toIndexedSeq: _*))
  def isNotIn[T]: Fun1[T, java.lang.Iterable[T]] = fun1("isNotIn", (e: Expect[T], i: java.lang.Iterable[T]) => e.isNotIn(i.asScala))

  implicit class RxKotlinPair[T](pair: kotlin.Pair[String, T]) {
      def withNullableSuffix() = new kotlin.Pair(pair.getFirst+" (nullable)", pair.getSecond)
  }
}
