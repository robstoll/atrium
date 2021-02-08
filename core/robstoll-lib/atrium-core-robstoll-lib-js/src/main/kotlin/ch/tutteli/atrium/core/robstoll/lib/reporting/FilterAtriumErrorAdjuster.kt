//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

@Deprecated(
    "Use FilterAtriumErrorAdjuster from atrium-core, will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.reporting.erroradjusters.FilterAtriumErrorAdjuster")
)
abstract class FilterAtriumErrorAdjuster : AtriumErrorAdjuster {

    final override fun adjust(throwable: Throwable) {
        val filteredStack = adjustStack(throwable.stackBacktrace.asSequence())
        val prefix = "    at "
        throwable.asDynamic().stack = filteredStack.joinToString("\n$prefix", prefix)
        throwable.cause?.let { adjust(it) }
    }

    /**
     * Does nothing (no adjustments) - override in subclass if you want a different behaviour.
     */
    override fun adjustOtherThanStacks(throwable: Throwable) {}
}
