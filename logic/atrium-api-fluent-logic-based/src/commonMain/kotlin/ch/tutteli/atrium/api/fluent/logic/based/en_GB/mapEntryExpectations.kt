//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*

/**
 * Expects that the property [Map.Entry.key] of the subject of `this` expectation
 * is equal to the given [key] and the property [Map.Entry.value] is equal to the given [value].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.MapEntryExpectationSamples.toEqualKeyValue
 *
 * @since 0.17.0
 */
fun <K, V, T : Map.Entry<K, V>> Expect<T>.toEqualKeyValue(key: K, value: V): Expect<T> =
    _coreAppend { isKeyValue(key, value) }
