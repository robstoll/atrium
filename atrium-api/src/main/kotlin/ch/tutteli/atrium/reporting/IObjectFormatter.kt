package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

/**
 * Represents a formatter for objects.
 *
 * Typically it formats [IAssertionPlant.subject]s and expected values of [IAssertion]s.
 */
interface IObjectFormatter {

    /**
     * Returns a formatted version of the given [value].
     *
     * Following the minimum requirements for an [IObjectFormatter]:
     * - it will return [RawString.NULL].[string][RawString.string] in case [value] is `null`
     * - it will return [RawString.string] in case [value] is a [RawString]
     * - it will return the translation of [value] in case it is a [TranslatableRawString]
     *
     * Besides that it is up to the concrete implementation how it formats [value]. Yet, following some advices:
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
