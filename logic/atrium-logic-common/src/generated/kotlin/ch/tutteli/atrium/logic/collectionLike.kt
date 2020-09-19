//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.creating.typeutils.CollectionLike


fun <T : CollectionLike> AssertionContainer<T>.isEmpty(converter: (T) -> Collection<*>): Assertion = _collectionLikeImpl.isEmpty(this, converter)
fun <T : CollectionLike> AssertionContainer<T>.isNotEmpty(converter: (T) -> Collection<*>): Assertion = _collectionLikeImpl.isNotEmpty(this, converter)

fun <T : CollectionLike> AssertionContainer<T>.size(converter: (T) -> Collection<*>): ExtractedFeaturePostStep<T, Int> = _collectionLikeImpl.size(this, converter)
