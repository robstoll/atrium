package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*

/**
 * Expects that the subject of `this` expectation is less than [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ComparableAssertionSamples.isLessThan
 */
@Deprecated("Use toBeLessThan; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeLessThan<T>(expected)"))
infix fun <T : Comparable<T>> Expect<T>.isLessThan(expected: T): Expect<T> =
    _logicAppend { isLessThan(expected) }

/**
 * Expects that the subject of `this` expectation is less than or equal [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ComparableAssertionSamples.isLessThanOrEqual
 */
@Deprecated(
    "Use toBeLessThanOrEqualTo; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeLessThanOrEqualTo<T>(expected)")
)
infix fun <T : Comparable<T>> Expect<T>.isLessThanOrEqual(expected: T): Expect<T> =
    _logicAppend { isLessThanOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation is greater than [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ComparableAssertionSamples.isGreaterThan
 */
@Deprecated(
    "Use toBeGreaterThan; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeGreaterThan<T>(expected)")
)
infix fun <T : Comparable<T>> Expect<T>.isGreaterThan(expected: T): Expect<T> =
    _logicAppend { isGreaterThan(expected) }

/**
 * Expects that the subject of `this` expectation is greater than or equal [expected].
 * The comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ComparableAssertionSamples.isGreaterThanOrEqual
 */
@Deprecated(
    "Use toBeGreaterThanOrEqualTo; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeGreaterThanOrEqualTo<T>(expected)")
)
infix fun <T : Comparable<T>> Expect<T>.isGreaterThanOrEqual(expected: T): Expect<T> =
    _logicAppend { isGreaterThanOrEqual(expected) }

/**
 * Expects that the subject of `this` expectation is equal to [expected]
 * where the comparison is carried out with [Comparable.compareTo].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.ComparableAssertionSamples.isEqualComparingTo
 *
 * @since 0.13.0
 */
@Deprecated(
    "Use toBeEqualComparingTo; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeEqualComparingTo<T>(expected)")
)
infix fun <T : Comparable<T>> Expect<T>.isEqualComparingTo(expected: T): Expect<T> =
    _logicAppend { isEqualComparingTo(expected) }
