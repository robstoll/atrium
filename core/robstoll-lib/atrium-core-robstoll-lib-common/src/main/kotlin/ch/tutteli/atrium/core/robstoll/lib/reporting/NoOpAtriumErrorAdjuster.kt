//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

/**
 * An implementation of an [AtriumErrorAdjuster] which adjust nothings.
 */
@Deprecated(
    "Use NoOpAtriumErrorAdjuster from atrium-core, will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.reporting.erroradjusters.NoOpAtriumErrorAdjuster")
)
expect object NoOpAtriumErrorAdjuster : AtriumErrorAdjuster

/**
 * An implementation of an [AtriumErrorAdjuster] which adjust nothings and can be used by the platforms to provide the
 * actual type of [NoOpAtriumErrorAdjuster].
 */
@Deprecated(
    "Use NoOpAtriumErrorAdjuster from atrium-core, will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.reporting.erroradjusters.NoOpAtriumErrorAdjusterCommon")
)
abstract class NoOpAtriumErrorAdjusterCommon : AtriumErrorAdjuster {
    override fun adjustOtherThanStacks(throwable: Throwable) {}
    override fun adjust(throwable: Throwable) {}
}
