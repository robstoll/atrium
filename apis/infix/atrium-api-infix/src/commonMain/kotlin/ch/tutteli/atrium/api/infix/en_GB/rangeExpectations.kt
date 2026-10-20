package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.toBeInRange

/**
 * Expects that the subject of `this` expectation is within the [expected] range.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.RangeExpectationSamples.toBeInCharRange
 *
 * @since 1.3.0
 */
infix fun Expect<Char>.toBeIn(expected: CharRange): Expect<Char> =
    _logicAppend { toBeInRange(expected) }

/**
 * Expects that the subject of `this` expectation is within the [expected] range.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.RangeExpectationSamples.toBeInIntRange
 *
 * @since 1.3.0
 */
infix fun Expect<Int>.toBeIn(expected: IntRange): Expect<Int> =
    _logicAppend { toBeInRange(expected) }

/**
 * Expects that the subject of `this` expectation is within the [expected] range.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.RangeExpectationSamples.toBeInLongRange
 *
 * @since 1.3.0
 */
infix fun Expect<Long>.toBeIn(expected: LongRange): Expect<Long> =
    _logicAppend { toBeInRange(expected) }
