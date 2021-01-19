package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.size

/**
 * Creates an [Expect] for the property [Collection.size] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.15.0
 */
val <T : Map<*, *>> Expect<T>.size: Expect<Int>
    get() = _logic.size(::toEntries).transform()

/**
 * Expects that the property [Collection.size] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.15.0
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.size(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    _logic.size(::toEntries).collectAndAppend(assertionCreator)


private fun <T : Map<*, *>> toEntries(t: T): Collection<*> = t.entries
