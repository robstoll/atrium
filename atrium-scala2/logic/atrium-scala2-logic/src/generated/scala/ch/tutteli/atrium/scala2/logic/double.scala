//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.DoubleKt
import ch.tutteli.atrium.scala2.logic._

class DoubleLogic[(container: AssertionContainer[,]) {

    def toBeWithErrorTolerance(expected: Double, tolerance: Double): Assertion =
        DoubleKt.toBeWithErrorTolerance(container, expected, tolerance)
}
