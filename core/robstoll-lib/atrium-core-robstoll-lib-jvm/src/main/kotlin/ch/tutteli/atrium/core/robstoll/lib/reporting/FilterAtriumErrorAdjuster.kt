package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

abstract class FilterAtriumErrorAdjuster : AtriumErrorAdjuster {
    final override fun adjust(atriumError: AtriumError): AtriumError {
        val filteredStackTrace = atriumError.stackTrace.asSequence().filterUndesiredFrames()
        val arr = filteredStackTrace.toList().toTypedArray()
        atriumError.stackTrace = arr
        return atriumError
    }

    protected abstract fun Sequence<StackTraceElement>.filterUndesiredFrames(): Sequence<StackTraceElement>
}
