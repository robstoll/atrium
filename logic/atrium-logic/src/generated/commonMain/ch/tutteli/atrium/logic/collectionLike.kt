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
import ch.tutteli.atrium.logic.creating.typeutils.CollectionLike
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultCollectionLikeAssertions


fun <T : CollectionLike> AssertionContainer<T>.isEmpty(converter: (T) -> Collection<*>): Assertion = impl.isEmpty(this, converter)
fun <T : CollectionLike> AssertionContainer<T>.isNotEmpty(converter: (T) -> Collection<*>): Assertion = impl.isNotEmpty(this, converter)

fun <T : CollectionLike> AssertionContainer<T>.size(converter: (T) -> Collection<*>): FeatureExtractorBuilder.ExecutionStep<T, Int> = impl.size(this, converter)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: CollectionLikeAssertions
    get() = getImpl(CollectionLikeAssertions::class) { DefaultCollectionLikeAssertions() }
