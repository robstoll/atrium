@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents an assertion plant for nullable types and offers the possibility to [addAssertion]s and to report them.
 *
 * You can think of it as an [Assertion] factory which does more than just factoring
 * but also provides quality assurance capabilities.
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 */
@Deprecated("Switch from ReportingAssertionPlantNullable to ReportingAssertionContainer; will be removed with 1.0.0", ReplaceWith("ReportingAssertionContainer"))
interface ReportingAssertionPlantNullable<out T>
    : AssertionPlantNullable<T>, BaseReportingAssertionPlant<T, AssertionPlantNullable<T>>