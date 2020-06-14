package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect

package object scala extends ScalaKotlinConversions {

  implicit def expectToAnyAssertions[T](expect: Expect[T]): AnyAssertions[T] = new AnyAssertions[T](expect)
}

package scala {

  import kotlin.jvm.internal.Reflection
  import kotlin.reflect.KClass

  import _root_.scala.reflect.ClassTag

  trait ScalaKotlinConversions {
    type KUnit = kotlin.Unit
    type KFun0[R] = kotlin.jvm.functions.Function0[R]
    type KFun1[T, R] = kotlin.jvm.functions.Function1[T, R]
    type KFun2[T, A1, R] = kotlin.jvm.functions.Function2[T, A1, R]
    type KFun3[T, A1, A2, R] = kotlin.jvm.functions.Function3[T, A1, A2, R]
    type KFun4[T, A1, A2, A3, R] = kotlin.jvm.functions.Function4[T, A1, A2, A3, R]
    type KFun5[T, A1, A2, A3, A4, R] = kotlin.jvm.functions.Function5[T, A1, A2, A3, A4, R]

    type KAssertionCreator[T] = KFun1[Expect[T], KUnit]

    implicit def scalaAssertionCreatorToKotlin[T](f: Expect[T] => Unit): KAssertionCreator[T] =
      if (f == null) null else e => { f(e); kotlin.Unit.INSTANCE }

    implicit def kClass[T](implicit classTag: ClassTag[T]): KClass[T] =
      Reflection.createKotlinClass(classTag.runtimeClass).asInstanceOf[KClass[T]]
  }
}
