// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultRangeAssertions

fun <T : Comparable<T>> AssertionContainer<T>.isInRange(range: ClosedRange<T>): Assertion = impl.isInRange(this, range)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: RangeAssertions
    get() = getImpl(RangeAssertions::class) { DefaultRangeAssertions() }
