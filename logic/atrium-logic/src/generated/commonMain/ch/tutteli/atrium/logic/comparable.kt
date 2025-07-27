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
import ch.tutteli.atrium.logic.impl.DefaultComparableAssertions

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isLessThan(expected: T2): Assertion = impl.isLessThan(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isLessThanOrEqual(expected: T2): Assertion = impl.isLessThanOrEqual(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isNotGreaterThan(expected: T2): Assertion = impl.isNotGreaterThan(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isEqualComparingTo(expected: T2): Assertion = impl.isEqualComparingTo(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isGreaterThanOrEqual(expected: T2): Assertion = impl.isGreaterThanOrEqual(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isNotLessThan(expected: T2): Assertion = impl.isNotLessThan(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isGreaterThan(expected: T2): Assertion = impl.isGreaterThan(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ComparableAssertions
    get() = getImpl(ComparableAssertions::class) { DefaultComparableAssertions() }
