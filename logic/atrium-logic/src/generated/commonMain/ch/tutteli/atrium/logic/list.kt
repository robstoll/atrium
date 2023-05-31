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

    //TODO 1.1.0 change to ListLike in order that it works as well for arrays
fun <E, T : List<E>> AssertionContainer<T>.get(index: Int): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.get(this, index)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ListAssertions
    get() = getImpl(ListAssertions::class) { DefaultListAssertions() }
