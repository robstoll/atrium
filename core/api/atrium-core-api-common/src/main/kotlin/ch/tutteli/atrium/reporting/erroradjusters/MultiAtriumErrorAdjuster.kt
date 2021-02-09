package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.reporting.AtriumErrorAdjuster

expect class MultiAtriumErrorAdjuster(
    firstAdjuster: AtriumErrorAdjuster,
    secondAdjuster: AtriumErrorAdjuster,
    otherAdjusters: List<AtriumErrorAdjuster>
) : AtriumErrorAdjuster
