//TODO rename file to sequenceExpectations in 0.18.0
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
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Sequence::asIterable, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return an [Expect] for the subject of `this` expectation.
 */
fun <E, T : Sequence<E>> Expect<T>.asIterable(assertionCreator: Expect<Iterable<E>>.() -> Unit): Expect<T> =
    apply { asIterable()._logic.appendAssertionsCreatedBy(assertionCreator) }

/**
 * Turns `Expect<E, T : Sequence<E>>` into `Expect<List<E>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::toList) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.14.0
 */
fun <E, T : Sequence<E>> Expect<T>.asList(): Expect<List<E>> = _logic.changeSubject.unreported { it.toList() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::toList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0
 */
fun <E, T : Sequence<E>> Expect<T>.asList(assertionCreator: Expect<List<E>>.() -> Unit): Expect<T> =
    apply { asList()._logic.appendAssertionsCreatedBy(assertionCreator) }
