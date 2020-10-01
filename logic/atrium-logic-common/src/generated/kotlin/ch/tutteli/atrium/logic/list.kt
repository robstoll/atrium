//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder


fun <E, T : List<E>> AssertionContainer<T>.get(index: Int): FeatureExtractorBuilder.ExecutionStep<T, E> = _listImpl.get(this, index)
