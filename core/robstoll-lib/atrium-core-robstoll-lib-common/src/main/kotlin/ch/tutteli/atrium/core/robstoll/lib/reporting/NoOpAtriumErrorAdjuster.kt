package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

class NoOpAtriumErrorAdjuster : AtriumErrorAdjuster {
    override fun adjust(atriumError: AtriumError): AtriumError = atriumError
}
