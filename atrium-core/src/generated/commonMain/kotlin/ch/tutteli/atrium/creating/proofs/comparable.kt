// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.proofs.impl.DefaultComparableProofs

    /** @since 1.3.0 */
fun <SubjectT : Comparable<T2>, T2 : Any?> ProofContainer<SubjectT>.toBeLessThan(expected: T2): Proof =
    impl.toBeLessThan(this, expected)

    /** @since 1.3.0 */
fun <SubjectT : Comparable<T2>, T2 : Any?> ProofContainer<SubjectT>.toBeLessThanOrEqualTo(expected: T2): Proof =
    impl.toBeLessThanOrEqualTo(this, expected)

    /** @since 1.3.0 */
fun <SubjectT : Comparable<T2>, T2 : Any?> ProofContainer<SubjectT>.notToBeGreaterThan(expected: T2): Proof =
    impl.notToBeGreaterThan(this, expected)

    /** @since 1.3.0 */
fun <SubjectT : Comparable<T2>, T2 : Any?> ProofContainer<SubjectT>.toBeEqualComparingTo(expected: T2): Proof =
    impl.toBeEqualComparingTo(this, expected)

    /** @since 1.3.0 */
fun <SubjectT : Comparable<T2>, T2 : Any?> ProofContainer<SubjectT>.toBeGreaterThanOrEqualTo(expected: T2): Proof =
    impl.toBeGreaterThanOrEqualTo(this, expected)

    /** @since 1.3.0 */
fun <SubjectT : Comparable<T2>, T2 : Any?> ProofContainer<SubjectT>.notToBeLessThan(expected: T2): Proof =
    impl.notToBeLessThan(this, expected)

    /** @since 1.3.0 */
fun <SubjectT : Comparable<T2>, T2 : Any?> ProofContainer<SubjectT>.toBeGreaterThan(expected: T2): Proof =
    impl.toBeGreaterThan(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> ProofContainer<T>.impl: ComparableProofs
    get() = getImpl(ComparableProofs::class) { DefaultComparableProofs() }
