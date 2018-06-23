package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents a plant for [Assertion]s and offers the possibility to [addAssertion]s and to report them.
 *
 * You can think of it as an [Assertion] factory which does more than just factoring
 * but also provides quality assurance capabilities.
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 */
interface ReportingAssertionPlant<out T : Any> : AssertionPlant<T>, BaseReportingAssertionPlant<T, AssertionPlant<T>>
