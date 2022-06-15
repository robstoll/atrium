package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Turns `Expect<E, T : Iterable<E>>` into `Expect<List<E>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableSubjectChangersSamples.asListFeature
 *
 * @since 0.14.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.asList(
    @Suppress("UNUSED_PARAMETER") o: o
): Expect<List<E>> = _logic.changeSubject.unreported { it.toList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableSubjectChangersSamples.asList
 *
 * @since 0.14.0
 */
infix fun <E, T : Iterable<E>> Expect<T>.asList(assertionCreator: Expect<List<E>>.() -> Unit): Expect<T> =
    apply { asList(o)._logic.appendAsGroup(assertionCreator) }
