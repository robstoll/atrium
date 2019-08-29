package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

expect class MultiAtriumErrorAdjusterImpl(
    firstAdjuster: AtriumErrorAdjuster,
    secondAdjuster: AtriumErrorAdjuster,
    otherAdjusters: List<AtriumErrorAdjuster>
) : AtriumErrorAdjuster
