package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents a container for [Assertion]s and offers the possibility to [addAssertion]s which are reported
 * in case they do not hold.
 *
 * @param T The type of the [subject] of this [Assert].
 */
interface ReportingAssertionContainer<T> : Expect<T>, AssertionContainerWithCommonFields<T>
