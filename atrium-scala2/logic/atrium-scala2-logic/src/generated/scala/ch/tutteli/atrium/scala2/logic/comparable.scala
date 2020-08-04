//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.ComparableKt
import ch.tutteli.atrium.scala2.logic._

class ComparableLogic[(container: AssertionContainer[[]) {

    def isLessThan[T2](expected: T2): Assertion = ComparableKt.isLessThan(container, expected)

    def isLessThanOrEqual[T2](expected: T2): Assertion = ComparableKt.isLessThanOrEqual(container, expected)

    def isGreaterThan[T2](expected: T2): Assertion = ComparableKt.isGreaterThan(container, expected)

    def isGreaterThanOrEqual[T2](expected: T2): Assertion = ComparableKt.isGreaterThanOrEqual(container, expected)

    def isEqualComparingTo[T2](expected: T2): Assertion = ComparableKt.isEqualComparingTo(container, expected)
}
