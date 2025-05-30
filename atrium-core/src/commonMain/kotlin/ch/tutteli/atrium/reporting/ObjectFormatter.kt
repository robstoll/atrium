package ch.tutteli.atrium.reporting

/**
 * Represents a formatter for objects.
 *
 * Typically, it formats the subject of the formulated expectation including the expected values.
 */
interface ObjectFormatter {

    /**
     * Returns a formatted version of the given [value].
     *
     * Following the minimum requirements for an [ObjectFormatter]:
     * - it will return [Text.NULL].[string][Text.string] in case [value] is `null`
     * - it will return [Text.string] in case [value] is a [Text]
     * - it will return the translation of [value] in case it is a [ch.tutteli.atrium.reporting.translating.Translatable]
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
