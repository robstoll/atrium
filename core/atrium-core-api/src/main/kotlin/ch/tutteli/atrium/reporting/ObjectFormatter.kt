package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.TranslatableBasedRawString

/**
 * Represents a formatter for objects.
 *
 * Typically it formats [AssertionPlant.subject]s and expected values of [IAssertion]s.
 */
interface ObjectFormatter {

    /**
     * Returns a formatted version of the given [value].
     *
     * Following the minimum requirements for an [ObjectFormatter]:
     * - it will return [RawString.NULL].[string][StringBasedRawString.string] in case [value] is `null`
     * - it will return [StringBasedRawString.string] in case [value] is a [StringBasedRawString]
     * - it will return the translation of [value] in case it is a [TranslatableBasedRawString]
     *
     * Besides that it is up to the concrete implementation how it formats [value].
     * Nonetheless, following some conventions:
     * - return `"true"` in case [value] is `true`
     * - return `"false"` in case [value] is `false`
     * - wrap [Char] in apostrophes (e.g. `"'a'"`)
     * - wrap [CharSequence] in quotes (e.g. `"\"hello\""`)
     *
     * @param value The value which shall be formatted.
     *
     * @return The formatted [value].
     */
    fun format(value: Any?): String
}
