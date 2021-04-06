package ch.tutteli.atrium.core.polyfills

/**
 * Returns the stack trace of the given [Throwable] as [List] of [String]s representing the different lines.
 *
 * Notice that there is no specification defining how lines are represented (formatted) and whether they contain
 * a line number and/or a column number. This is platform specific.
 *
 * Please open a feature request if you would like to rely on a specified representation:
 * https://github.com/robstoll/atrium/issues/new?template=feature_request.md
 */
expect val Throwable.stackBacktrace: List<String>
