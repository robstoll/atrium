// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultFloatingPointAssertions

fun AssertionContainer<Float>.toBeWithErrorTolerance(expected: Float, tolerance: Float): Assertion =
    impl.toBeWithErrorTolerance(this, expected, tolerance)
fun AssertionContainer<Double>.toBeWithErrorTolerance(expected: Double, tolerance: Double): Assertion =
    impl.toBeWithErrorTolerance(this, expected, tolerance)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: FloatingPointAssertions
    get() = getImpl(FloatingPointAssertions::class) { DefaultFloatingPointAssertions() }
