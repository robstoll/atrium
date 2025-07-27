// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.util.*
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultOptionalAssertions

fun <T : Optional<*>> AssertionContainer<T>.isEmpty(): Assertion = impl.isEmpty(this)
fun <E: Any, T : Optional<E>> AssertionContainer<T>.isPresent(): FeatureExtractorBuilder.ExecutionStep<T, E> = impl.isPresent(this)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: OptionalAssertions
    get() = getImpl(OptionalAssertions::class) { DefaultOptionalAssertions() }
