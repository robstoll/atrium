package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

abstract class FilterAtriumErrorAdjuster : AtriumErrorAdjuster {

    final override fun adjust(atriumError: AtriumError): AtriumError {
        val filteredStack = atriumError.stackBacktrace.asSequence().filterUndesiredStackFrames()
        val prefix = "    at "
        atriumError.asDynamic().stack = filteredStack.joinToString("\n$prefix", prefix)
        return atriumError
    }

    final override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> =
        stackTrace.filterUndesiredStackFrames()

    /**
     * Returns the given [atriumError] without adjusting anything.
     *
     * Override in subclass if you want a different behaviour.
     *
     * @return the given [atriumError].
     */
    override fun adjustOtherThanStack(atriumError: AtriumError): AtriumError = atriumError

    protected abstract fun Sequence<String>.filterUndesiredStackFrames(): Sequence<String>
}
