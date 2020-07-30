package ch.tutteli.atrium.scala2.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect

object TestUtils {

  implicit def kotlinAssertionCreatorToScala[T](f: KFun1[_ >: Expect[T], KUnit]): Expect[T] => Unit = {
    val scalaF = kFun1ToScala(f)
    if (scalaF == null) null else scalaF(_)
  }

  implicit def kFun0ToScala[R](f: KFun0[R]): () => R =
    if (f == null) null else () => f.invoke()

  implicit def kFun1ToScala[T, R](f: KFun1[T, R]): T => R =
    if (f == null) null else f.invoke

  implicit def kFun2ToScala[T, A1, R](f: KFun2[T, A1, R]): (T, A1) => R =
    if (f == null) null else f.invoke

  implicit def kFun3ToScala[T, A1, A2, R](f: KFun3[T, A1, A2, R]): (T, A1, A2) => R =
    if (f == null) null else f.invoke

  implicit def kFun4ToScala[T, A1, A2, A3, R](f: KFun4[T, A1, A2, A3, R]): (T, A1, A2, A3) => R =
    if (f == null) null else f.invoke

  implicit def kFun5ToScala[T, A1, A2, A3, A4, R](f: KFun5[T, A1, A2, A3, A4, R]): (T, A1, A2, A3, A4) => R =
    if (f == null) null else f.invoke

  type SpecPair[T] = kotlin.Pair[String, T]

  type Feature0[T, R] = SpecPair[KFun1[Expect[T], Expect[R]]]
  type Feature1[T, A1, R] = SpecPair[KFun2[Expect[T], A1, Expect[R]]]
  type Feature2[T, A1, A2, R] = SpecPair[KFun3[Expect[T], A1, A2, Expect[R]]]
  type Feature3[T, A1, A2, A3, R] = SpecPair[KFun4[Expect[T], A1, A2, A3, Expect[R]]]
  type Feature4[T, A1, A2, A3, A4, R] = SpecPair[KFun5[Expect[T], A1, A2, A3, A4, Expect[R]]]

  type Fun0[T] = Feature0[T, T]
  type Fun1[T, A1] = Feature1[T, A1, T]
  type Fun2[T, A1, A2] = Feature2[T, A1, A2, T]
  type Fun3[T, A1, A2, A3] = Feature3[T, A1, A2, A3, T]
  type Fun4[T, A1, A2, A3, A4] = Feature4[T, A1, A2, A3, A4, T]

  def feature0[T, R](name: String, f: Expect[T] => Expect[R]): Feature0[T, R] =
    new kotlin.Pair(name + " (feature)", f)

  def feature1[T, A1, R](name: String, f: (Expect[T], A1) => Expect[R]): Feature1[T, A1, R] =
    new kotlin.Pair(name + " (feature)", f)

  def feature2[T, A1, A2, R](name: String, f: (Expect[T], A1, A2) => Expect[R]): Feature2[T, A1, A2, R] =
    new kotlin.Pair(name + " (feature)", f)

  def feature3[T, A1, A2, A3, R](name: String, f: (Expect[T], A1, A2, A3) => Expect[R]): Feature3[T, A1, A2, A3, R] =
    new kotlin.Pair(name + " (feature)", f)

  def feature4[T, A1, A2, A3, A4, R](
      name: String,
      f: (Expect[T], A1, A2, A3, A4) => Expect[R]
  ): Feature4[T, A1, A2, A3, A4, R] =
    new kotlin.Pair(name + " (feature)", f)

  def fun0[T](name: String, f: Expect[T] => Expect[T]): Fun0[T] =
    new kotlin.Pair(name, f)

  def fun1[T, A1](name: String, f: (Expect[T], A1) => Expect[T]): Fun1[T, A1] =
    new kotlin.Pair(name, f)

  def fun2[T, A1, A2](name: String, f: (Expect[T], A1, A2) => Expect[T]): Fun2[T, A1, A2] =
    new kotlin.Pair(name, f)

  def fun3[T, A1, A2, A3](name: String, f: (Expect[T], A1, A2, A3) => Expect[T]): Fun3[T, A1, A2, A3] =
    new kotlin.Pair(name, f)

  def fun4[T, A1, A2, A3, A4](name: String, f: (Expect[T], A1, A2, A3, A4) => Expect[T]): Fun4[T, A1, A2, A3, A4] =
    new kotlin.Pair(name, f)

}
