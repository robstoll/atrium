// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultComparableAssertions

        @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeLessThan, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeLessThan()", "ch.tutteli.atrium.creating.proofs.toBeLessThan")
    )
fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isLessThan(expected: T2): Assertion = impl.isLessThan(this, expected)

        @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeLessThanOrEqualTo, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeLessThanOrEqualTo()", "ch.tutteli.atrium.creating.proofs.toBeLessThanOrEqualTo")
    )
fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isLessThanOrEqual(expected: T2): Assertion = impl.isLessThanOrEqual(this, expected)

        @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeGreaterThan, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeGreaterThan()", "ch.tutteli.atrium.creating.proofs.notToBeGreaterThan")
    )
fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isNotGreaterThan(expected: T2): Assertion = impl.isNotGreaterThan(this, expected)

        @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeEqualComparingTo, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeEqualComparingTo()", "ch.tutteli.atrium.creating.proofs.toBeEqualComparingTo")
    )
fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isEqualComparingTo(expected: T2): Assertion = impl.isEqualComparingTo(this, expected)

        @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeGreaterThanOrEqualTo, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeGreaterThanOrEqualTo()", "ch.tutteli.atrium.creating.proofs.toBeGreaterThanOrEqualTo")
    )
fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isGreaterThanOrEqual(expected: T2): Assertion = impl.isGreaterThanOrEqual(this, expected)

        @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeLessThan, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeLessThan()", "ch.tutteli.atrium.creating.proofs.notToBeLessThan")
    )
fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isNotLessThan(expected: T2): Assertion = impl.isNotLessThan(this, expected)

        @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeGreaterThan, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeGreaterThan()", "ch.tutteli.atrium.creating.proofs.toBeGreaterThan")
    )
fun <T1 : Comparable<T2>, T2 : Any?> AssertionContainer<T1>.isGreaterThan(expected: T2): Assertion = impl.isGreaterThan(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ComparableAssertions
    get() = getImpl(ComparableAssertions::class) { DefaultComparableAssertions() }
