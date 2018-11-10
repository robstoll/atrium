package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

class RemoveRunnerAtriumErrorAdjuster : AtriumErrorAdjuster {

    override fun adjust(atriumError: AtriumError): AtriumError {
        val filteredStackTrace = atriumError.stackTrace.asSequence()
            .takeWhile {
                !it.className.startsWith("org.junit") && !it.className.startsWith("org.jetbrains.spek")
            }
        atriumError.stackTrace = filteredStackTrace.toList().toTypedArray()
        return atriumError
    }
}
