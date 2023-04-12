package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

abstract class FilterAtriumErrorAdjuster : AtriumErrorAdjuster {

    final override fun adjust(throwable: Throwable) {
        val filteredStack = adjustStack(throwable.stackBacktrace.asSequence())
        val prefix = "    at "
        throwable.asDynamic().stack = filteredStack.map {
            if (it.startsWith("/")) {
                // in order that intellij renders the link correctly we prefix it with the file name
                it.substringAfterLast("/").substringBeforeLast(".kt") + " (" + it + ")"
            } else {
                it
            }
        }.joinToString("\n$prefix", prefix)
        throwable.cause?.let { adjust(it) }
    }

    /**
     * Does nothing (no adjustments) - override in subclass if you want a different behaviour.
     */
    override fun adjustOtherThanStacks(throwable: Throwable) {}
}
