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

fun <T : BigDecimal> AssertionContainer<T>.toBeWithErrorTolerance(expected: BigDecimal, tolerance: BigDecimal): Assertion =
    _floatingPointJvmImpl.toBeWithErrorTolerance(this, expected, tolerance)
