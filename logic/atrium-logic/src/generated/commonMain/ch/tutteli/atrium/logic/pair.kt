// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Pair] type.
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultPairAssertions

fun <K, T : Pair<K, *>> AssertionContainer<T>.first(): FeatureExtractorBuilder.ExecutionStep<T, K> = impl.first(this)
fun <V, T : Pair<*, V>> AssertionContainer<T>.second(): FeatureExtractorBuilder.ExecutionStep<T, V> = impl.second(this)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: PairAssertions
    get() = getImpl(PairAssertions::class) { DefaultPairAssertions() }
