package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.logic.isInRange
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend

/**
 * Expects that the subject of `this` expectation is within the [expected] range (inclusive).
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.RangeExpectationSamples.toBeInRangeChar
 *
 * @since 1.3.0
 */
fun Expect<Char>.toBeInRange(expected: CharRange): Expect<Char> =
    _logicAppend { isInRange(expected) }

/**
 * Expects that the subject of `this` expectation is within the [expected] range (inclusive).
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.RangeExpectationSamples.toBeInRangeInt
 *
 * @since 1.3.0
 */
fun Expect<Int>.toBeInRange(expected: IntRange): Expect<Int> =
    _logicAppend { isInRange(expected) }

/**
 * Expects that the subject of `this` expectation is within the [expected] range (inclusive).
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.RangeExpectationSamples.toBeInRangeLong
 *
 * @since 1.3.0
 */
fun Expect<Long>.toBeInRange(expected: LongRange): Expect<Long> =
    _logicAppend { isInRange(expected) }
