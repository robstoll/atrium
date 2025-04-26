package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.proofs.notToBeGreaterThan
import ch.tutteli.atrium.creating.proofs.notToBeLessThan
import ch.tutteli.atrium.creating.proofs.toBeEqualComparingTo
import ch.tutteli.atrium.creating.proofs.toBeGreaterThan
import ch.tutteli.atrium.creating.proofs.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.creating.proofs.toBeLessThan
import ch.tutteli.atrium.creating.proofs.toBeLessThanOrEqualTo

/**
 * Expects that the subject of `this` expectation is less than (`<`) [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ComparableExpectationSamples.toBeLessThan
 *
 * @since 0.17.0
 */
fun <T : Comparable<T>> Expect<T>.toBeLessThan(expected: T): Expect<T> =
    _coreAppend { toBeLessThan(expected) }

/**
 * Expects that the subject of `this` expectation is less than or equal (`<=`) [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * You could also use [notToBeGreaterThan] which is logically equivalent.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ComparableExpectationSamples.toBeLessThanOrEqualTo
 *
 * @since 0.17.0
 */
fun <T : Comparable<T>> Expect<T>.toBeLessThanOrEqualTo(expected: T): Expect<T> =
    _coreAppend { toBeLessThanOrEqualTo(expected) }

/**
 * Expects that the subject of `this` expectation is not greater than [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * You could also use [toBeLessThanOrEqualTo] which is logically equivalent.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ComparableExpectationSamples.notToBeGreaterThan
 *
 * @since 0.17.0
 */
fun <T : Comparable<T>> Expect<T>.notToBeGreaterThan(expected: T): Expect<T> =
    _coreAppend { notToBeGreaterThan(expected) }

/**
 * Expects that the subject of `this` expectation is equal to [expected]
 * where the comparison is carried out based on [Comparable.compareTo].
 *
 * Use [toEqual] if you want a comparison based on [Any.equals].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ComparableExpectationSamples.toBeEqualComparingTo
 *
 * @since 0.17.0
 */
fun <T : Comparable<T>> Expect<T>.toBeEqualComparingTo(expected: T): Expect<T> =
    _coreAppend { toBeEqualComparingTo(expected) }

/**
 * Expects that the subject of `this` expectation is greater than or equal (`>=`) [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * You could also use [notToBeLessThan] which is logically equivalent.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ComparableExpectationSamples.toBeGreaterThanOrEqualTo
 *
 * @since 0.17.0
 */
fun <T : Comparable<T>> Expect<T>.toBeGreaterThanOrEqualTo(expected: T): Expect<T> =
    _coreAppend { toBeGreaterThanOrEqualTo(expected) }

/**
 * Expects that the subject of `this` expectation is not less than [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * You could also use [toBeGreaterThanOrEqualTo] which is logically equivalent.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ComparableExpectationSamples.notToBeLessThan
 *
 * @since 0.17.0
 */
fun <T : Comparable<T>> Expect<T>.notToBeLessThan(expected: T): Expect<T> =
    _coreAppend { notToBeLessThan(expected) }


/**
 * Expects that the subject of `this` expectation is greater than (`>`) [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ComparableExpectationSamples.toBeGreaterThan
 *
 * @since 0.17.0
 */
fun <T : Comparable<T>> Expect<T>.toBeGreaterThan(expected: T): Expect<T> =
    _coreAppend { toBeGreaterThan(expected) }
