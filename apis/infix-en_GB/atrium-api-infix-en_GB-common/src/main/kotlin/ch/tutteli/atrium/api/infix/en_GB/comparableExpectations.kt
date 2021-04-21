package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*

/**
 * Expects that the subject of `this` expectation is less than [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ComparableExpectationSamples.toBeLessThan
 *
 * @since 0.17.0
 */
infix fun <T : Comparable<T>> Expect<T>.toBeLessThan(expected: T): Expect<T> =
    _logicAppend { isLessThan(expected) }

/**
 * Expects that the subject of `this` expectation is less than or equal [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ComparableExpectationSamples.toBeLessThanOrEqual
 *
 * @since 0.17.0
 */
infix fun <T : Comparable<T>> Expect<T>.toBeLessThanOrEqual(expected: T): Expect<T> =
    _logicAppend { isLessThanOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation is greater than [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ComparableExpectationSamples.toBeGreaterThan
 *
 * @since 0.17.0
 */
infix fun <T : Comparable<T>> Expect<T>.toBeGreaterThan(expected: T): Expect<T> =
    _logicAppend { isGreaterThan(expected) }

/**
 * Expects that the subject of `this` expectation is greater than or equal [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ComparableExpectationSamples.toBeGreaterThanOrEqual
 *
 * @since 0.17.0
 */
infix fun <T : Comparable<T>> Expect<T>.toBeGreaterThanOrEqual(expected: T): Expect<T> =
    _logicAppend { isGreaterThanOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation is equal to [expected]
 * where the comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ComparableExpectationSamples.toBeEqualComparingTo
 *
 * @since 0.17.0
 */
infix fun <T : Comparable<T>> Expect<T>.toBeEqualComparingTo(expected: T): Expect<T> =
    _logicAppend { isEqualComparingTo(expected) }
