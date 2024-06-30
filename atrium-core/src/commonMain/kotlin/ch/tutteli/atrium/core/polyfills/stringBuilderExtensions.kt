package ch.tutteli.atrium.core.polyfills

/**
 * Appends a line separator to this [StringBuilder].
 */
expect fun StringBuilder.appendln(): StringBuilder

/**
 * Appends the given number of Spaces to this [StringBuilder].
 *
 * @since 1.3.0
 */
fun StringBuilder.appendSpaces(numberOfSpaces: Int) = repeat(numberOfSpaces) { append(' ') }
