package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.core.polyfills.stack
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

class RemoveRunnerAtriumErrorAdjuster : AtriumErrorAdjuster {

    override fun adjust(atriumError: AtriumError): AtriumError {
        val filteredStack = atriumError.stack.asSequence()
            .takeWhile { !it.contains(Regex("[\\\\|/]mocha[\\\\|/]")) }
        val prefix = "    at "
        atriumError.asDynamic().stack = filteredStack.joinToString("\n$prefix", prefix)
        return atriumError
    }
}
