//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic._

class FloatingPointLogic[T](container: AssertionContainer[T]) {

    def toBeWithErrorTolerance(expected: Float, tolerance: Float): Assertion =
        FloatingPointKt.toBeWithErrorTolerance(container, expected, tolerance)
    def toBeWithErrorTolerance(expected: Double, tolerance: Double): Assertion =
        FloatingPointKt.toBeWithErrorTolerance(container, expected, tolerance)
}
