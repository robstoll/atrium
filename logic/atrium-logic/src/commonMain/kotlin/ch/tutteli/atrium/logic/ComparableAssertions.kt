//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Comparable] type.
 */
@Deprecated("Switch to CollectionLikeProofs, will be removed with 2.0.0 at the latest")
interface ComparableAssertions {
    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeLessThan, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeLessThan()", "ch.tutteli.atrium.creating.proofs.toBeLessThan")
    )
    fun <T1 : Comparable<T2>, T2 : Any?> isLessThan(container: AssertionContainer<T1>, expected: T2): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeLessThanOrEqualTo, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeLessThanOrEqualTo()", "ch.tutteli.atrium.creating.proofs.toBeLessThanOrEqualTo")
    )
    fun <T1 : Comparable<T2>, T2 : Any?> isLessThanOrEqual(container: AssertionContainer<T1>, expected: T2): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeGreaterThan, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeGreaterThan()", "ch.tutteli.atrium.creating.proofs.notToBeGreaterThan")
    )
    fun <T1 : Comparable<T2>, T2 : Any?> isNotGreaterThan(container: AssertionContainer<T1>, expected: T2): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeEqualComparingTo, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeEqualComparingTo()", "ch.tutteli.atrium.creating.proofs.toBeEqualComparingTo")
    )
    fun <T1 : Comparable<T2>, T2 : Any?> isEqualComparingTo(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeGreaterThanOrEqualTo, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeGreaterThanOrEqualTo()", "ch.tutteli.atrium.creating.proofs.toBeGreaterThanOrEqualTo")
    )
    fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThanOrEqual(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeLessThan, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeLessThan()", "ch.tutteli.atrium.creating.proofs.notToBeLessThan")
    )
    fun <T1 : Comparable<T2>, T2 : Any?> isNotLessThan(container: AssertionContainer<T1>, expected: T2): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeGreaterThan, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeGreaterThan()", "ch.tutteli.atrium.creating.proofs.toBeGreaterThan")
    )
    fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThan(container: AssertionContainer<T1>, expected: T2): Assertion
}
