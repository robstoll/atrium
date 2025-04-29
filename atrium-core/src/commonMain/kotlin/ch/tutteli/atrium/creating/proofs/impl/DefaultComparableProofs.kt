@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.ComparableProofs
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.buildSimpleProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.*

class DefaultComparableProofs : ComparableProofs {

    override fun <T1 : Comparable<T2>, T2 : Any?> toBeLessThan(
        container: ProofContainer<T1>,
        expected: T2
    ): Proof = container.buildSimpleProof(TO_BE_LESS_THAN, expected) { it < expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> toBeLessThanOrEqualTo(
        container: ProofContainer<T1>,
        expected: T2
    ): Proof = container.buildSimpleProof(TO_BE_LESS_THAN_OR_EQUAL_TO, expected) { it <= expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> notToBeGreaterThan(
        container: ProofContainer<T1>,
        expected: T2
    ): Proof = container.buildSimpleProof(NOT_TO_BE_GREATER_THAN, expected) { it <= expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> toBeEqualComparingTo(
        container: ProofContainer<T1>,
        expected: T2
    ): Proof = container.buildSimpleProof(TO_BE_EQUAL_COMPARING_TO, expected) { it.compareTo(expected) == 0 }


    override fun <T1 : Comparable<T2>, T2 : Any?> toBeGreaterThanOrEqualTo(
        container: ProofContainer<T1>,
        expected: T2
    ): Proof = container.buildSimpleProof(TO_BE_GREATER_THAN_OR_EQUAL_TO, expected) { it >= expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> notToBeLessThan(
        container: ProofContainer<T1>,
        expected: T2
    ): Proof = container.buildSimpleProof(NOT_TO_BE_LESS_THAN, expected) { it >= expected }


    override fun <T1 : Comparable<T2>, T2 : Any?> toBeGreaterThan(
        container: ProofContainer<T1>,
        expected: T2
    ): Proof = container.buildSimpleProof(TO_BE_GREATER_THAN, expected) { it > expected }

}
