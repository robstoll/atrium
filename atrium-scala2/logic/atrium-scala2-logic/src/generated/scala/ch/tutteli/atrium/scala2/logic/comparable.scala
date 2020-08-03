//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.scala2.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic._

class ComparableLogic[T](container: AssertionContainer[T]) {

    def isLessThan(expected: T2): Assertion = ComparableKt.isLessThan(container, expected)

    def isLessThanOrEqual(expected: T2): Assertion = ComparableKt.isLessThanOrEqual(container, expected)

    def isGreaterThan(expected: T2): Assertion = ComparableKt.isGreaterThan(container, expected)

    def isGreaterThanOrEqual(expected: T2): Assertion = ComparableKt.isGreaterThanOrEqual(container, expected)

    def isEqualComparingTo(expected: T2): Assertion = ComparableKt.isEqualComparingTo(container, expected)
}
