package ch.tutteli.atrium.scala2.api.fluent.en_GB

import scala.collection.generic.IsIterable
import ch.tutteli.atrium.creating.Expect
import java.lang.{Iterable => JIterable}

import scala.jdk.CollectionConverters._

object IsIterableHelpers {
  type IsIterableFixA[Repr, A0] = IsIterable[Repr] { type A = A0 }

  implicit class RxExpectIsIterable[Repr](expect: Expect[Repr]) {

    def asExpectJIterable[E](implicit isIterable: IsIterableFixA[Repr, E]): Expect[JIterable[E]] =
      ExpectImpl.changeSubject(expect).unreported { iterableLikeSubject =>
        val subject = isIterable.apply(iterableLikeSubject)
        subject match {
          case l: List[E]     => l.asJava
          case l: Iterable[E] => l.asJava
          case subject        => subject.toList.asJava
        }
      }
  }

  implicit class RxExpectJ[E](expectJ: Expect[JIterable[E]]) {

    def backToScalaRepr[Repr](originalExpect: Expect[Repr]): Expect[Repr] =
      originalExpect.getMaybeSubject.fold(
        () => expectJ.asInstanceOf[Expect[Repr]],
        subject => ExpectImpl.changeSubject(expectJ).unreported(_ => subject)
      )
  }
}
