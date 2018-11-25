package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

actual object NoOpAtriumErrorAdjuster : NoOpAtriumErrorAdjusterCommon(), AtriumErrorAdjuster {
    override fun adjustStack(stackTrace: Sequence<String>): Sequence<String> = stackTrace
}
