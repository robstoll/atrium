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
     * Returns a formatted version of the given [value].
     *
     * It will return [RawString.string] in case [value] is a [RawString].
     *
     * @param value The value which shall be formatted.
     *
     * @return The formatted [value].
     */
    fun format(value: Any?): String
}
