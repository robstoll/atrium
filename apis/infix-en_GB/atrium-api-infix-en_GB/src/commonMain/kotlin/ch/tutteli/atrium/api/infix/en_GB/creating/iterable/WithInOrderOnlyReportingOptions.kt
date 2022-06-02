package ch.tutteli.atrium.api.infix.en_GB.creating.iterable

import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions

/**
 * Parameter object to wrap a given [T] next to specifying [InOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
data class WithInOrderOnlyReportingOptions<out T>(val options: InOrderOnlyReportingOptions.() -> Unit, val t: T)
