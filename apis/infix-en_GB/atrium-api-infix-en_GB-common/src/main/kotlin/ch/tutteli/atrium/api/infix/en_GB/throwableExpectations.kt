package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.CharSequenceOrNumberOrChar

/**
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null and contains
 * [expected]'s [toString] representation using a non-disjoint search.
 **
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ThrowableExpectationSamples.messageToContain
 *
 * @since 0.17.0
 */
infix fun <T : Throwable> Expect<T>.messageToContain(expected: CharSequenceOrNumberOrChar): Expect<T> =
    this messageToContain values(expected)

/**
 * Expects that the property [Throwable.message] of the subject of `this` expectation is not null and contains
 * [values]'s [toString] representation using a non-disjoint search.
 **
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed
 * (this function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @param values The values which are expected to be contained within [Throwable.message]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ThrowableExpectationSamples.messageFeature
 *
 * @since 0.17.0
 */
infix fun <T : Throwable> Expect<T>.messageToContain(values: Values<Any>): Expect<T> =
    message { toContain(values) }
