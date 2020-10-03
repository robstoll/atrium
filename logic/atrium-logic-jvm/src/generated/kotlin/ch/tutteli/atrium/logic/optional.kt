//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.util.*

fun <T : Optional<*>> AssertionContainer<T>.isEmpty(): Assertion = _optionalImpl.isEmpty(this)
fun <E, T : Optional<E>> AssertionContainer<T>.isPresent(): FeatureExtractorBuilder.ExecutionStep<T, E> = _optionalImpl.isPresent(this)
