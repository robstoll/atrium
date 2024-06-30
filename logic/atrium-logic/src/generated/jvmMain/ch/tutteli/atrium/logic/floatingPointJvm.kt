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
import java.math.BigDecimal

/**
 * Collection of assertion functions and builders which are applicable to floating point number like types which are
 * specific to the JVM platform (currently only for [BigDecimal]).
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultFloatingPointJvmAssertions

fun <T : BigDecimal> AssertionContainer<T>.toBeWithErrorTolerance(expected: BigDecimal, tolerance: BigDecimal): Assertion =
    impl.toBeWithErrorTolerance(this, expected, tolerance)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: FloatingPointJvmAssertions
    get() = getImpl(FloatingPointJvmAssertions::class) { DefaultFloatingPointJvmAssertions() }
