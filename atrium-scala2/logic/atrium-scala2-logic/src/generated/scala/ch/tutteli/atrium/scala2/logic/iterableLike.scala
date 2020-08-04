//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.IterableLikeKt
import ch.tutteli.atrium.scala2.logic._

class IterableLikeLogic[T, E](container: AssertionContainer[T]) {


    //TODO add with 0.14.0
//    fun <T, E] iterableLikeContainsBuilder(
//        container: AssertionContainer[T],
//        converter: (T) => Iterable[E]
//    ): IterableLikeContains.Builder[T, E, NoOpSearchBehaviour]
//
//    fun <T, E] iterableLikeContainsNotBuilder(
//        container: AssertionContainer[T],
//        converter: (T) => Iterable[E]
//    ): IterableLikeContains.Builder[T, E, NotSearchBehaviour]

    def all[E](converter: (T) => Iterable[E], assertionCreatorOrNull: Expect[E] => Unit): Assertion =
        IterableLikeKt.all(container, converter, assertionCreatorOrNull)

    def hasNext(converter: (T) => Iterable[E]): Assertion = IterableLikeKt.hasNext(container, converter)

    def hasNotNext(converter: (T) => Iterable[E]): Assertion = IterableLikeKt.hasNotNext(container, converter)

    def min[E : Comparable[E]](converter: (T) => Iterable[E]): ExtractedFeaturePostStep[T, E] = IterableLikeKt.min(container, converter)

    def max[E : Comparable[E]](converter: (T) => Iterable[E]): ExtractedFeaturePostStep[T, E] = IterableLikeKt.max(container, converter)
}
