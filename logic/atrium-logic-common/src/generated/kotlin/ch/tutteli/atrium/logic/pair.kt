//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder

fun <K, T : Pair<K, *>> AssertionContainer<T>.first(): FeatureExtractorBuilder.ExecutionStep<T, K> = _pairImpl.first(this)
fun <V, T : Pair<*, V>> AssertionContainer<T>.second(): FeatureExtractorBuilder.ExecutionStep<T, V> = _pairImpl.second(this)
