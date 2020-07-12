//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

fun <K, T : Pair<K, *>> AssertionContainer<T>.first(): ExtractedFeaturePostStep<T, K> = _pairImpl.first(this)
fun <V, T : Pair<*, V>> AssertionContainer<T>.second(): ExtractedFeaturePostStep<T, V> = _pairImpl.second(this)
