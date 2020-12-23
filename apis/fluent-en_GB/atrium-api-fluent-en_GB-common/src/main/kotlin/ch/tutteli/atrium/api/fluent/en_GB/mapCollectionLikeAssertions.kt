package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.size


/**
 * Creates an [Expect] for the property [Map.size] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.15.0
 */
val <T : Map<*, *>> Expect<T>.size: Expect<Int>
    get() = _logic.size(::toEntries).transform()

/**
 * Expects that the property [Map.size] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.15.0
 */
fun <K, V, T : Map<out K, V>> Expect<T>.size(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    _logic.size(::toEntries).collectAndAppend(assertionCreator)

private fun <T : Map<*, *>> toEntries(t: T): Collection<*> = t.entries
