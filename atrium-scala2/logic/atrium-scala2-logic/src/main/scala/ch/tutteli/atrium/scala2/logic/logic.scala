package ch.tutteli.atrium.scala2

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.LogicKt
import ch.tutteli.atrium.assertions.Assertion
import kotlin.reflect.KClass
import kotlin.jvm.internal.Reflection
import scala.reflect.ClassTag

package object logic extends LogicImpls {
  def kClassOf[T](implicit tag: KClassTag[T]): KClass[T] = tag.kClass

  implicit def kClass[T](implicit classTag: ClassTag[T]): KClassTag[T] =
    new KClassTag(classToKClass(classTag.runtimeClass))

  def classToKClass[T](clazz: Class[_]): KClass[T] =
    Reflection.createKotlinClass(clazz).asInstanceOf[KClass[T]]

  class KClassTag[T](val kClass: KClass[T]) extends AnyVal
}
