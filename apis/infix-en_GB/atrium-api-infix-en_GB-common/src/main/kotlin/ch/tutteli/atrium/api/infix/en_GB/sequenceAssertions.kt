package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject

/**
 * Turns `Expect<E, T : Sequence<E>>` into `Expect<Iterable<E>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature { f(it::asIterable) }` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
infix fun <E, T : Sequence<E>> Expect<T>.asIterable(
    @Suppress("UNUSED_PARAMETER") o: o
): Expect<Iterable<E>> = _logic.changeSubject.unreported { it.asIterable() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Iterable].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::asIterable) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
infix fun <E, T : Sequence<E>> Expect<T>.asIterable(assertionCreator: Expect<Iterable<E>>.() -> Unit): Expect<T> =
    apply { asIterable(o).addAssertionsCreatedBy(assertionCreator) }

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
infix fun <E, T : Sequence<E>> Expect<T>.asList(
    @Suppress("UNUSED_PARAMETER") o: o
): Expect<List<E>> = _logic.changeSubject.unreported { it.toList() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [List].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of({ f(it::toList) }, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return An [Expect] for the current subject of the assertion.
 *
 * @since 0.14.0
 */
infix fun <E, T : Sequence<E>> Expect<T>.asList(assertionCreator: Expect<List<E>>.() -> Unit): Expect<T> =
    apply { asList(o).addAssertionsCreatedBy(assertionCreator) }
