//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultComparableAssertions

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isLessThan(expected: T2): Assertion = impl.isLessThan(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isLessThanOrEqual(expected: T2): Assertion = impl.isLessThanOrEqual(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isGreaterThan(expected: T2): Assertion = impl.isGreaterThan(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isGreaterThanOrEqual(expected: T2): Assertion = impl.isGreaterThanOrEqual(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isEqualComparingTo(expected: T2): Assertion = impl.isEqualComparingTo(this, expected)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ComparableAssertions
    get() = getImpl(ComparableAssertions::class) { DefaultComparableAssertions() }
