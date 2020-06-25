//---------------------------------------------------
//  Generated content, modify:
//  logic/atrium-logic-common/build.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isLessThan(expected: T2): Assertion = _comparableImpl.isLessThan(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isLessThanOrEqual(expected: T2): Assertion = _comparableImpl.isLessThanOrEqual(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isGreaterThan(expected: T2): Assertion = _comparableImpl.isGreaterThan(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isGreaterThanOrEqual(expected: T2): Assertion = _comparableImpl.isGreaterThanOrEqual(this, expected)

fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isEqualComparingTo(expected: T2): Assertion = _comparableImpl.isEqualComparingTo(this, expected)
