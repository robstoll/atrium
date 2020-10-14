package ch.tutteli.atrium.api.fluent.en_GB.scala

import ch.tutteli.atrium.creating.Expect
import java.lang.{Iterable => JIterable}
import scala.reflect.ClassTag


// FIXME rewrite after fusing IterableFutureAssertionsSpec with IterableAssertionsSpecr
//class IterableFeatureAssertionsSpec
//    extends ch.tutteli.atrium.specs.integration.IterableFeatureAssertionsSpec(
//      feature0("min", changeToScalaIterable(_).min()),
//      fun1("min", changeToScalaIterable(_).min(_).asExpectJIterable),
//      feature0("max", changeToScalaIterable(_).max()),
//      fun1("max", changeToScalaIterable(_).max(_).asExpectJIterable),
//      "[Atrium] "
//    )
//
//class ArrayFeatureAssertionsSpec
//    extends ch.tutteli.atrium.specs.integration.IterableFeatureAssertionsSpec(
//      feature0("min", changeToScalaArray(_).min()),
//      fun1("min", changeToScalaArray(_).min(_).asExpectJIterable),
//      feature0("max", changeToScalaArray(_).max()),
//      fun1("max", changeToScalaArray(_).max(_).asExpectJIterable),
//      "[Atrium] "
//    )

//noinspection TypeAnnotation
object IterableFeatureAssertionsSpec {

  def changeToScalaIterable[E](expect: Expect[JIterable[E]]) = {
    import scala.jdk.CollectionConverters._
    ExpectImpl.changeSubject(expect).unreported(s => s.asScala)
  }

  def changeToScalaArray[E: ClassTag](expect: Expect[JIterable[E]]) = {
    import scala.jdk.CollectionConverters._
    ExpectImpl.changeSubject(expect).unreported(s => s.asScala.toArray)
  }
}
