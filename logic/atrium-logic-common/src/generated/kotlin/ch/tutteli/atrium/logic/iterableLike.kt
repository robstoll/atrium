// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultIterableLikeAssertions


fun <T : Any, E> AssertionContainer<T>.containsBuilder(converter: (T) -> Iterable<E>): IterableLikeContains.EntryPointStep<E, T, NoOpSearchBehaviour> = impl.containsBuilder(this, converter)

fun <T : Any, E> AssertionContainer<T>.containsNotBuilder(converter: (T) -> Iterable<E>): NotCheckerStep<E, T, NotSearchBehaviour> = impl.containsNotBuilder(this, converter)

fun <T : Any, E : Any> AssertionContainer<T>.all(converter: (T) -> Iterable<E?>, assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Assertion =
    impl.all(this, converter, assertionCreatorOrNull)

fun <T : Any, E> AssertionContainer<T>.hasNext(converter: (T) -> Iterable<E>): Assertion = impl.hasNext(this, converter)

fun <T : Any, E> AssertionContainer<T>.hasNotNext(converter: (T) -> Iterable<E>): Assertion = impl.hasNotNext(this, converter)

fun <T : Any, E : Comparable<E>> AssertionContainer<T>.min(converter: (T) -> Iterable<E>): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.min(this, converter)

fun <T : Any, E : Comparable<E>> AssertionContainer<T>.max(converter: (T) -> Iterable<E>): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.max(this, converter)

fun <T : Any, E> AssertionContainer<T>.containsNoDuplicates(converter: (T) -> Iterable<E>): Assertion = impl.containsNoDuplicates(this, converter)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: IterableLikeAssertions
    get() = getImpl(IterableLikeAssertions::class) { DefaultIterableLikeAssertions() }
