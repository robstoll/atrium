package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

expect object NoOpAtriumErrorAdjuster : AtriumErrorAdjuster

abstract class NoOpAtriumErrorAdjusterCommon : AtriumErrorAdjuster {
    override fun adjustOtherThanStack(atriumError: AtriumError): AtriumError = atriumError

    override fun adjust(atriumError: AtriumError): AtriumError = atriumError
}
