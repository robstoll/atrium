//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep


fun <E, T : List<E>> AssertionContainer<T>.get(index: Int): ExtractedFeaturePostStep<T, E> = _listImpl.get(this, index)
