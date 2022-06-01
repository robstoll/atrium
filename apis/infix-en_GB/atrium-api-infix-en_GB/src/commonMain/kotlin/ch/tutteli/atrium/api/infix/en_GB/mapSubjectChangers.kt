package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*

/**
 * Turns `Expect<Map<K, V>>` into `Expect<Set<Map.Entry<K, V>>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::entries) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapSubjectChangerSamples.asEntriesFeature
 *
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.asEntries(
    @Suppress("UNUSED_PARAMETER") o: o
): Expect<Set<Map.Entry<K, V>>> = _logic.changeSubject.unreported { it.entries }

/**
 * Turns `Expect<Map<K, V>>` into `Expect<Set<Map.Entry<K, V>>>` and expects that it holds all assertions the given
 * [assertionCreator] creates for it.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::entries) }` if you want to show the transformation in reporting.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapSubjectChangerSamples.asEntries
 *
 */
infix fun <K, V, T : Map<out K, V>> Expect<T>.asEntries(
    assertionCreator: Expect<Set<Map.Entry<K, V>>>.() -> Unit
): Expect<T> = apply { asEntries(o)._logic.appendAsGroup(assertionCreator) }
