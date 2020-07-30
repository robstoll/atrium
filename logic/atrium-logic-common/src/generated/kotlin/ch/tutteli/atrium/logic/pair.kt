//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultPairAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: PairAssertions
    get() = getImpl(PairAssertions::class) { DefaultPairAssertions() }

fun <K, T : Pair<K, *>> AssertionContainer<T>.first(): ExtractedFeaturePostStep<T, K> = impl.first(this)
fun <V, T : Pair<*, V>> AssertionContainer<T>.second(): ExtractedFeaturePostStep<T, V> = impl.second(this)
