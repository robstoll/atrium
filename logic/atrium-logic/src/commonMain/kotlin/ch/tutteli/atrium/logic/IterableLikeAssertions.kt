package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Collection of assertion functions and builders which are applicable to subjects which can be transformed to an
 * [Iterable] - intended for types which are Iterable like such as [Array] or [Sequence].
 */
interface IterableLikeAssertions {

    fun <T : IterableLike, E> builderContainsInIterableLike(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour>

    fun <T : IterableLike, E> builderContainsNotInIterableLike(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): NotCheckerStep<E, T, NotSearchBehaviour>

    fun <T : IterableLike, E : Any> all(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion

    fun <T : IterableLike, E> hasNext(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): Assertion

    fun <T : IterableLike, E> hasNotNext(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): Assertion

    fun <T : IterableLike, E : Any> hasNotNextOrAny(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion

    fun <T : IterableLike, E : Any> hasNotNextOrAll(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion

    fun <T : IterableLike, E : Any> hasNotNextOrNone(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Assertion

    fun <T : IterableLike, E : Comparable<E>> min(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): FeatureExtractorBuilder.ExecutionStep<T, E>

    fun <T : IterableLike, E : Comparable<E>> max(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): FeatureExtractorBuilder.ExecutionStep<T, E>

    fun <T : IterableLike, E> containsNoDuplicates(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): Assertion

    fun <T : IterableLike, E : Comparable<E>> last(
        container: AssertionContainer<T>,
        converter: (T) -> Iterable<E>
    ): FeatureExtractorBuilder.ExecutionStep<T, E>

}
