package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Turns `Assert<Sequence<E>>` into `Assert<Iterable<E>>`.
 *
 * @return The newly created [Assert] for the transformed subject.
 */
fun <E, T : Sequence<E>> Assert<T>.asIterable()
    = AssertImpl.changeSubject(this) { subject.asIterable() }
