package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

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
