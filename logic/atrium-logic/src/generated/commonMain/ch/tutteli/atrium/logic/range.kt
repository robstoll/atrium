// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultRangeAssertions

fun <T : Comparable<T>> AssertionContainer<T>.toBeInRange(range: ClosedRange<T>): Assertion = impl.toBeInRange(this, range)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: RangeAssertions
    get() = getImpl(RangeAssertions::class) { DefaultRangeAssertions() }
