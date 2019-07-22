package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Turns `Expect<Sequence<E>>` into `Expect<Iterable<E>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(Sequence::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 */
@Suppress("DEPRECATION")
fun <E> Expect<Sequence<E>>.asIterable(): Expect<Iterable<E>>
    = ExpectImpl.changeSubject.unreported(this) { it.asIterable() }
