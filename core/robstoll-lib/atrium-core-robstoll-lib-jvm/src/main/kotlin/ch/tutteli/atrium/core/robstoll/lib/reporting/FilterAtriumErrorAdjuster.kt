//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

@Deprecated(
    "Use FilterAtriumErrorAdjuster from atrium-core, will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster")
)
abstract class FilterAtriumErrorAdjuster : AtriumErrorAdjuster {

    final override fun adjust(throwable: Throwable) {
        val filteredStackTrace = adjustStackTrace(throwable.stackTrace.asSequence())
        val arr = filteredStackTrace.toList().toTypedArray()
        throwable.stackTrace = arr
        throwable.cause?.let { adjust(it) }
        throwable.suppressed.forEach { adjust(it) }
        adjustOtherThanStacks(throwable)
    }

    /**
     * Does nothing (no adjustments) - override in subclass if you want a different behaviour.
     */
    override fun adjustOtherThanStacks(throwable: Throwable) {}
}
