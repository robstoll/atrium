//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.FloatKt
import ch.tutteli.atrium.scala2.logic._

class FloatLogic[(container: AssertionContainer[,]) {

    def toBeWithErrorTolerance(expected: Float, tolerance: Float): Assertion =
        FloatKt.toBeWithErrorTolerance(container, expected, tolerance)
}
