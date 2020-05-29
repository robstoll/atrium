package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a formatter for objects.
 *
 * Typically it formats [AssertionPlant.subject][SubjectProvider.subject]s and expected values of [Assertion]s.
 */
interface ObjectFormatter {

    /**
     * Returns a formatted version of the given [value].
     *
     * Following the minimum requirements for an [ObjectFormatter]:
     * - it will return [Text.NULL].[string][Text.string] in case [value] is `null`
     * - it will return [Text.string] in case [value] is a [Text]
     * - it will return the translation of [value] in case it is a [Translatable]
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
