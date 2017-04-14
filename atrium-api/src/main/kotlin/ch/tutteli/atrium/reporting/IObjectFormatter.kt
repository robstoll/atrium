package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Represents a formatter for objects.
 *
 * Typically it formats [IAssertionPlant.subject]s and expected values of [IAssertion]s.
 */
interface IObjectFormatter {

    /**
     * Returns a formatted version of the given [any].
     *
     * It will return [RawString.string] in case [any] is a [RawString].
     *
     * @return The formatted [any].
     */
    fun format(any: Any?): String
}
