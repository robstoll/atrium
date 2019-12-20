package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domainNullable

/**
 * Expects that the subject of the assertion (an [Iterable]) has at least one element and
 * that every element holds all assertions created by the [assertionCreatorOrNull] or that all elements are `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Expect<T>.all(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Expect<T> =
    addAssertion(_domainNullable.all(assertionCreatorOrNull))
