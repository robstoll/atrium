package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

/**
 * An implementation of an [AtriumErrorAdjuster] which adjusts nothing.
 */
expect object NoOpAtriumErrorAdjuster : AtriumErrorAdjuster

/**
 * An implementation of an [AtriumErrorAdjuster] which adjusts nothing and can be used by the platforms to provide the
 * actual type of [NoOpAtriumErrorAdjuster].
 */
abstract class NoOpAtriumErrorAdjusterCommon : AtriumErrorAdjuster {
    override fun adjustOtherThanStacks(throwable: Throwable) {}
    override fun adjust(throwable: Throwable) {}
}
