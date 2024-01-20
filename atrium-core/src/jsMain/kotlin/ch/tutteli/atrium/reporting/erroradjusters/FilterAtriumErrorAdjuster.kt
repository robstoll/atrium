package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

abstract class FilterAtriumErrorAdjuster : AtriumErrorAdjuster {

    final override fun adjust(throwable: Throwable) {
        val filteredStack = adjustStack(throwable.stackBacktrace.asSequence())
        val prefix = "    at "
        val stack = filteredStack.joinToString("\n$prefix", prefix)
        throwable.asDynamic().stack = stack
        throwable.cause?.let { adjust(it) }
    }

    /**
     * Does nothing (no adjustments) - override in subclass if you want a different behaviour.
     */
    override fun adjustOtherThanStacks(throwable: Throwable) {}
}
