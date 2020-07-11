//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.LocalDateTime
import java.util.*

fun <T : Optional<*>> AssertionContainer<T>.isEmpty(): Assertion = _optionalImpl.isEmpty(this)
fun <E, T : Optional<E>> AssertionContainer<T>.isPresent(): ExtractedFeaturePostStep<T, E> = _optionalImpl.isPresent(this)
