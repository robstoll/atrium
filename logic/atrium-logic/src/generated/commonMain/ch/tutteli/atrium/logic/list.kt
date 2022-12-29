// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultListAssertions

    //TODO 0.20.0 change to ListLike in order that it works as well for arrays
fun <E, T : List<E>> AssertionContainer<T>.get(index: Int): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.get(this, index)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ListAssertions
    get() = getImpl(ListAssertions::class) { DefaultListAssertions() }
