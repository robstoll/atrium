package ch.tutteli.atrium.api.infix.en_GB.creating.iterable

import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions

/**
 * Parameter object to wrap a given [T] next to specifying [InAnyOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
//TODO 2.0.0 remove data?
data class WithInAnyOrderOnlyReportingOptions<out T>(val options: InAnyOrderOnlyReportingOptions.() -> Unit, val t: T)
