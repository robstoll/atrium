//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

fun AssertionContainer<Float>.toBeWithErrorTolerance(expected: Float, tolerance: Float): Assertion =
    _floatingPointImpl.toBeWithErrorTolerance(this, expected, tolerance)
fun AssertionContainer<Double>.toBeWithErrorTolerance(expected: Double, tolerance: Double): Assertion =
    _floatingPointImpl.toBeWithErrorTolerance(this, expected, tolerance)
