// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

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
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultIterableLikeAssertions


fun <T : IterableLike, E> AssertionContainer<T>.builderContainsInIterableLike(converter: (T) -> Iterable<E>): IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour> = impl.builderContainsInIterableLike(this, converter)

fun <T : IterableLike, E> AssertionContainer<T>.builderContainsNotInIterableLike(converter: (T) -> Iterable<E>): NotCheckerStep<E, T, NotSearchBehaviour> = impl.builderContainsNotInIterableLike(this, converter)

fun <T : IterableLike, E : Any> AssertionContainer<T>.all(converter: (T) -> Iterable<E?>, assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Assertion =
    impl.all(this, converter, assertionCreatorOrNull)

fun <T : IterableLike, E> AssertionContainer<T>.hasNext(converter: (T) -> Iterable<E>): Assertion = impl.hasNext(this, converter)

fun <T : IterableLike, E> AssertionContainer<T>.hasNotNext(converter: (T) -> Iterable<E>): Assertion = impl.hasNotNext(this, converter)

fun <T : IterableLike, E : Any> AssertionContainer<T>.hasNotNextOrAny(converter: (T) -> Iterable<E?>, assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Assertion =
    impl.hasNotNextOrAny(this, converter, assertionCreatorOrNull)

fun <T : IterableLike, E : Any> AssertionContainer<T>.hasNotNextOrAll(converter: (T) -> Iterable<E?>, assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Assertion =
    impl.hasNotNextOrAll(this, converter, assertionCreatorOrNull)

fun <T : IterableLike, E : Any> AssertionContainer<T>.hasNotNextOrNone(converter: (T) -> Iterable<E?>, assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Assertion =
    impl.hasNotNextOrNone(this, converter, assertionCreatorOrNull)

fun <T : IterableLike, E : Comparable<E>> AssertionContainer<T>.min(converter: (T) -> Iterable<E>): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.min(this, converter)

fun <T : IterableLike, E : Comparable<E>> AssertionContainer<T>.max(converter: (T) -> Iterable<E>): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.max(this, converter)

fun <T : IterableLike, E> AssertionContainer<T>.containsNoDuplicates(converter: (T) -> Iterable<E>): Assertion = impl.containsNoDuplicates(this, converter)

fun <T : IterableLike, E> AssertionContainer<T>.last(converter: (T) -> Iterable<E>): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.last(this, converter)


@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: IterableLikeAssertions
    get() = getImpl(IterableLikeAssertions::class) { DefaultIterableLikeAssertions() }
