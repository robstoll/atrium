// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultIteratorAssertions

fun <E, T : Iterator<E>> AssertionContainer<T>.hasNext(): Assertion = impl.hasNext(this)
fun <E, T : Iterator<E>> AssertionContainer<T>.hasNotNext(): Assertion = impl.hasNotNext(this)
fun <E, T : Iterator<E>> AssertionContainer<T>.next(): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.next(this)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: IteratorAssertions
    get() = getImpl(IteratorAssertions::class) { DefaultIteratorAssertions() }
