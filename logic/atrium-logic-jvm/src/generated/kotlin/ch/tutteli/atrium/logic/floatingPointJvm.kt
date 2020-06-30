//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.math.BigDecimal

fun <T : BigDecimal> AssertionContainer<T>.toBeWithErrorTolerance(expected: BigDecimal, tolerance: BigDecimal): Assertion =
    _floatingPointJvmImpl.toBeWithErrorTolerance(this, expected, tolerance)
