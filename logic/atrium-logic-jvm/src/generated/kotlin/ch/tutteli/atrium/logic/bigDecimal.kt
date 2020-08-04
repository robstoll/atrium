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
import java.math.BigDecimal

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultBigDecimalAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: BigDecimalAssertions
    get() = getImpl(BigDecimalAssertions::class) { DefaultBigDecimalAssertions() }

fun <T : BigDecimal> AssertionContainer<T>.isNumericallyEqualTo(expected: T): Assertion = impl.isNumericallyEqualTo(this, expected)
fun <T : BigDecimal> AssertionContainer<T>.isNotNumericallyEqualTo(expected: T): Assertion = impl.isNotNumericallyEqualTo(this, expected)
fun <T : BigDecimal> AssertionContainer<T>.isEqualIncludingScale(expected: T, nameOfIsNumericallyEqualTo: String): Assertion =
    impl.isEqualIncludingScale(this, expected, nameOfIsNumericallyEqualTo)

fun <T : BigDecimal> AssertionContainer<T>.isNotEqualIncludingScale(expected: T): Assertion = impl.isNotEqualIncludingScale(this, expected)

fun <T : BigDecimal> AssertionContainer<T>.toBeWithErrorTolerance(expected: BigDecimal, tolerance: BigDecimal): Assertion =
    impl.toBeWithErrorTolerance(this, expected, tolerance)
