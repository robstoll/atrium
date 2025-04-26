package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.ProofContainer

/**
 * Collection of proof functions and builders which are applicable to [Comparable].
 *
 * @since 1.3.0
 */
interface ComparableProofs {
    /** @since 1.3.0 */
    fun <SubjectT : Comparable<T2>, T2 : Any?> toBeLessThan(container: ProofContainer<SubjectT>, expected: T2): Proof

    /** @since 1.3.0 */
    fun <SubjectT : Comparable<T2>, T2 : Any?> toBeLessThanOrEqualTo(
        container: ProofContainer<SubjectT>,
        expected: T2
    ): Proof

    /** @since 1.3.0 */
    fun <SubjectT : Comparable<T2>, T2 : Any?> notToBeGreaterThan(
        container: ProofContainer<SubjectT>,
        expected: T2
    ): Proof

    /** @since 1.3.0 */
    fun <SubjectT : Comparable<T2>, T2 : Any?> toBeEqualComparingTo(
        container: ProofContainer<SubjectT>,
        expected: T2
    ): Proof

    /** @since 1.3.0 */
    fun <SubjectT : Comparable<T2>, T2 : Any?> toBeGreaterThanOrEqualTo(
        container: ProofContainer<SubjectT>,
        expected: T2
    ): Proof

    /** @since 1.3.0 */
    fun <SubjectT : Comparable<T2>, T2 : Any?> notToBeLessThan(container: ProofContainer<SubjectT>, expected: T2): Proof

    /** @since 1.3.0 */
    fun <SubjectT : Comparable<T2>, T2 : Any?> toBeGreaterThan(container: ProofContainer<SubjectT>, expected: T2): Proof
}
