package ch.tutteli.atrium.core.polyfills

/**
 * Appends a line separator to this StringBuilder.
 */
//TODO 1.1.0 rename to appendLine once we migrate to Kotlin 1.4 or higher
expect fun StringBuilder.appendln(): StringBuilder
