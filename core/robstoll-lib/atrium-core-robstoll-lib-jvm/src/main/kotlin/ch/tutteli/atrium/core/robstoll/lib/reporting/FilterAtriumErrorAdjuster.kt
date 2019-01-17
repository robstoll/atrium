package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

abstract class FilterAtriumErrorAdjuster : AtriumErrorAdjuster {

    final override fun <T : Throwable> adjust(throwable: T) {
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
    override fun <T : Throwable> adjustOtherThanStacks(throwable: T) {}
}
