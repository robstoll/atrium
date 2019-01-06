package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

abstract class FilterAtriumErrorAdjuster : AtriumErrorAdjuster {

    final override fun adjust(atriumError: AtriumError): AtriumError {
        val filteredStackTrace = atriumError.stackTrace.asSequence().filterUndesiredStackFrames()
        val arr = filteredStackTrace.toList().toTypedArray()
        atriumError.stackTrace = arr
        return atriumError
    }

    final override fun adjustStackTrace(stackTrace: Sequence<StackTraceElement>): Sequence<StackTraceElement> =
        stackTrace.filterUndesiredStackFrames()

    /**
     * Returns the given [atriumError] without adjusting anything.
     *
     * Override in subclass if you want a different behaviour.
     *
     * @return the given [atriumError].
     */
    override fun adjustOtherThanStack(atriumError: AtriumError): AtriumError = atriumError

    protected abstract fun Sequence<StackTraceElement>.filterUndesiredStackFrames(): Sequence<StackTraceElement>
}
