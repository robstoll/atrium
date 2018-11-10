package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.core.polyfills.stack
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

abstract class FilterAtriumErrorAdjuster : AtriumErrorAdjuster {
    final override fun adjust(atriumError: AtriumError): AtriumError {
        val filteredStack = atriumError.stack.asSequence().filterUndesiredFrames()
        val prefix = "    at "
        atriumError.asDynamic().stack = filteredStack.joinToString("\n$prefix", prefix)
        return atriumError
    }

    protected abstract fun Sequence<String>.filterUndesiredFrames(): Sequence<String>
}
