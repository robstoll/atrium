package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject

/**
 * Turns `Expect<Sequence<E>>` into `Expect<Iterable<E>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Sequence::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
fun <E, T : Sequence<E>> Expect<T>.asIterable(): Expect<Iterable<E>> =
    _logic.changeSubject.unreported { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Sequence::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
fun <E, T : Sequence<E>> Expect<T>.asIterable(assertionCreator: Expect<Iterable<E>>.() -> Unit): Expect<T> =
    apply { asIterable().addAssertionsCreatedBy(assertionCreator) }
