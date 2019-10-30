@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Turns `Assert<Sequence<E>>` into `Assert<Iterable<E>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `returnValueOf(Sequence::asIterable)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().asIterable()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.asIterable"
    )
)
fun <E> Assert<Sequence<E>>.asIterable(): Assert<Iterable<E>>
    = ExpectImpl.changeSubject(this).unreported { it.asIterable() }
