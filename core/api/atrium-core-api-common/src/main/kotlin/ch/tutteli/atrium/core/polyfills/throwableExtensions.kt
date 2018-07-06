package ch.tutteli.atrium.core.polyfills

/**
 * Returns the stack trace of the given [Throwable] as [List] of [String]s representing the different lines.
 */
expect val Throwable.stack: List<String>
