package ch.tutteli.atrium.scala2

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.LogicKt
import ch.tutteli.atrium.assertions.Assertion
import kotlin.reflect.KClass
import kotlin.jvm.internal.Reflection
import scala.reflect.ClassTag

package object logic extends LogicImpls with ScalaKotlinConversions {

  import kotlin.jvm.internal.Reflection

  import _root_.scala.reflect.ClassTag

  class KClassTag[T](val kClass: KClass[T]) extends AnyVal

  def kClassOf[T](implicit tag: KClassTag[T]): KClass[T] = tag.kClass

  implicit def kClass[T](implicit classTag: ClassTag[T]): KClassTag[T] =
    new KClassTag(classToKClass(classTag.runtimeClass))

  def classToKClass[T](clazz: Class[_]): KClass[T] =
    Reflection.createKotlinClass(clazz).asInstanceOf[KClass[T]]
}

package logic {

  trait ScalaKotlinConversions {
    type KUnit = kotlin.Unit

    type KFun0[+R] =
      kotlin.jvm.functions.Function0[
        R @uncheckedVariance
      ]
    type KFun1[-T, +R] =
      kotlin.jvm.functions.Function1[
        T @uncheckedVariance,
        R @uncheckedVariance
      ]
    type KFun2[-T, -A1, +R] =
      kotlin.jvm.functions.Function2[
        T @uncheckedVariance,
        A1 @uncheckedVariance,
        R @uncheckedVariance
      ]
    type KFun3[-T, -A1, -A2, +R] =
      kotlin.jvm.functions.Function3[
        T @uncheckedVariance,
        A1 @uncheckedVariance,
        A2 @uncheckedVariance,
        R @uncheckedVariance
      ]
    type KFun4[-T, -A1, -A2, -A3, +R] =
      kotlin.jvm.functions.Function4[
        T @uncheckedVariance,
        A1 @uncheckedVariance,
        A2 @uncheckedVariance,
        A3 @uncheckedVariance,
        R @uncheckedVariance
      ]
    type KFun5[-T, -A1, -A2, -A3, -A4, +R] =
      kotlin.jvm.functions.Function5[
        T @uncheckedVariance,
        A1 @uncheckedVariance,
        A2 @uncheckedVariance,
        A3 @uncheckedVariance,
        A4 @uncheckedVariance,
        R @uncheckedVariance
      ]

    type KAssertionCreator[T] = KFun1[Expect[T], KUnit]

    implicit def scalaAssertionCreatorToKotlin[T](
        f: Expect[T] => Unit
    ): KAssertionCreator[T] =
      if (f == null) null else e => { f(e); kotlin.Unit.INSTANCE }

    implicit def scalaFun0ToKotlin[R](f: () => R): KFun0[R] =
      if (f == null) null else f.apply _

    implicit def scalaFun1ToKotlin[T, R](f: T => R): KFun1[T, R] =
      if (f == null) null else f.apply _

    implicit def scalaFun2ToKotlin[T, A1, R](f: (T, A1) => R): KFun2[T, A1, R] =
      if (f == null) null else f.apply _

    implicit def scalaFun3ToKotlin[T, A1, A2, R](
        f: (T, A1, A2) => R
    ): KFun3[T, A1, A2, R] =
      if (f == null) null else f.apply _

    implicit def scalaFun4ToKotlin[T, A1, A2, A3, R](
        f: (T, A1, A2, A3) => R
    ): KFun4[T, A1, A2, A3, R] =
      if (f == null) null else f.apply _

    implicit def scalaFun5ToKotlin[T, A1, A2, A3, A4, R](
        f: (T, A1, A2, A3, A4) => R
    ): KFun5[T, A1, A2, A3, A4, R] =
      if (f == null) null else f.apply _

    implicit def kClass[T](implicit classTag: ClassTag[T]): KClassTag[T] =
      new KClassTag(Logic.classToKClass(classTag.runtimeClass))
  }
}