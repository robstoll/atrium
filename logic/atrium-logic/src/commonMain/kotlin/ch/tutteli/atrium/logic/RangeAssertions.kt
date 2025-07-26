package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

interface RangeAssertions {
    fun <T : Comparable<T>> isInRange(container: AssertionContainer<T>, range: ClosedRange<T>): Assertion
}
