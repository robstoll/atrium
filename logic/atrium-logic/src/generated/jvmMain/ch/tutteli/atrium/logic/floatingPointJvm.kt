// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.math.BigDecimal
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultFloatingPointJvmAssertions

fun <T : BigDecimal> AssertionContainer<T>.toBeWithErrorTolerance(expected: BigDecimal, tolerance: BigDecimal): Assertion =
    impl.toBeWithErrorTolerance(this, expected, tolerance)

                    @OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: FloatingPointJvmAssertions
    get() = getImpl(FloatingPointJvmAssertions::class) { DefaultFloatingPointJvmAssertions() }
