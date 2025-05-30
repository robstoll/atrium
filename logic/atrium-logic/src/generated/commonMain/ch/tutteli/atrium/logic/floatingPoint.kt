// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

/**
 * Collection of assertion functions and builders which are applicable to subjects with a floating point number like
 * type (currently [Float] and [Double]).
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultFloatingPointAssertions

fun AssertionContainer<Float>.toBeWithErrorTolerance(expected: Float, tolerance: Float): Assertion =
    impl.toBeWithErrorTolerance(this, expected, tolerance)
fun AssertionContainer<Double>.toBeWithErrorTolerance(expected: Double, tolerance: Double): Assertion =
    impl.toBeWithErrorTolerance(this, expected, tolerance)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: FloatingPointAssertions
    get() = getImpl(FloatingPointAssertions::class) { DefaultFloatingPointAssertions() }
